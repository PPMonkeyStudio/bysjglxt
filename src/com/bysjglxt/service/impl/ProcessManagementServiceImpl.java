package com.bysjglxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bysjglxt.dao.ProcessManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_leader;
import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.DTO.ProcessDefinitionDetailDTO;
import com.bysjglxt.domain.DTO.ProcessDetailDTO;
import com.bysjglxt.domain.DTO.TaskDTO;
import com.bysjglxt.domain.VO.ProcessManagementVO;
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
		if (flag == -1) {
			return "创建失败";
		}
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
	public int openProcess(String processInstanceName, String process_definition_id, String operation) {
		// 判断用户是否已经开启该流程
		bysjglxt_process_instance processInstanceIsOpen = new bysjglxt_process_instance();
		// 根据流程定义ID以及流程实例化者ID判断是否已经开启流程
		processInstanceIsOpen = processManagementDao.getProcessInstanceByDefinitionAndMan(process_definition_id,
				operation);
		if (processInstanceIsOpen != null) {
			return -2;
		}
		boolean flag = true;
		bysjglxt_student_user bysjglxt_student_user = null;
		bysjglxt_process_instance bysjglxt_process_instance = new bysjglxt_process_instance();
		bysjglxt_task_instance bysjglxt_task_instance = null;
		List<bysjglxt_leader> listLeader = new ArrayList<bysjglxt_leader>();
		bysjglxt_leader bysjglxt_leader = null;
		bysjglxt_topic_select bysjglxt_topic_select = null;
		bysjglxt_teacher_user bysjglxt_teacher_user = null;
		bysjglxt_section bysjglxt_section = null;
		bysjglxt_task_instance bysjglxt_task_instanceFather = null;
		bysjglxt_task_instance bysjglxt_task_instanceReturn = null;
		List<bysjglxt_task_definition> list_bysjglxt_task_definition = new ArrayList<bysjglxt_task_definition>();
		// 创建流程实例
		bysjglxt_process_instance.setProcess_instance_id(TeamUtil.getUuid());
		bysjglxt_process_instance.setProcess_instance_name(processInstanceName);
		bysjglxt_process_instance.setProcess_instance_process_definition(process_definition_id);
		bysjglxt_process_instance.setProcess_instance_state("活动");
		bysjglxt_process_instance.setProcess_instance_man(operation);
		bysjglxt_process_instance.setProcess_instance_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_process_instance
				.setProcess_instance_gmt_modified(bysjglxt_process_instance.getProcess_instance_gmt_create());
		flag = processManagementDao.instanceProcess(bysjglxt_process_instance);
		if (!flag)
			return -3;
		// 遍历任务表中属于这个流程的任务定义
		list_bysjglxt_task_definition = processManagementDao.getListBelongProcess(process_definition_id);
		// 判断第一个任务实例为正在进行
		int x = 0;
		System.out.println(list_bysjglxt_task_definition.size());
		for (bysjglxt_task_definition bysjglxt_task_definition : list_bysjglxt_task_definition) {
			x++;
			bysjglxt_student_user = new bysjglxt_student_user();
			bysjglxt_leader = new bysjglxt_leader();
			bysjglxt_task_instance = new bysjglxt_task_instance();
			bysjglxt_topic_select = new bysjglxt_topic_select();
			bysjglxt_task_instanceFather = new bysjglxt_task_instance();
			bysjglxt_section = new bysjglxt_section();
			bysjglxt_task_instanceReturn = new bysjglxt_task_instance();
			bysjglxt_task_instance.setTask_instance_id(TeamUtil.getUuid());
			bysjglxt_task_instance
					.setTask_instance_process_instance(bysjglxt_process_instance.getProcess_instance_id());
			bysjglxt_task_instance.setTask_instance_task_definition(bysjglxt_task_definition.getTask_definition_id());
			switch (bysjglxt_task_definition.getTask_definition_role()) {
			case 1:
				// 指导老师
				// 判断学生账号是否错误
				// 判断那是否是学生点击开启流程
				bysjglxt_student_user = processManagementDao.getStudentUser(operation);
				if (bysjglxt_student_user == null) {
					return -1;
				}
				// 根据学生user ID获取学生选题表信息
				bysjglxt_topic_select = processManagementDao.getStudentSelectTopicByStudentUserID(operation);
				if (bysjglxt_topic_select == null) {
					return -3;
				}
				bysjglxt_task_instance.setTask_instance_role(bysjglxt_topic_select.getTopic_select_teacher_tutor());
				break;
			case 3:
				// 领导小组长
				listLeader = processManagementDao.getListLeader();
				if (listLeader == null) {
					return -3;
				}
				bysjglxt_leader = listLeader.get(0);
				bysjglxt_task_instance.setTask_instance_role(bysjglxt_leader.getLeader_teacher_id());
				break;
			case 4:
				// 判断学生账号是否错误
				// 判断那是否是学生点击开启流程
				bysjglxt_student_user = processManagementDao.getStudentUser(operation);
				if (bysjglxt_student_user == null) {
					return -1;
				}
				// 根据学生user ID获取学生选题表信息
				bysjglxt_topic_select = processManagementDao.getStudentSelectTopicByStudentUserID(operation);
				// 根据指导老师ID获得教师所属教研室
				if (bysjglxt_topic_select == null) {
					return -3;
				}
				// 根据教师UserID获得教师信息user对象
				bysjglxt_teacher_user = processManagementDao
						.getTeacherUserByNum(bysjglxt_topic_select.getTopic_select_teacher_tutor());
				if (bysjglxt_teacher_user == null) {
					return -3;
				}
				// 根据老师所属教研室获得教研室主任
				bysjglxt_section = processManagementDao.getSectionById(bysjglxt_teacher_user.getUser_teacher_section());
				if (bysjglxt_section == null) {
					return -3;
				}
				bysjglxt_task_instance.setTask_instance_role(bysjglxt_section.getSection_leader());
				break;
			case 5:
				// 判断学生账号是否错误
				// 判断那是否是学生点击开启流程
				bysjglxt_student_user = processManagementDao.getStudentUser(operation);
				if (bysjglxt_student_user == null) {
					return -1;
				}
				// 根据学生user ID获取学生选题表信息
				bysjglxt_task_instance.setTask_instance_role(operation);
				break;
			}
			// 父任务实例ID
			// (1).先获得父任务定义ID
			// bysjglxt_task_definition.getTask_definition_father()
			// (2).根据任务实例所属流程实例ID以及任务实例所属任务定义ID得到父任务实例ID
			if (bysjglxt_task_definition.getTask_definition_father() != null
					&& bysjglxt_task_definition.getTask_definition_father().trim().length() > 0) {
				bysjglxt_task_instanceFather = processManagementDao
						.getTaskInstanceByProcessInstanceIdAndTaskDefinitionId(
								bysjglxt_process_instance.getProcess_instance_id(),
								bysjglxt_task_definition.getTask_definition_father());
				bysjglxt_task_instance.setTask_instance_father(bysjglxt_task_instanceFather.getTask_instance_id());
			}
			// 返回的任务实例
			// (1)获得返回任务定义ID
			// bysjglxt_task_definition.getTask_definition_return()
			// (2)根据任务实例所属流程实例ID以及任务实例所属任务定义ID得到返回任务实例ID
			if (bysjglxt_task_definition.getTask_definition_return() != null
					&& bysjglxt_task_definition.getTask_definition_return().trim().length() > 0) {

				bysjglxt_task_instanceReturn = processManagementDao
						.getTaskInstanceByProcessInstanceIdAndTaskDefinitionId(
								bysjglxt_process_instance.getProcess_instance_id(),
								bysjglxt_task_definition.getTask_definition_return());
				if (bysjglxt_task_instanceReturn == null) {
				}
				bysjglxt_task_instance.setTask_instance_return(bysjglxt_task_instanceReturn.getTask_instance_id());
			}
			// 状态初始化 2：未开始
			if (x == 1) {
				bysjglxt_task_instance.setTask_instance_state(1);
			} else {
				bysjglxt_task_instance.setTask_instance_state(2);
			}
			bysjglxt_task_instance.setTask_instance_gmt_create(TeamUtil.getStringSecond());
			bysjglxt_task_instance.setTask_instance_gmt_modified(bysjglxt_task_instance.getTask_instance_gmt_create());
			flag = processManagementDao.instanceTask(bysjglxt_task_instance);
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

	// 点击我的任务
	@Override
	public ProcessManagementVO getMyTaskByPage(ProcessManagementVO processManagementVo, String userID) {
		List<ProcessDetailDTO> list_ProcessInstanceDetailDTO = new ArrayList<ProcessDetailDTO>();
		List<bysjglxt_task_instance> list_bysjglxtTaskInstance = new ArrayList<bysjglxt_task_instance>();
		ProcessDetailDTO processDetailDTO = null;
		bysjglxt_process_definition bysjglxtProcessDefinition = null;
		bysjglxt_task_definition bysjglxtTaskDefinition = null;
		bysjglxt_process_instance bysjglxtProcessInstance = null;
		// 获得符合条件的10条任务实例数据
		list_bysjglxtTaskInstance = processManagementDao.getListTaskInstanceByPager(processManagementVo, userID);
		// 遍历10条分别封装其他对象
		for (bysjglxt_task_instance bysjglxt_task_instance : list_bysjglxtTaskInstance) {
			bysjglxtTaskDefinition = new bysjglxt_task_definition();
			bysjglxtProcessInstance = new bysjglxt_process_instance();
			bysjglxtProcessDefinition = new bysjglxt_process_definition();
			processDetailDTO = new ProcessDetailDTO();
			// 根据任务定义ID获取任务定义表
			bysjglxtTaskDefinition = processManagementDao
					.getTaskDefinition(bysjglxt_task_instance.getTask_instance_task_definition());
			if (bysjglxtTaskDefinition == null) {
				return null;
			}
			// 根据流程实例ID获取流程实例表
			bysjglxtProcessInstance = processManagementDao
					.getProcessInstanceById(bysjglxt_task_instance.getTask_instance_process_instance());
			if (bysjglxtProcessInstance == null) {
				return null;
			}
			// 根据流程定义ID获取流程定义表
			bysjglxtProcessDefinition = processManagementDao
					.getProcessDefinition(bysjglxtProcessInstance.getProcess_instance_process_definition());
			if (bysjglxtProcessDefinition == null) {
				return null;
			}
			processDetailDTO.setBysjglxtProcessDefinition(bysjglxtProcessDefinition);
			processDetailDTO.setBysjglxtProcessInstance(bysjglxtProcessInstance);
			processDetailDTO.setBysjglxtTaskDefinition(bysjglxtTaskDefinition);
			processDetailDTO.setBysjglxtTaskInstance(bysjglxt_task_instance);
			list_ProcessInstanceDetailDTO.add(processDetailDTO);
		}
		processManagementVo.setList_ProcessDetailDTO(list_ProcessInstanceDetailDTO);
		// 获得符合条件的总记录数
		list_bysjglxtTaskInstance = processManagementDao.getAllTaskList(processManagementVo, userID);
		int i = list_bysjglxtTaskInstance.size();
		processManagementVo.setTotalRecords(i);
		processManagementVo.setTotalPages(((i - 1) / processManagementVo.getPageSize()) + 1);
		if (processManagementVo.getPageIndex() <= 1) {
			processManagementVo.setHavePrePage(false);
		} else {
			processManagementVo.setHavePrePage(true);
		}
		if (processManagementVo.getPageIndex() >= processManagementVo.getTotalPages()) {
			processManagementVo.setHaveNextPage(false);
		} else {
			processManagementVo.setHaveNextPage(true);
		}
		return processManagementVo;
	}

	// 删除流程定义
	@Override
	public int deleteProcessDefinition(List<String> listProcessDefinitionId) {
		boolean flag = true;
		bysjglxt_process_definition bysjglxt_process_definition = null;
		List<bysjglxt_process_instance> listProcessInstance = new ArrayList<bysjglxt_process_instance>();
		List<bysjglxt_task_definition> listProcessDefinition = new ArrayList<bysjglxt_task_definition>();
		List<bysjglxt_task_instance> listTaskInstance = new ArrayList<bysjglxt_task_instance>();
		for (String processDefinitionId : listProcessDefinitionId) {
			bysjglxt_process_definition = new bysjglxt_process_definition();
			// 根据流程定义ID获取流程定义对象
			bysjglxt_process_definition = processManagementDao.getProcessDefinition(processDefinitionId);
			if (bysjglxt_process_definition == null) {
				return -3;
			}
			// 根据流程定义获取任务定义
			listProcessDefinition = processManagementDao.getListBelongProcess(processDefinitionId);
			// 根据流程定义获取流程实例List
			listProcessInstance = processManagementDao.getListProcessInstanceByDefinitionId(processDefinitionId);
			for (bysjglxt_process_instance bysjglxt_process_instance : listProcessInstance) {
				// 根据流程实例删除任务实例
				flag = processManagementDao
						.deleteTaskInstanceByProcessInstance(bysjglxt_process_instance.getProcess_instance_id());
				if (!flag)
					return -3;
			}
			// 根据流程定义ID删除流程实例
			flag = processManagementDao.deleteProcessInstanceByProcessDefinitionId(processDefinitionId);
			if (!flag)
				return -3;
			// 根据流程定义ID删除任务定义
			flag = processManagementDao.deleteTaskDefinitionByProcessDefinitionId(processDefinitionId);
			if (!flag)
				return -3;
			// 删除流程定义
			flag = processManagementDao.deleteProcessDefinitionByProcessDefinitionId(processDefinitionId);
			if (!flag)
				return -3;
		}
		return 1;
	}

	/**
	 * 获得正在进行的任务实例
	 */
	@Override
	public TaskDTO getCurrentTaskDTO(String userId) {
		TaskDTO taskDTO = new TaskDTO();
		bysjglxt_task_instance bysjglxt_task_instance = new bysjglxt_task_instance();
		bysjglxt_task_definition bysjglxt_task_definition = new bysjglxt_task_definition();
		// 获取用户正在进行的任务实例
		bysjglxt_task_instance = processManagementDao.getTaskInstanceing(userId);
		// 根据任务实例的任务定义ID获取任务定义表
		bysjglxt_task_definition = processManagementDao
				.getTaskDefinition(bysjglxt_task_instance.getTask_instance_task_definition());
		taskDTO.setTaskDefinition(bysjglxt_task_definition);
		taskDTO.setTaskInstance(bysjglxt_task_instance);
		return taskDTO;
	}

	/********************************************** 下面是点击通过或打回 ***************************/

	// 通过
	@Override
	public int pass(String taskInstanceId) {
		boolean flag = true;
		bysjglxt_task_instance currentTaskInstance = new bysjglxt_task_instance();
		bysjglxt_task_instance nextTaskInstance = new bysjglxt_task_instance();
		currentTaskInstance = processManagementDao.getTaskInstanceingById(taskInstanceId);
		if (currentTaskInstance == null) {
			return -3;
			// 无该任务实例
		}
		// 只有正在进行的才能点击通过
		if (currentTaskInstance.getTask_instance_state() != 1) {
			return -2;
			// 任务实例不是正在进行状态不能点击通过
		}
		// 更改任务实例状态,将之改为已结束
		currentTaskInstance.setTask_instance_state(3);
		currentTaskInstance.setTask_instance_gmt_modified(TeamUtil.getStringSecond());
		// 存储任务实例
		flag = processManagementDao.instanceTask(currentTaskInstance);
		if (!flag)
			return -1;// 更改任务实例失败
		// 将当前任务ID作为父任务实例ID可以获得下一个任务实例
		nextTaskInstance = processManagementDao
				.getTaskInstanceByFatherTaskId(currentTaskInstance.getTask_instance_id());
		if (nextTaskInstance == null) {
			return -3;// 无该任务实例
		}
		// 更改任务实例状态,将之改为已结束
		nextTaskInstance.setTask_instance_state(1);
		nextTaskInstance.setTask_instance_gmt_modified(TeamUtil.getStringSecond());
		// 存储任务实例
		flag = processManagementDao.instanceTask(nextTaskInstance);
		if (!flag)
			return -1;// 更改任务实例失败
		return 1;// 成功
	}

	// 打回
	@Override
	public int repulse(String taskInstanceId) {
		boolean flag = true;
		// 1.根据任务实例ID获取任务实例实例对象
		bysjglxt_task_instance currentTaskInstance = new bysjglxt_task_instance();
		bysjglxt_task_instance currentTaskInstanceFather = new bysjglxt_task_instance();
		bysjglxt_task_instance currentTaskInstanceReturn = new bysjglxt_task_instance();
		currentTaskInstance = processManagementDao.getTaskInstanceingById(taskInstanceId);
		if (currentTaskInstance == null) {
			return -3;
			// 无该任务实例
		}
		// 2.将当前实例对象更改未未开始
		// 更改任务实例状态,将之改为已结束
		currentTaskInstance.setTask_instance_state(2);
		currentTaskInstance.setTask_instance_gmt_modified(TeamUtil.getStringSecond());
		// 存储任务实例
		flag = processManagementDao.instanceTask(currentTaskInstance);
		if (!flag)
			return -1;// 更改任务实例失败
		// 3.先获得返回任务实例的ID currentTaskInstance.getTask_instance_return()
		// id 是父任务实例ID
		String id = currentTaskInstance.getTask_instance_father();
		while (true) {
			// 父任务实例
			currentTaskInstanceFather = processManagementDao.getTaskInstanceingById(id);
			// 如果属于返回的任务实例
			if ((currentTaskInstance.getTask_instance_return())
					.equals(currentTaskInstanceFather.getTask_instance_id())) {
				// 将返回的任务实例状态更改为正在进行
				currentTaskInstanceFather.setTask_instance_state(1);
				currentTaskInstanceFather.setTask_instance_gmt_modified(TeamUtil.getStringSecond());
				// 存储任务实例
				flag = processManagementDao.instanceTask(currentTaskInstanceFather);
				if (!flag)
					return -1;
				break;
			}
			// 如果不是返回的任务实例,将状态更改为未开始
			currentTaskInstanceFather.setTask_instance_state(2);
			currentTaskInstanceFather.setTask_instance_gmt_modified(TeamUtil.getStringSecond());
			// 存储任务实例
			flag = processManagementDao.instanceTask(currentTaskInstanceFather);
			if (!flag)
				return -1;
			id = currentTaskInstanceFather.getTask_instance_father();
		}
		return 1;
	}

}
