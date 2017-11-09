package com.bysjglxt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bysjglxt.dao.GraduationProjectManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_defence;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
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
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TaskDTO;
import com.bysjglxt.domain.DTO.TeacherTutorStudentDTO;
import com.bysjglxt.domain.VO.TeacherTutorStudentVO;
import com.bysjglxt.service.GraduationProjectManagementService;

import util.TeamUtil;

public class GraduationProjectManagementServiceImpl implements GraduationProjectManagementService {

	private GraduationProjectManagementDao graduationProjectManagementDao;

	public void setGraduationProjectManagementDao(GraduationProjectManagementDao graduationProjectManagementDao) {
		this.graduationProjectManagementDao = graduationProjectManagementDao;
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
	public int updateStudentTaskbook(bysjglxt_taskbook updateTaskbook) {
		int flag = 2;
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		bysjglxt_taskbook = graduationProjectManagementDao.getTaskbookById(updateTaskbook.getTaskbook_id());
		if (bysjglxt_taskbook != null) {
			bysjglxt_taskbook.setTaskbook_acontent_required(updateTaskbook.getTaskbook_acontent_required());
			bysjglxt_taskbook.setTaskbook_reference(updateTaskbook.getTaskbook_reference());
			bysjglxt_taskbook.setTaskbook_plan(updateTaskbook.getTaskbook_plan());
			bysjglxt_taskbook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInTaskBook(bysjglxt_taskbook);
		}
		return flag;
	}

	/**
	 * 教研室主任更改任务书
	 */
	@Override
	public int updateTeacherTaskbook(bysjglxt_taskbook updateTaskbook) {
		int flag = 2;
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		bysjglxt_taskbook = graduationProjectManagementDao.getTaskbookById(updateTaskbook.getTaskbook_id());
		if (bysjglxt_taskbook != null) {
			bysjglxt_taskbook.setTaskbook_opinion(updateTaskbook.getTaskbook_plan());
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
			bysjglxt_report_opening
					.setReport_opening_documentary_survey(updateReportOpening.getReport_opening_documentary_survey());
			bysjglxt_report_opening.setReport_opening_main(updateReportOpening.getReport_opening_main());
			bysjglxt_report_opening.setReport_opening_detail(updateReportOpening.getReport_opening_detail());
			bysjglxt_report_opening.setReport_opening_reference(updateReportOpening.getReport_opening_reference());
			bysjglxt_report_opening.setReport_opening_plan(updateReportOpening.getReport_opening_plan());
			bysjglxt_report_opening
					.setReport_opening_gmt_modified(updateReportOpening.getReport_opening_gmt_modified());
			flag = graduationProjectManagementDao.fillEmptyInOpening(bysjglxt_report_opening);
		}
		return flag;
	}

	/**
	 * 学生更改前期情况记录
	 */
	@Override
	public int updateStudentRecordProgressEarlystage(bysjglxt_record_progress updateRecordProgress) {
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
		return flag;
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
	 */
	@Override
	public int updateStudentRecordProgressMetaphase(bysjglxt_record_progress updateRecordProgress) {
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
		return flag;
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
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_is_teacher_opinion(updateEvaluateTutor.getEvaluate_tutor_is_teacher_opinion());
			bysjglxt_evaluate_tutor.setEvaluate_tutor_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInEvaluateTutor(bysjglxt_evaluate_tutor);

			if (flag != 2) {
				// 根据得到的答辩评分表将指导教师评分占比填写进去
				bysjglxt_defence = graduationProjectManagementDao
						.findDefenceByUserId(bysjglxt_evaluate_tutor.getEvaluate_tutor_student());
				bysjglxt_defence
						.setDefence_grade_evaluate_tutor(bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_total() * 0.3);
				System.out.println(bysjglxt_defence.getDefence_grade_evaluate_tutor());
				bysjglxt_defence.setDefence_gmt_modified(TeamUtil.getStringSecond());
				flag = graduationProjectManagementDao.fillEmptyDefence(bysjglxt_defence);
			}
		}
		return flag;
	}

	@Override
	public int bysjglxt_evaluate_review(bysjglxt_evaluate_review updateEvaluateReview) {
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
			bysjglxt_evaluate_review.setEvaluate_review_is_teacher_opinion(
					updateEvaluateReview.getEvaluate_review_is_teacher_opinion());
			bysjglxt_evaluate_review.setEvaluate_review_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyEvaluateReview(bysjglxt_evaluate_review);
			if (flag != 2) {
				bysjglxt_defence = graduationProjectManagementDao
						.findDefenceByUserId(bysjglxt_evaluate_review.getEvaluate_review_student());
				bysjglxt_defence.setDefence_grade_evaluate_tutor(
						bysjglxt_evaluate_review.getEvaluate_review_grade_total() * 0.3);
				System.out.println(bysjglxt_defence.getDefence_grade_evaluate_review());
				bysjglxt_defence.setDefence_gmt_modified(TeamUtil.getStringSecond());
				flag = graduationProjectManagementDao.fillEmptyDefence(bysjglxt_defence);
			}

		}
		return flag;
	}

	@Override
	public int bysjglxt_defence(bysjglxt_defence updateDefence) {
		int flag = 2;
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_defence = graduationProjectManagementDao.findDefenceById(updateDefence.getDefence_id());
		String grade = "";
		if (bysjglxt_defence != null) {
			bysjglxt_defence.setDefence_record(updateDefence.getDefence_record());
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
			bysjglxt_defence
					.setDefence_total((int) (Math.round((bysjglxt_defence.getDefence_grade_evaluate_tutor() * 0.4
							+ bysjglxt_defence.getDefence_grade_evaluate_review()
							+ bysjglxt_defence.getDefence_grade_evaluate_tutor()) + 0.5)));
			/**
			 * 成绩总评还没有写
			 */

			bysjglxt_defence.setDefence_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyDefence(bysjglxt_defence);
		}
		return flag;
	}

	/******************************** 下面是我的毕业设计需要 ***************************************/

	@Override
	public bysjglxt_taskbook get_TaskBook(String userId) {
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		bysjglxt_taskbook = graduationProjectManagementDao.getTaskBookByUserId(userId);
		return bysjglxt_taskbook;
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
		bysjglxt_record_progress = graduationProjectManagementDao.getRecordProgress(userId, "完善期");
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
	public void exportAll() {

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
				params.put("${coverStudentNum}", bysjglxt_student_basic.getStudent_basic_num());
				params.put("${coverStudentName}", bysjglxt_student_basic.getStudent_basic_name());
				// 届别
				params.put("${coverSessional}", sessional + "");
				params.put("${coverMajor}", bysjglxt_student_basic.getStudent_basic_major());
			} else {
				params.put("${coverStudentNum}", "");
				params.put("${coverStudentName}", "");
				params.put("${coverSessional}", "");
				params.put("${coverMajor}", "");

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
				params.put("${coverTutorName}", bysjglxt_teacher_basic_tutor.getName());
				params.put("${coverTutorTitle}", bysjglxt_teacher_basic_tutor.getProfessional_title());
				if (bysjglxt_topic_select.getTopic_select_teacher_review() != null
						&& bysjglxt_topic_select.getTopic_select_teacher_review().trim().length() > 0) {
					// 根据老师userId获得评阅老师user表
					bysjglxt_teacher_user_evaluate = graduationProjectManagementDao
							.getTeacherUserByUserId(bysjglxt_topic_select.getTopic_select_teacher_review());
					// 根据老师basicid获得评阅老师basic表
					bysjglxt_teacher_basic_evaluate = graduationProjectManagementDao
							.getTeacherBasicByBasicId(bysjglxt_teacher_user_evaluate.getUser_teacher_id());
					params.put("${coverEvaluateName}", bysjglxt_teacher_basic_evaluate.getName());
					params.put("${coverEvaluateTitle}", bysjglxt_teacher_basic_evaluate.getProfessional_title());
				} else {
					params.put("${coverEvaluateName}", "");
					params.put("${coverEvaluateTitle}", "");
				}
			} else {
				params.put("${coverTutorName}", "");
				params.put("${coverTutorTitle}", "");
				params.put("${coverEvaluateName}", "");
				params.put("${coverEvaluateTitle}", "");
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
				params.put("${taskNum}", bysjglxt_student_basic.getStudent_basic_num());
				params.put("${taskNam}", bysjglxt_student_basic.getStudent_basic_name());
				params.put("${taskMajor}", bysjglxt_student_basic.getStudent_basic_major());
			} else {
				params.put("${taskNum}", "");
				params.put("${taskNam}", "");
				params.put("${taskMajor}", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("${taskChineseName}", bysjglxt_topic.getTopic_name_chinese());
					params.put("${taskEnglishName}", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("${taskChineseName}", "");
					params.put("${taskEnglishName}", "");
				}
			}
			// 根据user Id获取任务书表
			bysjglxt_taskbook = graduationProjectManagementDao
					.getTaskBookByUserId(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_taskbook != null) {
				params.put("${taskRequired}", bysjglxt_taskbook.getTaskbook_acontent_required());
				params.put("${taskReference}", bysjglxt_taskbook.getTaskbook_reference());
				params.put("${taskPlan}", bysjglxt_taskbook.getTaskbook_plan());
				params.put("${taskOpinion}", bysjglxt_taskbook.getTaskbook_opinion());
			} else {
				params.put("${taskRequired}", "");
				params.put("${taskReference}", "");
				params.put("${taskPlan}", "");
				params.put("${taskOpinion}", "");
			}
		}

		return params;
	}
	@Override
	public TeacherTutorStudentVO teacherTutorStudentVO(TeacherTutorStudentVO teacherTutorStudentVO,
			String teacherUserId) {
		List<TeacherTutorStudentDTO> list_TeacherTutorStudentDTO = new ArrayList<TeacherTutorStudentDTO>();
		TeacherTutorStudentDTO teacherTutorStudentDTO = new TeacherTutorStudentDTO();
		TaskDTO taskDTO = new TaskDTO();
		bysjglxt_task_definition taskDefinition = new bysjglxt_task_definition();
		bysjglxt_task_instance taskInstance = new bysjglxt_task_instance();
		bysjglxt_student_basic bysjglxtStudentBasic = new bysjglxt_student_basic();
		bysjglxt_student_user bysjglxtStudentUser = new bysjglxt_student_user();
		bysjglxt_topic bysjglxtTopic = new bysjglxt_topic();
		StudentInformationDTO studentInformationDTO = new StudentInformationDTO();
		List<bysjglxt_process_instance> listProcessInstance = new ArrayList<bysjglxt_process_instance>();
		bysjglxt_process_definition bysjglxt_process_definition = new bysjglxt_process_definition();
		bysjglxt_process_instance processInstance = new bysjglxt_process_instance();
		List<bysjglxt_topic_select> list_bysjglxt_topic_select = new ArrayList<bysjglxt_topic_select>();
		List<bysjglxt_topic_select> list_Allbysjglxt_topic_select = new ArrayList<bysjglxt_topic_select>();
		// 获得总记录数
		list_Allbysjglxt_topic_select = graduationProjectManagementDao
				.getTeacherTutorStudentAllSelectTopic(teacherTutorStudentVO, teacherUserId);
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
		list_bysjglxt_topic_select = graduationProjectManagementDao
				.getTeacherTutorStudentSelectTopicByPage(teacherTutorStudentVO, teacherUserId);
		System.out.println(list_bysjglxt_topic_select.size());
		// 2.遍历选题拿到学生userId信息
		for (bysjglxt_topic_select bysjglxt_topic_select : list_bysjglxt_topic_select) {
			taskDefinition = new bysjglxt_task_definition();
			bysjglxtStudentUser = new bysjglxt_student_user();
			bysjglxtStudentBasic = new bysjglxt_student_basic();
			taskInstance = new bysjglxt_task_instance();
			teacherTutorStudentDTO = new TeacherTutorStudentDTO();
			processInstance = new bysjglxt_process_instance();
			bysjglxtTopic = new bysjglxt_topic();
			// 3.根据选题所属学生拿到学生user表
			bysjglxtStudentUser = graduationProjectManagementDao
					.getStudentUserByUserId(bysjglxt_topic_select.getTopic_select_student());
			if (bysjglxtStudentUser != null) {
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
				System.out.println("学生user表为空");
			}
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

}
