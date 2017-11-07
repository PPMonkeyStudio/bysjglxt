package com.bysjglxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bysjglxt.dao.ProcessManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_defence;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_leader;
import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;
import com.bysjglxt.domain.DO.bysjglxt_taskbook;
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
	public int openSelectTopicInstance(String processInstanceName, String process_definition_id, String operation) {
		int i = 0;
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
		for (bysjglxt_task_definition bysjglxt_task_definition : list_bysjglxt_task_definition) {
			bysjglxt_student_user = new bysjglxt_student_user();
			bysjglxt_leader = new bysjglxt_leader();
			bysjglxt_task_instance = new bysjglxt_task_instance();
			bysjglxt_topic_select = new bysjglxt_topic_select();
			bysjglxt_task_instanceFather = new bysjglxt_task_instance();
			bysjglxt_section = new bysjglxt_section();
			bysjglxt_task_instanceReturn = new bysjglxt_task_instance();
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
				bysjglxt_task_instance.setTask_instance_return(bysjglxt_task_instanceReturn.getTask_instance_id());
			}
			// 状态初始化 2：未开始
			bysjglxt_task_instance.setTask_instance_state(2);
			bysjglxt_task_instance.setTask_instance_gmt_create(TeamUtil.getStringSecond());
			bysjglxt_task_instance.setTask_instance_gmt_modified(bysjglxt_task_instance.getTask_instance_gmt_create());
			flag = processManagementDao.instanceTask(bysjglxt_task_instance);
			if (!flag)
				return -3;

		}
		return i;

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
		System.out.println(List_bysjglxtTaskDefinition.size());
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
				System.out.println("任务定义记录为空");
				return null;
			}
			// 根据流程实例ID获取流程实例表
			bysjglxtProcessInstance = processManagementDao
					.getProcessInstanceById(bysjglxt_task_instance.getTask_instance_process_instance());
			if (bysjglxtProcessInstance == null) {
				System.out.println("流程实例记录为空");
				return null;
			}
			// 根据流程定义ID获取流程定义表
			bysjglxtProcessDefinition = processManagementDao
					.getProcessDefinition(bysjglxtProcessInstance.getProcess_instance_process_definition());
			if (bysjglxtProcessDefinition == null) {
				System.out.println("流程定义记录为空");
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
		System.out.println("总记录数:" + list_ProcessInstanceDetailDTO.size());
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

	/******************************** 下面是我的毕业设计需要 ***************************************/

	@Override
	public bysjglxt_taskbook get_TaskBook(String userId) {
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		bysjglxt_taskbook = processManagementDao.getTaskBookByUserId(userId);
		return bysjglxt_taskbook;
	}

	@Override
	public bysjglxt_report_opening get_ReportOpening(String userId) {
		bysjglxt_report_opening bysjglxt_report_opening = new bysjglxt_report_opening();
		bysjglxt_report_opening = processManagementDao.getReportOpening(userId);
		return bysjglxt_report_opening;
	}

	@Override
	public bysjglxt_record_progress get_RecordProgress_1(String userId) {
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		bysjglxt_record_progress = processManagementDao.getRecordProgress(userId, "前期");
		return bysjglxt_record_progress;
	}

	@Override
	public bysjglxt_record_progress get_RecordProgress_2(String userId) {
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		bysjglxt_record_progress = processManagementDao.getRecordProgress(userId, "中期");
		return bysjglxt_record_progress;
	}

	@Override
	public bysjglxt_record_progress get_RecordProgress_3(String userId) {
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		bysjglxt_record_progress = processManagementDao.getRecordProgress(userId, "后期");
		return bysjglxt_record_progress;
	}

	@Override
	public bysjglxt_record_progress get_RecordProgress_4(String userId) {
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		bysjglxt_record_progress = processManagementDao.getRecordProgress(userId, "完善期");
		return bysjglxt_record_progress;
	}

	@Override
	public bysjglxt_summary get_Summary(String userId) {
		bysjglxt_summary bysjglxt_summary = new bysjglxt_summary();
		bysjglxt_summary = processManagementDao.getSummary(userId);
		return bysjglxt_summary;
	}

	@Override
	public bysjglxt_examination_formal get_ExaminationFormal(String userId) {
		bysjglxt_examination_formal bysjglxt_examination_formal = new bysjglxt_examination_formal();
		bysjglxt_examination_formal = processManagementDao.getExaminationFormal(userId);
		return bysjglxt_examination_formal;
	}

	@Override
	public bysjglxt_evaluate_tutor get_EvaluateTutor(String userId) {
		bysjglxt_evaluate_tutor bysjglxt_evaluate_tutor = new bysjglxt_evaluate_tutor();
		bysjglxt_evaluate_tutor = processManagementDao.getEvaluateTutor(userId);
		return bysjglxt_evaluate_tutor;
	}

	@Override
	public bysjglxt_evaluate_review get_EvaluateReview(String userId) {
		bysjglxt_evaluate_review bysjglxt_evaluate_review = new bysjglxt_evaluate_review();
		bysjglxt_evaluate_review = processManagementDao.getEvaluateReview(userId);
		return bysjglxt_evaluate_review;
	}

	@Override
	public bysjglxt_defence get_Defence(String userId) {
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_defence = processManagementDao.getDefence(userId);
		return bysjglxt_defence;
	}
	@Override
	public TaskDTO taskDTO(String userId) {
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
}
