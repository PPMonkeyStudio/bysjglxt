package com.bysjglxt.domain;

public class bysjglxt_task_definition {
	private String task_definition_id;
	private String task_definition_name;
	private String task_definition_process_definition;
	private int task_definition_type;
	private int task_definition_role;
	private String task_definition_return;
	private String task_definition_father;
	private String task_definition_gmt_create;
	private String task_definition_gmt_modified;

	public String getTask_definition_id() {
		return task_definition_id;
	}

	public void setTask_definition_id(String task_definition_id) {
		this.task_definition_id = task_definition_id;
	}

	public String getTask_definition_name() {
		return task_definition_name;
	}

	public void setTask_definition_name(String task_definition_name) {
		this.task_definition_name = task_definition_name;
	}

	public String getTask_definition_process_definition() {
		return task_definition_process_definition;
	}

	public void setTask_definition_process_definition(String task_definition_process_definition) {
		this.task_definition_process_definition = task_definition_process_definition;
	}

	public int getTask_definition_type() {
		return task_definition_type;
	}

	public void setTask_definition_type(int task_definition_type) {
		this.task_definition_type = task_definition_type;
	}

	public int getTask_definition_role() {
		return task_definition_role;
	}

	public void setTask_definition_role(int task_definition_role) {
		this.task_definition_role = task_definition_role;
	}

	public String getTask_definition_return() {
		return task_definition_return;
	}

	public void setTask_definition_return(String task_definition_return) {
		this.task_definition_return = task_definition_return;
	}

	public String getTask_definition_father() {
		return task_definition_father;
	}

	public void setTask_definition_father(String task_definition_father) {
		this.task_definition_father = task_definition_father;
	}

	public String getTask_definition_gmt_create() {
		return task_definition_gmt_create;
	}

	public void setTask_definition_gmt_create(String task_definition_gmt_create) {
		this.task_definition_gmt_create = task_definition_gmt_create;
	}

	public String getTask_definition_gmt_modified() {
		return task_definition_gmt_modified;
	}

	public void setTask_definition_gmt_modified(String task_definition_gmt_modified) {
		this.task_definition_gmt_modified = task_definition_gmt_modified;
	}

	@Override
	public String toString() {
		return "bysjglxt_task_definition [task_definition_id=" + task_definition_id + ", task_definition_name="
				+ task_definition_name + ", task_definition_process_definition=" + task_definition_process_definition
				+ ", task_definition_type=" + task_definition_type + ", task_definition_role=" + task_definition_role
				+ ", task_definition_return=" + task_definition_return + ", task_definition_father="
				+ task_definition_father + ", task_definition_gmt_create=" + task_definition_gmt_create
				+ ", task_definition_gmt_modified=" + task_definition_gmt_modified + "]";
	}

}
