package com.bysjglxt.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.service.TopicManagementService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TopicManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	/*
	 * 
	 */
	private TopicManagementService topicManagementService;

	private HttpServletResponse http_response;

	private HttpServletRequest http_request;
	/*
	 * 
	 */
	private bysjglxt_topic newTopic;

	/**
	 * @说明 跳转列表页
	 * 
	 * @return
	 */
	public String TopicListPage() {

		return "TopicListPage";
	}

	/**
	 * @说明 跳转创建课题页
	 * 
	 * @return
	 */
	public String CreateTopicPage() {
		return "CreateTopicPage";
	}

	/**
	 * 创建新的课题
	 * 
	 * @throws IOException
	 */
	public void CreateTopic() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");
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

	public TopicManagementService getTopicManagementService() {
		return topicManagementService;
	}

	public void setTopicManagementService(TopicManagementService topicManagementService) {
		this.topicManagementService = topicManagementService;
	}

	public bysjglxt_topic getNewTopic() {
		return newTopic;
	}

	public void setNewTopic(bysjglxt_topic newTopic) {
		this.newTopic = newTopic;
	}

}
