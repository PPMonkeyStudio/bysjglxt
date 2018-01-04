package com.bysjglxt.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.bysjglxt.domain.DO.bysjglxt_defence;
import com.bysjglxt.domain.DO.bysjglxt_dissertation;
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
	 * 上传毕业论文 弃用
	 * 
	 * @param file
	 * @param thesisName
	 * @return -1 上传过后删除失败
	 * @return -2存储失败
	 * @return 1 成功
	 * @throws IOException
	 */
	public int uploadDissertation(String userId, File file, String thesisName) throws IOException;

	/**
	 * 下载毕业论文
	 * 
	 * @param userId
	 * @return
	 */
	public File downloadDissertation(String userId);

	/**
	 * @D 保存毕业论文
	 * @D 1.新：空 file
	 * @D 2.旧：空 fileName
	 * @D 3.userId
	 * @D 4.判断新文件是否存在
	 * @QD 5.存在
	 * @QP 6.判断是否存在旧文件
	 * @QWD 7.存在
	 * @QWD 8.进行删除
	 * @QWD.9.不存在
	 * @QP 10直接上传新文件
	 * @QD 11.不存在
	 * @ED 12.判断旧文件名字是否为空
	 * @RD 13.为空
	 * @RD 14.根据用户名查找是否有毕业论文已经存在
	 * @RD 15.存在
	 * @RD 16.删除旧文件
	 * @RD 17.不存在
	 * @RD 18.不进行操作
	 * @RD 19.不为空
	 * @RD 20.不进行操作
	 * @param file
	 *            新文件
	 * @param oldFileName
	 *            旧文件名
	 * @param userId
	 *            学生userId
	 * @param newFileName
	 *            新文件名
	 * @return
	 * @throws IOException
	 */
	public int saveDissertation(File file, String oldFileName, String userId, String newFileName) throws IOException;

	/**
	 * @说明 获取毕业论文表的对象，
	 * @author ZB
	 * @param userId
	 * @return
	 */
	public bysjglxt_dissertation get_Dissertation(String userId);

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
	 * @说明 教师更改任务书
	 * @param bysjglxt_taskbook
	 * @return 1.创建成功 2.创建失败
	 */
	public int updateTeacherTaskbook(bysjglxt_taskbook updateTaskbook);

	/**
	 * @说明 教研室更改任务书
	 * @param bysjglxt_taskbook
	 * @return 1.创建成功 2.创建失败
	 */
	public int updateSectionTaskbook(bysjglxt_taskbook updateTaskbook);

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
	public int updateEvaluateReview(bysjglxt_evaluate_review updateEvaluateReview);

	/**
	 * @说明 更改答辩评分以及成绩评定表
	 * @param updateDefence
	 * @return
	 */
	public int updateDefence(bysjglxt_defence updateDefence);

	/**
	 * 记录员填写的部分
	 */
	public int updateDefenceRecorder(bysjglxt_defence updateDefence);

	/**
	 * 这里是教师指导的毕业设计
	 * 
	 * 
	 * @param teacherTutorStudentVO
	 * @param teacherUserId
	 * @return
	 */
	public TeacherTutorStudentVO teacherTutorStudentVO(TeacherTutorStudentVO teacherTutorStudentVO,
			String teacherUserId);

	/**
	 * 
	 * 这里是我管理的毕业设计
	 * 
	 */
	public TeacherTutorStudentVO teacherManagementStudentVO(TeacherTutorStudentVO teacherTutorStudentVO,
			String teacherUserId);

	/**********************************
	 * 
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
	public File exportAll(List<String> userId) throws Exception;

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
	 */
	public Map<String, Object> exportOpeningReport(String studentUserId);

	/**
	 * 导出进展前期情况记录
	 */
	public Map<String, Object> exportEarlystage(String studentUserId);

	/**
	 * 导出中期情况记录
	 * 
	 */
	public Map<String, Object> exportMetaphase(String studentUserId);

	/**
	 * 导出后期情况记录
	 * 
	 */
	public Map<String, Object> exportLaterstage(String studentUserId);

	/**
	 * 导出完善期情况记录
	 */

	public Map<String, Object> exportPerfect(String studentUserId);

	/**
	 * 导出个人学习工作总结
	 */
	public Map<String, Object> exportSummary(String studentUserId);

	/**
	 * 导出形式审查表
	 */
	public Map<String, Object> exportFormal(String studentUserId);

	/**
	 * 导出指导老师评价表
	 */
	public Map<String, Object> exportTeacherOpin(String studentUserId);

	/**
	 * 导出评阅老师评价表
	 */

	public Map<String, Object> exportReviewOpin(String studentUserId);

	/**
	 * 导出答辩评分表
	 */
	public Map<String, Object> exportDefence(String studentUserId);

}
