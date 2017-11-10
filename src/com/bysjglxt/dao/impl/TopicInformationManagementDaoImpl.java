package com.bysjglxt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.TopicInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_leader;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_taskbook;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DO.bysjglxt_topic_invite_teacher;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.VO.TopicInformationManagementVO;

public class TopicInformationManagementDaoImpl implements TopicInformationManagementDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public boolean CreateTopic(bysjglxt_topic newTopic) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.saveOrUpdate(newTopic);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public boolean createTopicInviteTeacher(bysjglxt_topic_invite_teacher invite_teacher) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.saveOrUpdate(invite_teacher);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean DeleteTopic(String topicID) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete from bysjglxt_topic where topic_id='" + topicID + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateTopicState(String topicID, String moTime) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_topic set topic_examine_state = '审核已通过',topic_gmt_modified='" + moTime
					+ "' where topic_id='" + topicID + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean closeTopic(String string, String moTime) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_topic set topic_examine_state = '已关闭',topic_gmt_modified='" + moTime
					+ "' where topic_id='" + string + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean notAdoptTopic(String string, String moTime) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_topic set topic_examine_state = '审核未通过',topic_gmt_modified='" + moTime
					+ "' where topic_id='" + string + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteTopicInviteTeacher(String topicId) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete from bysjglxt_topic_invite_teacher where topic_invite_teacher_id='" + topicId + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<bysjglxt_topic> VO_Topic_By_PageAndSearch(TopicInformationManagementVO topicManagementVO,
			int studentOrTeacher) {
		Session session = getSession();
		String hql = "from bysjglxt_topic where 1=1";
		if (topicManagementVO.getSource() != null && topicManagementVO.getSource().trim().length() > 0) {
			hql = hql + " and topic_source='" + topicManagementVO.getSource().trim() + "'";
		}
		if (topicManagementVO.getType() != null && topicManagementVO.getType().trim().length() > 0) {
			hql = hql + " and topic_type='" + topicManagementVO.getType().trim() + "'";
		}
		if (topicManagementVO.getSearch() != null && topicManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + topicManagementVO.getSearch().trim() + "%";
			hql = hql + " and topic_name_chinese like '" + search + "' or topic_name_english like '" + search + "' ";
		}

		if (topicManagementVO.getState() != null && topicManagementVO.getState().trim().length() > 0) {
			hql = hql + " and topic_examine_state = '" + topicManagementVO.getState() + "'";
		}

		if (studentOrTeacher == 2) {
			hql = hql + " and topic_examine_state = '审核已通过' ";
		}

		if (topicManagementVO.getTeacher() != null && topicManagementVO.getTeacher().trim().length() > 0) {
			hql = hql + " and topic_teacher = '" + topicManagementVO.getTeacher() + "'";
		}
		hql = hql + " order by topic_gmt_create desc";
		Query query = session.createQuery(hql);
		query.setFirstResult((topicManagementVO.getPageIndex() - 1) * topicManagementVO.getPageSize());
		query.setMaxResults(topicManagementVO.getPageSize());
		List<bysjglxt_topic> listbysjglxt_topicByPageAndSearch = query.list();
		session.clear();
		if (topicManagementVO.getSearch() != null && topicManagementVO.getSearch().trim().length() > 0) {
			for (bysjglxt_topic bysjglxt_topic : listbysjglxt_topicByPageAndSearch) {
				bysjglxt_topic.setTopic_name_chinese(
						bysjglxt_topic.getTopic_name_chinese().replaceAll(topicManagementVO.getSearch().trim(),
								"<span style='color: #ff5063;'>" + topicManagementVO.getSearch().trim() + "</span>"));
			}
		}
		return listbysjglxt_topicByPageAndSearch;
	}

	@Override
	public bysjglxt_topic getBysjglxtTopicById(String topic_id) {
		Session session = getSession();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		String hql = "from bysjglxt_topic where topic_id ='" + topic_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_topic = (bysjglxt_topic) query.uniqueResult();
		return bysjglxt_topic;
	}

	@Override
	public bysjglxt_topic_invite_teacher getBysjglxtTopicInviteTeacher(String topic_invite_teache_id) {
		Session session = getSession();
		bysjglxt_topic_invite_teacher bysjglxt_topic_invite_teacher = new bysjglxt_topic_invite_teacher();
		String hql = "from bysjglxt_topic_invite_teacher where topic_invite_teacher_id ='" + topic_invite_teache_id
				+ "'";
		Query query = session.createQuery(hql);
		bysjglxt_topic_invite_teacher = (bysjglxt_topic_invite_teacher) query.uniqueResult();
		return bysjglxt_topic_invite_teacher;
	}

	@Override
	public boolean teacherIsSelect(String Topic_teacher) {
		Session session = getSession();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		boolean flag = false;
		String hql = "from bysjglxt_teacher_user where user_teacher_id = '" + Topic_teacher
				+ "' and user_teacher_max_guidance > user_teacher_guidance_num";
		Query query = session.createQuery(hql);
		bysjglxt_teacher_user = (bysjglxt_teacher_user) query.uniqueResult();
		if (bysjglxt_teacher_user != null) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean topicIsSelect(String topic_id) {
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		boolean flag = false;
		Session session = getSession();
		String hql = "from bysjglxt_topic where topic_id='" + topic_id + "' and topic_student_max>topic_student_num";
		Query query = session.createQuery(hql);
		bysjglxt_topic = (bysjglxt_topic) query.uniqueResult();
		if (bysjglxt_topic != null) {
			flag = true;
		}
		return flag;
	}

	@Override
	public bysjglxt_teacher_user getTeacherUser(String topic_teacher) {
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		Session session = getSession();
		String hql = "from bysjglxt_teacher_user where user_teacher_id='" + topic_teacher + "'";
		Query query = session.createQuery(hql);
		bysjglxt_teacher_user = (bysjglxt_teacher_user) query.uniqueResult();
		return bysjglxt_teacher_user;
	}

	@Override
	public boolean createStudentSclectInformation(bysjglxt_topic_select bysjglxt_topic_select) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.saveOrUpdate(bysjglxt_topic_select);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addTopicStudentNum(String topicID) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_topic set topic_student_num = topic_student_num+1 where topic_id = '"
					+ topicID + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addTeacherUserSrtudentNum(String user_teacher_id) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_teacher_user set user_teacher_guidance_num = user_teacher_guidance_num+1 where user_teacher_id = '"
					+ user_teacher_id + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateStudentList(String topicId, String studentIdList) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_topic set topic_student = '" + studentIdList + "' where topic_id = '"
					+ topicId + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean teacherIsUserId(String user_teacher_id) {
		Session session = getSession();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		String hql = "from bysjglxt_teacher_user where user_teacher_id='" + user_teacher_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_teacher_user = (bysjglxt_teacher_user) query.uniqueResult();
		if (bysjglxt_teacher_user == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public bysjglxt_teacher_user getTeacherUserInfo(String topic_teacher) {
		Session session = getSession();
		bysjglxt_teacher_user bysjglxt_teacher_user = null;
		String hql = "from bysjglxt_teacher_user where user_teacher_id='" + topic_teacher + "'";
		Query query = session.createQuery(hql);
		bysjglxt_teacher_user = (bysjglxt_teacher_user) query.uniqueResult();
		return bysjglxt_teacher_user;
	}

	@Override
	public bysjglxt_teacher_basic getTeacherBasicInfo(String user_teacher_basic) {
		Session session = getSession();
		bysjglxt_teacher_basic TeacherBasicInformation = null;
		String hql = "from bysjglxt_teacher_basic where teacher_basic_id='" + user_teacher_basic + "'";
		Query query = session.createQuery(hql);
		TeacherBasicInformation = (bysjglxt_teacher_basic) query.uniqueResult();
		return TeacherBasicInformation;
	}

	@Override
	public bysjglxt_section getTeacherSection(String user_teacher_section) {
		Session session = getSession();
		bysjglxt_section bysjglxt_section = new bysjglxt_section();
		String hql = "from bysjglxt_section where section_id ='" + user_teacher_section + "'";
		Query query = session.createQuery(hql);
		bysjglxt_section = (bysjglxt_section) query.uniqueResult();
		return bysjglxt_section;
	}

	@Override
	public List<bysjglxt_topic> getAllTopic() {
		Session session = getSession();
		List<bysjglxt_topic> listAllTopic = new ArrayList<bysjglxt_topic>();
		String hql = "from bysjglxt_topic where topic_examine_state = '审核已通过'";
		Query query = session.createQuery(hql);
		listAllTopic = query.list();
		return listAllTopic;
	}

	@Override
	public List<bysjglxt_topic> VO_Topic_BySearch(TopicInformationManagementVO topicManagementVO,
			int studentOrTeacher) {
		Session session = getSession();
		String hql = "from bysjglxt_topic where 1=1";
		if (topicManagementVO.getSource() != null && topicManagementVO.getSource().trim().length() > 0) {
			hql = hql + " and topic_source='" + topicManagementVO.getSource().trim() + "'";
		}
		if (topicManagementVO.getType() != null && topicManagementVO.getType().trim().length() > 0) {
			hql = hql + " and topic_type='" + topicManagementVO.getType().trim() + "'";
		}
		if (topicManagementVO.getSearch() != null && topicManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + topicManagementVO.getSearch().trim() + "%";
			hql = hql + " and topic_name_chinese like '" + search + "' or topic_name_english like '" + search + "'";
		}
		if (topicManagementVO.getState() != null && topicManagementVO.getState().trim().length() > 0) {
			hql = hql + " and topic_examine_state = '" + topicManagementVO.getState() + "'";
		}
		if (studentOrTeacher == 2) {
			hql = hql + " and topic_examine_state = '审核已通过' ";
		}
		if (topicManagementVO.getTeacher() != null && topicManagementVO.getTeacher().trim().length() > 0) {
			hql = hql + " and topic_teacher = '" + topicManagementVO.getTeacher() + "'";
		}
		hql = hql + " order by topic_gmt_create desc";
		Query query = session.createQuery(hql);
		List<bysjglxt_topic> listbysjglxt_topicByPageAndSearch = query.list();
		session.clear();
		return listbysjglxt_topicByPageAndSearch;
	}

	@Override
	public boolean updateStudentUserRecord(String studentID) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_student_user set user_student_is_select_topic = '1' where user_student_id = '"
					+ studentID + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;

	}

	@Override
	public bysjglxt_student_user studentIsSelectTopic(String studentID) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		Session session = getSession();
		String hql = "from bysjglxt_student_user where user_student_id ='" + studentID + "'";
		Query query = session.createQuery(hql);
		bysjglxt_student_user = (bysjglxt_student_user) query.uniqueResult();
		return bysjglxt_student_user;
	}

	@Override
	public List<bysjglxt_topic_select> getTopicSelectList(String topicId) {
		List<bysjglxt_topic_select> listTopicSelect = new ArrayList<bysjglxt_topic_select>();
		Session session = getSession();
		String hql = "from bysjglxt_topic_select where topic_select_topic = '" + topicId + "'";
		Query query = session.createQuery(hql);
		listTopicSelect = query.list();
		return listTopicSelect;
	}

	@Override
	public boolean updateStudentUserNotSelect(String topic_select_student) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_student_user set user_student_is_select_topic = '2' where user_student_id = '"
					+ topic_select_student + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteTopicSelect(String topic_select_id) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete from bysjglxt_topic_select where topic_select_id='" + topic_select_id + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public bysjglxt_topic_select getStudentTopicSelectByUserId(String studentUserId) {
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		Session session = getSession();
		String hql = "from bysjglxt_topic_select where topic_select_student = '" + studentUserId + "'";
		Query query = session.createQuery(hql);
		bysjglxt_topic_select = (bysjglxt_topic_select) query.uniqueResult();
		return bysjglxt_topic_select;
	}

	@Override
	public bysjglxt_leader getLeader(String user_teacher_id) {
		bysjglxt_leader bysjglxt_leader = new bysjglxt_leader();
		Session session = getSession();
		String hql = "from bysjglxt_leader where leader_teacher_id = '" + user_teacher_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_leader = (bysjglxt_leader) query.uniqueResult();
		return bysjglxt_leader;
	}

	@Override
	public List<bysjglxt_topic> VO_Topic_By_PageAndSearch(TopicInformationManagementVO topicManagementVO,
			String teacherUserId) {
		Session session = getSession();
		String hql = "from bysjglxt_topic where 1=1";
		if (topicManagementVO.getSource() != null && topicManagementVO.getSource().trim().length() > 0) {
			hql = hql + " and topic_source='" + topicManagementVO.getSource().trim() + "'";
		}
		if (topicManagementVO.getType() != null && topicManagementVO.getType().trim().length() > 0) {
			hql = hql + " and topic_type='" + topicManagementVO.getType().trim() + "'";
		}
		if (topicManagementVO.getSearch() != null && topicManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + topicManagementVO.getSearch().trim() + "%";
			hql = hql + " and topic_name_chinese like '" + search + "' or topic_name_english like '" + search + "' ";
		}

		if (topicManagementVO.getState() != null && topicManagementVO.getState().trim().length() > 0) {
			hql = hql + " and topic_examine_state = '" + topicManagementVO.getState() + "'";
		}
		hql = hql + " and topic_teacher = '" + teacherUserId + "'";
		hql = hql + " order by topic_gmt_create desc";
		Query query = session.createQuery(hql);
		query.setFirstResult((topicManagementVO.getPageIndex() - 1) * topicManagementVO.getPageSize());
		query.setMaxResults(topicManagementVO.getPageSize());
		List<bysjglxt_topic> listbysjglxt_topicByPageAndSearch = query.list();
		session.clear();
		if (topicManagementVO.getSearch() != null && topicManagementVO.getSearch().trim().length() > 0) {
			for (bysjglxt_topic bysjglxt_topic : listbysjglxt_topicByPageAndSearch) {
				bysjglxt_topic.setTopic_name_chinese(
						bysjglxt_topic.getTopic_name_chinese().replaceAll(topicManagementVO.getSearch().trim(),
								"<span style='color: #ff5063;'>" + topicManagementVO.getSearch().trim() + "</span>"));
			}
		}
		return listbysjglxt_topicByPageAndSearch;
	}

	@Override
	public List<bysjglxt_topic> VO_Topic_BySearch(TopicInformationManagementVO topicManagementVO,
			String teacherUserId) {
		Session session = getSession();
		String hql = "from bysjglxt_topic where 1=1";
		if (topicManagementVO.getSource() != null && topicManagementVO.getSource().trim().length() > 0) {
			hql = hql + " and topic_source='" + topicManagementVO.getSource().trim() + "'";
		}
		if (topicManagementVO.getType() != null && topicManagementVO.getType().trim().length() > 0) {
			hql = hql + " and topic_type='" + topicManagementVO.getType().trim() + "'";
		}
		if (topicManagementVO.getSearch() != null && topicManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + topicManagementVO.getSearch().trim() + "%";
			hql = hql + " and topic_name_chinese like '" + search + "' or topic_name_english like '" + search + "'";
		}
		if (topicManagementVO.getState() != null && topicManagementVO.getState().trim().length() > 0) {
			hql = hql + " and topic_examine_state = '" + topicManagementVO.getState() + "'";
		}
		hql = hql + " and topic_teacher = '" + teacherUserId + "'";
		hql = hql + " order by topic_gmt_create desc";
		Query query = session.createQuery(hql);
		List<bysjglxt_topic> listbysjglxt_topicByPageAndSearch = query.list();
		session.clear();
		return listbysjglxt_topicByPageAndSearch;
	}

	@Override
	public List<bysjglxt_topic_select> getTopicSelectByTopicId(String topicId) {
		List<bysjglxt_topic_select> listSelect = new ArrayList<bysjglxt_topic_select>();
		Session session = getSession();
		String hql = "from bysjglxt_topic_select where topic_select_topic = '" + topicId + "'";
		Query query = session.createQuery(hql);
		listSelect = query.list();
		return listSelect;
	}

	@Override
	public bysjglxt_student_basic getStudentBasic(String user_student_basic) {
		bysjglxt_student_basic listSelect = new bysjglxt_student_basic();
		Session session = getSession();
		String hql = "from bysjglxt_student_basic where student_basic_id = '" + user_student_basic + "'";
		Query query = session.createQuery(hql);
		listSelect = (bysjglxt_student_basic) query.uniqueResult();
		return listSelect;
	}

	@Override
	public bysjglxt_topic studentIsExistSpecial(String studentID, String topicID) {
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		Session session = getSession();
		String sel = "%" + studentID + "%";
		String hql = "from bysjglxt_topic where topic_id = '" + topicID + "' and topic_student like '" + sel + "'";
		Query query = session.createQuery(hql);
		bysjglxt_topic = (bysjglxt_topic) query.uniqueResult();
		return bysjglxt_topic;
	}

	@Override
	public List<bysjglxt_topic> VO_Topic_By_StudentPageAndSearch(TopicInformationManagementVO topicManagementVO,
			String studentUserId) {
		Session session = getSession();
		String hql = "select topic from bysjglxt_topic topic,bysjglxt_topic_select selecttopic where selecttopic.topic_select_topic=topic.topic_id";
		if (topicManagementVO.getSource() != null && topicManagementVO.getSource().trim().length() > 0) {
			hql = hql + " and topic.topic_source='" + topicManagementVO.getSource().trim() + "'";
		}
		if (topicManagementVO.getType() != null && topicManagementVO.getType().trim().length() > 0) {
			hql = hql + " and topic.topic_type='" + topicManagementVO.getType().trim() + "'";
		}
		if (topicManagementVO.getSearch() != null && topicManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + topicManagementVO.getSearch().trim() + "%";
			hql = hql + " and topic.topic_name_chinese like '" + search + "' or topic.topic_name_english like '"
					+ search + "' ";
		}

		if (topicManagementVO.getState() != null && topicManagementVO.getState().trim().length() > 0) {
			hql = hql + " and topic.topic_examine_state = '" + topicManagementVO.getState() + "'";
		}
		hql = hql + " and selecttopic.topic_select_student = '" + studentUserId + "'";
		hql = hql + " order by topic.topic_gmt_create desc";
		Query query = session.createQuery(hql);
		query.setFirstResult((topicManagementVO.getPageIndex() - 1) * topicManagementVO.getPageSize());
		query.setMaxResults(topicManagementVO.getPageSize());
		List<bysjglxt_topic> listbysjglxt_topicByPageAndSearch = query.list();
		session.clear();
		if (topicManagementVO.getSearch() != null && topicManagementVO.getSearch().trim().length() > 0) {
			for (bysjglxt_topic bysjglxt_topic : listbysjglxt_topicByPageAndSearch) {
				bysjglxt_topic.setTopic_name_chinese(
						bysjglxt_topic.getTopic_name_chinese().replaceAll(topicManagementVO.getSearch().trim(),
								"<span style='color: #ff5063;'>" + topicManagementVO.getSearch().trim() + "</span>"));
			}
		}
		return listbysjglxt_topicByPageAndSearch;
	}

	@Override
	public bysjglxt_student_user getStudentUserByUserId(String userId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		Session session = getSession();
		String hql = "from bysjglxt_student_user where user_student_id = '" + userId + "'";
		Query query = session.createQuery(hql);
		bysjglxt_student_user = (bysjglxt_student_user) query.uniqueResult();
		return bysjglxt_student_user;
	}

	@Override
	public boolean updateTeacherSelectNum(String topic_select_teacher_tutor) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_teacher_user set user_teacher_guidance_num = user_teacher_guidance_num-1 where user_teacher_id = '"
					+ topic_select_teacher_tutor + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateTopicNum(String topic_select_topic) {

		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_topic set topic_student_num = topic_student_num-1 where topic_id = '"
					+ topic_select_topic + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

}