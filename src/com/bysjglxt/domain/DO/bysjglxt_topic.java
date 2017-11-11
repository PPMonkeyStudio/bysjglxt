package com.bysjglxt.domain.DO;

public class bysjglxt_topic {
	private String topic_id;
	private String topic_name_chinese;
	private String topic_name_english;
	private String topic_requirement;
	private String topic_source;
	private String topic_type;
	private int topic_student_max;
	private String topic_remark;
	private String topic_teacher;
	private String topic_student;
	private String topic_gmt_create;
	private String topic_gmt_modified;
	private String topic_examine_state;
	private int topic_student_num;

	public int getTopic_student_num() {
		return topic_student_num;
	}

	public void setTopic_student_num(int topic_student_num) {
		this.topic_student_num = topic_student_num;
	}

	public String getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(String topic_id) {
		this.topic_id = topic_id;
	}

	public String getTopic_name_chinese() {
		return topic_name_chinese;
	}

	public void setTopic_name_chinese(String topic_name_chinese) {
		this.topic_name_chinese = topic_name_chinese;
	}

	public String getTopic_name_english() {
		return topic_name_english;
	}

	public void setTopic_name_english(String topic_name_english) {
		this.topic_name_english = topic_name_english;
	}

	public String getTopic_requirement() {
		return topic_requirement;
	}

	public void setTopic_requirement(String topic_requirement) {
		this.topic_requirement = topic_requirement;
	}

	public String getTopic_source() {
		return topic_source;
	}

	public void setTopic_source(String topic_source) {
		this.topic_source = topic_source;
	}

	public String getTopic_type() {
		return topic_type;
	}

	public void setTopic_type(String topic_type) {
		this.topic_type = topic_type;
	}

	public int getTopic_student_max() {
		return topic_student_max;
	}

	public void setTopic_student_max(int topic_student_max) {
		this.topic_student_max = topic_student_max;
	}

	public String getTopic_remark() {
		return topic_remark;
	}

	public void setTopic_remark(String topic_remark) {
		this.topic_remark = topic_remark;
	}

	public String getTopic_teacher() {
		return topic_teacher;
	}

	public void setTopic_teacher(String topic_teacher) {
		this.topic_teacher = topic_teacher;
	}

	public String getTopic_student() {
		return topic_student;
	}

	public void setTopic_student(String topic_student) {
		this.topic_student = topic_student;
	}

	public String getTopic_gmt_create() {
		return topic_gmt_create;
	}

	public void setTopic_gmt_create(String topic_gmt_create) {
		this.topic_gmt_create = topic_gmt_create;
	}

	public String getTopic_gmt_modified() {
		return topic_gmt_modified;
	}

	public void setTopic_gmt_modified(String topic_gmt_modified) {
		this.topic_gmt_modified = topic_gmt_modified;
	}

	public String getTopic_examine_state() {
		return topic_examine_state;
	}

	public void setTopic_examine_state(String topic_examine_state) {
		this.topic_examine_state = topic_examine_state;
	}

	@Override
	public String toString() {
		return "bysjglxt_topic [topic_id=" + topic_id + ", topic_name_chinese=" + topic_name_chinese
				+ ", topic_name_english=" + topic_name_english + ", topic_requirement=" + topic_requirement
				+ ", topic_source=" + topic_source + ", topic_type=" + topic_type + ", topic_student_max="
				+ topic_student_max + ", topic_remark=" + topic_remark + ", topic_teacher=" + topic_teacher
				+ ", topic_student=" + topic_student + ", topic_gmt_create=" + topic_gmt_create
				+ ", topic_gmt_modified=" + topic_gmt_modified + ", topic_examine_state=" + topic_examine_state
				+ ", topic_student_num=" + topic_student_num + "]";
	}

}
