package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DO.bysjglxt_topic_invite_teacher;
import com.bysjglxt.domain.VO.TopicManagementVO;

public interface TopicManagementDao {

	boolean CreateTopic(bysjglxt_topic newTopic);

	boolean DeleteTopic(String topicID);

	boolean updateTopicState(String topicID);

	boolean closeTopic(String string);

	boolean notAdoptTopic(String string);

	boolean createTopicInviteTeacher(bysjglxt_topic_invite_teacher invite_teacher);

	boolean deleteTopicInviteTeacher(String topicId);

	List<com.bysjglxt.domain.DO.bysjglxt_topic> VO_Topic_By_PageAndSearch(TopicManagementVO topicManagementVO);

	com.bysjglxt.domain.DO.bysjglxt_topic_invite_teacher getBysjglxtTopicInviteTeacher(String topic_id);

}
