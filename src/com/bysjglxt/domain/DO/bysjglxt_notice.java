package com.bysjglxt.domain.DO;

public class bysjglxt_notice {
	private String notice_id;
	private String notice_launch;
	private String notice_belong;
	private String notice_content;
	private int notice_state;
	private String notice_gmt_create;
	private String notice_gmt_modified;

	private int notice_leixing = 2;

	public String getNotice_id() {
		return notice_id;
	}

	public int getNotice_leixing() {
		return notice_leixing;
	}

	public void setNotice_leixing(int notice_leixing) {
		this.notice_leixing = notice_leixing;
	}

	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}

	public String getNotice_launch() {
		return notice_launch;
	}

	public void setNotice_launch(String notice_launch) {
		this.notice_launch = notice_launch;
	}

	public String getNotice_belong() {
		return notice_belong;
	}

	public void setNotice_belong(String notice_belong) {
		this.notice_belong = notice_belong;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public int getNotice_state() {
		return notice_state;
	}

	public void setNotice_state(int notice_state) {
		this.notice_state = notice_state;
	}

	public String getNotice_gmt_create() {
		return notice_gmt_create;
	}

	public void setNotice_gmt_create(String notice_gmt_create) {
		this.notice_gmt_create = notice_gmt_create;
	}

	public String getNotice_gmt_modified() {
		return notice_gmt_modified;
	}

	public void setNotice_gmt_modified(String notice_gmt_modified) {
		this.notice_gmt_modified = notice_gmt_modified;
	}

	@Override
	public String toString() {
		return "bysjglxt_notice [notice_id=" + notice_id + ", notice_launch=" + notice_launch + ", notice_belong="
				+ notice_belong + ", notice_content=" + notice_content + ", notice_state=" + notice_state
				+ ", notice_gmt_create=" + notice_gmt_create + ", notice_gmt_modified=" + notice_gmt_modified
				+ ", notice_leixing=" + notice_leixing + "]";
	}

}
