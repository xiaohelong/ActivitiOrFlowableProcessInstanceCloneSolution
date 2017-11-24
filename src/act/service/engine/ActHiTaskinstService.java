/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.service.engine;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.entity.engine.ActHiTaskinst;
import com.thinkgem.jeesite.modules.act.dao.engine.ActHiTaskinstDao;

/**
 * act_hi_taskinstService
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
@Service
@Transactional(readOnly = true)
public class ActHiTaskinstService extends CrudService<ActHiTaskinstDao, ActHiTaskinst> implements ActEngineServiceBase<ActHiTaskinst>{

	public ActHiTaskinst get(String id) {
		return super.get(id);
	}
	
	public List<ActHiTaskinst> findList(ActHiTaskinst actHiTaskinst) {
		return super.findList(actHiTaskinst);
	}
	
	public Page<ActHiTaskinst> findPage(Page<ActHiTaskinst> page, ActHiTaskinst actHiTaskinst) {
		return super.findPage(page, actHiTaskinst);
	}
	
	@Transactional(readOnly = false)
	public void save(ActHiTaskinst actHiTaskinst) {
		super.save(actHiTaskinst);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActHiTaskinst actHiTaskinst) {
		super.delete(actHiTaskinst);
	}

	@Override
	public int saveBatch(List<ActHiTaskinst> data) {
		return dao.saveBatch(data);
	}
}