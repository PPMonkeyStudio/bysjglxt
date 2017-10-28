package com.bysjglxt.service;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_topic;

public interface TopicManagementService {

	/**
	 * @DATE 2017-10-28
	 * @说明 教师创建课题，存储到bysjglxt_topic表中
	 * @param newTopic
	 * @return 1成功 0失败
	 */
	public boolean CreateTopic(bysjglxt_topic newTopic);

	/**
	 * @说明 删除课题
	 * @param topic_id
	 * @return 1成功 0失败
	 */
	public boolean DeleteTopic(List<String> topicID);

	/**
	 * @说明 采纳课题
	 * @param topicID
	 * @return
	 */
	public boolean adoptTopic(List<String> topicID);
	/**
	 * @说明 关闭课题
	 * 
	 */
	
	public boolean closeTopic(List<String> topicID);
	
	
	

}
