/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.service.engine;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.entity.engine.ActHiAttachment;
import com.thinkgem.jeesite.modules.act.dao.engine.ActHiAttachmentDao;

/**
 * act_hi_attachmentService
 * @author xiaohelong
 * @version 2017-11-11
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
@Service
@Transactional(readOnly = true)
public class ActHiAttachmentService extends CrudService<ActHiAttachmentDao, ActHiAttachment>  implements  ActEngineServiceBase<ActHiAttachment>{

	public ActHiAttachment get(String id) {
		return super.get(id);
	}
	
	public List<ActHiAttachment> findList(ActHiAttachment actHiAttachment) {
		return super.findList(actHiAttachment);
	}
	
	public Page<ActHiAttachment> findPage(Page<ActHiAttachment> page, ActHiAttachment actHiAttachment) {
		return super.findPage(page, actHiAttachment);
	}
	
	@Transactional(readOnly = false)
	public void save(ActHiAttachment actHiAttachment) {
		super.save(actHiAttachment);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActHiAttachment actHiAttachment) {
		super.delete(actHiAttachment);
	}

	@Override
	public int saveBatch(List<ActHiAttachment> data) {
		return dao.saveBatch(data);
	}
}