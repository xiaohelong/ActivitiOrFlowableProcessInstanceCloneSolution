/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.entity.engine;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * act_hi_procinstEntity
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
public class ActHiProcinst extends DataEntity<ActHiProcinst> {
	
	private static final long serialVersionUID = 1L;
	private String procInstId;		// proc_inst_id_
	private String businessKey;		// business_key_
	private String procDefId;		// proc_def_id_
	private Date startTime;		// start_time_
	private Date endTime;		// end_time_
	private Long duration;		// duration_
	private String startUserId;		// start_user_id_
	private String startActId;		// start_act_id_
	private String endActId;		// end_act_id_
	private String superProcessInstanceId;		// super_process_instance_id_
	private String deleteReason;		// delete_reason_
	private String tenantId;		// tenant_id_
	private String name;		// name_
	
	public ActHiProcinst() {
		super();
	}

	public ActHiProcinst(String id){
		super(id);
	}

	@Length(min=0, max=192, message="proc_inst_id_长度必须介于 0 和 192 之间")
	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	
	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	
	@Length(min=0, max=192, message="proc_def_id_长度必须介于 0 和 192 之间")
	public String getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
	
	public String getStartUserId() {
		return startUserId;
	}

	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}
	
	public String getStartActId() {
		return startActId;
	}

	public void setStartActId(String startActId) {
		this.startActId = startActId;
	}
	
	public String getEndActId() {
		return endActId;
	}

	public void setEndActId(String endActId) {
		this.endActId = endActId;
	}
	
	@Length(min=0, max=192, message="super_process_instance_id_长度必须介于 0 和 192 之间")
	public String getSuperProcessInstanceId() {
		return superProcessInstanceId;
	}

	public void setSuperProcessInstanceId(String superProcessInstanceId) {
		this.superProcessInstanceId = superProcessInstanceId;
	}
	
	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}