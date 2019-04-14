package com.bysjglxt.domain.DO;

public class bysjglxt_report_opening {
	private String report_opening_id;
	private String report_opening_student;
	/*
	 * private String report_opening_documentary_survey; private String
	 * report_opening_main; private String report_opening_detail; private String
	 * report_opening_reference; private String report_opening_plan;
	 */
	private String report_opening_file;
	private String report_opening_teacher_file;
	private Integer report_file_is_xiazai = -1;
	private Integer report_opening_teacher_file_is_xiazai = -1;
	private String report_opening_gmt_create;
	private String report_opening_gmt_modified;

	public String getReport_opening_teacher_file() {
		return report_opening_teacher_file;
	}

	public void setReport_opening_teacher_file(String report_opening_teacher_file) {
		this.report_opening_teacher_file = report_opening_teacher_file;
	}

	public Integer getReport_opening_teacher_file_is_xiazai() {
		return report_opening_teacher_file_is_xiazai;
	}

	public void setReport_opening_teacher_file_is_xiazai(Integer report_opening_teacher_file_is_xiazai) {
		this.report_opening_teacher_file_is_xiazai = report_opening_teacher_file_is_xiazai;
	}

	public String getReport_opening_id() {
		return report_opening_id;
	}

	public Integer getReport_file_is_xiazai() {
		return report_file_is_xiazai;
	}

	public void setReport_file_is_xiazai(Integer report_file_is_xiazai) {
		this.report_file_is_xiazai = report_file_is_xiazai;
	}

	public void setReport_opening_id(String report_opening_id) {
		this.report_opening_id = report_opening_id;
	}

	public String getReport_opening_student() {
		return report_opening_student;
	}

	public void setReport_opening_student(String report_opening_student) {
		this.report_opening_student = report_opening_student;
	}

	public String getReport_opening_gmt_create() {
		return report_opening_gmt_create;
	}

	public void setReport_opening_gmt_create(String report_opening_gmt_create) {
		this.report_opening_gmt_create = report_opening_gmt_create;
	}

	public String getReport_opening_gmt_modified() {
		return report_opening_gmt_modified;
	}

	public void setReport_opening_gmt_modified(String report_opening_gmt_modified) {
		this.report_opening_gmt_modified = report_opening_gmt_modified;
	}

	public String getReport_opening_file() {
		return report_opening_file;
	}

	public void setReport_opening_file(String report_opening_file) {
		this.report_opening_file = report_opening_file;
	}

	@Override
	public String toString() {
		return "bysjglxt_report_opening [report_opening_id=" + report_opening_id + ", report_opening_student="
				+ report_opening_student + ", report_opening_file=" + report_opening_file
				+ ", report_opening_teacher_file=" + report_opening_teacher_file + ", report_file_is_xiazai="
				+ report_file_is_xiazai + ", report_opening_teacher_file_is_xiazai="
				+ report_opening_teacher_file_is_xiazai + ", report_opening_gmt_create=" + report_opening_gmt_create
				+ ", report_opening_gmt_modified=" + report_opening_gmt_modified + "]";
	}




}
