/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.service.engine;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.entity.engine.ActHiIdentitylink;
import com.thinkgem.jeesite.modules.act.dao.engine.ActHiIdentitylinkDao;

/**
 * act_hi_identitylinkService
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
@Service
@Transactional(readOnly = true)
public class ActHiIdentitylinkService extends CrudService<ActHiIdentitylinkDao, ActHiIdentitylink> implements ActEngineServiceBase<ActHiIdentitylink> {

	public ActHiIdentitylink get(String id) {
		return super.get(id);
	}
	public List<ActHiIdentitylink> findList(ActHiIdentitylink actHiIdentitylink) {
		return super.findList(actHiIdentitylink);
	}

	/**
	 * 身份关链表（有为空的，也有不为空的，需要进行特殊处理即找出所有流程相关的任务或者流程本身再去重）
	 * @param actHiIdentitylink
	 * @return
	 */
	public List<ActHiIdentitylink> findAllIdentitylinkByProcInstId(ActHiIdentitylink actHiIdentitylink){
		return dao.findAllIdentitylinkByProcInstId(actHiIdentitylink);
	}
	public Page<ActHiIdentitylink> findPage(Page<ActHiIdentitylink> page, ActHiIdentitylink actHiIdentitylink) {
		return super.findPage(page, actHiIdentitylink);
	}
	
	@Transactional(readOnly = false)
	public void save(ActHiIdentitylink actHiIdentitylink) {
		super.save(actHiIdentitylink);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActHiIdentitylink actHiIdentitylink) {
		super.delete(actHiIdentitylink);
	}

	@Override
	public int saveBatch(List<ActHiIdentitylink> data) {
		return dao.saveBatch(data);
	}
}