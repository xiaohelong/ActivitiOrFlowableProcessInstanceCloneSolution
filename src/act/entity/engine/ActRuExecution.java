/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.entity.engine;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * act_ru_executionEntity
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
public class ActRuExecution extends DataEntity<ActRuExecution> {
	
	private static final long serialVersionUID = 1L;
	private String rev;		// rev_
	private String procInstId;		// proc_inst_id_
	private String businessKey;		// business_key_
	private String parentId;		// parent_id_
	private String procDefId;		// proc_def_id_
	private String superExec;		// super_exec_
	private String actId;		// act_id_
	private String isActive;		// is_active_
	private String isConcurrent;		// is_concurrent_
	private String isScope;		// is_scope_
	private String isEventScope;		// is_event_scope_
	private String suspensionState;		// suspension_state_
	private String cachedEntState;		// cached_ent_state_
	private String tenantId;		// tenant_id_
	private String name;		// name_
	private Date lockTime;		// lock_time_
	
	public ActRuExecution() {
		super();
	}

	public ActRuExecution(String id){
		super(id);
	}

	@Length(min=0, max=11, message="rev_长度必须介于 0 和 11 之间")
	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}
	
	@Length(min=0, max=64, message="proc_inst_id_长度必须介于 0 和 64 之间")
	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	
	@Length(min=0, max=255, message="business_key_长度必须介于 0 和 255 之间")
	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	
	@Length(min=0, max=64, message="parent_id_长度必须介于 0 和 64 之间")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Length(min=0, max=64, message="proc_def_id_长度必须介于 0 和 64 之间")
	public String getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	
	@Length(min=0, max=64, message="super_exec_长度必须介于 0 和 64 之间")
	public String getSuperExec() {
		return superExec;
	}

	public void setSuperExec(String superExec) {
		this.superExec = superExec;
	}
	
	@Length(min=0, max=255, message="act_id_长度必须介于 0 和 255 之间")
	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}
	
	@Length(min=0, max=4, message="is_active_长度必须介于 0 和 4 之间")
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@Length(min=0, max=4, message="is_concurrent_长度必须介于 0 和 4 之间")
	public String getIsConcurrent() {
		return isConcurrent;
	}

	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}
	
	@Length(min=0, max=4, message="is_scope_长度必须介于 0 和 4 之间")
	public String getIsScope() {
		return isScope;
	}

	public void setIsScope(String isScope) {
		this.isScope = isScope;
	}
	
	@Length(min=0, max=4, message="is_event_scope_长度必须介于 0 和 4 之间")
	public String getIsEventScope() {
		return isEventScope;
	}

	public void setIsEventScope(String isEventScope) {
		this.isEventScope = isEventScope;
	}
	
	@Length(min=0, max=11, message="suspension_state_长度必须介于 0 和 11 之间")
	public String getSuspensionState() {
		return suspensionState;
	}

	public void setSuspensionState(String suspensionState) {
		this.suspensionState = suspensionState;
	}
	
	@Length(min=0, max=11, message="cached_ent_state_长度必须介于 0 和 11 之间")
	public String getCachedEntState() {
		return cachedEntState;
	}

	public void setCachedEntState(String cachedEntState) {
		this.cachedEntState = cachedEntState;
	}
	
	@Length(min=0, max=255, message="tenant_id_长度必须介于 0 和 255 之间")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	@Length(min=0, max=255, message="name_长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	
}