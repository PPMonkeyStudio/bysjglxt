package com.bysjglxt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.TopicInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_leader;
import com.bysjglxt.domain.DO.bysjglxt_notice;
import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic;
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
	public boolean addObject(Object obj) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.saveOrUpdate(obj);
			session.flush();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public boolean CreateTopic(bysjglxt_topic newTopic) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.saveOrUpdate(newTopic);
			session.flush();
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
		hql = hql + " order by topic_examine_state desc,topic_gmt_create desc";
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
			session.flush();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean saveObj(Object obj) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.saveOrUpdate(obj);
			session.flush();
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

	/**
	 * 弃用
	 */
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
		hql = hql + " order by topic_examine_state desc,topic_gmt_create desc";
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
		session.clear();
		return listSelect;
	}

	@Override
	public bysjglxt_student_basic getStudentBasic(String user_student_basic) {
		bysjglxt_student_basic listSelect = new bysjglxt_student_basic();
		Session session = getSession();
		String hql = "from bysjglxt_student_basic where student_basic_id = '" + user_student_basic + "'";
		Query query = session.createQuery(hql);
		listSelect = (bysjglxt_student_basic) query.uniqueResult();
		session.clear();
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

	@Override
	public bysjglxt_process_definition getProcessDefinitionByName(String string) {
		bysjglxt_process_definition bysjglxt_process_definition = new bysjglxt_process_definition();
		Session session = getSession();
		String hql = "from bysjglxt_process_definition where process_definition_name = '" + string + "'";
		Query query = session.createQuery(hql);
		bysjglxt_process_definition = (bysjglxt_process_definition) query.uniqueResult();
		return bysjglxt_process_definition;
	}

	@Override
	public bysjglxt_process_instance getProcessInstanceByStateAndDefinitionId(String process_definition_id,
			String string) {
		bysjglxt_process_instance bysjglxt_process_instance = new bysjglxt_process_instance();
		Session session = getSession();
		String hql = "from bysjglxt_process_instance where process_instance_process_definition = '"
				+ process_definition_id + "' and process_instance_state = '" + string + "'";
		Query query = session.createQuery(hql);
		bysjglxt_process_instance = (bysjglxt_process_instance) query.uniqueResult();
		return bysjglxt_process_instance;
	}

	@Override
	public bysjglxt_task_instance getTaskInstanceByProcessInstanceIdAndState(String process_instance_id, int i) {
		bysjglxt_task_instance bysjglxt_task_instance = new bysjglxt_task_instance();
		Session session = getSession();
		String hql = "from bysjglxt_task_instance where task_instance_process_instance = '" + process_instance_id
				+ "' and task_instance_state = '" + i + "'";
		Query query = session.createQuery(hql);
		bysjglxt_task_instance = (bysjglxt_task_instance) query.uniqueResult();
		return bysjglxt_task_instance;
	}

	@Override
	public bysjglxt_task_definition getTaskDefinitionById(String task_instance_task_definition) {
		bysjglxt_task_definition bysjglxt_task_definition = new bysjglxt_task_definition();
		Session session = getSession();
		String hql = "from bysjglxt_task_definition where task_definition_id = '" + task_instance_task_definition + "'";
		Query query = session.createQuery(hql);
		bysjglxt_task_definition = (bysjglxt_task_definition) query.uniqueResult();
		return bysjglxt_task_definition;
	}

	@Override
	public List<bysjglxt_leader> getAllLeader() {
		Session session = getSession();
		List<bysjglxt_leader> bysjglxt_leader = new ArrayList<bysjglxt_leader>();
		String hql = "from bysjglxt_leader";
		Query query = session.createQuery(hql);
		bysjglxt_leader = query.list();
		return bysjglxt_leader;
	}

	@Override
	public boolean createNoti1ceRecord(bysjglxt_notice bysjglxt_notice) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.saveOrUpdate(bysjglxt_notice);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public bysjglxt_topic_select getSelectTopicById(String selectId) {
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		Session session = getSession();
		String hql = "from bysjglxt_topic_select where topic_select_id = '" + selectId + "'";
		Query query = session.createQuery(hql);
		bysjglxt_topic_select = (bysjglxt_topic_select) query.uniqueResult();
		return bysjglxt_topic_select;
	}

	@Override
	public bysjglxt_topic_select getSelectTopicByOwnId(String selectId) {
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		Session session = getSession();
		String hql = "from bysjglxt_topic_select where topic_select_topic = '" + selectId + "'";
		Query query = session.createQuery(hql);
		bysjglxt_topic_select = (bysjglxt_topic_select) query.uniqueResult();
		return bysjglxt_topic_select;
	}

	// 根据流程实例化者、状态以及流程定义名获取流程实例
	@Override
	public bysjglxt_process_instance getProcessInstanceByManStateAndName(String topic_select_student) {
		bysjglxt_process_instance bysjglxt_process_instance = new bysjglxt_process_instance();
		Session session = getSession();
		String hql = "select processInstance from bysjglxt_process_instance processInstance,bysjglxt_process_definition processDefinition where processInstance.process_instance_process_definition=processDefinition.process_definition_id ";
		hql = hql + " and processInstance.process_instance_man='" + topic_select_student
				+ "' and processInstance.process_instance_state='活动' and processDefinition.process_definition_name='选题流程' ";
		Query query = session.createQuery(hql);
		bysjglxt_process_instance = (bysjglxt_process_instance) query.uniqueResult();
		return bysjglxt_process_instance;
	}

	@Override
	public bysjglxt_task_definition getTaskDefinitionByName(String string) {
		bysjglxt_task_definition bysjglxt_task_definition = new bysjglxt_task_definition();
		Session session = getSession();
		String hql = "from bysjglxt_task_definition where task_definition_name = '" + string + "'";
		Query query = session.createQuery(hql);
		bysjglxt_task_definition = (bysjglxt_task_definition) query.uniqueResult();
		return bysjglxt_task_definition;
	}

	@Override
	public bysjglxt_task_instance getTaskInstanceByNameAndProcessInstanceId(String task_definition_id,
			String process_instance_id) {
		bysjglxt_task_instance bysjglxt_task_instance = new bysjglxt_task_instance();
		Session session = getSession();
		String hql = "from bysjglxt_task_instance where task_instance_process_instance = '" + process_instance_id
				+ "' and task_instance_task_definition = '" + task_definition_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_task_instance = (bysjglxt_task_instance) query.uniqueResult();
		return bysjglxt_task_instance;
	}

	/**
	 * 获取筛选后可以进行被指定的学生
	 */
	@Override
	public List<bysjglxt_student_user> getListStudentUserByDesignation(String studentMajor, String studentGrade,
			String search) {
		List<bysjglxt_student_user> listUser = new ArrayList<bysjglxt_student_user>();
		String hql = "select studentUser from bysjglxt_student_user studentUser,bysjglxt_student_basic studentBasic where studentUser.user_student_basic=studentBasic.student_basic_id ";
		hql = hql + " and studentUser.user_student_is_operate_premission=1 ";
		if (search != null && search.trim().length() > 0) {
			String sss = "%" + search.trim() + "%";
			hql = hql + " and studentBasic.student_basic_num like '" + sss + "'";
		}
		Session session = getSession();
		Query query = session.createQuery(hql);
		listUser = query.list();
		session.clear();
		return listUser;
	}

	// 根据课题ID,模糊查询学生是否被指定到当前课题
	@Override
	public bysjglxt_topic getTopicByIdAndStudent(String student_user, String topicId) {
		String student = "%" + student_user + "%";
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		String hql = "from bysjglxt_topic where topic_id='" + topicId + "' and topic_student like '" + student + "'";
		Session session = getSession();
		Query query = session.createQuery(hql);
		bysjglxt_topic = (bysjglxt_topic) query.uniqueResult();
		session.clear();
		return bysjglxt_topic;
	}

	@Override
	public bysjglxt_process_instance getProcessInstanceByManStatePAndName(String topic_select_student) {
		bysjglxt_process_instance bysjglxt_process_instance = new bysjglxt_process_instance();
		Session session = getSession();
		String hql = "select processInstance from bysjglxt_process_instance processInstance,bysjglxt_process_definition processDefinition where processInstance.process_instance_process_definition=processDefinition.process_definition_id ";
		hql = hql + " and processInstance.process_instance_man='" + topic_select_student
				+ "' and processInstance.process_instance_state='活动' and processDefinition.process_definition_name='毕业设计流程' ";
		Query query = session.createQuery(hql);
		bysjglxt_process_instance = (bysjglxt_process_instance) query.uniqueResult();
		return bysjglxt_process_instance;
	}

}