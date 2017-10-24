package com.bysjglxt.service.impl;

import java.io.File;
import java.util.List;

import com.bysjglxt.dao.StudentInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.service.StudentInformationManagementService;

public class StudentInformationManagementServiceImpl implements StudentInformationManagementService {
	
	//Dao层的注入
	private StudentInformationManagementDao studentInformationManagementDao;
	public void setStudentInformationManagementDao(StudentInformationManagementDao studentInformationManagementDao) {
		this.studentInformationManagementDao = studentInformationManagementDao;
	}

	@Override
	public List<bysjglxt_student_basic> convertStudentExcelToList(File studentExcel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveStudentList(List<bysjglxt_student_basic> studentBasicList) {
		// TODO Auto-generated method stub
		return false;
	}

}
