package com.bysjglxt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.SectionInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.VO.SectionInformationManagementVO;

public class SectionInformationManagementDaoImpl implements SectionInformationManagementDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public int getCountSectionLeader() {
		Session session = getSession();
		String hql = "select count(*) from bysjglxt_section where 1=1";
		Query query = session.createQuery(hql);
		int count = ((Number) query.uniqueResult()).intValue();
		return count;
	}

	@Override
	public List<bysjglxt_section> getListSectionByPage(SectionInformationManagementVO sectionInformationManagementVO,
			String college) {
		Session session = getSession();
		String hql = "from bysjglxt_section where section_college_id='" + college + "' order by section_gmt_create";
		Query query = session.createQuery(hql);
		query.setFirstResult(
				(sectionInformationManagementVO.getPageIndex() - 1) * sectionInformationManagementVO.getPageSize());
		query.setMaxResults(sectionInformationManagementVO.getPageSize());
		List<bysjglxt_section> listSectionByPage = query.list();
		session.clear();
		return listSectionByPage;
	}

	@Override
	public bysjglxt_teacher_user getBysjglxtTeacherUserById(String section_id) {
		Session session = getSession();
		bysjglxt_teacher_user teacherUserInformation = null;
		String hql = "from bysjglxt_teacher_user where user_teacher_id='" + section_id + "'";
		Query query = session.createQuery(hql);
		teacherUserInformation = (bysjglxt_teacher_user) query.uniqueResult();
		return teacherUserInformation;

	}

	@Override
	public bysjglxt_teacher_basic getBysjglxtTeacherBasicById(String user_teacher_basic) {
		Session session = getSession();
		bysjglxt_teacher_basic TeacherBasicInformation = null;
		String hql = "from bysjglxt_teacher_basic where teacher_basic_id='" + user_teacher_basic + "'";
		Query query = session.createQuery(hql);
		TeacherBasicInformation = (bysjglxt_teacher_basic) query.uniqueResult();
		return TeacherBasicInformation;
	}

	@Override
	public boolean create_Section(bysjglxt_section newSection) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.saveOrUpdate(newSection);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteSection(String string) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete from bysjglxt_section where section_id='" + string + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<bysjglxt_section> listBysjglxtSection() {
		Session session = getSession();
		List<bysjglxt_section> listSection = new ArrayList<bysjglxt_section>();
		String hql = "from bysjglxt_section";
		Query query = session.createQuery(hql);
		listSection = query.list();
		return listSection;
	}

	@Override
	public boolean updateSection(bysjglxt_section bysjglxt_section) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.saveOrUpdate(bysjglxt_section);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean setTeacherUserSectionNull(String string) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_teacher_user set user_teacher_section = '' where user_teacher_section = '"
					+ string + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
}
