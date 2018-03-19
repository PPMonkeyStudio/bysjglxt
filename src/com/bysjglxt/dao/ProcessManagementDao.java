package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_notice;
import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;
import com.bysjglxt.domain.DO.bysjglxt_taskbook;
import com.bysjglxt.domain.VO.ProcessManagementVO;

public interface ProcessManagementDao {

	public int createProcessDefine(bysjglxt_process_definition selectTopicProcessDefinition);

	public int createTaskDefine(bysjglxt_task_definition selectTopicTaskDefine);

	public List<bysjglxt_process_definition> getAllProcessDefinition();

	public bysjglxt_process_definition getProcessDefinition(String process_definition_id);

	public bysjglxt_student_user getStudentUser(String operation);

	public boolean instanceProcess(bysjglxt_process_instance processInstance);

	public List<bysjglxt_task_definition> getListBelongProcess(String process_definition_id);

	public bysjglxt_task_instance taskInstanceIsExistId(String operation, String task_definition_id);

	public boolean instanceTask(bysjglxt_task_instance taskInstance);

	public List<bysjglxt_task_instance> getListTaskInstanceByPager(ProcessManagementVO processManagementVo,
			String userID);

	public bysjglxt_task_definition getTaskDefinition(String task_instance_task_definition);

	public bysjglxt_process_instance getProcessInstanceById(String task_instance_process_instance);

	public List<bysjglxt_task_instance> getAllTaskList(ProcessManagementVO processManagementVo, String userID);

	public com.bysjglxt.domain.DO.bysjglxt_task_instance getTaskInstanceByProcessInstanceIdAndTaskDefinitionId(
			String process_instance_id, String task_definition_father);

	public com.bysjglxt.domain.DO.bysjglxt_topic_select getStudentSelectTopicByStudentUserID(String operation);

	public com.bysjglxt.domain.DO.bysjglxt_teacher_user getTeacherUserByNum(String topic_select_teacher_tutor);

	public com.bysjglxt.domain.DO.bysjglxt_section getSectionById(String user_teacher_id);

	public List<com.bysjglxt.domain.DO.bysjglxt_process_instance> getListProcessInstanceByDefinitionId(
			String processDefinitionId);

	public List<bysjglxt_task_instance> getListTaskInstanceByProcessInstanceId(String process_instance_id);

	public boolean deleteTaskInstanceByProcessInstance(String process_instance_id);

	public boolean deleteProcessInstanceByProcessDefinitionId(String processDefinitionId);

	public boolean deleteTaskDefinitionByProcessDefinitionId(String processDefinitionId);

	public boolean deleteProcessDefinitionByProcessDefinitionId(String processDefinitionId);

	public bysjglxt_process_instance getProcessInstanceByDefinitionAndMan(String process_definition_id,
			String operation);

	public bysjglxt_task_instance getTaskInstanceingById(String taskInstanceId);

	public bysjglxt_task_instance getTaskInstanceByFatherTaskId(String task_instance_id);

	public com.bysjglxt.domain.DO.bysjglxt_task_instance getTaskInstanceing(String userId);

	public com.bysjglxt.domain.DO.bysjglxt_process_instance getProcessInstanceByUserAndState(String userId,String college);

	public List<bysjglxt_task_definition> getTaskDefinitionByProcessDefinitionId(
			String task_definition_process_definition);

	public com.bysjglxt.domain.DO.bysjglxt_student_basic getStudentBasicById(String user_student_basic);

	public com.bysjglxt.domain.DO.bysjglxt_section getSectionByName(String section);

	public void fillNoticeRecord(bysjglxt_notice bysjglxt_notice);

	public com.bysjglxt.domain.DO.bysjglxt_process_instance getSelectProcessInstance(String college);

	//
	public List<String> getListStudentSelect(String process_definition_id, String college);

	public bysjglxt_process_definition getProcessDefinitionByName(String string);

	public String getStudentNameByUserId(String stringId);

	// 根据学院来获取管理员
	public List<com.bysjglxt.domain.DO.bysjglxt_teacher_user> getListAdminCollegeByCollege(
			String user_student_belong_college);

	// 根据专业名称获取教研室对象
	public com.bysjglxt.domain.DO.bysjglxt_section getSectionByMajorId(String user_student_belong_major);

}
