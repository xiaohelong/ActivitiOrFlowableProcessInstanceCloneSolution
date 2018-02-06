/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package act.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.Reflections;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.act.entity.engine.*;
import com.thinkgem.jeesite.modules.act.service.engine.*;
import com.thinkgem.jeesite.modules.act.utils.ActIdReplace;
import com.thinkgem.jeesite.modules.order.constant.Arya;
import com.thinkgem.jeesite.modules.order.constant.Bran;
import com.thinkgem.jeesite.modules.order.entity.TOrdermanagerProc;
import com.thinkgem.jeesite.modules.order.service.TOrdermanagerProcService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.db.DbIdGenerator;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 对给定流程实例进行克隆
 *
 * @author xiaohelong
 * @version 2017-11-07
 */
@Service
@Transactional(readOnly = true)
public class ActCloneService extends BaseService {
    //Act History
    @Autowired
    private ActHiActinstService actHiActinstService;
    @Autowired
    private ActHiAttachmentService actHiAttachmentService;
    @Autowired
    private ActHiCommentService actHiCommentService;
    @Autowired
    private ActHiDetailService actHiDetailService;
    @Autowired
    private ActHiIdentitylinkService actHiIdentitylinkService;
    @Autowired
    private ActHiProcinstService actHiProcinstService;
    @Autowired
    private ActHiTaskinstService actHiTaskinstService;
    @Autowired
    private ActHiVarinstService actHiVarinstService;
    //Act Runtime
    @Autowired
    private ActRuEventSubscrService actRuEventSubscrService;
    @Autowired
    private ActRuExecutionService actRuExecutionService;
    @Autowired
    private ActRuIdentitylinkService actRuIdentitylinkService;
    @Autowired
    private ActRuJobService actRuJobService;
    @Autowired
    private ActRuTaskService actRuTaskService;
    @Autowired
    private ActRuVariableService actRuVariableService;
    @Autowired
    private ActGeBytearrayService actGeBytearrayService;
    /**
     * idSet
     * id集合，旧ID为Key,新ID为Value,每得到一个不同的老KD，
     * 就检测是否存在，存在即忽略，如果是检测到一个还没有加入的旧ID，则加入，并且给出对应的新ID
     */
    private Map<String,Map<String, String>> idSet=new HashMap<String, Map<String, String>>();//第一个String代表流程ID,主要用于支持递归处理
    /**
     * 排除不需要改的的字段,特别有如proc_def_id_字段
     */
    private Set<String> excludeFieldsSet=new HashSet<String>();

    /**
     * 根据指定的流程实例ID,以及要克隆的份数，决定克隆多少份流程实例ID。以指定的流程实例为根节点，对流程进行递归处理。
     * #act_hi_procinst 中 SUPER_PROCESS_INSTANCE_ID存储了父级流程实例ID
     * select * from act_hi_procinst where PROC_INST_ID_='7525af84eb34484dacfb67895b376734'
     * #ru
     * #act_ru_execution中 Parent_ID存储了父级exeuction Id(并行线路，call子流程时也存储父级实例ID)
     * #act_ru_execution中 Super_Exec_存储父流程(子流程时，存储父流程的在act_ru_execution中的ID，而不是实例ID，这是关联的一种方式)
     *
     * @param procInsID 欲复制的流程实例ID
     * @param copies    欲复制的流程实例ID
     * @return 克隆后的流程实例ID列表
     */
    @Transactional(readOnly = false)
    public List<ActHiProcinst> cloneProcInsByID(String procInsID, Integer copies) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        List<ActHiProcinst> retVal = new ArrayList<ActHiProcinst>();
        ActRuExecution actRuExecution = actRuExecutionService.get(procInsID);
        //here need to check procInsID to assure it is a  top level process instance
        if (actRuExecution == null) {
            //not running
            logger.info(procInsID + " process Instance is not running");//if you want to know more ,throw self defined exection here
            //Throw a running execption
            return null;
        }
        if (!actRuExecutionService.isTopLevel(actRuExecution)) {
            logger.info(actRuExecution.getId() + " process instance is not top level instance return");//if you want to know more ,throw self defined exection here
            return null;
        }
        if (copies > 0) {
            for (int i = 0; i < copies; i++) {
                ActHiProcinst newClonedProcinst = cloneProcInsByIDOnlyOneCopy(procInsID,null);
                if (newClonedProcinst != null) {
                    logger.info(" newId " + newClonedProcinst.getId() + " cloned");
                    retVal.add(newClonedProcinst);
                }
            }
        }
        return retVal;
    }

    /**
     * 核心思路：将此流程实例相关的的所有信息进行收集，再利用全文替换的思想，将相关联的ID号统一替换成新的ID号，这样就可以保证复制的流程关系一模一样。
     * 要保存记录成功，必须要移除掉所有的外键(主要是act_ru_)
     * 具体步骤如下：
     * //step1 begin:get all record
     * //step1 end:get all record
     * //step2 begin:read all data id to a set(some fields just like proc_def_id_ need to be excluded
     * //step2 end:read all data id to a set(some fields just like proc_def_id_ need to be excluded
     * //step3 begin:set all got data to isnewrecord and replace old id with it's related new id
     * //step3 end:set all got data to isnewrecord and replace old id with it's related new id
     * //step4 begin:save to database,and return
     * //step4 end:save to database,and return
     * 这里主要全文替换是ID，且是几个特有的字段。
     *
     * @param procInstanceID 需要克隆的实例流程
     * @param newParentId 新的父亲的ID,主要用于对子流程的递归
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Transactional(readOnly = false)
    public ActHiProcinst cloneProcInsByIDOnlyOneCopy(String procInstanceID,String newParentId) {
        ActHiProcinst needCloneProc=actHiProcinstService.get(procInstanceID);
        if(needCloneProc==null){//can't get the data
            return null;
        }
        initData(procInstanceID);
        Map<String,String> subIdSet=idSet.get(procInstanceID);

        if(!StringUtils.isBlank(newParentId))
        {//subprocess and it is in recursive call,need to set the new parentid to replace old parentid
            subIdSet.put(needCloneProc.getSuperProcessInstanceId(),newParentId);//this is the relation created;
        }
        //1. act_hi_actinst table
        ActHiActinst actHiActinstFindEntity = new ActHiActinst();
        actHiActinstFindEntity.setProcInstId(procInstanceID);
        List<ActHiActinst> actHiActinsts = actHiActinstService.findList(actHiActinstFindEntity);
        if(actHiActinsts!=null&&actHiActinsts.size()>0) {
            ActIdReplace<ActHiActinst> actHiActinstActIdReplace = new ActIdReplace<ActHiActinst>(ActHiActinst.class);
            actHiActinstActIdReplace.replaceCollection(actHiActinsts, subIdSet, excludeFieldsSet);//传过去的值按java的引用传递规则会对应更改并返回
            actHiActinstService.saveBatch(actHiActinsts);
        }

        //actHiActinstService.saveBatch(actHiActinsts);
        //2. act_hi_attachment table
        ActHiAttachment actHiAttachmentFindEntity = new ActHiAttachment();
        actHiAttachmentFindEntity.setProcInstId(procInstanceID);
        List<ActHiAttachment> actHiAttachments = actHiAttachmentService.findList(actHiAttachmentFindEntity);
        if(actHiAttachments!=null&&actHiAttachments.size()>0) {
            ActIdReplace<ActHiAttachment> actHiAttachmentActIdReplace = new ActIdReplace<ActHiAttachment>(ActHiAttachment.class);
            actHiAttachmentActIdReplace.replaceCollection(actHiAttachments, subIdSet, excludeFieldsSet);//传过去的值按java的引用传递规则会对应更改并返回
            actHiAttachmentService.saveBatch(actHiAttachments);
        }
        //3. act_hi_comment table
        //在系统中驳回和签收中没有写入流程实例（有为3rf空的，也有不为空的，需要进行特殊处理即找出所有流程相关的任务或者流程本身再去重）
        //可以先通过taskinst找到所有任务，再进行直接通过ProcInstId找出的记录，去重即可。
        ActHiComment actHiCommentFindEntity = new ActHiComment();
        actHiCommentFindEntity.setProcInstId(procInstanceID);
        List<ActHiComment> actHiComments = actHiCommentService.findAllCommentByProcInstId(actHiCommentFindEntity);
        if(actHiComments!=null&&actHiComments.size()>0) {
            ActIdReplace<ActHiComment> actHiCommentActIdReplace = new ActIdReplace<ActHiComment>(ActHiComment.class);
            actHiCommentActIdReplace.replaceCollection(actHiComments, subIdSet, excludeFieldsSet);//传过去的值按java的引用传递规则会对应更改并返回
            actHiCommentService.saveBatch(actHiComments);
        }
        //4. act_hi_detail table
        ActHiDetail actHiDetailFindEntity = new ActHiDetail();
        actHiDetailFindEntity.setProcInstId(procInstanceID);
        List<ActHiDetail> actHiDetails = actHiDetailService.findList(actHiDetailFindEntity);
        if(actHiDetails!=null&&actHiDetails.size()>0) {
            ActIdReplace<ActHiDetail> actHiDetailActIdReplace = new ActIdReplace<ActHiDetail>(ActHiDetail.class);
            actHiDetailActIdReplace.replaceCollection(actHiDetails, subIdSet, excludeFieldsSet);//传过去的值按java的引用传递规则会对应更改并返回
            actHiDetailService.saveBatch(actHiDetails);
        }
        //5. act_hi_identitylink table
        //身份关链表（有为空的，也有不为空的，需要进行特殊处理即找出所有流程相关的任务或者流程本身再去重）
        //可以先通过taskinst找到所有任务，再进行直接通过ProcInstId找出的记录，去重即可。
        ActHiIdentitylink actHiIdentitylinkFindEntity = new ActHiIdentitylink();
        actHiIdentitylinkFindEntity.setProcInstId(procInstanceID);
        List<ActHiIdentitylink> actHiIdentitylinks = actHiIdentitylinkService.findAllIdentitylinkByProcInstId(actHiIdentitylinkFindEntity);
        if(actHiIdentitylinks!=null&&actHiIdentitylinks.size()>0) {
            ActIdReplace<ActHiIdentitylink> actHiIdentitylinkActIdReplace = new ActIdReplace<ActHiIdentitylink>(ActHiIdentitylink.class);
            actHiIdentitylinkActIdReplace.replaceCollection(actHiIdentitylinks, subIdSet, excludeFieldsSet);//传过去的值按java的引用传递规则会对应更改并返回
            actHiIdentitylinkService.saveBatch(actHiIdentitylinks);
        }
        //6.act_hi_procinst table
        ActHiProcinst actHiProcinstFindEntity = new ActHiProcinst();
        actHiProcinstFindEntity.setProcInstId(procInstanceID);
        List<ActHiProcinst> actHiProcinsts = actHiProcinstService.findList(actHiProcinstFindEntity);
        if(actHiProcinsts!=null&&actHiProcinsts.size()>0)
        {
            ActIdReplace<ActHiProcinst> actHiProcinstActIdReplace=new ActIdReplace<ActHiProcinst>(ActHiProcinst.class);
            actHiProcinstActIdReplace.replaceCollection(actHiProcinsts,subIdSet,excludeFieldsSet);//传过去的值按java的引用传递规则会对应更改并返回
            actHiProcinstService.saveBatch(actHiProcinsts);
        }
        //7.act_hi_taskinst table
        ActHiTaskinst actHiTaskinstFindEntity = new ActHiTaskinst();
        actHiTaskinstFindEntity.setProcInstId(procInstanceID);
        List<ActHiTaskinst> actHiTaskinsts = actHiTaskinstService.findList(actHiTaskinstFindEntity);
        if(actHiTaskinsts!=null&&actHiActinsts.size()>0) {
            ActIdReplace<ActHiTaskinst> actHiTaskinstActIdReplace = new ActIdReplace<ActHiTaskinst>(ActHiTaskinst.class);
            actHiTaskinstActIdReplace.replaceCollection(actHiTaskinsts, subIdSet, excludeFieldsSet);//传过去的值按java的引用传递规则会对应更改并返回
            actHiTaskinstService.saveBatch(actHiTaskinsts);
        }
        //8.act_hi_varinst
        ActHiVarinst actHiVarinstFindEntity = new ActHiVarinst();
        actHiVarinstFindEntity.setProcInstId(procInstanceID);
        List<ActHiVarinst> actHiVarinsts = actHiVarinstService.findList(actHiVarinstFindEntity);
        if(actHiVarinsts!=null&&actHiVarinsts.size()>0) {
            ActIdReplace<ActHiVarinst> actHiVarinstActIdReplace = new ActIdReplace<ActHiVarinst>(ActHiVarinst.class);
            actHiVarinstActIdReplace.replaceCollection(actHiVarinsts, subIdSet, excludeFieldsSet);//传过去的值按java的引用传递规则会对应更改并返回
            actHiVarinstService.saveBatch(actHiVarinsts);
        }
        //act_RU part
        //1.act_ru_event_subscr table
        ActRuEventSubscr actRuEventSubscrFindEntity = new ActRuEventSubscr();
        actRuEventSubscrFindEntity.setProcInstId(procInstanceID);
        List<ActRuEventSubscr> actRuEventSubscrs = actRuEventSubscrService.findList(actRuEventSubscrFindEntity);
        if(actRuEventSubscrs!=null&&actRuEventSubscrs.size()>0) {
            ActIdReplace<ActRuEventSubscr> actRuEventSubscrActIdReplace = new ActIdReplace<ActRuEventSubscr>(ActRuEventSubscr.class);
            actRuEventSubscrActIdReplace.replaceCollection(actRuEventSubscrs, subIdSet, excludeFieldsSet);//传过去的值按java的引用传递规则会对应更改并返回
            actRuEventSubscrService.saveBatch(actRuEventSubscrs);
        }
        //2.act_ru_execution
        ActRuExecution actRuExecutionFindEntity = new ActRuExecution();
        actRuExecutionFindEntity.setProcInstId(procInstanceID);
        List<ActRuExecution> actRuExecutions = actRuExecutionService.findList(actRuExecutionFindEntity);
        if(actRuExecutions!=null&&actRuExecutions.size()>0)
        {
            ActIdReplace<ActRuExecution> actRuExecutionActIdReplace=new ActIdReplace<ActRuExecution>(ActRuExecution.class);
            actRuExecutionActIdReplace.replaceCollection(actRuExecutions,subIdSet,excludeFieldsSet);//传过去的值按java的引用传递规则会对应更改并返回
            actRuExecutionService.saveBatch(actRuExecutions);
        }

        //身份关链表（有为空的，也有不为空的，需要进行特殊处理即找出所有流程相关的任务或者流程本身再去重）
        //3.act_ru_identityservice table
        ActRuIdentitylink actRuIdentitylinkFindEntity = new ActRuIdentitylink();
        actRuIdentitylinkFindEntity.setProcInstId(procInstanceID);
        List<ActRuIdentitylink> actRuIdentitylinks = actRuIdentitylinkService.findAllIdentitylinkByProcInstId(actRuIdentitylinkFindEntity);
        if(actRuIdentitylinks!=null&&actRuIdentitylinks.size()>0)
        {
            ActIdReplace<ActRuIdentitylink> actRuIdentitylinkActIdReplace=new ActIdReplace<ActRuIdentitylink>(ActRuIdentitylink.class);
            actRuIdentitylinkActIdReplace.replaceCollection(actRuIdentitylinks,subIdSet,excludeFieldsSet);//传过去的值按java的引用传递规则会对应更改并返回
            actRuIdentitylinkService.saveBatch(actRuIdentitylinks);
        }

        //5.act_ru_task table
        ActRuTask actRuTaskFindEntity = new ActRuTask();
        actRuTaskFindEntity.setProcInstId(procInstanceID);
        List<ActRuTask> actRuTasks = actRuTaskService.findList(actRuTaskFindEntity);
        if(actRuTasks!=null&&actRuTasks.size()>0)
        {
            ActIdReplace<ActRuTask> actRuTaskActIdReplace=new ActIdReplace<ActRuTask>(ActRuTask.class);
            actRuTaskActIdReplace.replaceCollection(actRuTasks,subIdSet,excludeFieldsSet);//传过去的值按java的引用传递规则会对应更改并返回
            actRuTaskService.saveBatch(actRuTasks);
        }

        //4.act_ru_job table
        ActRuJob actRuJobFindEnity = new ActRuJob();
        actRuJobFindEnity.setProcessInstanceId(procInstanceID);
        List<ActRuJob> actRuJobs = actRuJobService.findList(actRuJobFindEnity);
        if(actRuJobs!=null&&actRuJobs.size()>0) {
            ActIdReplace<ActRuJob> actRuJobActIdReplace = new ActIdReplace<ActRuJob>(ActRuJob.class);
            actRuJobActIdReplace.replaceCollection(actRuJobs, subIdSet, excludeFieldsSet);//传过去的值按java的引用传递规则会对应更改并返回
            actRuJobService.saveBatch(actRuJobs);
        }

        //6.act_ru_variable table
        ActRuVariable actRuVariableFindEntity = new ActRuVariable();
        actRuVariableFindEntity.setProcInstId(procInstanceID);
        List<ActRuVariable> actRuVariables = actRuVariableService.findList(actRuVariableFindEntity);
        if(actRuVariables!=null&&actRuVariables.size()>0) {
            ActIdReplace<ActRuVariable> actRuVariableActIdReplace = new ActIdReplace<ActRuVariable>(ActRuVariable.class);
            actRuVariableActIdReplace.replaceCollection(actRuVariables, subIdSet, excludeFieldsSet);//传过去的值按java的引用传递规则会对应更改并返回
            actRuVariableService.saveBatch(actRuVariables);
        }

        //7.act_ge_bytearray table ru hi variable related table
        List<ActGeBytearray> actGeBytearrays=actGeBytearrayService.findProcRelatedRecord(needCloneProc);
        if(actGeBytearrays!=null&&actGeBytearrays.size()>0){
            ActIdReplace<ActGeBytearray> actGeBytearrayActIdReplace = new ActIdReplace<ActGeBytearray>(ActGeBytearray.class);
            actGeBytearrayActIdReplace.replaceCollection(actGeBytearrays, subIdSet, excludeFieldsSet);//传过去的值按java的引用传递规则会对应更改并返回
            actGeBytearrayService.saveBatch(actGeBytearrays);
        }

        //对于正在执行的也会在hi_proc_inst中有记录，因此只要从hi_proc_inst中获取到子流程ID即可进行递归处理。
        ActHiProcinst findChildProc=new ActHiProcinst();
        findChildProc.setSuperProcessInstanceId(procInstanceID);
        List<ActHiProcinst> childProcesses=actHiProcinstService.findChildActHiProcinst(findChildProc);
        if(childProcesses!=null){
            for(int i=0;i<childProcesses.size();i++){
                //recursive call 递归时，是需要以已完成的流程实例为基础,，因为递归时需要复制已经结束的(正在运行的肯定要支持),并且需要父节点更新过去。
                ActHiProcinst childProcess=childProcesses.get(i);
                cloneProcInsByIDOnlyOneCopy(childProcess.getId(),subIdSet.get(procInstanceID));//子递归时，需要将新的父ID传入,不需要保存该返回值，因为直接存入了数据库，只需要在顶级获取即可
            }
        }

        ActHiProcinst newCloneProc=null;
        try {
            newCloneProc=(ActHiProcinst) BeanUtils.cloneBean(needCloneProc);
            newCloneProc.setId(subIdSet.get(procInstanceID));//返回新的ID值
            newCloneProc.setProcInstId(subIdSet.get(procInstanceID));//返回新的ID值实例
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        idSet.put(procInstanceID,subIdSet);//对更改过的值进行回更新
        return newCloneProc;//todo 返回新克隆的ID
    }
    /**
     * Constructor 初始化
     */
    private void initData(String procInstId) {
        Map<String,String> subIdSet=new HashMap<String, String>();
        idSet.put(procInstId,subIdSet);
        //todo这里有问题
        subIdSet.put(procInstId,IdGen.uuid());//initialize
        excludeFieldsSet.add("getProcDefId");
    }
}
