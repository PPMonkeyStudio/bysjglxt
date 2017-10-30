package com.bysjglxt.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.VO.SectionInformationManagementVO;
import com.bysjglxt.service.SectionInformationManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SectionInformationManagementAction extends ActionSupport
		implements ServletResponseAware, ServletRequestAware {
	/*
	 * 
	 */
	private SectionInformationManagementService sectionInformationManagementService;

	private HttpServletResponse http_response;

	private HttpServletRequest http_request;
	/*
	 * 
	 * 
	 */
	private SectionInformationManagementVO sectionInformationManagementVO;

	/**
	 * @说明 跳转列表页
	 * 
	 * @return
	 */
	public String SectionManagementPage() {

		return "SectionManagementPage";
	}

	/**
	 * @说明 跳转手动添加学生页
	 * 
	 * @return
	 */
	public String CreateSectionPage() {
		return "CreateSectionPage";
	}

	/**
	 * @throws IOException
	 */
	public void ListSectionByPage() {
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();
			Gson gson = gsonBuilder.create();
			sectionInformationManagementVO = sectionInformationManagementService
					.VO_Section_By_Page(sectionInformationManagementVO);
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write(gson.toJson(sectionInformationManagementVO));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
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

	/*
	 * 
	 */
	public SectionInformationManagementService getSectionInformationManagementService() {
		return sectionInformationManagementService;
	}

	public void setSectionInformationManagementService(
			SectionInformationManagementService sectionInformationManagementService) {
		this.sectionInformationManagementService = sectionInformationManagementService;
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

	public SectionInformationManagementVO getSectionInformationManagementVO() {
		return sectionInformationManagementVO;
	}

	public void setSectionInformationManagementVO(SectionInformationManagementVO sectionInformationManagementVO) {
		this.sectionInformationManagementVO = sectionInformationManagementVO;
	}

}
