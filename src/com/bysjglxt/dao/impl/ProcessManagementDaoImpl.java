package com.bysjglxt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bysjglxt.dao.ProcessManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_defence;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_leader;
import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;
import com.bysjglxt.domain.DO.bysjglxt_taskbook;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.VO.ProcessManagementVO;

public class ProcessManagementDaoImpl implements ProcessManagementDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public int createProcessDefine(bysjglxt_process_definition selectTopicProcessDefinition) {
		int flag = 1;
		try {
			Session session = getSession();
			session.saveOrUpdate(selectTopicProcessDefinition);
		} catch (Exception e) {
			flag = -1;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int createTaskDefine(bysjglxt_task_definition selectTopicTaskDefine) {
		int flag = 1;
		try {
			Session session = getSession();
			session.saveOrUpdate(selectTopicTaskDefine);
		} catch (Exception e) {
			flag = -1;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<bysjglxt_process_definition> getAllProcessDefinition() {
		List<bysjglxt_process_definition> listProcessDefinition = new ArrayList<bysjglxt_process_definition>();
		Session session = getSession();
		String hql = "from bysjglxt_process_definition";
		Query query = session.createQuery(hql);
		listProcessDefinition = query.list();
		return listProcessDefinition;
	}

	// 根据流程定义Id获得流程定义
	@Override
	public bysjglxt_process_definition getProcessDefinition(String process_definition_id) {
		bysjglxt_process_definition bysjglxt_process_definition = new bysjglxt_process_definition();
		Session session = getSession();
		String hql = "from bysjglxt_process_definition where process_definition_id = '" + process_definition_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_process_definition = (com.bysjglxt.domain.DO.bysjglxt_process_definition) query.uniqueResult();
		return bysjglxt_process_definition;
	}

	// 根据学生UserId获取User表
	@Override
	public bysjglxt_student_user getStudentUser(String operation) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		Session session = getSession();
		String hql = "from bysjglxt_student_user where user_student_id = '" + operation + "'";
		Query query = session.createQuery(hql);
		bysjglxt_student_user = (com.bysjglxt.domain.DO.bysjglxt_student_user) query.uniqueResult();
		return bysjglxt_student_user;
	}

	// 根据领导小组ID获取leader表
	@Override
	public bysjglxt_leader getLeader(String operation) {
		bysjglxt_leader bysjglxt_leader = new bysjglxt_leader();
		Session session = getSession();
		String hql = "from bysjglxt_leader where leader_teacher_id = '" + operation + "'";
		Query query = session.createQuery(hql);
		bysjglxt_leader = (com.bysjglxt.domain.DO.bysjglxt_leader) query.uniqueResult();
		return bysjglxt_leader;
	}

	// 实例化流程实例
	@Override
	public boolean instanceProcess(bysjglxt_process_instance processInstance) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.saveOrUpdate(processInstance);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	// 获得属于该流程定义的所有任务定义
	@Override
	public List<bysjglxt_task_definition> getListBelongProcess(String process_definition_id) {
		Session session = getSession();
		List<bysjglxt_task_definition> bysjglxt_task_definition = new ArrayList<bysjglxt_task_definition>();
		String hql = "from bysjglxt_task_definition where task_definition_process_definition = '"
				+ process_definition_id + "' order by task_definition_gmt_create asc";
		Query query = session.createQuery(hql);
		System.out.println(hql);
		bysjglxt_task_definition = query.list();
		return bysjglxt_task_definition;
	}

	/**
	 * 弃用
	 */
	@Override
	public bysjglxt_task_instance taskInstanceIsExistId(String operation, String task_definition_id) {
		bysjglxt_task_instance bysjglxt_task_instance = new bysjglxt_task_instance();
		Session session = getSession();
		String hql = "from bysjglxt_task_instance where task_instance_role = '" + operation
				+ "' and task_instance_task_definition = '" + task_definition_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_task_instance = (com.bysjglxt.domain.DO.bysjglxt_task_instance) query.uniqueResult();
		return bysjglxt_task_instance;
	}

	@Override
	public boolean instanceTask(bysjglxt_task_instance taskInstance) {
		boolean flag = true;
		try {
			Session session = getSession();
			session.saveOrUpdate(taskInstance);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<bysjglxt_task_instance> getListTaskInstanceByPager(ProcessManagementVO processManagementVo,
			String userID) {
		Session session = getSession();
		List<bysjglxt_task_instance> listTaskInstance = new ArrayList<bysjglxt_task_instance>();
		String hql = "select taskInstance from bysjglxt_task_instance taskInstance,bysjglxt_task_definition taskDefinition,bysjglxt_process_definition processDefinition where taskInstance.task_instance_task_definition=taskDefinition.task_definition_id and processDefinition.process_definition_id=taskDefinition.task_definition_process_definition";
		// 筛选我的
		hql = hql + " and task_instance_role='" + userID + "'";
		// 搜索
		if (processManagementVo.getSearch() != null && processManagementVo.getSearch().trim().length() > 0) {
			String search = "%" + processManagementVo.getSearch().trim() + "%";
			hql = hql + " and taskDefinition.task_definition_name like '" + search + "'";
		}
		// 状态
		if (processManagementVo.getState() != 0) {
			hql = hql + " and taskInstance.task_instance_state='" + processManagementVo.getState() + "'";
		}
		// 据流程实例ID筛选
		if (processManagementVo.getProcessInstance() != null
				&& processManagementVo.getProcessInstance().trim().length() > 0) {
			hql = hql + " and taskInstance.task_instance_process_instance='"
					+ processManagementVo.getProcessInstance().trim() + "'";
		}
		// 根据流程定义ID筛选
		if (processManagementVo.getProcessDefinition() != null
				&& processManagementVo.getProcessDefinition().trim().length() > 0) {
			hql = hql + " and processDefinition.process_definition_id='"
					+ processManagementVo.getProcessDefinition().trim() + "'";
		}
		hql = hql + " order by taskInstance.task_instance_state";
		Query query = session.createQuery(hql);
		query.setFirstResult((processManagementVo.getPageIndex() - 1) * processManagementVo.getPageSize());
		query.setMaxResults(processManagementVo.getPageSize());
		listTaskInstance = query.list();
		session.clear();
		return listTaskInstance;
	}

	// 根据任务定义Id获取任务定义
	@Override
	public bysjglxt_task_definition getTaskDefinition(String task_instance_task_definition) {
		bysjglxt_task_definition bysjglxt_task_definition = new bysjglxt_task_definition();
		Session session = getSession();
		String hql = "from bysjglxt_task_definition where task_definition_id = '" + task_instance_task_definition + "'";
		Query query = session.createQuery(hql);
		bysjglxt_task_definition = (bysjglxt_task_definition) query.uniqueResult();
		return bysjglxt_task_definition;
	}

	// 根据流程实例ID获取流程实例对象
	@Override
	public bysjglxt_process_instance getProcessInstanceById(String task_instance_process_instance) {
		bysjglxt_process_instance bysjglxt_process_instance = new bysjglxt_process_instance();
		Session session = getSession();
		String hql = "from bysjglxt_process_instance where process_instance_id = '" + task_instance_process_instance
				+ "'";
		Query query = session.createQuery(hql);
		bysjglxt_process_instance = (bysjglxt_process_instance) query.uniqueResult();
		return bysjglxt_process_instance;
	}

	@Override
	public List<bysjglxt_task_instance> getAllTaskList(ProcessManagementVO processManagementVo, String userID) {
		Session session = getSession();
		List<bysjglxt_task_instance> listTaskInstance = new ArrayList<bysjglxt_task_instance>();
		String hql = "select taskInstance from bysjglxt_task_instance taskInstance,bysjglxt_task_definition taskDefinition,bysjglxt_process_definition processDefinition where taskInstance.task_instance_task_definition=taskDefinition.task_definition_id and processDefinition.process_definition_id=taskDefinition.task_definition_process_definition";
		// 筛选我的
		hql = hql + " and task_instance_role='" + userID + "'";
		// 搜索
		if (processManagementVo.getSearch() != null && processManagementVo.getSearch().trim().length() > 0) {
			String search = "%" + processManagementVo.getSearch().trim() + "%";
			hql = hql + " and taskDefinition.task_definition_name like '" + search + "'";
		}
		// 状态
		if (processManagementVo.getState() != 0) {
			hql = hql + " and taskInstance.task_instance_state='" + processManagementVo.getState() + "'";
		}
		// 据流程实例ID筛选
		if (processManagementVo.getProcessInstance() != null
				&& processManagementVo.getProcessInstance().trim().length() > 0) {
			hql = hql + " and taskInstance.task_instance_process_instance='"
					+ processManagementVo.getProcessInstance().trim() + "'";
		}
		// 根据流程定义ID筛选
		if (processManagementVo.getProcessDefinition() != null
				&& processManagementVo.getProcessDefinition().trim().length() > 0) {
			hql = hql + " and processDefinition.process_definition_id='"
					+ processManagementVo.getProcessDefinition().trim() + "'";
		}
		hql = hql + " order by taskInstance.task_instance_state";
		Query query = session.createQuery(hql);
		listTaskInstance = query.list();
		session.clear();
		return listTaskInstance;
	}

	@Override
	public bysjglxt_task_instance getTaskInstanceByProcessInstanceIdAndTaskDefinitionId(String process_instance_id,
			String task_definition_father) {
		bysjglxt_task_instance bysjglxt_task_instance = new bysjglxt_task_instance();
		Session session = getSession();
		String hql = "from bysjglxt_task_instance where task_instance_process_instance = '" + process_instance_id
				+ "' and task_instance_task_definition = '" + task_definition_father + "'";
		System.out.println(hql);
		Query query = session.createQuery(hql);
		bysjglxt_task_instance = (bysjglxt_task_instance) query.uniqueResult();
		return bysjglxt_task_instance;
	}

	@Override
	public bysjglxt_topic_select getStudentSelectTopicByStudentUserID(String operation) {
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		Session session = getSession();
		String hql = "from bysjglxt_topic_select where topic_select_student = '" + operation + "'";
		Query query = session.createQuery(hql);
		bysjglxt_topic_select = (bysjglxt_topic_select) query.uniqueResult();
		return bysjglxt_topic_select;
	}

	@Override
	public bysjglxt_teacher_user getTeacherUserByNum(String topic_select_teacher_tutor) {
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		Session session = getSession();
		String hql = "from bysjglxt_teacher_user where user_teacher_id = '" + topic_select_teacher_tutor + "'";
		Query query = session.createQuery(hql);
		bysjglxt_teacher_user = (bysjglxt_teacher_user) query.uniqueResult();
		return bysjglxt_teacher_user;
	}

	@Override
	public bysjglxt_section getSectionById(String user_section_id) {
		bysjglxt_section bysjglxt_section = new bysjglxt_section();
		Session session = getSession();
		String hql = "from bysjglxt_section where section_id = '" + user_section_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_section = (bysjglxt_section) query.uniqueResult();
		return bysjglxt_section;
	}

	@Override
	public List<bysjglxt_leader> getListLeader() {
		List<bysjglxt_leader> listLeader = new ArrayList<bysjglxt_leader>();
		Session session = getSession();
		String hql = "from bysjglxt_leader";
		Query query = session.createQuery(hql);
		listLeader = query.list();
		return listLeader;
	}

	@Override
	public List<bysjglxt_process_instance> getListProcessInstanceByDefinitionId(String processDefinitionId) {
		List<bysjglxt_process_instance> listProcessInstance = new ArrayList<bysjglxt_process_instance>();
		Session session = getSession();
		String hql = "from bysjglxt_process_instance where process_instance_process_definition = '"
				+ processDefinitionId + "'";
		Query query = session.createQuery(hql);
		listProcessInstance = query.list();
		return listProcessInstance;
	}

	// 根据流程实例ID获取任务实例 没必要
	@Override
	public List<bysjglxt_task_instance> getListTaskInstanceByProcessInstanceId(String process_instance_id) {
		List<bysjglxt_task_instance> listTaskInstance = new ArrayList<bysjglxt_task_instance>();
		Session session = getSession();
		String hql = "from bysjglxt_task_instance where task_instance_process_instance = '" + listTaskInstance + "'";
		Query query = session.createQuery(hql);
		listTaskInstance = query.list();
		return listTaskInstance;
	}

	// 根据流程实例ID删除任务实例
	@Override
	public boolean deleteTaskInstanceByProcessInstance(String process_instance_id) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete bysjglxt_task_instance where task_instance_process_instance ='" + process_instance_id
					+ "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	// 根据流程定义ID删除流程实例
	@Override
	public boolean deleteProcessInstanceByProcessDefinitionId(String processDefinitionId) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete bysjglxt_process_instance where process_instance_process_definition ='"
					+ processDefinitionId + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	// 根据流程定义ID删除任务定义
	@Override
	public boolean deleteTaskDefinitionByProcessDefinitionId(String processDefinitionId) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete bysjglxt_task_definition where task_definition_process_definition ='"
					+ processDefinitionId + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	// 根据流程定义ID删除流程定义
	@Override
	public boolean deleteProcessDefinitionByProcessDefinitionId(String processDefinitionId) {
		boolean flag = true;
		try {
			Session session = getSession();
			String hql = "delete bysjglxt_process_definition where process_definition_id ='" + processDefinitionId
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
	public bysjglxt_process_instance getProcessInstanceByDefinitionAndMan(String process_definition_id,
			String operation) {
		bysjglxt_process_instance bysjglxt_process_instance = new bysjglxt_process_instance();
		Session session = getSession();
		String hql = "from bysjglxt_process_instance where process_instance_process_definition='"
				+ process_definition_id + "' and process_instance_man='" + operation
				+ "' and process_instance_state='活动' ";
		Query query = session.createQuery(hql);
		bysjglxt_process_instance = (bysjglxt_process_instance) query.uniqueResult();
		return bysjglxt_process_instance;
	}

	// 根据角色以及状态得到个人正在进行的任务实例
	@Override
	public bysjglxt_task_instance getTaskInstanceing(String userId) {
		bysjglxt_task_instance bysjglxt_task_instance = new bysjglxt_task_instance();
		Session session = getSession();
		String hql = "from bysjglxt_task_instance where task_instance_role='" + userId + "' and task_instance_state=1 ";
		Query query = session.createQuery(hql);
		bysjglxt_task_instance = (com.bysjglxt.domain.DO.bysjglxt_task_instance) query.uniqueResult();
		return bysjglxt_task_instance;
	}

	/**
	 * 根据任务实例ID获得任务实例
	 */
	@Override
	public bysjglxt_task_instance getTaskInstanceingById(String taskInstanceId) {
		bysjglxt_task_instance bysjglxt_task_instance = new bysjglxt_task_instance();
		Session session = getSession();
		String hql = "from bysjglxt_task_instance where task_instance_id='" + taskInstanceId + "'";
		Query query = session.createQuery(hql);
		bysjglxt_task_instance = (bysjglxt_task_instance) query.uniqueResult();
		return bysjglxt_task_instance;
	}

	@Override
	public bysjglxt_task_instance getTaskInstanceByFatherTaskId(String task_instance_id) {
		bysjglxt_task_instance bysjglxt_task_instance = new bysjglxt_task_instance();
		Session session = getSession();
		String hql = "from bysjglxt_task_instance where task_instance_father='" + task_instance_id + "'";
		Query query = session.createQuery(hql);
		bysjglxt_task_instance = (bysjglxt_task_instance) query.uniqueResult();
		return bysjglxt_task_instance;
	}

}
