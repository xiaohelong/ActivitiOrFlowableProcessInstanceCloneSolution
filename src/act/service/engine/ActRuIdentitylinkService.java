/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.service.engine;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.entity.engine.ActRuIdentitylink;
import com.thinkgem.jeesite.modules.act.dao.engine.ActRuIdentitylinkDao;

/**
 * act_ru_identitylinkService
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
@Service
@Transactional(readOnly = true)
public class ActRuIdentitylinkService extends CrudService<ActRuIdentitylinkDao, ActRuIdentitylink> implements ActEngineServiceBase<ActRuIdentitylink>{

	public ActRuIdentitylink get(String id) {
		return super.get(id);
	}
	
	public List<ActRuIdentitylink> findList(ActRuIdentitylink actRuIdentitylink) {
		return super.findList(actRuIdentitylink);
	}

	/**
身份关链表（有为空的，也有不为空的，需要进行特殊处理即找出所有流程相关的任务或者流程本身再去重）
	 * @param actRuIdentitylink
	 * @return
	 */
	public List<ActRuIdentitylink> findAllIdentitylinkByProcInstId(ActRuIdentitylink actRuIdentitylink) {
		return dao.findAllIdentitylinkByProcInstId(actRuIdentitylink);
	}
	public Page<ActRuIdentitylink> findPage(Page<ActRuIdentitylink> page, ActRuIdentitylink actRuIdentitylink) {
		return super.findPage(page, actRuIdentitylink);
	}
	
	@Transactional(readOnly = false)
	public void save(ActRuIdentitylink actRuIdentitylink) {
		super.save(actRuIdentitylink);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActRuIdentitylink actRuIdentitylink) {
		super.delete(actRuIdentitylink);
	}

	@Override
	public int saveBatch(List<ActRuIdentitylink> data) {
		return dao.saveBatch(data);
	}
}