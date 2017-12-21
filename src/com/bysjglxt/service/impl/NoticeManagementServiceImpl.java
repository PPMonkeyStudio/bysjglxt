package com.bysjglxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bysjglxt.dao.NoticeManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_notice;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DTO.NoticeDTO;
import com.bysjglxt.domain.VO.NoticeVO;
import com.bysjglxt.service.NoticeManagementService;

import util.TeamUtil;

public class NoticeManagementServiceImpl implements NoticeManagementService {
	private NoticeManagementDao noticeManagementDao;

	public void setNoticeManagementDao(NoticeManagementDao noticeManagementDao) {
		this.noticeManagementDao = noticeManagementDao;
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
