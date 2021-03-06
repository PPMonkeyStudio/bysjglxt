package com.bysjglxt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.StudentInformationManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_defence;
import com.bysjglxt.domain.DO.bysjglxt_dissertation;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_major;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;
import com.bysjglxt.domain.DO.bysjglxt_taskbook;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.DTO.ExportGeaduationStudentDTO;
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
			StudentInformationManagementVO studentInformationManagementVO, String college) {
		Session session = getSession();
		String hql = "select distinct(basic) from bysjglxt_student_basic basic,bysjglxt_student_user student_user where basic.student_basic_id=student_user.user_student_basic and student_user.user_student_belong_college='"
				+ college + "' ";
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
		if ((studentInformationManagementVO.getStudent_basic_level() != null
				&& studentInformationManagementVO.getStudent_basic_level().trim().length() > 0)
				|| "".equals(studentInformationManagementVO.getStudent_basic_level())) {
			hql = hql + " and basic.student_basic_level='"
					+ studentInformationManagementVO.getStudent_basic_level().trim() + "'";
		}
		if (studentInformationManagementVO.getUser_student_is_operate_premission() != -1) {
			hql = hql + "and student_user.user_student_is_operate_premission='"
					+ studentInformationManagementVO.getUser_student_is_operate_premission() + "'";
		}
		if (studentInformationManagementVO.getUser_student_is_select_topic() != -1) {
			hql = hql + " and student_user.user_student_is_select_topic = '"
					+ studentInformationManagementVO.getUser_student_is_select_topic() + "'";
		}
		hql = hql + " order by basic.student_basic_num";
		Query query = session.createQuery(hql);
		query.setFirstResult(
				(studentInformationManagementVO.getPageIndex() - 1) * studentInformationManagementVO.getPageSize());
		query.setMaxResults(studentInformationManagementVO.getPageSize());
		List<bysjglxt_student_basic> listStudentBasicInformationByPageAndSearch = query.list();
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
	public List<bysjglxt_major> listStudent_Major(String college) throws Exception {
		Session session = getSession();
		List<bysjglxt_major> listStudent_Major = new ArrayList<bysjglxt_major>();
		String hql = "select major from bysjglxt_major major,bysjglxt_section section where major.major_belong_section=section.section_id and section_college_id='"
				+ college + "'";
		Query query = session.createQuery(hql);
		listStudent_Major = query.list();
		return listStudent_Major;
	}

	@Override
	public List<String> listStudent_Level() {
		Session session = getSession();
		List<String> listStudent_Level = new ArrayList<String>();
		String hql = "select distinct(student_basic_level) from bysjglxt_student_basic";
		Query query = session.createQuery(hql);
		listStudent_Level = query.list();
		return listStudent_Level;
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
			String hql = "update bysjglxt_student_user set user_student_is_operate_premission=2,user_student_gmt_modified='"
					+ moTime + "' where user_student_id='" + string + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
			session.flush();
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
	public List<bysjglxt_student_basic> getResultBySearch(StudentInformationManagementVO studentInformationManagementVO,
			String college) {
		Session session = getSession();
		String hql = "select basic from bysjglxt_student_basic basic,bysjglxt_student_user student_user where basic.student_basic_id=student_user.user_student_basic and student_user.user_student_belong_college='"
				+ college + "'";
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
		if ((studentInformationManagementVO.getStudent_basic_level() != null
				&& studentInformationManagementVO.getStudent_basic_level().trim().length() > 0)
				|| "".equals(studentInformationManagementVO.getStudent_basic_level())) {
			hql = hql + " and basic.student_basic_level='"
					+ studentInformationManagementVO.getStudent_basic_level().trim() + "'";
		}
		if (studentInformationManagementVO.getUser_student_is_operate_premission() != -1) {
			hql = hql + " and student_user.user_student_is_operate_premission='"
					+ studentInformationManagementVO.getUser_student_is_operate_premission() + "'";
		}
		if (studentInformationManagementVO.getUser_student_is_select_topic() != -1) {
			hql = hql + " and student_user.user_student_is_select_topic = '"
					+ studentInformationManagementVO.getUser_student_is_select_topic() + "'";
		}
		hql = hql + " order by basic.student_basic_num";
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

	@Override
	public bysjglxt_topic_select getTopicSelect(String user_student_id) {
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		Session session = getSession();
		String hql = "from bysjglxt_topic_select where topic_select_student = '" + user_student_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_topic_select = (bysjglxt_topic_select) query.uniqueResult();
		return bysjglxt_topic_select;
	}

	// 删除选题
	@Override
	public boolean deleteTopicSelect(String topic_select_id) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete from bysjglxt_topic_select where topic_select_id='" + topic_select_id + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public bysjglxt_taskbook getTaskBookByStudent(String user_student_id) {
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		Session session = getSession();
		String hql = "from bysjglxt_taskbook where bysjglxt_taskbook_student = '" + user_student_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_taskbook = (bysjglxt_taskbook) query.uniqueResult();
		return bysjglxt_taskbook;
	}

	@Override
	public boolean deleteTaskBookStudent(String taskbook_id) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete from bysjglxt_taskbook where taskbook_id='" + taskbook_id + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public bysjglxt_report_opening getReportOpening(String topic_select_student) {
		bysjglxt_report_opening bysjglxt_report_opening = new bysjglxt_report_opening();
		Session session = getSession();
		String hql = "from bysjglxt_report_opening where report_opening_student = '" + topic_select_student + "'";
		Query query = session.createQuery(hql);
		bysjglxt_report_opening = (bysjglxt_report_opening) query.uniqueResult();
		return bysjglxt_report_opening;
	}

	@Override
	public boolean deleteReportOpening(String topic_select_student) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete from bysjglxt_report_opening where report_opening_id='" + topic_select_student + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<bysjglxt_record_progress> getProgress(String topic_select_student) {
		List<bysjglxt_record_progress> listProgress = new ArrayList<bysjglxt_record_progress>();
		Session session = getSession();
		String hql = "from bysjglxt_record_progress where record_progress_student ='" + topic_select_student + "'";
		Query query = session.createQuery(hql);
		listProgress = query.list();
		return listProgress;
	}

	@Override
	public boolean deleteProgress(String record_progress_id) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete from bysjglxt_record_progress where record_progress_id='" + record_progress_id + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public bysjglxt_summary getSummary(String topic_select_student) {
		bysjglxt_summary bysjglxt_summary = new bysjglxt_summary();
		Session session = getSession();
		String hql = "from bysjglxt_summary where summary_student = '" + topic_select_student + "'";
		Query query = session.createQuery(hql);
		bysjglxt_summary = (bysjglxt_summary) query.uniqueResult();
		return bysjglxt_summary;
	}

	@Override
	public boolean deleteSummary(String summary_id) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete from bysjglxt_summary where summary_id='" + summary_id + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public bysjglxt_examination_formal getExaminationFormal(String topic_select_student) {
		bysjglxt_examination_formal bysjglxt_examination_formal = new bysjglxt_examination_formal();
		Session session = getSession();
		String hql = "from bysjglxt_examination_formal where examination_formal_student = '" + topic_select_student
				+ "'";
		Query query = session.createQuery(hql);
		bysjglxt_examination_formal = (bysjglxt_examination_formal) query.uniqueResult();
		return bysjglxt_examination_formal;
	}

	@Override
	public boolean deleteExaminationFormal(String examination_formal_id) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete from bysjglxt_examination_formal where examination_formal_id='" + examination_formal_id
					+ "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public bysjglxt_evaluate_tutor getEvaluateTutor(String topic_select_student) {
		bysjglxt_evaluate_tutor bysjglxt_evaluate_tutor = new bysjglxt_evaluate_tutor();
		Session session = getSession();
		String hql = "from bysjglxt_evaluate_tutor where evaluate_tutor_student = '" + topic_select_student + "'";
		Query query = session.createQuery(hql);
		bysjglxt_evaluate_tutor = (bysjglxt_evaluate_tutor) query.uniqueResult();
		return bysjglxt_evaluate_tutor;
	}

	@Override
	public boolean deleteEvaluateTutor(String evaluate_tutor_id) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete from bysjglxt_evaluate_tutor where evaluate_tutor_id='" + evaluate_tutor_id + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public bysjglxt_evaluate_review getEvaluateReview(String topic_select_student) {
		bysjglxt_evaluate_review bysjglxt_evaluate_review = new bysjglxt_evaluate_review();
		Session session = getSession();
		String hql = "from bysjglxt_evaluate_review where evaluate_review_student = '" + topic_select_student + "'";
		Query query = session.createQuery(hql);
		bysjglxt_evaluate_review = (bysjglxt_evaluate_review) query.uniqueResult();
		return bysjglxt_evaluate_review;
	}

	@Override
	public boolean deleteEvaluateReview(String evaluate_review_id) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete from bysjglxt_evaluate_review where evaluate_review_id='" + evaluate_review_id + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public bysjglxt_defence getDefence(String user_student_id) {
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		Session session = getSession();
		String hql = "from bysjglxt_defence where defence_student = '" + user_student_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_defence = (bysjglxt_defence) query.uniqueResult();
		return bysjglxt_defence;
	}

	@Override
	public boolean deleteDefence(String defence_id) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete from bysjglxt_defence where defence_id='" + defence_id + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<bysjglxt_student_user> getListStudentByNotClose(String college) {
		List<bysjglxt_student_user> listStudentByNotClose = new ArrayList<bysjglxt_student_user>();
		Session session = getSession();
		String hql = "from bysjglxt_student_user where user_student_is_operate_premission ='1' and user_student_belong_college='"
				+ college + "' ";
		Query query = session.createQuery(hql);
		listStudentByNotClose = query.list();
		session.clear();
		return listStudentByNotClose;
	}

	@Override
	public List<bysjglxt_student_user> getListStudentByExport(ExportGeaduationStudentDTO exportGeaduationStudentDTO,
			String college) {
		List<bysjglxt_student_user> listUser = new ArrayList<>();
		Session session = getSession();
		String hql = "select usr from bysjglxt_student_basic basic, bysjglxt_process_definition definition, bysjglxt_student_user usr,bysjglxt_process_instance instance where basic.student_basic_id=usr.user_student_basic and instance.process_instance_process_definition=definition.process_definition_id and instance.process_instance_man=usr.user_student_id and definition.process_definition_name='毕业设计流程' and usr.user_student_belong_college='"
				+ college + "'";
		if (exportGeaduationStudentDTO.getMajor() != null && !("-1".equals(exportGeaduationStudentDTO.getMajor()))) {
			hql = hql + " and basic.student_basic_major='" + exportGeaduationStudentDTO.getMajor().trim() + "'";
		}
		if (exportGeaduationStudentDTO.getLevel() != null && !("-1".equals(exportGeaduationStudentDTO.getLevel()))) {
			hql = hql + " and basic.student_basic_level='" + exportGeaduationStudentDTO.getLevel().trim() + "'";
		}
		Query query = session.createQuery(hql);
		listUser = query.list();
		session.clear();
		return listUser;
	}

	@Override
	public bysjglxt_teacher_user getTeacherUserById(String userId) {
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		Session session = getSession();
		String hql = "from bysjglxt_teacher_user where user_teacher_id = '" + userId
				+ "' and user_teacher_is_college_admin=1";
		Query query = session.createQuery(hql);
		bysjglxt_teacher_user = (bysjglxt_teacher_user) query.uniqueResult();
		return bysjglxt_teacher_user;
	}

	@Override
	public bysjglxt_major getMajorByMajorId(String majorCode) {
		bysjglxt_major bysjglxt_major = new bysjglxt_major();
		Session session = getSession();
		String hql = "from bysjglxt_major where major_id = '" + majorCode + "'";
		Query query = session.createQuery(hql);
		bysjglxt_major = (bysjglxt_major) query.uniqueResult();
		return bysjglxt_major;
	}
	
	@Override
	public bysjglxt_major getMajorByCode(String code) {
		bysjglxt_major bysjglxt_major = new bysjglxt_major();
		Session session = getSession();
		String hql = "from bysjglxt_major where major_professionalcode = '" + code + "'";
		Query query = session.createQuery(hql);
		bysjglxt_major = (bysjglxt_major) query.uniqueResult();
		return bysjglxt_major;
	}

	@Override
	public boolean saveObject(Object obj) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.saveOrUpdate(obj);
			session.flush();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	// 遍历出某个学生的任务实例
	@Override
	public List<bysjglxt_task_instance> getTaskInstanceByProcessManId(String user_student_id) {
		List<bysjglxt_task_instance> listTaskInstance = new ArrayList<>();
		Session session = getSession();
		String hql = "select taskInstance from bysjglxt_task_instance taskInstance,bysjglxt_process_instance processInstance where taskInstance.task_instance_process_instance=processInstance.process_instance_id and processInstance.process_instance_man='"
				+ user_student_id + "'";
		Query query = session.createQuery(hql);
		listTaskInstance = query.list();
		session.clear();
		return listTaskInstance;
	}

	// 删除某个任务实例
	@Override
	public boolean deleteTaskInstanceById(String task_instance_id) {
		Session session = getSession();
		String hql = "delete from bysjglxt_task_instance where task_instance_id='" + task_instance_id + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}

	@Override
	public List<bysjglxt_process_instance> getProcessInstanceByMan(String user_student_id) {
		List<bysjglxt_process_instance> listProcessInstance = new ArrayList<>();
		Session session = getSession();
		String hql = "from bysjglxt_process_instance where process_instance_man='" + user_student_id + "'";
		Query query = session.createQuery(hql);
		listProcessInstance = query.list();
		session.clear();
		return listProcessInstance;
	}

	@Override
	public boolean deleteProcessById(String process_instance_id) {
		Session session = getSession();
		String hql = "delete from bysjglxt_process_instance where process_instance_id='" + process_instance_id + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}

	// 获取毕业论文
	@Override
	public bysjglxt_dissertation getDissertationByUserId(String user_student_id) {
		bysjglxt_dissertation bysjglxt_dissertation = new bysjglxt_dissertation();
		Session session = getSession();
		String hql = "from bysjglxt_dissertation where dissertation_student = '" + user_student_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_dissertation = (bysjglxt_dissertation) query.uniqueResult();
		return bysjglxt_dissertation;
	}

	@Override
	public boolean deleteDissertationById(String dissertation_id) {
		Session session = getSession();
		String hql = "delete from bysjglxt_dissertation where dissertation_id='" + dissertation_id + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}


}
