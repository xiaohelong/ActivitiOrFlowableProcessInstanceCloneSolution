/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.service.engine;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.entity.engine.ActRuJob;
import com.thinkgem.jeesite.modules.act.dao.engine.ActRuJobDao;

/**
 * act_ru_jobService
 * @author xiaohelong
 * @version 2017-11-11
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
@Service
@Transactional(readOnly = true)
public class ActRuJobService extends CrudService<ActRuJobDao, ActRuJob> implements  ActEngineServiceBase<ActRuJob>{

	public ActRuJob get(String id) {
		return super.get(id);
	}
	
	public List<ActRuJob> findList(ActRuJob actRuJob) {
		return super.findList(actRuJob);
	}
	
	public Page<ActRuJob> findPage(Page<ActRuJob> page, ActRuJob actRuJob) {
		return super.findPage(page, actRuJob);
	}
	
	@Transactional(readOnly = false)
	public void save(ActRuJob actRuJob) {
		super.save(actRuJob);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActRuJob actRuJob) {
		super.delete(actRuJob);
	}

	@Override
	public int saveBatch(List<ActRuJob> data) {
		return dao.saveBatch(data);
	}
}