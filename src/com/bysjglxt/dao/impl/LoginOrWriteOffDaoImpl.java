package com.bysjglxt.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.LoginOrWriteOffDao;
import com.bysjglxt.domain.DO.bysjglxt_leader;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;

public class LoginOrWriteOffDaoImpl implements LoginOrWriteOffDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * 根据学号得到学生登录表信息
	 */
	@Override
	public bysjglxt_student_user getBysjglxtStudentUserByNum(String username) {
		Session session = getSession();
		String hql = "from bysjglxt_student_user where user_student_num='" + username + "'";
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		Query query = session.createQuery(hql);
		bysjglxt_student_user = (bysjglxt_student_user) query.uniqueResult();
		return bysjglxt_student_user;
	}

	@Override
	public bysjglxt_teacher_user getBysjglxtTeacherUserByNum(String username) {
		Session session = getSession();
		String hql = "from bysjglxt_teacher_user where user_teacher_num='" + username + "'";
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		Query query = session.createQuery(hql);
		bysjglxt_teacher_user = (bysjglxt_teacher_user) query.uniqueResult();
		return bysjglxt_teacher_user;
	}

	@Override
	public bysjglxt_teacher_basic getBysjglxtTeacherBasicById(String user_teacher_basic) {
		Session session = getSession();
		String hql = "from bysjglxt_teacher_basic where teacher_basic_id='" + user_teacher_basic + "'";
		bysjglxt_teacher_basic bysjglxt_teacher_basic = new bysjglxt_teacher_basic();
		Query query = session.createQuery(hql);
		bysjglxt_teacher_basic = (bysjglxt_teacher_basic) query.uniqueResult();
		return bysjglxt_teacher_basic;
	}

	@Override
	public bysjglxt_section getBysjglxtTeacherSection(String user_teacher_section) {
		Session session = getSession();
		String hql = "from bysjglxt_section where section_id = '" + user_teacher_section + "'";
		bysjglxt_section bysjglxt_section = new bysjglxt_section();
		Query query = session.createQuery(hql);
		bysjglxt_section = (bysjglxt_section) query.uniqueResult();
		return bysjglxt_section;
	}

	@Override
	public bysjglxt_student_basic getBysjglxtStudentBasicById(String user_student_basic) {
		Session session = getSession();
		String hql = "from bysjglxt_student_basic where student_basic_id = '" + user_student_basic + "'";
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		Query query = session.createQuery(hql);
		bysjglxt_student_basic = (bysjglxt_student_basic) query.uniqueResult();
		return bysjglxt_student_basic;
	}

	@Override
	public bysjglxt_leader getLeaderById(String user_teacher_id) {
		bysjglxt_leader bysjglxt_leader = new bysjglxt_leader();
		Session session = getSession();
		String hql = "from bysjglxt_leader where leader_teacher_id = '" + user_teacher_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_leader = (bysjglxt_leader) query.uniqueResult();
		return bysjglxt_leader;
	}
}