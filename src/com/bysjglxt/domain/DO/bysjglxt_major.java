package com.bysjglxt.domain.DO;

public class bysjglxt_major {
	private String major_id;
	private String major_professionalcode;
	private String major_name;
	private String major_belong_section;
	private String major_gmt_create;
	private String major_gmt_modified;

	@Override
	public String toString() {
		return "bysjglxt_major [major_id=" + major_id + ", major_professionalcode=" + major_professionalcode
				+ ", major_name=" + major_name + ", major_belong_section=" + major_belong_section
				+ ", major_gmt_create=" + major_gmt_create + ", major_gmt_modified=" + major_gmt_modified + "]";
	}

	public String getMajor_id() {
		return major_id;
	}

	public void setMajor_id(String major_id) {
		this.major_id = major_id;
	}

	public String getMajor_professionalcode() {
		return major_professionalcode;
	}

	public void setMajor_professionalcode(String major_professionalcode) {
		this.major_professionalcode = major_professionalcode;
	}

	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	public String getMajor_belong_section() {
		return major_belong_section;
	}

	public void setMajor_belong_section(String major_belong_section) {
		this.major_belong_section = major_belong_section;
	}

	public String getMajor_gmt_create() {
		return major_gmt_create;
	}

	public void setMajor_gmt_create(String major_gmt_create) {
		this.major_gmt_create = major_gmt_create;
	}

	public String getMajor_gmt_modified() {
		return major_gmt_modified;
	}

	public void setMajor_gmt_modified(String major_gmt_modified) {
		this.major_gmt_modified = major_gmt_modified;
	}

}
