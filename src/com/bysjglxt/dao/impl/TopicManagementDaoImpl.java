package com.bysjglxt.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.TopicManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DO.bysjglxt_topic_invite_teacher;
import com.bysjglxt.domain.VO.TopicManagementVO;

import util.TeamUtil;

public class TopicManagementDaoImpl implements TopicManagementDao {
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
			System.out.println(topicID);
			String hql = "delete from bysjglxt_topic where topic_id='" + topicID + "'";
			Query query = session.createQuery(hql);
			System.out.println(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateTopicState(String topicID) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_topic set topic_examine_state = '审核已通过' where topic_id='" + topicID + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean closeTopic(String string) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_topic set topic_examine_state = '关闭' where topic_id='" + string + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean notAdoptTopic(String string) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_topic set topic_examine_state = '审核未通过' where topic_id='" + string + "'";
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
			System.out.println(topicId);
			String hql = "delete from bysjglxt_topic_invite_teacher where topic_invite_teacher_topic_id='" + topicId
					+ "'";
			Query query = session.createQuery(hql);
			System.out.println(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<bysjglxt_topic> VO_Topic_By_PageAndSearch(TopicManagementVO topicManagementVO) {
		Session session = getSession();
		String hql = "from bysjglxt_topic where 1=1";
		boolean isChinese = false;
		if (topicManagementVO.getSource() != null && topicManagementVO.getSource().trim().length() > 0) {
			hql = hql + " and topic_source='" + topicManagementVO.getSource().trim() + "'";
		}
		if (topicManagementVO.getType() != null && topicManagementVO.getType().trim().length() > 0) {
			hql = hql + " and topic_type='" + topicManagementVO.getType().trim() + "'";
		}
		if (TeamUtil.isChinese(topicManagementVO.getSearch())) {
			isChinese = true;
		}
		if (isChinese && topicManagementVO.getSearch() != null && topicManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + topicManagementVO.getSearch().trim() + "%";
			hql = hql + " and topic_name_chinese like '" + search + "'";
		}
		if (!isChinese && topicManagementVO.getSearch() != null && topicManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + topicManagementVO.getSearch().trim() + "%";
			hql = hql + " and topic_name_english like '" + search + "'";
		}
		hql = hql + " order by topic_gmt_create";
		System.out.println("kk\t" + hql);
		Query query = session.createQuery(hql);
		query.setFirstResult((topicManagementVO.getPageIndex() - 1) * topicManagementVO.getPageSize());
		query.setMaxResults(topicManagementVO.getPageSize());
		List<bysjglxt_topic> listbysjglxt_topicByPageAndSearch = query.list();
		session.clear();
		if (isChinese && topicManagementVO.getSearch() != null && topicManagementVO.getSearch().trim().length() > 0) {
			for (bysjglxt_topic bysjglxt_topic : listbysjglxt_topicByPageAndSearch) {
				bysjglxt_topic.setTopic_name_chinese(bysjglxt_topic.getTopic_name_chinese().replaceAll(
						bysjglxt_topic.getTopic_name_chinese(),
						"<span style='color: #ff5063;'>" + bysjglxt_topic.getTopic_name_chinese().trim() + "</span>"));
			}
		}
		if (!isChinese && topicManagementVO.getSearch() != null && topicManagementVO.getSearch().trim().length() > 0) {
			for (bysjglxt_topic bysjglxt_topic : listbysjglxt_topicByPageAndSearch) {
				bysjglxt_topic.setTopic_name_english(bysjglxt_topic.getTopic_name_english().replaceAll(
						bysjglxt_topic.getTopic_name_english(),
						"<span style='color: #ff5063;'>" + bysjglxt_topic.getTopic_name_english().trim() + "</span>"));
			}
		}
		return listbysjglxt_topicByPageAndSearch;
	}

	@Override
	public bysjglxt_topic_invite_teacher getBysjglxtTopicInviteTeacher(String topic_id) {
		bysjglxt_topic_invite_teacher bysjglxt_topic_invite_teacher = new bysjglxt_topic_invite_teacher();
		Session session = getSession();
		String hql = "from bysjglxt_topic_invite_teacher where topic_invite_teacher_topic_id = '" + topic_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_topic_invite_teacher = (bysjglxt_topic_invite_teacher) query.uniqueResult();
		return bysjglxt_topic_invite_teacher;
	}

}
