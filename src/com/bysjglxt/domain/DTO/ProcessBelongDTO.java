package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;

public class ProcessBelongDTO {

	private bysjglxt_process_definition bysjglxt_process_definition;
	private bysjglxt_process_instance bysjglxt_process_instance;

	@Override
	public String toString() {
		return "ProcessBelongDTO [bysjglxt_process_definition=" + bysjglxt_process_definition
				+ ", bysjglxt_process_instance=" + bysjglxt_process_instance + "]";
	}

	public bysjglxt_process_definition getBysjglxt_process_definition() {
		return bysjglxt_process_definition;
	}

	public void setBysjglxt_process_definition(bysjglxt_process_definition bysjglxt_process_definition) {
		this.bysjglxt_process_definition = bysjglxt_process_definition;
	}

	public bysjglxt_process_instance getBysjglxt_process_instance() {
		return bysjglxt_process_instance;
	}

	public void setBysjglxt_process_instance(bysjglxt_process_instance bysjglxt_process_instance) {
		this.bysjglxt_process_instance = bysjglxt_process_instance;
	}

}
