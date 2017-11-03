package com.bysjglxt.service.impl;

import com.bysjglxt.dao.GraduationProjectManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_defence;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_taskbook;
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
		bysjglxt_record_progressPerfect.setRecord_progress_stage("后期");
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

	@Override
	public int updateTaskbook(bysjglxt_taskbook updateTaskbook) {
		int flag = 2;
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		bysjglxt_taskbook = graduationProjectManagementDao.getTaskbookById(updateTaskbook.getTaskbook_id());
		if (bysjglxt_taskbook != null) {
			bysjglxt_taskbook.setTaskbook_acontent_required(updateTaskbook.getTaskbook_acontent_required());
			bysjglxt_taskbook.setTaskbook_reference(updateTaskbook.getTaskbook_reference());
			bysjglxt_taskbook.setTaskbook_plan(updateTaskbook.getTaskbook_plan());
			bysjglxt_taskbook.setTaskbook_opinion(updateTaskbook.getTaskbook_plan());
			bysjglxt_taskbook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInTaskBook(bysjglxt_taskbook);
		}
		return flag;
	}

	@Override
	public int updateReportOpening(bysjglxt_report_opening updateReportOpening) {
		int flag = 2;
		bysjglxt_report_opening bysjglxt_report_opening = new bysjglxt_report_opening();
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
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

			if (flag != 2) {
				// 根据阶段以及学生userId查找出进展情况记录表数据
				bysjglxt_record_progress = graduationProjectManagementDao
						.findRecordProgressByuserIdAndStage(bysjglxt_report_opening.getReport_opening_student(), "前期");
				if (bysjglxt_record_progress != null) {
					bysjglxt_record_progress.setRecord_progress_gmt_stop(TeamUtil.getStringSecond());
					bysjglxt_record_progress.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
					flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress);
				}
			}

		}
		return flag;
	}

	@Override
	public int updateRecordProgressEarlystage(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Earlystage = new bysjglxt_record_progress();
		bysjglxt_record_progress bysjglxt_record_progress_Metaphase = new bysjglxt_record_progress();
		bysjglxt_record_progress_Earlystage = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Earlystage != null) {
			bysjglxt_record_progress_Earlystage.setRecord_progress_gmt_stop(TeamUtil.getStringSecond());
			bysjglxt_record_progress_Earlystage
					.setRecord_progress_record(updateRecordProgress.getRecord_progress_record());
			bysjglxt_record_progress_Earlystage
					.setRecord_progress_opinion(updateRecordProgress.getRecord_progress_opinion());
			bysjglxt_record_progress_Earlystage.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Earlystage);
			if (flag != 2) {
				bysjglxt_record_progress_Metaphase = graduationProjectManagementDao.findRecordProgressByuserIdAndStage(
						bysjglxt_record_progress_Earlystage.getRecord_progress_student(), "中期");
				if (bysjglxt_record_progress_Metaphase != null) {
					bysjglxt_record_progress_Metaphase.setRecord_progress_gmt_start(TeamUtil.getStringSecond());
					bysjglxt_record_progress_Metaphase.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
					flag = graduationProjectManagementDao
							.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Metaphase);
				}

			}
		}
		return flag;
	}

}
