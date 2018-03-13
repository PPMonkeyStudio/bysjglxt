package com.bysjglxt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.MajorManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_major;
import com.bysjglxt.domain.DO.bysjglxt_section;
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

	// 根据专业代码进行查询
	@Override
	public bysjglxt_major getMajorByProfrssional(String majorProfessional) {
		bysjglxt_major major = new bysjglxt_major();
		String hql = "from bysjglxt_major where major_professionalcode='" + majorProfessional + "'";
		Query query = getSession().createQuery(hql);
		major = (bysjglxt_major) query.uniqueResult();
		return major;
	}

}
