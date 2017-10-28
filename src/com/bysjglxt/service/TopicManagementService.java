package com.bysjglxt.service;

import com.bysjglxt.domain.DO.bysjglxt_topic;

public interface TopicManagementService {
	/**
	 * @说明 教师创建课题，存储到bysjglxt_topic表中
	 * 
	 * @param newTopic
	 * 
	 * @return 是否存储成功,1是 0否
	 */
	public boolean CreateTopic(bysjglxt_topic newTopic);
}
