package com.bysjglxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bysjglxt.dao.NoticeManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_notice;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.DTO.NoticeDTO;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.NoticeVO;
import com.bysjglxt.service.NoticeManagementService;

import util.TeamUtil;

public class NoticeManagementServiceImpl implements NoticeManagementService {
	private NoticeManagementDao noticeManagementDao;

	public void setNoticeManagementDao(NoticeManagementDao noticeManagementDao) {
		this.noticeManagementDao = noticeManagementDao;
	}


	@Override
	public List<NoticeDTO> getTaskByLiXing(String leixing, String userId) {
		List<NoticeDTO> list = new ArrayList<>();
		NoticeDTO noticeDTO = new NoticeDTO();
		List<bysjglxt_notice> listNotice = new ArrayList<>();
		listNotice = noticeManagementDao.getTaskByLiXing(leixing, userId);
		for (bysjglxt_notice bysjglxt_notice : listNotice) {
			noticeDTO = new NoticeDTO();
			if("系统管理员".equals(bysjglxt_notice.getNotice_launch())) {
				noticeDTO.setLaunchName("admin");
			}else {
				//判断是否是系部管理员
				bysjglxt_teacher_user teacher =noticeManagementDao.getTeacherUserById(bysjglxt_notice.getNotice_launch());
				if(teacher!=null && teacher.getUser_teacher_is_college_admin()==1) {
					noticeDTO.setLaunchName("admin");
				}else {
					noticeDTO.setLaunchName("other");
				}
			}
			noticeDTO.setBysjglxt_notice(bysjglxt_notice);
			list.add(noticeDTO);
		}
		return list;
	}

	/**
	 * 
	 */
	@Override
	public String sendMessage(String sendString, String message_content,TeacherInformationDTO teacherInfoDTO,StudentInformationDTO studentInfoDTO,String juese) {
		//根据规则获取sendString
		/**
		 * value			 含义
		 * AllStudentTeacher_  该学院所有师生
		 * AllTeacher_		   该学院所有老师
		 * AllStudent
		 * MyTutorTeacher_	  我的指导老师
		 * MyTutorStudent_    我指导的学生
		 * jiaoyanteacher_... 某个教研室的所有老师
		 * jiaoyanstudent_... 某个教研室的所有学生
		 * teacher_...		  某个老师的userId
		 * student_... 		 某个学生的userId
		 */
		if(sendString==null || sendString.trim().length()<=0) {
			return "error";
		}
		String college_id = "";
		if("teacher".equals(juese)) {
			college_id = teacherInfoDTO.getBysjglxtTeacherUser().getUser_teacher_belong_college();
		}else if("student".equals(juese)) {
			college_id = studentInfoDTO.getBysjglxtStudentUser().getUser_student_belong_college();
		}
		List<StudentInformationDTO> listStudentUser = new ArrayList<>();
		List<TeacherInformationDTO> listTeacherUser = new ArrayList<>();
		switch(sendString.substring(0, sendString.indexOf("_"))) {
		case "AllStudentTeacher":
			//获取该学院所有有权限的学生
			listStudentUser = noticeManagementDao.getStudentUserByCollegeId(college_id);
			//获取所有老师
			listTeacherUser = noticeManagementDao.getTeacherUserByCollegeId(college_id);
			//发消息
			sendTeacherMessage(listTeacherUser,teacherInfoDTO,studentInfoDTO,juese,message_content);
			//
			sendStudentMessage(listStudentUser,teacherInfoDTO,studentInfoDTO,juese,message_content);
			break;
		case "AllTeacher":
			//获取所有老师
			listTeacherUser = noticeManagementDao.getTeacherUserByCollegeId(college_id);
			//发消息
			sendTeacherMessage(listTeacherUser,teacherInfoDTO,studentInfoDTO,juese,message_content);	
			break;
		case "AllStudent":
			//获取该学院所有有权限的学生
			listStudentUser = noticeManagementDao.getStudentUserByCollegeId(college_id);
			//发消息
			sendStudentMessage(listStudentUser,teacherInfoDTO,studentInfoDTO,juese,message_content);
			break;
		case "MyTutorTeacher":
			//获取我的指导老师
			bysjglxt_topic_select topicSelect = new bysjglxt_topic_select();
			topicSelect = noticeManagementDao.getSelectTopicByUserId(studentInfoDTO.getBysjglxtStudentUser().getUser_student_id());
			if(topicSelect==null) {
				return "error";
			}
			listTeacherUser.add(noticeManagementDao.getTeacherInfoByUserId(topicSelect.getTopic_select_teacher_tutor()));
			sendTeacherMessage(listTeacherUser,teacherInfoDTO,studentInfoDTO,juese,message_content);	
			break;
		case "MyReviewTeacher":
			break;
		case "MyTutorStudent":
			//
			listStudentUser = noticeManagementDao.getStudentInfoByTutorUserId(teacherInfoDTO.getBysjglxtTeacherUser().getUser_teacher_id());
			sendStudentMessage(listStudentUser,teacherInfoDTO,studentInfoDTO,juese,message_content);
			break;
		case "MyReviewStudent":
			listStudentUser = noticeManagementDao.getStudentInfoByReviewUserId(teacherInfoDTO.getBysjglxtTeacherUser().getUser_teacher_id());
			sendStudentMessage(listStudentUser,teacherInfoDTO,studentInfoDTO,juese,message_content);
			break;
		case "jiaoyanteacher":
			listTeacherUser = noticeManagementDao.getTeacherInfoBySectionId(sendString.substring(sendString.lastIndexOf("_")+1));
			sendTeacherMessage(listTeacherUser,teacherInfoDTO,studentInfoDTO,juese,message_content);
			break;
		case "jiaoyanstudent":
			listStudentUser = noticeManagementDao.getStudentInfoBySectionId(sendString.substring(sendString.lastIndexOf("_")+1));
			sendStudentMessage(listStudentUser,teacherInfoDTO,studentInfoDTO,juese,message_content);
			break;
		case "teacher":
			listTeacherUser.add(noticeManagementDao.getTeacherInfoByUserId(sendString.substring(sendString.lastIndexOf("_")+1)));
			sendTeacherMessage(listTeacherUser,teacherInfoDTO,studentInfoDTO,juese,message_content);
			break;
		case "student":
			listStudentUser.add(noticeManagementDao.getStudentInfoByUserId(sendString.substring(sendString.lastIndexOf("_")+1)));
			sendStudentMessage(listStudentUser,teacherInfoDTO,studentInfoDTO,juese,message_content);
			break;
		}
		return "success";
	}
	
	public int sendTeacherMessage(List<TeacherInformationDTO> list,TeacherInformationDTO teacherInfoDTO,StudentInformationDTO studentInfoDTO,String juese, String message_content) {
		bysjglxt_notice notice = new bysjglxt_notice();
		if("student".equals(juese)) {
			for (TeacherInformationDTO teacherInformationDTO : list) {
				notice = new bysjglxt_notice();
				notice.setNotice_id(TeamUtil.getUuid());
				notice.setNotice_launch(studentInfoDTO.getBysjglxtStudentUser().getUser_student_id());
				notice.setNotice_belong(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id());
				notice.setNotice_content("["+studentInfoDTO.getBysjglxtStudentBasic().getStudent_basic_num()+"]"+studentInfoDTO.getBysjglxtStudentBasic().getStudent_basic_name()+"，给您发送消息。内容如下:\n"+message_content);
				notice.setNotice_leixing(4);
				notice.setNotice_state(2);
				notice.setNotice_gmt_create(TeamUtil.getStringSecond());
				notice.setNotice_gmt_modified(notice.getNotice_gmt_create());
				noticeManagementDao.saveNotice(notice);
			}
		}else if("teacher".equals(juese)) {
			for (TeacherInformationDTO teacherInformationDTO : list) {
				if(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id().equals(teacherInfoDTO.getBysjglxtTeacherUser().getUser_teacher_id())) {
					continue;
				}
				notice = new bysjglxt_notice();
				notice.setNotice_id(TeamUtil.getUuid());
				notice.setNotice_launch(teacherInfoDTO.getBysjglxtTeacherUser().getUser_teacher_id());
				notice.setNotice_belong(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id());
				notice.setNotice_content("["+teacherInfoDTO.getBysjglxtTeacherBasic().getJob_number()+"]"+teacherInfoDTO.getBysjglxtTeacherBasic().getName()+"，给您发送消息。内容如下:\n"+message_content);
				notice.setNotice_leixing(4);
				notice.setNotice_state(2);
				notice.setNotice_gmt_create(TeamUtil.getStringSecond());
				notice.setNotice_gmt_modified(notice.getNotice_gmt_create());
				noticeManagementDao.saveNotice(notice);
			}
		}
		return 1;
	}
	
	public int sendStudentMessage(List<StudentInformationDTO> list,TeacherInformationDTO teacherInfoDTO,StudentInformationDTO studentInfoDTO,String juese, String message_content) {
		bysjglxt_notice notice = new bysjglxt_notice();
		if("student".equals(juese)) {
			for (StudentInformationDTO studentInformationDTO : list) {
				if(studentInformationDTO.getBysjglxtStudentUser().getUser_student_id().equals(studentInfoDTO.getBysjglxtStudentUser().getUser_student_id()))
					continue;
				notice = new bysjglxt_notice();
				notice.setNotice_id(TeamUtil.getUuid());
				notice.setNotice_launch(studentInfoDTO.getBysjglxtStudentUser().getUser_student_id());
				notice.setNotice_belong(studentInformationDTO.getBysjglxtStudentUser().getUser_student_id());
				notice.setNotice_content("["+studentInfoDTO.getBysjglxtStudentBasic().getStudent_basic_num()+"]"+studentInfoDTO.getBysjglxtStudentBasic().getStudent_basic_name()+"，给您发送消息。内容如下:\n"+message_content);
				notice.setNotice_leixing(4);
				notice.setNotice_state(2);
				notice.setNotice_gmt_create(TeamUtil.getStringSecond());
				notice.setNotice_gmt_modified(notice.getNotice_gmt_create());
				noticeManagementDao.saveNotice(notice);
			}
		}else if("teacher".equals(juese)) {
			for (StudentInformationDTO studentInformationDTO : list) {
				notice = new bysjglxt_notice();
				notice.setNotice_id(TeamUtil.getUuid());
				notice.setNotice_launch(teacherInfoDTO.getBysjglxtTeacherUser().getUser_teacher_id());
				notice.setNotice_belong(studentInformationDTO.getBysjglxtStudentUser().getUser_student_id());
				notice.setNotice_content("["+teacherInfoDTO.getBysjglxtTeacherBasic().getJob_number()+"]"+teacherInfoDTO.getBysjglxtTeacherBasic().getName()+"，给您发送消息。内容如下:\n"+message_content);
				notice.setNotice_leixing(4);
				notice.setNotice_state(2);
				notice.setNotice_gmt_create(TeamUtil.getStringSecond());
				notice.setNotice_gmt_modified(notice.getNotice_gmt_create());
				noticeManagementDao.saveNotice(notice);
			}
		}
		return 1;
	}
	
	
	// 获得所有未读状态的消息
	@Override
	public List<NoticeDTO> listNotice(String userId) {
		List<NoticeDTO> listNoticeDTO = new ArrayList<NoticeDTO>();
		NoticeDTO noticeDTO = null;
		List<bysjglxt_notice> listNotice = new ArrayList<bysjglxt_notice>();
		// 获取所有未读状态的消息
		listNotice = noticeManagementDao.getListNoticeByBelongAndState(userId, 2);
		// 遍历所有listNotice
		for (bysjglxt_notice bysjglxt_notice : listNotice) {
			noticeDTO = new NoticeDTO();
			noticeDTO.setBysjglxt_notice(bysjglxt_notice);
			noticeDTO.setLaunchName(getUserNameByUserId(bysjglxt_notice.getNotice_launch()));
			noticeDTO.setBelongName(getUserNameByUserId(bysjglxt_notice.getNotice_belong()));
			listNoticeDTO.add(noticeDTO);
		}
		return listNoticeDTO;
	}

	@Override
	public int getNoticeStateCount(String userId) {
		List<bysjglxt_notice> listNotice = new ArrayList<bysjglxt_notice>();
		listNotice = noticeManagementDao.getListNoticeCount(userId, 2);
		return listNotice.size();
	}

	// 获取VO
	@Override
	public NoticeVO noticeVO(NoticeVO noticeVO, String userId) {
		List<NoticeDTO> listNoDto = new ArrayList<NoticeDTO>();
		List<bysjglxt_notice> listAllNotice = new ArrayList<bysjglxt_notice>();
		NoticeDTO noticeDTO = null;
		// 获取所有记录
		listAllNotice = noticeManagementDao.getAllNoticeByPage(noticeVO, userId);
		int i = listAllNotice.size();
		// 获取总记录数
		noticeVO.setTotalRecords(i);
		noticeVO.setTotalPages(((i - 1) / noticeVO.getPageSize()) + 1);
		if (noticeVO.getPageIndex() <= 1) {
			noticeVO.setHavePrePage(false);
		} else {
			noticeVO.setHavePrePage(true);
		}
		if (noticeVO.getPageIndex() >= noticeVO.getTotalPages()) {
			noticeVO.setHaveNextPage(false);
		} else {
			noticeVO.setHaveNextPage(true);
		}
		// 获取符合条件的若干条
		listAllNotice = noticeManagementDao.getNoticeByPage(noticeVO, userId);
		for (bysjglxt_notice bysjglxt_notice : listAllNotice) {
			noticeDTO = new NoticeDTO();
			noticeDTO.setBysjglxt_notice(bysjglxt_notice);
			noticeDTO.setLaunchName(getUserNameByUserId(bysjglxt_notice.getNotice_launch()));
			noticeDTO.setBelongName(getUserNameByUserId(bysjglxt_notice.getNotice_belong()));
			listNoDto.add(noticeDTO);
		}
		noticeVO.setListNoticeDTO(listNoDto);
		return noticeVO;
	}

	// 根据userId判断发起者名字
	public String getUserNameByUserId(String userId) {
		String username = "";
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_teacher_basic bysjglxt_teacher_basic = new bysjglxt_teacher_basic();
		// 1.在学生user表中查找对应信息
		bysjglxt_student_user = noticeManagementDao.getStudentUserById(userId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取相应的student basic表信息
			bysjglxt_student_basic = noticeManagementDao
					.getStudentBasicById(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				username = bysjglxt_student_basic.getStudent_basic_name();
				return username;
			} else {
				return "";
			}
		}
		// 在教师user表中查找对应信息
		bysjglxt_teacher_user = noticeManagementDao.getTeacherUserById(userId);
		if (bysjglxt_teacher_user != null) {
			// 根据basicId获取相应的teacherBasic表信息
			bysjglxt_teacher_basic = noticeManagementDao
					.getTeacherBasicById(bysjglxt_teacher_user.getUser_teacher_basic());
			if (bysjglxt_teacher_basic != null) {
				username = bysjglxt_teacher_basic.getName();
				return username;
			} else {
				return "";
			}
		}
		return username;
	}

	@Override
	public int updateNoticeState(String noticeId) {
		int i = 0;
		bysjglxt_notice bysjglxt_notice = new bysjglxt_notice();
		bysjglxt_notice = noticeManagementDao.getNoticeById(noticeId);
		if (bysjglxt_notice != null) {
			bysjglxt_notice.setNotice_state(1);
			bysjglxt_notice.setNotice_gmt_modified(TeamUtil.getStringSecond());
			// 存储
			i = noticeManagementDao.saveNotice(bysjglxt_notice);
		}
		return i;
	}

}
