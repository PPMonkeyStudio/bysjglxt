package com.bysjglxt.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.VO.TeacherInformationManagementVO;
import com.bysjglxt.service.SectionInformationManagementService;
import com.bysjglxt.service.TeacherInformationManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TeacherInformationManagementAction extends ActionSupport
		implements ServletResponseAware, ServletRequestAware {

	private TeacherInformationManagementService teacherInformationManagementService;

	private SectionInformationManagementService sectionInformationManagementService;

	private HttpServletResponse http_response;

	private HttpServletRequest http_request;
	/*
	 * 删除所选学生列表
	 */
	private List<String> ListDeleteTeacherID;
	/*
	 * 学生excel
	 */
	private File EXCEL_Teacher;

	private String EXCEL_TeacherFileName;

	private String EXCEL_TeacherContentType;

	/*
	 * 
	 */
	private TeacherInformationManagementVO teacherInformationManagementVO;
	/*
	 * 修改的学生基础信息
	 */
	private bysjglxt_teacher_basic updateTeacher;
	/*
	 * 修改的学生基础信息
	 */
	private bysjglxt_teacher_basic newTeacher;

	/*
	 * 
	 */
	public String TeacherManagementPage() {

		return "TeacherManagementPage";
	}

	public String CreateTeacherPage() {

		return "CreateTeacherPage";
	}

	/**
	 * @说明 获取所有教师信息，通过ajax返回
	 * 
	 * @throws IOException
	 */
	public void ListTeacherByPageAndSearch() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		teacherInformationManagementVO = teacherInformationManagementService
				.VO_TEACHER_By_PageAndSearch(teacherInformationManagementVO);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(teacherInformationManagementVO));

	}

	/**
	 * @说明 预览Excel导入的教师信息
	 * 
	 * @throws Exception
	 */
	public void PreviewTeacherEXCEL() throws Exception {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();

		List<bysjglxt_teacher_basic> list_PreviewTeacherEXCEL = teacherInformationManagementService
				.convertTeacherExcelToList(EXCEL_Teacher, EXCEL_TeacherFileName);

		http_response.setContentType("text/html;charset=utf-8");

		http_response.getWriter().write(gson.toJson(list_PreviewTeacherEXCEL));
	}

	/**
	 * @说明
	 * @throws IOException
	 */
	public void DeleteTeacher() throws IOException {
		teacherInformationManagementService.remove_TeacherList(ListDeleteTeacherID);

		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");

	}

	/**
	 * @说明 在确认后存储导入的Excel教师信息
	 * 
	 * @throws Exception
	 */
	public void SaveTeacherEXCEL() throws Exception {
		http_response.setContentType("text/html;charset=utf-8");

		List<bysjglxt_teacher_basic> list_PreviewTeacherEXCEL = teacherInformationManagementService
				.convertTeacherExcelToList(EXCEL_Teacher, EXCEL_TeacherFileName);

		if (teacherInformationManagementService.saveTeacherList(list_PreviewTeacherEXCEL)) {
			http_response.getWriter().write("success");
		} else {
			http_response.getWriter().write("fail");
		}

	}

	/**
	 * @throws IOException
	 * @说明 修改教师基础信息
	 */
	public void UpdateTeacher() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");
	}

	/**
	 * @创建教师
	 */
	public void CreateTeacher() {
		teacherInformationManagementService.save_NewTeacher(newTeacher);
		http_response.setContentType("text/html;charset=utf-8");
		try {
			http_response.getWriter().write("success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @throws Exception
	 * @说明 获取学生专业
	 */
	public void GetTeacherSection() throws Exception {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		List<bysjglxt_section> list_Section = teacherInformationManagementService.listBysjglxtSection();

		http_response.getWriter().write(gson.toJson(list_Section));

	}

	public void ListTeacherAll() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter()
				.write(gson.toJson(teacherInformationManagementService.list_TeacherInformationDTO_All()));

	}
	/*
	 * 
	 */

	public void setTeacherInformationManagementService(
			TeacherInformationManagementService teacherInformationManagementService) {
		this.teacherInformationManagementService = teacherInformationManagementService;
	}

	public TeacherInformationManagementService getTeacherInformationManagementService() {
		return teacherInformationManagementService;
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

	public File getEXCEL_Teacher() {
		return EXCEL_Teacher;
	}

	public void setEXCEL_Teacher(File eXCEL_Teacher) {
		EXCEL_Teacher = eXCEL_Teacher;
	}

	public String getEXCEL_TeacherFileName() {
		return EXCEL_TeacherFileName;
	}

	public void setEXCEL_TeacherFileName(String eXCEL_TeacherFileName) {
		EXCEL_TeacherFileName = eXCEL_TeacherFileName;
	}

	public String getEXCEL_TeacherContentType() {
		return EXCEL_TeacherContentType;
	}

	public void setEXCEL_TeacherContentType(String eXCEL_TeacherContentType) {
		EXCEL_TeacherContentType = eXCEL_TeacherContentType;
	}

	public TeacherInformationManagementVO getTeacherInformationManagementVO() {
		return teacherInformationManagementVO;
	}

	public void setTeacherInformationManagementVO(TeacherInformationManagementVO teacherInformationManagementVO) {
		this.teacherInformationManagementVO = teacherInformationManagementVO;
	}

	public List<String> getListDeleteTeacherID() {
		return ListDeleteTeacherID;
	}

	public void setListDeleteTeacherID(List<String> listDeleteTeacherID) {
		ListDeleteTeacherID = listDeleteTeacherID;
	}

	public bysjglxt_teacher_basic getUpdateTeacher() {
		return updateTeacher;
	}

	public void setUpdateTeacher(bysjglxt_teacher_basic updateTeacher) {
		this.updateTeacher = updateTeacher;
	}

	public bysjglxt_teacher_basic getNewTeacher() {
		return newTeacher;
	}

	public void setNewTeacher(bysjglxt_teacher_basic newTeacher) {
		this.newTeacher = newTeacher;
	}

	public SectionInformationManagementService getSectionInformationManagementService() {
		return sectionInformationManagementService;
	}

	public void setSectionInformationManagementService(
			SectionInformationManagementService sectionInformationManagementService) {
		this.sectionInformationManagementService = sectionInformationManagementService;
	}

}
