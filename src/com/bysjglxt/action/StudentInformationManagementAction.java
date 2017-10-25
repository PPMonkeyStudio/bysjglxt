package com.bysjglxt.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.VO.StudentInformationManagementVO;
import com.bysjglxt.service.StudentInformationManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class StudentInformationManagementAction extends ActionSupport
		implements ServletResponseAware, ServletRequestAware {

	private StudentInformationManagementService studentInformationManagementService;

	private HttpServletResponse http_response;

	private HttpServletRequest http_request;

	/**
	 * 
	 * @return
	 */
	public String listPage() {

		return "listPage";
	}

	/**
	 * 获取所有学生信息，通过ajax返回
	 */
	public void ListStudentAll() {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();

		StudentInformationManagementVO studentInformationManagementVO = new StudentInformationManagementVO();

		// 获取所有学生信息列表
		List<StudentInformationDTO> list_StudentInformationDTO_All = studentInformationManagementService
				.list_StudentInformationDTO_All();

		studentInformationManagementVO.setList_StudentInformationDTO(list_StudentInformationDTO_All);

		try {

			http_response.setContentType("text/html;charset=utf-8");

			http_response.getWriter().write(gson.toJson(studentInformationManagementVO));

		} catch (IOException e) {

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

	public StudentInformationManagementService getStudentInformationManagementService() {
		return studentInformationManagementService;
	}

	public void setStudentInformationManagementService(
			StudentInformationManagementService studentInformationManagementService) {
		this.studentInformationManagementService = studentInformationManagementService;
	}

}
