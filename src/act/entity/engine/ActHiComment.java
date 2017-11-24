/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.entity.engine;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * act_hi_commentEntity
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
public class ActHiComment extends DataEntity<ActHiComment> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// type_
	private Date time;		// time_
	private User user;		// user_id_
	private String taskId;		// task_id_
	private String procInstId;		// proc_inst_id_
	private String action;		// action_
	private String message;		// message_
	private String fullMsg;		// full_msg_
	
	public ActHiComment() {
		super();
	}

	public ActHiComment(String id){
		super(id);
	}

	@Length(min=0, max=255, message="type_长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="time_不能为空")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=64, message="task_id_长度必须介于 0 和 64 之间")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	@Length(min=0, max=64, message="proc_inst_id_长度必须介于 0 和 64 之间")
	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	
	@Length(min=0, max=255, message="action_长度必须介于 0 和 255 之间")
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	@Length(min=0, max=4000, message="message_长度必须介于 0 和 4000 之间")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getFullMsg() {
		return fullMsg;
	}

	public void setFullMsg(String fullMsg) {
		this.fullMsg = fullMsg;
	}
	
}