package com.bysjglxt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bysjglxt.dao.StudentInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_defence;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_taskbook;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.VO.StudentInformationManagementVO;
import com.bysjglxt.service.StudentInformationManagementService;

import util.ExcelToBean;
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
			list = ExcelToBean.parseUpdateExcel(workbook, "bysjglxt_student_basic");
		} else {
			HSSFWorkbook workbook = new HSSFWorkbook(input);
			list = ExcelToBean.parseExcel(workbook, "bysjglxt_student_basic");
		}
		List<bysjglxt_student_basic> lists = ExcelToBean.toObjectPerproList(list, bysjglxt_student_basic.class);
		return lists;
	}

	@Override
	public boolean saveStudentList(List<bysjglxt_student_basic> studentBasicList) {
		boolean flag = false;
		bysjglxt_student_user bysjglxt_student_user = null;
		for (bysjglxt_student_basic bysjglxt_student_basic : studentBasicList) {
			bysjglxt_student_user = new bysjglxt_student_user();
			/**
			 * 根据学号判断该学生 是否存在，若存在则不进行保存
			 */
			if (studentInformationManagementDao.studentBasicIsExist(bysjglxt_student_basic.getStudent_basic_num())) {
				continue;
			}
			bysjglxt_student_basic.setStudent_basic_id(TeamUtil.getUuid());
			flag = studentInformationManagementDao.saveStudentBasic(bysjglxt_student_basic);
			bysjglxt_student_user.setUser_student_id(TeamUtil.getUuid());
			bysjglxt_student_user.setUser_student_num(bysjglxt_student_basic.getStudent_basic_num());
			bysjglxt_student_user
					.setUser_student_password(md5.GetMD5Code(bysjglxt_student_basic.getStudent_basic_num()));
			bysjglxt_student_user.setUser_student_is_select_topic(2);
			bysjglxt_student_user.setUser_student_basic(bysjglxt_student_basic.getStudent_basic_id());
			bysjglxt_student_user.setUser_student_is_operate_premission(1);
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
	public boolean save_NewStudent(bysjglxt_student_basic student_basic) {
		boolean flag = false;
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		student_basic.setStudent_basic_id(TeamUtil.getStringSecond());
		flag = studentInformationManagementDao.studentBasicIsExist(student_basic.getStudent_basic_num());
		if (flag) {
			return false;
		}
		flag = studentInformationManagementDao.saveStudentBasic(student_basic);
		bysjglxt_student_user.setUser_student_id(TeamUtil.getUuid());
		bysjglxt_student_user.setUser_student_num(student_basic.getStudent_basic_num());
		bysjglxt_student_user.setUser_student_password(md5.GetMD5Code(student_basic.getStudent_basic_num()));
		bysjglxt_student_user.setUser_student_basic(student_basic.getStudent_basic_id());
		bysjglxt_student_user.setUser_student_is_select_topic(0);
		bysjglxt_student_user.setUser_student_is_operate_premission(1);
		bysjglxt_student_user.setUser_student_gmt_create(TeamUtil.getStringSecond());
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
			StudentInformationManagementVO studentInformationManagementVO) {
		List<bysjglxt_student_basic> sizeList = studentInformationManagementDao
				.getResultBySearch(studentInformationManagementVO);
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
				.listStudentBasicInformationByPageAndSearch(studentInformationManagementVO);
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
	public List<String> list_Student_Major() throws Exception {
		return studentInformationManagementDao.listStudent_Major();
	}

	@Override
	public List<String> listStudentLevel() {
		return studentInformationManagementDao.listStudent_Level();
	}
	
	
	@Override
	public List<String> list_Student_Grade() throws Exception {
		return studentInformationManagementDao.listStudent_Grade();
	}

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
		bysjglxt_defence defence = null;
		for (String string : listString) {
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
			if (bysjglxt_student_user.getUser_student_is_operate_premission() == 0) {

				// 1.删除该学生的选题
				bysjglxt_topic_select = studentInformationManagementDao
						.getTopicSelect(bysjglxt_student_user.getUser_student_id());
				if (bysjglxt_topic_select != null) {
					flag = studentInformationManagementDao
							.deleteTopicSelect(bysjglxt_topic_select.getTopic_select_id());
				}
				if (!flag)
					break;
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

	@Override
	public boolean update_Take_Student_Operate_Permission(List<String> listString) {
		boolean flag = false;
		for (String string : listString) {
			flag = studentInformationManagementDao.update_Take_Student_Operate_Permission(string,
					TeamUtil.getStringSecond());
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
	public boolean updatePassword(String user_student_id, String password) {
		boolean flag = false;
		if (user_student_id == null || user_student_id.trim().length() <= 0) {
			return false;
		}
		if (password == null || password.trim().length() <= 0) {
			return false;
		}
		flag = studentInformationManagementDao.updatePassword(user_student_id, md5.GetMD5Code(password),
				TeamUtil.getStringSecond());
		return flag;
	}

	

}
