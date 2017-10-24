package com.bysjglxt.domain;

public class bysjglxt_topic_select {
	private String topic_select_id;
	private String topic_select_student;
	private String topic_select_teacher_tutor;
	private String topic_select_teacher_review;
	private String topic_select_topic;
	private String topic_select_gmt_create;
	private String topic_select_gmt_modified;

	public String getTopic_select_id() {
		return topic_select_id;
	}

	public void setTopic_select_id(String topic_select_id) {
		this.topic_select_id = topic_select_id;
	}

	public String getTopic_select_student() {
		return topic_select_student;
	}

	public void setTopic_select_student(String topic_select_student) {
		this.topic_select_student = topic_select_student;
	}

	public String getTopic_select_teacher_tutor() {
		return topic_select_teacher_tutor;
	}

	public void setTopic_select_teacher_tutor(String topic_select_teacher_tutor) {
		this.topic_select_teacher_tutor = topic_select_teacher_tutor;
	}

	public String getTopic_select_teacher_review() {
		return topic_select_teacher_review;
	}

	public void setTopic_select_teacher_review(String topic_select_teacher_review) {
		this.topic_select_teacher_review = topic_select_teacher_review;
	}

	public String getTopic_select_topic() {
		return topic_select_topic;
	}

	public void setTopic_select_topic(String topic_select_topic) {
		this.topic_select_topic = topic_select_topic;
	}

	public String getTopic_select_gmt_create() {
		return topic_select_gmt_create;
	}

	public void setTopic_select_gmt_create(String topic_select_gmt_create) {
		this.topic_select_gmt_create = topic_select_gmt_create;
	}

	public String getTopic_select_gmt_modified() {
		return topic_select_gmt_modified;
	}

	public void setTopic_select_gmt_modified(String topic_select_gmt_modified) {
		this.topic_select_gmt_modified = topic_select_gmt_modified;
	}

	@Override
	public String toString() {
		return "bysjglxt_topic_select [topic_select_id=" + topic_select_id + ", topic_select_student="
				+ topic_select_student + ", topic_select_teacher_tutor=" + topic_select_teacher_tutor
				+ ", topic_select_teacher_review=" + topic_select_teacher_review + ", topic_select_topic="
				+ topic_select_topic + ", topic_select_gmt_create=" + topic_select_gmt_create
				+ ", topic_select_gmt_modified=" + topic_select_gmt_modified + "]";
	}

}
