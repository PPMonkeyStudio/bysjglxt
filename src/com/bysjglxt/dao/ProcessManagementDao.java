package com.bysjglxt.dao;

import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;

public interface ProcessManagementDao {

	public int createProcessDefine(bysjglxt_process_definition selectTopicProcessDefinition);

	public int createTaskDefine(bysjglxt_task_definition selectTopicTaskDefine);
	
	
}
