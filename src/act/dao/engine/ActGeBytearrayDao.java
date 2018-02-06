/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package act.dao.engine;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.act.entity.engine.ActGeBytearray;
import com.thinkgem.jeesite.modules.act.entity.engine.ActHiProcinst;

import java.util.List;

/**
 * 变量表关联的表DAO接口
 * @author xiaohelong2005@126.com
 * @version 2018-01-16
 */
@MyBatisDao
public interface ActGeBytearrayDao extends ActEngineDaoBase<ActGeBytearray> {
    public List<ActGeBytearray> findProcRelatedRecord(ActHiProcinst proc);
}