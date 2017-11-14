package com.bysjglxt.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.NoticeVO;
import com.bysjglxt.service.NoticeManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class NoticeManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private NoticeManagementService noticeManagementService;
	private HttpServletResponse http_response;
	private HttpServletRequest http_request;
	/*
	 * 
	 */
	private NoticeVO noticeVO;
	private String readNoticeID;

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public String NoticeManagementPage() {

		return "NoticeManagementPage";
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void ListNoticeByPage() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			http_response
					.getWriter().write(
							gson.toJson(
									noticeManagementService.noticeVO(noticeVO,
											((TeacherInformationDTO) ActionContext.getContext().getSession()
													.get("userTeacherDTO")).getBysjglxtTeacherUser()
															.getUser_teacher_id())));
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			http_response
					.getWriter().write(
							gson.toJson(
									noticeManagementService.noticeVO(noticeVO,
											((StudentInformationDTO) ActionContext.getContext().getSession()
													.get("userStudentDTO")).getBysjglxtStudentUser()
															.getUser_student_id())));
		}

	}

	public void readNotice() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		noticeManagementService.updateNoticeState(readNoticeID);
		http_response.getWriter().write("success");
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
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

	public void setNoticeManagementService(NoticeManagementService noticeManagementService) {
		this.noticeManagementService = noticeManagementService;
	}

	public NoticeManagementService getNoticeManagementService() {
		return noticeManagementService;
	}

	public NoticeVO getNoticeVO() {
		return noticeVO;
	}

	public void setNoticeVO(NoticeVO noticeVO) {
		this.noticeVO = noticeVO;
	}

	public String getReadNoticeID() {
		return readNoticeID;
	}

	public void setReadNoticeID(String readNoticeID) {
		this.readNoticeID = readNoticeID;
	}

}
