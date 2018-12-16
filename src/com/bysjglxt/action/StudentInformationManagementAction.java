package com.bysjglxt.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DO.bysjglxt_major;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DTO.ExportGeaduationStudentDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.StudentInformationManagementVO;
import com.bysjglxt.service.StudentInformationManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
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

	private ExportGeaduationStudentDTO exportGeaduationStudentDTO;

	/*
	 * 删除所选学生列表
	 */
	private List<String> ListDeleteStudentID;
	/*
	 * 
	 */
	private List<String> ListGiveOperatePremissionStudentID;
	/*
	 * 
	 */
	private List<String> ListTakeOperatePremissionStudentID;

	/*
	 * 手动添加的学生
	 */
	private bysjglxt_student_basic newStudent;
	/*
	 * 修改的学生基础信息
	 */
	private bysjglxt_student_basic updateStudent;

	private bysjglxt_student_user studentUser;

	private bysjglxt_major major;

	/**
	 * @说明 跳转列表页
	 * 
	 * @return
	 */
	public String listPage() {

		return "listPage";
	}

	/**
	 * @说明 跳转列学生信息管理页
	 * 
	 * @return
	 */
	public String listStudentInfoPage() {
		return "listStudentInfoPage";
	}
	
	/**
	 * @说明 跳转手动添加学生页
	 * 
	 * @return
	 */
	public String CreateStudentPage() {
		return "CreateStudentPage";
	}

	// 遍历可导出毕业设计过程管理手册学生
	public void listStudentGreauation() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		exportGeaduationStudentDTO = studentInformationManagementService.listStudentGreauation(
				exportGeaduationStudentDTO, userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id());
		http_response.setContentType("text/html;charset=utf-8");
		try {
			http_response.getWriter().write(gson.toJson(exportGeaduationStudentDTO));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 分配学生专业
	// 1.分配的专业id
	// 2.谁改变专业studentUserId
	public void distributionStudentMajor() {
		http_response.setContentType("text/html;charset=utf-8");
		try {
			if (studentInformationManagementService.distributionStudentMajor(studentUser.getUser_student_id(),
					major.getMajor_id()) == -1) {
				http_response.getWriter().write("分配失败");
			} else {
				http_response.getWriter().write("分配成功");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

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
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		studentInformationManagementVO = studentInformationManagementService.VO_Student_By_PageAndSearch(
				studentInformationManagementVO, userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id());
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(studentInformationManagementVO));
	}

	public void listStudentNoClose() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(studentInformationManagementService
				.listStudentNoClose(userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id())));
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
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		if (studentInformationManagementService.saveStudentList(list_PreviewStudentEXCEL,
				userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id())) {
			http_response.getWriter().write("success");
		} else {
			http_response.getWriter().write("数据重复");
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

	// 打开学生
	public void GiveStudentOperatePremission() throws IOException {
		studentInformationManagementService.update_Give_Student_Operate_Permission(ListGiveOperatePremissionStudentID);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write("success");

	}

	// 关闭学生信息
	public void TakeStudentOperatePremission() throws IOException {
		studentInformationManagementService.update_Take_Student_Operate_Permission(ListTakeOperatePremissionStudentID);
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
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		http_response.setContentType("text/html;charset=utf-8");
		List<bysjglxt_major> list_Student_Major = studentInformationManagementService
				.list_Student_Major(userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id());
		http_response.getWriter().write(gson.toJson(list_Student_Major));
	}

	/**
	 * 
	 * @throws Exception
	 * @说明 获取学生年级
	 */
	public void GetStudentLevel() throws Exception {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		List<String> list_Student_Level = studentInformationManagementService.listStudentLevel();
		http_response.getWriter().write(gson.toJson(list_Student_Level));
	}

	/**
	 * @throws IOException
	 * @说明 手动添加的学生
	 */
	public void CreateStudent() throws IOException {
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		studentInformationManagementService.save_NewStudent(newStudent,
				userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id());
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

	public List<String> getListGiveOperatePremissionStudentID() {
		return ListGiveOperatePremissionStudentID;
	}

	public void setListGiveOperatePremissionStudentID(List<String> listGiveOperatePremissionStudentID) {
		ListGiveOperatePremissionStudentID = listGiveOperatePremissionStudentID;
	}

	public List<String> getListTakeOperatePremissionStudentID() {
		return ListTakeOperatePremissionStudentID;
	}

	public void setListTakeOperatePremissionStudentID(List<String> listTakeOperatePremissionStudentID) {
		ListTakeOperatePremissionStudentID = listTakeOperatePremissionStudentID;
	}

	public ExportGeaduationStudentDTO getExportGeaduationStudentDTO() {
		return exportGeaduationStudentDTO;
	}

	public void setExportGeaduationStudentDTO(ExportGeaduationStudentDTO exportGeaduationStudentDTO) {
		this.exportGeaduationStudentDTO = exportGeaduationStudentDTO;
	}

	public bysjglxt_student_user getStudentUser() {
		return studentUser;
	}

	public void setStudentUser(bysjglxt_student_user studentUser) {
		this.studentUser = studentUser;
	}

	public bysjglxt_major getMajor() {
		return major;
	}

	public void setMajor(bysjglxt_major major) {
		this.major = major;
	}

}
