package com.bysjglxt.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DO.bysjglxt_admin;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.service.LoginOrWriteOffService;
import com.bysjglxt.service.StudentInformationManagementService;
import com.bysjglxt.service.TeacherInformationManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginOrWriteOffAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private LoginOrWriteOffService loginOrWriteOffService;
	private StudentInformationManagementService studentInformationManagementService;
	private TeacherInformationManagementService teacherInformationManagementService;
	private HttpServletResponse http_response;
	private HttpServletRequest http_request;
	/*
	 * 
	 */
	private String username;
	private String password;
	/*
	 * 
	 */
	private String oldPassword;
	private String newPassword;

	/*
	 * 登录
	 */
	public void login() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		switch (loginOrWriteOffService.login(username, password)) {
		//
		case -1: {
			http_response.getWriter().write("账号不存在");
			break;
		}
		//
		case -2: {
			http_response.getWriter().write("密码不正确");
			break;
		}
		//
		case -3: {
			http_response.getWriter().write("无权限");
			break;
		}
		// 教师登录
		case 1: {
			TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) loginOrWriteOffService.loginInformation(1,
					username);
			ActionContext.getContext().getSession().put("userTeacherDTO", userTeacherDTO);
			ActionContext.getContext().getSession().remove("MyTutorGraduationProjectStudentID");
			http_response.getWriter().write("教师登录成功");
			break;
		}
		// 学生登录
		case 2: {
			StudentInformationDTO userStudentDTO = (StudentInformationDTO) loginOrWriteOffService.loginInformation(2,
					username);
			ActionContext.getContext().getSession().put("userStudentDTO", userStudentDTO);
			ActionContext.getContext().getSession().remove("MyTutorGraduationProjectStudentID");
			http_response.getWriter().write("学生登录成功");
			break;
		}
		// 管理员登录
		case 3: {
			bysjglxt_admin bysjglxt_admin = (bysjglxt_admin) loginOrWriteOffService.loginInformation(3, username);
			ActionContext.getContext().getSession().put("admin", bysjglxt_admin);
			ActionContext.getContext().getSession().remove("MyTutorGraduationProjectStudentID");
			http_response.getWriter().write("管理员登录成功");
			break;
		}
		default:
			break;
		}
	}

	/*
	 * 登出
	 */
	public String logout() {
		// 移出session
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			ActionContext.getContext().getSession().remove("userTeacherDTO");
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			ActionContext.getContext().getSession().remove("userStudentDTO");
		} else if (ActionContext.getContext().getSession().get("admin") != null) {
			ActionContext.getContext().getSession().remove("admin");
		}

		return "logout";

	}

	public void getUserSessionForAjax() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			http_response.getWriter().write(gson.toJson(ActionContext.getContext().getSession().get("userTeacherDTO")));
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			http_response.getWriter().write(gson.toJson(ActionContext.getContext().getSession().get("userStudentDTO")));
		} else if (ActionContext.getContext().getSession().get("admin") != null) {
			http_response.getWriter().write(gson.toJson(ActionContext.getContext().getSession().get("admin")));
		}
	}

	/*
	 * 
	 */
	public void updatePassword() {
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			teacherInformationManagementService.updatePassword(
					((TeacherInformationDTO) ActionContext.getContext().getSession().get("userTeacherDTO"))
							.getBysjglxtTeacherUser().getUser_teacher_id(),
					newPassword);
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			studentInformationManagementService.updatePassword(
					((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
							.getBysjglxtStudentUser().getUser_student_id(),
					newPassword);
		} else if (ActionContext.getContext().getSession().get("admin") != null) {
		}
	}

	/**
	 * 跳转到首页
	 * 
	 * @return
	 */
	public String index() {
		return "index";
	}

	public String navbar() {
		return "navbar";
	}

	/*
	 * 
	 */
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

	/*
	 * 
	 */
	public void setLoginOrWriteOffService(LoginOrWriteOffService loginOrWriteOffService) {
		this.loginOrWriteOffService = loginOrWriteOffService;
	}

	public LoginOrWriteOffService getLoginOrWriteOffService() {
		return loginOrWriteOffService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public StudentInformationManagementService getStudentInformationManagementService() {
		return studentInformationManagementService;
	}

	public void setStudentInformationManagementService(
			StudentInformationManagementService studentInformationManagementService) {
		this.studentInformationManagementService = studentInformationManagementService;
	}

	public TeacherInformationManagementService getTeacherInformationManagementService() {
		return teacherInformationManagementService;
	}

	public void setTeacherInformationManagementService(
			TeacherInformationManagementService teacherInformationManagementService) {
		this.teacherInformationManagementService = teacherInformationManagementService;
	}
}
