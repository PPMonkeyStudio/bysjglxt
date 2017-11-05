package com.bysjglxt.service;

import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;

public interface ProcessManagementService {

	/**
	 * @说明 点击创建选题流程 1. 创建选题流程定义记录 1 成功 -1失败
	 * @param SelectTopicProcessDefinition
	 * @return
	 */
	public int createSelectTopicProcessDefine(bysjglxt_process_definition selectTopicProcessDefinition);

	/**
	 * @说明 点击创建任务定义
	 * @param selectTopicTaskDefine
	 * @return
	 */
	public int createSelectTopicTaskDefine(bysjglxt_task_definition selectTopicTaskDefine);

	/**
	 * 
	 * @说明 创建流程和任务实例
	 * 
	 * @param selectTopicProcessInstance
	 * @return
	 */
	// public int openSelectTopicInstance(bysjglxt_process_instance
	// selectTopicProcessInstance);

}
