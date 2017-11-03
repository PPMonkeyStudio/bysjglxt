package com.bysjglxt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.StudentInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.VO.StudentInformationManagementVO;

import util.TeamUtil;

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
			session.saveOrUpdate(bysjglxt_student_basic);
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
			session.saveOrUpdate(bysjglxt_student_user);
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
		String hql = "select distinct(basic) from bysjglxt_student_basic basic,bysjglxt_student_user student_user where basic.student_basic_id=student_user.user_student_basic ";
		boolean flag = false;
		// 判断搜索框中的字符串是不是全是数字
		if (TeamUtil.isDigit(studentInformationManagementVO.getSearch())) {
			flag = true;
		}

		if (!flag && studentInformationManagementVO.getSearch() != null
				&& studentInformationManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + studentInformationManagementVO.getSearch().trim() + "%";
			hql = hql + " and basic.student_basic_name like '" + search + "'";
		}

		if (flag && studentInformationManagementVO.getSearch() != null
				&& studentInformationManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + studentInformationManagementVO.getSearch().trim() + "%";
			hql = hql + " and basic.student_basic_num like '" + search + "'";
		}
		if (studentInformationManagementVO.getSex() != null
				&& studentInformationManagementVO.getSex().trim().length() > 0) {
			hql = hql + " and basic.student_basic_sex ='" + studentInformationManagementVO.getSex() + "'";
		}

		if ((studentInformationManagementVO.getStudent_basic_major() != null
				&& studentInformationManagementVO.getStudent_basic_major().trim().length() > 0)
				|| "".equals(studentInformationManagementVO.getStudent_basic_major())) {
			hql = hql + " and basic.student_basic_major='"
					+ studentInformationManagementVO.getStudent_basic_major().trim() + "'";
		}
		if ((studentInformationManagementVO.getStudent_basic_grade() != null
				&& studentInformationManagementVO.getStudent_basic_grade().trim().length() > 0)
				|| "".equals(studentInformationManagementVO.getStudent_basic_grade())) {
			hql = hql + " and basic.student_basic_grade='"
					+ studentInformationManagementVO.getStudent_basic_grade().trim() + "'";
		}
		if (studentInformationManagementVO.getUser_student_is_operate_premission() != -1) {
			hql = hql + "and student_user.user_student_is_operate_premission='"
					+ studentInformationManagementVO.getUser_student_is_operate_premission() + "'";
		}
		if (studentInformationManagementVO.getUser_student_is_select_topic() != -1) {
			hql = hql + " and student_user.user_student_is_select_topic = '"
					+ studentInformationManagementVO.getUser_student_is_operate_premission() + "'";
		}
		hql = hql + " order by basic.student_basic_num";
		System.out.println(hql);
		Query query = session.createQuery(hql);
		query.setFirstResult(
				(studentInformationManagementVO.getPageIndex() - 1) * studentInformationManagementVO.getPageSize());
		query.setMaxResults(studentInformationManagementVO.getPageSize());
		List<bysjglxt_student_basic> listStudentBasicInformationByPageAndSearch = query.list();
		System.out.println(listStudentBasicInformationByPageAndSearch.size());
		if (!flag && studentInformationManagementVO.getSearch() != null
				&& studentInformationManagementVO.getSearch().trim().length() > 0) {
			for (bysjglxt_student_basic bysjglxt_student_basic : listStudentBasicInformationByPageAndSearch) {
				bysjglxt_student_basic.setStudent_basic_name(bysjglxt_student_basic.getStudent_basic_name()
						.replaceAll(studentInformationManagementVO.getSearch().trim(), "<span style='color: #ff5063;'>"
								+ studentInformationManagementVO.getSearch().trim() + "</span>"));
			}
		}
		if (flag && studentInformationManagementVO.getSearch() != null
				&& studentInformationManagementVO.getSearch().trim().length() > 0) {
			for (bysjglxt_student_basic bysjglxt_student_basic : listStudentBasicInformationByPageAndSearch) {
				bysjglxt_student_basic.setStudent_basic_num(bysjglxt_student_basic.getStudent_basic_num()
						.replaceAll(studentInformationManagementVO.getSearch().trim(), "<span style='color: #ff5063;'>"
								+ studentInformationManagementVO.getSearch().trim() + "</span>"));
			}
		}
		session.clear();
		return listStudentBasicInformationByPageAndSearch;
	}

	/**
	 * 改方法暂时被弃用
	 * 
	 * @param search
	 * @return
	 */
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
	public bysjglxt_student_user getStudentInfoByBasicId(String student_basic_id) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		Session session = getSession();
		String hql = "from bysjglxt_student_user where user_student_basic='" + student_basic_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_student_user = (bysjglxt_student_user) query.uniqueResult();
		return bysjglxt_student_user;
	}

	@Override
	public List<String> listStudent_Major() throws Exception {
		Session session = getSession();
		List<String> listStudent_Major = new ArrayList<String>();
		String hql = "select distinct(student_basic_major) from bysjglxt_student_basic";
		Query query = session.createQuery(hql);
		listStudent_Major = query.list();
		return listStudent_Major;
	}

	@Override
	public List<String> listStudent_Grade() throws Exception {
		Session session = getSession();
		List<String> listStudent_Grade = new ArrayList<String>();
		String hql = "select distinct(student_basic_grade) from bysjglxt_student_basic";
		Query query = session.createQuery(hql);
		listStudent_Grade = query.list();
		return listStudent_Grade;
	}

	@Override
	public boolean update_Give_Student_Operate_Permission(String string, String moTime) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_student_user set user_student_is_operate_premission=1,user_student_gmt_modified='"
					+ moTime + "' where user_student_id='" + string + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}

		return flag;
	}

	@Override
	public boolean update_Take_Student_Operate_Permission(String string, String moTime) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_student_user set user_student_is_operate_premission=0,user_student_gmt_modified='"
					+ moTime + "' where user_student_id='" + string + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}

		return flag;
	}

	@Override
	public boolean update_StudentBasicInfomation(bysjglxt_student_basic bysjglxt_student_basic) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.saveOrUpdate(bysjglxt_student_basic);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}

		return flag;
	}

	@Override
	public List<bysjglxt_student_basic> getResultBySearch(
			StudentInformationManagementVO studentInformationManagementVO) {
		Session session = getSession();
		String hql = "select basic from bysjglxt_student_basic basic,bysjglxt_student_user student_user where basic.student_basic_id=student_user.user_student_basic";
		boolean flag = false;
		// 判断搜索框中的字符串是不是全是数字
		if (TeamUtil.isDigit(studentInformationManagementVO.getSearch())) {
			flag = true;
		}
		if (!flag && studentInformationManagementVO.getSearch() != null
				&& studentInformationManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + studentInformationManagementVO.getSearch().trim() + "%";
			hql = hql + " and basic.student_basic_name like '" + search + "'";
		}
		if (flag && studentInformationManagementVO.getSearch() != null
				&& studentInformationManagementVO.getSearch().trim().length() > 0) {
			String search = "%" + studentInformationManagementVO.getSearch().trim() + "%";
			hql = hql + " and basic.student_basic_num like '" + search + "'";
		}
		if (studentInformationManagementVO.getSex() != null
				&& studentInformationManagementVO.getSex().trim().length() > 0) {
			hql = hql + " and basic.student_basic_sex ='" + studentInformationManagementVO.getSex() + "'";
		}
		if ((studentInformationManagementVO.getStudent_basic_major() != null
				&& studentInformationManagementVO.getStudent_basic_major().trim().length() > 0)
				|| "".equals(studentInformationManagementVO.getStudent_basic_major())) {
			hql = hql + " and basic.student_basic_major='"
					+ studentInformationManagementVO.getStudent_basic_major().trim() + "'";
		}
		if ((studentInformationManagementVO.getStudent_basic_grade() != null
				&& studentInformationManagementVO.getStudent_basic_grade().trim().length() > 0)
				|| "".equals(studentInformationManagementVO.getStudent_basic_grade())) {
			hql = hql + " and basic.student_basic_grade='"
					+ studentInformationManagementVO.getStudent_basic_grade().trim() + "'";
		}
		if (studentInformationManagementVO.getUser_student_is_operate_premission() != -1) {
			hql = hql + " and student_user.user_student_is_operate_premission='"
					+ studentInformationManagementVO.getUser_student_is_operate_premission() + "'";
		}
		if (studentInformationManagementVO.getUser_student_is_select_topic() != -1) {
			hql = hql + " and student_user.user_student_is_select_topic = '"
					+ studentInformationManagementVO.getUser_student_is_operate_premission() + "'";
		}
		hql = hql + " order by basic.student_basic_num";
		System.out.println(hql);
		Query query = session.createQuery(hql);
		List<bysjglxt_student_basic> list_bysjglxt_student_basic = query.list();
		session.clear();
		return list_bysjglxt_student_basic;
	}

	@Override
	public boolean updatePassword(String user_student_id, String password, String moTime) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "update bysjglxt_student_user set user_student_password = '" + password
					+ "',user_student_gmt_modified='" + moTime + "' where user_student_id='" + user_student_id + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public boolean studentBasicIsExist(String student_basic_id) {
		Session session = getSession();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		String hql = "from bysjglxt_student_basic where student_basic_num='" + student_basic_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_student_basic = (bysjglxt_student_basic) query.uniqueResult();
		if (bysjglxt_student_basic != null) {
			return true;
		} else {
			return false;
		}

	}

}
