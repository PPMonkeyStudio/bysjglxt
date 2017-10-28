package com.bysjglxt.action;

import com.bysjglxt.service.TopicManagementService;

public class TopicManagementAction {

	/**
	 * service注入
	 */
	private TopicManagementService topicManagementService;

	public void setTopicManagementService(TopicManagementService topicManagementService) {
		this.topicManagementService = topicManagementService;
	}

}
