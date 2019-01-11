package com.bysjglxt.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DO.bysjglxt_major;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.MajorVO;
import com.bysjglxt.service.MajorManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MajorManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	/*
	 * 
	 */
	private MajorManagementService majorManagementService;

	public void setMajorManagementService(MajorManagementService majorManagementService) {
		this.majorManagementService = majorManagementService;
	}

	/**
	 * 
	 */
	private HttpServletResponse http_response;
	private HttpServletRequest http_request;
	private MajorVO majorVO;
	private bysjglxt_major bysjglxtMajor;

	/**
	 * 根据专业名称获取专业
	 * @throws IOException
	 */
	public void getMajorByMajorName() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(majorManagementService.getMajorByMajorName(bysjglxtMajor)));
	}
	
	
	/**
	 * @throws IOException
	 * 
	 */

	public String MajorManagementPage() {

		return "MajorManagementPage";
	}

	// 获取该学院所有专业
	public void listMajorByPage() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userTeacherDTO") == null) {
			http_response.getWriter().write("error");
		}
		http_response.getWriter().write(gson.toJson(majorManagementService.majorManagementVO(majorVO,
										((TeacherInformationDTO) ActionContext.getContext().getSession()
												.get("userTeacherDTO")).getBysjglxtTeacherUser()
														.getUser_teacher_id())));
	}

	// 手动添加专业
	public void addMajor() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		// 1成功添加
		// -1数据问题
		// -2添加失败
		http_response.getWriter().write("" + majorManagementService.addMajor(bysjglxtMajor));
	}

	public void updateMajor() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("" + majorManagementService.updateSection(bysjglxtMajor));
	}

	// 不要用
	/*
	 * public void listSection() throws IOException { GsonBuilder gsonBuilder =
	 * new GsonBuilder(); gsonBuilder.setPrettyPrinting();// 格式化json数据 Gson gson
	 * = gsonBuilder.create();
	 * http_response.setContentType("text/html;charset=utf-8"); if
	 * (ActionContext.getContext().getSession().get("userTeacherDTO") == null) {
	 * http_response.getWriter().write("error"); } TeacherInformationDTO
	 * userTeacherDTO = (TeacherInformationDTO)
	 * ActionContext.getContext().getSession() .get("userTeacherDTO");
	 * List<SectionDTO> sectionDTO = new ArrayList<>(); sectionDTO =
	 * majorManagementService
	 * .listSectionDTO(userTeacherDTO.getBysjglxtTeacherUser().
	 * getUser_teacher_id());
	 * http_response.getWriter().write(gson.toJson(sectionDTO)); }
	 */

	/**
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

	/**
	 * 
	 */

	public MajorVO getMajorVO() {
		return majorVO;
	}

	public void setMajorVO(MajorVO majorVO) {
		this.majorVO = majorVO;
	}

	public bysjglxt_major getBysjglxtMajor() {
		return bysjglxtMajor;
	}

	public void setBysjglxtMajor(bysjglxt_major bysjglxtMajor) {
		this.bysjglxtMajor = bysjglxtMajor;
	}

	public MajorManagementService getMajorManagementService() {
		return majorManagementService;
	}

}
