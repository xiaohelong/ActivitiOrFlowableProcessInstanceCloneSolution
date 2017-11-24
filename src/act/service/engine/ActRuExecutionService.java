/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.service.engine;

import java.util.List;

import com.thinkgem.jeesite.modules.act.entity.Act;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.entity.engine.ActRuExecution;
import com.thinkgem.jeesite.modules.act.dao.engine.ActRuExecutionDao;

/**
 * act_ru_executionService
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
@Service
@Transactional(readOnly = true)
public class ActRuExecutionService extends CrudService<ActRuExecutionDao, ActRuExecution> implements ActEngineServiceBase<ActRuExecution>{

	public ActRuExecution get(String id) {
		return super.get(id);
	}
	public Boolean isTopLevel(ActRuExecution actRuExecution){
		Boolean retVal=false;
		if(actRuExecution.getParentId()==null&&actRuExecution.getSuperExec()==null&&findChildByExecutionID(actRuExecution)==null)//is top level
			retVal=true;
		return retVal;
	}
	public List<ActRuExecution> findList(ActRuExecution actRuExecution) {
		return super.findList(actRuExecution);
	}
	/**
	 * 通过ExecutionID找到其所关联的子流程
	 * @param actRuExecution
	 * @return 返回与给定执行体相关的正在执行的子流程实例
	 */
	public ActRuExecution findChildByExecutionID(ActRuExecution actRuExecution)
	{
		return dao.findChildByExecutionID(actRuExecution);
	}
	/**
	 * 通过流程实例找到其所有正在执行的子流程实例
	 * @param actRuExecution
	 * @return 正在执行的子流程实例表
	 */
	public List<ActRuExecution> findChildInRunning(ActRuExecution actRuExecution)
	{
		return dao.findChildInRunning(actRuExecution);
	}

	/**
	 * 找到流程下除自己外的所有正在执行体
	 * @param actRuExecution 要复制的流程实例
	 * @return 返回除自己外的所有正在执行体
	 */
	public List<ActRuExecution> findAllExecutions(ActRuExecution actRuExecution)
	{
		return dao.findAllExecutions(actRuExecution);
	}
	public Page<ActRuExecution> findPage(Page<ActRuExecution> page, ActRuExecution actRuExecution) {
		return super.findPage(page, actRuExecution);
	}
	
	@Transactional(readOnly = false)
	public void save(ActRuExecution actRuExecution) {
		super.save(actRuExecution);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActRuExecution actRuExecution) {
		super.delete(actRuExecution);
	}

	@Override
	public int saveBatch(List<ActRuExecution> data) {
		return dao.saveBatch(data);
	}
}