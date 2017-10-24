package com.bysjglxt.action;

import java.util.Date;

import com.bysjglxt.domain.DO.test;
import com.bysjglxt.service.TestService;
import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TestService testService;

	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	private test tt;

	public test getTt() {
		if (tt == null) {
			tt = new test();
		}
		return tt;
	}

	public void setTt(test tt) {
		this.tt = tt;
	}

	public String test() {
		System.out.println("fd");
		return "success";
	}

	public String add() {
		System.out.println(tt.getContent() + "\t" + tt.getId());
		tt.setCreateTime((new Date()).toString());
		testService.addTest(tt);
		return "add";
	}

}
