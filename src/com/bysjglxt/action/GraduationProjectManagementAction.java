package com.bysjglxt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.service.GraduationProjectManagementService;
import com.opensymphony.xwork2.ActionSupport;

public class GraduationProjectManagementAction extends ActionSupport
		implements ServletResponseAware, ServletRequestAware {
	/*
	 * 
	 */
	private GraduationProjectManagementService graduationProjectManagementService;
	private HttpServletResponse http_response;
	private HttpServletRequest http_request;

	/*
	 * 
	 */
	public String MyGraduationProjectPage() {
		return "MyGraduationProjectPage";
	}

	/*
	 * 
	 */

	public void get_Taskbook() {

	}

	public void get_ReportOpening() {

	}

	public void get_RecordProgress_1() {

	}

	public void get_RecordProgress_2() {

	}

	public void get_RecordProgress_3() {

	}

	public void get_RecordProgress_4() {

	}

	public void get_Summary() {

	}

	public void get_ExaminationFormal() {

	}

	public void get_EvaluateTutor() {

	}

	public void get_EvaluateReview() {

	}

	public void get_Defence() {

	}

	/*
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	@Override
	public void setServletResponse(HttpServletResponse http_response) {
		this.http_response = http_response;

	}

	public void setGraduationProjectManagementService(
			GraduationProjectManagementService graduationProjectManagementService) {
		this.graduationProjectManagementService = graduationProjectManagementService;
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

	public GraduationProjectManagementService getGraduationProjectManagementService() {
		return graduationProjectManagementService;
	}
}
