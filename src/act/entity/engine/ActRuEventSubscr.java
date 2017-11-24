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
 * act_ru_event_subscrEntity
 * @author xiaohelong
 * @version 2017-11-11
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
public class ActRuEventSubscr extends DataEntity<ActRuEventSubscr> {
	
	private static final long serialVersionUID = 1L;
	private String rev;		// rev_
	private String eventType;		// event_type_
	private String eventName;		// event_name_
	private String executionId;		// execution_id_
	private String procInstId;		// proc_inst_id_
	private String activityId;		// activity_id_
	private String configuration;		// configuration_
	private Date created;		// created_
	private String procDefId;		// proc_def_id_
	private String tenantId;		// tenant_id_
	
	public ActRuEventSubscr() {
		super();
	}

	public ActRuEventSubscr(String id){
		super(id);
	}

	@Length(min=0, max=11, message="rev_长度必须介于 0 和 11 之间")
	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}
	
	@Length(min=1, max=255, message="event_type_长度必须介于 1 和 255 之间")
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	@Length(min=0, max=255, message="event_name_长度必须介于 0 和 255 之间")
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	@Length(min=0, max=64, message="execution_id_长度必须介于 0 和 64 之间")
	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	
	@Length(min=0, max=64, message="proc_inst_id_长度必须介于 0 和 64 之间")
	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	
	@Length(min=0, max=64, message="activity_id_长度必须介于 0 和 64 之间")
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	@Length(min=0, max=255, message="configuration_长度必须介于 0 和 255 之间")
	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="created_不能为空")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	@Length(min=0, max=64, message="proc_def_id_长度必须介于 0 和 64 之间")
	public String getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	
	@Length(min=0, max=255, message="tenant_id_长度必须介于 0 和 255 之间")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
}