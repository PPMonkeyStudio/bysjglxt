package com.bysjglxt.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bysjglxt.dao.TeacherInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_college;
import com.bysjglxt.domain.DO.bysjglxt_notice;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.TeacherInformationManagementVO;
import com.bysjglxt.service.CollegeManagementService;
import com.bysjglxt.service.TeacherInformationManagementService;
import com.google.gson.JsonElement;

import util.ExcelToBean2;
import util.TeamUtil;
import util.md5;

public class TeacherInformationManagementServiceImpl implements TeacherInformationManagementService {

	// dao层注入
	private TeacherInformationManagementDao teacherInformationManagementDao;

	public void setTeacherInformationManagementDao(TeacherInformationManagementDao teacherInformationManagementDao) {
		this.teacherInformationManagementDao = teacherInformationManagementDao;
	}
	public static Properties properties = new Properties();//properties属性
	static {
		//加载消息文件
		try {
//			properties.load(CollegeManagementServiceImpl.class.getClassLoader().getResourceAsStream("notice.properties"));
			InputStream inputStream = CollegeManagementServiceImpl.class.getClassLoader().getResourceAsStream("notice.properties");
			BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
			properties.load(bf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	@Override
	public TeacherInformationDTO getTeacherInfoByUserId(bysjglxt_student_user updateStudentUser) {
		TeacherInformationDTO teacherInformationDTO = new TeacherInformationDTO();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_topic_select = teacherInformationManagementDao.getStudentSelectTopic(updateStudentUser.getUser_student_id());
		return teacherInformationManagementDao.getTeacherInfomationDTOByTeacherUserId(bysjglxt_topic_select.getTopic_select_teacher_review());
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
			list = ExcelToBean2.parseUpdateExcel(workbook, "bysjglxt_teacher_basic");
		} else {
			HSSFWorkbook workbook = new HSSFWorkbook(input);
			list = ExcelToBean2.parseExcel(workbook, "bysjglxt_teacher_basic");
		}
		List<bysjglxt_teacher_basic> lists = ExcelToBean2.toObjectPerproList(list, bysjglxt_teacher_basic.class);
		return lists;
	}

	// 根据使用者Id获取学院
	/*
	 * public String getCollegeByUserId(String userId) { bysjglxt_teacher_user
	 * bysjglxt_teacher_user = new bysjglxt_teacher_user(); bysjglxt_teacher_user =
	 * teacherInformationManagementDao.getStudentById(userId); if
	 * (bysjglxt_teacher_user.getUser_teacher_belong_college() != null &&
	 * bysjglxt_teacher_user.getUser_teacher_belong_college().trim().length() >= 0)
	 * { return bysjglxt_teacher_user.getUser_teacher_belong_college().trim(); }
	 * return null; }
	 */

	/**
	 * OK
	 */
	@Override
	public boolean saveTeacherList(List<bysjglxt_teacher_basic> teacherBasicList, String userId) {
		// 获取操作人是哪个学院的
		String college = collegeJudge(1, userId);
		//
		boolean flag = false;
		bysjglxt_teacher_user bysjglxt_teacher_user = null;
		bysjglxt_section bysjglxt_section = null;
		for (bysjglxt_teacher_basic bysjglxt_teacher_basic : teacherBasicList) {
			bysjglxt_section = new bysjglxt_section();
			bysjglxt_teacher_user = new bysjglxt_teacher_user();
			bysjglxt_teacher_basic.setTeacher_basic_id(TeamUtil.getUuid());
			bysjglxt_teacher_basic.setTeacher_basic_gmt_create(TeamUtil.getStringSecond());
			bysjglxt_teacher_basic.setTeacher_basic_gmt_modified(bysjglxt_teacher_basic.getTeacher_basic_gmt_create());
			/**
			 * 根据工号判断教师是否存在
			 */
			if (teacherInformationManagementDao.teacherBasicIsExist(bysjglxt_teacher_basic.getJob_number())) {
				continue;
			}
			flag = teacherInformationManagementDao.saveTeacherBasic(bysjglxt_teacher_basic);
			// 有关教研室：根据任教专业代码获取教研室，如果没有则填写无。
			if (bysjglxt_teacher_basic.getTeaching_profession_no() != null
					&& bysjglxt_teacher_basic.getTeaching_profession_no().trim().length() > 0) {
				bysjglxt_section = teacherInformationManagementDao
						.getSectionByMajorCode(bysjglxt_teacher_basic.getTeaching_profession_no().trim());
				if (bysjglxt_section == null) {
					bysjglxt_teacher_user.setUser_teacher_section("");
				} else {
					if (bysjglxt_section.getSection_id() != null
							&& bysjglxt_section.getSection_id().trim().length() > 0) {
						bysjglxt_teacher_user.setUser_teacher_section(bysjglxt_section.getSection_id().trim());
					} else {
						bysjglxt_teacher_user.setUser_teacher_section("");
					}
				}
			} else {
				bysjglxt_teacher_user.setUser_teacher_section("");
			}
			bysjglxt_teacher_user.setUser_teacher_id(TeamUtil.getUuid());
			bysjglxt_teacher_user.setUser_teacher_guidance_num(0);
			// 工号
			bysjglxt_teacher_user.setUser_teacher_num(bysjglxt_teacher_basic.getJob_number());
			// 密码
			bysjglxt_teacher_user.setUser_teacher_password(md5.GetMD5Code(bysjglxt_teacher_user.getUser_teacher_num()));
			bysjglxt_teacher_user.setUser_teacher_basic(bysjglxt_teacher_basic.getTeacher_basic_id());
			bysjglxt_teacher_user.setUser_teacher_max_guidance(-1);
			bysjglxt_teacher_user.setUser_teacher_belong_college(college);
			bysjglxt_teacher_user.setUser_teacher_gmt_create(TeamUtil.getStringSecond());
			bysjglxt_teacher_user.setUser_teacher_gmt_modified(bysjglxt_teacher_user.getUser_teacher_gmt_create());
			bysjglxt_teacher_user.setUser_teacher_is_recorder(2);
			bysjglxt_teacher_user.setUser_teacher_is_defence_leader(2);
			flag = teacherInformationManagementDao.saveTeacher(bysjglxt_teacher_user);
			if (!flag)
				break;
			createTeacherNotice(bysjglxt_teacher_user,userId);
		}
		return flag;
	}

	/**
	 * 创建老师的时候生成的通知
	 * @param bysjglxt_teacher_user
	 */
	public void createTeacherNotice(bysjglxt_teacher_user bysjglxt_teacher_user,String userId) {
		bysjglxt_notice notice = new bysjglxt_notice();
		notice.setNotice_id(TeamUtil.getUuid());
		notice.setNotice_launch(userId);
		notice.setNotice_belong(bysjglxt_teacher_user.getUser_teacher_id());
		notice.setNotice_content((String)(properties.get("createTeacher")));
		notice.setNotice_leixing(4);
		notice.setNotice_state(2);
		notice.setNotice_gmt_create(TeamUtil.getStringSecond());
		notice.setNotice_gmt_modified(notice.getNotice_gmt_create());
		teacherInformationManagementDao.saveObj(notice);
	}
	
	
	// 根据user Id以及用户角色身份获取用户是哪个系的
	public String collegeJudge(int studentOrTeacher, String userId) {
		// 老师
		if (studentOrTeacher == 1) {
			bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
			bysjglxt_teacher_user = teacherInformationManagementDao.getStudentById(userId);
			if (bysjglxt_teacher_user == null) {
				return null;
			}
			if (bysjglxt_teacher_user.getUser_teacher_belong_college() != null
					&& bysjglxt_teacher_user.getUser_teacher_belong_college().trim().length() > 0) {
				return bysjglxt_teacher_user.getUser_teacher_belong_college();
			}
		} else {
			// 学生
			bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
			bysjglxt_student_user = teacherInformationManagementDao.getStudentByUserId(userId);
			if (bysjglxt_student_user == null) {
				return null;
			}
			if (bysjglxt_student_user.getUser_student_belong_college() != null
					&& bysjglxt_student_user.getUser_student_belong_college().trim().length() > 0) {
				return bysjglxt_student_user.getUser_student_belong_college();
			}
		}
		return null;
	}

	/**
	 * 
	 */
	@Override
	public List<TeacherInformationDTO> list_TeacherInformationDTO_All(String userId, String colle, int role) {
		String college = "";
		if (colle != null && colle.trim().length() > 0) {
			college = colle;
		} else {
			if (role == 1) {
				college = collegeJudge(1, userId);
			} else {
				college = collegeJudge(2, userId);
			}
		}
		List<TeacherInformationDTO> list_TeacherInformationDTO_All = new ArrayList<TeacherInformationDTO>();
		TeacherInformationDTO teacherInformationDTO = null;
		List<bysjglxt_teacher_user> listAllTeacherUserInformation = teacherInformationManagementDao
				.list_TeacherUserInformation_All(college);

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
	public boolean save_NewTeacher(bysjglxt_teacher_basic teacher_basic,bysjglxt_section section, String userId) {
		String college = collegeJudge(1, userId);
		bysjglxt_college col = new bysjglxt_college();
		col = teacherInformationManagementDao.getCollegeById(college);
		boolean flag = false;
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_section bysjglxt_section = null;
		teacher_basic.setTeacher_basic_id(TeamUtil.getUuid());
		teacher_basic.setUnit_number(col.getCollege_code());
		teacher_basic.setUnit_name(col.getCollege_name());
		teacher_basic.setTeacher_basic_gmt_create(TeamUtil.getStringSecond());
		teacher_basic.setTeacher_basic_gmt_modified(teacher_basic.getTeacher_basic_gmt_create());
		flag = teacherInformationManagementDao.teacherBasicIsExist(teacher_basic.getJob_number());
		if (flag) {
			return false;
		}
		
		//保存
		flag = teacherInformationManagementDao.saveTeacherBasic(teacher_basic);
		// 有关教研室：根据任教专业代码获取教研室，如果没有则填写无
		if(section==null) {
			if (teacher_basic.getTeaching_profession_no() != null
					&& teacher_basic.getTeaching_profession_no().trim().length() > 0) {
				bysjglxt_section = teacherInformationManagementDao
						.getSectionByMajorCode(teacher_basic.getTeaching_profession_no().trim());
				if (bysjglxt_section == null) {
					bysjglxt_teacher_user.setUser_teacher_section("");
				} else {
					if (bysjglxt_section.getSection_id() != null && bysjglxt_section.getSection_id().trim().length() > 0) {
						bysjglxt_teacher_user.setUser_teacher_section(bysjglxt_section.getSection_id().trim());
					} else {
						bysjglxt_teacher_user.setUser_teacher_section("");
					}
				}
			} else {
				bysjglxt_teacher_user.setUser_teacher_section("");
			}
		}else {
			bysjglxt_teacher_user.setUser_teacher_section(section.getSection_id());
		}
		bysjglxt_teacher_user.setUser_teacher_id(TeamUtil.getUuid());
		bysjglxt_teacher_user.setUser_teacher_guidance_num(0);
		// 工号
		bysjglxt_teacher_user.setUser_teacher_num(teacher_basic.getJob_number());
		// 密码
		bysjglxt_teacher_user.setUser_teacher_password(md5.GetMD5Code("000000"));
		bysjglxt_teacher_user.setUser_teacher_basic(teacher_basic.getTeacher_basic_id());
		// 教研室未给予
		bysjglxt_teacher_user.setUser_teacher_max_guidance(-1);
		bysjglxt_teacher_user.setUser_teacher_belong_college(college);
		bysjglxt_teacher_user.setUser_teacher_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_teacher_user.setUser_teacher_gmt_modified(bysjglxt_teacher_user.getUser_teacher_gmt_create());
		bysjglxt_teacher_user.setUser_teacher_is_recorder(2);
		bysjglxt_teacher_user.setUser_teacher_is_defence_leader(2);
		flag = teacherInformationManagementDao.saveTeacher(bysjglxt_teacher_user);
		createTeacherNotice(bysjglxt_teacher_user,userId);
		return flag;
	}

	/**
	 * OK
	 */
	@Override
	public boolean remove_TeacherList(List<String> useTeacherIdList, String userId) {
		boolean flag = false;
		for (String teacher_user_id : useTeacherIdList) {
			if (teacher_user_id.equals(userId)) {
				continue;
			}
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
			TeacherInformationManagementVO teacherInformationManagementVO, String userId) {
		// 根据user Id获取学院
		String college = collegeJudge(1, userId);
		List<bysjglxt_teacher_basic> sizeList = teacherInformationManagementDao
				.getResultBySearch(teacherInformationManagementVO, college);
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
				.listTeacherBasicInformationByPageAndSearch(teacherInformationManagementVO, college);
		for (bysjglxt_teacher_basic bysjglxt_teacher_basic : listTeacherBasicInformationByPage) {
			bysjglxt_section = new bysjglxt_section();
			teacherInformationDTO = new TeacherInformationDTO();
			bysjglxt_teacher_user = teacherInformationManagementDao
					.getTeacherInfoByBasicId(bysjglxt_teacher_basic.getTeacher_basic_id());
			if (bysjglxt_teacher_user.getUser_teacher_section() != null
					&& bysjglxt_teacher_user.getUser_teacher_section().trim().length() > 0) {
				bysjglxt_section = teacherInformationManagementDao
						.get_TeacherSectionInformation_ByUserSectionId(bysjglxt_teacher_user.getUser_teacher_section());
			}
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
	public List<bysjglxt_section> listBysjglxtSection(String userId) {
		String college = collegeJudge(1, userId);
		return teacherInformationManagementDao.listBysjglxtSection(college);
	}

	/**
	 * 弃用
	 */
	@Override
	public boolean updateBasicAndUser(TeacherInformationDTO teacherInformationDTO) {
		// 修改基础表信息
		boolean flag = false;
		teacherInformationDTO.getBysjglxtTeacherBasic().setTeacher_basic_gmt_modified(TeamUtil.getStringSecond());
		flag = teacherInformationManagementDao.updateBasic(teacherInformationDTO.getBysjglxtTeacherBasic());
		if (!flag)
			return flag;
		teacherInformationDTO.getBysjglxtTeacherUser().setUser_teacher_gmt_modified(TeamUtil.getStringSecond());
		flag = teacherInformationManagementDao.updateUser(teacherInformationDTO.getBysjglxtTeacherUser());
		if (!flag)
			return flag;
		return flag;
	}

	/**
	 * 修改教师基础表信息
	 */
	@Override
	public boolean updateTeacherBasic(bysjglxt_teacher_basic bysjglxt_teacher_basic,bysjglxt_teacher_user bysjglxt_teacher_user) {
		//
		if(bysjglxt_teacher_basic ==null || bysjglxt_teacher_basic.getTeacher_basic_id()==null || "".equals(bysjglxt_teacher_basic.getTeacher_basic_id())) {
			return false;
		}
		//
		bysjglxt_teacher_basic teacherBasic = new bysjglxt_teacher_basic();
		bysjglxt_teacher_user teacherUser = new bysjglxt_teacher_user();
		teacherBasic = teacherInformationManagementDao.get_TeacherBasicInformation_ByUserBasic(bysjglxt_teacher_basic.getTeacher_basic_id());
		if(teacherBasic == null) {
			return false;
		}
		teacherBasic.setName(bysjglxt_teacher_basic.getName());
		teacherBasic.setSex(bysjglxt_teacher_basic.getSex());
		teacherBasic.setBirthday(bysjglxt_teacher_basic.getBirthday());
		teacherBasic.setInduction_date(bysjglxt_teacher_basic.getInduction_date());
		teacherBasic.setJob_statue(bysjglxt_teacher_basic.getJob_statue());
		teacherBasic.setProfessional_title(bysjglxt_teacher_basic.getProfessional_title());
		teacherBasic.setHighest_education(bysjglxt_teacher_basic.getHighest_education());
		teacherBasic.setHighest_degree(bysjglxt_teacher_basic.getHighest_degree());
		teacherBasic.setQq(bysjglxt_teacher_basic.getQq());
		teacherBasic.setPhone(bysjglxt_teacher_basic.getPhone());
		teacherBasic.setTeacher_basic_gmt_modified(TeamUtil.getStringSecond());
		teacherInformationManagementDao.saveTeacherBasic(teacherBasic);
		teacherUser = teacherInformationManagementDao.getTeacherInfoByBasicId(teacherBasic.getTeacher_basic_id());
		teacherUser.setUser_teacher_section(bysjglxt_teacher_user.getUser_teacher_section());
		teacherUser.setUser_teacher_max_guidance(bysjglxt_teacher_user.getUser_teacher_max_guidance());
		teacherUser.setUser_teacher_gmt_modified(TeamUtil.getStringSecond());
		teacherInformationManagementDao.saveTeacher(teacherUser);
		return true;
	}

	@Override
	public boolean updateTeacherUser(bysjglxt_teacher_user bysjglxt_teacher_user) {
		bysjglxt_teacher_user.setUser_teacher_gmt_modified(TeamUtil.getStringSecond());
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
			bysjglxt_teacher_user.setUser_teacher_password(md5.GetMD5Code(bysjglxt_teacher_user.getUser_teacher_num()));
			bysjglxt_teacher_user.setUser_teacher_gmt_modified(TeamUtil.getStringSecond());
			flag = teacherInformationManagementDao.saveTeacher(bysjglxt_teacher_user);
		}
		return flag;
	}

	@Override
	public int updatePassword(String user_teacher_id, String password, String oldPassword) {
		if (user_teacher_id == null || user_teacher_id.trim().length() <= 0) {
			return -1;
		}
		if (password == null || password.trim().length() <= 0) {
			return -1;
		}
		if (oldPassword == null || oldPassword.trim().length() <= 0) {
			return -1;
		}
		bysjglxt_teacher_user teacherUser = new bysjglxt_teacher_user();
		teacherUser = teacherInformationManagementDao.getStudentById(user_teacher_id);
		if (teacherUser != null) {
			if (teacherUser.getUser_teacher_password() != null
					&& !(md5.GetMD5Code(oldPassword).equals(teacherUser.getUser_teacher_password()))) {
				return -1;
			}
		}
		teacherInformationManagementDao.updatePassword(user_teacher_id, md5.GetMD5Code(password),
				TeamUtil.getStringSecond());
		return 1;
	}

	// 批量增加记录员
	@Override
	public int addRecorder(List<String> listTeacherUserId) {
		boolean flag = false;
		bysjglxt_teacher_user bysjglxt_teacher_user = null;
		for (String string : listTeacherUserId) {
			bysjglxt_teacher_user = new bysjglxt_teacher_user();
			// 先根据userId获取teacher_user对象
			bysjglxt_teacher_user = teacherInformationManagementDao.getStudentById(string);
			if (bysjglxt_teacher_user != null) {
				bysjglxt_teacher_user.setUser_teacher_is_recorder(1);
				bysjglxt_teacher_user.setUser_teacher_gmt_modified(TeamUtil.getStringSecond());
				flag = teacherInformationManagementDao.saveTeacher(bysjglxt_teacher_user);
				if (!flag)
					return -1;
			}
		}
		return 1;
	}

	/**
	 * 批量增加领导者信息
	 */
	@Override
	public int addLeader(List<String> listTeacherUserId) {
		boolean flag = false;
		bysjglxt_teacher_user bysjglxt_teacher_user = null;
		for (String string : listTeacherUserId) {
			bysjglxt_teacher_user = new bysjglxt_teacher_user();
			// 先根据userId获取teacher_user对象
			bysjglxt_teacher_user = teacherInformationManagementDao.getStudentById(string);
			if (bysjglxt_teacher_user != null) {
				bysjglxt_teacher_user.setUser_teacher_is_defence_leader(1);
				bysjglxt_teacher_user.setUser_teacher_gmt_modified(TeamUtil.getStringSecond());
				flag = teacherInformationManagementDao.saveTeacher(bysjglxt_teacher_user);
				if (!flag)
					return -1;
			}
		}
		return 1;
	}

	/**
	 * 批量驳回记录员身份
	 */
	@Override
	public int removeRecoder(List<String> listTeacherUserId) {
		boolean flag = false;
		bysjglxt_teacher_user bysjglxt_teacher_user = null;
		for (String string : listTeacherUserId) {
			bysjglxt_teacher_user = new bysjglxt_teacher_user();
			// 先根据userId获取teacher_user对象
			bysjglxt_teacher_user = teacherInformationManagementDao.getStudentById(string);
			if (bysjglxt_teacher_user != null) {
				bysjglxt_teacher_user.setUser_teacher_is_recorder(2);
				bysjglxt_teacher_user.setUser_teacher_gmt_modified(TeamUtil.getStringSecond());
				flag = teacherInformationManagementDao.saveTeacher(bysjglxt_teacher_user);
				if (!flag)
					return -1;
			}
		}
		return 1;
	}

	/**
	 * 批量驳回答辩小组长
	 */
	@Override
	public int removeLeader(List<String> listTeacherUserId) {
		boolean flag = false;
		bysjglxt_teacher_user bysjglxt_teacher_user = null;
		for (String string : listTeacherUserId) {
			bysjglxt_teacher_user = new bysjglxt_teacher_user();
			// 先根据userId获取teacher_user对象
			bysjglxt_teacher_user = teacherInformationManagementDao.getStudentById(string);
			if (bysjglxt_teacher_user != null) {
				bysjglxt_teacher_user.setUser_teacher_is_defence_leader(2);
				bysjglxt_teacher_user.setUser_teacher_gmt_modified(TeamUtil.getStringSecond());
				flag = teacherInformationManagementDao.saveTeacher(bysjglxt_teacher_user);
				if (!flag)
					return -1;
			}
		}
		return 1;
	}

}
