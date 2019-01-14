package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_notice;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.NoticeVO;

public interface NoticeManagementDao {

	public List<bysjglxt_notice> getListNoticeByBelongAndState(String userId, int i);

	public com.bysjglxt.domain.DO.bysjglxt_student_user getStudentUserById(String userId);

	public com.bysjglxt.domain.DO.bysjglxt_student_basic getStudentBasicById(String user_student_basic);

	public com.bysjglxt.domain.DO.bysjglxt_teacher_user getTeacherUserById(String userId);

	public com.bysjglxt.domain.DO.bysjglxt_teacher_basic getTeacherBasicById(String user_teacher_basic);

	public List<bysjglxt_notice> getAllNoticeByPage(NoticeVO noticeVO, String userId);

	public List<bysjglxt_notice> getNoticeByPage(NoticeVO noticeVO, String userId);

	public com.bysjglxt.domain.DO.bysjglxt_notice getNoticeById(String noticeId);

	public int saveNotice(bysjglxt_notice bysjglxt_notice);

	public List<bysjglxt_notice> getListNoticeCount(String userId, int i);

	public List<StudentInformationDTO> getStudentUserByCollegeId(String college_id);

	public List<TeacherInformationDTO> getTeacherUserByCollegeId(String college_id);

	public TeacherInformationDTO getTeacherInfoByUserId(String notice_launch);

	public bysjglxt_topic_select getSelectTopicByUserId(String user_student_id);

	public List<StudentInformationDTO> getStudentInfoByTutorUserId(String user_teacher_id);

	public List<StudentInformationDTO> getStudentInfoByReviewUserId(String user_teacher_id);

	public List<TeacherInformationDTO> getTeacherInfoBySectionId(String substring);

	public List<StudentInformationDTO> getStudentInfoBySectionId(String substring);

	public StudentInformationDTO getStudentInfoByUserId(String substring);

	public List<bysjglxt_notice> getTaskByLiXing(String leixing, String userId);

}
