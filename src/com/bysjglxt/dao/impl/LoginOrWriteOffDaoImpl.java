package com.bysjglxt.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.LoginOrWriteOffDao;

public class LoginOrWriteOffDaoImpl implements LoginOrWriteOffDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

}
