package com.bysjglxt.service;

import java.util.Map;

import com.bysjglxt.domain.DO.bysjglxt_defence;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_taskbook;
import com.bysjglxt.domain.VO.TeacherTutorStudentVO;

public interface GraduationProjectManagementService {

	/**
	 * @说明 学生点击开启开始毕业设计流程 1、创建个人毕业设计过程管理手册中所有信息记录
	 * 
	 * @DATE 2017-11-03
	 * @param studentId
	 * @return 1.创建成功 2.创建失败
	 * 
	 */
	public int startGraduationProjectProcess(String studentId);

	/**
	 * 
	 * 点击我的毕业设计
	 * 
	 */

	/**
	 * @说明 学生更改任务书
	 * @param bysjglxt_taskbook
	 * @return 1.创建成功 2.创建失败
	 */
	public int updateStudentTaskbook(bysjglxt_taskbook updateTaskbook);

	/**
	 * @说明 教师更改任务书
	 * @param bysjglxt_taskbook
	 * @return 1.创建成功 2.创建失败
	 */
	public int updateTeacherTaskbook(bysjglxt_taskbook updateTaskbook);

	/**
	 * @说明 更改开题报告表
	 * @param updateReportOpening
	 * @return 1.成功 2.失败
	 */
	public int updateReportOpening(bysjglxt_report_opening updateReportOpening);

	/**
	 * @说明 学生更改前期进展情况记录
	 * @param updateRecordProgress
	 * @return 1.成功 2.失败
	 */
	public int updateStudentRecordProgressEarlystage(bysjglxt_record_progress updateRecordProgress);

	/**
	 * @说明 教师更改前期进展情况记录
	 * @param updateRecordProgress
	 * @return 1.成功 2.失败
	 */
	public int updateTeacherRecordProcessEarlystage(bysjglxt_record_progress updateRecordProgress);

	/**
	 * @说明 学生更改中期进展情况记录
	 * @param updateRecordProgress
	 * @return
	 */
	public int updateStudentRecordProgressMetaphase(bysjglxt_record_progress updateRecordProgress);

	/**
	 * @说明 教师更改中期进展情况记录
	 * @param updateRecordProgress
	 * @return
	 */
	public int updateTeacherRecordProgressMetaphase(bysjglxt_record_progress updateRecordProgress);

	/**
	 * @说明 学生更改后期进展情况记录
	 * @param updateRecordProgress
	 * @return
	 */
	public int updateStudentRecordProgressLaterstage(bysjglxt_record_progress updateRecordProgress);

	/**
	 * @说明 教师更改后期进展情况记录
	 * @param updateRecordProgress
	 * @return
	 */
	public int updateTeacherRecordProgressLaterstage(bysjglxt_record_progress updateRecordProgress);

	/**
	 * @说明 学生更改完善期进展情况记录
	 * @param updateRecordProgress
	 * @return
	 */
	public int updateStudentRecordProgressPerfect(bysjglxt_record_progress updateRecordProgress);

	/**
	 * @说明 教师更改完善期进展情况记录
	 * @param updateRecordProgress
	 * @return
	 */
	public int updateTeacherRecordProgressPerfect(bysjglxt_record_progress updateRecordProgress);

	/**
	 * @说明 学生更改个人学习工作总结
	 * @param bysjglxt_summary
	 * @return
	 */
	public int updateStudentSummary(bysjglxt_summary bysjglxt_summary);

	/**
	 * @说明 教师更改个人学习工作总结
	 * @param bysjglxt_summary
	 * @return
	 */
	public int updateTeacherSummary(bysjglxt_summary bysjglxt_summary);

	/**
	 * @说明 指导教师更改形式审查表 是 1 否 2 无 3
	 * @param updateExaminationFormal
	 * @return
	 */
	public int updateTeacherExaminationFormal(bysjglxt_examination_formal updateExaminationFormal);

	/**
	 * @说明 领导小组长更改形式审查表 是 1 否 2 无 3
	 * @param updateExaminationFormal
	 * @return
	 */
	public int updateLeaderExaminationFormal(bysjglxt_examination_formal updateExaminationFormal);

	/**
	 * @说明 更改指导教师评价表
	 * @param updateEvaluateTutor
	 * @return
	 */
	public int updateEvaluateTutor(bysjglxt_evaluate_tutor updateEvaluateTutor);

	/**
	 * @说明 更改评阅教师评阅表
	 * @param updateEvaluateReview
	 * @return
	 */
	public int bysjglxt_evaluate_review(bysjglxt_evaluate_review updateEvaluateReview);

	/**
	 * @说明 更改答辩评分以及成绩评定表
	 * @param updateDefence
	 * @return
	 */
	public int bysjglxt_defence(bysjglxt_defence updateDefence);

	/*********************************************** 教师点击我指导的毕业设计 ************************/
	public TeacherTutorStudentVO teacherTutorStudentVO(TeacherTutorStudentVO teacherTutorStudentVO,
			String teacherUserId);

	/**********************************
	 * 下面是我的毕业设计里面需要的对象,此处需要一个一个的给
	 **********************************/

	// 1.获取任务书
	public bysjglxt_taskbook get_TaskBook(String userId);

	// 2.获取开题报告
	public bysjglxt_report_opening get_ReportOpening(String userId);

	// 3.获取前期情况记录
	public bysjglxt_record_progress get_RecordProgress_1(String userId);

	// 4.获取中期情况记录
	public bysjglxt_record_progress get_RecordProgress_2(String userId);

	// 5.获取后期情况记录
	public bysjglxt_record_progress get_RecordProgress_3(String userId);

	// 6.获取完善期情况记录
	public bysjglxt_record_progress get_RecordProgress_4(String userId);

	// 7.获取个人学习工作总结
	public bysjglxt_summary get_Summary(String userId);

	// 8.获取形式审查表
	public bysjglxt_examination_formal get_ExaminationFormal(String userId);

	// 8.获取指导教师评价表
	public bysjglxt_evaluate_tutor get_EvaluateTutor(String userId);

	// 9.获取评阅老师评价表
	public bysjglxt_evaluate_review get_EvaluateReview(String userId);

	// 10.获取答辩评分及成绩评定表
	public bysjglxt_defence get_Defence(String userId);

	/************************************ 下面是导出word的实现 ****************************************************/
	// 导出所有
	public void exportAll();

	/**
	 * 1.导出封面
	 * 
	 * @D 1.${coverStudentNum} 学号
	 * @D 2.${coverStudentName}姓名
	 * @D 3.${coverSessional} 届别
	 * @D 4.${coverMajor} 专业班级
	 * @D 5.${coverTutorName} ${coverTutorTitle}指导老师姓名及职称
	 * @D 6. ${coverEvaluateName} ${coverEvaluateTitle}评阅教师姓名及职称
	 * 
	 * @D 条件:学生userId 需要取到的对象
	 * @D bysjglxt_student_user
	 * @D bysjglxt_student_basic
	 * @D bysjglxt_topic_select
	 * @D bysjglxt_teacher_user
	 * @D bysjglxt_teacher_basic
	 */

	public Map<String, Object> exportCover(String studentUserId);

	/**
	 * 导出任务书
	 * 
	 * @D ${taskNum} 学号
	 * @D ${taskNam} 姓名
	 * @D ${taskMajor} 专业班级
	 * @D ${taskChineseName} 课题中文题目
	 * @D ${taskEnglishName} 课题英文题目
	 * @D ${taskRequired} 研究主要内容以及基本要求
	 * @D ${taskReference} 主要参考资料
	 * @D ${taskPlan} 进程计划
	 * @D ${taskOpinion} 教研室主任审核意见
	 * @D 条件:学生userId 需要取到的对象
	 * 
	 * @D bysjglxt_student_user
	 * @D bysjglxt_student_basic
	 * @D bysjglxt_taskbook
	 * 
	 */
	public Map<String, Object> exportTask(String studentUserId);

	/**
	 * 导出开题报告
	 * 
	 * @D
	 * 
	 */

}
