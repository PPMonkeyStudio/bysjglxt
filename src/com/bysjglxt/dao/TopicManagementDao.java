package com.bysjglxt.dao;

import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DO.bysjglxt_topic_invite_teacher;

public interface TopicManagementDao {

	boolean CreateTopic(bysjglxt_topic newTopic);

	boolean DeleteTopic(String topicID);

	boolean updateTopicState(String topicID);

	boolean closeTopic(String string);

	boolean notAdoptTopic(String string);

	boolean createTopicInviteTeacher(bysjglxt_topic_invite_teacher invite_teacher);

}
