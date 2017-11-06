package com.bysjglxt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.TeacherInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.VO.TeacherInformationManagementVO;

import util.TeamUtil;

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
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete from bysjglxt_teacher_user where user_teacher_id='" + user_teacher_id + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<bysjglxt_teacher_basic> listTeacherAllBasicInformationByAndSearch(
			TeacherInformationManagementVO teacherInformationManagementVO) {
		Session session = getSession();
		boolean flag = false;
		String hql = "from bysjglxt_teacher_basic where 1=1";

		// 判断搜索框中的字符串是不是全是数字
		if (TeamUtil.isDigit(teacherInformationManagementVO.getSearch())) {
			flag = true;
		}
		if (!flag && teacherInformationManagementVO.getSearch() != null
				&& teacherInformationManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + teacherInformationManagementVO.getSearch().trim() + "%";
			hql = hql + " and name like '" + search + "'";

		}
		if (flag && teacherInformationManagementVO.getSearch() != null
				&& teacherInformationManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + teacherInformationManagementVO.getSearch().trim() + "%";
			hql = hql + " and job_number like '" + search + "'";
		}

		if ((teacherInformationManagementVO.getProfessional_title() != null
				&& teacherInformationManagementVO.getProfessional_title().trim().length() > 0)
				|| "".equals(teacherInformationManagementVO.getProfessional_title())) {
			hql = hql + " and professional_title = '" + teacherInformationManagementVO.getProfessional_title().trim()
					+ "'";
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
	public bysjglxt_teacher_user getTeacherInfoByBasicId(String teacher_basic_id) {
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		Session session = getSession();
		String hql = "from bysjglxt_teacher_user where user_teacher_basic='" + teacher_basic_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_teacher_user = (bysjglxt_teacher_user) query.uniqueResult();
		return bysjglxt_teacher_user;
	}

	@Override
	public List<bysjglxt_teacher_basic> listTeacherBasicInformationByPageAndSearch(
			TeacherInformationManagementVO teacherInformationManagementVO) {
		Session session = getSession();
		boolean flag = false;
		String hql = "select distinct(basic) from bysjglxt_teacher_basic basic,bysjglxt_teacher_user teacher_user where basic.teacher_basic_id=teacher_user.user_teacher_basic";
		// 判断搜索框中的字符串是不是全是数字
		if (TeamUtil.isDigit(teacherInformationManagementVO.getSearch())) {
			flag = true;
		}
		if (!flag && teacherInformationManagementVO.getSearch() != null
				&& teacherInformationManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + teacherInformationManagementVO.getSearch().trim() + "%";
			hql = hql + " and basic.name like '" + search + "'";

		}
		if (flag && teacherInformationManagementVO.getSearch() != null
				&& teacherInformationManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + teacherInformationManagementVO.getSearch() + "%";
			hql = hql + " and basic.job_number like '" + search + "'";
		}

		if ((teacherInformationManagementVO.getProfessional_title() != null
				&& teacherInformationManagementVO.getProfessional_title().trim().length() > 0)
				|| "".equals(teacherInformationManagementVO.getProfessional_title())) {
			hql = hql + " and basic.professional_title = '"
					+ teacherInformationManagementVO.getProfessional_title().trim() + "'";
		}
		if (teacherInformationManagementVO.getSex() != null
				&& teacherInformationManagementVO.getSex().trim().length() > 0) {
			hql = hql + " and basic.sex='" + teacherInformationManagementVO.getSex() + "'";
		}
		if ((teacherInformationManagementVO.getSection() != null
				&& teacherInformationManagementVO.getSection().trim().length() > 0)
				|| "".equals(teacherInformationManagementVO.getSection())) {
			hql = hql + " and teacher_user.user_teacher_section = '"
					+ teacherInformationManagementVO.getSection().trim() + "'";
		}
		hql = hql + " order by job_number";
		Query query = session.createQuery(hql);
		query.setFirstResult(
				(teacherInformationManagementVO.getPageIndex() - 1) * teacherInformationManagementVO.getPageSize());
		query.setMaxResults(teacherInformationManagementVO.getPageSize());
		List<bysjglxt_teacher_basic> listTeacherBasicInformationByPageAndSearch = query.list();

		if (!flag && teacherInformationManagementVO.getSearch() != null
				&& teacherInformationManagementVO.getSearch().trim().length() > 0) {
			for (bysjglxt_teacher_basic bysjglxt_teacher_basic : listTeacherBasicInformationByPageAndSearch) {
				bysjglxt_teacher_basic.setName(bysjglxt_teacher_basic.getName()
						.replaceAll(teacherInformationManagementVO.getSearch().trim(), "<span style='color: #ff5063;'>"
								+ teacherInformationManagementVO.getSearch().trim() + "</span>"));
			}
		}
		if (flag && teacherInformationManagementVO.getSearch() != null
				&& teacherInformationManagementVO.getSearch().trim().length() > 0) {
			for (bysjglxt_teacher_basic bysjglxt_teacher_basic : listTeacherBasicInformationByPageAndSearch) {
				bysjglxt_teacher_basic.setJob_number(bysjglxt_teacher_basic.getJob_number()
						.replaceAll(teacherInformationManagementVO.getSearch().trim(), "<span style='color: #ff5063;'>"
								+ teacherInformationManagementVO.getSearch().trim() + "</span>"));
			}
		}
		session.clear();
		return listTeacherBasicInformationByPageAndSearch;
	}

	@Override
	public bysjglxt_section get_TeacherSectionInformation_ByUserSectionId(String user_teacher_section) {
		Session session = getSession();
		bysjglxt_section bysjglxt_section = new bysjglxt_section();
		String hql = "from bysjglxt_section where section_id ='" + user_teacher_section + "'";
		Query query = session.createQuery(hql);
		bysjglxt_section = (bysjglxt_section) query.uniqueResult();
		return bysjglxt_section;
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
	public boolean updateBasic(bysjglxt_teacher_basic bysjglxtTeacherBasic) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.saveOrUpdate(bysjglxtTeacherBasic);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateUser(bysjglxt_teacher_user bysjglxtTeacherUser) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.saveOrUpdate(bysjglxtTeacherUser);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<String> list_Teacher_Title() {
		Session session = getSession();
		List<String> listTeacher_Title = new ArrayList<String>();
		String hql = "select distinct(professional_title) from bysjglxt_teacher_basic";
		Query query = session.createQuery(hql);
		listTeacher_Title = query.list();
		return listTeacher_Title;
	}

	@Override
	public List<bysjglxt_teacher_basic> getResultBySearch(
			TeacherInformationManagementVO teacherInformationManagementVO) {
		Session session = getSession();
		String hql = "select distinct(basic) from bysjglxt_teacher_basic basic,bysjglxt_teacher_user teacher_user where basic.teacher_basic_id=teacher_user.user_teacher_basic";
		boolean flag = false;
		// 判断搜索框中的字符串是不是全是数字
		if (TeamUtil.isDigit(teacherInformationManagementVO.getSearch())) {
			flag = true;
		}
		if (!flag && teacherInformationManagementVO.getSearch() != null
				&& teacherInformationManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + teacherInformationManagementVO.getSearch().trim() + "%";
			hql = hql + " and basic.name like '" + search + "'";

		}
		if (flag && teacherInformationManagementVO.getSearch() != null
				&& teacherInformationManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + teacherInformationManagementVO.getSearch() + "%";
			hql = hql + " and basic.job_number like '" + search + "'";
		}

		if ((teacherInformationManagementVO.getProfessional_title() != null
				&& teacherInformationManagementVO.getProfessional_title().trim().length() > 0)
				|| "".equals(teacherInformationManagementVO.getProfessional_title())) {
			hql = hql + " and basic.professional_title = '"
					+ teacherInformationManagementVO.getProfessional_title().trim() + "'";

		}
		if (teacherInformationManagementVO.getSex() != null
				&& teacherInformationManagementVO.getSex().trim().length() > 0) {
			hql = hql + " and basic.sex='" + teacherInformationManagementVO.getSex() + "'";
		}
		if ((teacherInformationManagementVO.getSection() != null
				&& teacherInformationManagementVO.getSection().trim().length() > 0)
				|| "".equals(teacherInformationManagementVO.getSection())) {
			hql = hql + " and teacher_user.user_teacher_section = '"
					+ teacherInformationManagementVO.getSection().trim() + "'";
		}
		hql = hql + " order by basic.job_number";
		Query query = session.createQuery(hql);
		List<bysjglxt_teacher_basic> listTeacherAllBasicInformationByAndSearch = query.list();
		session.clear();
		return listTeacherAllBasicInformationByAndSearch;
	}

	@Override
	public boolean updatePassword(String user_teacher_id, String password, String moTime) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_teacher_user set user_teacher_password = '" + password
					+ "',user_teacher_gmt_modified='" + moTime + "' where user_teacger_id='" + user_teacher_id + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean teacherBasicIsExist(String job_number) {
		Session session = getSession();
		bysjglxt_teacher_basic bysjglxt_teacher_basic = new bysjglxt_teacher_basic();
		String hql = "from bysjglxt_teacher_basic where job_number='" + job_number + "'";
		Query query = session.createQuery(hql);
		bysjglxt_teacher_basic = (bysjglxt_teacher_basic) query.uniqueResult();
		if (bysjglxt_teacher_basic != null) {
			return true;
		} else {
			return false;
		}
	}
}
