package com.bysjglxt.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.DTO.TopicInformationManagementDTO;
import com.bysjglxt.domain.VO.TopicInformationManagementVO;
import com.bysjglxt.service.TopicInformationManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
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

	/*
	 * 
	 */
	private List<String> listAgreeTopicID;
	private List<String> listRefuseTopicID;
	private List<String> listCloseTopicID;
	private List<String> listDeleteTopicID;
	/*
	 * 学生选题
	 */
	private String studentSelectTopic;
	/*
	 * 
	 */

	/**
	 * 创建新的课题
	 * 
	 * @throws IOException
	 */
	public void CreateTopic() throws IOException {

		topicInformationManagementDTO.setTeacherInformationDTO(new TeacherInformationDTO());
		topicInformationManagementDTO.getTeacherInformationDTO().setBysjglxtTeacherUser(new bysjglxt_teacher_user());

		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
					.get("userTeacherDTO");
			topicInformationManagementDTO.getTeacherInformationDTO().getBysjglxtTeacherUser()
					.setUser_teacher_id(userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id());
		} else {
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("登录状态已失效");
			System.err.println("登录状态已失效");
			return;
		}

		topicInformationManagementService.CreateTopic(topicInformationManagementDTO);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");

	}

	/**
	 * 
	 * @throws IOException
	 */
	public void ListTopicByPageAndSearch() throws IOException {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(
				gson.toJson(topicInformationManagementService.VO_Topic_By_PageAndSearch(topicInformationManagementVO)));

	}

	/**
	 * 
	 * @throws IOException
	 */
	public void ListMyTopicByPageAndSearch() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(
				gson.toJson(topicInformationManagementService.VO_Topic_By_PageAndSearch(topicInformationManagementVO)));
	}

	/*
	 * public void aReact() throws IOException {
	 * http_response.setContentType("text/html;charset=utf-8");
	 * http_response.getWriter().write("readsdsds"); }
	 */

	public void agreeTopicList() throws IOException {
		topicInformationManagementService.adoptTopic(listAgreeTopicID);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");
	}

	public void refuseTopicList() throws IOException {
		topicInformationManagementService.notAdoptTopic(listRefuseTopicID);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");
	}

	public void closeTopicList() throws IOException {
		topicInformationManagementService.closeTopic(listCloseTopicID);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");
	}

	public void deleteTopicList() throws IOException {
		topicInformationManagementService.DeleteTopic(listDeleteTopicID);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");
	}

	public void studentSelectTopic() throws IOException {
		StudentInformationDTO userStudentDTO = (StudentInformationDTO) ActionContext.getContext().getSession()
				.get("userStudentDTO");
		http_response.setContentType("text/html;charset=utf-8");
		switch (topicInformationManagementService
				.selectTopic(userStudentDTO.getBysjglxtStudentUser().getUser_student_id(), studentSelectTopic)) {
		case 1: {
			http_response.getWriter().write("1");
			break;
		}
		case -1: {
			http_response.getWriter().write("-1");
			break;
		}
		case -2: {
			http_response.getWriter().write("-2");
			break;
		}
		case -3: {
			http_response.getWriter().write("-3");
			break;
		}
		case -4: {
			http_response.getWriter().write("-4");
			break;
		}
		default: {
			http_response.getWriter().write("-4");
			break;
		}
		}
	}

	/**
	 * @说明 跳转列表页
	 * 
	 * @return
	 */
	public String TopicListPage() {

		return "TopicListPage";
	}

	/**
	 * @说明 跳转列表页
	 * 
	 * @return
	 */
	public String MyTopicListPage() {

		return "MyTopicListPage";
	}

	/**
	 * @说明 跳转创建课题页
	 * 
	 * @return
	 */
	public String CreateTopicPage() {
		return "CreateTopicPage";
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

	public List<String> getListAgreeTopicID() {
		return listAgreeTopicID;
	}

	public void setListAgreeTopicID(List<String> listAgreeTopicID) {
		this.listAgreeTopicID = listAgreeTopicID;
	}

	public List<String> getListRefuseTopicID() {
		return listRefuseTopicID;
	}

	public void setListRefuseTopicID(List<String> listRefuseTopicID) {
		this.listRefuseTopicID = listRefuseTopicID;
	}

	public List<String> getListCloseTopicID() {
		return listCloseTopicID;
	}

	public void setListCloseTopicID(List<String> listCloseTopicID) {
		this.listCloseTopicID = listCloseTopicID;
	}

	public List<String> getListDeleteTopicID() {
		return listDeleteTopicID;
	}

	public void setListDeleteTopicID(List<String> listDeleteTopicID) {
		this.listDeleteTopicID = listDeleteTopicID;
	}

	public String getStudentSelectTopic() {
		return studentSelectTopic;
	}

	public void setStudentSelectTopic(String studentSelectTopic) {
		this.studentSelectTopic = studentSelectTopic;
	}

}
