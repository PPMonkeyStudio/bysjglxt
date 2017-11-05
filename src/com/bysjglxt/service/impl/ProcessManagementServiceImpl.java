package com.bysjglxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bysjglxt.dao.ProcessManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_leader;
import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;
import com.bysjglxt.domain.DTO.ProcessDefinitionDetailDTO;
import com.bysjglxt.service.ProcessManagementService;

import util.TeamUtil;

public class ProcessManagementServiceImpl implements ProcessManagementService {
	private ProcessManagementDao processManagementDao;

	public void setProcessManagementDao(ProcessManagementDao processManagementDao) {
		this.processManagementDao = processManagementDao;
	}

	// 创建选题流程定义表
	@Override
	public String createSelectTopicProcessDefine(bysjglxt_process_definition selectTopicProcessDefinition) {
		int flag = -1;
		selectTopicProcessDefinition.setProcess_definition_id(TeamUtil.getUuid());
		selectTopicProcessDefinition.setProcess_definition_gmt_create(TeamUtil.getStringSecond());
		selectTopicProcessDefinition
				.setProcess_definition_gmt_modified(selectTopicProcessDefinition.getProcess_definition_gmt_create());
		flag = processManagementDao.createProcessDefine(selectTopicProcessDefinition);
		return selectTopicProcessDefinition.getProcess_definition_id();
	}

	@Override
	public int createSelectTopicTaskDefine(bysjglxt_task_definition selectTopicTaskDefine) {
		int flag = -1;
		selectTopicTaskDefine.setTask_definition_id(TeamUtil.getUuid());
		selectTopicTaskDefine.setTask_definition_gmt_create(TeamUtil.getStringSecond());
		selectTopicTaskDefine.setTask_definition_gmt_modified(selectTopicTaskDefine.getTask_definition_gmt_create());
		flag = processManagementDao.createTaskDefine(selectTopicTaskDefine);
		return flag;
	}

	@Override
	public List<bysjglxt_process_definition> listProcessDefinition() {
		List<bysjglxt_process_definition> listProcessDefinition = new ArrayList<bysjglxt_process_definition>();
		listProcessDefinition = processManagementDao.getAllProcessDefinition();
		return listProcessDefinition;
	}

	@Override
	public int openSelectTopicInstance(String process_definition_id, String operation) {
		bysjglxt_process_definition processDefinition = new bysjglxt_process_definition();
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		bysjglxt_leader bysjglxtLeader = new bysjglxt_leader();
		bysjglxt_process_instance processInstance = new bysjglxt_process_instance();
		bysjglxt_task_instance taskInstance = null;
		bysjglxt_task_instance superTaskInstance = null;
		bysjglxt_task_instance returnTaskInstance = null;
		List<bysjglxt_task_definition> listTaskDefinition = new ArrayList<bysjglxt_task_definition>();
		boolean flag = true;
		// 1.领导小组长
		// 2.学生
		int actor = 0;
		// 1.创建流程实例
		// (1)根据流程定义ID获得流程定义
		processDefinition = processManagementDao.getProcessDefinition(process_definition_id);
		if (processDefinition == null) {
			return -4;
		}
		// (2)判断操作这是否是流程定义实例化角色
		// ②判断用户是哪种角色
		// 一、判断是否是学生或领导小组长
		studentUser = processManagementDao.getStudentUser(operation);
		if (studentUser != null) {
			actor = 2;
		} else {
			// 判断是否是领导小组
			bysjglxtLeader = processManagementDao.getLeader(operation);
			if (bysjglxtLeader != null) {
				actor = 1;
			} else {
				return -1;
			}
		}
		if (actor != processDefinition.getProcess_definition_instance_role()) {
			return -1;
		}
		// (3)实例化流程
		processInstance.setProcess_instance_id(TeamUtil.getUuid());
		processInstance.setProcess_instance_process_definition(processDefinition.getProcess_definition_id());
		processInstance.setProcess_instance_state("活动");
		processInstance.setProcess_instance_man("operation");
		processInstance.setProcess_instance_gmt_create(TeamUtil.getStringSecond());
		processInstance.setProcess_instance_gmt_modified(TeamUtil.getStringSecond());
		flag = processManagementDao.instanceProcess(processInstance);
		if (!flag)
			return -3;
		// 获取任务定义表中所有属于该流程的任务
		listTaskDefinition = processManagementDao.getListBelongProcess(processDefinition.getProcess_definition_id());
		for (bysjglxt_task_definition bysjglxt_task_definition : listTaskDefinition) {
			taskInstance = new bysjglxt_task_instance();
			superTaskInstance = new bysjglxt_task_instance();
			returnTaskInstance = new bysjglxt_task_instance();
			// 判断该任务实例是否已经存在
			// 使用任务实例执行角色ID 以及 任务定义ID
			taskInstance = processManagementDao.taskInstanceIsExistId(operation,
					bysjglxt_task_definition.getTask_definition_id());
			if (taskInstance == null) {
				taskInstance.setTask_instance_id(TeamUtil.getUuid());
			}
			taskInstance.setTask_instance_process_instance(processInstance.getProcess_instance_id());
			taskInstance.setTask_instance_task_definition(bysjglxt_task_definition.getTask_definition_id());
			taskInstance.setTask_instance_role(operation);
			// 判断父任务是否存在
			superTaskInstance = processManagementDao.taskInstanceIsExistId(operation,
					bysjglxt_task_definition.getTask_definition_father());
			if (superTaskInstance == null) {
				superTaskInstance.setTask_instance_id(TeamUtil.getUuid());
				// 保存父任务
				flag = processManagementDao.instanceTask(superTaskInstance);
				if (!flag)
					return -3;
			}
			taskInstance.setTask_instance_father(superTaskInstance.getTask_instance_id());
			// 判断返回的任务实例是否存在]
			returnTaskInstance = processManagementDao.taskInstanceIsExistId(operation,
					bysjglxt_task_definition.getTask_definition_return());
			if (returnTaskInstance == null) {
				returnTaskInstance.setTask_instance_id(TeamUtil.getUuid());
				// 保存返回任务
				flag = processManagementDao.instanceTask(returnTaskInstance);
				if (!flag)
					return -3;
			}
			taskInstance.setTask_instance_return(returnTaskInstance.getTask_instance_id());
			taskInstance.setTask_instance_state(1);
			taskInstance.setTask_instance_gmt_create(TeamUtil.getStringSecond());
			taskInstance.setTask_instance_gmt_modified(TeamUtil.getStringSecond());
			flag = processManagementDao.instanceTask(returnTaskInstance);
			if (!flag)
				return -3;
		}
		return 1;
	}

	@Override
	public ProcessDefinitionDetailDTO processDefinitionDetailDTO(String processDefinitionId) {
		ProcessDefinitionDetailDTO processDefinitionDetailDTO = new ProcessDefinitionDetailDTO();
		bysjglxt_process_definition bysjglxtProcessDefinition = new bysjglxt_process_definition();
		List<bysjglxt_task_definition> List_bysjglxtTaskDefinition = new ArrayList<bysjglxt_task_definition>();
		// 根据流程定义ID获取流程定义对象
		bysjglxtProcessDefinition = processManagementDao.getProcessDefinition(processDefinitionId);
		if (bysjglxtProcessDefinition == null) {
			return null;
		}
		List_bysjglxtTaskDefinition = processManagementDao.getListBelongProcess(processDefinitionId);
		processDefinitionDetailDTO.setBysjglxtProcessDefinition(bysjglxtProcessDefinition);
		processDefinitionDetailDTO.setList_bysjglxtTaskDefinition(List_bysjglxtTaskDefinition);
		return processDefinitionDetailDTO;
	}
}
