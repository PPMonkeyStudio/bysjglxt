package com.bysjglxt.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DO.bysjglxt_college;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DTO.CollegeInformationDTO;
import com.bysjglxt.service.CollegeManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @date 2018/2/19
 * @author JXX
 *
 */
public class CollegeManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private HttpServletResponse http_response;
	private HttpServletRequest http_request;
	/*
	 * 
	 */
	private CollegeManagementService collegeManagementService;
	private bysjglxt_teacher_user bysjglxt_teacher_user;
	private bysjglxt_teacher_basic bysjglxt_teacher_basic;
	private bysjglxt_college college;

	/*
	 * 
	 */
	/**
	 * 
	 * @return 'webString'
	 */
	public String CollegeManagementPage() {
		return "CollegeManagementPage";
	}

	/**
	 * 根据学院编号获取学院管理员信息
	 */
	public void getCollegeAdminInfoByCollegeCode() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		try {
			CollegeInformationDTO collegeInformationDTO = collegeManagementService
					.getCollegeAdminInfoByCollegeCode(college.getCollege_code());
			if (collegeInformationDTO == null) {
				collegeInformationDTO.setState("error");
			}
			http_response.getWriter().write(gson.toJson(collegeInformationDTO));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 遍历出所有的学院
	 * 
	 */
	public void listAllCollege() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		try {
			http_response.getWriter().write(gson.toJson(collegeManagementService.listCollegeInformationDTO()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改管理员
	 */
	public void updateAdmin() {
		if (collegeManagementService.updateCollegeAdmin(bysjglxt_teacher_user.getUser_teacher_id()) == -1) {
			try {
				http_response.getWriter().write("系统错误修改失败");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				http_response.getWriter().write("修改成功");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 添加学院
	 */
	public void addCollege() {
		try {
			// 教师信息更改为由后台自动的生成,不再从前台获取
			http_response.getWriter().write(collegeManagementService.addCollege(college, bysjglxt_teacher_basic) + "");
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

	public void setCollegeManagementService(CollegeManagementService collegeManagementService) {
		this.collegeManagementService = collegeManagementService;
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

	public bysjglxt_teacher_user getBysjglxt_teacher_user() {
		return bysjglxt_teacher_user;
	}

	public void setBysjglxt_teacher_user(bysjglxt_teacher_user bysjglxt_teacher_user) {
		this.bysjglxt_teacher_user = bysjglxt_teacher_user;
	}

	public bysjglxt_college getCollege() {
		return college;
	}

	public void setCollege(bysjglxt_college college) {
		this.college = college;
	}

	public CollegeManagementService getCollegeManagementService() {
		return collegeManagementService;
	}

	public bysjglxt_teacher_basic getBysjglxt_teacher_basic() {
		return bysjglxt_teacher_basic;
	}

	public void setBysjglxt_teacher_basic(bysjglxt_teacher_basic bysjglxt_teacher_basic) {
		this.bysjglxt_teacher_basic = bysjglxt_teacher_basic;
	}

	/*
	 * 
	 */

}
