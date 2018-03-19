package com.bysjglxt.domain.DTO;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;

public class ProcessDefinitionDetailDTO {

	private bysjglxt_process_definition bysjglxtProcessDefinition;
	private List<bysjglxt_task_definition> List_bysjglxtTaskDefinition;

	@Override
	public String toString() {
		return "ProcessDefinitionDetailDTO [bysjglxtProcessDefinition=" + bysjglxtProcessDefinition
				+ ", List_bysjglxtTaskDefinition=" + List_bysjglxtTaskDefinition + "]";
	}

	public bysjglxt_process_definition getBysjglxtProcessDefinition() {
		return bysjglxtProcessDefinition;
	}

	public void setBysjglxtProcessDefinition(bysjglxt_process_definition bysjglxtProcessDefinition) {
		this.bysjglxtProcessDefinition = bysjglxtProcessDefinition;
	}

	public List<bysjglxt_task_definition> getList_bysjglxtTaskDefinition() {
		return List_bysjglxtTaskDefinition;
	}

	public void setList_bysjglxtTaskDefinition(List<bysjglxt_task_definition> list_bysjglxtTaskDefinition) {
		List_bysjglxtTaskDefinition = list_bysjglxtTaskDefinition;
	}

}
