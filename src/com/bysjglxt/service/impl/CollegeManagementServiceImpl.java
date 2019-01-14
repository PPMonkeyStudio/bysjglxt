package com.bysjglxt.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.bysjglxt.dao.CollegeManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_college;
import com.bysjglxt.domain.DO.bysjglxt_notice;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DTO.CollegeInformationDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.service.CollegeManagementService;
import com.google.gson.JsonElement;

import util.TeamUtil;
import util.md5;

public class CollegeManagementServiceImpl implements CollegeManagementService {
	private CollegeManagementDao collegeManagementDao;

	public void setCollegeManagementDao(CollegeManagementDao collegeManagementDao) {
		this.collegeManagementDao = collegeManagementDao;
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
	
	
	/**
	 * 
	 */
	@Override
	public List<CollegeInformationDTO> listCollegeInformationDTO() {
		List<CollegeInformationDTO> listCollegeInformationDTO = new ArrayList<>();
		List<bysjglxt_college> listCollege = new ArrayList<>();
		List<bysjglxt_teacher_user> liUser = new ArrayList<>();
		CollegeInformationDTO collegeInformationDTO = null;
		TeacherInformationDTO teacherInformationDTO = null;
		bysjglxt_teacher_user bysjglxtTeacherUser = null;
		bysjglxt_teacher_basic bysjglxtTeacherBasic = null;
		bysjglxt_section bysjglxtSection = null;
		// 1.先遍历出所有的院系
		listCollege = collegeManagementDao.getListCollege();
		for (bysjglxt_college college : listCollege) {
			liUser = new ArrayList<>();
			collegeInformationDTO = new CollegeInformationDTO();
			teacherInformationDTO = new TeacherInformationDTO();
			bysjglxtTeacherBasic = new bysjglxt_teacher_basic();
			bysjglxtTeacherUser = new bysjglxt_teacher_user();
			bysjglxtSection = new bysjglxt_section();
			// 2.根据院系获取院系管理员
			// 这里使用list的原因是增加系统的扩展性，事实上在系统中一个学院只有管理员
			liUser = collegeManagementDao.getListCollegeAdmin(college.getCollege_id());
			// bysjglxtTeacherUser = liUser.get(0);
			if (liUser.size() == 0) {
				// 说明没有管理员
				collegeInformationDTO.setCollege(college);
				collegeInformationDTO.setTeacherInformationDTO(teacherInformationDTO);
				listCollegeInformationDTO.add(collegeInformationDTO);
			} else {
				bysjglxtTeacherUser = liUser.get(0);
				// 根据user表中basicId获取basic表信息
				if (bysjglxtTeacherUser.getUser_teacher_basic() != null
						&& bysjglxtTeacherUser.getUser_teacher_basic().trim().length() > 0) {
					bysjglxtTeacherBasic = collegeManagementDao
							.getTeacherBasicById(bysjglxtTeacherUser.getUser_teacher_basic().trim());
					teacherInformationDTO.setBysjglxtTeacherBasic(bysjglxtTeacherBasic);
				}
				if (bysjglxtTeacherUser.getUser_teacher_section() != null
						&& bysjglxtTeacherUser.getUser_teacher_section().trim().length() > 0) {
					bysjglxtSection = collegeManagementDao
							.getSectionById(bysjglxtTeacherUser.getUser_teacher_section().trim());
					teacherInformationDTO.setBysjglxtSection(bysjglxtSection);
				}
				teacherInformationDTO.setBysjglxtTeacherUser(bysjglxtTeacherUser);
				collegeInformationDTO.setTeacherInformationDTO(teacherInformationDTO);
				collegeInformationDTO.setCollege(college);
				listCollegeInformationDTO.add(collegeInformationDTO);
			}
		}
		return listCollegeInformationDTO;
	}

	@Override
	public int updateCollegeAdmin(String teacherUserId) {
		// 1.获取id老师的信息
		List<bysjglxt_teacher_user> liUser = new ArrayList<>();
		bysjglxt_teacher_user teacherUser = new bysjglxt_teacher_user();
		teacherUser = collegeManagementDao.getTeacherUserById(teacherUserId);
		if (teacherUser == null) {
			return -1;
		}
		// 2.根据院系获取院系管理员
		// 这里使用list的原因是增加系统的扩展性，事实上在系统中一个学院只有管理员
		liUser = collegeManagementDao.getListCollegeAdmin(teacherUser.getUser_teacher_belong_college());
		for (bysjglxt_teacher_user bysjglxt_teacher_user : liUser) {
			// 3.将管理员权限更改为否
			bysjglxt_teacher_user.setUser_teacher_is_college_admin(2);
			bysjglxt_teacher_user.setUser_teacher_gmt_modified(TeamUtil.getStringSecond());
			collegeManagementDao.saveObj(bysjglxt_teacher_user);
		}
		teacherUser.setUser_teacher_is_college_admin(1);
		teacherUser.setUser_teacher_gmt_modified(TeamUtil.getStringSecond());
		collegeManagementDao.saveObj(teacherUser);
		return 1;
	}


	/**
	 * 根据学院编号获取学院管理员信息
	 * @param college_code
	 * @return
	 */
	@Override
	public CollegeInformationDTO getCollegeAdminInfoByCollegeCode(String college_code) {
		bysjglxt_college college = new bysjglxt_college();
		CollegeInformationDTO collegeInformationDTO = new CollegeInformationDTO();
		TeacherInformationDTO teacherInformationDTO = new TeacherInformationDTO();
		if(college_code==null || "".equals(college_code)) {
			return null;
		}
		college = collegeManagementDao.getCollegeByCode(college_code);
		if(college == null) {
			return null;
		}
		//根据该学院的学院信息获取学院管理员信息
		bysjglxt_teacher_user teacherUser = new bysjglxt_teacher_user();
		teacherUser = collegeManagementDao.getTeacherUserByCollegeId(college.getCollege_id());
		if(teacherUser !=null && teacherUser.getUser_teacher_basic()!=null && !"".equals(teacherUser.getUser_teacher_basic())) {
			teacherInformationDTO.setBysjglxtTeacherUser(teacherUser);
			bysjglxt_teacher_basic teacherBasic = new bysjglxt_teacher_basic();
			teacherBasic = collegeManagementDao.getTeacherBasicById(teacherUser.getUser_teacher_basic());
			if(teacherBasic!=null) {
				teacherInformationDTO.setBysjglxtTeacherBasic(teacherBasic);
			}
			if(teacherUser.getUser_teacher_section()!=null && !"".equals(teacherUser.getUser_teacher_section().trim())) {
				bysjglxt_section section = new bysjglxt_section();
				section = collegeManagementDao.getSectionById(teacherUser.getUser_teacher_section().trim());
				if(section!=null) {
					teacherInformationDTO.setBysjglxtSection(section);
				}
			}
		}
		collegeInformationDTO.setCollege(college);
		collegeInformationDTO.setTeacherInformationDTO(teacherInformationDTO);
		return collegeInformationDTO;
	}

	
	
	/**
	 * 添加管理员
	 * 
	 * @return -1 数据错误 1 正确
	 */
	@Override
	public int addCollege(bysjglxt_college college,bysjglxt_teacher_basic bysjglxt_teacher_basic) {
//		bysjglxt_teacher_basic bysjglxt_teacher_basic = new bysjglxt_teacher_basic();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_college bysjglxt_college = new bysjglxt_college();
		bysjglxt_teacher_basic teacherBasic = new bysjglxt_teacher_basic();
		if (college.getCollege_code() == null || college.getCollege_code().trim().length() <= 0
				|| college.getCollege_name() == null || college.getCollege_name().trim().length() <= 0) {
			// 数据错误
			return -1;
		}
		/*if (bysjglxt_teacher_basic.getName() == null || bysjglxt_teacher_basic.getName().trim().length() <= 0
				|| bysjglxt_teacher_basic.getJob_number() == null
				|| bysjglxt_teacher_basic.getJob_number().trim().length() <= 0) {
			return -1;
		}*/
		// 根据所填写院系代码以及院系名称进行查询
		if (college.getCollege_code() != null && college.getCollege_code().trim().length() > 0) {
			bysjglxt_college = collegeManagementDao.getCollegeByCode(college.getCollege_code().trim());
			if (bysjglxt_college != null) {
				// 有重复
				return -2;
			}
		}
		if (college.getCollege_name() != null && college.getCollege_name().trim().length() > 0) {
			bysjglxt_college = collegeManagementDao.getCollegeByName(college.getCollege_name().trim());
			if (bysjglxt_college != null) {
				// 有重复
				return -2;
			}
		}
		// 1.根据工号进行排除重复
		/*if (bysjglxt_teacher_basic.getJob_number() != null
				&& bysjglxt_teacher_basic.getJob_number().trim().length() > 0) {
			teacherBasic = collegeManagementDao.getTeacherBasicByJobNum(bysjglxt_teacher_basic.getJob_number().trim());
			if (teacherBasic != null) {
				// 有重复
				return -2;
			}
		}*/
		// 添加学院
		college.setCollege_id(TeamUtil.getUuid());
		college.setCollege_gmt_create(TeamUtil.getStringSecond());
		college.setCollege_gmt_modified(college.getCollege_gmt_create());
		collegeManagementDao.saveObj(college);
		// 添加教师管理员
		bysjglxt_teacher_basic.setJob_number(college.getCollege_code().trim()+"000000");
		bysjglxt_teacher_basic.setTeacher_basic_id(TeamUtil.getUuid());
		bysjglxt_teacher_basic.setTeacher_basic_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_teacher_basic.setTeacher_basic_gmt_modified(bysjglxt_teacher_basic.getTeacher_basic_gmt_create());
		collegeManagementDao.saveObj(bysjglxt_teacher_basic);
		bysjglxt_teacher_user.setUser_teacher_id(TeamUtil.getUuid());
		bysjglxt_teacher_user.setUser_teacher_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_teacher_user.setUser_teacher_gmt_modified(bysjglxt_teacher_user.getUser_teacher_gmt_create());
		bysjglxt_teacher_user.setUser_teacher_num(bysjglxt_teacher_basic.getJob_number());
		bysjglxt_teacher_user.setUser_teacher_password(md5.GetMD5Code(bysjglxt_teacher_basic.getJob_number()));
		bysjglxt_teacher_user.setUser_teacher_basic(bysjglxt_teacher_basic.getTeacher_basic_id());
		bysjglxt_teacher_user.setUser_teacher_section("");
		bysjglxt_teacher_user.setUser_teacher_guidance_num(0);
		bysjglxt_teacher_user.setUser_teacher_max_guidance(-1);
		bysjglxt_teacher_user.setUser_teacher_is_recorder(2);
		bysjglxt_teacher_user.setUser_teacher_belong_college(college.getCollege_id());
		bysjglxt_teacher_user.setUser_teacher_is_college_admin(1);
		bysjglxt_teacher_user.setUser_teacher_is_defence_leader(2);
		collegeManagementDao.saveObj(bysjglxt_teacher_user);
		//TODO
		//消息通知，后续将使用Spring AOP进行横切解决
		bysjglxt_notice noticeCollege = new bysjglxt_notice();
		noticeCollege.setNotice_id(TeamUtil.getUuid());
		noticeCollege.setNotice_launch("系统管理员");
		noticeCollege.setNotice_belong(bysjglxt_teacher_user.getUser_teacher_id());
		//TODO 这里要留意一下
		String collegeNotice = (String) properties.get("createCollegeNotice");
		noticeCollege.setNotice_content(collegeNotice.replaceAll("college", college.getCollege_name()));
		noticeCollege.setNotice_leixing(2);
		noticeCollege.setNotice_state(2);
		noticeCollege.setNotice_gmt_modified(TeamUtil.getStringSecond());
		noticeCollege.setNotice_gmt_create(noticeCollege.getNotice_gmt_modified());
		collegeManagementDao.saveObj(noticeCollege);
		return 1;
	}



	/**
	 * 
	 */
	@Override
	public bysjglxt_college getCollegetById(String id) {
		return this.collegeManagementDao.getCollegeById(id);
	}

}
