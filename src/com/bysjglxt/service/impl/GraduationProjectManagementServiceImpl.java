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

	@Override
	public int updateRecordProgressMetaphase(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Metaphase = new bysjglxt_record_progress();
		bysjglxt_record_progress bysjglxt_record_progress_Laterstage = new bysjglxt_record_progress();
		bysjglxt_record_progress_Metaphase = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Metaphase != null) {
			bysjglxt_record_progress_Metaphase.setRecord_progress_gmt_stop(TeamUtil.getStringSecond());
			bysjglxt_record_progress_Metaphase
					.setRecord_progress_record(updateRecordProgress.getRecord_progress_record());
			bysjglxt_record_progress_Metaphase
					.setRecord_progress_opinion(updateRecordProgress.getRecord_progress_opinion());
			bysjglxt_record_progress_Metaphase.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Metaphase);
			if (flag != 2) {
				bysjglxt_record_progress_Laterstage = graduationProjectManagementDao.findRecordProgressByuserIdAndStage(
						bysjglxt_record_progress_Metaphase.getRecord_progress_student(), "后期");
				if (bysjglxt_record_progress_Laterstage != null) {
					bysjglxt_record_progress_Laterstage.setRecord_progress_gmt_start(TeamUtil.getStringSecond());
					bysjglxt_record_progress_Laterstage.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
					flag = graduationProjectManagementDao
							.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Laterstage);
				}
			}
		}
		return flag;
	}

	@Override
	public int updateRecordProgressLaterstage(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Laterstage = new bysjglxt_record_progress();
		bysjglxt_record_progress bysjglxt_record_progress_Perfect = new bysjglxt_record_progress();
		bysjglxt_record_progress_Laterstage = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Laterstage != null) {
			bysjglxt_record_progress_Laterstage.setRecord_progress_gmt_stop(TeamUtil.getStringSecond());
			bysjglxt_record_progress_Laterstage
					.setRecord_progress_record(updateRecordProgress.getRecord_progress_record());
			bysjglxt_record_progress_Laterstage
					.setRecord_progress_opinion(updateRecordProgress.getRecord_progress_opinion());
			bysjglxt_record_progress_Laterstage.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Laterstage);
			if (flag != 2) {
				bysjglxt_record_progress_Perfect = graduationProjectManagementDao.findRecordProgressByuserIdAndStage(
						bysjglxt_record_progress_Laterstage.getRecord_progress_student(), "完善");
				if (bysjglxt_record_progress_Perfect != null) {
					bysjglxt_record_progress_Perfect.setRecord_progress_gmt_start(TeamUtil.getStringSecond());
					bysjglxt_record_progress_Perfect.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
					flag = graduationProjectManagementDao
							.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Perfect);
				}
			}

		}
		return flag;
	}

	@Override
	public int updateRecordProgressPerfect(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Perfect = new bysjglxt_record_progress();
		bysjglxt_summary bysjglxt_summary = new bysjglxt_summary();
		bysjglxt_record_progress_Perfect = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Perfect != null) {
			bysjglxt_record_progress_Perfect.setRecord_progress_gmt_stop(TeamUtil.getStringSecond());
			bysjglxt_record_progress_Perfect
					.setRecord_progress_record(updateRecordProgress.getRecord_progress_record());
			bysjglxt_record_progress_Perfect
					.setRecord_progress_opinion(updateRecordProgress.getRecord_progress_opinion());
			bysjglxt_record_progress_Perfect.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Perfect);
			if (flag != 2) {
				bysjglxt_summary = graduationProjectManagementDao
						.findSummaryByUserId(bysjglxt_record_progress_Perfect.getRecord_progress_id());
				bysjglxt_summary.setSummary_gmt_start(TeamUtil.getStringSecond());
				bysjglxt_summary.setSummary_gmt_modified(TeamUtil.getStringSecond());
				flag = graduationProjectManagementDao.fillEmptyInSummary(bysjglxt_summary);
			}
		}

		return flag;
	}

	@Override
	public int updateSummary(bysjglxt_summary updateSummary) {
		int flag = 2;
		bysjglxt_summary bysjglxt_summary = new bysjglxt_summary();
		bysjglxt_summary = graduationProjectManagementDao.findSummaryById(updateSummary.getSummary_id());
		if (bysjglxt_summary != null) {
			bysjglxt_summary.setSummary_gmt_stop(TeamUtil.getStringSecond());
			bysjglxt_summary.setSummary_summary(updateSummary.getSummary_summary());
			bysjglxt_summary.setSummary_opinion(updateSummary.getSummary_opinion());
			bysjglxt_summary.setSummary_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInSummary(bysjglxt_summary);
		}
		return flag;
	}

	@Override
	public int updateExaminationFormal(bysjglxt_examination_formal updateExaminationFormal) {
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
			bysjglxt_examination_formal.setExamination_formal_is_leader_opinion(
					updateExaminationFormal.getExamination_formal_is_leader_opinion());
			bysjglxt_examination_formal.setExamination_formal_is_teacher_opinion(
					updateExaminationFormal.getExamination_formal_is_teacher_opinion());
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
			bysjglxt_defence.setDefence_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyDefence(bysjglxt_defence);
		}
		return flag;
	}

}
