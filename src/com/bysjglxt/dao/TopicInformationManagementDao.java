package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_notice;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TeacherDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.DTO.TopicInformationManagementDTO;
import com.bysjglxt.domain.VO.TopicInformationManagementVO;

public interface TopicInformationManagementDao {

	public bysjglxt_process_instance getProcessInstanceByManStatePAndName(String topic_select_student);

	public boolean CreateTopic(bysjglxt_topic newTopic);

	public boolean DeleteTopic(String topicID);

	public boolean saveObj(Object obj);

	public boolean updateTopicState(String topicID, String moTime);

	public boolean closeTopic(String string, String moTime);

	public boolean notAdoptTopic(String string, String moTime);

	public List<com.bysjglxt.domain.DO.bysjglxt_topic> VO_Topic_By_PageAndSearch(
			TopicInformationManagementVO topicManagementVO, int studentOrTeacher, String collegeId);

	public bysjglxt_topic getBysjglxtTopicById(String topic_id);

	public boolean teacherIsSelect(String topic_teacher);

	public bysjglxt_teacher_user getTeacherUser(String topic_teacher);

	public boolean topicIsSelect(String topic_id);

	public boolean createStudentSclectInformation(bysjglxt_topic_select bysjglxt_topic_select);

	public boolean addTopicStudentNum(String topicID);

	public boolean addObject(Object obj);

	public boolean addTeacherUserSrtudentNum(String user_teacher_id);

	public boolean updateStudentList(String topicId, String studentIdList);

	public boolean teacherIsUserId(String user_teacher_id);

	public com.bysjglxt.domain.DO.bysjglxt_teacher_user getTeacherUserInfo(String topic_teacher);

	public com.bysjglxt.domain.DO.bysjglxt_teacher_basic getTeacherBasicInfo(String user_teacher_basic);

	public com.bysjglxt.domain.DO.bysjglxt_section getTeacherSection(String user_teacher_section);

	public List<bysjglxt_topic> getAllTopic(String college);

	public List<bysjglxt_topic> VO_Topic_BySearch(TopicInformationManagementVO topicManagementVO, int studentOrTeacher,
			String collegeId);

	public boolean updateStudentUserRecord(String studentID);

	public com.bysjglxt.domain.DO.bysjglxt_student_user studentIsSelectTopic(String studentID);

	public List<bysjglxt_topic_select> getTopicSelectList(String topicId);

	public boolean updateStudentUserNotSelect(String topic_select_student);

	public boolean deleteTopicSelect(String topic_select_id);

	public bysjglxt_topic_select getStudentTopicSelectByUserId(String studentUserId);

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

	public boolean createNoti1ceRecord(bysjglxt_notice bysjglxt_notice);

	public com.bysjglxt.domain.DO.bysjglxt_topic_select getSelectTopicById(String selectId);

	public com.bysjglxt.domain.DO.bysjglxt_topic_select getSelectTopicByOwnId(String selectId);

	public com.bysjglxt.domain.DO.bysjglxt_process_instance getProcessInstanceByManStateAndName(
			String topic_select_student);

	public com.bysjglxt.domain.DO.bysjglxt_task_definition getTaskDefinitionByName(String string);

	public com.bysjglxt.domain.DO.bysjglxt_task_instance getTaskInstanceByNameAndProcessInstanceId(
			String task_definition_id, String process_instance_id);

	public List<bysjglxt_student_user> getListStudentUserByDesignation(String studentMajor, String studentGrade,
			String search, String college);

	public com.bysjglxt.domain.DO.bysjglxt_topic getTopicByIdAndStudent(String student_user, String topicId);

	public List<com.bysjglxt.domain.DO.bysjglxt_teacher_user> getListAdminByCollege(String user_teacher_belong_college);

	public String getTopicMaxNumByYear(String topic_year,String user_teacher_belong_college);

	/**
	 * 根据条件获取所有的选题
	 * 
	 * @param topic
	 * @param user_teacher_belong_college
	 * @return
	 */
	public List<bysjglxt_topic> listAllTopic(bysjglxt_topic topic, String user_teacher_belong_college);

	/**
	 * 获取课题
	 * @param topicId
	 * @return
	 */
	public bysjglxt_topic getTopicById(String topicId);
	/**
	 * 获取创建选题的老师
	 * @param year
	 * @return
	 */
	public List<bysjglxt_teacher_user> getTeacherUserCreateTopic(String year,String teacherColleage);

	/**
	 * 根据老师以及年份获取该老师所有创建的课题
	 * @param user_teacher_id
	 * @param year
	 * @return
	 */
	public List<bysjglxt_topic> getTopicByTeacherIdAndYear(String user_teacher_id, String year);
	/**
	 * 获取未创建选题的信息
	 * @param year
	 * @param user_teacher_belong_college
	 * @return
	 */
	public List<bysjglxt_teacher_user> getTeacherUserNotTopic(String year, String user_teacher_belong_college);

	TeacherInformationDTO getTeacherInfoDTOByUserId(String topic_teacher);

	/**
	 * 根据课题id获取学生信息
	 * @param topicId
	 * @return
	 */
	public List<StudentInformationDTO> getStudentInfoByTopicId(String topicId);

	public bysjglxt_notice getNoticeByTopicInfoAndLeiXing(String content, int i);

	public bysjglxt_teacher_user getTeacherByCollege(String user_teacher_belong_college);
	
	public boolean deleteNotice(String notice_id);

	public bysjglxt_notice getNoticeByContentAndLeiXingBelong(String topic_teacher, String string, int i);

	public StudentInformationDTO getStudentInfoByUserId(String studentUserId);

}
