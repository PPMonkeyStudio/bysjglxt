package com.bysjglxt.service.impl;

import com.bysjglxt.dao.TopicManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.service.TopicManagementService;

public class TopicManagementServiceImpl implements TopicManagementService {

	// Dao层的注入
	private TopicManagementDao topicManagementDao;

	public void setTopicManagementDao(TopicManagementDao topicManagementDao) {
		this.topicManagementDao = topicManagementDao;
	}

	@Override
	public boolean CreateTopic(bysjglxt_topic newTopic) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteTopic(String topicID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean adoptTopic(String topicID) {
		// TODO Auto-generated method stub
		return false;
	}

}
