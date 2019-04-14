package com.bysjglxt.domain.DO;

public class bysjglxt_student_user {

	private String user_student_id;
	private String user_student_num;
	private String user_student_password;
	private String user_student_basic;
	private int user_student_is_operate_premission;
	private int user_student_is_select_topic;
	private String user_student_belong_major;
	private String user_student_belong_college;
	private String user_student_gmt_create;
	private String user_student_gmt_modified;

	@Override
	public String toString() {
		return "bysjglxt_student_user [user_student_id=" + user_student_id + ", user_student_num=" + user_student_num
				+ ", user_student_password=" + user_student_password + ", user_student_basic=" + user_student_basic
				+ ", user_student_is_operate_premission=" + user_student_is_operate_premission
				+ ", user_student_is_select_topic=" + user_student_is_select_topic + ", user_student_belong_major="
				+ user_student_belong_major + ", user_student_belong_college=" + user_student_belong_college
				+ ", user_student_gmt_create=" + user_student_gmt_create + ", user_student_gmt_modified="
				+ user_student_gmt_modified + "]";
	}

	public String getUser_student_belong_college() {
		return user_student_belong_college;
	}

	public void setUser_student_belong_college(String user_student_belong_college) {
		this.user_student_belong_college = user_student_belong_college;
	}

	public String getUser_student_belong_major() {
		return user_student_belong_major;
	}

	public void setUser_student_belong_major(String user_student_belong_major) {
		this.user_student_belong_major = user_student_belong_major;
	}

	public int getUser_student_is_select_topic() {
		return user_student_is_select_topic;
	}

	public void setUser_student_is_select_topic(int user_student_is_select_topic) {
		this.user_student_is_select_topic = user_student_is_select_topic;
	}

	public String getUser_student_id() {
		return user_student_id;
	}

	public void setUser_student_id(String user_student_id) {
		this.user_student_id = user_student_id;
	}

	public String getUser_student_num() {
		return user_student_num;
	}

	public void setUser_student_num(String user_student_num) {
		this.user_student_num = user_student_num;
	}

	public String getUser_student_password() {
		return user_student_password;
	}

	public void setUser_student_password(String user_student_password) {
		this.user_student_password = user_student_password;
	}

	public String getUser_student_basic() {
		return user_student_basic;
	}

	public void setUser_student_basic(String user_student_basic) {
		this.user_student_basic = user_student_basic;
	}

	public String getUser_student_gmt_create() {
		return user_student_gmt_create;
	}

	public void setUser_student_gmt_create(String user_student_gmt_create) {
		this.user_student_gmt_create = user_student_gmt_create;
	}

	public String getUser_student_gmt_modified() {
		return user_student_gmt_modified;
	}

	public void setUser_student_gmt_modified(String user_student_gmt_modified) {
		this.user_student_gmt_modified = user_student_gmt_modified;
	}

	public int getUser_student_is_operate_premission() {
		return user_student_is_operate_premission;
	}

	public void setUser_student_is_operate_premission(int user_student_is_operate_premission) {
		this.user_student_is_operate_premission = user_student_is_operate_premission;
	}

}
