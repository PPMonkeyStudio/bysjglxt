package com.bysjglxt.service.impl;

import java.io.File;
import java.util.List;

import com.bysjglxt.dao.TeacherInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.service.TeacherInformationManagementService;

public class TeacherInformationManagementServiceImpl implements TeacherInformationManagementService {

	// dao层注入
	private TeacherInformationManagementDao teacherInformationManagementDao;
	public void setTeacherInformationManagementDao(TeacherInformationManagementDao teacherInformationManagementDao) {
		this.teacherInformationManagementDao = teacherInformationManagementDao;
	}

	@Override
	public List<bysjglxt_teacher_basic> convertTeacherExcelToList(File teacherExcel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveTeacherList(List<bysjglxt_teacher_basic> teacherBasicList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TeacherInformationDTO> list_TeacherInformationDTO_All() {
		// TODO Auto-generated method stub
		return null;
	}

}
