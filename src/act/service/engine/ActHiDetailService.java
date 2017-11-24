/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.service.engine;

import java.util.List;

import com.thinkgem.jeesite.modules.act.entity.engine.ActHiComment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.entity.engine.ActHiDetail;
import com.thinkgem.jeesite.modules.act.dao.engine.ActHiDetailDao;

/**
 * act_hi_detailService
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
@Service
@Transactional(readOnly = true)
public class ActHiDetailService extends CrudService<ActHiDetailDao, ActHiDetail> implements ActEngineServiceBase<ActHiDetail>{

	public ActHiDetail get(String id) {
		return super.get(id);
	}
	
	public List<ActHiDetail> findList(ActHiDetail actHiDetail) {
		return super.findList(actHiDetail);
	}
	
	public Page<ActHiDetail> findPage(Page<ActHiDetail> page, ActHiDetail actHiDetail) {
		return super.findPage(page, actHiDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(ActHiDetail actHiDetail) {
		super.save(actHiDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActHiDetail actHiDetail) {
		super.delete(actHiDetail);
	}
	@Override
	public int saveBatch(List<ActHiDetail> data) {
		return dao.saveBatch(data);
	}
}