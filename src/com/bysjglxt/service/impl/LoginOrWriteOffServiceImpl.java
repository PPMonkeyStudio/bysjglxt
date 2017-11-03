package com.bysjglxt.service.impl;

import com.bysjglxt.dao.LoginOrWriteOffDao;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.service.LoginOrWriteOffService;

import util.md5;

public class LoginOrWriteOffServiceImpl implements LoginOrWriteOffService {

	private LoginOrWriteOffDao loginOrWriteOffDao;

	public void setLoginOrWriteOffDao(LoginOrWriteOffDao loginOrWriteOffDao) {
		this.loginOrWriteOffDao = loginOrWriteOffDao;
	}

	@Override
	public int login(String username, String password) {
		if (username == null || username.trim().length() <= 0) {
			return -1;
		}
		if (password == null || password.trim().length() <= 0) {
			return -2;
		}
		int flag = 0;
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		// 1.在student_user表中查询
		bysjglxt_student_user = loginOrWriteOffDao.getBysjglxtStudentUserByNum(username);
		if (bysjglxt_student_user == null) {
			flag = -1;
		} else {
			if (bysjglxt_student_user.getUser_student_password().equals(md5.GetMD5Code(password))) {
				flag = 2;
				return flag;
			} else {
				flag = -2;
				return flag;
			}
		}
		// 2.在teacher_user表中查询
		bysjglxt_teacher_user = loginOrWriteOffDao.getBysjglxtTeacherUserByNum(username);
		if (bysjglxt_teacher_user == null) {
			flag = -1;
		} else {
			if (bysjglxt_teacher_user.getUser_teacher_password().equals(md5.GetMD5Code(password))) {
				flag = 1;
				return flag;
			} else {
				flag = -2;
				return flag;
			}
		}
		return flag;
	}

	@Override
	public Object loginInformation(int role, String username) {
		// 如果等于1，则说明是教师进行登录
		if (role == 1) {
			TeacherInformationDTO teacherInformationDTO = new TeacherInformationDTO();
			bysjglxt_teacher_basic bysjglxtTeacherBasic = new bysjglxt_teacher_basic();
			bysjglxt_teacher_user bysjglxtTeacherUser = new bysjglxt_teacher_user();
			bysjglxt_section bysjglxtSection = new bysjglxt_section();
			bysjglxtTeacherUser = loginOrWriteOffDao.getBysjglxtTeacherUserByNum(username);
			
			if (bysjglxtTeacherUser != null) {
				bysjglxtTeacherBasic = loginOrWriteOffDao
						.getBysjglxtTeacherBasicById(bysjglxtTeacherUser.getUser_teacher_basic());
			}
			if (bysjglxtTeacherUser != null) {
				bysjglxtSection = loginOrWriteOffDao
						.getBysjglxtTeacherSection(bysjglxtTeacherUser.getUser_teacher_section());
			}
			teacherInformationDTO.setBysjglxtSection(bysjglxtSection);
			teacherInformationDTO.setBysjglxtTeacherBasic(bysjglxtTeacherBasic);
			teacherInformationDTO.setBysjglxtTeacherUser(bysjglxtTeacherUser);
			return teacherInformationDTO;
		} else {
			StudentInformationDTO studentInformationDTO = new StudentInformationDTO();
			bysjglxt_student_basic bysjglxtStudentBasic = new bysjglxt_student_basic();
			bysjglxt_student_user bysjglxtStudentUser = new bysjglxt_student_user();
			bysjglxtStudentUser = loginOrWriteOffDao.getBysjglxtStudentUserByNum(username);
			if (bysjglxtStudentUser != null) {
				bysjglxtStudentBasic = loginOrWriteOffDao
						.getBysjglxtStudentBasicById(bysjglxtStudentUser.getUser_student_basic());
			}
			studentInformationDTO.setBysjglxtStudentBasic(bysjglxtStudentBasic);
			studentInformationDTO.setBysjglxtStudentUser(bysjglxtStudentUser);
			return studentInformationDTO;
		}
	}
}
