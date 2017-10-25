package com.bysjglxt.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.StudentInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;

public class StudentInformationManagementDaoImpl implements StudentInformationManagementDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public List<bysjglxt_student_user> list_StudentUserInformation_All() {
		Session session = getSession();
		List<bysjglxt_student_user> listAllStudentUserInformation = null;

		String hql = "from bysjglxt_student_user";
		Query query = session.createQuery(hql);

		listAllStudentUserInformation = query.list();

		return listAllStudentUserInformation;
	}

	@Override
	public bysjglxt_student_basic get_StudentBasicInformation_ByUserBasic(String id) {
		Session session = getSession();
		bysjglxt_student_basic StudentBasicInformation = null;

		String hql = "from bysjglxt_student_basic where student_basic_id='" + id + "'";
		Query query = session.createQuery(hql);

		StudentBasicInformation = (bysjglxt_student_basic) query.uniqueResult();

		System.out.println("StudentBasicInformation:" + StudentBasicInformation);

		return StudentBasicInformation;
	}

}
