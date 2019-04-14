package com.bysjglxt.domain.DO;

public class bysjglxt_task_instance {
	private String task_instance_id;
	private String task_instance_process_instance;
	private String task_instance_task_definition;
	private String task_instance_role;
	private String task_instance_father;
	private String task_instance_return;
	private int task_instance_state;
	private String task_instance_gmt_create;
	private int task_instance_is_update = -1;
	private String task_instance_gmt_modified;
	private String task_instance_start;
	private String task_instance_stop;
	

	public String getTask_instance_start() {
		return task_instance_start;
	}

	public void setTask_instance_start(String task_instance_start) {
		this.task_instance_start = task_instance_start;
	}

	public String getTask_instance_stop() {
		return task_instance_stop;
	}

	public void setTask_instance_stop(String task_instance_stop) {
		this.task_instance_stop = task_instance_stop;
	}

	public int getTask_instance_is_update() {
		return task_instance_is_update;
	}

	public void setTask_instance_is_update(int task_instance_is_update) {
		this.task_instance_is_update = task_instance_is_update;
	}

	public String getTask_instance_id() {
		return task_instance_id;
	}

	public void setTask_instance_id(String task_instance_id) {
		this.task_instance_id = task_instance_id;
	}

	public String getTask_instance_process_instance() {
		return task_instance_process_instance;
	}

	public void setTask_instance_process_instance(String task_instance_process_instance) {
		this.task_instance_process_instance = task_instance_process_instance;
	}

	public String getTask_instance_task_definition() {
		return task_instance_task_definition;
	}

	public void setTask_instance_task_definition(String task_instance_task_definition) {
		this.task_instance_task_definition = task_instance_task_definition;
	}

	public String getTask_instance_role() {
		return task_instance_role;
	}

	public void setTask_instance_role(String task_instance_role) {
		this.task_instance_role = task_instance_role;
	}

	public String getTask_instance_father() {
		return task_instance_father;
	}

	public void setTask_instance_father(String task_instance_father) {
		this.task_instance_father = task_instance_father;
	}

	public String getTask_instance_return() {
		return task_instance_return;
	}

	public void setTask_instance_return(String task_instance_return) {
		this.task_instance_return = task_instance_return;
	}

	public int getTask_instance_state() {
		return task_instance_state;
	}

	public void setTask_instance_state(int task_instance_state) {
		this.task_instance_state = task_instance_state;
	}

	public String getTask_instance_gmt_create() {
		return task_instance_gmt_create;
	}

	public void setTask_instance_gmt_create(String task_instance_gmt_create) {
		this.task_instance_gmt_create = task_instance_gmt_create;
	}

	public String getTask_instance_gmt_modified() {
		return task_instance_gmt_modified;
	}

	public void setTask_instance_gmt_modified(String task_instance_gmt_modified) {
		this.task_instance_gmt_modified = task_instance_gmt_modified;
	}

	@Override
	public String toString() {
		return "bysjglxt_task_instance [task_instance_id=" + task_instance_id + ", task_instance_process_instance="
				+ task_instance_process_instance + ", task_instance_task_definition=" + task_instance_task_definition
				+ ", task_instance_role=" + task_instance_role + ", task_instance_father=" + task_instance_father
				+ ", task_instance_return=" + task_instance_return + ", task_instance_state=" + task_instance_state
				+ ", task_instance_gmt_create=" + task_instance_gmt_create + ", task_instance_is_update="
				+ task_instance_is_update + ", task_instance_gmt_modified=" + task_instance_gmt_modified
				+ ", task_instance_start=" + task_instance_start + ", task_instance_stop=" + task_instance_stop + "]";
	}

	

}
