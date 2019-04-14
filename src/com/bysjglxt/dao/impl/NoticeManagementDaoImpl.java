package com.bysjglxt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.NoticeManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_notice;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.NoticeVO;

public class NoticeManagementDaoImpl implements NoticeManagementDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}


	@Override
	public TeacherInformationDTO getTeacherInfoByUserId(String notice_launch) {
		TeacherInformationDTO teacherInformationDTO = new TeacherInformationDTO();
		Session session = getSession();
		String hql = "select new com.bysjglxt.domain.DTO.TeacherInformationDTO(teacherBasic,teacherUser) from bysjglxt_teacher_user teacherUser,bysjglxt_teacher_basic teacherBasic where teacherUser.user_teacher_basic= teacherBasic.teacher_basic_id and teacherUser.user_teacher_id = '" + notice_launch + "'";
		Query query = session.createQuery(hql);
		teacherInformationDTO = (TeacherInformationDTO) query.uniqueResult();
		return teacherInformationDTO;
	}

	@Override
	public List<TeacherInformationDTO> getTeacherUserByCollegeId(String college_id) {
		List<TeacherInformationDTO> listTeacherUser = new ArrayList<>();
		Session session = getSession();
		String hql = "select new com.bysjglxt.domain.DTO.TeacherInformationDTO(teacherBasic,teacherUser) from bysjglxt_teacher_user teacherUser,bysjglxt_teacher_basic teacherBasic where teacherUser.user_teacher_basic= teacherBasic.teacher_basic_id and teacherUser.user_teacher_belong_college = '" + college_id + "'";
		Query query = session.createQuery(hql);
		listTeacherUser = query.list();
		return listTeacherUser;	
	}

	@Override
	public List<TeacherInformationDTO> getTeacherInfoBySectionId(String sectionId) {
		List<TeacherInformationDTO> listTeacherUser = new ArrayList<>();
		Session session = getSession();
		String hql = "select new com.bysjglxt.domain.DTO.TeacherInformationDTO(teacherBasic,teacherUser) from bysjglxt_teacher_user teacherUser,bysjglxt_teacher_basic teacherBasic where teacherUser.user_teacher_basic= teacherBasic.teacher_basic_id and teacherUser.user_teacher_section = '" + sectionId + "'";
		Query query = session.createQuery(hql);
		listTeacherUser = query.list();
		return listTeacherUser;	
	}


	@Override
	public List<StudentInformationDTO> getStudentUserByCollegeId(String college_id) {
		List<StudentInformationDTO> listStudentUser = new ArrayList<StudentInformationDTO>();
		Session session = getSession();
		String hql = "select new com.bysjglxt.domain.DTO.StudentInformationDTO(studentBasic,studentUser) from bysjglxt_student_user studentUser,bysjglxt_student_basic studentBasic where studentUser.user_student_basic= studentBasic.student_basic_id and studentUser.user_student_belong_college = '" + college_id + "' and studentUser.user_student_is_operate_premission = 1";
		Query query = session.createQuery(hql);
		listStudentUser = query.list();
		return listStudentUser;
	}

	@Override
	public StudentInformationDTO getStudentInfoByUserId(String userId) {
		StudentInformationDTO listStudentUser = new StudentInformationDTO();
		Session session = getSession();
		String hql = "select new com.bysjglxt.domain.DTO.StudentInformationDTO(studentBasic,studentUser) from bysjglxt_student_user studentUser,bysjglxt_student_basic studentBasic where studentUser.user_student_basic= studentBasic.student_basic_id and studentUser.user_student_id = '" + userId + "' and studentUser.user_student_is_operate_premission = 1";
		Query query = session.createQuery(hql);
		listStudentUser = (StudentInformationDTO) query.uniqueResult();
		return listStudentUser;
	}
	@Override
	public List<StudentInformationDTO> getStudentInfoBySectionId(String sectionId) {
		List<StudentInformationDTO> listStudentUser = new ArrayList<StudentInformationDTO>();
		Session session = getSession();
		String hql = "select new com.bysjglxt.domain.DTO.StudentInformationDTO(studentBasic,studentUser) from bysjglxt_student_user studentUser,bysjglxt_student_basic studentBasic,bysjglxt_major major where major.major_id=studentUser.user_student_belong_major and studentUser.user_student_basic= studentBasic.student_basic_id and major.major_belong_section = '" + sectionId + "' and studentUser.user_student_is_operate_premission = 1";
		Query query = session.createQuery(hql);
		listStudentUser = query.list();
		return listStudentUser;
	}

	@Override
	public List<StudentInformationDTO> getStudentInfoByTutorUserId(String user_teacher_id) {
		List<StudentInformationDTO> listStudentUser = new ArrayList<StudentInformationDTO>();
		Session session = getSession();
		String hql = "select new com.bysjglxt.domain.DTO.StudentInformationDTO(studentBasic,studentUser) from bysjglxt_student_user studentUser,bysjglxt_student_basic studentBasic,bysjglxt_topic_select topicSelect where topicSelect.topic_select_student= studentUser.user_student_id and studentUser.user_student_basic= studentBasic.student_basic_id  and studentUser.user_student_is_operate_premission = 1 and topicSelect.topic_select_teacher_tutor='"+user_teacher_id+"'";
		Query query = session.createQuery(hql);
		listStudentUser = query.list();
		return listStudentUser;
	}

	@Override
	public List<StudentInformationDTO> getStudentInfoByReviewUserId(String user_teacher_id) {
		List<StudentInformationDTO> listStudentUser = new ArrayList<StudentInformationDTO>();
		Session session = getSession();
		String hql = "select new com.bysjglxt.domain.DTO.StudentInformationDTO(studentBasic,studentUser) from bysjglxt_student_user studentUser,bysjglxt_student_basic studentBasic,bysjglxt_topic_select topicSelect where topicSelect.topic_select_student= studentUser.user_student_id and studentUser.user_student_basic= studentBasic.student_basic_id  and studentUser.user_student_is_operate_premission = 1 and topicSelect.topic_select_teacher_review='"+user_teacher_id+"'";
		Query query = session.createQuery(hql);
		listStudentUser = query.list();
		return listStudentUser;
	}

	
	// 根据状态以及拥有者ID获取通知
	@Override
	public List<bysjglxt_notice> getListNoticeByBelongAndState(String userId, int i) {
		List<bysjglxt_notice> list_bysjglxt_notice = new ArrayList<bysjglxt_notice>();
		Session session = getSession();
		String hql = "from bysjglxt_notice where notice_belong = '" + userId + "' and notice_state = '" + i
				+ "' order by notice_gmt_create desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(5);
		list_bysjglxt_notice = query.list();
		return list_bysjglxt_notice;
	}

	@Override
	public bysjglxt_student_user getStudentUserById(String userId) {
		Session session = getSession();
		String hql = "from bysjglxt_student_user where user_student_id='" + userId + "'";
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		Query query = session.createQuery(hql);
		bysjglxt_student_user = (bysjglxt_student_user) query.uniqueResult();
		return bysjglxt_student_user;
	}

	@Override
	public bysjglxt_student_basic getStudentBasicById(String user_student_basic) {
		Session session = getSession();
		bysjglxt_student_basic StudentBasicInformation = null;
		String hql = "from bysjglxt_student_basic where student_basic_id='" + user_student_basic + "'";
		Query query = session.createQuery(hql);
		StudentBasicInformation = (bysjglxt_student_basic) query.uniqueResult();
		return StudentBasicInformation;
	}

	@Override
	public bysjglxt_teacher_user getTeacherUserById(String userId) {
		Session session = getSession();
		bysjglxt_teacher_user TeacherInformation = null;
		String hql = "from bysjglxt_teacher_user where user_teacher_id='" + userId + "'";
		Query query = session.createQuery(hql);
		TeacherInformation = (bysjglxt_teacher_user) query.uniqueResult();
		return TeacherInformation;
	}

	@Override
	public bysjglxt_teacher_basic getTeacherBasicById(String user_teacher_basic) {
		Session session = getSession();
		bysjglxt_teacher_basic TeacherBasicInformation = null;
		String hql = "from bysjglxt_teacher_basic where teacher_basic_id='" + user_teacher_basic + "'";
		Query query = session.createQuery(hql);
		TeacherBasicInformation = (bysjglxt_teacher_basic) query.uniqueResult();
		return TeacherBasicInformation;
	}

	// 获取所有符合条件记录
	@Override
	public List<bysjglxt_notice> getAllNoticeByPage(NoticeVO noticeVO, String userId) {
		Session session = getSession();
		List<bysjglxt_notice> listNo = new ArrayList<bysjglxt_notice>();
		String hql = "from bysjglxt_notice where notice_belong='" + userId + "'";
		if (noticeVO.getState() != 0) {
			hql = hql + " and notice_state = '" + noticeVO.getState() + "'";
		}
		Query query = session.createQuery(hql);
		listNo = query.list();
		return listNo;
	}

	@Override
	public List<bysjglxt_notice> getNoticeByPage(NoticeVO noticeVO, String userId) {
		Session session = getSession();
		List<bysjglxt_notice> listNo = new ArrayList<bysjglxt_notice>();
		String hql = "from bysjglxt_notice where notice_belong='" + userId + "'";
		if (noticeVO.getState() != 0) {
			hql = hql + " and notice_state = '" + noticeVO.getState() + "'";
		}
		hql = hql + " order by notice_state desc,notice_gmt_create desc";
		Query query = session.createQuery(hql);
		query.setFirstResult((noticeVO.getPageIndex() - 1) * noticeVO.getPageSize());
		query.setMaxResults(noticeVO.getPageSize());
		listNo = query.list();
		return listNo;
	}

	@Override
	public bysjglxt_notice getNoticeById(String noticeId) {
		Session session = getSession();
		bysjglxt_notice bysjglxt_notice = null;
		String hql = "from bysjglxt_notice where notice_id='" + noticeId + "'";
		Query query = session.createQuery(hql);
		bysjglxt_notice = (bysjglxt_notice) query.uniqueResult();
		return bysjglxt_notice;
	}

	@Override
	public int saveNotice(bysjglxt_notice bysjglxt_notice) {
		int flag = 1;
		try {
			Session session = getSession();
			session.saveOrUpdate(bysjglxt_notice);
		} catch (Exception e) {
			flag = -1;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<bysjglxt_notice> getListNoticeCount(String userId, int i) {
		List<bysjglxt_notice> list_bysjglxt_notice = new ArrayList<bysjglxt_notice>();
		Session session = getSession();
		String hql = "from bysjglxt_notice where notice_belong = '" + userId + "' and notice_state = '" + i + "'";
		Query query = session.createQuery(hql);
		list_bysjglxt_notice = query.list();
		return list_bysjglxt_notice;
	}

	@Override
	public bysjglxt_topic_select getSelectTopicByUserId(String user_student_id) {
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		Session session = getSession();
		String hql = "from bysjglxt_topic_select where topic_select_student='"+user_student_id+"'";
		Query query = session.createQuery(hql);
		bysjglxt_topic_select = (com.bysjglxt.domain.DO.bysjglxt_topic_select) query.uniqueResult();
		return bysjglxt_topic_select;
	}

	@Override
	public List<bysjglxt_notice> getTaskByLiXing(String leixing, String userId) {
		List<bysjglxt_notice> list_bysjglxt_notice = new ArrayList<bysjglxt_notice>();
		Session session = getSession();
		String hql = "from bysjglxt_notice where notice_belong = '" + userId + "' and notice_leixing = '" + leixing + "' order by notice_gmt_create desc";
		Query query = session.createQuery(hql);
		list_bysjglxt_notice = query.list();
		return list_bysjglxt_notice;
	}


}
