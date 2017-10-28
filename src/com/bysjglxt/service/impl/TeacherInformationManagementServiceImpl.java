package com.bysjglxt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bysjglxt.dao.TeacherInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.service.TeacherInformationManagementService;

import util.ExcelToBean;
import util.TeamUtil;

public class TeacherInformationManagementServiceImpl implements TeacherInformationManagementService {

	// dao层注入
	private TeacherInformationManagementDao teacherInformationManagementDao;

	public void setTeacherInformationManagementDao(TeacherInformationManagementDao teacherInformationManagementDao) {
		this.teacherInformationManagementDao = teacherInformationManagementDao;
	}

	/**
	 * OK
	 */
	@Override
	public List<bysjglxt_teacher_basic> convertTeacherExcelToList(File teacherExcel, String EXCEL_TeacherFileName)
			throws Exception {
		String houzhui = EXCEL_TeacherFileName.substring(EXCEL_TeacherFileName.lastIndexOf(".") + 1);
		FileInputStream input = new FileInputStream(teacherExcel);
		List<Map<String, Object>> list = null;
		if ("xlsx".equals(houzhui)) {
			XSSFWorkbook workbook = new XSSFWorkbook(input);
			list = ExcelToBean.parseUpdateExcel(workbook, "bysjglxt_teacher_basic");
		} else {
			HSSFWorkbook workbook = new HSSFWorkbook(input);
			list = ExcelToBean.parseExcel(workbook, "bysjglxt_teacher_basic");
		}
		List<bysjglxt_teacher_basic> lists = ExcelToBean.toObjectPerproList(list, bysjglxt_teacher_basic.class);
		return lists;
	}

	/**
	 * OK
	 */
	@Override
	public boolean saveTeacherList(List<bysjglxt_teacher_basic> teacherBasicList) {
		boolean flag = false;
		bysjglxt_teacher_user bysjglxt_teacher_user = null;
		for (bysjglxt_teacher_basic bysjglxt_teacher_basic : teacherBasicList) {
			bysjglxt_teacher_user = new bysjglxt_teacher_user();
			bysjglxt_teacher_basic.setTeacher_basic_id(TeamUtil.getUuid());
			bysjglxt_teacher_basic.setTeacher_basic_gmt_create(TeamUtil.getStringSecond());
			bysjglxt_teacher_basic.setTeacher_basic_gmt_modified(bysjglxt_teacher_basic.getTeacher_basic_gmt_create());
			flag = teacherInformationManagementDao.saveTeacherBasic(bysjglxt_teacher_basic);
			bysjglxt_teacher_user.setUser_teacher_id(TeamUtil.getUuid());
			// 工号
			bysjglxt_teacher_user.setUser_teacher_num(bysjglxt_teacher_basic.getJob_number());
			// 密码
			bysjglxt_teacher_user.setUser_teacher_password(bysjglxt_teacher_user.getUser_teacher_num());
			bysjglxt_teacher_user.setUser_teacher_basic(bysjglxt_teacher_basic.getTeacher_basic_id());
			// 教研室
			bysjglxt_teacher_user.setUser_teacher_section("1");
			bysjglxt_teacher_user.setUser_teacher_max_guidance(-1);
			bysjglxt_teacher_user.setUser_teacher_gmt_create(TeamUtil.getStringSecond());
			bysjglxt_teacher_user.setUser_teacher_gmt_modified(bysjglxt_teacher_user.getUser_teacher_gmt_create());
			flag = teacherInformationManagementDao.saveTeacher(bysjglxt_teacher_user);
			if (!flag)
				break;
		}
		return flag;
	}

	/**
	 * 此方式暂时被弃用
	 */
	@Override
	public List<TeacherInformationDTO> list_TeacherInformationDTO_All() {
		List<TeacherInformationDTO> list_TeacherInformationDTO_All = new ArrayList<TeacherInformationDTO>();
		TeacherInformationDTO teacherInformationDTO = null;
		List<bysjglxt_teacher_user> listAllTeacherUserInformation = teacherInformationManagementDao
				.list_TeacherUserInformation_All();
		for (bysjglxt_teacher_user teacher_user : listAllTeacherUserInformation) {
			System.out.println("gg");
			teacherInformationDTO = new TeacherInformationDTO();
			teacherInformationDTO.setBysjglxtTeacherUser(teacher_user);
			teacherInformationDTO.setBysjglxtTeacherBasic(teacherInformationManagementDao
					.get_TeacherBasicInformation_ByUserBasic(teacher_user.getUser_teacher_basic()));
			list_TeacherInformationDTO_All.add(teacherInformationDTO);
		}
		return list_TeacherInformationDTO_All;
	}

	/**
	 * OK
	 */
	@Override
	public boolean save_NewTeacher(bysjglxt_teacher_basic teacher_basic) {
		boolean flag = false;
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		teacher_basic.setTeacher_basic_id(TeamUtil.getUuid());
		teacher_basic.setTeacher_basic_gmt_create(TeamUtil.getStringSecond());
		teacher_basic.setTeacher_basic_gmt_modified(teacher_basic.getTeacher_basic_gmt_create());
		flag = teacherInformationManagementDao.saveTeacherBasic(teacher_basic);
		bysjglxt_teacher_user.setUser_teacher_id(TeamUtil.getUuid());
		// 工号
		bysjglxt_teacher_user.setUser_teacher_num(teacher_basic.getJob_number());
		// 密码
		bysjglxt_teacher_user.setUser_teacher_password(bysjglxt_teacher_user.getUser_teacher_num());
		bysjglxt_teacher_user.setUser_teacher_basic(teacher_basic.getTeacher_basic_id());
		// 教研室
		bysjglxt_teacher_user.setUser_teacher_section("1");
		bysjglxt_teacher_user.setUser_teacher_max_guidance(-1);
		bysjglxt_teacher_user.setUser_teacher_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_teacher_user.setUser_teacher_gmt_modified(bysjglxt_teacher_user.getUser_teacher_gmt_create());
		flag = teacherInformationManagementDao.saveTeacher(bysjglxt_teacher_user);
		return flag;
	}

	/**
	 * OK
	 */
	@Override
	public boolean remove_TeacherList(List<String> useTeacherIdList) {
		boolean flag = false;
		for (String teacher_user_id : useTeacherIdList) {
			bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
			bysjglxt_teacher_user = teacherInformationManagementDao.getStudentById(teacher_user_id);
			flag = teacherInformationManagementDao
					.deleteTeacherBasicInfoById(bysjglxt_teacher_user.getUser_teacher_basic());
			flag = teacherInformationManagementDao.deleteTeacherInfoById(teacher_user_id);
		}
		return flag;
	}

}
