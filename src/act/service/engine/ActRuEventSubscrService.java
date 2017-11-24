/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.service.engine;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.entity.engine.ActRuEventSubscr;
import com.thinkgem.jeesite.modules.act.dao.engine.ActRuEventSubscrDao;

/**
 * act_ru_event_subscrService
 * @author xiaohelong
 * @version 2017-11-11
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
@Service
@Transactional(readOnly = true)
public class ActRuEventSubscrService extends CrudService<ActRuEventSubscrDao, ActRuEventSubscr> implements ActEngineServiceBase<ActRuEventSubscr>{

	public ActRuEventSubscr get(String id) {
		return super.get(id);
	}
	
	public List<ActRuEventSubscr> findList(ActRuEventSubscr actRuEventSubscr) {
		return super.findList(actRuEventSubscr);
	}
	
	public Page<ActRuEventSubscr> findPage(Page<ActRuEventSubscr> page, ActRuEventSubscr actRuEventSubscr) {
		return super.findPage(page, actRuEventSubscr);
	}
	
	@Transactional(readOnly = false)
	public void save(ActRuEventSubscr actRuEventSubscr) {
		super.save(actRuEventSubscr);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActRuEventSubscr actRuEventSubscr) {
		super.delete(actRuEventSubscr);
	}

	@Override
	public int saveBatch(List<ActRuEventSubscr> data) {
		return dao.saveBatch(data);
	}
}