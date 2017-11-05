package com.bysjglxt.domain.DO;

public class bysjglxt_process_instance {
	private String process_instance_id;
	private String process_instance_process_definition;
	private String process_instance_state;
	private String process_instance_man;
	private String process_instance_gmt_create;
	private String process_instance_gmt_modified;

	public String getProcess_instance_id() {
		return process_instance_id;
	}

	public void setProcess_instance_id(String process_instance_id) {
		this.process_instance_id = process_instance_id;
	}

	public String getProcess_instance_process_definition() {
		return process_instance_process_definition;
	}

	public void setProcess_instance_process_definition(String process_instance_process_definition) {
		this.process_instance_process_definition = process_instance_process_definition;
	}

	public String getProcess_instance_state() {
		return process_instance_state;
	}

	public void setProcess_instance_state(String process_instance_state) {
		this.process_instance_state = process_instance_state;
	}

	public String getProcess_instance_man() {
		return process_instance_man;
	}

	public void setProcess_instance_man(String process_instance_man) {
		this.process_instance_man = process_instance_man;
	}

	public String getProcess_instance_gmt_create() {
		return process_instance_gmt_create;
	}

	public void setProcess_instance_gmt_create(String process_instance_gmt_create) {
		this.process_instance_gmt_create = process_instance_gmt_create;
	}

	public String getProcess_instance_gmt_modified() {
		return process_instance_gmt_modified;
	}

	public void setProcess_instance_gmt_modified(String process_instance_gmt_modified) {
		this.process_instance_gmt_modified = process_instance_gmt_modified;
	}

	@Override
	public String toString() {
		return "bysjglxt_process_instance [process_instance_id=" + process_instance_id
				+ ", process_instance_process_definition=" + process_instance_process_definition
				+ ", process_instance_state=" + process_instance_state + ", process_instance_man="
				+ process_instance_man + ", process_instance_gmt_create=" + process_instance_gmt_create
				+ ", process_instance_gmt_modified=" + process_instance_gmt_modified + "]";
	}

}
