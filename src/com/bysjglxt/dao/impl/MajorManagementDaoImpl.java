package com.bysjglxt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.MajorManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_major;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.VO.MajorVO;

public class MajorManagementDaoImpl implements MajorManagementDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	// 添加对象
	@Override
	public int addObject(Object obj) {
		int flag = 1;
		try {
			Session session = getSession();
			session.saveOrUpdate(obj);
			session.flush();
		} catch (Exception e) {
			flag = -2;
			e.printStackTrace();
		}
		return flag;
	}

	// 获取学院所有专业
	@Override
	public List<bysjglxt_major> getAllMajorCount(MajorVO majorVo, String userId) {
		Session session = getSession();
		List<bysjglxt_major> listMajor = new ArrayList<>();
		String hql = "select major from bysjglxt_teacher_user teacherUser,bysjglxt_college college,bysjglxt_section section,bysjglxt_major major where teacherUser.user_teacher_belong_college=college.college_id and section.section_college_id=college.college_id and major.major_belong_section=section.section_id and teacherUser.user_teacher_id='"
				+ userId + "'";
		Query query = session.createQuery(hql);
		listMajor = query.list();
		return listMajor;
	}

	// 获取分页学院专业
	@Override
	public List<bysjglxt_major> getAllMajorByPage(MajorVO majorVo, String userId) {
		Session session = getSession();
		List<bysjglxt_major> listMajor = new ArrayList<>();
		String hql = "select major from bysjglxt_teacher_user teacherUser,bysjglxt_college college,bysjglxt_section section,bysjglxt_major major where teacherUser.user_teacher_belong_college=college.college_id and section.section_college_id=college.college_id and major.major_belong_section=section.section_id and teacherUser.user_teacher_id='"
				+ userId + "'";
		Query query = session.createQuery(hql);
		query.setFirstResult((majorVo.getPageIndex() - 1) * majorVo.getPageSize());
		query.setMaxResults(majorVo.getPageSize());
		listMajor = query.list();
		return listMajor;
	}

	// 根据教研室Id获取教研室信息
	@Override
	public bysjglxt_section getSectionById(String sectionId) {
		bysjglxt_section section = new bysjglxt_section();
		String hql = "from bysjglxt_section where section_id='" + sectionId + "'";
		Query query = getSession().createQuery(hql);
		section = (bysjglxt_section) query.uniqueResult();
		return section;
	}

	// 根据专业名称进行查询
	@Override
	public bysjglxt_major getMajorByName(String majorName) {
		bysjglxt_major major = new bysjglxt_major();
		String hql = "from bysjglxt_major where major_name='" + majorName + "'";
		Query query = getSession().createQuery(hql);
		major = (bysjglxt_major) query.uniqueResult();
		return major;
	}

	// 根据专业id进行查询
	@Override
	public bysjglxt_major getMajorById(String majorId) {
		bysjglxt_major major = new bysjglxt_major();
		String hql = "from bysjglxt_major where major_id='" + majorId + "'";
		Query query = getSession().createQuery(hql);
		major = (bysjglxt_major) query.uniqueResult();
		
		return major;
	}

	// 根据专业代码进行查询
	@Override
	public bysjglxt_major getMajorByProfrssional(String majorProfessional) {
		bysjglxt_major major = new bysjglxt_major();
		String hql = "from bysjglxt_major where major_professionalcode='" + majorProfessional + "'";
		Query query = getSession().createQuery(hql);
		major = (bysjglxt_major) query.uniqueResult();
		return major;
	}

	// 根据ID删除教研室
	@Override
	public int deleteSectionById(String section_id) {
		Session session = getSession();
		int i = 1;
		String hql = "delete from bysjglxt_section where section_id='" + section_id + "'";
		try {
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (Exception e) {
			i = -1;
		}
		return i;

	}

	// 获取教研室列表
	@Override
	public List<bysjglxt_section> listBysjglxtSection(String userId) {
		Session session = getSession();
		List<bysjglxt_section> listSection = new ArrayList<bysjglxt_section>();
		String hql = "select section from bysjglxt_section section,bysjglxt_teacher_user teacherUser,bysjglxt_college college where teacherUser.user_teacher_belong_college=college.college_id and college.college_id=section.section_college_id and teacherUser.user_teacher_id='"
				+ userId + "'";
		Query query = session.createQuery(hql);
		listSection = query.list();
		return listSection;
	}

	// 根据教师ID获取教师user信息
	@Override
	public bysjglxt_teacher_user getTeacherUserById(String section_leader) {
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		Session session = getSession();
		String hql = "from bysjglxt_teacher_user where user_teacher_id='" + section_leader + "'";
		Query query = session.createQuery(hql);
		bysjglxt_teacher_user = (bysjglxt_teacher_user) query.uniqueResult();
		return bysjglxt_teacher_user;
	}

	// 根据basicId获取教师基础信息
	@Override
	public bysjglxt_teacher_basic getTeacherBasicById(String basicId) {
		bysjglxt_teacher_basic bysjglxt_teacher_basic = new bysjglxt_teacher_basic();
		Session session = getSession();
		String hql = "from bysjglxt_teacher_basic where teacher_basic_id='" + basicId + "'";
		Query query = session.createQuery(hql);
		bysjglxt_teacher_basic = (bysjglxt_teacher_basic) query.uniqueResult();
		return bysjglxt_teacher_basic;
	}

}
