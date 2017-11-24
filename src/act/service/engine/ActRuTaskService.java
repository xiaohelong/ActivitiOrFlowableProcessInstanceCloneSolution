/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.service.engine;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.entity.engine.ActRuTask;
import com.thinkgem.jeesite.modules.act.dao.engine.ActRuTaskDao;

/**
 * act_ru_taskService
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
@Service
@Transactional(readOnly = true)
public class ActRuTaskService extends CrudService<ActRuTaskDao, ActRuTask> implements ActEngineServiceBase<ActRuTask>{

	public ActRuTask get(String id) {
		return super.get(id);
	}
	
	public List<ActRuTask> findList(ActRuTask actRuTask) {
		return super.findList(actRuTask);
	}
	
	public Page<ActRuTask> findPage(Page<ActRuTask> page, ActRuTask actRuTask) {
		return super.findPage(page, actRuTask);
	}
	
	@Transactional(readOnly = false)
	public void save(ActRuTask actRuTask) {
		super.save(actRuTask);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActRuTask actRuTask) {
		super.delete(actRuTask);
	}

	@Override
	public int saveBatch(List<ActRuTask> data) {
		return dao.saveBatch(data);
	}
}