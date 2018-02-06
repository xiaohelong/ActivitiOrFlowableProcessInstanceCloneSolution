package act.utils;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.act.entity.engine.ActHiActinst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 针对act需要克隆的类别，做一个模板通用工具，免得每一个都要写一次
 * Created by xiaohelong on 2017/11/11.
 */
public class ActIdReplace<T extends DataEntity<T>> {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private final Class<T> clazz;
    public ActIdReplace(Class<T> clazz){
        this.clazz=clazz;
    }
    /**
     * 替换指定集合的ID，这里利用了Java函数的参数值传递为引用传递的特性，即会修改传过来的参数（如果没有，那就是错的，这里必须是引用规则)
     * @param dataList
     * @param idSet
     * @param excludeFieldsSet
     */
    public void replaceCollection(List<T> dataList, Map<String, String> idSet,  Set<String> excludeFieldsSet){
        if (dataList != null && dataList.size() > 0) {
            try {
                for (int i = 0; i < dataList.size(); i++) {
                    T data = dataList.get(i);
                    data.setIsNewRecord(true);
                    /**循环读取所有属性值(核心代码区kernel code)
                     * 1.检测是不是排名和属性名
                     * 2.并检使用该值检测对应的值是不是字符型
                     * 3.如果是字符，则是否符合uuid的正则'
                     * 4.如果是id，则看此id是否已在id集合中，则忽略，如果集合中没有出现过，则加入该集合。
                     * 5.为了节省效率，直接在此循环中，将实体的id替换为集合中旧实体对应的id(不要再在后面统一处理了，减少循环，就会提升效率）
                     */
                    Method[] publicMethods = Class.forName(clazz.getName()).getMethods();//get all String fields(private protected public
                    if (publicMethods != null && publicMethods.length > 0) {
                        for (int m = 0; m < publicMethods.length; m++) {
                            String methodName = publicMethods[m].getName();
                            if ((!excludeFieldsSet.contains(methodName) && methodName.toLowerCase().contains("id")&&methodName.startsWith("get"))||methodName.equals("getSuperExec")) {//notExcluded and its' name start with get
                                Class returnType=publicMethods[m].getReturnType();//获取返回值的类型
                                if(returnType.getName().equals(String.class.getName())) {//id 只有字符串里面才会有的
                                    logger.info("get method name:"+methodName+" in  class"+data.getClass().getName());
                                    String getFieldData = (String) publicMethods[m].invoke(data);
                                    //act里面的id都是32位的,用DbIdGenerator生成,为了以示区别，我们用IdGen生成的32位的。生成
                                    if(getFieldData!=null&&getFieldData.length()==32)
                                    {
                                        if(!idSet.containsKey(getFieldData)){
                                            idSet.put(getFieldData, IdGen.uuid());
                                        }
                                        //调用set函数，传入新ID值
                                        String newId=idSet.get(getFieldData);//找出对应的新的用于替换的ID值，调用set命令
                                        String setMethodName=methodName.replace("get","set");
                                        Method setMethod=data.getClass().getMethod(setMethodName,String.class);
                                        logger.info("set method name:"+setMethodName+" in  class"+data.getClass().getName());
                                        setMethod.invoke(data,newId);//将老的ID改至对应的新ID，全部
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                logger.error(e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error(e.getMessage());
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            }catch (NoSuchMethodException e){
;                logger.error(e.getMessage());
            }
        }
    }
}
