package com.bysjglxt.domain.DO;

public class bysjglxt_process_definition {
	private String process_definition_id;
	private String process_definition_name;
	private int process_definition_instance_role;
	private String process_definition_gmt_create;
	private String process_definition_gmt_modified;

	public String getProcess_definition_id() {
		return process_definition_id;
	}

	public void setProcess_definition_id(String process_definition_id) {
		this.process_definition_id = process_definition_id;
	}

	public String getProcess_definition_name() {
		return process_definition_name;
	}

	public void setProcess_definition_name(String process_definition_name) {
		this.process_definition_name = process_definition_name;
	}

	public int getProcess_definition_instance_role() {
		return process_definition_instance_role;
	}

	public void setProcess_definition_instance_role(int process_definition_instance_role) {
		this.process_definition_instance_role = process_definition_instance_role;
	}

	public String getProcess_definition_gmt_create() {
		return process_definition_gmt_create;
	}

	public void setProcess_definition_gmt_create(String process_definition_gmt_create) {
		this.process_definition_gmt_create = process_definition_gmt_create;
	}

	public String getProcess_definition_gmt_modified() {
		return process_definition_gmt_modified;
	}

	public void setProcess_definition_gmt_modified(String process_definition_gmt_modified) {
		this.process_definition_gmt_modified = process_definition_gmt_modified;
	}

	@Override
	public String toString() {
		return "bysjglxt_process_definition [process_definition_id=" + process_definition_id
				+ ", process_definition_name=" + process_definition_name + ", process_definition_instance_role="
				+ process_definition_instance_role + ", process_definition_gmt_create=" + process_definition_gmt_create
				+ ", process_definition_gmt_modified=" + process_definition_gmt_modified + "]";
	}
}
