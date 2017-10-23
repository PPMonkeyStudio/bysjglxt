package com.bysjglxt.domain;

public class bysjglxt_summary {
	private String summary_id;
	private String summary_student;
	private String summary_gmt_start;
	private String summary_gmt_stop;
	private String summary_summary;
	private String summary_opinion;
	private String summary_gmt_create;
	private String summary_gmt_modified;

	public String getSummary_id() {
		return summary_id;
	}

	public void setSummary_id(String summary_id) {
		this.summary_id = summary_id;
	}

	public String getSummary_student() {
		return summary_student;
	}

	public void setSummary_student(String summary_student) {
		this.summary_student = summary_student;
	}

	public String getSummary_gmt_start() {
		return summary_gmt_start;
	}

	public void setSummary_gmt_start(String summary_gmt_start) {
		this.summary_gmt_start = summary_gmt_start;
	}

	public String getSummary_gmt_stop() {
		return summary_gmt_stop;
	}

	public void setSummary_gmt_stop(String summary_gmt_stop) {
		this.summary_gmt_stop = summary_gmt_stop;
	}

	public String getSummary_summary() {
		return summary_summary;
	}

	public void setSummary_summary(String summary_summary) {
		this.summary_summary = summary_summary;
	}

	public String getSummary_opinion() {
		return summary_opinion;
	}

	public void setSummary_opinion(String summary_opinion) {
		this.summary_opinion = summary_opinion;
	}

	public String getSummary_gmt_create() {
		return summary_gmt_create;
	}

	public void setSummary_gmt_create(String summary_gmt_create) {
		this.summary_gmt_create = summary_gmt_create;
	}

	public String getSummary_gmt_modified() {
		return summary_gmt_modified;
	}

	public void setSummary_gmt_modified(String summary_gmt_modified) {
		this.summary_gmt_modified = summary_gmt_modified;
	}

	@Override
	public String toString() {
		return "bysjglxt_summary [summary_id=" + summary_id + ", summary_student=" + summary_student
				+ ", summary_gmt_start=" + summary_gmt_start + ", summary_gmt_stop=" + summary_gmt_stop
				+ ", summary_summary=" + summary_summary + ", summary_opinion=" + summary_opinion
				+ ", summary_gmt_create=" + summary_gmt_create + ", summary_gmt_modified=" + summary_gmt_modified + "]";
	}

}
