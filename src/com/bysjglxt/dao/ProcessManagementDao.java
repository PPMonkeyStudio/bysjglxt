package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_leader;
import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;

public interface ProcessManagementDao {

	public int createProcessDefine(bysjglxt_process_definition selectTopicProcessDefinition);

	public int createTaskDefine(bysjglxt_task_definition selectTopicTaskDefine);

	public List<bysjglxt_process_definition> getAllProcessDefinition();

	public bysjglxt_process_definition getProcessDefinition(String process_definition_id);

	public bysjglxt_student_user getStudentUser(String operation);

	public bysjglxt_leader getLeader(String operation);

	public boolean instanceProcess(bysjglxt_process_instance processInstance);

	public List<bysjglxt_task_definition> getListBelongProcess(String process_definition_id);

	public bysjglxt_task_instance taskInstanceIsExistId(String operation, String task_definition_id);

	public boolean instanceTask(bysjglxt_task_instance taskInstance);
	
	
}
