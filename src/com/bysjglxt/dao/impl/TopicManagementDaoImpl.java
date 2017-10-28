package com.bysjglxt.dao.impl;

import org.hibernate.SessionFactory;

import com.bysjglxt.dao.TopicManagementDao;

public class TopicManagementDaoImpl implements TopicManagementDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
