package com.bysjglxt.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.bysjglxt.dao.SectionInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_college;
import com.bysjglxt.domain.DO.bysjglxt_notice;
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
	
	public static Properties properties = new Properties();//properties属性
	static {
		//加载消息文件
		try {
//			properties.load(CollegeManagementServiceImpl.class.getClassLoader().getResourceAsStream("notice.properties"));
			InputStream inputStream = CollegeManagementServiceImpl.class.getClassLoader().getResourceAsStream("notice.properties");
			BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
			properties.load(bf);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		if(sectionInformationManagementDao.create_Section(newSection)) {
			//查找是否有创建学院的通知
			//notice_launch=系统管理员
			//notice_belong=userId
			//notice_leixing=2
			bysjglxt_notice notice = new bysjglxt_notice();
			notice = sectionInformationManagementDao.getNoticeByLaunchBelongLeiXing("系统管理员",userId,2);
			if(notice!=null) {
				notice.setNotice_leixing(1);
				notice.setNotice_state(1);
				notice.setNotice_gmt_modified(TeamUtil.getStringSecond());
				sectionInformationManagementDao.savaObj(notice);
			}
			if(!"".equals(newSection.getSection_leader())) {
				//生成新的消息通知
				notice = new bysjglxt_notice();
				notice.setNotice_id(TeamUtil.getUuid());
				notice.setNotice_launch(userId);
				notice.setNotice_belong(newSection.getSection_leader());
				notice.setNotice_content(((String)(properties.get("createSection"))).replaceAll("section", newSection.getSection_name()));
				notice.setNotice_leixing(4);
				notice.setNotice_state(2);
				notice.setNotice_gmt_modified(TeamUtil.getStringSecond());
				notice.setNotice_gmt_modified(notice.getNotice_gmt_create());
				sectionInformationManagementDao.savaObj(notice);
			}
		}
		return false;
	}

	/**
	 * 已经不允许删除
	 */
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
	public List<bysjglxt_section> getSectionByUser(String user_teacher_section) {
		return sectionInformationManagementDao.getSectionByUser(user_teacher_section);
	}

	public bysjglxt_teacher_user getCollegeByTeacheUserId(String userId) {
		bysjglxt_teacher_user teacherUser = sectionInformationManagementDao.getBysjglxtTeacherUserById(userId);
		//
		if(teacherUser!=null && teacherUser.getUser_teacher_belong_college()!=null) {
			return sectionInformationManagementDao.getTeacherByCollege(teacherUser.getUser_teacher_belong_college());
		}
		return null;
	}
	
	
	@Override
	public boolean updateSection(bysjglxt_section bysjglxt_section) {
		bysjglxt_section.setSection_gmt_modified(TeamUtil.getStringSecond());
		bysjglxt_section oldSection = sectionInformationManagementDao.getSectionById(bysjglxt_section.getSection_id());
		if(sectionInformationManagementDao.updateSection(bysjglxt_section) && !"".equals(bysjglxt_section.getSection_leader())) {
			bysjglxt_teacher_user launch = getCollegeByTeacheUserId(bysjglxt_section.getSection_leader());
			if(launch == null) {
				return true;
			}
			bysjglxt_notice notice = new bysjglxt_notice();
			notice.setNotice_id(TeamUtil.getUuid());
			notice.setNotice_launch(launch.getUser_teacher_id());
			notice.setNotice_belong(bysjglxt_section.getSection_leader());
			notice.setNotice_content(((String)(properties.get("updateNewSection"))).replaceAll("section", bysjglxt_section.getSection_name()));
			notice.setNotice_leixing(4);
			notice.setNotice_state(2);
			notice.setNotice_gmt_create(TeamUtil.getStringSecond());
			notice.setNotice_gmt_modified(notice.getNotice_gmt_create());
			sectionInformationManagementDao.savaObj(notice);
		}
		if(oldSection!=null && oldSection.getSection_leader()!=null && !"".equals(oldSection.getSection_leader())) {
			bysjglxt_teacher_user launch = getCollegeByTeacheUserId(oldSection.getSection_leader());
			if(launch == null) {
				return true;
			}
			bysjglxt_notice notice = new bysjglxt_notice();
			notice.setNotice_id(TeamUtil.getUuid());
			notice.setNotice_launch(launch.getUser_teacher_id());
			notice.setNotice_belong(oldSection.getSection_leader());
			notice.setNotice_content(((String)(properties.get("updateOldSection"))).replaceAll("section", oldSection.getSection_name()));
			notice.setNotice_leixing(4);
			notice.setNotice_state(2);
			notice.setNotice_gmt_create(TeamUtil.getStringSecond());
			notice.setNotice_gmt_modified(notice.getNotice_gmt_create());
			sectionInformationManagementDao.savaObj(notice);
		}
		return true;
	}

}
