package com.bysjglxt.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.TopicManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DO.bysjglxt_topic_invite_teacher;

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

}
