package com.bysjglxt.service;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_college;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DTO.CollegeInformationDTO;

public interface CollegeManagementService {

	// 遍历出所有的系部管理员
	// DTO TeacherInformation\系部
	public List<CollegeInformationDTO> listCollegeInformationDTO();

	// 修改系部管理员
	public int updateCollegeAdmin(String teacherUserId);

	// 添加系部
	public int addCollege(bysjglxt_college college, bysjglxt_teacher_basic bysjglxt_teacher_basic);

}
