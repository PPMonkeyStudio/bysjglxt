package com.bysjglxt.service;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DTO.ProcessDefinitionDetailDTO;

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
	 * 
	 * @说明 创建流程和任务实例 参数：流程定义ID 1.成功 -4 系统繁忙，所输入的参数有问题 -1 无权限开启流程 -3实例化流程失败
	 * @param selectTopicProcessInstance
	 * @return
	 */
	public int openSelectTopicInstance(String process_definition_id, String operation);

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
	 * @return
	 */
	public int deleteProcessDefinition(String processDefinitionId);

	
	
	
}
