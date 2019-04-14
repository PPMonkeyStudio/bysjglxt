package com.bysjglxt.action;

import java.util.Date;

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



}
