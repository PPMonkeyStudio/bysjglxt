package com.bysjglxt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bysjglxt.dao.TeacherInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.TeacherInformationManagementVO;
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
			bysjglxt_teacher_user.setUser_teacher_guidance_num(0);
			// 工号
			bysjglxt_teacher_user.setUser_teacher_num(bysjglxt_teacher_basic.getJob_number());
			bysjglxt_teacher_user.setUser_teacher_section(null);
			// 密码
			bysjglxt_teacher_user.setUser_teacher_password(bysjglxt_teacher_user.getUser_teacher_num());
			bysjglxt_teacher_user.setUser_teacher_basic(bysjglxt_teacher_basic.getTeacher_basic_id());
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
	 * 
	 */
	@Override
	public List<TeacherInformationDTO> list_TeacherInformationDTO_All() {
		List<TeacherInformationDTO> list_TeacherInformationDTO_All = new ArrayList<TeacherInformationDTO>();
		TeacherInformationDTO teacherInformationDTO = null;
		List<bysjglxt_teacher_user> listAllTeacherUserInformation = teacherInformationManagementDao
				.list_TeacherUserInformation_All();
		for (bysjglxt_teacher_user teacher_user : listAllTeacherUserInformation) {
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
		bysjglxt_teacher_user.setUser_teacher_guidance_num(0);
		// 工号
		bysjglxt_teacher_user.setUser_teacher_num(teacher_basic.getJob_number());
		// 密码
		bysjglxt_teacher_user.setUser_teacher_password(bysjglxt_teacher_user.getUser_teacher_num());
		bysjglxt_teacher_user.setUser_teacher_basic(teacher_basic.getTeacher_basic_id());
		// 教研室
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

	@Override
	public TeacherInformationManagementVO VO_TEACHER_By_PageAndSearch(
			TeacherInformationManagementVO teacherInformationManagementVO) {

		List<bysjglxt_teacher_basic> sizeList = teacherInformationManagementDao
				.getResultBySearch(teacherInformationManagementVO);
		int i = sizeList.size();
		teacherInformationManagementVO.setTotalRecords(i);
		teacherInformationManagementVO.setTotalPages(((i - 1) / teacherInformationManagementVO.getPageSize()) + 1);
		if (teacherInformationManagementVO.getPageIndex() <= 1) {
			teacherInformationManagementVO.setHavePrePage(false);
		} else {
			teacherInformationManagementVO.setHavePrePage(true);
		}
		if (teacherInformationManagementVO.getPageIndex() >= teacherInformationManagementVO.getTotalPages()) {
			teacherInformationManagementVO.setHaveNextPage(false);
		} else {
			teacherInformationManagementVO.setHaveNextPage(true);
		}
		List<TeacherInformationDTO> listTeacherInformationDTO = new ArrayList<TeacherInformationDTO>();
		TeacherInformationDTO teacherInformationDTO = null;
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_section bysjglxt_section = new bysjglxt_section();
		List<bysjglxt_teacher_basic> listTeacherBasicInformationByPage = teacherInformationManagementDao
				.listTeacherBasicInformationByPageAndSearch(teacherInformationManagementVO);
		for (bysjglxt_teacher_basic bysjglxt_teacher_basic : listTeacherBasicInformationByPage) {
			teacherInformationDTO = new TeacherInformationDTO();
			bysjglxt_teacher_user = teacherInformationManagementDao
					.getTeacherInfoByBasicId(bysjglxt_teacher_basic.getTeacher_basic_id());
			bysjglxt_section = teacherInformationManagementDao
					.get_TeacherSectionInformation_ByUserSectionId(bysjglxt_teacher_user.getUser_teacher_section());
			teacherInformationDTO.setBysjglxtTeacherUser(bysjglxt_teacher_user);
			teacherInformationDTO.setBysjglxtTeacherBasic(bysjglxt_teacher_basic);
			teacherInformationDTO.setBysjglxtSection(bysjglxt_section);
			listTeacherInformationDTO.add(teacherInformationDTO);

		}
		teacherInformationManagementVO.setList_TeacherInformationDTO(listTeacherInformationDTO);
		return teacherInformationManagementVO;
	}

	/**
	 * 
	 */
	@Override
	public List<bysjglxt_section> listBysjglxtSection() {
		return teacherInformationManagementDao.listBysjglxtSection();
	}

	/**
	 * 弃用
	 */
	@Override
	public boolean updateBasicAndUser(TeacherInformationDTO teacherInformationDTO) {
		// 修改基础表信息
		boolean flag = false;
		flag = teacherInformationManagementDao.updateBasic(teacherInformationDTO.getBysjglxtTeacherBasic());
		if (!flag)
			return flag;
		flag = teacherInformationManagementDao.updateUser(teacherInformationDTO.getBysjglxtTeacherUser());
		if (!flag)
			return flag;
		return flag;
	}

	/**
	 * 修改教师基础表信息
	 */
	@Override
	public boolean updateTeacherBasic(bysjglxt_teacher_basic bysjglxt_teacher_basic) {
		return teacherInformationManagementDao.updateBasic(bysjglxt_teacher_basic);
	}

	@Override
	public boolean updateTeacherUser(bysjglxt_teacher_user bysjglxt_teacher_user) {
		return teacherInformationManagementDao.updateUser(bysjglxt_teacher_user);
	}

	/**
	 * 获取老师的职称
	 */
	@Override
	public List<String> list_Teacher_Title() {
		return teacherInformationManagementDao.list_Teacher_Title();
	}

	@Override
	public boolean resetPassword(String user_teacher_id) {
		boolean flag = false;
		if (user_teacher_id == null || user_teacher_id.trim().length() <= 0) {
			return false;
		}
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_teacher_user = teacherInformationManagementDao.getStudentById(user_teacher_id);
		if (bysjglxt_teacher_user != null) {
			bysjglxt_teacher_user.setUser_teacher_password(bysjglxt_teacher_user.getUser_teacher_num());
			bysjglxt_teacher_user.setUser_teacher_gmt_modified(TeamUtil.getStringSecond());
			flag = teacherInformationManagementDao.saveTeacher(bysjglxt_teacher_user);
		}
		return flag;
	}

	@Override
	public boolean updatePassword(String user_teacher_id, String password) {
		boolean flag = false;
		if (user_teacher_id == null || user_teacher_id.trim().length() <= 0) {
			return false;
		}
		if (password == null || password.trim().length() <= 0) {
			return false;
		}
		flag = teacherInformationManagementDao.updatePassword(user_teacher_id, password);
		return flag;
	}

}
