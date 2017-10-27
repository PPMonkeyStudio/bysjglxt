package com.bysjglxt.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.StudentInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.VO.StudentInformationManagementVO;

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
		return StudentBasicInformation;
	}

	@Override
	public boolean saveStudentBasic(bysjglxt_student_basic bysjglxt_student_basic) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.save(bysjglxt_student_basic);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean saveStudent(bysjglxt_student_user bysjglxt_student_user) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.save(bysjglxt_student_user);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public bysjglxt_student_user getStudentByNum(String user_student_id) {
		Session session = getSession();
		bysjglxt_student_user StudentInformation = null;
		String hql = "from bysjglxt_student_user where user_student_id='" + user_student_id + "'";
		Query query = session.createQuery(hql);
		StudentInformation = (bysjglxt_student_user) query.uniqueResult();
		return StudentInformation;
	}

	@Override
	public boolean deleteStudentBasicInfoById(String user_student_basic) {
		Session session = getSession();
		String hql = "delete from bysjglxt_student_basic where student_basic_id='" + user_student_basic + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		System.out.println(hql);
		return true;
	}

	@Override
	public boolean deleteStudentInfoById(String user_student_id) {
		Session session = getSession();
		String hql = "delete from bysjglxt_student_user where user_student_id='" + user_student_id + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}

	@Override
	public List<bysjglxt_student_basic> listStudentBasicInformationByPageAndSearch(
			StudentInformationManagementVO studentInformationManagementVO) {
		Session session = getSession();
		String hql = "from bysjglxt_student_basic where 1=1";
		if (studentInformationManagementVO.getSearch() != null
				&& studentInformationManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + studentInformationManagementVO.getSearch() + "%";
			hql = hql + " and student_basic_name like '" + search + "'";
			System.out.println(hql);
		}
		if (studentInformationManagementVO.getSex() != null
				&& studentInformationManagementVO.getSex().trim().length() > 0) {
			hql = hql + " and student_basic_sex ='" + studentInformationManagementVO.getSex() + "'";
		}
		if (studentInformationManagementVO.getStudent_basic_major() != null
				&& studentInformationManagementVO.getStudent_basic_major().trim().length() > 0) {
			hql = hql + " and student_basic_major='" + studentInformationManagementVO.getStudent_basic_major() + "'";
		}
		if (studentInformationManagementVO.getStudent_basic_grade() != null
				&& studentInformationManagementVO.getStudent_basic_grade().trim().length() > 0) {
			hql = hql + " and student_basic_grade='" + studentInformationManagementVO.getStudent_basic_grade() + "'";
		}
		hql = hql + " order by student_basic_num";
		Query query = session.createQuery(hql);
		query.setFirstResult(
				(studentInformationManagementVO.getPageIndex() - 1) * studentInformationManagementVO.getPageSize());
		query.setMaxResults(studentInformationManagementVO.getPageSize());
		List<bysjglxt_student_basic> listStudentBasicInformationByPageAndSearch = query.list();
		session.clear();
		if (studentInformationManagementVO.getSearch() != null
				&& studentInformationManagementVO.getSearch().trim().length() > 0) {
			for (bysjglxt_student_basic bysjglxt_student_basic : listStudentBasicInformationByPageAndSearch) {
				bysjglxt_student_basic.setStudent_basic_name(bysjglxt_student_basic.getStudent_basic_name()
						.replaceAll(studentInformationManagementVO.getSearch().trim(), "<span style='color: #ff5063;'>"
								+ studentInformationManagementVO.getSearch().trim() + "</span>"));
			}
		}
		return listStudentBasicInformationByPageAndSearch;
	}

	@Override
	public int get_StudentInfor_TotalRecords_BySearch(String search) {
		Session session = getSession();
		String hql = "select count(*) from bysjglxt_student_basic where 1=1";
		if (search != null && search.trim().length() > 0) {
			search = "%" + search + "%";
			hql = hql + " and student_basic_name like '" + search + "'";
		}
		Query query = session.createQuery(hql);
		int count = ((Number) query.uniqueResult()).intValue();
		return count;
	}

	@Override
	public bysjglxt_student_user getStudentInfoByBasicId(String student_basic_id, int permission) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		Session session = getSession();
		String hql = "from bysjglxt_student_user where user_student_basic='" + student_basic_id + "'";
		if (permission != 0) {
			hql = hql + " and user_student_is_operate_premission='" + permission + "'";
		}
		Query query = session.createQuery(hql);
		bysjglxt_student_user = (bysjglxt_student_user) query.uniqueResult();
		return bysjglxt_student_user;
	}
}
