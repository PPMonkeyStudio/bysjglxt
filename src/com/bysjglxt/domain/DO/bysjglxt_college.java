package com.bysjglxt.domain.DO;

public class bysjglxt_college {
	private String college_id;
	private String college_code;
	private String college_name;
	private String college_gmt_create;
	private String college_gmt_modified;

	@Override
	public String toString() {
		return "bysjglxt_college [college_id=" + college_id + ", college_code=" + college_code + ", college_name="
				+ college_name + ", college_gmt_create=" + college_gmt_create + ", college_gmt_modified="
				+ college_gmt_modified + "]";
	}

	public String getCollege_id() {
		return college_id;
	}

	public void setCollege_id(String college_id) {
		this.college_id = college_id;
	}

	public String getCollege_code() {
		return college_code;
	}

	public void setCollege_code(String college_code) {
		this.college_code = college_code;
	}

	public String getCollege_name() {
		return college_name;
	}

	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}

	public String getCollege_gmt_create() {
		return college_gmt_create;
	}

	public void setCollege_gmt_create(String college_gmt_create) {
		this.college_gmt_create = college_gmt_create;
	}

	public String getCollege_gmt_modified() {
		return college_gmt_modified;
	}

	public void setCollege_gmt_modified(String college_gmt_modified) {
		this.college_gmt_modified = college_gmt_modified;
	}

}
