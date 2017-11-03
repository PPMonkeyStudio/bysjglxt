package com.bysjglxt.service;

import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_taskbook;

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
	 * @说明 更改任务书
	 * @param bysjglxt_taskbook
	 * @return 1.创建成功 2.创建失败
	 */
	public int updateTaskbook(bysjglxt_taskbook updateTaskbook);

	/**
	 * @说明 更改开题报告表
	 * @param updateReportOpening
	 * @return 1.成功 2.失败
	 */
	public int updateReportOpening(bysjglxt_report_opening updateReportOpening);


	/**
	 * @说明 更改前期进展情况记录
	 * @param updateRecordProgress
	 * @return 1.成功 2.失败
	 */
	public int updateRecordProgressEarlystage(bysjglxt_record_progress updateRecordProgress);

}
