package com.bysjglxt.service.impl;

import com.bysjglxt.dao.GraduationProjectManagementDao;
import com.bysjglxt.service.GraduationProjectManagementService;

public class GraduationProjectManagementServiceImpl implements GraduationProjectManagementService {

	private GraduationProjectManagementDao graduationProjectManagementDao;

	public void setGraduationProjectManagementDao(GraduationProjectManagementDao graduationProjectManagementDao) {
		this.graduationProjectManagementDao = graduationProjectManagementDao;
	}

}
