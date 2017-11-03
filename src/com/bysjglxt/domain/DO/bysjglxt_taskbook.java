package com.bysjglxt.domain.DO;

public class bysjglxt_taskbook {
	private String taskbook_id;
	private String bysjglxt_taskbook_student;
	private String taskbook_acontent_required;
	private String taskbook_reference;
	private String taskbook_plan;
	private String taskbook_opinion;
	private String taskbook_gmt_create;
	private String taskbook_gmt_modified;

	public String getTaskbook_reference() {
		return taskbook_reference;
	}

	public void setTaskbook_reference(String taskbook_reference) {
		this.taskbook_reference = taskbook_reference;
	}

	public String getTaskbook_id() {
		return taskbook_id;
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

	public String getTaskbook_acontent_required() {
		return taskbook_acontent_required;
	}

	public void setTaskbook_acontent_required(String taskbook_acontent_required) {
		this.taskbook_acontent_required = taskbook_acontent_required;
	}

	public String getTaskbook_plan() {
		return taskbook_plan;
	}

	public void setTaskbook_plan(String taskbook_plan) {
		this.taskbook_plan = taskbook_plan;
	}

	public String getTaskbook_opinion() {
		return taskbook_opinion;
	}

	public void setTaskbook_opinion(String taskbook_opinion) {
		this.taskbook_opinion = taskbook_opinion;
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
				+ bysjglxt_taskbook_student + ", taskbook_acontent_required=" + taskbook_acontent_required
				+ ", taskbook_plan=" + taskbook_plan + ", taskbook_opinion=" + taskbook_opinion
				+ ", taskbook_gmt_create=" + taskbook_gmt_create + ", taskbook_gmt_modified=" + taskbook_gmt_modified
				+ "]";
	}

}
