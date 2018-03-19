package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;

public class ProcessDetailDTO {

	private bysjglxt_process_instance bysjglxtProcessInstance;
	private bysjglxt_process_definition bysjglxtProcessDefinition;
	private bysjglxt_task_definition bysjglxtTaskDefinition;
	private bysjglxt_task_instance bysjglxtTaskInstance;
	
	@Override
	public String toString() {
		return "ProcessDetailDTO [bysjglxtProcessInstance=" + bysjglxtProcessInstance + ", bysjglxtProcessDefinition="
				+ bysjglxtProcessDefinition + ", bysjglxtTaskDefinition=" + bysjglxtTaskDefinition
				+ ", bysjglxtTaskInstance=" + bysjglxtTaskInstance + "]";
	}

	public bysjglxt_task_definition getBysjglxtTaskDefinition() {
		return bysjglxtTaskDefinition;
	}

	public void setBysjglxtTaskDefinition(bysjglxt_task_definition bysjglxtTaskDefinition) {
		this.bysjglxtTaskDefinition = bysjglxtTaskDefinition;
	}

	public bysjglxt_task_instance getBysjglxtTaskInstance() {
		return bysjglxtTaskInstance;
	}

	public void setBysjglxtTaskInstance(bysjglxt_task_instance bysjglxtTaskInstance) {
		this.bysjglxtTaskInstance = bysjglxtTaskInstance;
	}

	public bysjglxt_process_definition getBysjglxtProcessDefinition() {
		return bysjglxtProcessDefinition;
	}

	public void setBysjglxtProcessDefinition(bysjglxt_process_definition bysjglxtProcessDefinition) {
		this.bysjglxtProcessDefinition = bysjglxtProcessDefinition;
	}

	public bysjglxt_process_instance getBysjglxtProcessInstance() {
		return bysjglxtProcessInstance;
	}

	public void setBysjglxtProcessInstance(bysjglxt_process_instance bysjglxtProcessInstance) {
		this.bysjglxtProcessInstance = bysjglxtProcessInstance;
	}

}
