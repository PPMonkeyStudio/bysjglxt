package com.bysjglxt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bysjglxt.dao.StudentInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_college;
import com.bysjglxt.domain.DO.bysjglxt_defence;
import com.bysjglxt.domain.DO.bysjglxt_dissertation;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_major;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;
import com.bysjglxt.domain.DO.bysjglxt_taskbook;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.DTO.ExportGeaduationStudentDTO;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.VO.StudentInformationManagementVO;
import com.bysjglxt.service.StudentInformationManagementService;

import util.ExcelToBean2;
import util.TeamUtil;
import util.md5;

public class StudentInformationManagementServiceImpl implements StudentInformationManagementService {

	// Dao层的注入
	private StudentInformationManagementDao studentInformationManagementDao;

	public void setStudentInformationManagementDao(StudentInformationManagementDao studentInformationManagementDao) {
		this.studentInformationManagementDao = studentInformationManagementDao;
	}

	@Override
	public List<bysjglxt_student_basic> convertStudentExcelToList(File studentExcel, String EXCEL_StudentFileName)
			throws Exception {
		String houzhui = EXCEL_StudentFileName.substring(EXCEL_StudentFileName.lastIndexOf(".") + 1);
		FileInputStream input = new FileInputStream(studentExcel);
		List<Map<String, Object>> list = null;
		if ("xlsx".equals(houzhui)) {
			XSSFWorkbook workbook = new XSSFWorkbook(input);
			list = ExcelToBean2.parseUpdateExcel(workbook, "bysjglxt_student_basic");
		} else {
			HSSFWorkbook workbook = new HSSFWorkbook(input);
			list = ExcelToBean2.parseExcel(workbook, "bysjglxt_student_basic");
		}
		List<bysjglxt_student_basic> lists = ExcelToBean2.toObjectPerproList(list, bysjglxt_student_basic.class);
		return lists;
	}

	@Override
	public boolean saveStudentList(List<bysjglxt_student_basic> studentBasicList, String userId) {
		boolean flag = false;
		bysjglxt_student_user bysjglxt_student_user = null;
		bysjglxt_major bysjglxt_major = null;
		String college = getCollegeByUserId(userId);
		for (bysjglxt_student_basic bysjglxt_student_basic : studentBasicList) {
			bysjglxt_student_user = new bysjglxt_student_user();
			bysjglxt_major = new bysjglxt_major();
			/**
			 * 根据学号判断该学生 是否存在，若存在则不进行保存
			 */
			if (studentInformationManagementDao.studentBasicIsExist(bysjglxt_student_basic.getStudent_basic_num())) {
				continue;
			}
			bysjglxt_student_basic.setStudent_basic_id(TeamUtil.getUuid());
			flag = studentInformationManagementDao.saveStudentBasic(bysjglxt_student_basic);
			// 判断专业是否存在于数据库中,使用专业代码进行比较
			if (bysjglxt_student_basic.getStudent_basic_professionalcode() != null
					&& bysjglxt_student_basic.getStudent_basic_professionalcode().trim().length() > 0) {
				// 根据专业代码在数据库中进行查询
				bysjglxt_major = new bysjglxt_major();
				bysjglxt_major = studentInformationManagementDao
						.getMajorByCode(bysjglxt_student_basic.getStudent_basic_professionalcode().trim());
				if (bysjglxt_major == null) {
					bysjglxt_major = new bysjglxt_major();
					bysjglxt_major.setMajor_id(TeamUtil.getUuid());
					bysjglxt_major.setMajor_professionalcode(
							bysjglxt_student_basic.getStudent_basic_professionalcode().trim());
					bysjglxt_major.setMajor_name(bysjglxt_student_basic.getStudent_basic_major());
					bysjglxt_major.setMajor_belong_section("无");
					bysjglxt_major.setMajor_gmt_create(TeamUtil.getStringSecond());
					bysjglxt_major.setMajor_gmt_modified(bysjglxt_major.getMajor_gmt_create());
					flag = studentInformationManagementDao.saveObject(bysjglxt_major);
				}
			}
			bysjglxt_student_user.setUser_student_id(TeamUtil.getUuid());
			bysjglxt_student_user.setUser_student_num(bysjglxt_student_basic.getStudent_basic_num());
			bysjglxt_student_user.setUser_student_belong_major(bysjglxt_major.getMajor_id());
			bysjglxt_student_user
					.setUser_student_password(md5.GetMD5Code(bysjglxt_student_basic.getStudent_basic_num()));
			bysjglxt_student_user.setUser_student_is_select_topic(2);
			bysjglxt_student_user.setUser_student_basic(bysjglxt_student_basic.getStudent_basic_id());
			bysjglxt_student_user.setUser_student_is_operate_premission(1);
			bysjglxt_student_user.setUser_student_belong_college(college);
			bysjglxt_student_user.setUser_student_gmt_create(TeamUtil.getStringSecond());
			bysjglxt_student_user.setUser_student_gmt_modified(bysjglxt_student_user.getUser_student_gmt_create());
			flag = studentInformationManagementDao.saveStudent(bysjglxt_student_user);
			if (!flag)
				break;
		}
		return flag;
	}

	@Override
	public List<StudentInformationDTO> list_StudentInformationDTO_All() {
		List<StudentInformationDTO> list_StudentInformationDTO_All = new ArrayList<StudentInformationDTO>();
		StudentInformationDTO studentInformationDTO = null;
		List<bysjglxt_student_user> listAllStudentUserInformation = studentInformationManagementDao
				.list_StudentUserInformation_All();
		for (bysjglxt_student_user student_user : listAllStudentUserInformation) {
			studentInformationDTO = new StudentInformationDTO();
			studentInformationDTO.setBysjglxtStudentUser(student_user);
			studentInformationDTO.setBysjglxtStudentBasic(studentInformationManagementDao
					.get_StudentBasicInformation_ByUserBasic(student_user.getUser_student_basic()));
			list_StudentInformationDTO_All.add(studentInformationDTO);
		}

		return list_StudentInformationDTO_All;
	}

	@Override
	public boolean save_NewStudent(bysjglxt_student_basic student_basic, String userId) {
		String college = getCollegeByUserId(userId);
		boolean flag = false;
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		student_basic.setStudent_basic_id(TeamUtil.getStringSecond());
		bysjglxt_major bysjglxt_major = new bysjglxt_major();
		flag = studentInformationManagementDao.studentBasicIsExist(student_basic.getStudent_basic_num());
		if (flag) {
			return false;
		}
		flag = studentInformationManagementDao.saveStudentBasic(student_basic);
		// 判断专业是否存在于数据库中,使用专业代码进行比较
		if (student_basic.getStudent_basic_professionalcode() != null
				&& student_basic.getStudent_basic_professionalcode().trim().length() > 0) {
			// 根据专业代码在数据库中进行查询
			bysjglxt_major = new bysjglxt_major();
			bysjglxt_major = studentInformationManagementDao
					.getMajorByCode(student_basic.getStudent_basic_professionalcode().trim());
			if (bysjglxt_major == null) {
				bysjglxt_major = new bysjglxt_major();
				bysjglxt_major.setMajor_id(TeamUtil.getUuid());
				bysjglxt_major.setMajor_professionalcode(student_basic.getStudent_basic_professionalcode().trim());
				bysjglxt_major.setMajor_name(student_basic.getStudent_basic_major());
				bysjglxt_major.setMajor_belong_section("无");
				bysjglxt_major.setMajor_gmt_create(TeamUtil.getStringSecond());
				bysjglxt_major.setMajor_gmt_modified(bysjglxt_major.getMajor_gmt_create());
				flag = studentInformationManagementDao.saveObject(bysjglxt_major);
			}
		}

		bysjglxt_student_user.setUser_student_id(TeamUtil.getUuid());
		bysjglxt_student_user.setUser_student_num(student_basic.getStudent_basic_num());
		bysjglxt_student_user.setUser_student_password(md5.GetMD5Code(student_basic.getStudent_basic_num()));
		bysjglxt_student_user.setUser_student_basic(student_basic.getStudent_basic_id());
		bysjglxt_student_user.setUser_student_is_select_topic(2);
		bysjglxt_student_user.setUser_student_is_operate_premission(1);
		bysjglxt_student_user.setUser_student_belong_major(bysjglxt_major.getMajor_id());
		bysjglxt_student_user.setUser_student_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_student_user.setUser_student_belong_college(college);
		bysjglxt_student_user.setUser_student_gmt_modified(bysjglxt_student_user.getUser_student_gmt_create());
		flag = studentInformationManagementDao.saveStudent(bysjglxt_student_user);
		return flag;
	}

	@Override
	public boolean remove_StudentList(List<String> useStudentIDList) {
		boolean flag = false;
		for (String student_num : useStudentIDList) {
			bysjglxt_student_user bysjglxt_student_user = studentInformationManagementDao.getStudentByNum(student_num);
			flag = studentInformationManagementDao
					.deleteStudentBasicInfoById(bysjglxt_student_user.getUser_student_basic());
			flag = studentInformationManagementDao.deleteStudentInfoById(bysjglxt_student_user.getUser_student_id());
		}
		return flag;
	}

	@Override
	public StudentInformationManagementVO VO_Student_By_PageAndSearch(
			StudentInformationManagementVO studentInformationManagementVO, String userId) {
		String college = getCollegeByUserId(userId);
		List<bysjglxt_student_basic> sizeList = studentInformationManagementDao
				.getResultBySearch(studentInformationManagementVO, college);
		int i = sizeList.size();
		studentInformationManagementVO.setTotalRecords(i);
		studentInformationManagementVO.setTotalPages(((i - 1) / studentInformationManagementVO.getPageSize()) + 1);
		if (studentInformationManagementVO.getPageIndex() <= 1) {
			studentInformationManagementVO.setHavePrePage(false);
		} else {
			studentInformationManagementVO.setHavePrePage(true);
		}
		if (studentInformationManagementVO.getPageIndex() >= studentInformationManagementVO.getTotalPages()) {
			studentInformationManagementVO.setHaveNextPage(false);
		} else {
			studentInformationManagementVO.setHaveNextPage(true);
		}
		List<StudentInformationDTO> listStudentInformationDTO = new ArrayList<StudentInformationDTO>();
		StudentInformationDTO studentInformationDTO = null;
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		List<bysjglxt_student_basic> listStudentBasicInformationByPage = studentInformationManagementDao
				.listStudentBasicInformationByPageAndSearch(studentInformationManagementVO, college);
		for (bysjglxt_student_basic bysjglxt_student_basic : listStudentBasicInformationByPage) {
			studentInformationDTO = new StudentInformationDTO();
			bysjglxt_student_user = studentInformationManagementDao
					.getStudentInfoByBasicId(bysjglxt_student_basic.getStudent_basic_id());
			studentInformationDTO.setBysjglxtStudentUser(bysjglxt_student_user);
			studentInformationDTO.setBysjglxtStudentBasic(bysjglxt_student_basic);
			listStudentInformationDTO.add(studentInformationDTO);
		}
		studentInformationManagementVO.setList_StudentInformationDTO(listStudentInformationDTO);

		return studentInformationManagementVO;
	}

	@Override
	public List<bysjglxt_major> list_Student_Major(String userId) throws Exception {
		String college = getCollegeByUserId(userId);
		return studentInformationManagementDao.listStudent_Major(college);
	}

	@Override
	public List<String> listStudentLevel() {
		return studentInformationManagementDao.listStudent_Level();
	}

	@Override
	public List<String> list_Student_Grade() throws Exception {
		return studentInformationManagementDao.listStudent_Grade();
	}

	// 打开学生
	@Override
	public boolean update_Give_Student_Operate_Permission(List<String> listString) {
		boolean flag = false;
		bysjglxt_student_user bysjglxt_student_user = null;
		bysjglxt_topic_select bysjglxt_topic_select = null;
		bysjglxt_taskbook bysjglxt_taskbook = null;
		bysjglxt_report_opening bysjglxt_report_opening = null;
		bysjglxt_summary summary = null;
		bysjglxt_examination_formal examinationFormal = null;
		bysjglxt_evaluate_tutor evaluateTutor = null;
		bysjglxt_evaluate_review evaluateReview = null;
		List<bysjglxt_process_instance> listProcessInstance = new ArrayList<>();
		List<bysjglxt_task_instance> listTaskInstance = new ArrayList<>();
		bysjglxt_dissertation dissertation = null;
		bysjglxt_defence defence = null;
		for (String string : listString) {
			listTaskInstance = new ArrayList<>();
			dissertation = new bysjglxt_dissertation();
			listProcessInstance = new ArrayList<>();
			bysjglxt_topic_select = new bysjglxt_topic_select();
			bysjglxt_student_user = new bysjglxt_student_user();
			bysjglxt_taskbook = new bysjglxt_taskbook();
			bysjglxt_report_opening = new bysjglxt_report_opening();
			List<bysjglxt_record_progress> listProgress = new ArrayList<bysjglxt_record_progress>();
			summary = new bysjglxt_summary();
			examinationFormal = new bysjglxt_examination_formal();
			evaluateTutor = new bysjglxt_evaluate_tutor();
			evaluateReview = new bysjglxt_evaluate_review();
			defence = new bysjglxt_defence();
			bysjglxt_student_user = studentInformationManagementDao.getStudentByNum(string);
			if (bysjglxt_student_user == null) {
				return false;
			}
			// 对于没有权限的重新开启，会将所有的对应的学生的所有数据进行删除
			if (bysjglxt_student_user.getUser_student_is_operate_premission() == 2) {
				// 将学生信息更改
				bysjglxt_student_user.setUser_student_is_select_topic(2);
				bysjglxt_student_user.setUser_student_is_operate_premission(1);
				bysjglxt_student_user.setUser_student_gmt_modified(TeamUtil.getStringSecond());
				flag = studentInformationManagementDao.saveObject(bysjglxt_student_user);
				// 1.删除该学生的选题
				bysjglxt_topic_select = studentInformationManagementDao
						.getTopicSelect(bysjglxt_student_user.getUser_student_id());
				if (bysjglxt_topic_select != null) {
					flag = studentInformationManagementDao
							.deleteTopicSelect(bysjglxt_topic_select.getTopic_select_id());
				}
				if (!flag)
					break;
				// 删除属于这个学生的毕业设计流程实例以及任务实例
				// 先删除任务实例
				listTaskInstance = studentInformationManagementDao
						.getTaskInstanceByProcessManId(bysjglxt_student_user.getUser_student_id());
				for (bysjglxt_task_instance bysjglxt_task_instance : listTaskInstance) {
					flag = studentInformationManagementDao
							.deleteTaskInstanceById(bysjglxt_task_instance.getTask_instance_id());
					if (!flag)
						break;
				}
				// 删除流程实例
				listProcessInstance = studentInformationManagementDao
						.getProcessInstanceByMan(bysjglxt_student_user.getUser_student_id());
				for (bysjglxt_process_instance bysjglxt_process_instance : listProcessInstance) {
					flag = studentInformationManagementDao
							.deleteProcessById(bysjglxt_process_instance.getProcess_instance_id());
					if (!flag)
						break;
				}
				// 删除毕业论文
				dissertation = studentInformationManagementDao
						.getDissertationByUserId(bysjglxt_student_user.getUser_student_id());
				if (dissertation != null) {
					if (dissertation.getDissertation_file() != null
							&& dissertation.getDissertation_file().trim().length() > 0) {
						/*
						 * 获取路径
						 */
						String lj = "";
						try {
							Properties props = new Properties();
							props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
							lj = props.getProperty("lj");
						} catch (Exception e) {
							e.printStackTrace();
						}
						String path = lj + "graduagtionThesi/";
						path = path + dissertation.getDissertation_id() + "_" + dissertation.getDissertation_file();
						File deleteFile = new File(path);
						if (deleteFile.exists()) {
							deleteFile.delete();
						}
						// 删除记录
						flag = studentInformationManagementDao
								.deleteDissertationById(dissertation.getDissertation_id());
						if (!flag)
							break;
					}
				}

				// 2.删除对应学生任务书表
				bysjglxt_taskbook = studentInformationManagementDao
						.getTaskBookByStudent(bysjglxt_student_user.getUser_student_id());
				if (bysjglxt_taskbook != null) {
					flag = studentInformationManagementDao.deleteTaskBookStudent(bysjglxt_taskbook.getTaskbook_id());
				}
				if (!flag)
					break;
				// 删除开题报告表
				bysjglxt_report_opening = studentInformationManagementDao
						.getReportOpening(bysjglxt_student_user.getUser_student_id());
				if (bysjglxt_report_opening != null) {
					flag = studentInformationManagementDao
							.deleteReportOpening(bysjglxt_report_opening.getReport_opening_id());
				}
				if (!flag)
					break;
				// 删除情况记录
				listProgress = studentInformationManagementDao.getProgress(bysjglxt_student_user.getUser_student_id());
				if (listProgress != null || listProgress.size() == 0) {
					for (bysjglxt_record_progress bysjglxt_record_progress : listProgress) {
						flag = studentInformationManagementDao
								.deleteProgress(bysjglxt_record_progress.getRecord_progress_id());
						if (!flag)
							break;
					}
				}
				if (!flag)
					break;
				// 删除个人学习总结
				summary = studentInformationManagementDao.getSummary(bysjglxt_student_user.getUser_student_id());
				if (summary != null) {
					flag = studentInformationManagementDao.deleteSummary(summary.getSummary_id());
				}
				if (!flag)
					break;
				// 删除形式审查表
				examinationFormal = studentInformationManagementDao
						.getExaminationFormal(bysjglxt_student_user.getUser_student_id());
				if (examinationFormal != null) {
					flag = studentInformationManagementDao
							.deleteExaminationFormal(examinationFormal.getExamination_formal_id());
				}
				if (!flag)
					break;
				// 删除指导教师评价表
				evaluateTutor = studentInformationManagementDao
						.getEvaluateTutor(bysjglxt_student_user.getUser_student_id());
				if (evaluateTutor != null) {
					flag = studentInformationManagementDao.deleteEvaluateTutor(evaluateTutor.getEvaluate_tutor_id());
				}
				if (!flag)
					break;
				// 删除评阅老师评价表
				evaluateReview = studentInformationManagementDao
						.getEvaluateReview(bysjglxt_student_user.getUser_student_id());
				if (evaluateReview != null) {
					flag = studentInformationManagementDao.deleteEvaluateReview(evaluateReview.getEvaluate_review_id());
				}
				if (!flag)
					break;
				// 删除答辩评分表
				defence = studentInformationManagementDao.getDefence(bysjglxt_student_user.getUser_student_id());
				if (defence != null) {
					flag = studentInformationManagementDao.deleteDefence(defence.getDefence_id());
				}
				if (!flag)
					break;
				flag = studentInformationManagementDao.update_Give_Student_Operate_Permission(string,
						TeamUtil.getStringSecond());
			} else {
				flag = studentInformationManagementDao.update_Give_Student_Operate_Permission(string,
						TeamUtil.getStringSecond());
			}
		}
		return flag;
	}

	/**
	 * 关闭学生权限操作 关闭学生权限： 1.将学生的状态更改为已关闭 2.如果学生已经选题,那么将该学生指导老师的指导学生数-1
	 */
	@Override
	public boolean update_Take_Student_Operate_Permission(List<String> listString) {
		bysjglxt_topic_select bysjglxt_topic_select = null;
		bysjglxt_teacher_user bysjglxt_teacher_user = null;
		boolean flag = false;
		for (String string : listString) {
			bysjglxt_teacher_user = new bysjglxt_teacher_user();
			bysjglxt_topic_select = new bysjglxt_topic_select();
			flag = studentInformationManagementDao.update_Take_Student_Operate_Permission(string,
					TeamUtil.getStringSecond());
			// 判断学生是否选题
			bysjglxt_topic_select = studentInformationManagementDao.getTopicSelect(string);
			if (bysjglxt_topic_select == null)
				continue;
			// 如果学生选题了,那么就将指导老师的指导学生数-1
			bysjglxt_teacher_user = studentInformationManagementDao
					.getTeacherUserById(bysjglxt_topic_select.getTopic_select_teacher_tutor());
			if (bysjglxt_teacher_user == null)
				continue;
			bysjglxt_teacher_user
					.setUser_teacher_guidance_num(bysjglxt_teacher_user.getUser_teacher_guidance_num() - 1);
			bysjglxt_teacher_user.setUser_teacher_gmt_modified(TeamUtil.getStringSecond());
			flag = studentInformationManagementDao.saveObject(bysjglxt_teacher_user);
		}
		return flag;
	}

	@Override
	public boolean update_StudentBasicInfomation(bysjglxt_student_basic bysjglxt_student_basic) {
		return studentInformationManagementDao.update_StudentBasicInfomation(bysjglxt_student_basic);
	}

	@Override
	public boolean resetPassword(String user_student_id) {
		boolean flag = false;
		if (user_student_id == null || user_student_id.trim().length() <= 0) {
			return false;
		}
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_user = studentInformationManagementDao.getStudentByNum(user_student_id);
		if (bysjglxt_student_user != null) {
			bysjglxt_student_user.setUser_student_password(md5.GetMD5Code(bysjglxt_student_user.getUser_student_num()));
			bysjglxt_student_user.setUser_student_gmt_modified(TeamUtil.getStringSecond());
			flag = studentInformationManagementDao.saveStudent(bysjglxt_student_user);
		}
		return flag;
	}

	@Override
	public int updatePassword(String user_student_id, String password, String oldPassword) {
		if (user_student_id == null || user_student_id.trim().length() <= 0) {
			return -1;
		}
		if (password == null || password.trim().length() <= 0) {
			return -1;
		}
		if (oldPassword == null || oldPassword.trim().length() <= 0) {
			return -1;
		}
		bysjglxt_student_user studentUser = new bysjglxt_student_user();
		studentUser = studentInformationManagementDao.getStudentByNum(user_student_id);
		if (studentUser != null) {
			if (studentUser.getUser_student_password() != null
					&& !(md5.GetMD5Code(oldPassword).equals(studentUser.getUser_student_password()))) {
				return -1;
			}
		}
		studentInformationManagementDao.updatePassword(user_student_id, md5.GetMD5Code(password),
				TeamUtil.getStringSecond());
		return 1;
	}

	@Override
	public List<StudentInformationDTO> listStudentNoClose(String userId) {
		List<StudentInformationDTO> listStudentNoClose = new ArrayList<StudentInformationDTO>();
		StudentInformationDTO studentInformationDTO = new StudentInformationDTO();
		bysjglxt_student_basic bysjglxtStudentBasic = new bysjglxt_student_basic();
		List<bysjglxt_student_user> listBysjglxtStudentUser = new ArrayList<bysjglxt_student_user>();
		String college = getCollegeByUserId(userId);
		// 查找出所有没有关闭的学生
		listBysjglxtStudentUser = studentInformationManagementDao.getListStudentByNotClose(college);
		for (bysjglxt_student_user bysjglxt_student_user : listBysjglxtStudentUser) {
			studentInformationDTO = new StudentInformationDTO();
			bysjglxtStudentBasic = new bysjglxt_student_basic();
			// 获取学生basic信息
			bysjglxtStudentBasic = studentInformationManagementDao
					.get_StudentBasicInformation_ByUserBasic(bysjglxt_student_user.getUser_student_basic());
			studentInformationDTO.setBysjglxtStudentBasic(bysjglxtStudentBasic);
			studentInformationDTO.setBysjglxtStudentUser(bysjglxt_student_user);
			listStudentNoClose.add(studentInformationDTO);
		}
		return listStudentNoClose;
	}

	// 根据使用者Id获取学院
	public String getCollegeByUserId(String userId) {
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_teacher_user = studentInformationManagementDao.getTeacherUserById(userId);
		if (bysjglxt_teacher_user.getUser_teacher_belong_college() != null
				&& bysjglxt_teacher_user.getUser_teacher_belong_college().trim().length() >= 0) {
			return bysjglxt_teacher_user.getUser_teacher_belong_college().trim();
		}
		return null;
	}

	// 导出可以导出毕业设计过程管理手册
	@Override
	public ExportGeaduationStudentDTO listStudentGreauation(ExportGeaduationStudentDTO exportGeaduationStudentDTO,
			String userId) {
		List<bysjglxt_student_user> listStudentUser = new ArrayList<>();
		List<StudentInformationDTO> listStudentInformationDTO = new ArrayList<>();
		StudentInformationDTO studentInformationDTO = new StudentInformationDTO();
		bysjglxt_student_basic studentBasic = new bysjglxt_student_basic();
		// 获取操作者学院
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_teacher_user = studentInformationManagementDao.getTeacherUserById(userId);
		if (bysjglxt_teacher_user.getUser_teacher_belong_college() == null
				|| bysjglxt_teacher_user.getUser_teacher_belong_college().trim().length() == 0) {
			return exportGeaduationStudentDTO;
		}
		// 获取可以进行导出的学生信息
		listStudentUser = studentInformationManagementDao.getListStudentByExport(exportGeaduationStudentDTO,
				bysjglxt_teacher_user.getUser_teacher_belong_college());
		// 遍历学生user信息拿到学生basic表的信息
		for (bysjglxt_student_user bysjglxt_student_user : listStudentUser) {
			// 学生basic信息
			studentInformationDTO = new StudentInformationDTO();
			studentBasic = new bysjglxt_student_basic();
			studentBasic = studentInformationManagementDao
					.get_StudentBasicInformation_ByUserBasic(bysjglxt_student_user.getUser_student_basic());
			studentInformationDTO.setBysjglxtStudentUser(bysjglxt_student_user);
			studentInformationDTO.setBysjglxtStudentBasic(studentBasic);
			listStudentInformationDTO.add(studentInformationDTO);
		}
		exportGeaduationStudentDTO.setListStudentInformationDTO(listStudentInformationDTO);
		return exportGeaduationStudentDTO;
	}

	/**
	 * 根据学生userId以及专业Id分配专业
	 */
	@Override
	public int distributionStudentMajor(String studentUserId, String majorId) {
		boolean flag = false;
		bysjglxt_major bysjglxt_major = new bysjglxt_major();
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		// 分配专业
		// 1.获取专业信息
		bysjglxt_major = studentInformationManagementDao.getMajorByMajorId(majorId);
		if (bysjglxt_major == null) {
			return -1;
		}
		// 2.更改user表中信息
		bysjglxt_student_user = studentInformationManagementDao.getStudentByNum(studentUserId);
		if (bysjglxt_student_user == null) {
			return -1;
		}
		bysjglxt_student_user.setUser_student_belong_major(bysjglxt_major.getMajor_id());
		bysjglxt_student_user.setUser_student_gmt_modified(TeamUtil.getStringSecond());
		flag = studentInformationManagementDao.saveObject(bysjglxt_student_user);
		if (!flag)
			return -1;
		if (bysjglxt_student_user.getUser_student_basic() != null
				&& bysjglxt_student_user.getUser_student_basic().trim().length() > 0) {
			bysjglxt_student_basic = studentInformationManagementDao
					.get_StudentBasicInformation_ByUserBasic(bysjglxt_student_user.getUser_student_basic().trim());
			if (bysjglxt_student_basic != null) {
				bysjglxt_student_basic.setStudent_basic_major(bysjglxt_major.getMajor_name());
				if (bysjglxt_major.getMajor_professionalcode() != null
						&& bysjglxt_major.getMajor_professionalcode().trim().length() > 0) {
					bysjglxt_student_basic
							.setStudent_basic_professionalcode(bysjglxt_major.getMajor_professionalcode().trim());
				}
				flag = studentInformationManagementDao.saveObject(bysjglxt_student_basic);
				if (!flag)
					return -1;
			}
		}
		return 1;
	}

}
