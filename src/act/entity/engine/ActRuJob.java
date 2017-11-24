/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.entity.engine;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * act_ru_jobEntity
 * @author xiaohelong
 * @version 2017-11-11
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
public class ActRuJob extends DataEntity<ActRuJob> {
	
	private static final long serialVersionUID = 1L;
	private String rev;		// rev_
	private String type;		// type_
	private Date lockExpTime;		// lock_exp_time_
	private String lockOwner;		// lock_owner_
	private String exclusive;		// exclusive_
	private String executionId;		// execution_id_
	private String processInstanceId;		// process_instance_id_
	private String procDefId;		// proc_def_id_
	private String retries;		// retries_
	private String exceptionStackId;		// exception_stack_id_
	private String exceptionMsg;		// exception_msg_
	private Date duedate;		// duedate_
	private String repeat;		// repeat_
	private String handlerType;		// handler_type_
	private String handlerCfg;		// handler_cfg_
	private String tenantId;		// tenant_id_
	
	public ActRuJob() {
		super();
	}

	public ActRuJob(String id){
		super(id);
	}

	@Length(min=0, max=11, message="rev_长度必须介于 0 和 11 之间")
	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}
	
	@Length(min=1, max=255, message="type_长度必须介于 1 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLockExpTime() {
		return lockExpTime;
	}

	public void setLockExpTime(Date lockExpTime) {
		this.lockExpTime = lockExpTime;
	}
	
	@Length(min=0, max=255, message="lock_owner_长度必须介于 0 和 255 之间")
	public String getLockOwner() {
		return lockOwner;
	}

	public void setLockOwner(String lockOwner) {
		this.lockOwner = lockOwner;
	}
	
	@Length(min=0, max=1, message="exclusive_长度必须介于 0 和 1 之间")
	public String getExclusive() {
		return exclusive;
	}

	public void setExclusive(String exclusive) {
		this.exclusive = exclusive;
	}
	
	@Length(min=0, max=64, message="execution_id_长度必须介于 0 和 64 之间")
	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	
	@Length(min=0, max=64, message="process_instance_id_长度必须介于 0 和 64 之间")
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	@Length(min=0, max=64, message="proc_def_id_长度必须介于 0 和 64 之间")
	public String getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	
	@Length(min=0, max=11, message="retries_长度必须介于 0 和 11 之间")
	public String getRetries() {
		return retries;
	}

	public void setRetries(String retries) {
		this.retries = retries;
	}
	
	@Length(min=0, max=64, message="exception_stack_id_长度必须介于 0 和 64 之间")
	public String getExceptionStackId() {
		return exceptionStackId;
	}

	public void setExceptionStackId(String exceptionStackId) {
		this.exceptionStackId = exceptionStackId;
	}
	
	@Length(min=0, max=4000, message="exception_msg_长度必须介于 0 和 4000 之间")
	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	
	@Length(min=0, max=255, message="repeat_长度必须介于 0 和 255 之间")
	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}
	
	@Length(min=0, max=255, message="handler_type_长度必须介于 0 和 255 之间")
	public String getHandlerType() {
		return handlerType;
	}

	public void setHandlerType(String handlerType) {
		this.handlerType = handlerType;
	}
	
	@Length(min=0, max=4000, message="handler_cfg_长度必须介于 0 和 4000 之间")
	public String getHandlerCfg() {
		return handlerCfg;
	}

	public void setHandlerCfg(String handlerCfg) {
		this.handlerCfg = handlerCfg;
	}
	
	@Length(min=0, max=255, message="tenant_id_长度必须介于 0 和 255 之间")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
}