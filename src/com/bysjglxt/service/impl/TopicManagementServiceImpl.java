package com.bysjglxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bysjglxt.dao.TopicManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DO.bysjglxt_topic_invite_teacher;
import com.bysjglxt.domain.DTO.TopicInformationDTO;
import com.bysjglxt.domain.VO.TopicManagementVO;
import com.bysjglxt.service.TopicManagementService;

import util.TeamUtil;

public class TopicManagementServiceImpl implements TopicManagementService {

	// Dao层的注入
	private TopicManagementDao topicManagementDao;

	public void setTopicManagementDao(TopicManagementDao topicManagementDao) {
		this.topicManagementDao = topicManagementDao;
	}

	@Override
	public boolean CreateTopic(TopicInformationDTO topicInformationDTO) {
		boolean flag = false;
		bysjglxt_topic newTopic = new bysjglxt_topic();
		bysjglxt_topic_invite_teacher invite_teacher = new bysjglxt_topic_invite_teacher();
		invite_teacher = topicInformationDTO.getBysjglxtTopicInviteTeacher();
		newTopic = topicInformationDTO.getBysjglxtTopic();
		newTopic.setTopic_id(TeamUtil.getUuid());
		newTopic.setTopic_gmt_create(TeamUtil.getStringSecond());
		newTopic.setTopic_gmt_modified(TeamUtil.getStringSecond());
		newTopic.setTopic_examine_state("未审核");
		newTopic.setTopic_student_num(0);
		flag = topicManagementDao.CreateTopic(newTopic);
		if (flag) {
			invite_teacher.setTopic_invite_teacher_id(TeamUtil.getUuid());
			invite_teacher.setTopic_invite_teacher_topic_id(newTopic.getTopic_id());
			invite_teacher.setTopic_invite__teacher_gmt_create(TeamUtil.getStringSecond());
			invite_teacher.setTopic_invite_teacher_gmt_modify(invite_teacher.getTopic_invite__teacher_gmt_create());
			flag = topicManagementDao.createTopicInviteTeacher(invite_teacher);
		}
		return flag;
	}

	@Override
	public boolean DeleteTopic(List<String> topic_id) {
		boolean flag = false;
		for (String topicId : topic_id) {
			flag = topicManagementDao.deleteTopicInviteTeacher(topicId);
			if (flag) {
				flag = topicManagementDao.DeleteTopic(topicId);
			}
		}
		return flag;
	}

	@Override
	public boolean adoptTopic(List<String> topicID) {
		boolean flag = false;
		for (String string : topicID) {
			flag = topicManagementDao.updateTopicState(string);
		}
		return flag;
	}

	@Override
	public boolean closeTopic(List<String> topicID) {
		boolean flag = false;
		for (String string : topicID) {
			flag = topicManagementDao.closeTopic(string);
		}
		return flag;
	}

	@Override
	public boolean notAdoptTopic(List<String> topicID) {
		boolean flag = false;
		for (String string : topicID) {
			flag = topicManagementDao.notAdoptTopic(string);
		}
		return flag;
	}

	@Override
	public TopicManagementVO VO_Topic_By_PageAndSearch(TopicManagementVO topicManagementVO) {
		List<TopicInformationDTO> listTopicInformationDTO = new ArrayList<TopicInformationDTO>();
		TopicInformationDTO topicInformationDTO = null;
		bysjglxt_topic_invite_teacher bysjglxt_topic_invite_teacher = null;
		List<bysjglxt_topic> list_bysjglxt_topic = new ArrayList<bysjglxt_topic>();
		// 获得符合条件的所有课题
		list_bysjglxt_topic = topicManagementDao.VO_Topic_By_PageAndSearch(topicManagementVO);
		int i = 0;
		for (bysjglxt_topic tbysjglxt_topic : list_bysjglxt_topic) {
			topicInformationDTO = new TopicInformationDTO();
			topicInformationDTO.setBysjglxtTopic(tbysjglxt_topic);
			bysjglxt_topic_invite_teacher = topicManagementDao
					.getBysjglxtTopicInviteTeacher(tbysjglxt_topic.getTopic_id());
			topicInformationDTO.setBysjglxtTopicInviteTeacher(bysjglxt_topic_invite_teacher);
			listTopicInformationDTO.add(topicInformationDTO);
			i++;
		}
		topicManagementVO.setListTopicInformationDTO(listTopicInformationDTO);
		System.out.println(i);
		topicManagementVO.setTotalRecords(i);
		topicManagementVO.setTotalPages(((i - 1) / topicManagementVO.getPageSize()) + 1);
		if (topicManagementVO.getPageIndex() <= 1) {
			topicManagementVO.setHavePrePage(false);
		} else {
			topicManagementVO.setHavePrePage(true);
		}
		if (topicManagementVO.getPageIndex() >= topicManagementVO.getTotalPages()) {
			topicManagementVO.setHaveNextPage(false);
		} else {
			topicManagementVO.setHaveNextPage(true);
		}

		return topicManagementVO;
	}

	@Override
	public boolean selectTopic(String studentID, String topicID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean topicSelectStudent(String TeacherID, String topicID, List<String> studentIDList) {
		// TODO Auto-generated method stub
		return false;
	}

}
