package com.bysjglxt.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.TeacherInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.VO.TeacherInformationManagementVO;

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
		String hql = "from bysjglxt_teacher_basic where teacher_basic_id='" + user_teacher_basic + "'";
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
	public List<bysjglxt_teacher_basic> listTeacherAllBasicInformationByAndSearch(
			TeacherInformationManagementVO teacherInformationManagementVO) {
		Session session = getSession();
		String hql = "from bysjglxt_teacher_basic where 1=1";
		if (teacherInformationManagementVO.getSearch() != null
				&& teacherInformationManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + teacherInformationManagementVO.getSearch().trim() + "%";
			hql = hql + " and name like '" + search + "'";
		}
		if (teacherInformationManagementVO.getSex() != null
				&& teacherInformationManagementVO.getSex().trim().length() > 0) {
			hql = hql + " and sex='" + teacherInformationManagementVO.getSex() + "'";
		}
		hql = hql + " order by job_number";
		Query query = session.createQuery(hql);
		List<bysjglxt_teacher_basic> listTeacherAllBasicInformationByAndSearch = query.list();
		session.clear();
		return listTeacherAllBasicInformationByAndSearch;
	}

	@Override
	public bysjglxt_teacher_user getTeacherInfoByBasicId(String teacher_basic_id, String section) {
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		Session session = getSession();
		String hql = "from bysjglxt_teacher_user where user_teacher_basic='" + teacher_basic_id + "'";
		if (section != null && section.trim().length() > 0) {
			hql = hql + " and user_teacher_section = '" + section + "'";
		}
		Query query = session.createQuery(hql);
		bysjglxt_teacher_user = (bysjglxt_teacher_user) query.uniqueResult();
		return bysjglxt_teacher_user;
	}

	@Override
	public List<bysjglxt_teacher_basic> listTeacherBasicInformationByPageAndSearch(
			TeacherInformationManagementVO teacherInformationManagementVO) {
		Session session = getSession();
		String hql = "from bysjglxt_teacher_basic where 1=1";
		if (teacherInformationManagementVO.getSearch() != null
				&& teacherInformationManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + teacherInformationManagementVO.getSearch().trim() + "%";
			hql = hql + " and name like '" + search + "'";
		}
		if (teacherInformationManagementVO.getSex() != null
				&& teacherInformationManagementVO.getSex().trim().length() > 0) {
			hql = hql + " and sex='" + teacherInformationManagementVO.getSex() + "'";
		}
		hql = hql + " order by job_number";
		Query query = session.createQuery(hql);
		query.setFirstResult(
				(teacherInformationManagementVO.getPageIndex() - 1) * teacherInformationManagementVO.getPageSize());
		query.setMaxResults(teacherInformationManagementVO.getPageSize());
		List<bysjglxt_teacher_basic> listTeacherBasicInformationByPageAndSearch = query.list();
		session.clear();
		if (teacherInformationManagementVO.getSearch() != null
				&& teacherInformationManagementVO.getSearch().trim().length() > 0) {
			for (bysjglxt_teacher_basic bysjglxt_teacher_basic : listTeacherBasicInformationByPageAndSearch) {
				bysjglxt_teacher_basic.setName(bysjglxt_teacher_basic.getName()
						.replaceAll(teacherInformationManagementVO.getSearch().trim(), "<span style='color: #ff5063;'>"
								+ teacherInformationManagementVO.getSearch().trim() + "</span>"));
			}
		}
		return listTeacherBasicInformationByPageAndSearch;
	}
}
