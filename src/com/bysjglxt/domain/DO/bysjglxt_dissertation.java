package com.bysjglxt.domain.DO;

public class bysjglxt_dissertation {
	private String dissertation_id;
	private String dissertation_student; // 所属学生
	private String dissertation_file; // 论文文件名及后缀
	private String dissertation_gmt_create; // 创建时间
	private String dissertation_gmt_modified; // 修改时间

	
	public String getDissertation_id() {
		return dissertation_id;
	}

	public void setDissertation_id(String dissertation_id) {
		this.dissertation_id = dissertation_id;
	}

	public String getDissertation_student() {
		return dissertation_student;
	}

	public void setDissertation_student(String dissertation_student) {
		this.dissertation_student = dissertation_student;
	}

	public String getDissertation_file() {
		return dissertation_file;
	}

	public void setDissertation_file(String dissertation_file) {
		this.dissertation_file = dissertation_file;
	}

	public String getDissertation_gmt_create() {
		return dissertation_gmt_create;
	}

	public void setDissertation_gmt_create(String dissertation_gmt_create) {
		this.dissertation_gmt_create = dissertation_gmt_create;
	}

	public String getDissertation_gmt_modified() {
		return dissertation_gmt_modified;
	}

	public void setDissertation_gmt_modified(String dissertation_gmt_modified) {
		this.dissertation_gmt_modified = dissertation_gmt_modified;
	}

	@Override
	public String toString() {
		return "bysjglxt_dissertation [dissertation_id=" + dissertation_id + ", dissertation_student="
				+ dissertation_student + ", dissertation_file=" + dissertation_file + ", dissertation_gmt_create="
				+ dissertation_gmt_create + ", dissertation_gmt_modified=" + dissertation_gmt_modified + "]";
	}


}
