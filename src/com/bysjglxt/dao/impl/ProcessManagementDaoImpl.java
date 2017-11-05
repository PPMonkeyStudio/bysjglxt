package com.bysjglxt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.ProcessManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;

public class ProcessManagementDaoImpl implements ProcessManagementDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public int createProcessDefine(bysjglxt_process_definition selectTopicProcessDefinition) {
		int flag = 1;
		try {
			Session session = getSession();
			session.saveOrUpdate(selectTopicProcessDefinition);
		} catch (Exception e) {
			flag = -1;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int createTaskDefine(bysjglxt_task_definition selectTopicTaskDefine) {
		int flag = 1;
		try {
			Session session = getSession();
			session.saveOrUpdate(selectTopicTaskDefine);
		} catch (Exception e) {
			flag = -1;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<bysjglxt_process_definition> getAllProcessDefinition() {
		List<bysjglxt_process_definition> listProcessDefinition = new ArrayList<bysjglxt_process_definition>();
		Session session = getSession();
		String hql = "from bysjglxt_process_definition";
		Query query = session.createQuery(hql);
		listProcessDefinition = query.list();
		return listProcessDefinition;
	}
}
