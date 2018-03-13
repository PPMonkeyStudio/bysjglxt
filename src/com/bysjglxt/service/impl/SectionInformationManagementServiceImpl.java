package com.bysjglxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bysjglxt.dao.SectionInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.SectionInformationManagementVO;
import com.bysjglxt.service.SectionInformationManagementService;

import util.TeamUtil;

public class SectionInformationManagementServiceImpl implements SectionInformationManagementService {

	// Dao层的注入
	private SectionInformationManagementDao sectionInformationManagementDao;

	public void setSectionInformationManagementDao(SectionInformationManagementDao sectionInformationManagementDao) {
		this.sectionInformationManagementDao = sectionInformationManagementDao;
	}

	// 根据使用者Id获取学院
	public String getCollegeByUserId(String userId) {
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_teacher_user = sectionInformationManagementDao.getBysjglxtTeacherUserById(userId);
		if (bysjglxt_teacher_user.getUser_teacher_belong_college() != null
				&& bysjglxt_teacher_user.getUser_teacher_belong_college().trim().length() >= 0) {
			return bysjglxt_teacher_user.getUser_teacher_belong_college().trim();
		}
		return null;
	}

	@Override
	public SectionInformationManagementVO VO_Section_By_Page(
			SectionInformationManagementVO sectionInformationManagementVO, String userId) {
		// 获取学院
		String college = getCollegeByUserId(userId);
		List<TeacherInformationDTO> listTeacherInformationDTO = new ArrayList<TeacherInformationDTO>();
		List<bysjglxt_section> listBysjglxtSection = new ArrayList<bysjglxt_section>();
		TeacherInformationDTO teacherInformationDTO = null;
		bysjglxt_teacher_user bysjglxt_teacher_user = null;
		bysjglxt_teacher_basic bysjglxtTeacherBasic = null;
		int i = 0;
		// 获取总记录数
		i = sectionInformationManagementDao.getCountSectionLeader();
		sectionInformationManagementVO.setTotalRecords(i);
		sectionInformationManagementVO.setTotalPages(((i - 1) / sectionInformationManagementVO.getPageSize()) + 1);
		if (sectionInformationManagementVO.getPageIndex() <= 1) {
			sectionInformationManagementVO.setHavePrePage(false);
		} else {
			sectionInformationManagementVO.setHavePrePage(true);
		}
		if (sectionInformationManagementVO.getPageIndex() >= sectionInformationManagementVO.getTotalPages()) {
			sectionInformationManagementVO.setHaveNextPage(false);
		} else {
			sectionInformationManagementVO.setHaveNextPage(true);
		}
		// 据分页得到教研室信息
		listBysjglxtSection = sectionInformationManagementDao.getListSectionByPage(sectionInformationManagementVO,
				college);
		// 遍历所有教研室资料得到教研室主任信息
		for (bysjglxt_section bysjglxt_section : listBysjglxtSection) {
			teacherInformationDTO = new TeacherInformationDTO();
			teacherInformationDTO.setBysjglxtSection(bysjglxt_section);
			bysjglxt_teacher_user = new bysjglxt_teacher_user();
			// 根据教研室主任user的id来获取user表所有信息
			if (bysjglxt_section.getSection_leader() != null
					&& bysjglxt_section.getSection_leader().trim().length() > 0) {
				bysjglxt_teacher_user = sectionInformationManagementDao
						.getBysjglxtTeacherUserById(bysjglxt_section.getSection_leader());
				if (bysjglxt_teacher_user != null) {
					teacherInformationDTO.setBysjglxtTeacherUser(bysjglxt_teacher_user);
					bysjglxtTeacherBasic = new bysjglxt_teacher_basic();
					bysjglxtTeacherBasic = sectionInformationManagementDao
							.getBysjglxtTeacherBasicById(bysjglxt_teacher_user.getUser_teacher_basic());
					teacherInformationDTO.setBysjglxtTeacherBasic(bysjglxtTeacherBasic);
				}
			}
			listTeacherInformationDTO.add(teacherInformationDTO);
		}
		sectionInformationManagementVO.setTeacherInformationDTO(listTeacherInformationDTO);
		return sectionInformationManagementVO;
	}

	@Override
	public boolean Create_Section(bysjglxt_section newSection, String userId) {
		newSection.setSection_college_id(getCollegeByUserId(userId));
		newSection.setSection_id(TeamUtil.getUuid());
		newSection.setSection_gmt_create(TeamUtil.getStringSecond());
		newSection.setSection_gmt_modified(TeamUtil.getStringSecond());
		return sectionInformationManagementDao.create_Section(newSection);
	}

	@Override
	public boolean deleteSection(List<String> listSectionId) {
		boolean flag = false;
		for (String string : listSectionId) {
			/**
			 * 将所有属于这个教研室的老师的教研室所属为空
			 */
			flag = sectionInformationManagementDao.setTeacherUserSectionNull(string);
			if (!flag)
				break;
			flag = sectionInformationManagementDao.deleteSection(string);
			if (!flag)
				break;
		}
		return flag;
	}

	@Override
	public List<bysjglxt_section> listBysjglxtSection(String userId) {
		return sectionInformationManagementDao.listBysjglxtSection(userId);
	}

	@Override
	public boolean updateSection(bysjglxt_section bysjglxt_section) {
		bysjglxt_section.setSection_gmt_modified(TeamUtil.getStringSecond());
		return sectionInformationManagementDao.updateSection(bysjglxt_section);
	}

}
