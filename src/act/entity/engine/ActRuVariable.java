/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.entity.engine;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * act_ru_variableEntity
 * @author xiaohelong
 * @version 2017-11-08
 *  * email:xiaohelong2005@126.com
 *       xiaohelong2005@gmail.com
 *       twitter.com/xiaohelong
 */
public class ActRuVariable extends DataEntity<ActRuVariable> {
	
	private static final long serialVersionUID = 1L;
	private String rev;		// rev_
	private String type;		// type_
	private String name;		// name_
	private String executionId;		// execution_id_
	private String procInstId;		// proc_inst_id_
	private String taskId;		// task_id_
	private String bytearrayId;		// bytearray_id_
	private String double_;		// double_
	private Long long_;		// long_
	private String text;		// text_
	private String text2;		// text2_
	
	public ActRuVariable() {
		super();
	}

	public ActRuVariable(String id){
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
	
	@Length(min=1, max=255, message="name_长度必须介于 1 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	@Length(min=0, max=64, message="task_id_长度必须介于 0 和 64 之间")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	@Length(min=0, max=64, message="bytearray_id_长度必须介于 0 和 64 之间")
	public String getBytearrayId() {
		return bytearrayId;
	}

	public void setBytearrayId(String bytearrayId) {
		this.bytearrayId = bytearrayId;
	}
	
	public String getDouble() {
		return double_;
	}

	public void setDouble(String double_) {
		this.double_ = double_;
	}
	
	public Long getLong() {
		return long_;
	}

	public void setLong(Long long_) {
		this.long_ = long_;
	}
	
	@Length(min=0, max=4000, message="text_长度必须介于 0 和 4000 之间")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Length(min=0, max=4000, message="text2_长度必须介于 0 和 4000 之间")
	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}
	
}