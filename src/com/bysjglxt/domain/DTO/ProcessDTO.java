package com.bysjglxt.domain.DTO;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;

public class ProcessDTO {

	// 流程定义
	private bysjglxt_process_definition processDefinition;
	// 流程实例
	private bysjglxt_process_instance processInstance;
	// 属于这个流程的任务
	private List<TaskDTO> listTaskBelongProcess;

	public bysjglxt_process_definition getProcessDefinition() {
		return processDefinition;
	}

	public void setProcessDefinition(bysjglxt_process_definition processDefinition) {
		this.processDefinition = processDefinition;
	}

	public bysjglxt_process_instance getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(bysjglxt_process_instance processInstance) {
		this.processInstance = processInstance;
	}

	public List<TaskDTO> getListTaskBelongProcess() {
		return listTaskBelongProcess;
	}

	public void setListTaskBelongProcess(List<TaskDTO> listTaskBelongProcess) {
		this.listTaskBelongProcess = listTaskBelongProcess;
	}

	@Override
	public String toString() {
		return "ProcessDTO [processDefinition=" + processDefinition + ", processInstance=" + processInstance
				+ ", listTaskBelongProcess=" + listTaskBelongProcess + "]";
	}

}
