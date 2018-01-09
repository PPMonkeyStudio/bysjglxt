package com.bysjglxt.domain.DO;

public class bysjglxt_comment {
	private String comment_id;
	private String comment_grade;
	private String comment_category;
	private String comment_content;
	private String comment_college;
	private String comment_gmt_create;
	private String comment_gmt_modified;

	@Override
	public String toString() {
		return "bysjglxt_comment [comment_id=" + comment_id + ", comment_grade=" + comment_grade + ", comment_category="
				+ comment_category + ", comment_content=" + comment_content + ", comment_college=" + comment_college
				+ ", comment_gmt_create=" + comment_gmt_create + ", comment_gmt_modified=" + comment_gmt_modified + "]";
	}

	public String getComment_id() {
		return comment_id;
	}

	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}

	public String getComment_grade() {
		return comment_grade;
	}

	public void setComment_grade(String comment_grade) {
		this.comment_grade = comment_grade;
	}

	public String getComment_category() {
		return comment_category;
	}

	public void setComment_category(String comment_category) {
		this.comment_category = comment_category;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getComment_college() {
		return comment_college;
	}

	public void setComment_college(String comment_college) {
		this.comment_college = comment_college;
	}

	public String getComment_gmt_create() {
		return comment_gmt_create;
	}

	public void setComment_gmt_create(String comment_gmt_create) {
		this.comment_gmt_create = comment_gmt_create;
	}

	public String getComment_gmt_modified() {
		return comment_gmt_modified;
	}

	public void setComment_gmt_modified(String comment_gmt_modified) {
		this.comment_gmt_modified = comment_gmt_modified;
	}

}
