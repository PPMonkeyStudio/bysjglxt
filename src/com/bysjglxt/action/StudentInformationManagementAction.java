package com.bysjglxt.action;

import com.bysjglxt.service.StudentInformationManagementService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class StudentInformationManagementAction extends ActionSupport {

	private StudentInformationManagementService studentInformationManagementService;

	/*
	 * 
	 */

	public String page() {

		return "page";
	}

	/*
	 * 
	 */

	public StudentInformationManagementService getStudentInformationManagementService() {
		return studentInformationManagementService;
	}

	public void setStudentInformationManagementService(
			StudentInformationManagementService studentInformationManagementService) {
		this.studentInformationManagementService = studentInformationManagementService;
	}

}
