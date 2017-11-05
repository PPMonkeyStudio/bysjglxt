package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DO.bysjglxt_topic_invite_teacher;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.VO.TopicInformationManagementVO;

public interface TopicInformationManagementDao {

	public boolean CreateTopic(bysjglxt_topic newTopic);

	public boolean DeleteTopic(String topicID);

	public boolean updateTopicState(String topicID, String moTime);

	public boolean closeTopic(String string, String moTime);

	public boolean notAdoptTopic(String string, String moTime);

	public boolean createTopicInviteTeacher(bysjglxt_topic_invite_teacher invite_teacher);

	public boolean deleteTopicInviteTeacher(String topicId);

	public List<com.bysjglxt.domain.DO.bysjglxt_topic> VO_Topic_By_PageAndSearch(
			TopicInformationManagementVO topicManagementVO, int studentOrTeacher);

	public bysjglxt_topic getBysjglxtTopicById(String topic_id);

	public bysjglxt_topic_invite_teacher getBysjglxtTopicInviteTeacher(String topic_invite_teache_id);

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

	public List<bysjglxt_topic> VO_Topic_BySearch(TopicInformationManagementVO topicManagementVO,int studentOrTeacher);

	public boolean updateStudentUserRecord(String studentID);

	public com.bysjglxt.domain.DO.bysjglxt_student_user studentIsSelectTopic(String studentID);

	public List<bysjglxt_topic_select> getTopicSelectList(String topicId);

	public boolean updateStudentUserNotSelect(String topic_select_student);

	public boolean deleteTaskBook(String topic_select_student);

	public boolean deleteReportOpening(String topic_select_student);

	public com.bysjglxt.domain.DO.bysjglxt_taskbook getTaskBookByStudent(String topic_select_student);

	public bysjglxt_report_opening getReportOpening(String topic_select_student);

	public List<bysjglxt_record_progress> getProgress(String topic_select_student);

	public boolean deleteProgress(String record_progress_id);

	public bysjglxt_summary getSummary(String topic_select_student);

	public boolean deleteSummary(String summary_id);

	public bysjglxt_examination_formal getExaminationFormal(String topic_select_student);

	public boolean deleteExaminationFormal(String examination_formal_id);

	public bysjglxt_evaluate_tutor getEvaluateTutor(String topic_select_student);

	public boolean deleteEvaluateTutor(String evaluate_tutor_id);

	public bysjglxt_evaluate_review getEvaluateReview(String topic_select_student);

	public boolean deleteEvaluateReview(String evaluate_review_id);

	public boolean deleteTopicSelect(String topic_select_id);

}
