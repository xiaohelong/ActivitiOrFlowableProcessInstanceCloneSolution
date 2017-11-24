/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.service.engine;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.entity.engine.ActHiVarinst;
import com.thinkgem.jeesite.modules.act.dao.engine.ActHiVarinstDao;

/**
 * act_hi_varinstService
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
@Service
@Transactional(readOnly = true)
public class ActHiVarinstService extends CrudService<ActHiVarinstDao, ActHiVarinst> implements ActEngineServiceBase<ActHiVarinst>{

	public ActHiVarinst get(String id) {
		return super.get(id);
	}
	
	public List<ActHiVarinst> findList(ActHiVarinst actHiVarinst) {
		return super.findList(actHiVarinst);
	}
	
	public Page<ActHiVarinst> findPage(Page<ActHiVarinst> page, ActHiVarinst actHiVarinst) {
		return super.findPage(page, actHiVarinst);
	}
	
	@Transactional(readOnly = false)
	public void save(ActHiVarinst actHiVarinst) {
		super.save(actHiVarinst);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActHiVarinst actHiVarinst) {
		super.delete(actHiVarinst);
	}

	@Override
	public int saveBatch(List<ActHiVarinst> data) {
		return dao.saveBatch(data);
	}
}