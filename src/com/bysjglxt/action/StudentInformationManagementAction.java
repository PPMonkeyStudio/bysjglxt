package com.bysjglxt.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.VO.StudentInformationManagementVO;
import com.bysjglxt.service.StudentInformationManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class StudentInformationManagementAction extends ActionSupport
		implements ServletResponseAware, ServletRequestAware {
	/*
	 * 
	 */
	private StudentInformationManagementService studentInformationManagementService;

	private HttpServletResponse http_response;

	private HttpServletRequest http_request;

	/*
	 * 学生excel
	 */
	private File EXCEL_Student;

	private String EXCEL_StudentFileName;

	private String EXCEL_StudentContentType;

	/*
	 * 从js中返回来的学生信息list
	 */
	private List<bysjglxt_student_basic> Save_Student_EXCEL_List;

	private StudentInformationManagementVO studentInformationManagementVO;

	/*
	 * 删除所选学生列表
	 */
	private List<String> ListDeleteStudentID;

	/*
	 * 手动添加的学生
	 */
	private bysjglxt_student_basic newStudent;
	/*
	 * 修改的学生基础信息
	 */
	private bysjglxt_student_basic updateStudent;

	/**
	 * @说明 跳转列表页
	 * 
	 * @return
	 */
	public String listPage() {

		return "listPage";
	}

	/**
	 * @说明 跳转手动添加学生页
	 * 
	 * @return
	 */
	public String CreateStudentPage() {
		return "CreateStudentPage";
	}

	/**
	 * @说明 获取所有学生信息，通过ajax返回
	 * 
	 * @throws IOException
	 */
	public void ListStudentByPageAndSearch() throws IOException {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		studentInformationManagementVO = studentInformationManagementService
				.VO_Student_By_PageAndSearch(studentInformationManagementVO);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(studentInformationManagementVO));
	}

	/**
	 * 
	 * @说明 预览Excel导入的学生信息
	 * 
	 * @throws Exception
	 */
	public void PreviewStudentEXCEL() throws Exception {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();

		List<bysjglxt_student_basic> list_PreviewStudentEXCEL = studentInformationManagementService
				.convertStudentExcelToList(EXCEL_Student, EXCEL_StudentFileName);

		http_response.setContentType("text/html;charset=utf-8");

		http_response.getWriter().write(gson.toJson(list_PreviewStudentEXCEL));

	}

	/**
	 * @说明 在确认后存储导入的Excel学生信息
	 * 
	 * @throws Exception
	 */
	public void SaveStudentEXCEL() throws Exception {
		http_response.setContentType("text/html;charset=utf-8");

		List<bysjglxt_student_basic> list_PreviewStudentEXCEL = studentInformationManagementService
				.convertStudentExcelToList(EXCEL_Student, EXCEL_StudentFileName);

		if (studentInformationManagementService.saveStudentList(list_PreviewStudentEXCEL)) {
			http_response.getWriter().write("success");
		} else {
			http_response.getWriter().write("fail");
		}

	}

	/**
	 * @说明
	 * @throws IOException
	 */
	public void DeleteStudent() throws IOException {
		studentInformationManagementService.remove_StudentList(ListDeleteStudentID);

		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");

	}

	/**
	 * 
	 * @throws Exception
	 * @说明 获取学生专业
	 */
	public void GetStudentMajor() throws Exception {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		List<String> list_Student_Major = studentInformationManagementService.list_Student_Major();

		http_response.getWriter().write(gson.toJson(list_Student_Major));

	}

	/**
	 * 
	 * @throws Exception
	 * @说明 获取学生年级
	 */
	public void GetStudentGrade() throws Exception {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		List<String> list_Student_Grade = studentInformationManagementService.list_Student_Grade();

		http_response.getWriter().write(gson.toJson(list_Student_Grade));
	}

	/**
	 * @throws IOException
	 * @说明 手动添加的学生
	 */
	public void CreateStudent() throws IOException {
		studentInformationManagementService.save_NewStudent(newStudent);
		http_response.setContentType("text/html;charset=utf-8");

		http_response.getWriter().write("success");
	}

	/**
	 * @throws IOException
	 * @说明 修改学生基础信息
	 */
	public void UpdateStudent() throws IOException {
		studentInformationManagementService.update_StudentBasicInfomation(updateStudent);
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

	public List<bysjglxt_student_basic> getSave_Student_EXCEL_List() {
		return Save_Student_EXCEL_List;
	}

	public void setSave_Student_EXCEL_List(List<bysjglxt_student_basic> save_Student_EXCEL_List) {
		Save_Student_EXCEL_List = save_Student_EXCEL_List;
	}

	public File getEXCEL_Student() {
		return EXCEL_Student;
	}

	public void setEXCEL_Student(File eXCEL_Student) {
		EXCEL_Student = eXCEL_Student;
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

	public StudentInformationManagementVO getStudentInformationManagementVO() {
		return studentInformationManagementVO;
	}

	public void setStudentInformationManagementVO(StudentInformationManagementVO studentInformationManagementVO) {
		this.studentInformationManagementVO = studentInformationManagementVO;
	}

	public String getEXCEL_StudentContentType() {
		return EXCEL_StudentContentType;
	}

	public void setEXCEL_StudentContentType(String eXCEL_StudentContentType) {
		EXCEL_StudentContentType = eXCEL_StudentContentType;
	}

	public StudentInformationManagementService getStudentInformationManagementService() {
		return studentInformationManagementService;
	}

	public List<String> getListDeleteStudentID() {
		return ListDeleteStudentID;
	}

	public void setListDeleteStudentID(List<String> listDeleteStudentID) {
		ListDeleteStudentID = listDeleteStudentID;
	}

	public String getEXCEL_StudentFileName() {
		return EXCEL_StudentFileName;
	}

	public void setEXCEL_StudentFileName(String eXCEL_StudentFileName) {
		EXCEL_StudentFileName = eXCEL_StudentFileName;
	}

	public void setStudentInformationManagementService(
			StudentInformationManagementService studentInformationManagementService) {
		this.studentInformationManagementService = studentInformationManagementService;
	}

	public bysjglxt_student_basic getNewStudent() {
		return newStudent;
	}

	public void setNewStudent(bysjglxt_student_basic newStudent) {
		this.newStudent = newStudent;
	}

	public bysjglxt_student_basic getUpdateStudent() {
		return updateStudent;
	}

	public void setUpdateStudent(bysjglxt_student_basic updateStudent) {
		this.updateStudent = updateStudent;
	}

}
