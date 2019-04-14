package com.bysjglxt.domain.DO;

public class bysjglxt_section {
	private String section_id;
	private String section_name;
	private String section_leader;
	private String section_college_id;
	private String section_gmt_create;
	private String section_gmt_modified;

	@Override
	public String toString() {
		return "bysjglxt_section [section_id=" + section_id + ", section_name=" + section_name + ", section_leader="
				+ section_leader + ", section_college_id=" + section_college_id + ", section_gmt_create="
				+ section_gmt_create + ", section_gmt_modified=" + section_gmt_modified + "]";
	}

	public String getSection_college_id() {
		return section_college_id;
	}

	public void setSection_college_id(String section_college_id) {
		this.section_college_id = section_college_id;
	}

	public String getSection_id() {
		return section_id;
	}

	public void setSection_id(String section_id) {
		this.section_id = section_id;
	}

	public String getSection_name() {
		return section_name;
	}

	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}

	public String getSection_leader() {
		return section_leader;
	}

	public void setSection_leader(String section_leader) {
		this.section_leader = section_leader;
	}

	public String getSection_gmt_create() {
		return section_gmt_create;
	}

	public void setSection_gmt_create(String section_gmt_create) {
		this.section_gmt_create = section_gmt_create;
	}

	public String getSection_gmt_modified() {
		return section_gmt_modified;
	}

	public void setSection_gmt_modified(String section_gmt_modified) {
		this.section_gmt_modified = section_gmt_modified;
	}

}
