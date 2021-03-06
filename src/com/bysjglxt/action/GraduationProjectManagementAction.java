package com.bysjglxt.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.bysjglxt.domain.DO.bysjglxt_comment;
import com.bysjglxt.domain.DO.bysjglxt_defence;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_taskbook;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.CommentInformationVO;
import com.bysjglxt.domain.VO.TeacherTutorStudentVO;
import com.bysjglxt.service.GraduationProjectManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * GraduationProjectManagementAction class
 * 
 * @date 2018/2/19
 * @author JXX
 */
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

	private TeacherTutorStudentVO teacherManagementStudentVO;

	private String MyTutorGraduationProjectStudentID;

	/**
	 * 
	 * 
	 */
	/*
	 * 评语excel
	 */
	private File EXCEL_Comment;

	private String EXCEL_CommentFileName;

	private String EXCEL_CommentContentType;

	/*
	 * 
	 */
	private InputStream inputStream;
	private String fileName;
	/*
	 * 更新
	 */
	private bysjglxt_taskbook updateTaskbook;
	private bysjglxt_report_opening updateReportOpening;
	private bysjglxt_record_progress updateRecordProgressEarlystage;
	private bysjglxt_record_progress updateRecordProgressMetaphase;
	private bysjglxt_record_progress updateRecordProgressLaterstage;
	private bysjglxt_record_progress updateRecordProgressPerfect;
	private bysjglxt_summary updateSummary;
	private bysjglxt_examination_formal updateExaminationFormal;
	private bysjglxt_evaluate_tutor updateEvaluateTutor;
	private bysjglxt_evaluate_review updateEvaluateReview;
	private bysjglxt_defence updateDefence;
	private bysjglxt_comment comment;
	//
	private File dissertation;
	private String dissertationFileName;
	private String dissertationContentType;

	private String oldDissertation;

	/*
	 * 
	 */
	// 评语列表
	private List<bysjglxt_comment> listComment;
	private String DissertationUserID;
	private CommentInformationVO commentInformationVO;
	private List<String> listStringUse;
	private String StringUse;
	private String graduationComment;
	/**
	 * 删除的评语list
	 * 
	 */
	private List<String> listCommentId;

	/*
	 * 
	 */
	public String MyGraduationProjectPage() {
		if (MyTutorGraduationProjectStudentID != null) {
			ActionContext.getContext().getSession().put("MyTutorGraduationProjectStudentID",
					MyTutorGraduationProjectStudentID);
		} else {
			ActionContext.getContext().getSession().remove("MyTutorGraduationProjectStudentID");
		}
		return "MyGraduationProjectPage";
	}

	public String CollegeCommentPage() {
		return "CollegeCommentPage";
	}

	/**
	 * 生成指导老师毕业设计的评语
	 */
	public void generateTutorGraduationComment() {
		http_response.setContentType("text/html;charset=utf-8");
		try {
			http_response.getWriter()
					.write(graduationProjectManagementService.generateTutorTotalGraduationComment(updateEvaluateTutor));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成评阅老师评语
	 * 
	 */
	public void generateReviewGraduationComment() {
		http_response.setContentType("text/html;charset=utf-8");
		try {
			http_response.getWriter().write(
					graduationProjectManagementService.generateReviewTotalGraduationComment(updateEvaluateReview));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改
	 */
	public void updateComment() {
		graduationProjectManagementService.updateComment(comment);
		http_response.setContentType("text/html;charset=utf-8");
		try {
			http_response.getWriter().write("success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 批量删除
	 */
	public void deleteListComment() {
		graduationProjectManagementService.deleteListComment(listCommentId);
		http_response.setContentType("text/html;charset=utf-8");
		try {
			http_response.getWriter().write("success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 遍历评语
	 */
	public void listAllCommentCollege() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();

		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		commentInformationVO = graduationProjectManagementService.getListAllCommentInformation(commentInformationVO,
				userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id());
		http_response.setContentType("text/html;charset=utf-8");
		try {
			http_response.getWriter().write(gson.toJson(commentInformationVO));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加单条评语
	 */
	public void saveNewComment() {
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		graduationProjectManagementService.saveNewComment(comment,
				userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id());
		http_response.setContentType("text/html;charset=utf-8");
		try {
			http_response.getWriter().write("添加成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 预览评语
	 */
	public void getPreviewListComment() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		try {
			listComment = graduationProjectManagementService.previewComment(EXCEL_Comment, EXCEL_CommentFileName);
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write(gson.toJson(listComment));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 存储评语
	 */
	public void saveComment() {
		TeacherInformationDTO userTeacherDTO = (TeacherInformationDTO) ActionContext.getContext().getSession()
				.get("userTeacherDTO");
		try {
			graduationProjectManagementService.saveComment(EXCEL_Comment, EXCEL_CommentFileName,
					userTeacherDTO.getBysjglxtTeacherUser().getUser_teacher_id());
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write("导入成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 我管理的毕业设计
	 * 
	 * @return
	 */
	public String MyManagementGraduationProjectPage() {
		return "MyManagementGraduationProjectPage";
	}

	/**
	 * 我指导的毕业设计
	 * 
	 * @return
	 */
	public String MyTutorGraduationProjectPage() {
		return "MyTutorGraduationProjectPage";
	}

	/**
	 * 我评阅的毕业设计
	 * 
	 * @return
	 */
	public String MyReviewGraduationProjectPage() {
		return "MyReviewGraduationProjectPage";
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

	/**
	 * 我评阅的毕业设计
	 * 
	 * @throws IOException
	 */
	public void listMyReviewGraduationProjectByPageAndSearch() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();

		teacherTutorStudentVO = graduationProjectManagementService.teacherReviewStudentVO(teacherTutorStudentVO,
				((TeacherInformationDTO) ActionContext.getContext().getSession().get("userTeacherDTO"))
						.getBysjglxtTeacherUser().getUser_teacher_id());
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(teacherTutorStudentVO));
	}

	public void ListMyManagementGraduationProjectByPageAndSearch() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();

		teacherManagementStudentVO = graduationProjectManagementService.teacherManagementStudentVO(
				teacherManagementStudentVO,
				((TeacherInformationDTO) ActionContext.getContext().getSession().get("userTeacherDTO"))
						.getBysjglxtTeacherUser().getUser_teacher_id());
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(teacherManagementStudentVO));
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

	/**
	 * 获取
	 * 
	 * @throws IOException
	 */
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

	/**
	 * 获取defence
	 * 
	 * @throws IOException
	 */
	public void get_Defence() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter()
				.write(gson.toJson(graduationProjectManagementService.get_Defence(updateDefence.getDefence_student())));

	}

	/**
	 * 导出我的以及批量导出应该是调用同一个方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exportAll() throws Exception {
		//
		// listStringUse.add(((StudentInformationDTO)
		// ActionContext.getContext().getSession().get("userStudentDTO"))
		// .getBysjglxtStudentUser().getUser_student_id());
		//
		String[] StringUse_sz = StringUse.split(",");
		listStringUse = Arrays.asList(StringUse_sz);
		File exportFile = graduationProjectManagementService.exportAll(listStringUse);
		if (exportFile != null) {
			fileName = new String(exportFile.getName().getBytes("GBK"), "ISO-8859-1");
			inputStream = new FileInputStream(exportFile);
			exportFile.delete();
			return "exportAll";
		} else {
			return "MyManagementGraduationProjectPage";
		}

	}

	/**
	 * 保存老师完成的任务书
	 * 
	 * @throws IOException
	 */
	public void updateTeacherTaskbook() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService.updateTeacherTaskbook(updateTaskbook) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	/**
	 * 教研室主任填写任务审核意见
	 * 
	 * @throws IOException
	 */
	public void updateSectionTaskbook() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService.updateSectionTaskbook(updateTaskbook) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	/**
	 * 更新开题报告
	 * 
	 * @throws IOException
	 */
	public void updateReportOpening() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService.updateReportOpening(updateReportOpening) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateStudentRecordProgressEarlystage() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService
				.updateStudentRecordProgressEarlystage(updateRecordProgressEarlystage) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateTeacherRecordProcessEarlystage() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService
				.updateTeacherRecordProcessEarlystage(updateRecordProgressEarlystage) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateStudentRecordProgressMetaphase() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService
				.updateStudentRecordProgressMetaphase(updateRecordProgressMetaphase) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateTeacherRecordProgressMetaphase() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService
				.updateTeacherRecordProgressMetaphase(updateRecordProgressMetaphase) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateStudentRecordProgressLaterstage() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService
				.updateStudentRecordProgressLaterstage(updateRecordProgressLaterstage) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateTeacherRecordProgressLaterstage() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService
				.updateTeacherRecordProgressLaterstage(updateRecordProgressLaterstage) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateStudentRecordProgressPerfect() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService.updateStudentRecordProgressPerfect(updateRecordProgressPerfect) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateTeacherRecordProgressPerfect() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService.updateTeacherRecordProgressPerfect(updateRecordProgressPerfect) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateStudentSummary() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService.updateStudentSummary(updateSummary) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateTeacherSummary() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService.updateTeacherSummary(updateSummary) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateTeacherExaminationFormal() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService.updateTeacherExaminationFormal(updateExaminationFormal) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateLeaderExaminationFormal() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService.updateLeaderExaminationFormal(updateExaminationFormal) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateEvaluateTutor() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService.updateEvaluateTutor(updateEvaluateTutor) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateEvaluateReview() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService.updateEvaluateReview(updateEvaluateReview) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateDefenceRecorder() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService.updateDefenceRecorder(updateDefence) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	public void updateDefence() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (graduationProjectManagementService.updateDefence(updateDefence) == 1) {
			http_response.getWriter().write("保存成功");
		} else {
			http_response.getWriter().write("系统繁忙");
		}
	}

	/*
	 * 获取论文
	 */
	public void get_Dissertation() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		http_response.setContentType("text/html;charset=utf-8");
		if (ActionContext.getContext().getSession().get("userTeacherDTO") != null) {
			http_response.getWriter().write(gson.toJson(graduationProjectManagementService.get_Dissertation(
					(String) ActionContext.getContext().getSession().get("MyTutorGraduationProjectStudentID"))));
		} else if (ActionContext.getContext().getSession().get("userStudentDTO") != null) {
			http_response.getWriter()
					.write(gson.toJson(graduationProjectManagementService.get_Dissertation(
							((StudentInformationDTO) ActionContext.getContext().getSession().get("userStudentDTO"))
									.getBysjglxtStudentUser().getUser_student_id())));
		}
	}

	/**
	 * 下载毕业设计过程管理手册
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	public String downloadDissertation() throws UnsupportedEncodingException, FileNotFoundException {

		File downloadDissertation = graduationProjectManagementService.downloadDissertation(DissertationUserID);

		fileName = new String(downloadDissertation.getName().getBytes("GBK"), "ISO-8859-1");

		inputStream = new FileInputStream(downloadDissertation);

		return "downloadDissertation";
	}

	public void updateDissertation() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		http_response
				.getWriter().write(
						graduationProjectManagementService
								.saveDissertation(dissertation, oldDissertation,
										((StudentInformationDTO) ActionContext.getContext().getSession()
												.get("userStudentDTO")).getBysjglxtStudentUser().getUser_student_id(),
										dissertationFileName));

	}

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

	public String getGraduationComment() {
		return graduationComment;
	}

	public void setGraduationComment(String graduationComment) {
		this.graduationComment = graduationComment;
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

	public bysjglxt_report_opening getUpdateReportOpening() {
		return updateReportOpening;
	}

	public void setUpdateReportOpening(bysjglxt_report_opening updateReportOpening) {
		this.updateReportOpening = updateReportOpening;
	}

	public bysjglxt_summary getUpdateSummary() {
		return updateSummary;
	}

	public void setUpdateSummary(bysjglxt_summary updateSummary) {
		this.updateSummary = updateSummary;
	}

	public bysjglxt_examination_formal getUpdateExaminationFormal() {
		return updateExaminationFormal;
	}

	public void setUpdateExaminationFormal(bysjglxt_examination_formal updateExaminationFormal) {
		this.updateExaminationFormal = updateExaminationFormal;
	}

	public bysjglxt_evaluate_tutor getUpdateEvaluateTutor() {
		return updateEvaluateTutor;
	}

	public void setUpdateEvaluateTutor(bysjglxt_evaluate_tutor updateEvaluateTutor) {
		this.updateEvaluateTutor = updateEvaluateTutor;
	}

	public bysjglxt_evaluate_review getUpdateEvaluateReview() {
		return updateEvaluateReview;
	}

	public void setUpdateEvaluateReview(bysjglxt_evaluate_review updateEvaluateReview) {
		this.updateEvaluateReview = updateEvaluateReview;
	}

	public bysjglxt_record_progress getUpdateRecordProgressEarlystage() {
		return updateRecordProgressEarlystage;
	}

	public void setUpdateRecordProgressEarlystage(bysjglxt_record_progress updateRecordProgressEarlystage) {
		this.updateRecordProgressEarlystage = updateRecordProgressEarlystage;
	}

	public bysjglxt_record_progress getUpdateRecordProgressMetaphase() {
		return updateRecordProgressMetaphase;
	}

	public void setUpdateRecordProgressMetaphase(bysjglxt_record_progress updateRecordProgressMetaphase) {
		this.updateRecordProgressMetaphase = updateRecordProgressMetaphase;
	}

	public bysjglxt_record_progress getUpdateRecordProgressLaterstage() {
		return updateRecordProgressLaterstage;
	}

	public void setUpdateRecordProgressLaterstage(bysjglxt_record_progress updateRecordProgressLaterstage) {
		this.updateRecordProgressLaterstage = updateRecordProgressLaterstage;
	}

	public bysjglxt_record_progress getUpdateRecordProgressPerfect() {
		return updateRecordProgressPerfect;
	}

	public void setUpdateRecordProgressPerfect(bysjglxt_record_progress updateRecordProgressPerfect) {
		this.updateRecordProgressPerfect = updateRecordProgressPerfect;
	}

	public File getDissertation() {
		return dissertation;
	}

	public void setDissertation(File dissertation) {
		this.dissertation = dissertation;
	}

	public String getDissertationFileName() {
		return dissertationFileName;
	}

	public void setDissertationFileName(String dissertationFileName) {
		this.dissertationFileName = dissertationFileName;
	}

	public String getDissertationContentType() {
		return dissertationContentType;
	}

	public void setDissertationContentType(String dissertationContentType) {
		this.dissertationContentType = dissertationContentType;
	}

	public String getOldDissertation() {
		return oldDissertation;
	}

	public void setOldDissertation(String oldDissertation) {
		this.oldDissertation = oldDissertation;
	}

	public TeacherTutorStudentVO getTeacherManagementStudentVO() {
		return teacherManagementStudentVO;
	}

	public bysjglxt_defence getUpdateDefence() {
		return updateDefence;
	}

	public void setUpdateDefence(bysjglxt_defence updateDefence) {
		this.updateDefence = updateDefence;
	}

	public void setTeacherManagementStudentVO(TeacherTutorStudentVO teacherManagementStudentVO) {
		this.teacherManagementStudentVO = teacherManagementStudentVO;
	}

	public String getDissertationUserID() {
		return DissertationUserID;
	}

	public void setDissertationUserID(String dissertationUserID) {
		DissertationUserID = dissertationUserID;
	}

	public List<String> getListStringUse() {
		return listStringUse;
	}

	public void setListStringUse(List<String> listStringUse) {
		this.listStringUse = listStringUse;
	}

	public String getStringUse() {
		return StringUse;
	}

	public void setStringUse(String stringUse) {
		StringUse = stringUse;
	}

	public File getEXCEL_Comment() {
		return EXCEL_Comment;
	}

	public void setEXCEL_Comment(File eXCEL_Comment) {
		EXCEL_Comment = eXCEL_Comment;
	}

	public String getEXCEL_CommentFileName() {
		return EXCEL_CommentFileName;
	}

	public bysjglxt_comment getComment() {
		return comment;
	}

	public void setComment(bysjglxt_comment comment) {
		this.comment = comment;
	}

	public void setEXCEL_CommentFileName(String eXCEL_CommentFileName) {
		EXCEL_CommentFileName = eXCEL_CommentFileName;
	}

	public String getEXCEL_CommentContentType() {
		return EXCEL_CommentContentType;
	}

	public void setEXCEL_CommentContentType(String eXCEL_CommentContentType) {
		EXCEL_CommentContentType = eXCEL_CommentContentType;
	}

	public CommentInformationVO getCommentInformationVO() {
		return commentInformationVO;
	}

	public void setCommentInformationVO(CommentInformationVO commentInformationVO) {
		this.commentInformationVO = commentInformationVO;
	}

	public List<String> getListCommentId() {
		return listCommentId;
	}

	public void setListCommentId(List<String> listCommentId) {
		this.listCommentId = listCommentId;
	}

	public void setListComment(List<bysjglxt_comment> listComment) {
		this.listComment = listComment;
	}

}
