package com.bysjglxt.service;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_defence;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DO.bysjglxt_taskbook;
import com.bysjglxt.domain.DTO.ProcessDefinitionDetailDTO;
import com.bysjglxt.domain.DTO.TaskDTO;
import com.bysjglxt.domain.VO.ProcessManagementVO;

public interface ProcessManagementService {

	/**
	 * @说明 点击创建流程 1. 创建流程定义记录 1 成功 -1失败
	 * @param SelectTopicProcessDefinition
	 * @return
	 */
	public String createSelectTopicProcessDefine(bysjglxt_process_definition selectTopicProcessDefinition);

	/**
	 * @说明 点击创建任务定义
	 * @param selectTopicTaskDefine
	 * @return
	 */
	public int createSelectTopicTaskDefine(bysjglxt_task_definition selectTopicTaskDefine);

	/**
	 * 遍历出流程定义
	 * 
	 * @return
	 */
	public List<bysjglxt_process_definition> listProcessDefinition();

	/**
	 * @param processInstanceName
	 *            流程实例名称 process_definition_id 流程定义ID operation 操作者ID processNum
	 *            流程编号 1 选题流程 2毕业设计流程 3答辩流程
	 * 
	 * @说明 创建流程和任务实例 参数：流程定义ID 1.成功 -4 系统繁忙，所输入的参数有问题 -1 无权限开启流程 -3实例化流程失败
	 * 
	 * @return
	 */
	public int openSelectTopicInstance(String processInstanceName, String process_definition_id, String operation);

	/**
	 * 遍历出所有的定义表
	 * 
	 * @return
	 */
	public ProcessDefinitionDetailDTO processDefinitionDetailDTO(String processDefinitionId);

	/**
	 * 删除流程定义
	 * 
	 * @param processDefinitionId
	 * @return 1:成功 -4系统繁忙，输入的参数有误 -3删除失败
	 */
	public int deleteProcessDefinition(List<String> listProcessDefinitionId);

	/**
	 * 点击我的任务，将内容分页显示
	 * 
	 * @param processManagementVo
	 * @return
	 */
	public ProcessManagementVO getMyTaskByPage(ProcessManagementVO processManagementVo, String userID);

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

	// 当前正在进行的实例
	public TaskDTO taskDTO(String userId);

}
