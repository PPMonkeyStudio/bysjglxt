package com.bysjglxt.domain;
public class bysjglxt_user_teacher {
	private String user_teacher_id;
	private String user_teacher_num;
	private String user_teacher_password;
	private String user_teacher_basic;
	private String user_teacher_section;
	private int user_teacher_max_guidance;
	private String user_teacher_gmt_create;
	private String user_teacher_gmt_modified;

	public String getUser_teacher_id() {
		return user_teacher_id;
	}

	public void setUser_teacher_id(String user_teacher_id) {
		this.user_teacher_id = user_teacher_id;
	}

	public String getUser_teacher_num() {
		return user_teacher_num;
	}

	public void setUser_teacher_num(String user_teacher_num) {
		this.user_teacher_num = user_teacher_num;
	}

	public String getUser_teacher_password() {
		return user_teacher_password;
	}

	public void setUser_teacher_password(String user_teacher_password) {
		this.user_teacher_password = user_teacher_password;
	}

	public String getUser_teacher_basic() {
		return user_teacher_basic;
	}

	public void setUser_teacher_basic(String user_teacher_basic) {
		this.user_teacher_basic = user_teacher_basic;
	}

	public String getUser_teacher_section() {
		return user_teacher_section;
	}

	public void setUser_teacher_section(String user_teacher_section) {
		this.user_teacher_section = user_teacher_section;
	}

	public int getUser_teacher_max_guidance() {
		return user_teacher_max_guidance;
	}

	public void setUser_teacher_max_guidance(int user_teacher_max_guidance) {
		this.user_teacher_max_guidance = user_teacher_max_guidance;
	}

	public String getUser_teacher_gmt_create() {
		return user_teacher_gmt_create;
	}

	public void setUser_teacher_gmt_create(String user_teacher_gmt_create) {
		this.user_teacher_gmt_create = user_teacher_gmt_create;
	}

	public String getUser_teacher_gmt_modified() {
		return user_teacher_gmt_modified;
	}

	public void setUser_teacher_gmt_modified(String user_teacher_gmt_modified) {
		this.user_teacher_gmt_modified = user_teacher_gmt_modified;
	}

	@Override
	public String toString() {
		return "bysjglxt_user_teacher [user_teacher_id=" + user_teacher_id + ", user_teacher_num=" + user_teacher_num
				+ ", user_teacher_password=" + user_teacher_password + ", user_teacher_basic=" + user_teacher_basic
				+ ", user_teacher_section=" + user_teacher_section + ", user_teacher_max_guidance="
				+ user_teacher_max_guidance + ", user_teacher_gmt_create=" + user_teacher_gmt_create
				+ ", user_teacher_gmt_modified=" + user_teacher_gmt_modified + "]";
	}
}
