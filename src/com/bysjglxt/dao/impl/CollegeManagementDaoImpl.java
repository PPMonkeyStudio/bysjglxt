package com.bysjglxt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.CollegeManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_college;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;

public class CollegeManagementDaoImpl implements CollegeManagementDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	// 遍历出所有的学院
	@Override
	public List<bysjglxt_college> getListCollege() {
		List<bysjglxt_college> listCollege = new ArrayList<>();
		Session session = getSession();
		String hql = "from bysjglxt_college";
		Query query = session.createQuery(hql);
		listCollege = query.list();
		session.clear();
		return listCollege;
	}

	// 查找对应学院的管理员老师userId
	@Override
	public List<bysjglxt_teacher_user> getListCollegeAdmin(String college_id) {
		List<bysjglxt_teacher_user> listTeacherUser = new ArrayList<>();
		Session session = getSession();
		String hql = "from bysjglxt_teacher_user where user_teacher_belong_college='" + college_id
				+ "' and user_teacher_is_college_admin=1";
		Query query = session.createQuery(hql);
		listTeacherUser = query.list();
		session.clear();
		return listTeacherUser;
	}

	// 根据basicId获取basic表信息
	@Override
	public bysjglxt_teacher_basic getTeacherBasicById(String trim) {
		bysjglxt_teacher_basic bysjglxt_teacher_basic = new bysjglxt_teacher_basic();
		Session session = getSession();
		String hql = "from bysjglxt_teacher_basic where teacher_basic_id='" + trim + "'";
		Query query = session.createQuery(hql);
		bysjglxt_teacher_basic = (bysjglxt_teacher_basic) query.uniqueResult();
		session.clear();
		return bysjglxt_teacher_basic;
	}

	// 根据Id获取教研室
	@Override
	public bysjglxt_section getSectionById(String trim) {
		bysjglxt_section bysjglxt_section = new bysjglxt_section();
		Session session = getSession();
		String hql = "from bysjglxt_section where section_id='" + trim + "'";
		Query query = session.createQuery(hql);
		bysjglxt_section = (bysjglxt_section) query.uniqueResult();
		session.clear();
		return bysjglxt_section;
	}

	// 根据userId获取user信息
	@Override
	public bysjglxt_teacher_user getTeacherUserById(String teacherUserId) {
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		Session session = getSession();
		String hql = "from bysjglxt_teacher_user where user_teacher_id='" + teacherUserId + "'";
		Query query = session.createQuery(hql);
		bysjglxt_teacher_user = (bysjglxt_teacher_user) query.uniqueResult();
		session.clear();
		return bysjglxt_teacher_user;
	}

	@Override
	public void saveObj(Object obj) {
		Session session = getSession();
		session.saveOrUpdate(obj);
		session.flush();
	}

	/**
	 * 根据学院代码进行查找
	 */
	@Override
	public bysjglxt_college getCollegeByCode(String collegeCode) {
		bysjglxt_college bysjglxt_college = new bysjglxt_college();
		Session session = getSession();
		String hql = "from bysjglxt_college where college_code='" + collegeCode + "'";
		Query query = session.createQuery(hql);
		bysjglxt_college = (bysjglxt_college) query.uniqueResult();
		session.clear();
		return bysjglxt_college;
	}

	/**
	 * 根据学院名称进行查找
	 */
	@Override
	public bysjglxt_college getCollegeByName(String collegeName) {
		bysjglxt_college bysjglxt_college = new bysjglxt_college();
		Session session = getSession();
		String hql = "from bysjglxt_college where college_name='" + collegeName + "'";
		Query query = session.createQuery(hql);
		bysjglxt_college = (bysjglxt_college) query.uniqueResult();
		session.clear();
		return bysjglxt_college;
	}

	/**
	 * 根据工号查找教师信息
	 */
	@Override
	public bysjglxt_teacher_basic getTeacherBasicByJobNum(String teacherJobNum) {
		bysjglxt_teacher_basic bysjglxt_teacher_basic = new bysjglxt_teacher_basic();
		Session session = getSession();
		String hql = "from bysjglxt_teacher_basic where job_number='" + teacherJobNum + "'";
		Query query = session.createQuery(hql);
		bysjglxt_teacher_basic = (bysjglxt_teacher_basic) query.uniqueResult();
		session.clear();
		return bysjglxt_teacher_basic;
	}

	@Override
	public bysjglxt_college getCollegeById(String id) {
		bysjglxt_college bysjglxt_college = new bysjglxt_college();
		Session session = getSession();
		String hql = "from bysjglxt_college where college_id='" + id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_college = (bysjglxt_college) query.uniqueResult();
		session.clear();
		return bysjglxt_college;
	}

}
