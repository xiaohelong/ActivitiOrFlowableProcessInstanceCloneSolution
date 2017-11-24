/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.entity.engine;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * act_hi_actinstEntity
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
public class ActHiActinst extends DataEntity<ActHiActinst> {
	
	private static final long serialVersionUID = 1L;
	private String procDefId;		// proc_def_id_
	private String procInstId;		// proc_inst_id_
	private String executionId;		// execution_id_
	private String actId;		// act_id_
	private String taskId;		// task_id_
	private String callProcInstId;		// call_proc_inst_id_
	private String actName;		// act_name_
	private String actType;		// act_type_
	private String assignee;		// assignee_
	private Date startTime;		// start_time_
	private Date endTime;		// end_time_
	private Long duration;		// duration_
	private String tenantId;		// tenant_id_
	
	public ActHiActinst() {
		super();
	}

	public ActHiActinst(String id){
		super(id);
	}

	@Length(min=1, max=64, message="proc_def_id_长度必须介于 1 和 64 之间")
	public String getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	
	@Length(min=1, max=64, message="proc_inst_id_长度必须介于 1 和 64 之间")
	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	
	@Length(min=1, max=64, message="execution_id_长度必须介于 1 和 64 之间")
	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	
	@Length(min=1, max=255, message="act_id_长度必须介于 1 和 255 之间")
	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}
	
	@Length(min=0, max=64, message="task_id_长度必须介于 0 和 64 之间")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	@Length(min=0, max=64, message="call_proc_inst_id_长度必须介于 0 和 64 之间")
	public String getCallProcInstId() {
		return callProcInstId;
	}

	public void setCallProcInstId(String callProcInstId) {
		this.callProcInstId = callProcInstId;
	}
	
	@Length(min=0, max=255, message="act_name_长度必须介于 0 和 255 之间")
	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}
	
	@Length(min=1, max=255, message="act_type_长度必须介于 1 和 255 之间")
	public String getActType() {
		return actType;
	}

	public void setActType(String actType) {
		this.actType = actType;
	}
	
	@Length(min=0, max=255, message="assignee_长度必须介于 0 和 255 之间")
	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="start_time_不能为空")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
	@Length(min=0, max=255, message="tenant_id_长度必须介于 0 和 255 之间")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
}