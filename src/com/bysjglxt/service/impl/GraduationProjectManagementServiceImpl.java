package com.bysjglxt.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bysjglxt.dao.GraduationProjectManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_college;
import com.bysjglxt.domain.DO.bysjglxt_comment;
import com.bysjglxt.domain.DO.bysjglxt_defence;
import com.bysjglxt.domain.DO.bysjglxt_dissertation;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;
import com.bysjglxt.domain.DO.bysjglxt_taskbook;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.DTO.ProcessBelongDTO;
import com.bysjglxt.domain.DTO.ReviewTutorTeacherInfoDTO;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TaskDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.DTO.TeacherTutorStudentDTO;
import com.bysjglxt.domain.VO.CommentInformationVO;
import com.bysjglxt.domain.VO.TeacherTutorStudentVO;
import com.bysjglxt.service.GraduationProjectManagementService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import util.ExcelToBean2;
import util.TeamUtil;

public class GraduationProjectManagementServiceImpl implements GraduationProjectManagementService {

	private GraduationProjectManagementDao graduationProjectManagementDao;

	public void setGraduationProjectManagementDao(GraduationProjectManagementDao graduationProjectManagementDao) {
		this.graduationProjectManagementDao = graduationProjectManagementDao;
	}
	
	/**
	 * 我指导的学生数量
	 */
	@Override
	public String getGraduationTutorCount(String user_teacher_id,int state) {
		return graduationProjectManagementDao.getGraduationTutorCount(user_teacher_id,state);
	}

	
	/**
	 * 获取我管理的学生数量
	 */
	@Override
	public int getGraduationManageCount(String user_teacher_id) {
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_teacher_user = graduationProjectManagementDao.getTeacherUserByUserId(user_teacher_id);
		bysjglxt_section bysjglxt_section = new bysjglxt_section();
		String actor = "";
		if (bysjglxt_teacher_user != null) {
			if (bysjglxt_teacher_user.getUser_teacher_is_college_admin() == 1) {
				actor = "系部管理员";
			} else {
				// 根据教研室主任userId获取教研室
				bysjglxt_section = graduationProjectManagementDao.getSectionByUserId(user_teacher_id);
				if (bysjglxt_section != null) {
					actor = "教研室主任";
				}else {
					return 0;
				}
			}
		} else {
			return 0;
		}
		//获取该系部有效的学生数量
		
		
		
		//获取该教研室有效的学生数量
		
		
		return 0;
	}
	/**
	 * 批量分配
	 */
	@Override
	public List<ReviewTutorTeacherInfoDTO> piliangAssign(String sectionId, int studentNum, int teacherNum) {
		List<ReviewTutorTeacherInfoDTO> list = new ArrayList<>();
		ReviewTutorTeacherInfoDTO reviewTutorTeacherInfoDTO = new ReviewTutorTeacherInfoDTO();
		List<TeacherInformationDTO> listTeacherInformationDTO = graduationProjectManagementDao.getTeacherInformationDTO(sectionId,teacherNum);
		//该教研室未分配评阅老师的学生数量
		int studentCount = graduationProjectManagementDao.getStudentCountNotAssignedTeacher(sectionId,teacherNum);
		//保证分配的学生数量不会出错
		if(studentCount < studentNum) {
			studentNum = studentCount;
		}
		if(listTeacherInformationDTO==null || listTeacherInformationDTO.size()<=0)
			return null;
		//分配的数量
		int pinjun = studentNum / (listTeacherInformationDTO.size());
		int ewai = studentNum % (listTeacherInformationDTO.size());
		List<bysjglxt_student_user> listStudentUser = new ArrayList<>();
		for (TeacherInformationDTO teacherInformationDTO : listTeacherInformationDTO) {
			listStudentUser = new ArrayList<>();
			int assignStudentCount = pinjun;
			//获取可分配的学生
			if(ewai > 0) {
				assignStudentCount++;
				//调用分配方法需要studentUserId 和 评阅老师teacherUserId
			}
			listStudentUser = graduationProjectManagementDao.getStudentNotAssignedTeacher(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id(),sectionId,assignStudentCount);
			for (bysjglxt_student_user bysjglxt_student_user : listStudentUser) {
				reviewTutorTeacherInfoDTO = new ReviewTutorTeacherInfoDTO();
				bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
				TeacherInformationDTO tutorInformationDTO = new TeacherInformationDTO();
				StudentInformationDTO studentInformationDTO = new StudentInformationDTO();
				if(assignment(bysjglxt_student_user.getUser_student_id(), teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id()) == 1) {
					reviewTutorTeacherInfoDTO.setReviewTeacherInformationDTO(teacherInformationDTO);
					// 根据选题Id获取选题表信息
					bysjglxt_topic_select = graduationProjectManagementDao.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
					//根据学生UserId获取指导老师信息
					tutorInformationDTO = graduationProjectManagementDao.getTeacherInfomationDTOByTeacherUserId(bysjglxt_topic_select.getTopic_select_teacher_tutor());
					reviewTutorTeacherInfoDTO.setTutorTeacherInformationDTO(tutorInformationDTO);
					studentInformationDTO = graduationProjectManagementDao.getStudentInfoByUserId(bysjglxt_student_user.getUser_student_id());
					reviewTutorTeacherInfoDTO.setStudentInformationDTO(studentInformationDTO);
					//获取学生信息
					list.add(reviewTutorTeacherInfoDTO);
				}
			}
			ewai--;
		}
		return list;
	}
	public int assignment(String studentUserId, String reviewId) {
		boolean flag = false;
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_task_instance bysjglxt_task_instance = new bysjglxt_task_instance();
		bysjglxt_task_definition bysjglxt_task_definition = new bysjglxt_task_definition();
		bysjglxt_process_instance bysjglxt_process_instance = new bysjglxt_process_instance();
		// 根据选题Id获取选题表信息
		bysjglxt_topic_select = graduationProjectManagementDao.getStudentSelectTopic(studentUserId);
		if (bysjglxt_topic_select == null)
			return -1;
		bysjglxt_topic_select.setTopic_select_teacher_review(reviewId);
		bysjglxt_topic_select.setTopic_select_gmt_modified(TeamUtil.getStringSecond());
		if (!(graduationProjectManagementDao.saveObj(bysjglxt_topic_select)==1))
			return -1;
		// 判断该学生是否开启毕业设计流程
		// 用man.state.bysjglxt_process_definitionName 得到该学生流程实例表
		bysjglxt_process_instance = graduationProjectManagementDao
				.getProcessInstanceByManStatePAndName(bysjglxt_topic_select.getTopic_select_student());
		if (bysjglxt_process_instance != null) {
			// 根据任务定义名获取任务定义表
			bysjglxt_task_definition = graduationProjectManagementDao.getTaskDefinitionByName("评阅老师填写评阅审查表");
			if (bysjglxt_task_definition != null) {
				// 根据流程实例Id以及任务定义ID可以获取任务实例表
				bysjglxt_task_instance = graduationProjectManagementDao.getTaskInstanceByNameAndProcessInstanceId(
						bysjglxt_task_definition.getTask_definition_id(),
						bysjglxt_process_instance.getProcess_instance_id());
				if (bysjglxt_task_instance != null) {
					bysjglxt_task_instance
							.setTask_instance_role(bysjglxt_topic_select.getTopic_select_teacher_review());
					graduationProjectManagementDao.saveObj(bysjglxt_task_instance);
				}
			}
		}

		return 1;
	}

	/**
	 * 更改状态
	 */
	@Override
	public int updateTaskInstaceState(String studentUserId, int state) {
		return graduationProjectManagementDao.updateTaskInstaceState(studentUserId, state);
	}

	/**
	 * 获取学生的流程
	 */
	@Override
	public List<TaskDTO> getStudentGraduationProcess(String studentUserId) {
		return graduationProjectManagementDao.getStudentGraduationProcess(studentUserId);
	}

	@Override
	public bysjglxt_task_instance getTaskInstance(String taskName, String userId) {
		return graduationProjectManagementDao.getTaskInstance(taskName, userId);
	}

	/**
	 * 组合评阅老师评语
	 */
	@Override
	public String generateReviewTotalGraduationComment(bysjglxt_evaluate_review bysjglxt_evaluate_review) {
		String comment = "        ";
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		// 根据所属学生获取学生basic表
		if (bysjglxt_evaluate_review.getEvaluate_review_student() != null
				&& bysjglxt_evaluate_review.getEvaluate_review_student().trim().length() > 0) {
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByUserId(bysjglxt_evaluate_review.getEvaluate_review_student().trim());
			comment = comment + bysjglxt_student_basic.getStudent_basic_name();
		}
		// 1.引言
		comment = comment
				+ generateGraduationComment("引言", bysjglxt_evaluate_review.getEvaluate_review_grade_total(), 100)
				+ "\n";
		// 2.工作态度
		comment = comment
				+ generateGraduationComment("工作态度", bysjglxt_evaluate_review.getEvaluate_review_grade_total(), 100)
				+ "\n";
		// 3.选题质量
		comment = comment + generateGraduationComment("选题质量",
				bysjglxt_evaluate_review.getEvaluate_review_grade_training_objective(), 6) + "\n";
		// 4.题目难易度
		comment = comment
				+ generateGraduationComment("题目难易度", bysjglxt_evaluate_review.getEvaluate_review_grade_difficulty(), 4)
				+ "\n";
		// 5.题目工作量
		comment = comment
				+ generateGraduationComment("题目工作量", bysjglxt_evaluate_review.getEvaluate_review_grade_workload(), 5)
				+ "\n";
		// 6.题目与生产、科研、实验室建设等实际的结合程度
		comment = comment + generateGraduationComment("题目与生产、科研、实验室建设等实际的结合程度",
				bysjglxt_evaluate_review.getEvaluate_review_grade_bind(), 5) + "\n";
		// 7.综合运用知识
		comment = comment + generateGraduationComment("综合运用知识",
				bysjglxt_evaluate_review.getEvaluate_review_grade_comprehensive(), 8) + "\n";
		// 查阅文献资料及资料应用
		comment = comment + generateGraduationComment("查阅文献资料及资料应用",
				bysjglxt_evaluate_review.getEvaluate_review_grade_reference(), 7) + "\n";
		// 实验设计
		comment = comment + generateGraduationComment("实验设计",
				bysjglxt_evaluate_review.getEvaluate_review_grade_experimental_design(), 7) + "\n";
		// 计算能力
		comment = comment
				+ generateGraduationComment("计算能力", bysjglxt_evaluate_review.getEvaluate_review_grade_computing(), 6)
				+ "\n";
		// 外语应用
		comment = comment + generateGraduationComment("外语应用",
				bysjglxt_evaluate_review.getEvaluate_review_grade_foreign_language(), 6) + "\n";
		// 计算机应用
		comment = comment
				+ generateGraduationComment("计算机应用", bysjglxt_evaluate_review.getEvaluate_review_grade_computer(), 6)
				+ "\n";
		// 创新
		comment = comment
				+ generateGraduationComment("创新", bysjglxt_evaluate_review.getEvaluate_review_grade_innovate(), 7)
				+ "\n";
		// 对实验结果的分析能力
		comment = comment + generateGraduationComment("对实验结果的分析能力",
				bysjglxt_evaluate_review.getEvaluate_review_grade_analysis(), 7) + "\n";
		// 插图（或图纸）质量
		comment = comment
				+ generateGraduationComment("插图（或图纸）质量", bysjglxt_evaluate_review.getEvaluate_review_grade_chart(), 6)
				+ "\n";
		// 设计的实用性与科学性
		comment = comment + generateGraduationComment("设计的实用性与科学性",
				bysjglxt_evaluate_review.getEvaluate_review_grade_practicability(), 6) + "\n";
		// 设计规范化程度
		comment = comment + generateGraduationComment("设计规范化程度",
				bysjglxt_evaluate_review.getEvaluate_review_grade_normalization(), 7) + "\n";
		// 设计说明书撰写水平
		comment = comment + generateGraduationComment("设计说明书撰写水平",
				bysjglxt_evaluate_review.getEvaluate_review_grade_instructions(), 7) + "\n";
		// 总结
		comment = comment
				+ generateGraduationComment("总结", bysjglxt_evaluate_review.getEvaluate_review_grade_total(), 100);
		return comment;
	}

	/**
	 * 组合指导老师评语
	 * 
	 * @param evaluateTutor
	 * @return
	 */
	@Override
	public String generateTutorTotalGraduationComment(bysjglxt_evaluate_tutor evaluateTutor) {
		String comment = "";
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		// 根据所属学生获取学生basic表
		if (evaluateTutor.getEvaluate_tutor_student() != null
				&& evaluateTutor.getEvaluate_tutor_student().trim().length() > 0) {
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByUserId(evaluateTutor.getEvaluate_tutor_student());
			comment = comment + bysjglxt_student_basic.getStudent_basic_name();
		}
		// 1.引言
		comment = comment + generateGraduationComment("引言", evaluateTutor.getEvaluate_tutor_grade_total(), 100) + "\n";
		// 2.工作态度
		comment = comment + generateGraduationComment("工作态度", evaluateTutor.getEvaluate_tutor_grade_total(), 100)
				+ "\n";
		// 3.选题质量
		comment = comment
				+ generateGraduationComment("选题质量", evaluateTutor.getEvaluate_tutor_grade_training_objective(), 6)
				+ "\n";
		// 4.题目难易度
		comment = comment + generateGraduationComment("题目难易度", evaluateTutor.getEvaluate_tutor_grade_difficulty(), 4)
				+ "\n";
		// 5.题目工作量
		comment = comment + generateGraduationComment("题目工作量", evaluateTutor.getEvaluate_tutor_grade_workload(), 5)
				+ "\n";
		// 6.题目与生产、科研、实验室建设等实际的结合程度
		comment = comment
				+ generateGraduationComment("题目与生产、科研、实验室建设等实际的结合程度", evaluateTutor.getEvaluate_tutor_grade_bind(), 5)
				+ "\n";
		// 7.综合运用知识
		comment = comment
				+ generateGraduationComment("综合运用知识", evaluateTutor.getEvaluate_tutor_grade_comprehensive(), 8) + "\n";
		// 查阅文献资料及资料应用
		comment = comment
				+ generateGraduationComment("查阅文献资料及资料应用", evaluateTutor.getEvaluate_tutor_grade_reference(), 7) + "\n";
		// 实验设计
		comment = comment
				+ generateGraduationComment("实验设计", evaluateTutor.getEvaluate_tutor_grade_experimental_design(), 7)
				+ "\n";
		// 计算能力
		comment = comment + generateGraduationComment("计算能力", evaluateTutor.getEvaluate_tutor_grade_computing(), 6)
				+ "\n";
		// 外语应用
		comment = comment
				+ generateGraduationComment("外语应用", evaluateTutor.getEvaluate_tutor_grade_foreign_language(), 6) + "\n";
		// 计算机应用
		comment = comment + generateGraduationComment("计算机应用", evaluateTutor.getEvaluate_tutor_grade_computer(), 6)
				+ "\n";
		// 创新
		comment = comment + generateGraduationComment("创新", evaluateTutor.getEvaluate_tutor_grade_innovate(), 7) + "\n";
		// 对实验结果的分析能力
		comment = comment + generateGraduationComment("对实验结果的分析能力", evaluateTutor.getEvaluate_tutor_grade_analysis(), 7)
				+ "\n";
		// 插图（或图纸）质量
		comment = comment + generateGraduationComment("插图（或图纸）质量", evaluateTutor.getEvaluate_tutor_grade_chart(), 6)
				+ "\n";
		// 设计的实用性与科学性
		comment = comment
				+ generateGraduationComment("设计的实用性与科学性", evaluateTutor.getEvaluate_tutor_grade_practicability(), 6)
				+ "\n";
		// 设计规范化程度
		comment = comment
				+ generateGraduationComment("设计规范化程度", evaluateTutor.getEvaluate_tutor_grade_normalization(), 7) + "\n";
		// 设计说明书撰写水平
		comment = comment
				+ generateGraduationComment("设计说明书撰写水平", evaluateTutor.getEvaluate_tutor_grade_instructions(), 7)
				+ "\n";
		// 总结
		comment = comment + generateGraduationComment("总结", evaluateTutor.getEvaluate_tutor_grade_total(), 100);
		return comment;
	}

	/**
	 * 单点评语
	 */
	@Override
	public String generateGraduationComment(String commentCategory, int getGrade, int totalGrade) {
		List<bysjglxt_comment> listComment = new ArrayList<>();
		bysjglxt_comment bysjglxt_comment = new bysjglxt_comment();
		// 1.根据分数判断等级
		String grade = TeamUtil.grade(totalGrade, getGrade);
		// 获取该类别中所有这个等级的评语
		listComment = graduationProjectManagementDao.getListCommentByGradeAndCategory(commentCategory, grade);
		// 根据listsize获取某一条
		if (listComment.size() > 0) {
			Random rand = new Random();
			bysjglxt_comment = listComment.get(rand.nextInt(listComment.size()));
			return bysjglxt_comment.getComment_content();
		} else {
			return null;
		}

	}

	/**
	 * 更改评语
	 */
	@Override
	public void updateComment(bysjglxt_comment comment) {
		bysjglxt_comment bysjglxt_comment = new bysjglxt_comment();
		bysjglxt_comment = graduationProjectManagementDao.getCommentById(comment.getComment_id());
		bysjglxt_comment.setComment_category(comment.getComment_category());
		bysjglxt_comment.setComment_content(comment.getComment_content());
		bysjglxt_comment.setComment_grade(comment.getComment_grade());
		bysjglxt_comment.setComment_gmt_modified(TeamUtil.getStringSecond());
		graduationProjectManagementDao.saveObj(bysjglxt_comment);
	}

	/**
	 * 删除评语
	 * 
	 */
	@Override
	public void deleteListComment(List<String> listCommentId) {
		bysjglxt_comment bysjglxt_comment = null;
		for (String comment_id : listCommentId) {
			bysjglxt_comment = new bysjglxt_comment();
			bysjglxt_comment = graduationProjectManagementDao.getCommentById(comment_id);
			if (bysjglxt_comment != null) {
				graduationProjectManagementDao.deleteCommentById(comment_id);
			}
		}
	}

	// 遍历评语
	@Override
	public CommentInformationVO getListAllCommentInformation(CommentInformationVO commentInformationVO, String userId) {
		String college = getCollegeByUserId(userId);
		List<bysjglxt_comment> listAllCommentInformation = new ArrayList<>();
		// 获取记录数
		// 这里的1 代表的是获取数量
		listAllCommentInformation = graduationProjectManagementDao.getListAllCommentInformation(commentInformationVO,
				college, 1);
		int i = listAllCommentInformation.size();
		commentInformationVO.setTotalRecords(i);
		commentInformationVO.setTotalPages(((i - 1) / commentInformationVO.getPageSize()) + 1);
		if (commentInformationVO.getPageIndex() <= 1) {
			commentInformationVO.setHavePrePage(false);
		} else {
			commentInformationVO.setHavePrePage(true);
		}
		if (commentInformationVO.getPageIndex() >= commentInformationVO.getTotalPages()) {
			commentInformationVO.setHaveNextPage(false);
		} else {
			commentInformationVO.setHaveNextPage(true);
		}
		// 内容
		listAllCommentInformation = graduationProjectManagementDao.getListAllCommentInformation(commentInformationVO,
				college, 2);
		commentInformationVO.setListComment(listAllCommentInformation);
		return commentInformationVO;
	}

	// 添加单条评语
	@Override
	public void saveNewComment(bysjglxt_comment comment, String user_teacher_id) {
		String college = getCollegeByUserId(user_teacher_id);
		comment.setComment_id(TeamUtil.getUuid());
		comment.setComment_college(college);
		comment.setComment_gmt_create(TeamUtil.getStringSecond());
		comment.setComment_gmt_modified(comment.getComment_gmt_create());
		graduationProjectManagementDao.saveObj(comment);
	}

	// 评语预览
	@Override
	public List<bysjglxt_comment> previewComment(File commentExcel, String EXCEL_CommentFileName) throws Exception {
		String houzhui = EXCEL_CommentFileName.substring(EXCEL_CommentFileName.lastIndexOf(".") + 1);
		FileInputStream input = new FileInputStream(commentExcel);
		List<Map<String, Object>> list = null;
		if ("xlsx".equals(houzhui)) {
			XSSFWorkbook workbook = new XSSFWorkbook(input);
			list = ExcelToBean2.parseUpdateExcel(workbook, "bysjglxt_comment");
		} else {
			HSSFWorkbook workbook = new HSSFWorkbook(input);
			list = ExcelToBean2.parseExcel(workbook, "bysjglxt_comment");
		}
		List<bysjglxt_comment> lists = ExcelToBean2.toObjectPerproList(list, bysjglxt_comment.class);
		return lists;
	}

	// 存储评语
	@Override
	public int saveComment(File commentExcel, String EXCEL_CommentFileName, String userId) throws Exception {
		String college = getCollegeByUserId(userId);
		String houzhui = EXCEL_CommentFileName.substring(EXCEL_CommentFileName.lastIndexOf(".") + 1);
		FileInputStream input = new FileInputStream(commentExcel);
		List<Map<String, Object>> list = null;
		if ("xlsx".equals(houzhui)) {
			XSSFWorkbook workbook = new XSSFWorkbook(input);
			list = ExcelToBean2.parseUpdateExcel(workbook, "bysjglxt_comment");
		} else {
			HSSFWorkbook workbook = new HSSFWorkbook(input);
			list = ExcelToBean2.parseExcel(workbook, "bysjglxt_comment");
		}
		List<bysjglxt_comment> lists = ExcelToBean2.toObjectPerproList(list, bysjglxt_comment.class);
		for (bysjglxt_comment comment : lists) {
			comment.setComment_id(TeamUtil.getUuid());
			comment.setComment_college(college);
			comment.setComment_gmt_create(TeamUtil.getStringSecond());
			comment.setComment_gmt_modified(comment.getComment_gmt_create());
			graduationProjectManagementDao.saveObj(comment);
		}
		return 1;
	}

	// 根据使用者Id获取学院
	public String getCollegeByUserId(String userId) {
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_teacher_user = graduationProjectManagementDao.getTeacherUserByUserId(userId);
		if (bysjglxt_teacher_user.getUser_teacher_belong_college() != null
				&& bysjglxt_teacher_user.getUser_teacher_belong_college().trim().length() >= 0) {
			return bysjglxt_teacher_user.getUser_teacher_belong_college().trim();
		}
		return null;
	}

	/**
	 * 保存完善的任务书
	 * 
	 * @throws IOException
	 */
	@Override
	public int saveWanTaskbook(File file, String oldFileName, String userId, String newFileName) throws IOException {
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		studentUser = graduationProjectManagementDao.getStudentUserByUserId(userId);
		if(studentUser==null) {
			return 0;
		}
		//获取学院信息
		bysjglxt_college college = new bysjglxt_college();
		college = graduationProjectManagementDao.getCollegeById(studentUser.getUser_student_belong_college());
		//获取课题最大的year
		String year = graduationProjectManagementDao.getMaxTopicYear();
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag = false;
		String path = "";
		bysjglxt_taskbook taskBook = new bysjglxt_taskbook();
		taskBook = graduationProjectManagementDao.getTaskBookByUserId(userId);
		// 如果新文件为空
		if (file == null) {
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/完善任务书/";
			// 判断旧文件是否处于空的状态
			if ("".equals(oldFileName) || !(oldFileName.trim().length() > 0)) {
				// 如果是空
				// 判断：在数据库中属于这个学生的开题报告是否存在
				if (taskBook != null && taskBook.getTaskbook_wan_file() != null
						&& taskBook.getTaskbook_wan_file().trim().length() > 0) {
					// 如果存在,则将原有毕业论文删除
					// 先进行删除
					// 删除学生上传的文件
					path = path + taskBook.getTaskbook_wan_file();
					File deleteFile = new File(path);
					deleteFile.delete();
					// flag = graduationProjectManagementDao.deleteWanTaskBookFileByUserId(userId);
					taskBook.setTaskbook_wan_file(null);
					taskBook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
					taskBook.setTaskbook_wan_file_xiazai(-1);
					graduationProjectManagementDao.saveObj(taskBook);
					return 1;
				} else {
					// 如果不存在,不进行任何操作
					return 1;
				}
			} else {
				// 如果不是空
				// 不进行操作
				return 1;
			}
		} else {
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/完善任务书/";
			// 如果新文件存在
			// 1.判断是否有属于这个学生的毕业论文存在
			if (taskBook != null && taskBook.getTaskbook_wan_file() != null
					&& taskBook.getTaskbook_wan_file().trim().length() > 0) {
				// 如果存在
				// 先进行删除
				// 删除学生上传的文件
				path = path + taskBook.getTaskbook_wan_file();
				File deleteFile = new File(path);
				deleteFile.delete();
				// 删除学生毕业论文记录
				flag = graduationProjectManagementDao.deleteWanTaskBookFileByUserId(userId);
				if (!flag) {
					return -1;
				}
			}
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/完善任务书/";
			// 保存毕业论文
			// 1.上传毕业论文
			if (taskBook != null) {
				String fileNameP = studentUser.getUser_student_num()+"WR."+newFileName.substring(newFileName.lastIndexOf(".")+1);
				path = path  + fileNameP;
				File newFile = new File(path);
				FileUtils.copyFile(file, newFile);
				// 存储数据到数据库
				taskBook.setTaskbook_wan_file(fileNameP);
				taskBook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
				taskBook.setTaskbook_wan_file_xiazai(-1);
				flag = graduationProjectManagementDao.saveObj(taskBook) == 1 ? true : false;
				if (!flag)
					return -2;
			} else {
				return -2;
			}
		}
		return 1;
	}
	//
	@Override
	public int updateShenTaskbook(File file, String oldFileName, String userId,String newFileName) throws IOException {
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		studentUser = graduationProjectManagementDao.getStudentUserByUserId(userId);
		if(studentUser==null) {
			return 0;
		}
		//获取学院信息
		bysjglxt_college college = new bysjglxt_college();
		college = graduationProjectManagementDao.getCollegeById(studentUser.getUser_student_belong_college());
		//获取课题最大的year
		String year = graduationProjectManagementDao.getMaxTopicYear();
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag = false;
		String path = "";
		bysjglxt_taskbook taskBook = new bysjglxt_taskbook();
		taskBook = graduationProjectManagementDao.getTaskBookByUserId(userId);
		// 如果新文件为空
				if (file == null) {
					path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/指导老师修改任务书/";
					// 判断旧文件是否处于空的状态
					if ("".equals(oldFileName) || !(oldFileName.trim().length() > 0)) {
						// 如果是空
						// 判断：在数据库中属于这个学生的开题报告是否存在
						if (taskBook != null && taskBook.getTaskbook_shen_file() != null
								&& taskBook.getTaskbook_shen_file().trim().length() > 0) {
							// 如果存在,则将原有毕业论文删除
							// 先进行删除
							// 删除学生上传的文件
							path = path + taskBook.getTaskbook_shen_file();
							File deleteFile = new File(path);
							deleteFile.delete();
							// flag = graduationProjectManagementDao.deleteXiaTaskBookFileByUserId(userId);
							taskBook.setTaskbook_shen_file(null);
							taskBook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
							taskBook.setTaskbook_shen_file_xiazai(-1);
							graduationProjectManagementDao.saveObj(taskBook);
							return 1;
						} else {
							// 如果不存在,不进行任何操作
							return 1;
						}
					} else {
						// 如果不是空
						// 不进行操作
						return 1;
					}
				} else {
					path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/指导老师修改任务书/";
					// 如果新文件存在
					// 1.判断是否有属于这个学生的毕业论文存在
					if (taskBook != null && taskBook.getTaskbook_shen_file() != null
							&& taskBook.getTaskbook_shen_file().trim().length() > 0) {
						// 如果存在
						// 先进行删除
						// 删除学生上传的文件
						path = path + taskBook.getTaskbook_shen_file();
						File deleteFile = new File(path);
						deleteFile.delete();
						// 删除学生毕业论文记录
						flag = graduationProjectManagementDao.deleteShenTaskBookFileByUserId(userId);
						if (!flag) {
							return -1;
						}
					}
					
					path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/指导老师修改任务书/";
					// 保存毕业论文
					// 1.上传毕业论文 //+ taskBook.getTaskbook_id() + "_"
					if (taskBook != null) {
						String fileNameP = studentUser.getUser_student_num()+"XGR."+newFileName.substring(newFileName.lastIndexOf(".")+1);
						path = path  + fileNameP;
						File newFile = new File(path);
						FileUtils.copyFile(file, newFile);
						// 存储数据到数据库
						taskBook.setTaskbook_shen_file(fileNameP);
						taskBook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
						taskBook.setTaskbook_shen_file_xiazai(-1);
						flag = graduationProjectManagementDao.saveObj(taskBook) == 1 ? true : false;
						if (!flag)
							return -2;
					} else {
						return -2;
					}
				}
		return 1;
	}
	// 保存任务书
	@Override
	public int saveXiaTaskbook(File file, String oldFileName, String userId, String newFileName) throws IOException {
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		studentUser = graduationProjectManagementDao.getStudentUserByUserId(userId);
		if(studentUser==null) {
			return 0;
		}
		//获取学院信息
		bysjglxt_college college = new bysjglxt_college();
		college = graduationProjectManagementDao.getCollegeById(studentUser.getUser_student_belong_college());
		//获取课题最大的year
		String year = graduationProjectManagementDao.getMaxTopicYear();
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag = false;
		String path = "";
		bysjglxt_taskbook taskBook = new bysjglxt_taskbook();
		taskBook = graduationProjectManagementDao.getTaskBookByUserId(userId);
		// 如果新文件为空
		if (file == null) {
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/下发任务书/";
			// 判断旧文件是否处于空的状态
			if ("".equals(oldFileName) || !(oldFileName.trim().length() > 0)) {
				// 如果是空
				// 判断：在数据库中属于这个学生的开题报告是否存在
				if (taskBook != null && taskBook.getTaskbook_xia_file() != null
						&& taskBook.getTaskbook_xia_file().trim().length() > 0) {
					// 如果存在,则将原有毕业论文删除
					// 先进行删除
					// 删除学生上传的文件
					path = path + taskBook.getTaskbook_xia_file();
					File deleteFile = new File(path);
					deleteFile.delete();
					// flag = graduationProjectManagementDao.deleteXiaTaskBookFileByUserId(userId);
					taskBook.setTaskbook_xia_file(null);
					taskBook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
					taskBook.setTaskbook_xia_file_xiazai(-1);
					graduationProjectManagementDao.saveObj(taskBook);
					return 1;
				} else {
					// 如果不存在,不进行任何操作
					return 1;
				}
			} else {
				// 如果不是空
				// 不进行操作
				return 1;
			}
		} else {
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/下发任务书/";
			// 如果新文件存在
			// 1.判断是否有属于这个学生的毕业论文存在
			if (taskBook != null && taskBook.getTaskbook_xia_file() != null
					&& taskBook.getTaskbook_xia_file().trim().length() > 0) {
				// 如果存在
				// 先进行删除
				// 删除学生上传的文件
				path = path + taskBook.getTaskbook_xia_file();
				File deleteFile = new File(path);
				deleteFile.delete();
				// 删除学生毕业论文记录
				flag = graduationProjectManagementDao.deleteXiaTaskBookFileByUserId(userId);
				if (!flag) {
					return -1;
				}
			}
			
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/下发任务书/";
			// 保存毕业论文
			// 1.上传毕业论文 //+ taskBook.getTaskbook_id() + "_"
			if (taskBook != null) {
				String fileNameP = studentUser.getUser_student_num()+"XR."+newFileName.substring(newFileName.lastIndexOf(".")+1);
				path = path  + fileNameP;
				File newFile = new File(path);
				FileUtils.copyFile(file, newFile);
				// 存储数据到数据库
				taskBook.setTaskbook_xia_file(fileNameP);
				taskBook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
				taskBook.setTaskbook_xia_file_xiazai(-1);
				flag = graduationProjectManagementDao.saveObj(taskBook) == 1 ? true : false;
				if (!flag)
					return -2;
			} else {
				return -2;
			}
		}
		return 1;
	}
	
	
	public int saveRecordProgress(File file, String oldFileName, String userId, String newFileName,bysjglxt_record_progress record_progress,int state) throws IOException {
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		studentUser = graduationProjectManagementDao.getStudentUserByUserId(userId);
		if(studentUser==null) {
			return 0;
		}
		//获取学院信息
		bysjglxt_college college = new bysjglxt_college();
		college = graduationProjectManagementDao.getCollegeById(studentUser.getUser_student_belong_college());
		//获取课题最大的year
		String year = graduationProjectManagementDao.getMaxTopicYear();
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag = false;
		String path = "";
		String weizhi = "";
		String houzhui = "";
		switch(state) {
		case 1:
			weizhi = "前期进展资料";
			houzhui = "QQZL";
			break;
		case 2:
			weizhi = "撰写进展资料";
			houzhui = "ZXZL";
			break;
		case 3:
			weizhi = "中期进展资料";
			houzhui = "ZQZL";
			break;
		case 4:
			weizhi = "后期进展资料";
			houzhui = "HQZL";
			break;
		}
		// 如果新文件为空
		if (file == null) {
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/"+weizhi+"/";
			// 判断旧文件是否处于空的状态
			if ("".equals(oldFileName) || !(oldFileName.trim().length() > 0)) {
				// 如果是空
				// 判断：在数据库中属于这个学生的开题报告是否存在
				if (record_progress != null && record_progress.getRecord_progress_student_file() != null
						&& record_progress.getRecord_progress_student_file().trim().length() > 0) {
					// 如果存在,则将原有毕业论文删除
					// 先进行删除
					// 删除学生上传的文件
					path = path + record_progress.getRecord_progress_student_file();
					File deleteFile = new File(path);
					deleteFile.delete();
					// flag = graduationProjectManagementDao.deleteXiaTaskBookFileByUserId(userId);
					record_progress.setRecord_progress_student_file(null);
					record_progress.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
					graduationProjectManagementDao.saveObj(record_progress);
					return 1;
				} else {
					// 如果不存在,不进行任何操作
					return 1;
				}
			} else {
				// 如果不是空
				// 不进行操作
				return 1;
			}
		} else {
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/"+weizhi+"/";
			// 如果新文件存在
			// 1.判断是否有属于这个学生的毕业论文存在
			if (record_progress != null && record_progress.getRecord_progress_student_file() != null
					&& record_progress.getRecord_progress_student_file().trim().length() > 0) {
				// 如果存在
				// 先进行删除
				// 删除学生上传的文件
				path = path + record_progress.getRecord_progress_student_file();
				File deleteFile = new File(path);
				deleteFile.delete();
				// 删除学生毕业论文记录
				record_progress.setRecord_progress_student_file(null);
				record_progress.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
				graduationProjectManagementDao.saveObj(record_progress);
			}
			
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/"+weizhi+"/";
			// 保存毕业论文
			// 1.上传毕业论文 //+ taskBook.getTaskbook_id() + "_"
			if (record_progress != null) {
				String fileNameP = studentUser.getUser_student_num()+houzhui+"."+newFileName.substring(newFileName.lastIndexOf(".")+1);
				path = path  + fileNameP;
				File newFile = new File(path);
				FileUtils.copyFile(file, newFile);
				// 存储数据到数据库
				record_progress.setRecord_progress_student_file(fileNameP);
				record_progress.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
				graduationProjectManagementDao.saveObj(record_progress);
				flag = graduationProjectManagementDao.saveObj(record_progress) == 1 ? true : false;
				if (!flag)
					return -2;
			} else {
				return -2;
			}
		}
		return 1;
	}
	
	/**
	 * 保存老师的开题报告
	 * @throws IOException 
	 */
	@Override
	public int saveTeacherReportOpening(File file, String oldFileName, String userId, String newFileName) throws IOException {
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		studentUser = graduationProjectManagementDao.getStudentUserByUserId(userId);
		if(studentUser==null) {
			return 0;
		}
		//获取学院信息
		bysjglxt_college college = new bysjglxt_college();
		college = graduationProjectManagementDao.getCollegeById(studentUser.getUser_student_belong_college());
		//获取课题最大的year
		String year = graduationProjectManagementDao.getMaxTopicYear();
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag = false;
		String path = "";
		bysjglxt_report_opening reportOpening = new bysjglxt_report_opening();
		reportOpening = graduationProjectManagementDao.getReportOpeningUser(userId);
		// 如果新文件为空
		if (file == null) {
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/教师开题报告/";
			// 判断旧文件是否处于空的状态
			if ("".equals(oldFileName) || !(oldFileName.trim().length() > 0)) {
				// 如果是空
				// 判断：在数据库中属于这个学生的开题报告是否存在
				if (reportOpening != null && reportOpening.getReport_opening_teacher_file() != null
						&& reportOpening.getReport_opening_teacher_file().trim().length() > 0) {
					// 如果存在,则将原有毕业论文删除
					// 先进行删除
					// 删除学生上传的文件
					path = path + reportOpening.getReport_opening_teacher_file();
					File deleteFile = new File(path);
					deleteFile.delete();
					// flag =
					// graduationProjectManagementDao.deleteReportOpeningFileByUserId(userId);
					reportOpening.setReport_opening_teacher_file(null);
					reportOpening.setReport_opening_teacher_file_is_xiazai(-1);
					reportOpening.setReport_opening_gmt_modified(TeamUtil.getStringSecond());
					graduationProjectManagementDao.saveObj(reportOpening);
					return 1;
				} else {
					// 如果不存在,不进行任何操作
					return 1;
				}
			} else {
				// 如果不是空
				// 不进行操作
				return 1;
			}
		} else {
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/教师开题报告/";
			// 如果新文件存在
			// 1.判断是否有属于这个学生的毕业论文存在
			if (reportOpening != null && reportOpening.getReport_opening_teacher_file() != null
					&& reportOpening.getReport_opening_teacher_file().trim().length() > 0) {
				// 如果存在
				// 先进行删除
				// 删除学生上传的文件
				path = path + reportOpening.getReport_opening_teacher_file();
				File deleteFile = new File(path);
				deleteFile.delete();
				// 删除学生毕业论文记录
				flag = graduationProjectManagementDao.deleteReportOpeningTeacherFileByUserId(userId);
				if (!flag) {
					return -1;
				}
			}
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/教师开题报告/";
			// 保存毕业论文
			// 1.上传毕业论文
			if (reportOpening != null) {
				String fileNameP = studentUser.getUser_student_num()+"JK."+newFileName.substring(newFileName.lastIndexOf(".")+1);
				path = path  + fileNameP;
//				path = path + reportOpening.getReport_opening_id() + "_" + newFileName;
				File newFile = new File(path);
				FileUtils.copyFile(file, newFile);
				// 存储数据到数据库
				reportOpening.setReport_opening_teacher_file_is_xiazai(-1);
				reportOpening.setReport_opening_teacher_file(fileNameP);
				reportOpening.setReport_opening_gmt_modified(TeamUtil.getStringSecond());
				flag = graduationProjectManagementDao.saveObj(reportOpening) == 1 ? true : false;
				if (!flag)
					return -2;
			} else {
				return -2;
			}
		}
		return 1;
	}
	// 保存开题报告
	@Override
	public int saveReportOpening(File file, String oldFileName, String userId, String newFileName) throws IOException {
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		studentUser = graduationProjectManagementDao.getStudentUserByUserId(userId);
		if(studentUser==null) {
			return 0;
		}
		//获取学院信息
		bysjglxt_college college = new bysjglxt_college();
		college = graduationProjectManagementDao.getCollegeById(studentUser.getUser_student_belong_college());
		//获取课题最大的year
		String year = graduationProjectManagementDao.getMaxTopicYear();
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag = false;
		String path = "";
		bysjglxt_report_opening reportOpening = new bysjglxt_report_opening();
		reportOpening = graduationProjectManagementDao.getReportOpeningUser(userId);
		// 如果新文件为空
		if (file == null) {
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/学生开题报告/";
			// 判断旧文件是否处于空的状态
			if ("".equals(oldFileName) || !(oldFileName.trim().length() > 0)) {
				// 如果是空
				// 判断：在数据库中属于这个学生的开题报告是否存在
				if (reportOpening != null && reportOpening.getReport_opening_file() != null
						&& reportOpening.getReport_opening_file().trim().length() > 0) {
					// 如果存在,则将原有毕业论文删除
					// 先进行删除
					// 删除学生上传的文件
					path = path + reportOpening.getReport_opening_file();
					File deleteFile = new File(path);
					deleteFile.delete();
					// flag =
					// graduationProjectManagementDao.deleteReportOpeningFileByUserId(userId);
					reportOpening.setReport_opening_file(null);
					reportOpening.setReport_file_is_xiazai(-1);
					reportOpening.setReport_opening_gmt_modified(TeamUtil.getStringSecond());
					graduationProjectManagementDao.saveObj(reportOpening);
					return 1;
				} else {
					// 如果不存在,不进行任何操作
					return 1;
				}
			} else {
				// 如果不是空
				// 不进行操作
				return 1;
			}
		} else {
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/学生开题报告/";
			// 如果新文件存在
			// 1.判断是否有属于这个学生的毕业论文存在
			if (reportOpening != null && reportOpening.getReport_opening_file() != null
					&& reportOpening.getReport_opening_file().trim().length() > 0) {
				// 如果存在
				// 先进行删除
				// 删除学生上传的文件
				path = path + reportOpening.getReport_opening_file();
				File deleteFile = new File(path);
				deleteFile.delete();
				// 删除学生毕业论文记录
				flag = graduationProjectManagementDao.deleteReportOpeningFileByUserId(userId);
				if (!flag) {
					return -1;
				}
			}
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/学生开题报告/";
			// 保存毕业论文
			// 1.上传毕业论文
			if (reportOpening != null) {
				String fileNameP = studentUser.getUser_student_num()+"SK."+newFileName.substring(newFileName.lastIndexOf(".")+1);
				path = path  + fileNameP;
//				path = path + reportOpening.getReport_opening_id() + "_" + newFileName;
				File newFile = new File(path);
				FileUtils.copyFile(file, newFile);
				// 存储数据到数据库
				reportOpening.setReport_file_is_xiazai(-1);
				reportOpening.setReport_opening_file(fileNameP);
				reportOpening.setReport_opening_gmt_modified(TeamUtil.getStringSecond());
				flag = graduationProjectManagementDao.saveObj(reportOpening) == 1 ? true : false;
				if (!flag)
					return -2;
			} else {
				return -2;
			}
		}
		return 1;
	}

	/**
	 * 保存毕业论文
	 * 
	 * @throws IOException
	 */
	@Override
	public int saveDissertation(File file, String oldFileName, String userId, String newFileName) throws IOException {
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		studentUser = graduationProjectManagementDao.getStudentUserByUserId(userId);
		if(studentUser==null) {
			return 0;
		}
		//获取学院信息
		bysjglxt_college college = new bysjglxt_college();
		college = graduationProjectManagementDao.getCollegeById(studentUser.getUser_student_belong_college());
		//获取课题最大的year
		String year = graduationProjectManagementDao.getMaxTopicYear();
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag = false;
		String path = "";
		bysjglxt_dissertation bysjglxt_dissertation = new bysjglxt_dissertation();
		// 如果新文件为空
		if (file == null) {
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/毕业论文/";
			// 判断旧文件是否处于空的状态
			if ("".equals(oldFileName) || !(oldFileName.trim().length() > 0)) {
				// 如果是空
				// 判断：在数据库中属于这个学生的毕业论文是否存在
				bysjglxt_dissertation = graduationProjectManagementDao.getThesisByStudent(userId);
				if (bysjglxt_dissertation != null && bysjglxt_dissertation.getDissertation_student() != null
						&& bysjglxt_dissertation.getDissertation_student().trim().length() > 0) {
					// 如果存在,则将原有毕业论文删除
					// 先进行删除
					// 删除学生上传的文件
					path = path + bysjglxt_dissertation.getDissertation_id() + "_"
							+ bysjglxt_dissertation.getDissertation_file();
					File deleteFile = new File(path);
					deleteFile.delete();
					flag = graduationProjectManagementDao.deleteThesisByUserId(userId);
					return 1;
				} else {
					// 如果不存在,不进行任何操作
					return 1;
				}
			} else {
				// 如果不是空
				// 不进行操作
				return 1;
			}
		} else {
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/毕业论文/";
			// 如果新文件存在
			bysjglxt_dissertation = graduationProjectManagementDao.getThesisByStudent(userId);
			// 1.判断是否有属于这个学生的毕业论文存在
			if (bysjglxt_dissertation != null && bysjglxt_dissertation.getDissertation_student() != null
					&& bysjglxt_dissertation.getDissertation_student().trim().length() > 0) {
				// 如果存在
				// 先进行删除
				// 删除学生上传的文件
				path = path + bysjglxt_dissertation.getDissertation_id() + "_"
						+ bysjglxt_dissertation.getDissertation_file();
				File deleteFile = new File(path);
				deleteFile.delete();
				// 删除学生毕业论文记录
				flag = graduationProjectManagementDao.deleteThesisByUserId(userId);
				if (!flag) {
					return -1;
				}
			}
			path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/毕业论文/";
			// 保存毕业论文
			// 1.上传毕业论文
			bysjglxt_dissertation = new bysjglxt_dissertation();
			bysjglxt_dissertation = graduationProjectManagementDao.getThesisByStudentId(userId);
			if (bysjglxt_dissertation != null) {
				path = path + bysjglxt_dissertation.getDissertation_id() + "_" + newFileName;
				File newFile = new File(path);
				FileUtils.copyFile(file, newFile);
				// 存储数据到数据库
				bysjglxt_dissertation.setDissertation_file(newFileName);
				bysjglxt_dissertation.setDissertation_gmt_modified(bysjglxt_dissertation.getDissertation_gmt_create());
				flag = graduationProjectManagementDao.fillEmptyThesisRecord(bysjglxt_dissertation);
				if (!flag)
					return -2;
			} else {
				return -2;
			}
		}
		return 1;
	}
	@Override
	public File downloadZhuanRecordProgress(String juese, String userID) {
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		studentUser = graduationProjectManagementDao.getStudentUserByUserId(userID);
		if(studentUser==null) {
			return null;
		}
		//获取学院信息
		bysjglxt_college college = new bysjglxt_college();
		college = graduationProjectManagementDao.getCollegeById(studentUser.getUser_student_belong_college());
		//获取课题最大的year
		String year = graduationProjectManagementDao.getMaxTopicYear();
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		bysjglxt_record_progress recordProcess = new bysjglxt_record_progress();
		recordProcess = graduationProjectManagementDao.getRecordProgress(userID, "中期");
		// 1.根据user Id获得学生毕业论文表中的记录
		String path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/撰写进展资料/";
		if (recordProcess == null) {
			return null;
		}
		path = path + recordProcess.getRecord_progress_student_file();
//		path = path + wanTaskbook.getTaskbook_shen_file();
		File file = new File(path);
		// 如果下载的人是学生
		/*if("teacher".equals(juese)) {
			return file;
		}*/
		// 获取某个学生的指导老师
		/*bysjglxt_topic_select topicSelect = new bysjglxt_topic_select();
		topicSelect = graduationProjectManagementDao.getStudentSelectTopic(userID);
		if (topicSelect != null && topicSelect.getTopic_select_teacher_tutor() != null
				&& topicSelect.getTopic_select_teacher_tutor().trim().length() > 0) {
			if ((topicSelect.getTopic_select_teacher_tutor()).equals(juese)) {
				// 更改任务书
				wanTaskbook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
				wanTaskbook.setTaskbook_shen_file_xiazai(1);
				graduationProjectManagementDao.saveObj(wanTaskbook);
			}
		}*/
		// 如果下载的人是学生
		/*if("student".equals(juese)) {
			// 更改任务书
			wanTaskbook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
			wanTaskbook.setTaskbook_shen_file_xiazai(1);
			graduationProjectManagementDao.saveObj(wanTaskbook);
		}*/
		return file;
	}
	@Override
	public File downloadQianRecordProgress(String juese, String userID) {
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		studentUser = graduationProjectManagementDao.getStudentUserByUserId(userID);
		if(studentUser==null) {
			return null;
		}
		//获取学院信息
		bysjglxt_college college = new bysjglxt_college();
		college = graduationProjectManagementDao.getCollegeById(studentUser.getUser_student_belong_college());
		//获取课题最大的year
		String year = graduationProjectManagementDao.getMaxTopicYear();
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		bysjglxt_record_progress recordProcess = new bysjglxt_record_progress();
		recordProcess = graduationProjectManagementDao.getRecordProgress(userID, "前期");
		// 1.根据user Id获得学生毕业论文表中的记录
		String path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/前期进展资料/";
		if (recordProcess == null) {
			return null;
		}
		path = path + recordProcess.getRecord_progress_student_file();
//		path = path + wanTaskbook.getTaskbook_shen_file();
		File file = new File(path);
		// 如果下载的人是学生
		/*if("teacher".equals(juese)) {
			return file;
		}*/
		// 获取某个学生的指导老师
		/*bysjglxt_topic_select topicSelect = new bysjglxt_topic_select();
		topicSelect = graduationProjectManagementDao.getStudentSelectTopic(userID);
		if (topicSelect != null && topicSelect.getTopic_select_teacher_tutor() != null
				&& topicSelect.getTopic_select_teacher_tutor().trim().length() > 0) {
			if ((topicSelect.getTopic_select_teacher_tutor()).equals(juese)) {
				// 更改任务书
				wanTaskbook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
				wanTaskbook.setTaskbook_shen_file_xiazai(1);
				graduationProjectManagementDao.saveObj(wanTaskbook);
			}
		}*/
		// 如果下载的人是学生
		/*if("student".equals(juese)) {
			// 更改任务书
			wanTaskbook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
			wanTaskbook.setTaskbook_shen_file_xiazai(1);
			graduationProjectManagementDao.saveObj(wanTaskbook);
		}*/
		return file;
	}
	@Override
	public File downloadShenTaskBookTwo(String juese, String userID) {
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		studentUser = graduationProjectManagementDao.getStudentUserByUserId(userID);
		if(studentUser==null) {
			return null;
		}
		//获取学院信息
		bysjglxt_college college = new bysjglxt_college();
		college = graduationProjectManagementDao.getCollegeById(studentUser.getUser_student_belong_college());
		//获取课题最大的year
		String year = graduationProjectManagementDao.getMaxTopicYear();
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 1.根据user Id获得学生毕业论文表中的记录
		bysjglxt_taskbook wanTaskbook = new bysjglxt_taskbook();
		String path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/指导老师修改任务书/";
		wanTaskbook = graduationProjectManagementDao.getTaskBookByUserId(userID);
		if (wanTaskbook == null) {
			return null;
		}
		path = path + wanTaskbook.getTaskbook_shen_file();
		File file = new File(path);
		// 如果下载的人是学生
		if("teacher".equals(juese)) {
			return file;
		}
		// 获取某个学生的指导老师
		/*bysjglxt_topic_select topicSelect = new bysjglxt_topic_select();
		topicSelect = graduationProjectManagementDao.getStudentSelectTopic(userID);
		if (topicSelect != null && topicSelect.getTopic_select_teacher_tutor() != null
				&& topicSelect.getTopic_select_teacher_tutor().trim().length() > 0) {
			if ((topicSelect.getTopic_select_teacher_tutor()).equals(juese)) {
				// 更改任务书
				wanTaskbook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
				wanTaskbook.setTaskbook_shen_file_xiazai(1);
				graduationProjectManagementDao.saveObj(wanTaskbook);
			}
		}*/
		// 如果下载的人是学生
		if("student".equals(juese)) {
			// 更改任务书
			wanTaskbook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
			wanTaskbook.setTaskbook_shen_file_xiazai(1);
			graduationProjectManagementDao.saveObj(wanTaskbook);
		}
		return file;
	}

	/**
	 * 下载完善任务书
	 */
	@Override
	public File downloadWanTaskBook(String juese, String userID) {
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		studentUser = graduationProjectManagementDao.getStudentUserByUserId(userID);
		if(studentUser==null) {
			return null;
		}
		//获取学院信息
		bysjglxt_college college = new bysjglxt_college();
		college = graduationProjectManagementDao.getCollegeById(studentUser.getUser_student_belong_college());
		//获取课题最大的year
		String year = graduationProjectManagementDao.getMaxTopicYear();
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 1.根据user Id获得学生毕业论文表中的记录
		bysjglxt_taskbook wanTaskbook = new bysjglxt_taskbook();
		String path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/完善任务书/";
		wanTaskbook = graduationProjectManagementDao.getTaskBookByUserId(userID);
		if (wanTaskbook == null) {
			return null;
		}
		path = path + wanTaskbook.getTaskbook_wan_file();
		File file = new File(path);
		if("student".equals(juese)) {
			return file;
		}
		// 如果下载的人是学生
		// 获取某个学生的指导老师
		bysjglxt_topic_select topicSelect = new bysjglxt_topic_select();
		topicSelect = graduationProjectManagementDao.getStudentSelectTopic(userID);
		if (topicSelect != null && topicSelect.getTopic_select_teacher_tutor() != null
				&& topicSelect.getTopic_select_teacher_tutor().trim().length() > 0) {
			if ((topicSelect.getTopic_select_teacher_tutor()).equals(juese)) {
				// 更改任务书
				wanTaskbook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
				wanTaskbook.setTaskbook_wan_file_xiazai(1);
				graduationProjectManagementDao.saveObj(wanTaskbook);
			}
		}

		return file;
	}

	// 下载下发任务书
	@Override
	public File downloadXiaTaskBook(String juese, String userID) {
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		studentUser = graduationProjectManagementDao.getStudentUserByUserId(userID);
		if(studentUser==null) {
			return null;
		}
		//获取学院信息
		bysjglxt_college college = new bysjglxt_college();
		college = graduationProjectManagementDao.getCollegeById(studentUser.getUser_student_belong_college());
		//获取课题最大的year
		String year = graduationProjectManagementDao.getMaxTopicYear();
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 1.根据user Id获得学生毕业论文表中的记录
		bysjglxt_taskbook xiaTaskbook = new bysjglxt_taskbook();
		String path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/下发任务书/";
		xiaTaskbook = graduationProjectManagementDao.getTaskBookByUserId(userID);
		if (xiaTaskbook == null) {
			return null;
		}// xiaTaskbook.getTaskbook_id() + "_" + 
		path = path +xiaTaskbook.getTaskbook_xia_file();
		File file = new File(path);
		// 如果下载的人是学生
		if("student".equals(juese)) {
			// 更改任务书
			xiaTaskbook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
			xiaTaskbook.setTaskbook_xia_file_xiazai(1);
			graduationProjectManagementDao.saveObj(xiaTaskbook);
		}
		return file;
	}

	/**
	 * 下载开题报告
	 */
	@Override
	public File downloadReportOpening(String juese, String userID) {
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		studentUser = graduationProjectManagementDao.getStudentUserByUserId(userID);
		if(studentUser==null) {
			return null;
		}
		//获取学院信息
		bysjglxt_college college = new bysjglxt_college();
		college = graduationProjectManagementDao.getCollegeById(studentUser.getUser_student_belong_college());
		//获取课题最大的year
		String year = graduationProjectManagementDao.getMaxTopicYear();
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 1.根据user Id获得学生毕业论文表中的记录
		bysjglxt_report_opening reportOpening = new bysjglxt_report_opening();
		String path = lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/开题报告/";
		reportOpening = graduationProjectManagementDao.getReportOpeningUser(userID);
		if (reportOpening == null) {
			return null;
		}
		path = path + reportOpening.getReport_opening_file();
		File file = new File(path);
		// 如果下载的人是学生
		// 获取某个学生的指导老师
		bysjglxt_topic_select topicSelect = new bysjglxt_topic_select();
		topicSelect = graduationProjectManagementDao.getStudentSelectTopic(userID);
		if (topicSelect != null && topicSelect.getTopic_select_teacher_tutor() != null
				&& topicSelect.getTopic_select_teacher_tutor().trim().length() > 0) {
			if ((topicSelect.getTopic_select_teacher_tutor()).equals(juese)) {
				// 更改任务书
				reportOpening.setReport_file_is_xiazai(1);
				reportOpening.setReport_opening_gmt_modified(TeamUtil.getStringSecond());
				graduationProjectManagementDao.saveObj(reportOpening);
			}
		}
		return file;
	}

	// 下载毕业论文
	@Override
	public File downloadDissertation(String userId) {
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		studentUser = graduationProjectManagementDao.getStudentUserByUserId(userId);
		if(studentUser==null) {
			return null;
		}
		//获取学院信息
		bysjglxt_college college = new bysjglxt_college();
		college = graduationProjectManagementDao.getCollegeById(studentUser.getUser_student_belong_college());
		//获取课题最大的year
		String year = graduationProjectManagementDao.getMaxTopicYear();
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 1.根据user Id获得学生毕业论文表中的记录
		bysjglxt_dissertation bysjglxt_dissertation = new bysjglxt_dissertation();
		String path =  lj + "bysjglxt/"+college.getCollege_code()+"/"+year+"/毕业论文/";
		bysjglxt_dissertation = graduationProjectManagementDao.getThesisByStudent(userId);
		if (bysjglxt_dissertation == null) {
			return null;
		}
		path = path + bysjglxt_dissertation.getDissertation_id() + "_" + bysjglxt_dissertation.getDissertation_file();
		File file = new File(path);
		return file;
	}

	/**
	 * 我管理的毕业设计
	 * 
	 */
	@Override
	public TeacherTutorStudentVO teacherManagementStudentVO(TeacherTutorStudentVO teacherTutorStudentVO,
			String teacherUserId) {
		List<TeacherTutorStudentDTO> list_TeacherTutorStudentDTO = new ArrayList<TeacherTutorStudentDTO>();
		TeacherTutorStudentDTO teacherTutorStudentDTO = new TeacherTutorStudentDTO();
		TaskDTO taskDTO = new TaskDTO();
		ProcessBelongDTO processBelongDTO = null;
		TeacherInformationDTO teacherInformationDTO = new TeacherInformationDTO();
		bysjglxt_task_definition taskDefinition = new bysjglxt_task_definition();
		bysjglxt_task_instance taskInstance = new bysjglxt_task_instance();
		bysjglxt_student_basic bysjglxtStudentBasic = new bysjglxt_student_basic();
		bysjglxt_section bysjglxt_section = new bysjglxt_section();
		bysjglxt_student_user bysjglxtStudentUser = new bysjglxt_student_user();
		bysjglxt_topic bysjglxtTopic = new bysjglxt_topic();
		bysjglxt_teacher_basic bysjglxt_teacher_basic = null;
		StudentInformationDTO studentInformationDTO = new StudentInformationDTO();
		List<bysjglxt_process_instance> listProcessInstance = new ArrayList<bysjglxt_process_instance>();
		bysjglxt_process_definition bysjglxt_process_definition = new bysjglxt_process_definition();
		bysjglxt_process_instance processInstance = new bysjglxt_process_instance();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_teacher_user teacherUser = null;
		List<bysjglxt_topic_select> list_bysjglxt_topic_select = new ArrayList<bysjglxt_topic_select>();
		List<bysjglxt_topic_select> list_Allbysjglxt_topic_select = new ArrayList<bysjglxt_topic_select>();
		// 判断老师的身份
		String actor = "";
		String section = "";
		// TODO 如果一个身份即是带角色的身份又是答辩小组长等身份 那么就会报错
		// 1.判断老师是不是领导小组角色
		bysjglxt_teacher_user = graduationProjectManagementDao.getTeacherUserByUserId(teacherUserId);
		if (bysjglxt_teacher_user != null) {
			if (bysjglxt_teacher_user.getUser_teacher_is_college_admin() == 1) {
				actor = "系部管理员";
			} else {
				// 根据教研室主任userId获取教研室
				bysjglxt_section = graduationProjectManagementDao.getSectionByUserId(teacherUserId);
				if (bysjglxt_section != null) {
					actor = "教研室主任";
					section = bysjglxt_section.getSection_id();
				} else {
					// 1.判断老师是不是记录员或答辩小组长
					// 根据userId获取teacherUser表信息
					bysjglxt_teacher_user = graduationProjectManagementDao.getTeacherUserByUserId(teacherUserId);
					if (bysjglxt_teacher_user.getUser_teacher_is_recorder() == 1) {
						actor = "记录员";
					} else {
						if (bysjglxt_teacher_user.getUser_teacher_is_defence_leader() == 1) {
							actor = "答辩小组长";
						} else {
							actor = "无";
						}
					}
				}
			}
		} else {
			return teacherTutorStudentVO;
		}
		// 获得总记录数
		list_Allbysjglxt_topic_select = graduationProjectManagementDao.getTeacherTutorStudentAllSelectTopic(
				teacherTutorStudentVO, teacherUserId, actor, section,
				bysjglxt_teacher_user.getUser_teacher_belong_college());
		int i = list_Allbysjglxt_topic_select.size();
		teacherTutorStudentVO.setTotalRecords(i);
		teacherTutorStudentVO.setTotalPages(((i - 1) / teacherTutorStudentVO.getPageSize()) + 1);
		if (teacherTutorStudentVO.getPageIndex() <= 1) {
			teacherTutorStudentVO.setHavePrePage(false);
		} else {
			teacherTutorStudentVO.setHavePrePage(true);
		}
		if (teacherTutorStudentVO.getPageIndex() >= teacherTutorStudentVO.getTotalPages()) {
			teacherTutorStudentVO.setHaveNextPage(false);
		} else {
			teacherTutorStudentVO.setHaveNextPage(true);
		}
		// 1.根据教师ID筛选出符合条件的最多10条选题数据
		list_bysjglxt_topic_select = graduationProjectManagementDao.getTeacherTutorStudentSelectTopicByPage(
				teacherTutorStudentVO, teacherUserId, actor, section,
				bysjglxt_teacher_user.getUser_teacher_belong_college());
		// 2.遍历选题拿到学生userId信息
		for (bysjglxt_topic_select bysjglxt_topic_select : list_bysjglxt_topic_select) {
			processBelongDTO = new ProcessBelongDTO();
			bysjglxt_teacher_basic = new bysjglxt_teacher_basic();
			teacherUser = new bysjglxt_teacher_user();
			teacherInformationDTO = new TeacherInformationDTO();
			taskDTO = new TaskDTO();
			taskDefinition = new bysjglxt_task_definition();
			bysjglxtStudentUser = new bysjglxt_student_user();
			bysjglxtStudentBasic = new bysjglxt_student_basic();
			taskInstance = new bysjglxt_task_instance();
			teacherTutorStudentDTO = new TeacherTutorStudentDTO();
			processInstance = new bysjglxt_process_instance();
			bysjglxtTopic = new bysjglxt_topic();
			studentInformationDTO = new StudentInformationDTO();
			if (bysjglxt_topic_select.getTopic_select_teacher_review() != null
					&& bysjglxt_topic_select.getTopic_select_teacher_review().trim().length() > 0) {
				// 添加评阅老师信息
				// 获取teacherUserbyId
				teacherUser = graduationProjectManagementDao
						.getTeacherUserByUserId(bysjglxt_topic_select.getTopic_select_teacher_review().trim());
				if (teacherUser != null) {
					if (teacherUser.getUser_teacher_basic() != null
							&& teacherUser.getUser_teacher_basic().trim().length() > 0) {
						bysjglxt_teacher_basic = graduationProjectManagementDao
								.getTeacherBasicByBasicId(teacherUser.getUser_teacher_basic().trim());
						if (bysjglxt_teacher_basic != null) {
							teacherInformationDTO.setBysjglxtTeacherBasic(bysjglxt_teacher_basic);
						}
						teacherInformationDTO.setBysjglxtTeacherUser(teacherUser);
					}
				}
			}

			// 3.根据选题所属学生拿到学生user表
			bysjglxtStudentUser = graduationProjectManagementDao
					.getStudentUserByUserId(bysjglxt_topic_select.getTopic_select_student());
			if (bysjglxtStudentUser != null) {
				// 变颜色
				if (teacherTutorStudentVO.getSearch() != null
						&& teacherTutorStudentVO.getSearch().trim().length() > 0) {
					bysjglxtStudentUser.setUser_student_num(bysjglxtStudentUser.getUser_student_num().replaceAll(
							teacherTutorStudentVO.getSearch(),
							"<span style='color: #ff5063;'>" + teacherTutorStudentVO.getSearch().trim() + "</span>"));
				}
				// 根据学生basicId获取学生basic表
				bysjglxtStudentBasic = graduationProjectManagementDao
						.getStudentBasicByBasicId(bysjglxtStudentUser.getUser_student_basic());
				if (bysjglxtStudentBasic != null) {
					studentInformationDTO.setBysjglxtStudentBasic(bysjglxtStudentBasic);
					studentInformationDTO.setBysjglxtStudentUser(bysjglxtStudentUser);
				}
				// 根据学生userId获取该学生所有的流程实例记录
				listProcessInstance = graduationProjectManagementDao
						.getProcessInstanceByMan(bysjglxt_topic_select.getTopic_select_student());
				// 遍历
				for (bysjglxt_process_instance bysjglxt_process_instance : listProcessInstance) {
					bysjglxt_process_definition = new bysjglxt_process_definition();
					// 根据流程定义ID获取流程定义表
					bysjglxt_process_definition = graduationProjectManagementDao.getProcessDefinitionByID(
							bysjglxt_process_instance.getProcess_instance_process_definition());
					if (bysjglxt_process_definition != null) {
						if ("毕业设计流程".equals(bysjglxt_process_definition.getProcess_definition_name())) {
							processInstance = bysjglxt_process_instance;
							processBelongDTO.setBysjglxt_process_definition(bysjglxt_process_definition);
							processBelongDTO.setBysjglxt_process_instance(processInstance);
							break;
						}
					}
				}
				if (processInstance != null) {
					// 一个流程实例中只有一个任务实例是处于正在进行的状态
					// 根据流程实例ID以及任务实例状态即可判断得到流程进度
					taskInstance = graduationProjectManagementDao
							.getTaskInstanceByProcessInstanceId(processInstance.getProcess_instance_id());
					if (taskInstance != null) {
						// 根据任务实例所属任务定义ID获取任务定义
						taskDefinition = graduationProjectManagementDao
								.getTaskDefinition(taskInstance.getTask_instance_task_definition());
						taskDTO.setTaskDefinition(taskDefinition);
						taskDTO.setTaskInstance(taskInstance);
					}
				}
			} else {
			}
			teacherTutorStudentDTO.setReviewTeacher(teacherInformationDTO);
			teacherTutorStudentDTO.setProcessBelongDTO(processBelongDTO);
			teacherTutorStudentDTO.setStudentInformationDTO(studentInformationDTO);
			teacherTutorStudentDTO.setTaskDTO(taskDTO);
			// 根据选题所属课题拿到课题表信息
			bysjglxtTopic = graduationProjectManagementDao
					.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
			if (bysjglxtTopic != null) {
				teacherTutorStudentDTO.setBysjglxtTopic(bysjglxtTopic);
			}
			list_TeacherTutorStudentDTO.add(teacherTutorStudentDTO);
		}
		teacherTutorStudentVO.setList_TeacherTutorStudentDTO(list_TeacherTutorStudentDTO);
		return teacherTutorStudentVO;
	}

	@Override
	public TeacherTutorStudentVO teacherReviewStudentVO(TeacherTutorStudentVO teacherTutorStudentVO,
			String teacherUserId) {
		List<TeacherTutorStudentDTO> list_TeacherTutorStudentDTO = new ArrayList<TeacherTutorStudentDTO>();
		TeacherTutorStudentDTO teacherTutorStudentDTO = new TeacherTutorStudentDTO();
		TaskDTO taskDTO = new TaskDTO();
		ProcessBelongDTO processBelongDTO = null;
		bysjglxt_task_definition taskDefinition = new bysjglxt_task_definition();
		bysjglxt_task_instance taskInstance = new bysjglxt_task_instance();
		bysjglxt_student_basic bysjglxtStudentBasic = new bysjglxt_student_basic();
		bysjglxt_student_user bysjglxtStudentUser = new bysjglxt_student_user();
		bysjglxt_topic bysjglxtTopic = new bysjglxt_topic();
		StudentInformationDTO studentInformationDTO = new StudentInformationDTO();
		List<bysjglxt_process_instance> listProcessInstance = new ArrayList<bysjglxt_process_instance>();
		bysjglxt_process_definition bysjglxt_process_definition = new bysjglxt_process_definition();
		bysjglxt_process_instance processInstance = new bysjglxt_process_instance();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		List<bysjglxt_topic_select> list_bysjglxt_topic_select = new ArrayList<bysjglxt_topic_select>();
		List<bysjglxt_topic_select> list_Allbysjglxt_topic_select = new ArrayList<bysjglxt_topic_select>();
		// 判断老师的身份
		String actor = "评阅老师";
		String section = "";
		bysjglxt_teacher_user = graduationProjectManagementDao.getTeacherUserByUserId(teacherUserId);
		// 获得总记录数
		list_Allbysjglxt_topic_select = graduationProjectManagementDao.getTeacherTutorStudentAllSelectTopic(
				teacherTutorStudentVO, teacherUserId, actor, section,
				bysjglxt_teacher_user.getUser_teacher_belong_college());
		int i = list_Allbysjglxt_topic_select.size();
		teacherTutorStudentVO.setTotalRecords(i);
		teacherTutorStudentVO.setTotalPages(((i - 1) / teacherTutorStudentVO.getPageSize()) + 1);
		if (teacherTutorStudentVO.getPageIndex() <= 1) {
			teacherTutorStudentVO.setHavePrePage(false);
		} else {
			teacherTutorStudentVO.setHavePrePage(true);
		}
		if (teacherTutorStudentVO.getPageIndex() >= teacherTutorStudentVO.getTotalPages()) {
			teacherTutorStudentVO.setHaveNextPage(false);
		} else {
			teacherTutorStudentVO.setHaveNextPage(true);
		}
		// 1.根据教师ID筛选出符合条件的最多10条选题数据
		list_bysjglxt_topic_select = graduationProjectManagementDao.getTeacherTutorStudentSelectTopicByPage(
				teacherTutorStudentVO, teacherUserId, actor, section,
				bysjglxt_teacher_user.getUser_teacher_belong_college());
		// 2.遍历选题拿到学生userId信息
		for (bysjglxt_topic_select bysjglxt_topic_select : list_bysjglxt_topic_select) {
			processBelongDTO = new ProcessBelongDTO();
			taskDTO = new TaskDTO();
			taskDefinition = new bysjglxt_task_definition();
			bysjglxtStudentUser = new bysjglxt_student_user();
			bysjglxtStudentBasic = new bysjglxt_student_basic();
			taskInstance = new bysjglxt_task_instance();
			teacherTutorStudentDTO = new TeacherTutorStudentDTO();
			processInstance = new bysjglxt_process_instance();
			bysjglxtTopic = new bysjglxt_topic();
			studentInformationDTO = new StudentInformationDTO();
			// 3.根据选题所属学生拿到学生user表
			bysjglxtStudentUser = graduationProjectManagementDao
					.getStudentUserByUserId(bysjglxt_topic_select.getTopic_select_student());

			if (bysjglxtStudentUser != null) {
				// 变颜色
				if (teacherTutorStudentVO.getSearch() != null
						&& teacherTutorStudentVO.getSearch().trim().length() > 0) {
					bysjglxtStudentUser.setUser_student_num(bysjglxtStudentUser.getUser_student_num().replaceAll(
							teacherTutorStudentVO.getSearch(),
							"<span style='color: #ff5063;'>" + teacherTutorStudentVO.getSearch().trim() + "</span>"));
				}
				// 根据学生basicId获取学生basic表
				bysjglxtStudentBasic = graduationProjectManagementDao
						.getStudentBasicByBasicId(bysjglxtStudentUser.getUser_student_basic());
				if (bysjglxtStudentBasic != null) {
					studentInformationDTO.setBysjglxtStudentBasic(bysjglxtStudentBasic);
					studentInformationDTO.setBysjglxtStudentUser(bysjglxtStudentUser);
				}
				// 根据学生userId获取该学生所有的流程实例记录
				listProcessInstance = graduationProjectManagementDao
						.getProcessInstanceByMan(bysjglxt_topic_select.getTopic_select_student());
				// 遍历
				for (bysjglxt_process_instance bysjglxt_process_instance : listProcessInstance) {
					bysjglxt_process_definition = new bysjglxt_process_definition();
					// 根据流程定义ID获取流程定义表
					bysjglxt_process_definition = graduationProjectManagementDao.getProcessDefinitionByID(
							bysjglxt_process_instance.getProcess_instance_process_definition());
					if (bysjglxt_process_definition != null) {
						if ("毕业设计流程".equals(bysjglxt_process_definition.getProcess_definition_name())) {
							processInstance = bysjglxt_process_instance;
							processBelongDTO.setBysjglxt_process_definition(bysjglxt_process_definition);
							processBelongDTO.setBysjglxt_process_instance(processInstance);
							break;
						}
					}
				}
				if (processInstance != null) {
					// 一个流程实例中只有一个任务实例是处于正在进行的状态
					// 根据流程实例ID以及任务实例状态即可判断得到流程进度
					taskInstance = graduationProjectManagementDao
							.getTaskInstanceByProcessInstanceId(processInstance.getProcess_instance_id());
					if (taskInstance != null) {
						// 根据任务实例所属任务定义ID获取任务定义
						taskDefinition = graduationProjectManagementDao
								.getTaskDefinition(taskInstance.getTask_instance_task_definition());
						taskDTO.setTaskDefinition(taskDefinition);
						taskDTO.setTaskInstance(taskInstance);
					}
				}
			}
			teacherTutorStudentDTO.setProcessBelongDTO(processBelongDTO);
			teacherTutorStudentDTO.setStudentInformationDTO(studentInformationDTO);
			teacherTutorStudentDTO.setTaskDTO(taskDTO);
			// 根据选题所属课题拿到课题表信息
			bysjglxtTopic = graduationProjectManagementDao
					.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
			if (bysjglxtTopic != null) {
				teacherTutorStudentDTO.setBysjglxtTopic(bysjglxtTopic);
			}

			list_TeacherTutorStudentDTO.add(teacherTutorStudentDTO);
		}
		teacherTutorStudentVO.setList_TeacherTutorStudentDTO(list_TeacherTutorStudentDTO);
		return teacherTutorStudentVO;
	}

	/**
	 * 我指导的毕业设计
	 */
	@Override
	public TeacherTutorStudentVO teacherTutorStudentVO(TeacherTutorStudentVO teacherTutorStudentVO,
			String teacherUserId) {
		List<TeacherTutorStudentDTO> list_TeacherTutorStudentDTO = new ArrayList<TeacherTutorStudentDTO>();
		TeacherTutorStudentDTO teacherTutorStudentDTO = new TeacherTutorStudentDTO();
		TaskDTO taskDTO = new TaskDTO();
		ProcessBelongDTO processBelongDTO = null;
		bysjglxt_task_definition taskDefinition = new bysjglxt_task_definition();
		bysjglxt_task_instance taskInstance = new bysjglxt_task_instance();
		bysjglxt_student_basic bysjglxtStudentBasic = new bysjglxt_student_basic();
		bysjglxt_student_user bysjglxtStudentUser = new bysjglxt_student_user();
		bysjglxt_topic bysjglxtTopic = new bysjglxt_topic();
		List<bysjglxt_topic_select> listTopicSelect = new ArrayList<bysjglxt_topic_select>();
		StudentInformationDTO studentInformationDTO = new StudentInformationDTO();
		List<bysjglxt_process_instance> listProcessInstance = new ArrayList<bysjglxt_process_instance>();
		bysjglxt_process_definition bysjglxt_process_definition = new bysjglxt_process_definition();
		bysjglxt_process_instance processInstance = new bysjglxt_process_instance();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		List<bysjglxt_topic_select> list_bysjglxt_topic_select = new ArrayList<bysjglxt_topic_select>();
		List<bysjglxt_topic_select> list_Allbysjglxt_topic_select = new ArrayList<bysjglxt_topic_select>();
		// 判断老师的身份
		String actor = "";
		String section = "";
		// 1.判断老师是否是指导老师
		listTopicSelect = graduationProjectManagementDao.getSelectTopicByTutorId(teacherUserId);
		if (listTopicSelect.size() > 0) {
			actor = "指导教师";
		} else {
			actor = "无";
		}
		bysjglxt_teacher_user = graduationProjectManagementDao.getTeacherUserByUserId(teacherUserId);
		// 获得总记录数
		list_Allbysjglxt_topic_select = graduationProjectManagementDao.getTeacherTutorStudentAllSelectTopic(
				teacherTutorStudentVO, teacherUserId, actor, section,
				bysjglxt_teacher_user.getUser_teacher_belong_college());
		int i = list_Allbysjglxt_topic_select.size();
		teacherTutorStudentVO.setTotalRecords(i);
		teacherTutorStudentVO.setTotalPages(((i - 1) / teacherTutorStudentVO.getPageSize()) + 1);
		if (teacherTutorStudentVO.getPageIndex() <= 1) {
			teacherTutorStudentVO.setHavePrePage(false);
		} else {
			teacherTutorStudentVO.setHavePrePage(true);
		}
		if (teacherTutorStudentVO.getPageIndex() >= teacherTutorStudentVO.getTotalPages()) {
			teacherTutorStudentVO.setHaveNextPage(false);
		} else {
			teacherTutorStudentVO.setHaveNextPage(true);
		}
		// 1.根据教师ID筛选出符合条件的最多10条选题数据
		list_bysjglxt_topic_select = graduationProjectManagementDao.getTeacherTutorStudentSelectTopicByPage(
				teacherTutorStudentVO, teacherUserId, actor, section,
				bysjglxt_teacher_user.getUser_teacher_belong_college());
		// 2.遍历选题拿到学生userId信息
		for (bysjglxt_topic_select bysjglxt_topic_select : list_bysjglxt_topic_select) {
			processBelongDTO = new ProcessBelongDTO();
			taskDTO = new TaskDTO();
			taskDefinition = new bysjglxt_task_definition();
			bysjglxtStudentUser = new bysjglxt_student_user();
			bysjglxtStudentBasic = new bysjglxt_student_basic();
			taskInstance = new bysjglxt_task_instance();
			teacherTutorStudentDTO = new TeacherTutorStudentDTO();
			processInstance = new bysjglxt_process_instance();
			bysjglxtTopic = new bysjglxt_topic();
			studentInformationDTO = new StudentInformationDTO();
			// 3.根据选题所属学生拿到学生user表
			bysjglxtStudentUser = graduationProjectManagementDao
					.getStudentUserByUserId(bysjglxt_topic_select.getTopic_select_student());

			if (bysjglxtStudentUser != null) {
				// 变颜色
				if (teacherTutorStudentVO.getSearch() != null
						&& teacherTutorStudentVO.getSearch().trim().length() > 0) {
					bysjglxtStudentUser.setUser_student_num(bysjglxtStudentUser.getUser_student_num().replaceAll(
							teacherTutorStudentVO.getSearch(),
							"<span style='color: #ff5063;'>" + teacherTutorStudentVO.getSearch().trim() + "</span>"));
				}
				// 根据学生basicId获取学生basic表
				bysjglxtStudentBasic = graduationProjectManagementDao
						.getStudentBasicByBasicId(bysjglxtStudentUser.getUser_student_basic());
				if (bysjglxtStudentBasic != null) {
					studentInformationDTO.setBysjglxtStudentBasic(bysjglxtStudentBasic);
					studentInformationDTO.setBysjglxtStudentUser(bysjglxtStudentUser);
				}
				// 根据学生userId获取该学生所有的流程实例记录
				listProcessInstance = graduationProjectManagementDao
						.getProcessInstanceByMan(bysjglxt_topic_select.getTopic_select_student());
				// 遍历
				for (bysjglxt_process_instance bysjglxt_process_instance : listProcessInstance) {
					bysjglxt_process_definition = new bysjglxt_process_definition();
					// 根据流程定义ID获取流程定义表
					bysjglxt_process_definition = graduationProjectManagementDao.getProcessDefinitionByID(
							bysjglxt_process_instance.getProcess_instance_process_definition());
					if (bysjglxt_process_definition != null) {
						if ("毕业设计流程".equals(bysjglxt_process_definition.getProcess_definition_name())) {
							processInstance = bysjglxt_process_instance;
							processBelongDTO.setBysjglxt_process_definition(bysjglxt_process_definition);
							processBelongDTO.setBysjglxt_process_instance(processInstance);
							break;
						}
					}
				}
				if (processInstance != null) {
					// 一个流程实例中只有一个任务实例是处于正在进行的状态
					// 根据流程实例ID以及任务实例状态即可判断得到流程进度
					taskInstance = graduationProjectManagementDao
							.getTaskInstanceByProcessInstanceId(processInstance.getProcess_instance_id());
					if (taskInstance != null) {
						// 根据任务实例所属任务定义ID获取任务定义
						taskDefinition = graduationProjectManagementDao
								.getTaskDefinition(taskInstance.getTask_instance_task_definition());
						taskDTO.setTaskDefinition(taskDefinition);
						taskDTO.setTaskInstance(taskInstance);
					}
				}
			}
			teacherTutorStudentDTO.setProcessBelongDTO(processBelongDTO);
			teacherTutorStudentDTO.setStudentInformationDTO(studentInformationDTO);
			teacherTutorStudentDTO.setTaskDTO(taskDTO);
			// 根据选题所属课题拿到课题表信息
			bysjglxtTopic = graduationProjectManagementDao
					.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
			/*
			 * if (teacherTutorStudentVO.getSearch() != null &&
			 * teacherTutorStudentVO.getSearch().trim().length() > 0) {
			 * bysjglxtTopic.setTopic_name_chinese(bysjglxtTopic.
			 * getTopic_name_chinese().replaceAll( teacherTutorStudentVO.getSearch(),
			 * "<span style='color: #ff5063;'>" + teacherTutorStudentVO.getSearch().trim() +
			 * "</span>")); }
			 */
			if (bysjglxtTopic != null) {
				teacherTutorStudentDTO.setBysjglxtTopic(bysjglxtTopic);
			}

			list_TeacherTutorStudentDTO.add(teacherTutorStudentDTO);
		}
		teacherTutorStudentVO.setList_TeacherTutorStudentDTO(list_TeacherTutorStudentDTO);
		return teacherTutorStudentVO;
	}

	/**
	 * 1 成功 2失败
	 */
	@Override
	public int startGraduationProjectProcess(String studentId) {
		if (studentId == null || studentId.trim().length() <= 0) {
			return 0;
		}
		int flag = 1;
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		bysjglxt_report_opening bysjglxt_report_opening = new bysjglxt_report_opening();
		bysjglxt_record_progress bysjglxt_record_progressEarlystage = new bysjglxt_record_progress();
		bysjglxt_record_progress bysjglxt_record_progressMetaphase = new bysjglxt_record_progress();
		bysjglxt_dissertation bysjglxt_dissertation = new bysjglxt_dissertation();
		bysjglxt_record_progress bysjglxt_record_progressLaterstage = new bysjglxt_record_progress();
		bysjglxt_record_progress bysjglxt_record_progressPerfect = new bysjglxt_record_progress();
		bysjglxt_summary bysjglxt_summary = new bysjglxt_summary();
		bysjglxt_examination_formal bysjglxt_examination_formal = new bysjglxt_examination_formal();
		bysjglxt_evaluate_tutor bysjglxt_evaluate_tutor = new bysjglxt_evaluate_tutor();
		bysjglxt_evaluate_review bysjglxt_evaluate_review = new bysjglxt_evaluate_review();
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_taskbook.setTaskbook_id(TeamUtil.getUuid());
		bysjglxt_taskbook.setBysjglxt_taskbook_student(studentId);
		bysjglxt_taskbook.setTaskbook_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_taskbook.setTaskbook_gmt_modified(bysjglxt_taskbook.getTaskbook_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInTaskBook(bysjglxt_taskbook);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_report_opening.setReport_opening_id(TeamUtil.getUuid());
		bysjglxt_report_opening.setReport_opening_student(studentId);
		bysjglxt_report_opening.setReport_opening_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_report_opening.setReport_opening_gmt_modified(bysjglxt_report_opening.getReport_opening_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInOpening(bysjglxt_report_opening);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_record_progressEarlystage.setRecord_progress_id(TeamUtil.getUuid());
		bysjglxt_record_progressEarlystage.setRecord_progress_student(studentId);
		bysjglxt_record_progressEarlystage.setRecord_progress_stage("前期");
		bysjglxt_record_progressEarlystage.setRecord_progress_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_record_progressEarlystage
				.setRecord_progress_gmt_modified(bysjglxt_record_progressEarlystage.getRecord_progress_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progressEarlystage);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_record_progressMetaphase.setRecord_progress_id(TeamUtil.getUuid());
		bysjglxt_record_progressMetaphase.setRecord_progress_student(studentId);
		bysjglxt_record_progressMetaphase.setRecord_progress_stage("中期");
		bysjglxt_record_progressMetaphase.setRecord_progress_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_record_progressMetaphase
				.setRecord_progress_gmt_modified(bysjglxt_record_progressMetaphase.getRecord_progress_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progressMetaphase);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_record_progressLaterstage.setRecord_progress_id(TeamUtil.getUuid());
		bysjglxt_record_progressLaterstage.setRecord_progress_student(studentId);
		bysjglxt_record_progressLaterstage.setRecord_progress_stage("后期");
		bysjglxt_record_progressLaterstage.setRecord_progress_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_record_progressLaterstage
				.setRecord_progress_gmt_modified(bysjglxt_record_progressLaterstage.getRecord_progress_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progressLaterstage);
		if (flag == 2) {
			return flag;
		}

		bysjglxt_record_progressPerfect.setRecord_progress_id(TeamUtil.getUuid());
		bysjglxt_record_progressPerfect.setRecord_progress_student(studentId);
		bysjglxt_record_progressPerfect.setRecord_progress_stage("完善");
		bysjglxt_record_progressPerfect.setRecord_progress_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_record_progressPerfect
				.setRecord_progress_gmt_modified(bysjglxt_record_progressPerfect.getRecord_progress_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progressPerfect);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_dissertation.setDissertation_id(TeamUtil.getUuid());
		bysjglxt_dissertation.setDissertation_student(studentId);
		bysjglxt_dissertation.setDissertation_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_dissertation.setDissertation_gmt_modified(bysjglxt_dissertation.getDissertation_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInDissertation(bysjglxt_dissertation);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_summary.setSummary_id(TeamUtil.getUuid());
		bysjglxt_summary.setSummary_student(studentId);
		bysjglxt_summary.setSummary_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_summary.setSummary_gmt_modified(TeamUtil.getStringSecond());
		flag = graduationProjectManagementDao.fillEmptyInSummary(bysjglxt_summary);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_examination_formal.setExamination_formal_id(TeamUtil.getUuid());
		bysjglxt_examination_formal.setExamination_formal_student(studentId);
		bysjglxt_examination_formal.setExamination_formal_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_examination_formal.setExamination_formal_gmt_modified(TeamUtil.getStringSecond());
		flag = graduationProjectManagementDao.fillEmptyInExaminationFormal(bysjglxt_examination_formal);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_evaluate_tutor.setEvaluate_tutor_id(TeamUtil.getUuid());
		bysjglxt_evaluate_tutor.setEvaluate_tutor_student(studentId);
		bysjglxt_evaluate_tutor.setEvaluate_tutor_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_evaluate_tutor.setEvaluate_tutor_gmt_modified(bysjglxt_evaluate_tutor.getEvaluate_tutor_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInEvaluateTutor(bysjglxt_evaluate_tutor);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_evaluate_review.setEvaluate_review_id(TeamUtil.getUuid());
		bysjglxt_evaluate_review.setEvaluate_review_student(studentId);
		bysjglxt_evaluate_review.setEvaluate_review_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_evaluate_review
				.setEvaluate_review_gmt_modified(bysjglxt_evaluate_review.getEvaluate_review_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyEvaluateReview(bysjglxt_evaluate_review);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_defence.setDefence_id(TeamUtil.getUuid());
		bysjglxt_defence.setDefence_student(studentId);
		bysjglxt_defence.setDefence_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_defence.setDefence_gmt_modified(bysjglxt_defence.getDefence_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyDefence(bysjglxt_defence);
		return flag;

	}

	/**
	 * 学生更改任务书
	 */
	@Override
	public int updateTeacherTaskbook(bysjglxt_taskbook updateTaskbook) {
		int flag = 2;
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		bysjglxt_taskbook = graduationProjectManagementDao.getTaskbookById(updateTaskbook.getTaskbook_id());
		if (bysjglxt_taskbook != null) {
			/*
			 * bysjglxt_taskbook.setTaskbook_acontent_required(updateTaskbook.
			 * getTaskbook_acontent_required());
			 * bysjglxt_taskbook.setTaskbook_reference(updateTaskbook.getTaskbook_reference(
			 * )); bysjglxt_taskbook.setTaskbook_plan(updateTaskbook.getTaskbook_plan());
			 */
			bysjglxt_taskbook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInTaskBook(bysjglxt_taskbook);
		}
		return flag;
	}

	/**
	 * 教研室主任更改任务书
	 */
	@Override
	public int updateSectionTaskbook(bysjglxt_taskbook updateTaskbook) {
		int flag = 2;
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		bysjglxt_taskbook = graduationProjectManagementDao.getTaskbookById(updateTaskbook.getTaskbook_id());
		if (bysjglxt_taskbook != null) {
			bysjglxt_taskbook.setTaskbook_leader_option(updateTaskbook.getTaskbook_leader_option());
			bysjglxt_taskbook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInTaskBook(bysjglxt_taskbook);
		}
		return flag;
	}

	/**
	 * 学生更改开题报告
	 */
	@Override
	public int updateReportOpening(bysjglxt_report_opening updateReportOpening) {
		int flag = 2;
		bysjglxt_report_opening bysjglxt_report_opening = new bysjglxt_report_opening();
		bysjglxt_report_opening = graduationProjectManagementDao
				.getReportOpening(updateReportOpening.getReport_opening_id());
		if (bysjglxt_report_opening != null) {

			/*
			 * bysjglxt_report_opening.setReport_opening_documentary_survey(
			 * updateReportOpening.getReport_opening_documentary_survey());
			 * bysjglxt_report_opening.setReport_opening_main(updateReportOpening.
			 * getReport_opening_main());
			 * bysjglxt_report_opening.setReport_opening_detail(updateReportOpening.
			 * getReport_opening_detail());
			 * bysjglxt_report_opening.setReport_opening_reference(updateReportOpening.
			 * getReport_opening_reference());
			 * bysjglxt_report_opening.setReport_opening_plan(updateReportOpening.
			 * getReport_opening_plan());
			 */
			bysjglxt_report_opening
					.setReport_opening_gmt_modified(updateReportOpening.getReport_opening_gmt_modified());
			flag = graduationProjectManagementDao.fillEmptyInOpening(bysjglxt_report_opening);
		}
		return flag;
	}

	/**
	 * 学生更改前期情况记录
	 * @throws IOException 
	 */
	@Override
	public int updateStudentRecordProgressEarlystage(bysjglxt_record_progress updateRecordProgress,File dissertation, String oldDissertation, String user_student_id,
			String dissertationFileName) throws IOException {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Earlystage = new bysjglxt_record_progress();
		bysjglxt_record_progress_Earlystage = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Earlystage != null) {
			bysjglxt_record_progress_Earlystage
					.setRecord_progress_record(updateRecordProgress.getRecord_progress_record());
			bysjglxt_record_progress_Earlystage.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Earlystage);
		}
		
		/**
		 * 
		 */
		return saveRecordProgress(dissertation, oldDissertation, user_student_id, dissertationFileName, bysjglxt_record_progress_Earlystage,1);
	}

	/**
	 * 老师更改前期情况记录
	 */
	@Override
	public int updateTeacherRecordProcessEarlystage(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Earlystage = new bysjglxt_record_progress();
		bysjglxt_record_progress_Earlystage = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Earlystage != null) {
			bysjglxt_record_progress_Earlystage
					.setRecord_progress_opinion(updateRecordProgress.getRecord_progress_opinion());
			bysjglxt_record_progress_Earlystage.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Earlystage);
		}
		return flag;
	}

	/**
	 * 学生更改中期记录
	 * @throws IOException 
	 */
	@Override
	public int updateStudentRecordProgressMetaphase(bysjglxt_record_progress updateRecordProgress,File dissertation, String oldDissertation, String user_student_id,
			String dissertationFileName) throws IOException {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Metaphase = new bysjglxt_record_progress();
		bysjglxt_record_progress_Metaphase = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Metaphase != null) {
			bysjglxt_record_progress_Metaphase
					.setRecord_progress_record(updateRecordProgress.getRecord_progress_record());
			bysjglxt_record_progress_Metaphase.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Metaphase);
		}
		
		return saveRecordProgress(dissertation, oldDissertation, user_student_id, dissertationFileName, bysjglxt_record_progress_Metaphase,2);
	}

	/**
	 * 教师更改中期记录
	 */
	@Override
	public int updateTeacherRecordProgressMetaphase(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Metaphase = new bysjglxt_record_progress();
		bysjglxt_record_progress_Metaphase = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		System.out.println("bysjglxt_record_progress_Metaphase:"+bysjglxt_record_progress_Metaphase);
		if (bysjglxt_record_progress_Metaphase != null) {
			bysjglxt_record_progress_Metaphase
					.setRecord_progress_opinion(updateRecordProgress.getRecord_progress_opinion());
			bysjglxt_record_progress_Metaphase.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Metaphase);
		}
		return flag;
	}

	/**
	 * 学生修改后期记录
	 */
	@Override
	public int updateStudentRecordProgressLaterstage(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Laterstage = new bysjglxt_record_progress();
		bysjglxt_record_progress_Laterstage = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Laterstage != null) {
			bysjglxt_record_progress_Laterstage
					.setRecord_progress_record(updateRecordProgress.getRecord_progress_record());
			bysjglxt_record_progress_Laterstage.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Laterstage);
		}
		return flag;
	}

	/**
	 * 教师修改后期记录
	 */
	@Override
	public int updateTeacherRecordProgressLaterstage(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Laterstage = new bysjglxt_record_progress();
		bysjglxt_record_progress_Laterstage = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Laterstage != null) {
			bysjglxt_record_progress_Laterstage
					.setRecord_progress_opinion(updateRecordProgress.getRecord_progress_opinion());
			bysjglxt_record_progress_Laterstage.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Laterstage);
		}
		return flag;
	}

	/**
	 * 学生修改完善期信息
	 */
	@Override
	public int updateStudentRecordProgressPerfect(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Perfect = new bysjglxt_record_progress();
		bysjglxt_record_progress_Perfect = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Perfect != null) {
			bysjglxt_record_progress_Perfect
					.setRecord_progress_record(updateRecordProgress.getRecord_progress_record());
			bysjglxt_record_progress_Perfect.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Perfect);
		}
		return flag;
	}

	/**
	 * 教师修改完善期信息
	 */
	@Override
	public int updateTeacherRecordProgressPerfect(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Perfect = new bysjglxt_record_progress();
		bysjglxt_record_progress_Perfect = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Perfect != null) {
			bysjglxt_record_progress_Perfect
					.setRecord_progress_opinion(updateRecordProgress.getRecord_progress_opinion());
			bysjglxt_record_progress_Perfect.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Perfect);
		}
		return flag;
	}

	/**
	 * 学生修改个人学习工作总结
	 */
	@Override
	public int updateStudentSummary(bysjglxt_summary updateSummary) {
		int flag = 2;
		bysjglxt_summary bysjglxt_summary = new bysjglxt_summary();
		bysjglxt_summary = graduationProjectManagementDao.findSummaryById(updateSummary.getSummary_id());
		if (bysjglxt_summary != null) {
			bysjglxt_summary.setSummary_summary(updateSummary.getSummary_summary());
			bysjglxt_summary.setSummary_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInSummary(bysjglxt_summary);
		}
		return flag;
	}

	/**
	 * 教师修改个人学习工作总结
	 */
	@Override
	public int updateTeacherSummary(bysjglxt_summary updateSummary) {
		int flag = 2;
		bysjglxt_summary bysjglxt_summary = new bysjglxt_summary();
		bysjglxt_summary = graduationProjectManagementDao.findSummaryById(updateSummary.getSummary_id());
		if (bysjglxt_summary != null) {
			bysjglxt_summary.setSummary_opinion(updateSummary.getSummary_opinion());
			bysjglxt_summary.setSummary_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInSummary(bysjglxt_summary);
		}
		return flag;
	}

	@Override
	public int updateTeacherExaminationFormal(bysjglxt_examination_formal updateExaminationFormal) {
		int flag = 2;
		bysjglxt_examination_formal bysjglxt_examination_formal = new bysjglxt_examination_formal();
		bysjglxt_examination_formal = graduationProjectManagementDao
				.findExaminationFormalById(updateExaminationFormal.getExamination_formal_id());
		if (bysjglxt_examination_formal != null) {
			bysjglxt_examination_formal
					.setExamination_formal_is_cover(updateExaminationFormal.getExamination_formal_is_cover());
			bysjglxt_examination_formal
					.setExamination_formal_is_a4(updateExaminationFormal.getExamination_formal_is_a4());
			bysjglxt_examination_formal
					.setExamination_formal_is_format(updateExaminationFormal.getExamination_formal_is_format());
			bysjglxt_examination_formal.setExamination_formal_is_abstract_chinese(
					updateExaminationFormal.getExamination_formal_is_abstract_chinese());
			bysjglxt_examination_formal
					.setExamination_formal_is_catalog(updateExaminationFormal.getExamination_formal_is_catalog());
			bysjglxt_examination_formal.setExamination_formal_is_abstract_foreign(
					updateExaminationFormal.getExamination_formal_is_abstract_foreign());
			bysjglxt_examination_formal
					.setExamination_formal_is_headline(updateExaminationFormal.getExamination_formal_is_headline());
			bysjglxt_examination_formal.setExamination_formal_is_punctuation(
					updateExaminationFormal.getExamination_formal_is_punctuation());
			bysjglxt_examination_formal
					.setExamination_formal_is_typo(updateExaminationFormal.getExamination_formal_is_typo());
			bysjglxt_examination_formal.setExamination_formal_is_reference_ten(
					updateExaminationFormal.getExamination_formal_is_reference_ten());
			bysjglxt_examination_formal.setExamination_formal_is_reference_foreign(
					updateExaminationFormal.getExamination_formal_is_reference_foreign());
			bysjglxt_examination_formal.setExamination_formal_is_reference_num(
					updateExaminationFormal.getExamination_formal_is_reference_num());
			bysjglxt_examination_formal.setExamination_formal_is_reference_new(
					updateExaminationFormal.getExamination_formal_is_reference_new());
			bysjglxt_examination_formal.setExamination_formal_is_reference_format(
					updateExaminationFormal.getExamination_formal_is_reference_format());
			bysjglxt_examination_formal.setExamination_formal_is_progress_metaphase(
					updateExaminationFormal.getExamination_formal_is_progress_metaphase());
			bysjglxt_examination_formal.setExamination_formal_is_progress_summary(
					updateExaminationFormal.getExamination_formal_is_progress_summary());
			bysjglxt_examination_formal.setExamination_formal_is_progress_actual(
					updateExaminationFormal.getExamination_formal_is_progress_actual());
			bysjglxt_examination_formal.setExamination_formal_is_progress_complete(
					updateExaminationFormal.getExamination_formal_is_progress_complete());
			bysjglxt_examination_formal.setExamination_formal_is_progress_logic(
					updateExaminationFormal.getExamination_formal_is_progress_logic());
			bysjglxt_examination_formal
					.setExamination_formal_is_chart(updateExaminationFormal.getExamination_formal_is_chart());
			bysjglxt_examination_formal
					.setExamination_formal_is_enclosure(updateExaminationFormal.getExamination_formal_is_enclosure());
			bysjglxt_examination_formal.setExamination_formal_is_teacher_opinion(
					updateExaminationFormal.getExamination_formal_is_teacher_opinion());
			bysjglxt_examination_formal.setExamination_formal_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInExaminationFormal(bysjglxt_examination_formal);
		}
		return flag;
	}

	/**
	 * 领导小组更改形式审查表
	 */
	@Override
	public int updateLeaderExaminationFormal(bysjglxt_examination_formal updateExaminationFormal) {
		int flag = 2;
		bysjglxt_examination_formal bysjglxt_examination_formal = new bysjglxt_examination_formal();
		bysjglxt_examination_formal = graduationProjectManagementDao
				.findExaminationFormalById(updateExaminationFormal.getExamination_formal_id());
		if (bysjglxt_examination_formal != null) {
			bysjglxt_examination_formal.setExamination_formal_is_leader_opinion(
					updateExaminationFormal.getExamination_formal_is_leader_opinion());
			bysjglxt_examination_formal.setExamination_formal_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInExaminationFormal(bysjglxt_examination_formal);
		}
		return flag;
	}

	@Override
	public int updateEvaluateTutor(bysjglxt_evaluate_tutor updateEvaluateTutor) {
		int flag = 2;
		bysjglxt_evaluate_tutor bysjglxt_evaluate_tutor = new bysjglxt_evaluate_tutor();
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_evaluate_tutor = graduationProjectManagementDao
				.findEvaluateTutor(updateEvaluateTutor.getEvaluate_tutor_id());
		if (bysjglxt_evaluate_tutor != null) {

			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_teacher_comment(updateEvaluateTutor.getEvaluate_tutor_teacher_comment());
			bysjglxt_evaluate_tutor.setEvaluate_tutor_grade_training_objective(
					updateEvaluateTutor.getEvaluate_tutor_grade_training_objective());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_difficulty(updateEvaluateTutor.getEvaluate_tutor_grade_difficulty());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_workload(updateEvaluateTutor.getEvaluate_tutor_grade_workload());
			bysjglxt_evaluate_tutor.setEvaluate_tutor_grade_bind(updateEvaluateTutor.getEvaluate_tutor_grade_bind());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_comprehensive(updateEvaluateTutor.getEvaluate_tutor_grade_comprehensive());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_reference(updateEvaluateTutor.getEvaluate_tutor_grade_reference());
			bysjglxt_evaluate_tutor.setEvaluate_tutor_grade_experimental_design(
					updateEvaluateTutor.getEvaluate_tutor_grade_experimental_design());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_computing(updateEvaluateTutor.getEvaluate_tutor_grade_computing());
			bysjglxt_evaluate_tutor.setEvaluate_tutor_grade_foreign_language(
					updateEvaluateTutor.getEvaluate_tutor_grade_foreign_language());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_computer(updateEvaluateTutor.getEvaluate_tutor_grade_computer());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_innovate(updateEvaluateTutor.getEvaluate_tutor_grade_innovate());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_analysis(updateEvaluateTutor.getEvaluate_tutor_grade_analysis());
			bysjglxt_evaluate_tutor.setEvaluate_tutor_grade_chart(updateEvaluateTutor.getEvaluate_tutor_grade_chart());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_instructions(updateEvaluateTutor.getEvaluate_tutor_grade_instructions());
			bysjglxt_evaluate_tutor.setEvaluate_tutor_grade_practicability(
					updateEvaluateTutor.getEvaluate_tutor_grade_practicability());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_normalization(updateEvaluateTutor.getEvaluate_tutor_grade_normalization());
			bysjglxt_evaluate_tutor.setEvaluate_tutor_grade_total(updateEvaluateTutor.getEvaluate_tutor_grade_total());
			if (bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_total() > 60) {
				bysjglxt_evaluate_tutor.setEvaluate_tutor_is_teacher_opinion(1);
			} else {
				bysjglxt_evaluate_tutor.setEvaluate_tutor_is_teacher_opinion(0);
			}
			bysjglxt_evaluate_tutor.setEvaluate_tutor_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.saveObj(bysjglxt_evaluate_tutor);
			if (flag != 2) {
				// 根据得到的答辩评分表将指导教师评分占比填写进去
				bysjglxt_defence = graduationProjectManagementDao
						.findDefenceByUserId(bysjglxt_evaluate_tutor.getEvaluate_tutor_student());
				bysjglxt_defence
						.setDefence_grade_evaluate_tutor(bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_total());
				bysjglxt_defence.setDefence_gmt_modified(TeamUtil.getStringSecond());
				flag = graduationProjectManagementDao.fillEmptyDefence(bysjglxt_defence);
			}
		}
		return flag;
	}

	@Override
	public int updateEvaluateReview(bysjglxt_evaluate_review updateEvaluateReview) {
		int flag = 2;
		bysjglxt_evaluate_review bysjglxt_evaluate_review = new bysjglxt_evaluate_review();
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_evaluate_review = graduationProjectManagementDao
				.findEvaluateReviewById(updateEvaluateReview.getEvaluate_review_id());
		if (bysjglxt_evaluate_review != null) {
			bysjglxt_evaluate_review
					.setEvaluate_review_teacher_comment(updateEvaluateReview.getEvaluate_review_teacher_comment());
			bysjglxt_evaluate_review.setEvaluate_review_grade_training_objective(
					updateEvaluateReview.getEvaluate_review_grade_training_objective());
			bysjglxt_evaluate_review
					.setEvaluate_review_grade_difficulty(updateEvaluateReview.getEvaluate_review_grade_difficulty());
			bysjglxt_evaluate_review
					.setEvaluate_review_grade_workload(updateEvaluateReview.getEvaluate_review_grade_workload());
			bysjglxt_evaluate_review
					.setEvaluate_review_grade_bind(updateEvaluateReview.getEvaluate_review_grade_bind());
			bysjglxt_evaluate_review.setEvaluate_review_grade_comprehensive(
					updateEvaluateReview.getEvaluate_review_grade_comprehensive());
			bysjglxt_evaluate_review
					.setEvaluate_review_grade_reference(updateEvaluateReview.getEvaluate_review_grade_reference());
			bysjglxt_evaluate_review.setEvaluate_review_grade_experimental_design(
					updateEvaluateReview.getEvaluate_review_grade_experimental_design());

			bysjglxt_evaluate_review
					.setEvaluate_review_grade_computing(updateEvaluateReview.getEvaluate_review_grade_computing());

			bysjglxt_evaluate_review.setEvaluate_review_grade_foreign_language(
					updateEvaluateReview.getEvaluate_review_grade_foreign_language());
			bysjglxt_evaluate_review
					.setEvaluate_review_grade_computer(updateEvaluateReview.getEvaluate_review_grade_computer());
			bysjglxt_evaluate_review
					.setEvaluate_review_grade_innovate(updateEvaluateReview.getEvaluate_review_grade_innovate());

			bysjglxt_evaluate_review
					.setEvaluate_review_grade_analysis(updateEvaluateReview.getEvaluate_review_grade_analysis());

			bysjglxt_evaluate_review
					.setEvaluate_review_grade_chart(updateEvaluateReview.getEvaluate_review_grade_chart());

			bysjglxt_evaluate_review.setEvaluate_review_grade_instructions(
					updateEvaluateReview.getEvaluate_review_grade_instructions());

			bysjglxt_evaluate_review.setEvaluate_review_grade_practicability(
					updateEvaluateReview.getEvaluate_review_grade_practicability());

			bysjglxt_evaluate_review.setEvaluate_review_grade_normalization(
					updateEvaluateReview.getEvaluate_review_grade_normalization());
			bysjglxt_evaluate_review
					.setEvaluate_review_grade_total(updateEvaluateReview.getEvaluate_review_grade_total());
			if(bysjglxt_evaluate_review.getEvaluate_review_grade_total()>60) {
				bysjglxt_evaluate_review.setEvaluate_review_is_teacher_opinion(1);
			}else {
				bysjglxt_evaluate_review.setEvaluate_review_is_teacher_opinion(0);
			}
			bysjglxt_evaluate_review.setEvaluate_review_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyEvaluateReview(bysjglxt_evaluate_review);
			if (flag != 2) {
				bysjglxt_defence = graduationProjectManagementDao
						.findDefenceByUserId(bysjglxt_evaluate_review.getEvaluate_review_student());
				bysjglxt_defence.setDefence_grade_evaluate_review(
						bysjglxt_evaluate_review.getEvaluate_review_grade_total());
				bysjglxt_defence.setDefence_gmt_modified(TeamUtil.getStringSecond());
				flag = graduationProjectManagementDao.fillEmptyDefence(bysjglxt_defence);
			}
		}
		return flag;
	}

	@Override
	public int updateDefence(bysjglxt_defence updateDefence) {
		int flag = 2;
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_defence = graduationProjectManagementDao.findDefenceByUserId(updateDefence.getDefence_student());
		String grade = "";
		if (bysjglxt_defence != null) {
			bysjglxt_defence.setDefence_leader_comment(updateDefence.getDefence_leader_comment());
			bysjglxt_defence.setDefence_grade_writing(updateDefence.getDefence_grade_writing());
			bysjglxt_defence.setDefence_grade_normalization(updateDefence.getDefence_grade_normalization());
			bysjglxt_defence.setDefence_grade_complete(updateDefence.getDefence_grade_complete());
			bysjglxt_defence.setDefence_grade_technology(updateDefence.getDefence_grade_technology());
			bysjglxt_defence.setDefence_grade_practicability(updateDefence.getDefence_grade_practicability());
			bysjglxt_defence.setDefence_grade_appearance(updateDefence.getDefence_grade_appearance());
			bysjglxt_defence.setDefence_grade_statement(updateDefence.getDefence_grade_statement());
			bysjglxt_defence.setDefence_grade_answer(updateDefence.getDefence_grade_answer());
			bysjglxt_defence.setDefence_grade_defence(updateDefence.getDefence_grade_defence());
			bysjglxt_defence.setDefence_total((int) (Math.round((bysjglxt_defence.getDefence_grade_defence() * 0.4
					+ bysjglxt_defence.getDefence_grade_evaluate_review() * 0.3
					+ bysjglxt_defence.getDefence_grade_evaluate_tutor() * 0.3) + 0.5)));
			/**
			 * 
			 * 五级评分制 优：90 中：80 良：70 及格：60 不及格：<60
			 */
			bysjglxt_defence.setDefence_finally(TeamUtil.grade(bysjglxt_defence.getDefence_total()));
			bysjglxt_defence.setDefence_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyDefence(bysjglxt_defence);
		}
		return flag;
	}

	/**
	 * 记录员更改答辩表
	 */
	@Override
	public int updateDefenceRecorder(bysjglxt_defence updateDefence) {
		int flag = 2;
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_defence = graduationProjectManagementDao.findDefenceByUserId(updateDefence.getDefence_student());
		if (bysjglxt_defence != null) {
			bysjglxt_defence.setDefence_chenshu_time(updateDefence.getDefence_chenshu_time());
			bysjglxt_defence.setDefence_tiwen_time(updateDefence.getDefence_tiwen_time());
			bysjglxt_defence.setDefence_record(updateDefence.getDefence_record());
			bysjglxt_defence.setDefence_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyDefence(bysjglxt_defence);
		}
		return flag;
	}

	/********************************
	 * 下面是我的毕业设计需要
	 ***************************************/

	@Override
	public bysjglxt_taskbook get_TaskBook(String userId) {
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		bysjglxt_taskbook = graduationProjectManagementDao.getTaskBookByUserId(userId);
		return bysjglxt_taskbook;
	}

	@Override
	public bysjglxt_dissertation get_Dissertation(String userId) {
		bysjglxt_dissertation bysjglxt_dissertation = new bysjglxt_dissertation();
		bysjglxt_dissertation = graduationProjectManagementDao.getThesisByStudent(userId);
		return bysjglxt_dissertation;
	}

	@Override
	public bysjglxt_report_opening get_ReportOpening(String userId) {
		bysjglxt_report_opening bysjglxt_report_opening = new bysjglxt_report_opening();
		bysjglxt_report_opening = graduationProjectManagementDao.getReportOpeningUser(userId);
		return bysjglxt_report_opening;
	}

	@Override
	public bysjglxt_record_progress get_RecordProgress_1(String userId) {
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		bysjglxt_record_progress = graduationProjectManagementDao.getRecordProgress(userId, "前期");
		return bysjglxt_record_progress;
	}

	@Override
	public bysjglxt_record_progress get_RecordProgress_2(String userId) {
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		bysjglxt_record_progress = graduationProjectManagementDao.getRecordProgress(userId, "中期");
		return bysjglxt_record_progress;
	}

	@Override
	public bysjglxt_record_progress get_RecordProgress_3(String userId) {
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		bysjglxt_record_progress = graduationProjectManagementDao.getRecordProgress(userId, "后期");
		return bysjglxt_record_progress;
	}

	@Override
	public bysjglxt_record_progress get_RecordProgress_4(String userId) {
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		bysjglxt_record_progress = graduationProjectManagementDao.getRecordProgress(userId, "完善");
		return bysjglxt_record_progress;
	}

	@Override
	public bysjglxt_summary get_Summary(String userId) {
		bysjglxt_summary bysjglxt_summary = new bysjglxt_summary();
		bysjglxt_summary = graduationProjectManagementDao.getSummary(userId);
		return bysjglxt_summary;
	}

	@Override
	public bysjglxt_examination_formal get_ExaminationFormal(String userId) {
		bysjglxt_examination_formal bysjglxt_examination_formal = new bysjglxt_examination_formal();
		bysjglxt_examination_formal = graduationProjectManagementDao.getExaminationFormal(userId);
		return bysjglxt_examination_formal;
	}

	@Override
	public bysjglxt_evaluate_tutor get_EvaluateTutor(String userId) {
		bysjglxt_evaluate_tutor bysjglxt_evaluate_tutor = new bysjglxt_evaluate_tutor();
		bysjglxt_evaluate_tutor = graduationProjectManagementDao.getEvaluateTutor(userId);
		return bysjglxt_evaluate_tutor;
	}

	@Override
	public bysjglxt_evaluate_review get_EvaluateReview(String userId) {
		bysjglxt_evaluate_review bysjglxt_evaluate_review = new bysjglxt_evaluate_review();
		bysjglxt_evaluate_review = graduationProjectManagementDao.getEvaluateReview(userId);
		return bysjglxt_evaluate_review;
	}

	@Override
	public bysjglxt_defence get_Defence(String userId) {
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_defence = graduationProjectManagementDao.getDefence(userId);
		return bysjglxt_defence;
	}

	@Override
	public File exportAll(List<String> userListId) throws Exception {
		if (userListId.size() < 1) {
			return null;
		}
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<File> fileList = new ArrayList<File>();
		for (String usrId : userListId) {
			fileList.add(exportOneStudent(usrId));
		}
		File zipFile = new File(lj + "毕业设计过程管理手册.zip");
		TeamUtil.zipFiles(fileList, zipFile);
		for (String usrId : userListId) {
			exportOneStudent(usrId).delete();
		}
		return zipFile;
	}

	// ---------------------------------------------------------------------------------------------//

	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public File exportAll() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		// 获取文件路径
		String lj = "";
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
		lj = props.getProperty("lj");
		Map<String, Object> param = new HashMap<String, Object>();
		params.putAll(param);
		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		// 设置编码
		configuration.setClassForTemplateLoading(this.getClass(), "");
		Template t = configuration.getTemplate("abc.ftl", "UTF-8");
		OutputStream os = new FileOutputStream(lj + "kokokoko.doc");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
		t.process(params, bw);
		return new File(lj + "kokokoko.doc");
	}

	// ---------------------------------------------------------------------------------------------//

	// 导出单个人--放在单个人那里使用
	public File exportOneStudent(String userId) throws Exception {
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		// 封面
		params.putAll(exportCover(userId));
		// 前期情况记录
		params.putAll(exportEarlystage(userId));
		// 撰写阶段
		params.putAll(exportMetaphase(userId));
		// 中期自查情况记录
		params.putAll(exportLaterstage(userId));
		// 完善情况记录
		params.putAll(exportPerfect(userId));
		// 个人学习工作总结
		params.putAll(exportSummary(userId));
		// 形式审查表
		params.putAll(exportFormal(userId));
		// 指导老师评价表
		params.putAll(exportTeacherOpin(userId));
		// 评阅老师评价表
		params.putAll(exportReviewOpin(userId));
		// 答辩评分表
		params.putAll(exportDefence(userId));
		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		// 根据user Id获取学生信息
		bysjglxt_student_basic studentBasic = new bysjglxt_student_basic();
		studentBasic = graduationProjectManagementDao.getStudentBasicByUserId(userId);
		configuration.setClassForTemplateLoading(this.getClass(), "");
		Template t = configuration.getTemplate("graduation.ftl", "UTF-8");
		String pa = lj + "毕业设计过程管理手册——" + studentBasic.getStudent_basic_num() + studentBasic.getStudent_basic_name()
				+ ".doc";
		OutputStream os = new FileOutputStream(pa);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
		t.process(params, bw);
		return new File(pa);
		// 任务书
		// params.putAll(exportTask(userId));
		// 开题报告
		// params.putAll(exportOpeningReport(userId));
		
	
	
		
		
		

		
	}

	// 导出封面
	@Override
	public Map<String, Object> exportCover(String studentUserId) {
		// 1.根据学生user Id获取学生登录表信息
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_teacher_user bysjglxt_teacher_user_tutor = new bysjglxt_teacher_user();
		bysjglxt_teacher_user bysjglxt_teacher_user_evaluate = new bysjglxt_teacher_user();
		bysjglxt_teacher_basic bysjglxt_teacher_basic_tutor = new bysjglxt_teacher_basic();
		bysjglxt_teacher_basic bysjglxt_teacher_basic_evaluate = new bysjglxt_teacher_basic();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				int sessional = Integer.parseInt(bysjglxt_student_basic.getStudent_basic_level());
				sessional = sessional + 4;
				// 学号
				params.put("f2", bysjglxt_student_basic.getStudent_basic_num());
				// 姓名
				params.put("f3", bysjglxt_student_basic.getStudent_basic_name());
				// 学院
				params.put("f4", bysjglxt_student_basic.getStudent_basic_college());
				// 届别
				params.put("f5", sessional + "");
				// TODO 这里有变化
				// 专业班级
				params.put("f6", bysjglxt_student_basic.getStudent_basic_major() + "专业  "
						+ bysjglxt_student_basic.getStudent_basic_class());
			} else {
				params.put("f2", "");
				params.put("f3", "");
				params.put("f4", "");
				params.put("f5", "");
				params.put("f6", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据老师userId获得指导老师user表
				bysjglxt_teacher_user_tutor = graduationProjectManagementDao
						.getTeacherUserByUserId(bysjglxt_topic_select.getTopic_select_teacher_tutor());
				// 根据老师basicId获得指导老师basic表
				bysjglxt_teacher_basic_tutor = graduationProjectManagementDao
						.getTeacherBasicByBasicId(bysjglxt_teacher_user_tutor.getUser_teacher_basic());
				params.put("f7", bysjglxt_teacher_basic_tutor.getName() + "    "
						+ bysjglxt_teacher_basic_tutor.getProfessional_title());
				if (bysjglxt_topic_select.getTopic_select_teacher_review() != null
						&& bysjglxt_topic_select.getTopic_select_teacher_review().trim().length() > 0) {
					// 根据老师userId获得评阅老师user表
					bysjglxt_teacher_user_evaluate = graduationProjectManagementDao
							.getTeacherUserByUserId(bysjglxt_topic_select.getTopic_select_teacher_review());
					// 根据老师basicid获得评阅老师basic表
					bysjglxt_teacher_basic_evaluate = graduationProjectManagementDao
							.getTeacherBasicByBasicId(bysjglxt_teacher_user_evaluate.getUser_teacher_basic());
					params.put("f8", bysjglxt_teacher_basic_evaluate.getName() + "    "
							+ bysjglxt_teacher_basic_evaluate.getProfessional_title());
				} else {
					params.put("f8", "");
				}
				// 根据选题获取课题
				bysjglxt_topic topic = new bysjglxt_topic();
				topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				String name = "";
				if (topic.getTopic_name_chinese() != null || !"".equals(topic.getTopic_name_chinese())) {
					int j = 0;
					while (j <= 1) {
						for (int i = 0; i < (36 - topic.getTopic_name_chinese().trim().length() >> 1) - 2; i++) {
							name += " ";
						}
						if (j == 0) {
							name += topic.getTopic_name_chinese().trim();
						}
						j++;
					}
				}
				params.put("f1", name);
			} else {
				params.put("f7", "");
				params.put("f8", "");
				params.put("f1", "");
			}

		}
		return params;
	}

	// 导出任务书
	@Override
	public Map<String, Object> exportTask(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("r1", bysjglxt_student_basic.getStudent_basic_num());
				params.put("r2", bysjglxt_student_basic.getStudent_basic_name());
				params.put("r3", bysjglxt_student_basic.getStudent_basic_class());
			} else {
				params.put("r1", "");
				params.put("r2", "");
				params.put("r3", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("r4", bysjglxt_topic.getTopic_name_chinese());
					params.put("r5", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("r4", "");
					params.put("r5", "");
				}
			}
			// 根据user Id获取任务书表
			bysjglxt_taskbook = graduationProjectManagementDao
					.getTaskBookByUserId(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_taskbook != null) {
				// params.put("r6", bysjglxt_taskbook.getTaskbook_acontent_required());
				// params.put("r7", bysjglxt_taskbook.getTaskbook_reference());
				// params.put("r8", bysjglxt_taskbook.getTaskbook_plan());
				// params.put("r13", bysjglxt_taskbook.getTaskbook_opinion());
			} else {
				params.put("r6", "");
				params.put("r7", "");
				params.put("r8", "");
				params.put("r13", "");
			}
		}
		params.put("r9", "");
		params.put("r10", "");
		params.put("r11", "");
		params.put("r12", "");
		params.put("r14", "");
		params.put("r15", "");
		params.put("r16", "");
		params.put("r17", "");
		params.put("r18", "");
		params.put("r19", "");
		params.put("r20", "");
		params.put("r21", "");
		return params;
	}

	// 导出开题报告
	@Override
	public Map<String, Object> exportOpeningReport(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_report_opening bysjglxt_report_opening = new bysjglxt_report_opening();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		/*
		 * bysjglxt_student_user =
		 * graduationProjectManagementDao.getStudentUserByUserId(studentUserId); if
		 * (bysjglxt_student_user != null) { // 根据basicId获取basic表 bysjglxt_student_basic
		 * = graduationProjectManagementDao
		 * .getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic()); if
		 * (bysjglxt_student_basic != null) { params.put("${openingNum}",
		 * bysjglxt_student_basic.getStudent_basic_num()); params.put("${openingNam}",
		 * bysjglxt_student_basic.getStudent_basic_name());
		 * params.put("${openingMajor}",
		 * bysjglxt_student_basic.getStudent_basic_major()); int sessional =
		 * Integer.parseInt(bysjglxt_student_basic.getStudent_basic_level()); sessional
		 * = sessional + 4; params.put("${openingSessional}", sessional + ""); } else {
		 * params.put("${openingNum}", ""); params.put("${openingNam}", "");
		 * params.put("${openingMajor}", ""); params.put("${openingSessional}", ""); }
		 * // 根据userid获取学生选题信息 bysjglxt_topic_select = graduationProjectManagementDao
		 * .getStudentSelectTopic(bysjglxt_student_user.getUser_student_id()); if
		 * (bysjglxt_topic_select != null) { // 根据课题ID获取课题表信息 bysjglxt_topic =
		 * graduationProjectManagementDao
		 * .getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic()); if
		 * (bysjglxt_topic != null) { params.put("${openingChineseName}",
		 * bysjglxt_topic.getTopic_name_chinese()); params.put("${openingEnglishName}",
		 * bysjglxt_topic.getTopic_name_english()); } else {
		 * params.put("${openingChineseName}", ""); params.put("${openingEnglishName}",
		 * ""); } } // 根据userId获取开题报告 bysjglxt_report_opening =
		 * graduationProjectManagementDao
		 * .getReportOpeningUser(bysjglxt_student_user.getUser_student_id()); if
		 * (bysjglxt_report_opening != null) { params.put("${openingDocumentSurvey}",
		 * bysjglxt_report_opening.getReport_opening_documentary_survey());
		 * params.put("${openingMain}",
		 * bysjglxt_report_opening.getReport_opening_main());
		 * params.put("${openingDetail}",
		 * bysjglxt_report_opening.getReport_opening_detail());
		 * params.put("${openingReference}",
		 * bysjglxt_report_opening.getReport_opening_reference());
		 * params.put("${openingPlan}",
		 * bysjglxt_report_opening.getReport_opening_plan()); } else {
		 * params.put("${openingDocumentSurvey}", ""); params.put("${openingMain}", "");
		 * params.put("${openingDetail}", ""); params.put("${openingReference}", "");
		 * params.put("${openingPlan}", ""); } }
		 */
		return params;
	}

	// 导出前期情况记录
	@Override
	public Map<String, Object> exportEarlystage(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("q1", bysjglxt_student_basic.getStudent_basic_num());
				params.put("q2", bysjglxt_student_basic.getStudent_basic_name());
				params.put("q3", bysjglxt_student_basic.getStudent_basic_class());
			} else {
				params.put("q1", "");
				params.put("q2", "");
				params.put("q3", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("q4", bysjglxt_topic.getTopic_name_chinese());
					params.put("q5", bysjglxt_topic.getTopic_name_english());
				}
			}else {
				params.put("q4", "");
				params.put("q5", "");
			}
			// 根据user ID以及阶段获取进展情况记录表
			bysjglxt_record_progress = graduationProjectManagementDao
					.getRecordProgress(bysjglxt_student_user.getUser_student_id(), "前期");
			if (bysjglxt_record_progress != null) {
				if(bysjglxt_record_progress.getRecord_progress_record()!=null || bysjglxt_record_progress.getRecord_progress_record().trim().length()>0) {
					params.put("q8", bysjglxt_record_progress.getRecord_progress_record());
				}else {
					params.put("q8", "");
				}
				if(bysjglxt_record_progress.getRecord_progress_opinion()!=null || bysjglxt_record_progress.getRecord_progress_opinion().trim().length()>0) {
					params.put("q9", bysjglxt_record_progress.getRecord_progress_opinion());
				}else {
					params.put("q9", "");
				}
			} else {
				params.put("q8", "");
				params.put("q9", "");
			}
			//获取对应的任务实例
			bysjglxt_task_instance taskInstanceStart = new bysjglxt_task_instance();
			taskInstanceStart = graduationProjectManagementDao.getTaskInstance("学生完成进展情况记录（前期准备阶段）", studentUserId);
			if(taskInstanceStart!=null && taskInstanceStart.getTask_instance_start().trim().length()>0) {
				params.put("q6", TeamUtil.timeToYear(taskInstanceStart.getTask_instance_start())
						+ " 年 " + TeamUtil.timeToMonth(taskInstanceStart.getTask_instance_start())
						+ " 月 " + TeamUtil.timeToDay(taskInstanceStart.getTask_instance_start())
						+ " 日 ");
			}else {
				params.put("q6", "");
			}
			bysjglxt_task_instance taskInstanceEnd = new bysjglxt_task_instance();
			taskInstanceEnd = graduationProjectManagementDao.getTaskInstance("指导老师填写进展情况意见（前期准备阶段）", studentUserId);
			if(taskInstanceEnd!=null && taskInstanceEnd.getTask_instance_stop().trim().length()>0) {
				params.put("q7", TeamUtil.timeToYear(taskInstanceEnd.getTask_instance_stop())
						+ " 年 " + TeamUtil.timeToMonth(taskInstanceEnd.getTask_instance_stop())
						+ " 月 " + TeamUtil.timeToDay(taskInstanceEnd.getTask_instance_stop())
						+ " 日 ");
			}else {
				params.put("q7", "");
			}
		}else {
			params.put("q1", "");
			params.put("q2", "");
			params.put("q3", "");
			params.put("q4", "");
			params.put("q5", "");
			params.put("q6", "");
			params.put("q7", "");
			params.put("q8", "");
			params.put("q9", "");
		}
		return params;
	}

	@Override
	public Map<String, Object> exportMetaphase(String studentUserId) {

		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("z1", bysjglxt_student_basic.getStudent_basic_num());
				params.put("z2", bysjglxt_student_basic.getStudent_basic_name());
				params.put("z3", bysjglxt_student_basic.getStudent_basic_class());
			} else {
				params.put("z1", "");
				params.put("z2", "");
				params.put("z3", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("z4", bysjglxt_topic.getTopic_name_chinese());
					params.put("z5", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("z4", "");
					params.put("z5", "");
				}
			}
			// 根据user ID以及阶段获取进展情况记录表
			bysjglxt_record_progress = graduationProjectManagementDao
					.getRecordProgress(bysjglxt_student_user.getUser_student_id(), "中期");
			if (bysjglxt_record_progress != null) {
				params.put("z8", bysjglxt_record_progress.getRecord_progress_record());
				params.put("z9", bysjglxt_record_progress.getRecord_progress_opinion());
			} else {
				params.put("z8", "");
				params.put("z9", "");
			}
			//获取对应的任务实例
			bysjglxt_task_instance taskInstanceStart = new bysjglxt_task_instance();
			taskInstanceStart = graduationProjectManagementDao.getTaskInstance("学生完成进展情况记录（撰写阶段）", studentUserId);
			if(taskInstanceStart!=null && taskInstanceStart.getTask_instance_start().trim().length()>0) {
				params.put("z6", TeamUtil.timeToYear(taskInstanceStart.getTask_instance_start())
						+ " 年 " + TeamUtil.timeToMonth(taskInstanceStart.getTask_instance_start())
						+ " 月 " + TeamUtil.timeToDay(taskInstanceStart.getTask_instance_start())
						+ " 日 ");
			}else {
				params.put("z6", "");
			}
			bysjglxt_task_instance taskInstanceEnd = new bysjglxt_task_instance();
			taskInstanceEnd = graduationProjectManagementDao.getTaskInstance("指导老师填写进展情况意见（撰写阶段）", studentUserId);
			if(taskInstanceEnd!=null && taskInstanceEnd.getTask_instance_stop().trim().length()>0) {
				params.put("z7", TeamUtil.timeToYear(taskInstanceEnd.getTask_instance_stop())
						+ " 年 " + TeamUtil.timeToMonth(taskInstanceEnd.getTask_instance_stop())
						+ " 月 " + TeamUtil.timeToDay(taskInstanceEnd.getTask_instance_stop())
						+ " 日 ");
			}else {
				params.put("z7", "");
			}
		}else {
			params.put("z1", "");
			params.put("z2", "");
			params.put("z3", "");
			params.put("z4", "");
			params.put("z5", "");
			params.put("z6", "");
			params.put("z7", "");
			params.put("z8", "");
			params.put("z9", "");
		}
		return params;
	}

	// 导出后期
	@Override
	public Map<String, Object> exportLaterstage(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("z11", bysjglxt_student_basic.getStudent_basic_num());
				params.put("z12", bysjglxt_student_basic.getStudent_basic_name());
				params.put("z13", bysjglxt_student_basic.getStudent_basic_class());
			} else {
				params.put("z11", "");
				params.put("z12", "");
				params.put("z13", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("z14", bysjglxt_topic.getTopic_name_chinese());
					params.put("z15", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("z14", "");
					params.put("z15", "");
				}
			}
			// 根据user ID以及阶段获取进展情况记录表
			bysjglxt_record_progress = graduationProjectManagementDao
					.getRecordProgress(bysjglxt_student_user.getUser_student_id(), "后期");
			if (bysjglxt_record_progress != null) {
				params.put("z18", bysjglxt_record_progress.getRecord_progress_record());
				params.put("z19", bysjglxt_record_progress.getRecord_progress_opinion());
			} else {
				params.put("z18", "");
				params.put("z19", "");
			}
			//获取对应的任务实例
			bysjglxt_task_instance taskInstanceStart = new bysjglxt_task_instance();
			taskInstanceStart = graduationProjectManagementDao.getTaskInstance("学生完成进展情况记录（中期自查阶段）", studentUserId);
			if(taskInstanceStart!=null && taskInstanceStart.getTask_instance_start().trim().length()>0) {
				params.put("z16", TeamUtil.timeToYear(taskInstanceStart.getTask_instance_start())
						+ " 年 " + TeamUtil.timeToMonth(taskInstanceStart.getTask_instance_start())
						+ " 月 " + TeamUtil.timeToDay(taskInstanceStart.getTask_instance_start())
						+ " 日 ");
			}else {
				params.put("z16", "");
			}
			bysjglxt_task_instance taskInstanceEnd = new bysjglxt_task_instance();
			taskInstanceEnd = graduationProjectManagementDao.getTaskInstance("指导老师填写进展情况意见（中期自查阶段）", studentUserId);
			if(taskInstanceEnd!=null && taskInstanceEnd.getTask_instance_stop().trim().length()>0) {
				params.put("z17", TeamUtil.timeToYear(taskInstanceEnd.getTask_instance_stop())
						+ " 年 " + TeamUtil.timeToMonth(taskInstanceEnd.getTask_instance_stop())
						+ " 月 " + TeamUtil.timeToDay(taskInstanceEnd.getTask_instance_stop())
						+ " 日 ");
			}else {
				params.put("z17", "");
			}
		}else {
			params.put("z11", "");
			params.put("z12", "");
			params.put("z13", "");
			params.put("z14", "");
			params.put("z15", "");
			params.put("z16", "");
			params.put("z17", "");
			params.put("z18", "");
			params.put("z19", "");
		}
		return params;
	}

	@Override
	public Map<String, Object> exportPerfect(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("w1", bysjglxt_student_basic.getStudent_basic_num());
				params.put("w2", bysjglxt_student_basic.getStudent_basic_name());
				params.put("w3", bysjglxt_student_basic.getStudent_basic_class());
			} else {
				params.put("w1", "");
				params.put("w2", "");
				params.put("w3", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("w4", bysjglxt_topic.getTopic_name_chinese());
					params.put("w5", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("w4", "");
					params.put("w5", "");
				}
			}
			// 根据user ID以及阶段获取进展情况记录表
			bysjglxt_record_progress = graduationProjectManagementDao
					.getRecordProgress(bysjglxt_student_user.getUser_student_id(), "完善");
			if (bysjglxt_record_progress != null) {
				params.put("w8", bysjglxt_record_progress.getRecord_progress_record());
				params.put("w9", bysjglxt_record_progress.getRecord_progress_opinion());
			} else {
				params.put("w8", "");
				params.put("w9", "");
			}
			//获取对应的任务实例
			bysjglxt_task_instance taskInstanceStart = new bysjglxt_task_instance();
			taskInstanceStart = graduationProjectManagementDao.getTaskInstance("学生完成进展情况记录（完善阶段）", studentUserId);
			if(taskInstanceStart!=null && taskInstanceStart.getTask_instance_start().trim().length()>0) {
				params.put("w6", TeamUtil.timeToYear(taskInstanceStart.getTask_instance_start())
						+ " 年 " + TeamUtil.timeToMonth(taskInstanceStart.getTask_instance_start())
						+ " 月 " + TeamUtil.timeToDay(taskInstanceStart.getTask_instance_start())
						+ " 日 ");
			}else {
				params.put("w6", "");
			}
			bysjglxt_task_instance taskInstanceEnd = new bysjglxt_task_instance();
			taskInstanceEnd = graduationProjectManagementDao.getTaskInstance("指导老师填写进展情况意见（完善阶段）", studentUserId);
			if(taskInstanceEnd!=null && taskInstanceEnd.getTask_instance_stop().trim().length()>0) {
				params.put("w7", TeamUtil.timeToYear(taskInstanceEnd.getTask_instance_stop())
						+ " 年 " + TeamUtil.timeToMonth(taskInstanceEnd.getTask_instance_stop())
						+ " 月 " + TeamUtil.timeToDay(taskInstanceEnd.getTask_instance_stop())
						+ " 日 ");
			}else {
				params.put("w7", "");
			}
		}else {
			params.put("w1", "");
			params.put("w2", "");
			params.put("w3", "");
			params.put("w4", "");
			params.put("w5", "");
			params.put("w6", "");
			params.put("w7", "");
			params.put("w8", "");
			params.put("w9", "");
		}
		return params;
	}

	@Override
	public Map<String, Object> exportSummary(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_summary bysjglxt_summary = new bysjglxt_summary();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("g1", bysjglxt_student_basic.getStudent_basic_num());
				params.put("g2", bysjglxt_student_basic.getStudent_basic_name());
				params.put("g3", bysjglxt_student_basic.getStudent_basic_class());
			} else {
				params.put("g1", "");
				params.put("g2", "");
				params.put("g3", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("g4", bysjglxt_topic.getTopic_name_chinese());
					params.put("g5", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("g4", "");
					params.put("g5", "");
				}
			}
			// 根据user Id获取个人学习工作总结
			bysjglxt_summary = graduationProjectManagementDao.getSummary(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_summary != null) {
				params.put("g8", bysjglxt_summary.getSummary_summary());
				params.put("g9", bysjglxt_summary.getSummary_opinion());
			} else {
				params.put("g8", "");
				params.put("g9", "");
			}
			//获取对应的任务实例
			bysjglxt_task_instance taskInstanceStart = new bysjglxt_task_instance();
			taskInstanceStart = graduationProjectManagementDao.getTaskInstance("学生完成个人学习工作总结", studentUserId);
			if(taskInstanceStart!=null && taskInstanceStart.getTask_instance_start().trim().length()>0) {
				params.put("g6", TeamUtil.timeToYear(taskInstanceStart.getTask_instance_start())
						+ " 年 " + TeamUtil.timeToMonth(taskInstanceStart.getTask_instance_start())
						+ " 月 " + TeamUtil.timeToDay(taskInstanceStart.getTask_instance_start())
						+ " 日 ");
			}else {
				params.put("g6", "");
			}
			bysjglxt_task_instance taskInstanceEnd = new bysjglxt_task_instance();
			taskInstanceEnd = graduationProjectManagementDao.getTaskInstance("指导老师填写个人学习工作总结意见", studentUserId);
			if(taskInstanceEnd!=null && taskInstanceEnd.getTask_instance_stop().trim().length()>0) {
				params.put("g7", TeamUtil.timeToYear(taskInstanceEnd.getTask_instance_stop())
						+ " 年 " + TeamUtil.timeToMonth(taskInstanceEnd.getTask_instance_stop())
						+ " 月 " + TeamUtil.timeToDay(taskInstanceEnd.getTask_instance_stop())
						+ " 日 ");
			}else {
				params.put("g7", "");
			}
		}else {
			params.put("g1", "");
			params.put("g2", "");
			params.put("g3", "");
			params.put("g4", "");
			params.put("g5", "");
			params.put("g6", "");
			params.put("g7", "");
			params.put("g8", "");
			params.put("g9", "");
		}
		return params;
	}

	/**
	 * 导出形式审查表
	 */
	@Override
	public Map<String, Object> exportFormal(String studentUserId) {

		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_examination_formal bysjglxt_examination_formal = new bysjglxt_examination_formal();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("x1", bysjglxt_student_basic.getStudent_basic_num());
				params.put("x2", bysjglxt_student_basic.getStudent_basic_name());
				params.put("x3", bysjglxt_student_basic.getStudent_basic_class());
			} else {
				params.put("x1", "");
				params.put("x2", "");
				params.put("x3", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("x4", bysjglxt_topic.getTopic_name_chinese());
					params.put("x5", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("x4", "");
					params.put("x5", "");
				}
			}
			// 根据user Id获取形式审查表
			bysjglxt_examination_formal = graduationProjectManagementDao
					.getExaminationFormal(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_examination_formal != null) {
				switch (bysjglxt_examination_formal.getExamination_formal_is_cover()) {
				case 1:
					params.put("x6a", "✓");
					params.put("x6b", "");
					params.put("x6c", "");
					break;
				case 0:
					params.put("x6a", "");
					params.put("x6b", "✓");
					params.put("x6c", "");
					break;
				case -1:
					params.put("x6a", "");
					params.put("x6b", "");
					params.put("x6c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_a4()) {
				case 1:
					params.put("x7a", "✓");
					params.put("x7b", "");
					params.put("x7c", "");
					break;
				case 0:
					params.put("x7a", "");
					params.put("x7b", "✓");
					params.put("x7c", "");
					break;
				case -1:
					params.put("x7a", "");
					params.put("x7b", "");
					params.put("x7c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_format()) {
				case 1:
					params.put("x8a", "✓");
					params.put("x8b", "");
					params.put("x8c", "");
					break;
				case 0:
					params.put("x8a", "");
					params.put("x8b", "✓");
					params.put("x8c", "");
					break;
				case -1:
					params.put("x8a", "");
					params.put("x8b", "");
					params.put("x8c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_abstract_chinese()) {
				case 1:
					params.put("x9a", "✓");
					params.put("x9b", "");
					params.put("x9c", "");
					break;
				case 0:
					params.put("x9a", "");
					params.put("x9b", "✓");
					params.put("x9c", "");
					break;
				case -1:
					params.put("x9a", "");
					params.put("x9b", "");
					params.put("x9c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_chinese_keyword()) {
				case 1:
					params.put("x10a", "✓");
					params.put("x10b", "");
					params.put("x10c", "");
					break;
				case 0:
					params.put("x10a", "");
					params.put("x10b", "✓");
					params.put("x10c", "");
					break;
				case -1:
					params.put("x10a", "");
					params.put("x10b", "");
					params.put("x10c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_abstract_foreign()) {
				case 1:
					params.put("x11a", "✓");
					params.put("x11b", "");
					params.put("x11c", "");
					break;
				case 0:
					params.put("x11a", "");
					params.put("x11b", "✓");
					params.put("x11c", "");
					break;
				case -1:
					params.put("x11a", "");
					params.put("x11b", "");
					params.put("x11c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_catalog()) {
				case 1:
					params.put("x12a", "✓");
					params.put("x12b", "");
					params.put("x12c", "");
					break;
				case 0:
					params.put("x12a", "");
					params.put("x12b", "✓");
					params.put("x12c", "");
					break;
				case -1:
					params.put("x12a", "");
					params.put("x12b", "");
					params.put("x12c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_headline()) {
				case 1:
					params.put("x13a", "✓");
					params.put("x13b", "");
					params.put("x13c", "");
					break;
				case 0:
					params.put("x13a", "");
					params.put("x13b", "✓");
					params.put("x13c", "");
					break;
				case -1:
					params.put("x13a", "");
					params.put("x13b", "");
					params.put("x13c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_punctuation()) {
				case 1:
					params.put("x14a", "✓");
					params.put("x14b", "");
					params.put("x14c", "");
					break;
				case 0:
					params.put("x14a", "");
					params.put("x14b", "✓");
					params.put("x14c", "");
					break;
				case -1:
					params.put("x14a", "");
					params.put("x14b", "");
					params.put("x14c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_typo()) {
				case 1:
					params.put("x15a", "✓");
					params.put("x15b", "");
					params.put("x15c", "");
					break;
				case 0:
					params.put("x15a", "");
					params.put("x15b", "✓");
					params.put("x15c", "");
					break;
				case -1:
					params.put("x15a", "");
					params.put("x15b", "");
					params.put("x15c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_reference_ten()) {
				case 1:
					params.put("x16a", "✓");
					params.put("x16b", "");
					params.put("x16c", "");
					break;
				case 0:
					params.put("x16a", "");
					params.put("x16b", "✓");
					params.put("x16c", "");
					break;
				case -1:
					params.put("x16a", "");
					params.put("x16b", "");
					params.put("x16c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_reference_foreign()) {
				case 1:
					params.put("x17a", "✓");
					params.put("x17b", "");
					params.put("x17c", "");
					break;
				case 0:
					params.put("x17a", "");
					params.put("x17b", "✓");
					params.put("x17c", "");
					break;
				case -1:
					params.put("x17a", "");
					params.put("x17b", "");
					params.put("x17c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_reference_new()) {
				case 1:
					params.put("x18a", "✓");
					params.put("x18b", "");
					params.put("x18c", "");
					break;
				case 0:
					params.put("x18a", "");
					params.put("x18b", "✓");
					params.put("x18c", "");
					break;
				case -1:
					params.put("x18a", "");
					params.put("x18b", "");
					params.put("x18c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_reference_num()) {
				case 1:
					params.put("x19a", "✓");
					params.put("x19b", "");
					params.put("x19c", "");
					break;
				case 0:
					params.put("x19a", "");
					params.put("x19b", "✓");
					params.put("x19c", "");
					break;
				case -1:
					params.put("x19a", "");
					params.put("x19b", "");
					params.put("x19c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_reference_format()) {
				case 1:
					params.put("x20a", "✓");
					params.put("x20b", "");
					params.put("x20c", "");
					break;
				case 0:
					params.put("x20a", "");
					params.put("x20b", "✓");
					params.put("x20c", "");
					break;
				case -1:
					params.put("x20a", "");
					params.put("x20b", "");
					params.put("x20c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_progress_metaphase()) {
				case 1:
					params.put("x21a", "✓");
					params.put("x21b", "");
					params.put("x21c", "");
					break;
				case 0:
					params.put("x21a", "");
					params.put("x21b", "✓");
					params.put("x21c", "");
					break;
				case -1:
					params.put("x21a", "");
					params.put("x21b", "");
					params.put("x21c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_progress_summary()) {
				case 1:
					params.put("x22a", "✓");
					params.put("x22b", "");
					params.put("x22c", "");
					break;
				case 0:
					params.put("x22a", "");
					params.put("x22b", "✓");
					params.put("x22c", "");
					break;
				case -1:
					params.put("x22a", "");
					params.put("x22b", "");
					params.put("x22c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_progress_actual()) {
				case 1:
					params.put("x23a", "✓");
					params.put("x23b", "");
					params.put("x23c", "");
					break;
				case 0:
					params.put("x23a", "");
					params.put("x23b", "✓");
					params.put("x23c", "");
					break;
				case -1:
					params.put("x23a", "");
					params.put("x23b", "");
					params.put("x23c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_progress_complete()) {
				case 1:
					params.put("x24a", "✓");
					params.put("x24b", "");
					params.put("x24c", "");
					break;
				case 0:
					params.put("x24a", "");
					params.put("x24b", "✓");
					params.put("x24c", "");
					break;
				case -1:
					params.put("x24a", "");
					params.put("x24b", "");
					params.put("x24c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_progress_logic()) {
				case 1:
					params.put("x25a", "✓");
					params.put("x25b", "");
					params.put("x25c", "");
					break;
				case 0:
					params.put("x25a", "");
					params.put("x25b", "✓");
					params.put("x25c", "");
					break;
				case -1:
					params.put("x25a", "");
					params.put("x25b", "");
					params.put("x25c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_chart()) {
				case 1:
					params.put("x26a", "✓");
					params.put("x26b", "");
					params.put("x26c", "");
					break;
				case 0:
					params.put("x26a", "");
					params.put("x26b", "✓");
					params.put("x26c", "");
					break;
				case -1:
					params.put("x26a", "");
					params.put("x26b", "");
					params.put("x26c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_enclosure()) {
				case 1:
					params.put("x27a", "✓");
					params.put("x27b", "");
					params.put("x27c", "");
					break;
				case 0:
					params.put("x27a", "");
					params.put("x27b", "✓");
					params.put("x27c", "");
					break;
				case -1:
					params.put("x27a", "");
					params.put("x27b", "");
					params.put("x27c", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_teacher_opinion()) {
				case 1:
					params.put("x28a", "✓");
					params.put("x28b", "");
					break;
				case 0:
					params.put("x28a", "");
					params.put("x28b", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_leader_opinion()) {
				case 1:
					params.put("x29a", "✓");
					params.put("x29b", "");
					break;
				case 0:
					params.put("x29a", "");
					params.put("x29b", "✓");
					break;
				}
			}
		}
		return params;
	}

	/**
	 * 导出指导老师评价表
	 */
	@Override
	public Map<String, Object> exportTeacherOpin(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_teacher_basic bysjglxt_teacher_basic = new bysjglxt_teacher_basic();
		bysjglxt_evaluate_tutor bysjglxt_evaluate_tutor = new bysjglxt_evaluate_tutor();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("p4", bysjglxt_student_basic.getStudent_basic_num());
				params.put("p5", bysjglxt_student_basic.getStudent_basic_name());
				params.put("p6", bysjglxt_student_basic.getStudent_basic_class());
			} else {
				params.put("p4", "");
				params.put("p5", "");
				params.put("p6", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("p7", bysjglxt_topic.getTopic_name_chinese());
					params.put("p8", bysjglxt_topic.getTopic_name_english());
					// 根据课题指导老师userId 获取教师user表信息
					bysjglxt_teacher_user = graduationProjectManagementDao
							.getTeacherUserByUserId(bysjglxt_topic.getTopic_teacher());
					if (bysjglxt_teacher_user != null) {
						bysjglxt_teacher_basic = graduationProjectManagementDao
								.getTeacherBasicByBasicId(bysjglxt_teacher_user.getUser_teacher_basic());
						if (bysjglxt_teacher_basic != null) {
							params.put("p1", bysjglxt_teacher_basic.getName());
							params.put("p2", bysjglxt_teacher_basic.getProfessional_title());
							params.put("p3", bysjglxt_teacher_basic.getUnit_name());
						}
					} else {
						params.put("p1", "");
						params.put("p2", "");
						params.put("p3", "");
					}

				} else {
					params.put("p1", "");
					params.put("p2", "");
					params.put("p3", "");
					params.put("p7", "");
					params.put("p8", "");
				}
			}
			// 根据学生user ID获得指导教师评价表
			bysjglxt_evaluate_tutor = graduationProjectManagementDao
					.getEvaluateTutor(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_evaluate_tutor != null) {
				params.put("p26", bysjglxt_evaluate_tutor.getEvaluate_tutor_teacher_comment() + "");
				params.put("p9", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_training_objective() + "");
				params.put("p10", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_difficulty() + "");
				params.put("p11", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_workload() + "");
				params.put("p12", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_bind() + "");
				/*params.put("${18g}",
						bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_training_objective()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_difficulty()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_workload()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_bind() + "");*/
				params.put("p13", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_comprehensive() + "");
				params.put("p14", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_reference() + "");
				params.put("p15", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_experimental_design() + "");
				params.put("p16", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_computing() + "");
				params.put("p17", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_foreign_language() + "");
				params.put("p18", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_computer() + "");
				params.put("p19", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_innovate() + "");
				/*params.put("${19g}",
						bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_comprehensive()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_reference()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_experimental_design()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_computing()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_foreign_language()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_computer()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_innovate() + "");*/

				params.put("p20", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_analysis() + "");
				params.put("p21", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_chart() + "");
				params.put("p22", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_instructions() + "");
				params.put("p23", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_practicability() + "");
				params.put("p24", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_normalization() + "");
				/*params.put("${20g}",
						bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_analysis()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_chart()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_instructions()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_practicability()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_normalization() + "");
				params.put("${17g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_total() + "");*/
				params.put("p25", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_total() + "");
				switch (bysjglxt_evaluate_tutor.getEvaluate_tutor_is_teacher_opinion()) {
				case 1:
					params.put("p27", "✓");
					params.put("p28", "");
					break;
				case 0:
					params.put("p27", "");
					params.put("p28", "✓");
					break;
				}
			} else {
				params.put("p9", "");
				params.put("p10", "");
				params.put("p11", "");
				params.put("p12", "");
				params.put("p13", "");
				params.put("p14", "");
				params.put("p15", "");
				params.put("p16", "");
				params.put("p17", "");
				params.put("p18", "");
				params.put("p19", "");
				params.put("p20", "");
				params.put("p21", "");
				params.put("p22", "");
				params.put("p23", "");
				params.put("p24", "");
				params.put("p26", "");
				params.put("p27", "");
				params.put("p28", "");
				params.put("p25", "");
			}
		}else {
			params.put("p26", "");
			params.put("p27", "");
			params.put("p28", "");
			params.put("p25", "");
			params.put("p7", "");
			params.put("p8", "");
			params.put("p9", "");
			params.put("p10", "");
			params.put("p11", "");
			params.put("p12", "");
			params.put("p13", "");
			params.put("p14", "");
			params.put("p15", "");
			params.put("p16", "");
			params.put("p17", "");
			params.put("p18", "");
			params.put("p19", "");
			params.put("p20", "");
			params.put("p21", "");
			params.put("p22", "");
			params.put("p23", "");
			params.put("p24", "");
			params.put("p1", "");
			params.put("p2", "");
			params.put("p3", "");
		}
		return params;
	}

	@Override
	public Map<String, Object> exportReviewOpin(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_teacher_basic bysjglxt_teacher_basic = new bysjglxt_teacher_basic();
		bysjglxt_evaluate_tutor bysjglxt_evaluate_tutor = new bysjglxt_evaluate_tutor();
		bysjglxt_evaluate_review bysjglxt_evaluate_review = new bysjglxt_evaluate_review();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("y4", bysjglxt_student_basic.getStudent_basic_num());
				params.put("y5", bysjglxt_student_basic.getStudent_basic_name());
				params.put("y6", bysjglxt_student_basic.getStudent_basic_class());
			} else {
				params.put("y4", "");
				params.put("y5", "");
				params.put("y6", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("y7", bysjglxt_topic.getTopic_name_chinese());
					params.put("y8", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("y7", "");
					params.put("y8", "");
				}
			}
			// 根据课题评阅老师userId 获取教师user表信息
			bysjglxt_teacher_user = graduationProjectManagementDao
					.getTeacherUserByUserId(bysjglxt_topic_select.getTopic_select_teacher_review());
			if (bysjglxt_teacher_user != null) {
				bysjglxt_teacher_basic = graduationProjectManagementDao
						.getTeacherBasicByBasicId(bysjglxt_teacher_user.getUser_teacher_basic());
				if (bysjglxt_teacher_basic != null) {
					params.put("y1", bysjglxt_teacher_basic.getName());
					params.put("y2", bysjglxt_teacher_basic.getProfessional_title());
					params.put("y3", bysjglxt_teacher_basic.getUnit_name());
				}
			} else {
				params.put("y1", "");
				params.put("y2", "");
				params.put("y3", "");
			}

			// 根据学生user ID获得指导教师评价表
			bysjglxt_evaluate_review = graduationProjectManagementDao
					.getEvaluateReview(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_evaluate_review != null) {
				params.put("y26", bysjglxt_evaluate_review.getEvaluate_review_teacher_comment() + "");
				params.put("y9", bysjglxt_evaluate_review.getEvaluate_review_grade_training_objective() + "");
				params.put("y10", bysjglxt_evaluate_review.getEvaluate_review_grade_difficulty() + "");
				params.put("y11", bysjglxt_evaluate_review.getEvaluate_review_grade_workload() + "");
				params.put("y12", bysjglxt_evaluate_review.getEvaluate_review_grade_bind() + "");
				/*params.put("${18t}",
						bysjglxt_evaluate_review.getEvaluate_review_grade_training_objective()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_difficulty()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_workload()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_bind() + "");*/
				params.put("y13", bysjglxt_evaluate_review.getEvaluate_review_grade_comprehensive() + "");
				params.put("y14", bysjglxt_evaluate_review.getEvaluate_review_grade_reference() + "");
				params.put("y15", bysjglxt_evaluate_review.getEvaluate_review_grade_experimental_design() + "");
				params.put("y16", bysjglxt_evaluate_review.getEvaluate_review_grade_computing() + "");
				params.put("y17", bysjglxt_evaluate_review.getEvaluate_review_grade_foreign_language() + "");
				params.put("y18", bysjglxt_evaluate_review.getEvaluate_review_grade_computer() + "");
				params.put("y19", bysjglxt_evaluate_review.getEvaluate_review_grade_innovate() + "");
				/*params.put("${19t}",
						bysjglxt_evaluate_review.getEvaluate_review_grade_comprehensive()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_reference()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_experimental_design()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_computing()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_foreign_language()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_computer()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_innovate() + "");*/

				params.put("y20", bysjglxt_evaluate_review.getEvaluate_review_grade_analysis() + "");
				params.put("y21", bysjglxt_evaluate_review.getEvaluate_review_grade_chart() + "");
				params.put("y22", bysjglxt_evaluate_review.getEvaluate_review_grade_instructions() + "");
				params.put("y23", bysjglxt_evaluate_review.getEvaluate_review_grade_practicability() + "");
				params.put("y24", bysjglxt_evaluate_review.getEvaluate_review_grade_normalization() + "");
				/*params.put("${20t}",
						bysjglxt_evaluate_review.getEvaluate_review_grade_analysis()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_chart()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_instructions()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_practicability()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_normalization() + "");*/
				params.put("y25", bysjglxt_evaluate_review.getEvaluate_review_grade_total() + "");
//				params.put("${21t}", bysjglxt_evaluate_review.getEvaluate_review_grade_total() + "");
				switch (bysjglxt_evaluate_review.getEvaluate_review_is_teacher_opinion()) {
				case 1:
					params.put("y27", "✓");
					params.put("y28", "");
					break;
				case 0:
					params.put("y27", "");
					params.put("y28", "✓");
					break;
				}
			} else {
				params.put("y9", "");
				params.put("y10", "");
				params.put("y11", "");
				params.put("y12", "");
				params.put("y13", "");
				params.put("y14", "");
				params.put("y15", "");
				params.put("y16", "");
				params.put("y17", "");
				params.put("y18", "");
				params.put("y19", "");
				params.put("y20", "");
				params.put("y21", "");
				params.put("y22", "");
				params.put("y23", "");
				params.put("y24", "");
				params.put("y25", "");
				params.put("y26", "");
				params.put("y27", "");
				params.put("y28", "");
			}
		}else {
			params.put("y1", "");
			params.put("y2", "");
			params.put("y3", "");
			params.put("y4", "");
			params.put("y5", "");
			params.put("y6", "");
			params.put("y7", "");
			params.put("y8", "");
			params.put("y9", "");
			params.put("y10", "");
			params.put("y11", "");
			params.put("y12", "");
			params.put("y13", "");
			params.put("y14", "");
			params.put("y15", "");
			params.put("y16", "");
			params.put("y17", "");
			params.put("y18", "");
			params.put("y19", "");
			params.put("y20", "");
			params.put("y21", "");
			params.put("y22", "");
			params.put("y23", "");
			params.put("y24", "");
			params.put("y25", "");
			params.put("y26", "");
			params.put("y27", "");
			params.put("y28", "");
		}
		return params;
	}

	@Override
	public Map<String, Object> exportDefence(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("d1", bysjglxt_student_basic.getStudent_basic_num());
				params.put("d2", bysjglxt_student_basic.getStudent_basic_name());
				params.put("d3", bysjglxt_student_basic.getStudent_basic_class());
				int sessional = Integer.parseInt(bysjglxt_student_basic.getStudent_basic_level());
				sessional = sessional + 4;
				params.put("d5", sessional + "");
			} else {
				params.put("d1", "");
				params.put("d2", "");
				params.put("d3", "");
				params.put("d5", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("d4", bysjglxt_topic.getTopic_num());
					params.put("d6", bysjglxt_topic.getTopic_name_chinese());
					params.put("d7", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("d4", "");
					params.put("d6", "");
					params.put("d7", "");
				}
			}
			bysjglxt_defence = graduationProjectManagementDao.getDefence(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_defence != null) {
				params.put("d8", bysjglxt_defence.getDefence_chenshu_time());
				params.put("d9", bysjglxt_defence.getDefence_tiwen_time());
				params.put("d10", bysjglxt_defence.getDefence_record());
				params.put("d11", bysjglxt_defence.getDefence_leader_comment());

				params.put("d12", bysjglxt_defence.getDefence_grade_writing() + "");
				params.put("d13", bysjglxt_defence.getDefence_grade_normalization() + "");
				params.put("d14", bysjglxt_defence.getDefence_grade_complete() + "");
				params.put("d15", bysjglxt_defence.getDefence_grade_technology() + "");
				params.put("d16", bysjglxt_defence.getDefence_grade_practicability() + "");
				/*params.put("${18z}",
						bysjglxt_defence.getDefence_grade_writing() + bysjglxt_defence.getDefence_grade_normalization()
								+ bysjglxt_defence.getDefence_grade_complete()
								+ bysjglxt_defence.getDefence_grade_technology()
								+ bysjglxt_defence.getDefence_grade_practicability() + "");*/
				params.put("d17", bysjglxt_defence.getDefence_grade_appearance() + "");
				params.put("d18", bysjglxt_defence.getDefence_grade_statement() + "");
				params.put("d19", bysjglxt_defence.getDefence_grade_answer() + "");
				/*params.put("${19z}",
						bysjglxt_defence.getDefence_grade_statement() + bysjglxt_defence.getDefence_grade_appearance()
								+ bysjglxt_defence.getDefence_grade_answer() + "");*/

//				params.put("${17z}", bysjglxt_defence.getDefence_grade_defence() + "");
				params.put("d20", bysjglxt_defence.getDefence_total() + "");
				params.put("d21", bysjglxt_defence.getDefence_grade_evaluate_tutor() + "");
				params.put("d22", bysjglxt_defence.getDefence_grade_evaluate_review() + "");
				params.put("d23", bysjglxt_defence.getDefence_total() + "");
				params.put("d24", bysjglxt_defence.getDefence_finally() + "");
			} else {
				params.put("d8", "");
				params.put("d10", "");
				params.put("d11", "");
				params.put("d12", "");
				params.put("d13", "");
				params.put("d14", "");
				params.put("d15", "");
				params.put("d16", "");
				params.put("d17", "");
				params.put("d18", "");
				params.put("d19", "");
				params.put("d20", "");
				params.put("d21", "");
				params.put("d22", "");
				params.put("d23", "");
				params.put("d24", "");
				
			}
		}else {
			params.put("d1", "");
			params.put("d2", "");
			params.put("d3", "");
			params.put("d4", "");
			params.put("d5", "");
			params.put("d6", "");
			params.put("d7", "");
			params.put("d8", "");
			params.put("d9", "");
			params.put("d10", "");
			params.put("d11", "");
			params.put("d12", "");
			params.put("d13", "");
			params.put("d14", "");
			params.put("d15", "");
			params.put("d16", "");
			params.put("d17", "");
			params.put("d18", "");
			params.put("d19", "");
			params.put("d20", "");
			params.put("d21", "");
			params.put("d22", "");
			params.put("d23", "");
			params.put("d24", "");
		}
		return params;
	}

}