package com.bysjglxt.domain.DO;

public class bysjglxt_admin {
	private String admin_id;
	private String admin_account;
	private String admin_password;
	private String admin_gmt_create;
	private String admin_gmt_modified;

	@Override
	public String toString() {
		return "bysjglxt_admin [admin_id=" + admin_id + ", admin_account=" + admin_account + ", admin_password="
				+ admin_password + ", admin_gmt_create=" + admin_gmt_create + ", admin_gmt_modified="
				+ admin_gmt_modified + "]";
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_account() {
		return admin_account;
	}

	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	public String getAdmin_gmt_create() {
		return admin_gmt_create;
	}

	public void setAdmin_gmt_create(String admin_gmt_create) {
		this.admin_gmt_create = admin_gmt_create;
	}

	public String getAdmin_gmt_modified() {
		return admin_gmt_modified;
	}

	public void setAdmin_gmt_modified(String admin_gmt_modified) {
		this.admin_gmt_modified = admin_gmt_modified;
	}

}
