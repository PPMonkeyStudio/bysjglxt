package com.bysjglxt.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.DTO.TopicInformationManagementDTO;
import com.bysjglxt.domain.VO.TopicInformationManagementVO;
import com.bysjglxt.service.TopicInformationManagementService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TopicInformationManagementAction extends ActionSupport
		implements ServletResponseAware, ServletRequestAware {
	/*
	 * 
	 */
	private TopicInformationManagementService topicInformationManagementService;

	private HttpServletResponse http_response;

	private HttpServletRequest http_request;
	/*
	 * 
	 */
	private TopicInformationManagementDTO topicInformationManagementDTO;

	private TopicInformationManagementVO topicInformationManagementVO;

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

		topicInformationManagementDTO.setTeacherInformationDTO(new TeacherInformationDTO());
		topicInformationManagementDTO.getTeacherInformationDTO().setBysjglxtTeacherUser(new bysjglxt_teacher_user());

		topicInformationManagementDTO.getTeacherInformationDTO().getBysjglxtTeacherUser()
				.setUser_teacher_id("27274613-c87f-40a2-a884-0fea646e5470");

		topicInformationManagementService.CreateTopic(topicInformationManagementDTO);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");
	}

	public void ListTopicByPageAndSearch() {
		topicInformationManagementService.VO_Topic_By_PageAndSearch(topicInformationManagementVO);
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

	public TopicInformationManagementDTO getTopicInformationDTO() {
		return topicInformationManagementDTO;
	}

	public void setTopicInformationDTO(TopicInformationManagementDTO topicInformationDTO) {
		this.topicInformationManagementDTO = topicInformationDTO;
	}

	public TopicInformationManagementService getTopicInformationManagementService() {
		return topicInformationManagementService;
	}

	public void setTopicInformationManagementService(
			TopicInformationManagementService topicInformationManagementService) {
		this.topicInformationManagementService = topicInformationManagementService;
	}

	public TopicInformationManagementDTO getTopicInformationManagementDTO() {
		return topicInformationManagementDTO;
	}

	public void setTopicInformationManagementDTO(TopicInformationManagementDTO topicInformationManagementDTO) {
		this.topicInformationManagementDTO = topicInformationManagementDTO;
	}

	public TopicInformationManagementVO getTopicInformationManagementVO() {
		return topicInformationManagementVO;
	}

	public void setTopicInformationManagementVO(TopicInformationManagementVO topicInformationManagementVO) {
		this.topicInformationManagementVO = topicInformationManagementVO;
	}

}
