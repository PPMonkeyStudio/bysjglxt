package com.bysjglxt.dao;

import com.bysjglxt.domain.DO.bysjglxt_topic;

public interface TopicManagementDao {

	boolean CreateTopic(bysjglxt_topic newTopic);

	boolean DeleteTopic(String topicID);

	boolean updateTopicState(String topicID);

	boolean closeTopic(String string);

	boolean notAdoptTopic(String string);

}
