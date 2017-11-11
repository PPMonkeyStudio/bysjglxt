package com.bysjglxt.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DO.bysjglxt_taskbook;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.TeacherTutorStudentVO;
import com.bysjglxt.service.GraduationProjectManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GraduationProjectManagementAction extends ActionSupport
		implements ServletResponseAware, ServletRequestAware {
	/*
	 * 
	 */
	private GraduationProjectManagementService graduationProjectManagementService;
	private HttpServletResponse http_response;
	private HttpServletRequest http_request;
	/*
	 * 
	 */
	private TeacherTutorStudentVO teacherTutorStudentVO;
	private String MyTutorGraduationProjectStudentID;
	/*
	 * 
	 */
	private InputStream inputStream;
	private String fileName;
	/*
	 * 更新
	 */
	private bysjglxt_taskbook updateTaskbook;

	/*
	 * 
	 */
	public String MyGraduationProjectPage() {
		System.out.println(MyTutorGraduationProjectStudentID);
		if (MyTutorGraduationProjectStudentID != null) {
			ActionContext.getContext().getSession().put("MyTutorGraduationProjectStudentID",
					MyTutorGraduationProjectStudentID);
		} else {
			ActionContext.getContext().getSession().remove("MyTutorGraduationProjectStudentID");
		}
		return "MyGraduationProjectPage";
	}

	public String MyTutorGraduationProjectPage() {
		return "MyTutorGraduationProjectPage";
	}

	/*
	 * 所有此教师指导的学生
	 */
	public void ListMyTutorGraduationProjectByPageAndSearch() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();

		teacherTutorStudentVO = graduationProjectManagementService.teacherTutorStudentVO(teacherTutorStudentVO,
				((TeacherInformationDTO) ActionContext.getContext().getSession().get("userTeacherDTO"))
						.getBysjglxtTeacherUser().getUser_teacher_id());
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(teacherTutorStudentVO));
	}
	/*
	 * 
	 */

	public void get_Taskbook() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			http_response.getWriter().write(gson.toJson(graduationProjectManagementService.get_TaskBook(
					(String) ActionContext.getContext().getSession().get("MyTutorGraduationProjectStudentID"))));
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			http_response.getWriter()
					.write(gson.toJson(graduationProjectManagementService.get_TaskBook(
							((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
									.getBysjglxtStudentUser().getUser_student_id())));
		}

	}

	public void updateTeacherTaskbook() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService.updateTeacherTaskbook(updateTaskbook) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}

	}

	public void get_ReportOpening() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			http_response.getWriter().write(gson.toJson(graduationProjectManagementService.get_ReportOpening(
					(String) ActionContext.getContext().getSession().get("MyTutorGraduationProjectStudentID"))));
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			http_response.getWriter()
					.write(gson.toJson(graduationProjectManagementService.get_ReportOpening(
							((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
									.getBysjglxtStudentUser().getUser_student_id())));
		}
	}

	public void get_RecordProgress_1() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			http_response.getWriter().write(gson.toJson(graduationProjectManagementService.get_RecordProgress_1(
					(String) ActionContext.getContext().getSession().get("MyTutorGraduationProjectStudentID"))));
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			http_response.getWriter()
					.write(gson.toJson(graduationProjectManagementService.get_RecordProgress_1(
							((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
									.getBysjglxtStudentUser().getUser_student_id())));
		}
	}

	public void get_RecordProgress_2() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			http_response.getWriter().write(gson.toJson(graduationProjectManagementService.get_RecordProgress_2(
					(String) ActionContext.getContext().getSession().get("MyTutorGraduationProjectStudentID"))));
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			http_response.getWriter()
					.write(gson.toJson(graduationProjectManagementService.get_RecordProgress_2(
							((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
									.getBysjglxtStudentUser().getUser_student_id())));
		}
	}

	public void get_RecordProgress_3() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			http_response.getWriter().write(gson.toJson(graduationProjectManagementService.get_RecordProgress_3(
					(String) ActionContext.getContext().getSession().get("MyTutorGraduationProjectStudentID"))));
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			http_response.getWriter()
					.write(gson.toJson(graduationProjectManagementService.get_RecordProgress_3(
							((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
									.getBysjglxtStudentUser().getUser_student_id())));
		}
	}

	public void get_RecordProgress_4() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			http_response.getWriter().write(gson.toJson(graduationProjectManagementService.get_RecordProgress_4(
					(String) ActionContext.getContext().getSession().get("MyTutorGraduationProjectStudentID"))));
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			http_response.getWriter()
					.write(gson.toJson(graduationProjectManagementService.get_RecordProgress_4(
							((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
									.getBysjglxtStudentUser().getUser_student_id())));
		}
	}

	public void get_Summary() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			http_response.getWriter().write(gson.toJson(graduationProjectManagementService.get_Summary(
					(String) ActionContext.getContext().getSession().get("MyTutorGraduationProjectStudentID"))));
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			http_response.getWriter()
					.write(gson.toJson(graduationProjectManagementService.get_Summary(
							((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
									.getBysjglxtStudentUser().getUser_student_id())));
		}
	}

	public void get_ExaminationFormal() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			http_response.getWriter().write(gson.toJson(graduationProjectManagementService.get_ExaminationFormal(
					(String) ActionContext.getContext().getSession().get("MyTutorGraduationProjectStudentID"))));
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			http_response.getWriter()
					.write(gson.toJson(graduationProjectManagementService.get_ExaminationFormal(
							((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
									.getBysjglxtStudentUser().getUser_student_id())));
		}
	}

	public void get_EvaluateTutor() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			http_response.getWriter().write(gson.toJson(graduationProjectManagementService.get_EvaluateTutor(
					(String) ActionContext.getContext().getSession().get("MyTutorGraduationProjectStudentID"))));
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			http_response.getWriter()
					.write(gson.toJson(graduationProjectManagementService.get_EvaluateTutor(
							((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
									.getBysjglxtStudentUser().getUser_student_id())));
		}
	}

	public void get_EvaluateReview() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			http_response.getWriter().write(gson.toJson(graduationProjectManagementService.get_EvaluateReview(
					(String) ActionContext.getContext().getSession().get("MyTutorGraduationProjectStudentID"))));
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			http_response.getWriter()
					.write(gson.toJson(graduationProjectManagementService.get_EvaluateReview(
							((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
									.getBysjglxtStudentUser().getUser_student_id())));
		}
	}

	public void get_Defence() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			http_response.getWriter().write(gson.toJson(graduationProjectManagementService.get_Defence(
					(String) ActionContext.getContext().getSession().get("MyTutorGraduationProjectStudentID"))));
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			http_response.getWriter()
					.write(gson.toJson(graduationProjectManagementService.get_Defence(
							((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
									.getBysjglxtStudentUser().getUser_student_id())));
		}
	}

	public String exportAll() throws Exception {
		File exportFile = graduationProjectManagementService
				.exportAll(((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
						.getBysjglxtStudentUser().getUser_student_id());

		fileName = new String(
				(((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
						.getBysjglxtStudentBasic().getStudent_basic_name() + "的毕业设计.docx").getBytes("GBK"),
				"ISO-8859-1");
		inputStream = new FileInputStream(exportFile);
		exportFile.delete();
		return "exportAll";
	}

	/*
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	@Override
	public void setServletResponse(HttpServletResponse http_response) {
		this.http_response = http_response;

	}

	public void setGraduationProjectManagementService(
			GraduationProjectManagementService graduationProjectManagementService) {
		this.graduationProjectManagementService = graduationProjectManagementService;
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

	public GraduationProjectManagementService getGraduationProjectManagementService() {
		return graduationProjectManagementService;
	}

	public TeacherTutorStudentVO getTeacherTutorStudentVO() {
		return teacherTutorStudentVO;
	}

	public void setTeacherTutorStudentVO(TeacherTutorStudentVO teacherTutorStudentVO) {
		this.teacherTutorStudentVO = teacherTutorStudentVO;
	}

	public String getMyTutorGraduationProjectStudentID() {
		return MyTutorGraduationProjectStudentID;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setMyTutorGraduationProjectStudentID(String myTutorGraduationProjectStudentID) {
		MyTutorGraduationProjectStudentID = myTutorGraduationProjectStudentID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public bysjglxt_taskbook getUpdateTaskbook() {
		return updateTaskbook;
	}

	public void setUpdateTaskbook(bysjglxt_taskbook updateTaskbook) {
		this.updateTaskbook = updateTaskbook;
	}
}
