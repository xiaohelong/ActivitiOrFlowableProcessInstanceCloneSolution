/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.service.engine;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.entity.engine.ActHiProcinst;
import com.thinkgem.jeesite.modules.act.dao.engine.ActHiProcinstDao;

/**
 * act_hi_procinstService
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
@Service
@Transactional(readOnly = true)
public class ActHiProcinstService extends CrudService<ActHiProcinstDao, ActHiProcinst> implements ActEngineServiceBase<ActHiProcinst>{

	public ActHiProcinst get(String id) {
		return super.get(id);
	}

	public List<ActHiProcinst> findChildActHiProcinst(ActHiProcinst actHiProcinst){
		return dao.findChildActHiProcinst(actHiProcinst);
	};
	public List<ActHiProcinst> findList(ActHiProcinst actHiProcinst) {
		return super.findList(actHiProcinst);
	}
	
	public Page<ActHiProcinst> findPage(Page<ActHiProcinst> page, ActHiProcinst actHiProcinst) {
		return super.findPage(page, actHiProcinst);
	}
	
	@Transactional(readOnly = false)
	public void save(ActHiProcinst actHiProcinst) {
		super.save(actHiProcinst);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActHiProcinst actHiProcinst) {
		super.delete(actHiProcinst);
	}

	@Override
	public int saveBatch(List<ActHiProcinst> data) {
		return dao.saveBatch(data);
	}
}