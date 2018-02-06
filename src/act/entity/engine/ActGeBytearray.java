/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package act.entity.engine;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 变量表关联的表Entity
 * @author xiaohelong2005@126.com
 * @version 2018-01-16
 */
public class ActGeBytearray extends DataEntity<ActGeBytearray> {
	
	private static final long serialVersionUID = 1L;
	private String rev;		// rev_
	private String name;		// name_
	private String deploymentId;		// deployment_id_
	private String bytes;		// bytes_
	private String generated;		// generated_
	
	public ActGeBytearray() {
		super();
	}

	public ActGeBytearray(String id){
		super(id);
	}

	@Length(min=0, max=11, message="rev_长度必须介于 0 和 11 之间")
	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}
	
	@Length(min=0, max=255, message="name_长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="deployment_id_长度必须介于 0 和 64 之间")
	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	
	public String getBytes() {
		return bytes;
	}

	public void setBytes(String bytes) {
		this.bytes = bytes;
	}
	
	@Length(min=0, max=4, message="generated_长度必须介于 0 和 4 之间")
	public String getGenerated() {
		return generated;
	}

	public void setGenerated(String generated) {
		this.generated = generated;
	}
	
}