package com.bysjglxt.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.DTO.TopicInformationManagementDTO;
import com.bysjglxt.domain.VO.TopicInformationManagementVO;
import com.bysjglxt.service.TopicInformationManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

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
	 * 
	 */
	private String studentID;
	/*
	 * 学生选题
	 */
	private String studentSelectTopic;
	/*
	 * 搜索
	 */
	private String search;
	/*
	 * 分配选题
	 */
	private String assignmentStudentUserId;
	// 已更改成学生userId
	private String assignmentTopicId;
	private String assignmentReviewTeacherId;

	private bysjglxt_topic topic;

	private String topicId;

	
	public void getTeacherNotTopicInfo() throws IOException {
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		http_response.setContentType("text/html;charset=utf-8");
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		http_response.getWriter().write(gson.toJson(topicInformationManagementService.getTeacherNotTopicInfo(userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_belong_college())));
	}
	
	/**
	 * 获取当前年份的教师创建课题的情况列表
	 * @return
	 * @throws IOException 
	 */
	public void getTeacherTopicInfo() throws IOException {
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		http_response.setContentType("text/html;charset=utf-8");
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		http_response.getWriter().write(gson.toJson(topicInformationManagementService.getTeacherTopicInfo(userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_belong_college())));
	}
	
	
	/*
	 * 
	 */

	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	/**
	 * @throws IOException 
	 * 
	 */
	public void getTopicById() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		bysjglxt_topic topic = topicInformationManagementService.getTopicById(topicId);
		http_response.getWriter().write(gson.toJson(topic));
	}
	/**
	 * 获取所有的选题
	 * 
	 * @throws IOException
	 */
	public void listAllTopic() throws IOException {
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		http_response.setContentType("text/html;charset=utf-8");
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		System.out.println("d:"+topic);
		List<bysjglxt_topic> list_topic = topicInformationManagementService.listAllTopic(topic,
				userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_belong_college());
		http_response.getWriter().write(gson.toJson(list_topic));
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

	public void listSelectBysjglxtTopic() throws IOException {
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		http_response.setContentType("text/html;charset=utf-8");
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		List<bysjglxt_topic> list_topic = topicInformationManagementService
				.listSelectBysjglxtTopic(userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id());
		http_response.getWriter().write(gson.toJson(list_topic));
	}

	/*
	 * 分配评阅教师
	 * 
	 */
	public void AssignmentReviewTeacher() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		topicInformationManagementService.assignment(assignmentTopicId, assignmentReviewTeacherId);
		http_response.getWriter().write("success");
	}

	/**
	 * 创建新的课题
	 * 
	 * @throws IOException
	 */
	public void CreateTopic() throws IOException {
		topicInformationManagementDTO.setTeacherInformationDTO(new TeacherInformationDTO());
		topicInformationManagementDTO.getTeacherInformationDTO().setBysjglxtTeacherUser(new bysjglxt_teacher_user());
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		topicInformationManagementDTO.getTeacherInformationDTO().getBysjglxtTeacherUser()
				.setUser_teacher_id(userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id());
		topicInformationManagementService.CreateTopic(topicInformationManagementDTO,userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_belong_college());
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");

	}

	public void UpdateTopic() throws IOException {
		topicInformationManagementService.updateTopic(topicInformationManagementDTO.getBysjglxtTopic());
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");
	}

	/**
	 * VO 课题列表
	 * 
	 * @throws IOException
	 */
	public void ListTopicByPageAndSearch() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userStudentDTO") == null) {
			TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
					.get("userTeacherDTO");
			http_response.getWriter().write(gson.toJson(topicInformationManagementService.VO_Topic_By_PageAndSearch(
					topicInformationManagementVO, 1, userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id())));
		} else {
			StudentInformationDTO userStudentDTO = (StudentInformationDTO) ActionContext.getContext().getSession()
					.get("userStudentDTO");
			http_response.getWriter().write(gson.toJson(topicInformationManagementService.VO_Topic_By_PageAndSearch(
					topicInformationManagementVO, 2, userStudentDTO.getBysjglxtStudentUser().getUser_student_id())));
		}
	}

	/*
	 * 指定或取消学生选题
	 */
	public void distributionTopicStudent() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		topicInformationManagementService.distributionTopicStudent(studentSelectTopic, studentID);
		http_response.getWriter().write("success");
	}

	/**
	 * @throws IOException
	 * 
	 */
	public void listDesignationStudentInformation() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		/*
		 * 
		 */
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		if (search == null) {
			search = "";
		} else {
		}
		/*
		 * 
		 */
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(topicInformationManagementService.listDesignationStudentInformation(
				studentSelectTopic, "-1", "-1", search, userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id())));
	}

	/**
	 * VO 我的课题
	 * 
	 * @throws IOException
	 */
	public void ListMyTopicByPageAndSearch() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		//
		if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			//
			StudentInformationDTO studentInformationDTO = (StudentInformationDTO) ActionContext.getContext()
					.getSession().get("userStudentDTO");
			//
			http_response.getWriter()
					.write(gson.toJson(topicInformationManagementService.VO_TopicBelongStudent_By_PageAndSearch(
							topicInformationManagementVO,
							studentInformationDTO.getBysjglxtStudentUser().getUser_student_id())));
		} else if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			//
			TeacherInformationDTO teacherInformationDTO = (TeacherInformationDTO) ActionContext.getContext()
					.getSession().get("userTeacherDTO");
			//
			http_response.getWriter()
					.write(gson.toJson(topicInformationManagementService.VO_TopicBelongTeacher_By_PageAndSearch(
							topicInformationManagementVO,
							teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id())));
		}

	}

	public void agreeTopicList() throws IOException {
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		topicInformationManagementService.adoptTopic(listAgreeTopicID,
				userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id());
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");
	}

	public void refuseTopicList() throws IOException {
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		topicInformationManagementService.notAdoptTopic(listRefuseTopicID,
				userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id());
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
	 * 分配课题
	 * @throws IOException 
	 */
	public void assignmentStudentTopic() throws IOException {
		topicInformationManagementService.assignmentStudentTopic(assignmentStudentUserId, assignmentTopicId);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");
	}

	public void dropTopic() throws IOException {
		topicInformationManagementService
				.dropTopic(((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
						.getBysjglxtStudentUser().getUser_student_id());
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

	public String getAssignmentStudentUserId() {
		return assignmentStudentUserId;
	}

	public void setAssignmentStudentUserId(String assignmentStudentUserId) {
		this.assignmentStudentUserId = assignmentStudentUserId;
	}

	public String getAssignmentTopicId() {
		return assignmentTopicId;
	}

	public void setAssignmentTopicId(String assignmentTopicId) {
		this.assignmentTopicId = assignmentTopicId;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getAssignmentReviewTeacherId() {
		return assignmentReviewTeacherId;
	}

	public void setAssignmentReviewTeacherId(String assignmentReviewTeacherId) {
		this.assignmentReviewTeacherId = assignmentReviewTeacherId;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public bysjglxt_topic getTopic() {
		return topic;
	}

	public void setTopic(bysjglxt_topic topic) {
		this.topic = topic;
	}

}
