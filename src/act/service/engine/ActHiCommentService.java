/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.service.engine;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.act.entity.engine.ActHiComment;
import com.thinkgem.jeesite.modules.act.dao.engine.ActHiCommentDao;

/**
 * act_hi_commentService
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
@Service
@Transactional(readOnly = true)
public class ActHiCommentService extends CrudService<ActHiCommentDao, ActHiComment> implements ActEngineServiceBase<ActHiComment> {

	public ActHiComment get(String id) {
		return super.get(id);
	}
	
	public List<ActHiComment> findList(ActHiComment actHiComment) {
		return super.findList(actHiComment);
	}

	/**
	 *  //在系统中驳回和签收中没有写入流程实例（有为3rf空的，也有不为空的，需要进行特殊处理即找出所有流程相关的任务或者流程本身再去重）
	 * @param actHiComment
	 * @return
	 */
	public List<ActHiComment> findAllCommentByProcInstId(ActHiComment actHiComment){
	     return dao.findAllCommentByProcInstId(actHiComment);
	};
	public Page<ActHiComment> findPage(Page<ActHiComment> page, ActHiComment actHiComment) {
		return super.findPage(page, actHiComment);
	}
	
	@Transactional(readOnly = false)
	public void save(ActHiComment actHiComment) {
		super.save(actHiComment);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActHiComment actHiComment) {
		super.delete(actHiComment);
	}

	@Override
	public int saveBatch(List<ActHiComment> data) {
		return dao.saveBatch(data);
	}
}