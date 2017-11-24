/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.service.engine;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.entity.engine.ActHiActinst;
import com.thinkgem.jeesite.modules.act.dao.engine.ActHiActinstDao;

/**
 * act_hi_actinstService
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
@Service
@Transactional(readOnly = true)
public class ActHiActinstService extends CrudService<ActHiActinstDao, ActHiActinst> implements  ActEngineServiceBase<ActHiActinst>{

	public ActHiActinst get(String id) {
		return super.get(id);
	}
	
	public List<ActHiActinst> findList(ActHiActinst actHiActinst) {
		return super.findList(actHiActinst);
	}
	
	public Page<ActHiActinst> findPage(Page<ActHiActinst> page, ActHiActinst actHiActinst) {
		return super.findPage(page, actHiActinst);
	}
	
	@Transactional(readOnly = false)
	public void save(ActHiActinst actHiActinst) {
		super.save(actHiActinst);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActHiActinst actHiActinst) {
		super.delete(actHiActinst);
	}

	@Override
	public int saveBatch(List<ActHiActinst> data) {
		return dao.saveBatch(data);
	}
}