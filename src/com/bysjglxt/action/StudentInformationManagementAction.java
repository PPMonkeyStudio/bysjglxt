package com.bysjglxt.action;

import com.bysjglxt.service.StudentInformationManagementService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class StudentInformationManagementAction extends ActionSupport {

	// service层注入
	private StudentInformationManagementService studentInformationManagementService;

	public void setStudentInformationManagementService(
			StudentInformationManagementService studentInformationManagementService) {
		this.studentInformationManagementService = studentInformationManagementService;
	}

}
