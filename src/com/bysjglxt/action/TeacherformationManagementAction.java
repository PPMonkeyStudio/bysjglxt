package com.bysjglxt.action;

import com.bysjglxt.service.TeacherInformationManagementService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TeacherformationManagementAction extends ActionSupport {

	// service层注入
	private TeacherInformationManagementService teacherInformationManagementService;

	public void setTeacherInformationManagementService(
			TeacherInformationManagementService teacherInformationManagementService) {
		this.teacherInformationManagementService = teacherInformationManagementService;
	}

}
