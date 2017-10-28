package com.bysjglxt.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.TeacherInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;

public class TeacherInformationManagementDaoImpl implements TeacherInformationManagementDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public List<bysjglxt_teacher_user> list_TeacherUserInformation_All() {
		Session session = getSession();
		List<bysjglxt_teacher_user> listAllTeacherUserInformation = null;
		String hql = "from bysjglxt_teacher_user";
		Query query = session.createQuery(hql);
		listAllTeacherUserInformation = query.list();
		return listAllTeacherUserInformation;
	}

	@Override
	public bysjglxt_teacher_basic get_TeacherBasicInformation_ByUserBasic(String user_teacher_basic) {
		Session session = getSession();
		bysjglxt_teacher_basic TeacherBasicInformation = null;
		String hql = "from bysjglxt_teacher_basic where user_teacher_basic='" + user_teacher_basic + "'";
		Query query = session.createQuery(hql);
		TeacherBasicInformation = (bysjglxt_teacher_basic) query.uniqueResult();
		return TeacherBasicInformation;
	}

	@Override
	public boolean saveTeacherBasic(bysjglxt_teacher_basic bysjglxt_teacher_basic) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.save(bysjglxt_teacher_basic);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean saveTeacher(bysjglxt_teacher_user bysjglxt_teacher_user) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.save(bysjglxt_teacher_user);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public bysjglxt_teacher_user getStudentById(String teacher_id) {
		Session session = getSession();
		bysjglxt_teacher_user TeacherInformation = null;
		String hql = "from bysjglxt_teacher_user where user_teacher_id='" + teacher_id + "'";
		Query query = session.createQuery(hql);
		TeacherInformation = (bysjglxt_teacher_user) query.uniqueResult();
		return TeacherInformation;
	}

	@Override
	public boolean deleteTeacherBasicInfoById(String user_teacher_basic) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete from bysjglxt_teacher_basic where teacher_basic_id='" + user_teacher_basic + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public boolean deleteTeacherInfoById(String user_teacher_id) {
		Session session = getSession();
		String hql = "delete from bysjglxt_teacher_user where user_teacher_id='" + user_teacher_id + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}
}
