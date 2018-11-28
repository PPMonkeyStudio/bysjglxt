package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_comment;
import com.bysjglxt.domain.DO.bysjglxt_defence;
import com.bysjglxt.domain.DO.bysjglxt_dissertation;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;
import com.bysjglxt.domain.DO.bysjglxt_taskbook;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.VO.CommentInformationVO;
import com.bysjglxt.domain.VO.TeacherTutorStudentVO;

public interface GraduationProjectManagementDao {

	public int saveObj(Object obj);

	public int fillEmptyInTaskBook(bysjglxt_taskbook bysjglxt_taskbook);

	public int fillEmptyInOpening(bysjglxt_report_opening bysjglxt_report_opening);

	public int fillEmptyInProgressEarlystage(bysjglxt_record_progress bysjglxt_record_progressEarlystage);

	public int fillEmptyInSummary(bysjglxt_summary bysjglxt_summary);

	public int fillEmptyInExaminationFormal(bysjglxt_examination_formal bysjglxt_examination_formal);

	public int fillEmptyInEvaluateTutor(bysjglxt_evaluate_tutor bysjglxt_evaluate_tutor);

	public int fillEmptyEvaluateReview(bysjglxt_evaluate_review bysjglxt_evaluate_review);

	public int fillEmptyDefence(bysjglxt_defence bysjglxt_defence);

	public com.bysjglxt.domain.DO.bysjglxt_taskbook getTaskbookById(String taskbook_id);

	public com.bysjglxt.domain.DO.bysjglxt_report_opening getReportOpening(String report_opening_id);

	public bysjglxt_record_progress getRecordProgress(String record_progress_id);

	public com.bysjglxt.domain.DO.bysjglxt_record_progress findRecordProgressByuserIdAndStage(
			String report_opening_student, String string);

	public com.bysjglxt.domain.DO.bysjglxt_summary findSummaryByUserId(String record_progress_id);

	public com.bysjglxt.domain.DO.bysjglxt_summary findSummaryById(String summary_id);

	public com.bysjglxt.domain.DO.bysjglxt_examination_formal findExaminationFormalById(String examination_formal_id);

	public com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor findEvaluateTutor(String evaluate_tutor_id);

	public com.bysjglxt.domain.DO.bysjglxt_evaluate_review findEvaluateReviewById(String evaluate_review_id);

	public bysjglxt_defence findDefenceById(String defence_id);

	public bysjglxt_defence findDefenceByUserId(String evaluate_tutor_student);

	public com.bysjglxt.domain.DO.bysjglxt_taskbook getTaskBookByUserId(String userId);

	public com.bysjglxt.domain.DO.bysjglxt_record_progress getRecordProgress(String userId, String string);

	public com.bysjglxt.domain.DO.bysjglxt_summary getSummary(String userId);

	public com.bysjglxt.domain.DO.bysjglxt_examination_formal getExaminationFormal(String userId);

	public com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor getEvaluateTutor(String userId);

	public com.bysjglxt.domain.DO.bysjglxt_evaluate_review getEvaluateReview(String userId);

	public com.bysjglxt.domain.DO.bysjglxt_defence getDefence(String userId);

	public com.bysjglxt.domain.DO.bysjglxt_report_opening getReportOpeningUser(String userId);

	/***************************************
	 * 导出
	 *******************************************/

	public com.bysjglxt.domain.DO.bysjglxt_student_user getStudentUserByUserId(String studentUserId);

	public com.bysjglxt.domain.DO.bysjglxt_student_basic getStudentBasicByBasicId(String user_student_basic);

	public com.bysjglxt.domain.DO.bysjglxt_topic_select getStudentSelectTopic(String user_student_id);

	public bysjglxt_teacher_user getTeacherUserByUserId(String topic_select_teacher_tutor);

	public bysjglxt_teacher_basic getTeacherBasicByBasicId(String user_teacher_basic);

	public com.bysjglxt.domain.DO.bysjglxt_topic getStudentTopicByTopicId(String topic_select_topic);

	public List<bysjglxt_topic_select> getTeacherTutorStudentSelectTopicByPage(
			TeacherTutorStudentVO teacherTutorStudentVO, String teacherUserId, String actor, String section,
			String college);

	public List<bysjglxt_process_instance> getProcessInstanceByMan(String topic_select_student);

	public com.bysjglxt.domain.DO.bysjglxt_process_definition getProcessDefinitionByID(
			String process_instance_process_definition);

	public bysjglxt_task_instance getTaskInstanceByProcessInstanceId(String process_instance_id);

	public bysjglxt_task_definition getTaskDefinition(String task_instance_task_definition);

	public List<bysjglxt_topic_select> getTeacherTutorStudentAllSelectTopic(TeacherTutorStudentVO teacherTutorStudentVO,
			String teacherUserId, String actor, String section, String college);

	public com.bysjglxt.domain.DO.bysjglxt_section getSectionByUserId(String teacherUserId);

	public com.bysjglxt.domain.DO.bysjglxt_dissertation getThesisByStudent(String userId);

	public boolean deleteThesisByUserId(String userId);

	public boolean fillEmptyThesisRecord(bysjglxt_dissertation bysjglxt_dissertation);

	public List<bysjglxt_topic_select> getSelectTopicByTutorId(String teacherUserId);

	public int fillEmptyInDissertation(bysjglxt_dissertation bysjglxt_dissertation);

	public com.bysjglxt.domain.DO.bysjglxt_dissertation getThesisByStudentId(String userId);

	public bysjglxt_student_basic getStudentBasicByUserId(String userId);

	public List<bysjglxt_comment> getListAllCommentInformation(CommentInformationVO commentInformationVO,
			String college, int i);

	// 根据Id获取评语
	public com.bysjglxt.domain.DO.bysjglxt_comment getCommentById(String comment_id);

	// 删除
	public void deleteCommentById(String comment_id);

	// 获取评语list
	public List<bysjglxt_comment> getListCommentByGradeAndCategory(String commentCategory, String grade);

	public boolean deleteReportOpeningFileByUserId(String userId);

	/**
	 * 
	 * @param taskName
	 * @param userId
	 * @return
	 */
	public bysjglxt_task_instance getTaskInstance(String taskName, String userId);

}
