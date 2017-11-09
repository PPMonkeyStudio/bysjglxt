package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;

public class TaskDTO {

	private bysjglxt_task_definition taskDefinition;
	private bysjglxt_task_instance taskInstance;

	@Override
	public String toString() {
		return "TaskDTO [taskDefinition=" + taskDefinition + ", taskInstance=" + taskInstance + "]";
	}

	public bysjglxt_task_definition getTaskDefinition() {
		return taskDefinition;
	}

	public void setTaskDefinition(bysjglxt_task_definition taskDefinition) {
		this.taskDefinition = taskDefinition;
	}

	public bysjglxt_task_instance getTaskInstance() {
		return taskInstance;
	}

	public void setTaskInstance(bysjglxt_task_instance taskInstance) {
		this.taskInstance = taskInstance;
	}

}
