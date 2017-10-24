package com.bysjglxt.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.TestDao;
import com.bysjglxt.domain.DO.test;

public class TestDaoImpl implements TestDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void addTest(test t) {
		getSession().saveOrUpdate(t);
	}

}
