/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.service.engine;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.entity.engine.ActRuVariable;
import com.thinkgem.jeesite.modules.act.dao.engine.ActRuVariableDao;

/**
 * act_ru_variableService
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
@Service
@Transactional(readOnly = true)
public class ActRuVariableService extends CrudService<ActRuVariableDao, ActRuVariable> implements ActEngineServiceBase<ActRuVariable>{

	public ActRuVariable get(String id) {
		return super.get(id);
	}
	
	public List<ActRuVariable> findList(ActRuVariable actRuVariable) {
		return super.findList(actRuVariable);
	}
	
	public Page<ActRuVariable> findPage(Page<ActRuVariable> page, ActRuVariable actRuVariable) {
		return super.findPage(page, actRuVariable);
	}
	
	@Transactional(readOnly = false)
	public void save(ActRuVariable actRuVariable) {
		super.save(actRuVariable);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActRuVariable actRuVariable) {
		super.delete(actRuVariable);
	}

	@Override
	public int saveBatch(List<ActRuVariable> data) {
		return dao.saveBatch(data);
	}
}