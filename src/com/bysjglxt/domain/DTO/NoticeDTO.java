package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_notice;

public class NoticeDTO {
	private String launchName;
	private String belongName;
	private bysjglxt_notice bysjglxt_notice;

	public String getLaunchName() {
		return launchName;
	}

	public void setLaunchName(String launchName) {
		this.launchName = launchName;
	}

	public String getBelongName() {
		return belongName;
	}

	public void setBelongName(String belongName) {
		this.belongName = belongName;
	}

	public bysjglxt_notice getBysjglxt_notice() {
		return bysjglxt_notice;
	}

	public void setBysjglxt_notice(bysjglxt_notice bysjglxt_notice) {
		this.bysjglxt_notice = bysjglxt_notice;
	}

	@Override
	public String toString() {
		return "NoticeDTO [launchName=" + launchName + ", belongName=" + belongName + ", bysjglxt_notice="
				+ bysjglxt_notice + "]";
	}
}
