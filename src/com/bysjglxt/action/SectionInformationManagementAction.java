package com.bysjglxt.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.SectionInformationManagementVO;
import com.bysjglxt.service.SectionInformationManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SectionInformationManagementAction extends ActionSupport
		implements ServletResponseAware, ServletRequestAware {
	/*
	 * 
	 */
	private SectionInformationManagementService sectionInformationManagementService;

	private HttpServletResponse http_response;

	private HttpServletRequest http_request;
	/*
	 * 
	 * 
	 */
	private SectionInformationManagementVO sectionInformationManagementVO;
	/*
	 * 
	 */
	private bysjglxt_section newSection;
	private bysjglxt_section updateSection;
	/*
	 * 删除所选教研室列表
	 */
	private List<String> ListDeleteSectionID;

	/**
	 * @说明 跳转列表页
	 * 
	 * @return
	 */
	public String SectionManagementPage() {

		return "SectionManagementPage";
	}

	/**
	 * @说明 跳转手动添加学生页
	 * 
	 * @return
	 */
	public String CreateSectionPage() {
		return "CreateSectionPage";
	}

	/**
	 * 删除所选教研室
	 * 
	 * @throws IOException
	 */
	public void DeleteSection() throws IOException {
		sectionInformationManagementService.deleteSection(ListDeleteSectionID);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");
	}

	/**
	 * @throws IOException
	 */
	public void ListSectionByPage() {
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();
			Gson gson = gsonBuilder.create();
			TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
					.get("userTeacherDTO");
			sectionInformationManagementVO = sectionInformationManagementService.VO_Section_By_Page(
					sectionInformationManagementVO, userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id());
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write(gson.toJson(sectionInformationManagementVO));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @throws IOException
	 */
	public void CreateSection() throws IOException {
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		sectionInformationManagementService.Create_Section(newSection,
				userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id());
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");
	}

	/**
	 * @throws IOException
	 * 
	 */
	public void UpdateSection() throws IOException {
		sectionInformationManagementService.updateSection(updateSection);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");
	}

	/**
	 * 遍历出属于操作者的所有教研室
	 * 
	 * @throws IOException
	 */
	public void listBysjglxtSection() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userTeacherDTO") == null) {
			http_response.getWriter().write("error");
		}
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		http_response.getWriter().write(gson.toJson(sectionInformationManagementService
				.listBysjglxtSection(userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id())));
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

	/*
	 * 
	 */
	public SectionInformationManagementService getSectionInformationManagementService() {
		return sectionInformationManagementService;
	}

	public void setSectionInformationManagementService(
			SectionInformationManagementService sectionInformationManagementService) {
		this.sectionInformationManagementService = sectionInformationManagementService;
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

	public SectionInformationManagementVO getSectionInformationManagementVO() {
		return sectionInformationManagementVO;
	}

	public void setSectionInformationManagementVO(SectionInformationManagementVO sectionInformationManagementVO) {
		this.sectionInformationManagementVO = sectionInformationManagementVO;
	}

	public bysjglxt_section getNewSection() {
		return newSection;
	}

	public void setNewSection(bysjglxt_section newSection) {
		this.newSection = newSection;
	}

	public List<String> getListDeleteSectionID() {
		return ListDeleteSectionID;
	}

	public void setListDeleteSectionID(List<String> listDeleteSectionID) {
		ListDeleteSectionID = listDeleteSectionID;
	}

	public bysjglxt_section getUpdateSection() {
		return updateSection;
	}

	public void setUpdateSection(bysjglxt_section updateSection) {
		this.updateSection = updateSection;
	}

}
