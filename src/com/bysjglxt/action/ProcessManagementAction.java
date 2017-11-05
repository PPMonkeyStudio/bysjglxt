package com.bysjglxt.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DTO.ProcessDefinitionDetailDTO;
import com.bysjglxt.service.ProcessManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class ProcessManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private ProcessManagementService processManagementService;

	private HttpServletResponse http_response;

	private HttpServletRequest http_request;
	/*
	 * 
	 * 
	 * 
	 */

	private bysjglxt_process_definition newProcessDefinition;

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public String ProcessDefinitionDetailPage() {
		return "ProcessDefinitionDetailPage";
	}

	public String ProcessDefinitionListPage() {

		return "ProcessDefinitionListPage";
	}

	/**
	 * @throws IOException
	 * 
	 */
	public void ListProcessDefinition() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		List<bysjglxt_process_definition> ListProcessDefinition = processManagementService.listProcessDefinition();
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(ListProcessDefinition));

	}

	public void getProcessDefinitionDTO() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		ProcessDefinitionDetailDTO processDefinitionDetailDTO = null;
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(processDefinitionDetailDTO));
	}

	public void CreatProcessDefinition() throws IOException {
		processManagementService.createSelectTopicProcessDefine(newProcessDefinition);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");
	}

	/*
	 * 
	 */

	public ProcessManagementService getProcessManagementService() {
		return processManagementService;
	}

	public void setProcessManagementService(ProcessManagementService processManagementService) {
		this.processManagementService = processManagementService;
	}

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

	public bysjglxt_process_definition getNewProcessDefinition() {
		return newProcessDefinition;
	}

	public void setNewProcessDefinition(bysjglxt_process_definition newProcessDefinition) {
		this.newProcessDefinition = newProcessDefinition;
	}

}
