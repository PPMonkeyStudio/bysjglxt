package com.bysjglxt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.service.MajorManagementService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MajorManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	/*
	 * 
	 */
	private MajorManagementService majorManagementService;

	public void setMajorManagementService(MajorManagementService majorManagementService) {
		this.majorManagementService = majorManagementService;
	}
	/**
	 * 
	 */
	private HttpServletResponse http_response;
	private HttpServletRequest http_request;

	
	
	
	
	/**
	 * 
	 */
	@Override
	public void setServletRequest(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	@Override
	public void setServletResponse(HttpServletResponse http_response) {
		this.http_response = http_response;

	}

	public HttpServletResponse getHttp_response() {
		return http_response;
	}

	public void setHttp_response(HttpServletResponse http_response) {
		this.http_response = http_response;
	}

	public HttpServletRequest getHttp_request() {
		return http_request;
	}

	public void setHttp_request(HttpServletRequest http_request) {
		this.http_request = http_request;
	}
	/**
	 * 
	 */

}
