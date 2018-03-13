package com.bysjglxt.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.MajorManagementDao;

public class MajorManagementDaoImpl implements MajorManagementDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
