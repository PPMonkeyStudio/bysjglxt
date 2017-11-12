package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_leader;
import com.bysjglxt.domain.DO.bysjglxt_notice;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.VO.TopicInformationManagementVO;

public interface TopicInformationManagementDao {

	public boolean CreateTopic(bysjglxt_topic newTopic);

	public boolean DeleteTopic(String topicID);

	public boolean updateTopicState(String topicID, String moTime);

	public boolean closeTopic(String string, String moTime);

	public boolean notAdoptTopic(String string, String moTime);

	public List<com.bysjglxt.domain.DO.bysjglxt_topic> VO_Topic_By_PageAndSearch(
			TopicInformationManagementVO topicManagementVO, int studentOrTeacher);

	public bysjglxt_topic getBysjglxtTopicById(String topic_id);

	public boolean teacherIsSelect(String topic_teacher);

	public com.bysjglxt.domain.DO.bysjglxt_teacher_user getTeacherUser(String topic_teacher);

	public boolean topicIsSelect(String topic_id);

	public boolean createStudentSclectInformation(bysjglxt_topic_select bysjglxt_topic_select);

	public boolean addTopicStudentNum(String topicID);

	public boolean addTeacherUserSrtudentNum(String user_teacher_id);

	public boolean updateStudentList(String topicId, String studentIdList);

	public boolean teacherIsUserId(String user_teacher_id);

	public com.bysjglxt.domain.DO.bysjglxt_teacher_user getTeacherUserInfo(String topic_teacher);

	public com.bysjglxt.domain.DO.bysjglxt_teacher_basic getTeacherBasicInfo(String user_teacher_basic);

	public com.bysjglxt.domain.DO.bysjglxt_section getTeacherSection(String user_teacher_section);

	public List<bysjglxt_topic> getAllTopic();

	public List<bysjglxt_topic> VO_Topic_BySearch(TopicInformationManagementVO topicManagementVO, int studentOrTeacher);

	public boolean updateStudentUserRecord(String studentID);

	public com.bysjglxt.domain.DO.bysjglxt_student_user studentIsSelectTopic(String studentID);

	public List<bysjglxt_topic_select> getTopicSelectList(String topicId);

	public boolean updateStudentUserNotSelect(String topic_select_student);

	public boolean deleteTopicSelect(String topic_select_id);

	public bysjglxt_topic_select getStudentTopicSelectByUserId(String studentUserId);

	public bysjglxt_leader getLeader(String user_teacher_id);

	public List<bysjglxt_topic> VO_Topic_By_PageAndSearch(TopicInformationManagementVO topicManagementVO,
			String teacherUserId);

	public List<bysjglxt_topic> VO_Topic_BySearch(TopicInformationManagementVO topicManagementVO, String teacherUserId);

	public List<bysjglxt_topic_select> getTopicSelectByTopicId(String topicId);

	public com.bysjglxt.domain.DO.bysjglxt_student_basic getStudentBasic(String user_student_basic);

	public com.bysjglxt.domain.DO.bysjglxt_topic studentIsExistSpecial(String studentID, String topicID);

	public List<bysjglxt_topic> VO_Topic_By_StudentPageAndSearch(TopicInformationManagementVO topicManagementVO,
			String studentUserId);

	public com.bysjglxt.domain.DO.bysjglxt_student_user getStudentUserByUserId(String userId);

	public boolean updateTeacherSelectNum(String topic_select_teacher_tutor);

	public boolean updateTopicNum(String topic_select_topic);

	public com.bysjglxt.domain.DO.bysjglxt_process_definition getProcessDefinitionByName(String string);

	public com.bysjglxt.domain.DO.bysjglxt_process_instance getProcessInstanceByStateAndDefinitionId(
			String process_definition_id, String string);

	public com.bysjglxt.domain.DO.bysjglxt_task_instance getTaskInstanceByProcessInstanceIdAndState(
			String process_instance_id, int i);

	public com.bysjglxt.domain.DO.bysjglxt_task_definition getTaskDefinitionById(String task_instance_task_definition);

	public List<bysjglxt_leader> getAllLeader();

	public boolean createNoti1ceRecord(bysjglxt_notice bysjglxt_notice);

}
