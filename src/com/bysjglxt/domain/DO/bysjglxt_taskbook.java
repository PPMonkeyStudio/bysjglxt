package com.bysjglxt.domain.DO;

public class bysjglxt_taskbook {
	private String taskbook_id;
	private String bysjglxt_taskbook_student;
	/*
	 * private String taskbook_acontent_required; private String taskbook_reference;
	 * private String taskbook_plan; private String taskbook_opinion;
	 */
	private String taskbook_xia_file = "";
	private String taskbook_wan_file = "";
	private Integer taskbook_wan_file_xiazai = -1;
	private Integer taskbook_xia_file_xiazai = -1;
	private String taskbook_leader_option = "";
	
	private String taskbook_gmt_create;
	private String taskbook_gmt_modified;

	public String getTaskbook_id() {
		return taskbook_id;
	}

	public String getTaskbook_xia_file() {
		return taskbook_xia_file;
	}

	public Integer getTaskbook_wan_file_xiazai() {
		return taskbook_wan_file_xiazai;
	}

	public String getTaskbook_leader_option() {
		return taskbook_leader_option;
	}

	public void setTaskbook_leader_option(String taskbook_leader_option) {
		this.taskbook_leader_option = taskbook_leader_option;
	}

	public void setTaskbook_wan_file_xiazai(Integer taskbook_wan_file_xiazai) {
		this.taskbook_wan_file_xiazai = taskbook_wan_file_xiazai;
	}

	public Integer getTaskbook_xia_file_xiazai() {
		return taskbook_xia_file_xiazai;
	}

	public void setTaskbook_xia_file_xiazai(Integer taskbook_xia_file_xiazai) {
		this.taskbook_xia_file_xiazai = taskbook_xia_file_xiazai;
	}

	public void setTaskbook_xia_file(String taskbook_xia_file) {
		this.taskbook_xia_file = taskbook_xia_file;
	}

	public String getTaskbook_wan_file() {
		return taskbook_wan_file;
	}

	public void setTaskbook_wan_file(String taskbook_wan_file) {
		this.taskbook_wan_file = taskbook_wan_file;
	}

	public void setTaskbook_id(String taskbook_id) {
		this.taskbook_id = taskbook_id;
	}

	public String getBysjglxt_taskbook_student() {
		return bysjglxt_taskbook_student;
	}

	public void setBysjglxt_taskbook_student(String bysjglxt_taskbook_student) {
		this.bysjglxt_taskbook_student = bysjglxt_taskbook_student;
	}

	public String getTaskbook_gmt_create() {
		return taskbook_gmt_create;
	}

	public void setTaskbook_gmt_create(String taskbook_gmt_create) {
		this.taskbook_gmt_create = taskbook_gmt_create;
	}

	public String getTaskbook_gmt_modified() {
		return taskbook_gmt_modified;
	}

	public void setTaskbook_gmt_modified(String taskbook_gmt_modified) {
		this.taskbook_gmt_modified = taskbook_gmt_modified;
	}

	@Override
	public String toString() {
		return "bysjglxt_taskbook [taskbook_id=" + taskbook_id + ", bysjglxt_taskbook_student="
				+ bysjglxt_taskbook_student + ", taskbook_xia_file=" + taskbook_xia_file + ", taskbook_wan_file="
				+ taskbook_wan_file + ", taskbook_wan_file_xiazai=" + taskbook_wan_file_xiazai
				+ ", taskbook_xia_file_xiazai=" + taskbook_xia_file_xiazai + ", taskbook_leader_option="
				+ taskbook_leader_option + ", taskbook_gmt_create=" + taskbook_gmt_create + ", taskbook_gmt_modified="
				+ taskbook_gmt_modified + "]";
	}





}
