package com.bysjglxt.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
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
	private String processDefinitionId;

	private bysjglxt_task_definition newTaskDefinition;

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
		ProcessDefinitionDetailDTO processDefinitionDetailDTO = processManagementService
				.processDefinitionDetailDTO(processDefinitionId);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(processDefinitionDetailDTO));
	}

	/**
	 * 创建流程定义
	 * 
	 * @throws IOException
	 */
	public void CreatProcessDefinition() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(processManagementService.createSelectTopicProcessDefine(newProcessDefinition));
	}

	/**
	 * 创建任务定义
	 * 
	 * @throws IOException
	 */
	public void CreatTaskDefinition() throws IOException {
		System.out.println(newTaskDefinition);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(processManagementService.createSelectTopicTaskDefine(newTaskDefinition));

	}

	/**
	 * 进入更新流程定义（即添加任务节点）的状态
	 * 
	 * @throws IOException
	 */
	public void UpdateProcessDefinition() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		ProcessDefinitionDetailDTO processDefinitionDetailDTO = processManagementService
				.processDefinitionDetailDTO(processDefinitionId);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(processDefinitionDetailDTO));
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

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public bysjglxt_task_definition getNewTaskDefinition() {
		return newTaskDefinition;
	}

	public void setNewTaskDefinition(bysjglxt_task_definition newTaskDefinition) {
		this.newTaskDefinition = newTaskDefinition;
	}

}
