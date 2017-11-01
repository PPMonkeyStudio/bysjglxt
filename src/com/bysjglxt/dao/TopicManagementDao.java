package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DO.bysjglxt_topic_invite_teacher;
import com.bysjglxt.domain.VO.TopicManagementVO;

public interface TopicManagementDao {

	public boolean CreateTopic(bysjglxt_topic newTopic);

	public boolean DeleteTopic(String topicID);

	public boolean updateTopicState(String topicID);

	public boolean closeTopic(String string);

	public boolean notAdoptTopic(String string);

	public boolean createTopicInviteTeacher(bysjglxt_topic_invite_teacher invite_teacher);

	public boolean deleteTopicInviteTeacher(String topicId);

	public List<com.bysjglxt.domain.DO.bysjglxt_topic> VO_Topic_By_PageAndSearch(TopicManagementVO topicManagementVO);

	public bysjglxt_topic getBysjglxtTopicById(String topic_id);

	public bysjglxt_topic_invite_teacher getBysjglxtTopicInviteTeacher(String topic_invite_teache_id);

}
