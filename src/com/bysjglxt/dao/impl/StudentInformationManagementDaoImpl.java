package com.bysjglxt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.StudentInformationManagementDao;
import com.bysjglxt.domain.DTO.StudentInformationDTO;

public class StudentInformationManagementDaoImpl implements StudentInformationManagementDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public List<StudentInformationDTO> list_StudentInformationDTO_All() {
		Session session = getSession();
		List<StudentInformationDTO> listAllStudentInformation = null;

		String hql = "from bysjglxt_student_basic,bysjglxt_student_user where bysjglxt_student_user.user_student_basic = bysjglxt_student_basic.student_basic_id";
		Query query = session.createQuery(hql);
		
		listAllStudentInformation = query.list();
		session.clear();
		return listAllStudentInformation;
	}
}
