package com.bysjglxt.service.impl;

import java.util.List;

import com.bysjglxt.dao.TopicManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_topic;
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
	public boolean CreateTopic(bysjglxt_topic newTopic) {
		newTopic.setTopic_id(TeamUtil.getUuid());
		newTopic.setTopic_gmt_create(TeamUtil.getStringSecond());
		newTopic.setTopic_gmt_modified(TeamUtil.getStringSecond());
		newTopic.setTopic_examine_state("未审核");
		return topicManagementDao.CreateTopic(newTopic);
	}

	@Override
	public boolean DeleteTopic(List<String> topicID) {
		boolean flag = false;
		for (String string : topicID) {
			flag = topicManagementDao.DeleteTopic(string);
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
	public TopicManagementVO VO_Topic_By_PageAndSearch(TopicManagementVO topicManagementVO) {
		// TODO Auto-generated method stub
		return null;
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
