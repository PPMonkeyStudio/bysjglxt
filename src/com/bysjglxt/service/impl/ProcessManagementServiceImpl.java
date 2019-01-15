package com.bysjglxt.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.bysjglxt.dao.ProcessManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_college;
import com.bysjglxt.domain.DO.bysjglxt_notice;
import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.DTO.ProcessDTO;
import com.bysjglxt.domain.DTO.ProcessDefinitionDetailDTO;
import com.bysjglxt.domain.DTO.ProcessDetailDTO;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TaskDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.ProcessManagementVO;
import com.bysjglxt.service.GraduationProjectManagementService;
import com.bysjglxt.service.ProcessManagementService;

import util.TeamUtil;

public class ProcessManagementServiceImpl implements ProcessManagementService {
	private ProcessManagementDao processManagementDao;
	private GraduationProjectManagementService graduationProjectManagementService;

	public void setGraduationProjectManagementService(
			GraduationProjectManagementService graduationProjectManagementService) {
		this.graduationProjectManagementService = graduationProjectManagementService;
	}

	public void setProcessManagementDao(ProcessManagementDao processManagementDao) {
		this.processManagementDao = processManagementDao;
	}
	public static Properties properties = new Properties();//properties属性
	static {
		//加载消息文件
		try {
//			properties.load(CollegeManagementServiceImpl.class.getClassLoader().getResourceAsStream("notice.properties"));
			InputStream inputStream = CollegeManagementServiceImpl.class.getClassLoader().getResourceAsStream("notice.properties");
			BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
			properties.load(bf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取所有需要进行选题的学生id
	 * 
	 * @D 可以选题的学生应该满足下面的一些情况：
	 * @D 1.学生已经进行了选题
	 * @D 2.没有开启毕业设计流程
	 */
	public List<String> listOpenGraduationProjectProcessStudentId(String process_definition_id, String college) {
		List<String> listSelectStudent = new ArrayList<String>();
		listSelectStudent = processManagementDao.getListStudentSelect(process_definition_id, college);
		return listSelectStudent;
	}

	/**
	 * 获得正在进行的选题流程实例
	 */
	@Override
	public ProcessDTO getCurrentTaskIng(String userId, int role) {
		// 获取学院
		String college = collegeJudge(role, userId);
		ProcessDTO processDTO = new ProcessDTO();
		bysjglxt_process_instance bysjglxt_process_instance = new bysjglxt_process_instance();
		List<TaskDTO> listTaskBelongProcess = new ArrayList<TaskDTO>();
		TaskDTO taskDTO = new TaskDTO();
		bysjglxt_task_definition taskDefinition = new bysjglxt_task_definition();
		List<bysjglxt_task_instance> listTaskInstance = new ArrayList<bysjglxt_task_instance>();
		bysjglxt_process_definition bysjglxt_process_definition = new bysjglxt_process_definition();
		// 1.根据流程实例状态获得选题流程实例
		bysjglxt_process_instance = processManagementDao.getSelectProcessInstance(college);
		if (bysjglxt_process_instance != null) {
			// 根据流程实例中流程定义Id获取流程定义
			bysjglxt_process_definition = processManagementDao
					.getProcessDefinition(bysjglxt_process_instance.getProcess_instance_process_definition());
			processDTO.setProcessDefinition(bysjglxt_process_definition);
			processDTO.setProcessInstance(bysjglxt_process_instance);
			// 根据流程实例ID获取流程实例
			listTaskInstance = processManagementDao
					.getListTaskInstanceByProcessInstanceId(bysjglxt_process_instance.getProcess_instance_id());
			for (bysjglxt_task_instance bysjglxt_task_instance : listTaskInstance) {
				taskDTO = new TaskDTO();
				taskDefinition = new bysjglxt_task_definition();
				taskDTO.setTaskInstance(bysjglxt_task_instance);
				// 根据任务实例获取任务定义
				taskDefinition = processManagementDao
						.getTaskDefinition(bysjglxt_task_instance.getTask_instance_task_definition());
				if (taskDefinition != null) {
					taskDTO.setTaskDefinition(taskDefinition);
				}
				listTaskBelongProcess.add(taskDTO);
			}
			processDTO.setListTaskBelongProcess(listTaskBelongProcess);
		}

		return processDTO;
	}

	// 创建选题流程定义表
	@Override
	public String createSelectTopicProcessDefine(bysjglxt_process_definition selectTopicProcessDefinition) {
		int flag = -1;
		selectTopicProcessDefinition.setProcess_definition_id(TeamUtil.getUuid());
		selectTopicProcessDefinition.setProcess_definition_gmt_create(TeamUtil.getStringSecond());
		selectTopicProcessDefinition
				.setProcess_definition_gmt_modified(selectTopicProcessDefinition.getProcess_definition_gmt_create());
		flag = processManagementDao.createProcessDefine(selectTopicProcessDefinition);
		if (flag == -1) {
			return "创建失败";
		}
		return selectTopicProcessDefinition.getProcess_definition_id();
	}

	@Override
	public int createSelectTopicTaskDefine(bysjglxt_task_definition selectTopicTaskDefine) {
		int flag = -1;
		List<bysjglxt_task_definition> list_bysjglxt_task_definition = new ArrayList<>();
		list_bysjglxt_task_definition = processManagementDao
				.getTaskDefinitionByProcessDefinitionId(selectTopicTaskDefine.getTask_definition_process_definition());
		selectTopicTaskDefine.setTask_definition_id(TeamUtil.getUuid());
		if (list_bysjglxt_task_definition.size() != 0) {
			selectTopicTaskDefine
					.setTask_definition_father(list_bysjglxt_task_definition.get(0).getTask_definition_id());
		}
		selectTopicTaskDefine.setTask_definition_gmt_create(TeamUtil.getStringSecond());
		selectTopicTaskDefine.setTask_definition_gmt_modified(selectTopicTaskDefine.getTask_definition_gmt_create());
		flag = processManagementDao.createTaskDefine(selectTopicTaskDefine);
		return flag;
	}

	@Override
	public List<bysjglxt_process_definition> listProcessDefinition() {
		List<bysjglxt_process_definition> listProcessDefinition = new ArrayList<bysjglxt_process_definition>();
		listProcessDefinition = processManagementDao.getAllProcessDefinition();
		return listProcessDefinition;
	}

	/**
	 * 开启毕业设计流程
	 * 
	 */
	@Override
	public int openGraduProcess(String teacherUserId) {
		// 获取系部管理员学院
		String college = collegeJudge(1, teacherUserId);
		// 根据学生userId获取学生姓名
		List<String> listGraduProcess = new ArrayList<>();
		String studentName;
		// 根据流程定义名获取流程定义ID
		bysjglxt_process_definition processDefinition = new bysjglxt_process_definition();
		// 根据流程定义
		processDefinition = processManagementDao.getProcessDefinitionByName("毕业设计流程");
		if (processDefinition == null)
			return 2;
		// 获取需要开启流程的学生列表
		listGraduProcess = listOpenGraduationProjectProcessStudentId(processDefinition.getProcess_definition_id(),
				college);
		// 遍历学生依次开启毕业设计流程
		for (String stringId : listGraduProcess) {
			studentName = null;
			int i = graduationProjectManagementService.startGraduationProjectProcess(stringId);
			if (i != 1) {
				return -3;
			}
			studentName = processManagementDao.getStudentNameByUserId(stringId);
			if (studentName == null || studentName.trim().length() <= 0) {
				continue;
			}
			openProcess(processDefinition.getProcess_definition_name() + "——" + studentName,
					processDefinition.getProcess_definition_id(), stringId, 2);
			if (i != 1) {
				return -3;
			}
		}
		if(processDefinition!=null && "毕业设计流程".equals(processDefinition.getProcess_definition_name())) {
			bysjglxt_teacher_user teacherUser = processManagementDao.getTeacherByCollege(college);
			String launch = teacherUser.getUser_teacher_id();
			List<TeacherInformationDTO> listTeacherUser = processManagementDao.getTeacherUserByCollegeId(college);
			List<StudentInformationDTO> listStudentUser = processManagementDao.getStudentUserByCollegeId(college);
			//通知该系部所有拥有教研室老师
			for (TeacherInformationDTO teacherInformationDTO : listTeacherUser) {
				if(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id().equals(launch)) {
					createTeacherNotice(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id(),launch,"cretaeAdminGraduation",2);
					continue;
				}
				createTeacherNotice(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id(),launch,"bootGraduation",4);
				//createTeacherNotice(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id(),launch,"createTeacherXiaTaskBook",2);
			}
			for (StudentInformationDTO studentInformationDTO : listStudentUser) {
				createTeacherNotice(studentInformationDTO.getBysjglxtStudentUser().getUser_student_id(),launch,"bootGraduation",4);
			}
			bysjglxt_notice notice = processManagementDao.getNoticeByBelongContentAndLeiXing(launch, ((String)(properties.get("createGraduationProcess"))),2);
			notice.setNotice_leixing(1);
			notice.setNotice_state(1);
			notice.setNotice_gmt_modified(TeamUtil.getStringSecond());
			processManagementDao.saveObj(notice);
		}
		return 1;
	}

	// 根据user Id以及用户角色身份获取用户是哪个系的
	public String collegeJudge(int studentOrTeacher, String userId) {
		// 老师
		if (studentOrTeacher == 1) {
			bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
			bysjglxt_teacher_user = processManagementDao.getTeacherUserByNum(userId);
			if (bysjglxt_teacher_user == null) {
				return null;
			}
			if (bysjglxt_teacher_user.getUser_teacher_belong_college() != null
					&& bysjglxt_teacher_user.getUser_teacher_belong_college().trim().length() > 0) {
				return bysjglxt_teacher_user.getUser_teacher_belong_college();
			}
		} else {
			// 学生
			bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
			bysjglxt_student_user = processManagementDao.getStudentUser(userId);
			if (bysjglxt_student_user == null) {
				return null;
			}
			if (bysjglxt_student_user.getUser_student_belong_college() != null
					&& bysjglxt_student_user.getUser_student_belong_college().trim().length() > 0) {
				return bysjglxt_student_user.getUser_student_belong_college();
			}
		}
		return null;
	}

	/**
	 * 这个方法用于开启选题流程以及毕业设计流程的调用
	 * 
	 * @param processInstanceName
	 * @param process_definition_id
	 * @param operation
	 * @return
	 */
	@Override
	public int openProcess(String processInstanceName, String process_definition_id, String operation, int fla) {
		// 判断用户是否已经开启该流程
		bysjglxt_process_instance processInstanceIsOpen = new bysjglxt_process_instance();
		// 根据流程定义ID以及流程实例化者ID判断是否已经开启流程
		processInstanceIsOpen = processManagementDao.getProcessInstanceByDefinitionAndMan(process_definition_id,
				operation);
		if (processInstanceIsOpen != null) {
			return -2;
		}
		// 根据流程定义Id获取流程定义
		bysjglxt_process_definition bysjglxt_process_definition = new bysjglxt_process_definition();
		bysjglxt_process_definition = processManagementDao.getProcessDefinition(process_definition_id);
		if (bysjglxt_process_definition == null) {
			return -3;
		}
		boolean flag = true;
		bysjglxt_student_user bysjglxt_student_user = null;
		bysjglxt_process_instance bysjglxt_process_instance = new bysjglxt_process_instance();
		bysjglxt_task_instance bysjglxt_task_instance = null;
		bysjglxt_section bysjglxt_section = null;
		bysjglxt_student_basic bysjglxt_student_basic = null;
		bysjglxt_topic_select bysjglxt_topic_select = null;
		bysjglxt_task_instance bysjglxt_task_instanceFather = null;
		bysjglxt_task_instance bysjglxt_task_instanceReturn = null;
		List<bysjglxt_task_definition> list_bysjglxt_task_definition = new ArrayList<bysjglxt_task_definition>();
		// 创建流程实例
		bysjglxt_process_instance.setProcess_instance_id(TeamUtil.getUuid());
		bysjglxt_process_instance.setProcess_instance_name(processInstanceName);
		bysjglxt_process_instance.setProcess_instance_process_definition(process_definition_id);
 		bysjglxt_process_instance.setProcess_instance_state("活动");
		bysjglxt_process_instance.setProcess_instance_man(operation);
		bysjglxt_process_instance.setProcess_instance_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_process_instance
				.setProcess_instance_gmt_modified(bysjglxt_process_instance.getProcess_instance_gmt_create());
		flag = processManagementDao.instanceProcess(bysjglxt_process_instance);
		if (!flag)
			return -3;
		// 遍历任务表中属于这个流程的任务定义
		list_bysjglxt_task_definition = processManagementDao.getListBelongProcess(process_definition_id);
		// 判断第一个任务实例为正在进行
		int x = 0;
		String section = null;
		List<String> listTask = new ArrayList<>();
		//设置可以被更改的流程
		listTask.add("学生完成进展情况记录（前期准备阶段）");
		listTask.add("学生完成进展情况记录（中期自查阶段）");
		listTask.add("学生完成个人学习工作总结");
		listTask.add("学生完成进展情况记录（完善阶段）");
		listTask.add("学生完成进展情况记录（撰写阶段）");
		listTask.add("指导老师填写进展情况意见（中期自查阶段）");
		listTask.add("指导老师填写进展情况意见（前期准备阶段）");
		listTask.add("指导老师填写进展情况意见（完善阶段）");
		listTask.add("指导老师填写进展情况意见（撰写阶段）");
		listTask.add("指导老师填写个人学习工作总结意见");
		for (bysjglxt_task_definition bysjglxt_task_definition : list_bysjglxt_task_definition) {
			x++;
			section = null;
			bysjglxt_section = new bysjglxt_section();
			bysjglxt_student_user = new bysjglxt_student_user();
			bysjglxt_student_basic = new bysjglxt_student_basic();
			List<bysjglxt_teacher_user> listTeacherUser = new ArrayList<>();
			bysjglxt_teacher_user bysjglxt_teacher_user = null;
			bysjglxt_task_instance = new bysjglxt_task_instance();
			bysjglxt_topic_select = new bysjglxt_topic_select();
			bysjglxt_task_instanceFather = new bysjglxt_task_instance();
			bysjglxt_task_instanceReturn = new bysjglxt_task_instance();
			bysjglxt_task_instance.setTask_instance_id(TeamUtil.getUuid());
			bysjglxt_task_instance
					.setTask_instance_process_instance(bysjglxt_process_instance.getProcess_instance_id());
			//设置流程默认可以被更改
			if(listTask.contains(bysjglxt_task_definition.getTask_definition_name())) {
				bysjglxt_task_instance.setTask_instance_is_update(1);
			}
			bysjglxt_task_instance.setTask_instance_task_definition(bysjglxt_task_definition.getTask_definition_id());
			switch (bysjglxt_task_definition.getTask_definition_role()) {
			case 1:
				// 指导老师
				// 判断学生账号是否错误
				// 判断那是否是学生点击开启流程
				bysjglxt_student_user = processManagementDao.getStudentUser(operation);
				if (bysjglxt_student_user == null) {
					return -1;
				}
				// 根据学生user ID获取学生选题表信息
				bysjglxt_topic_select = processManagementDao.getStudentSelectTopicByStudentUserID(operation);
				if (bysjglxt_topic_select == null) {
					return -3;
				}
				bysjglxt_task_instance.setTask_instance_role(bysjglxt_topic_select.getTopic_select_teacher_tutor());
				break;
			case 2:
				// 评阅老师
				// 判断学生账号是否错误
				// 判断那是否是学生点击开启流程
				bysjglxt_student_user = processManagementDao.getStudentUser(operation);
				if (bysjglxt_student_user == null) {
					return -1;
				}
				// 根据学生user ID获取学生选题表信息
				bysjglxt_topic_select = processManagementDao.getStudentSelectTopicByStudentUserID(operation);
				if (bysjglxt_topic_select == null) {
					return -3;
				}
				if (bysjglxt_topic_select.getTopic_select_teacher_review() != null) {
					bysjglxt_task_instance
							.setTask_instance_role(bysjglxt_topic_select.getTopic_select_teacher_review());
				}
				break;
			case 3:
				String roleCollege = "";
				if (fla == 1) {
					roleCollege = collegeJudge(1, operation);
				} else {
					bysjglxt_student_user = processManagementDao.getStudentUser(operation);
					if (bysjglxt_student_user == null) {
						return -1;
					}
					roleCollege = bysjglxt_student_user.getUser_student_belong_college();
				}

				// 系部管理员
				listTeacherUser = processManagementDao.getListAdminCollegeByCollege(roleCollege);
				if (listTeacherUser == null) {
					return -3;
				}
				bysjglxt_teacher_user = new bysjglxt_teacher_user();
				bysjglxt_teacher_user = listTeacherUser.get(0);
				bysjglxt_task_instance.setTask_instance_role(bysjglxt_teacher_user.getUser_teacher_id());
				break;
			case 4:
				// 教研室主任
				// 判断学生账号是否错误
				// 判断那是否是学生点击开启流程
				bysjglxt_student_user = processManagementDao.getStudentUser(operation);
				if (bysjglxt_student_user == null) {
					return -1;
				}
				// 根据学生专业获取教研室信息
				bysjglxt_section = processManagementDao
						.getSectionByMajorId(bysjglxt_student_user.getUser_student_belong_major());
				if (bysjglxt_section == null) {
					return -1;
				}
				bysjglxt_task_instance.setTask_instance_role(bysjglxt_section.getSection_leader());
				break;

			case 5:
				// 判断学生账号是否错误
				// 判断那是否是学生点击开启流程
				bysjglxt_student_user = processManagementDao.getStudentUser(operation);
				if (bysjglxt_student_user == null) {
					return -1;
				}
				// 根据学生user ID获取学生选题表信息
				bysjglxt_task_instance.setTask_instance_role(operation);
				break;
			}
			// 父任务实例ID
			// (1).先获得父任务定义ID
			// bysjglxt_task_definition.getTask_definition_father()
			// (2).根据任务实例所属流程实例ID以及任务实例所属任务定义ID得到父任务实例ID
			if (bysjglxt_task_definition.getTask_definition_father() != null
					&& bysjglxt_task_definition.getTask_definition_father().trim().length() > 0) {
				bysjglxt_task_instanceFather = processManagementDao
						.getTaskInstanceByProcessInstanceIdAndTaskDefinitionId(
								bysjglxt_process_instance.getProcess_instance_id(),
								bysjglxt_task_definition.getTask_definition_father());
				bysjglxt_task_instance.setTask_instance_father(bysjglxt_task_instanceFather.getTask_instance_id());
			}
			// 返回的任务实例
			// (1)获得返回任务定义ID
			// bysjglxt_task_definition.getTask_definition_return()
			// (2)根据任务实例所属流程实例ID以及任务实例所属任务定义ID得到返回任务实例ID
			if (bysjglxt_task_definition.getTask_definition_return() != null
					&& bysjglxt_task_definition.getTask_definition_return().trim().length() > 0) {

				bysjglxt_task_instanceReturn = processManagementDao
						.getTaskInstanceByProcessInstanceIdAndTaskDefinitionId(
								bysjglxt_process_instance.getProcess_instance_id(),
								bysjglxt_task_definition.getTask_definition_return());
				if (bysjglxt_task_instanceReturn == null) {
				}
				bysjglxt_task_instance.setTask_instance_return(bysjglxt_task_instanceReturn.getTask_instance_id());
			}
			// 状态初始化 2：未开始
			if (x == 1) {
				bysjglxt_task_instance.setTask_instance_state(1);
			} else {
				bysjglxt_task_instance.setTask_instance_state(2);
			}
			bysjglxt_task_instance.setTask_instance_gmt_create(TeamUtil.getStringSecond());
			bysjglxt_task_instance.setTask_instance_gmt_modified(bysjglxt_task_instance.getTask_instance_gmt_create());
			flag = processManagementDao.instanceTask(bysjglxt_task_instance);
			if (!flag)
				return -3;
		}
		//得先知道是哪个系的
		String college = "";
		String launch = "";
		List<StudentInformationDTO> listStudentUser = new ArrayList<>();
		List<TeacherInformationDTO> listTeacherUser = new ArrayList<>();
		if(bysjglxt_process_definition!=null && "选题流程".equals(bysjglxt_process_definition.getProcess_definition_name())) {
			//根据教师userId获取学院信息
			bysjglxt_teacher_user teacherUser = processManagementDao.getTeacherUserByNum(operation);
			if(teacherUser!=null && teacherUser.getUser_teacher_belong_college()!=null && !"".equals(teacherUser.getUser_teacher_belong_college())) {
				college = teacherUser.getUser_teacher_belong_college();
				launch = operation;
				listTeacherUser = processManagementDao.getTeacherUserByCollegeId(college);
				listStudentUser = processManagementDao.getStudentUserByCollegeId(college);
				//通知该系部所有拥有教研室老师
				for (TeacherInformationDTO teacherInformationDTO : listTeacherUser) {
					if(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id().equals(launch)) {
						createTeacherNotice(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id(),launch,"createAdminSelect",2);
						continue;
					}
					createTeacherNotice(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id(),launch,"bootSelectTopic",4);
					createTeacherNotice(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id(),launch,"createTeacherSelect",2);
				}
				for (StudentInformationDTO studentInformationDTO : listStudentUser) {
					createTeacherNotice(studentInformationDTO.getBysjglxtStudentUser().getUser_student_id(),launch,"bootSelectTopic",4);
				}
			}
		}else if(bysjglxt_process_definition!=null && "毕业设计流程".equals(bysjglxt_process_definition.getProcess_definition_name())) {
			//获取该学生的指导老师
			bysjglxt_student_user studentUser = processManagementDao.getStudentUser(operation);
			bysjglxt_student_basic studentBasic = processManagementDao.getStudentBasicById(studentUser.getUser_student_basic());
			if(studentUser!=null && studentUser.getUser_student_belong_college()!=null && !"".equals(studentUser.getUser_student_belong_college())) {
				college = studentUser.getUser_student_belong_college();
				bysjglxt_teacher_user teacherUser = processManagementDao.getTeacherByCollege(college);
				launch = teacherUser.getUser_teacher_id();
				//获取该学生的指导老师
				bysjglxt_teacher_user tutorTeacherUser = processManagementDao.getTeacherUserByStudentId(operation);
				String content = ((String)(properties.get("createTeacherXiaTaskBook"))).replaceAll("student_num", studentBasic.getStudent_basic_num()).replaceAll("student_name", studentBasic.getStudent_basic_name());
				createTeacherNoticeString(tutorTeacherUser.getUser_teacher_id(),launch,content,2);
			}
			
		}
		return 1;

	}
	
	/**
	 * @param 
	 */
	public void createTeacherNoticeString(String belongUserId,String userId,String content,int leixing) {
		bysjglxt_notice notice = new bysjglxt_notice();
		notice.setNotice_id(TeamUtil.getUuid());
		notice.setNotice_launch(userId);
		notice.setNotice_belong(belongUserId);
		notice.setNotice_content(content);
		notice.setNotice_leixing(leixing);
		notice.setNotice_state(2);
		notice.setNotice_gmt_create(TeamUtil.getStringSecond());
		notice.setNotice_gmt_modified(notice.getNotice_gmt_create());
		processManagementDao.saveObj(notice);
	}
	
	/**
	 * @param 
	 */
	public void createTeacherNotice(String belongUserId,String userId,String content,int leixing) {
		bysjglxt_notice notice = new bysjglxt_notice();
		notice.setNotice_id(TeamUtil.getUuid());
		notice.setNotice_launch(userId);
		notice.setNotice_belong(belongUserId);
		notice.setNotice_content((String)(properties.get(content)));
		notice.setNotice_leixing(leixing);
		notice.setNotice_state(2);
		notice.setNotice_gmt_create(TeamUtil.getStringSecond());
		notice.setNotice_gmt_modified(notice.getNotice_gmt_create());
		processManagementDao.saveObj(notice);
	}
	

	@Override
	public ProcessDefinitionDetailDTO processDefinitionDetailDTO(String processDefinitionId) {
		ProcessDefinitionDetailDTO processDefinitionDetailDTO = new ProcessDefinitionDetailDTO();
		bysjglxt_process_definition bysjglxtProcessDefinition = new bysjglxt_process_definition();
		List<bysjglxt_task_definition> List_bysjglxtTaskDefinition = new ArrayList<bysjglxt_task_definition>();
		// 根据流程定义ID获取流程定义对象
		bysjglxtProcessDefinition = processManagementDao.getProcessDefinition(processDefinitionId);
		if (bysjglxtProcessDefinition == null) {
			return null;
		}
		List_bysjglxtTaskDefinition = processManagementDao.getListBelongProcess(processDefinitionId);
		processDefinitionDetailDTO.setBysjglxtProcessDefinition(bysjglxtProcessDefinition);
		processDefinitionDetailDTO.setList_bysjglxtTaskDefinition(List_bysjglxtTaskDefinition);
		return processDefinitionDetailDTO;
	}

	// 点击我的任务
	@Override
	public ProcessManagementVO getMyTaskByPage(ProcessManagementVO processManagementVo, String userID) {
		List<ProcessDetailDTO> list_ProcessInstanceDetailDTO = new ArrayList<ProcessDetailDTO>();
		List<bysjglxt_task_instance> list_bysjglxtTaskInstance = new ArrayList<bysjglxt_task_instance>();
		ProcessDetailDTO processDetailDTO = null;
		bysjglxt_process_definition bysjglxtProcessDefinition = null;
		bysjglxt_task_definition bysjglxtTaskDefinition = null;
		bysjglxt_process_instance bysjglxtProcessInstance = null;
		// 获得符合条件的10条任务实例数据
		list_bysjglxtTaskInstance = processManagementDao.getListTaskInstanceByPager(processManagementVo, userID);
		// 遍历10条分别封装其他对象
		for (bysjglxt_task_instance bysjglxt_task_instance : list_bysjglxtTaskInstance) {
			bysjglxtTaskDefinition = new bysjglxt_task_definition();
			bysjglxtProcessInstance = new bysjglxt_process_instance();
			bysjglxtProcessDefinition = new bysjglxt_process_definition();
			processDetailDTO = new ProcessDetailDTO();
			// 根据任务定义ID获取任务定义表
			bysjglxtTaskDefinition = processManagementDao
					.getTaskDefinition(bysjglxt_task_instance.getTask_instance_task_definition());
			if (bysjglxtTaskDefinition == null) {
				return null;
			}
			// 根据流程实例ID获取流程实例表
			bysjglxtProcessInstance = processManagementDao
					.getProcessInstanceById(bysjglxt_task_instance.getTask_instance_process_instance());
			if (bysjglxtProcessInstance == null) {
				return null;
			}
			// 根据流程定义ID获取流程定义表
			bysjglxtProcessDefinition = processManagementDao
					.getProcessDefinition(bysjglxtProcessInstance.getProcess_instance_process_definition());
			if (bysjglxtProcessDefinition == null) {
				return null;
			}
			processDetailDTO.setBysjglxtProcessDefinition(bysjglxtProcessDefinition);
			processDetailDTO.setBysjglxtProcessInstance(bysjglxtProcessInstance);
			processDetailDTO.setBysjglxtTaskDefinition(bysjglxtTaskDefinition);
			processDetailDTO.setBysjglxtTaskInstance(bysjglxt_task_instance);
			list_ProcessInstanceDetailDTO.add(processDetailDTO);
		}
		processManagementVo.setList_ProcessDetailDTO(list_ProcessInstanceDetailDTO);
		// 获得符合条件的总记录数
		list_bysjglxtTaskInstance = processManagementDao.getAllTaskList(processManagementVo, userID);
		int i = list_bysjglxtTaskInstance.size();
		processManagementVo.setTotalRecords(i);
		processManagementVo.setTotalPages(((i - 1) / processManagementVo.getPageSize()) + 1);
		if (processManagementVo.getPageIndex() <= 1) {
			processManagementVo.setHavePrePage(false);
		} else {
			processManagementVo.setHavePrePage(true);
		}
		if (processManagementVo.getPageIndex() >= processManagementVo.getTotalPages()) {
			processManagementVo.setHaveNextPage(false);
		} else {
			processManagementVo.setHaveNextPage(true);
		}
		return processManagementVo;
	}

	// 删除流程定义
	@Override
	public int deleteProcessDefinition(List<String> listProcessDefinitionId) {
		boolean flag = true;
		bysjglxt_process_definition bysjglxt_process_definition = null;
		List<bysjglxt_process_instance> listProcessInstance = new ArrayList<bysjglxt_process_instance>();
		List<bysjglxt_task_definition> listProcessDefinition = new ArrayList<bysjglxt_task_definition>();
		List<bysjglxt_task_instance> listTaskInstance = new ArrayList<bysjglxt_task_instance>();
		for (String processDefinitionId : listProcessDefinitionId) {
			bysjglxt_process_definition = new bysjglxt_process_definition();
			// 根据流程定义ID获取流程定义对象
			bysjglxt_process_definition = processManagementDao.getProcessDefinition(processDefinitionId);
			if (bysjglxt_process_definition == null) {
				return -3;
			}
			// 根据流程定义获取任务定义
			listProcessDefinition = processManagementDao.getListBelongProcess(processDefinitionId);
			// 根据流程定义获取流程实例List
			listProcessInstance = processManagementDao.getListProcessInstanceByDefinitionId(processDefinitionId);
			for (bysjglxt_process_instance bysjglxt_process_instance : listProcessInstance) {
				// 根据流程实例删除任务实例
				flag = processManagementDao
						.deleteTaskInstanceByProcessInstance(bysjglxt_process_instance.getProcess_instance_id());
				if (!flag)
					return -3;
			}
			// 根据流程定义ID删除流程实例
			flag = processManagementDao.deleteProcessInstanceByProcessDefinitionId(processDefinitionId);
			if (!flag)
				return -3;
			// 根据流程定义ID删除任务定义
			flag = processManagementDao.deleteTaskDefinitionByProcessDefinitionId(processDefinitionId);
			if (!flag)
				return -3;
			// 删除流程定义
			flag = processManagementDao.deleteProcessDefinitionByProcessDefinitionId(processDefinitionId);
			if (!flag)
				return -3;
		}
		return 1;
	}

	/**
	 * 
	 * 获得正在进行的流程
	 *
	 */
	@Override
	public ProcessDTO getCurrentTaskDTO(String userId) {
		String college = collegeJudge(2, userId);
		TaskDTO taskDTO = new TaskDTO();
		List<TaskDTO> listTaskDTO = new ArrayList<TaskDTO>();
		ProcessDTO processDTO = new ProcessDTO();
		bysjglxt_task_definition bysjglxt_task_definition = new bysjglxt_task_definition();
		bysjglxt_process_definition bysjglxt_process_definition = new bysjglxt_process_definition();
		bysjglxt_process_instance bysjglxt_process_instance = new bysjglxt_process_instance();
		List<bysjglxt_task_instance> list_bysjglxt_task_instance = new ArrayList<bysjglxt_task_instance>();
		// 1.根据学院用户Id及流程实例状态获取用户正在进行的流程实例
		bysjglxt_process_instance = processManagementDao.getProcessInstanceByUserAndState(userId, college);
		// 2.根据流程定义ID获取流程定义
		if (bysjglxt_process_instance != null) {
			bysjglxt_process_definition = processManagementDao
					.getProcessDefinition(bysjglxt_process_instance.getProcess_instance_process_definition());
			// 3.根据流程实例ID获取任务实例
			list_bysjglxt_task_instance = processManagementDao
					.getListTaskInstanceByProcessInstanceId(bysjglxt_process_instance.getProcess_instance_id());
			// 4.根据任务实例获得任务定义
			for (bysjglxt_task_instance task_instance : list_bysjglxt_task_instance) {
				bysjglxt_task_definition = new bysjglxt_task_definition();
				taskDTO = new TaskDTO();
				// 5.根据任务定义ID获取任务定义表
				bysjglxt_task_definition = processManagementDao
						.getTaskDefinition(task_instance.getTask_instance_task_definition());
				taskDTO.setTaskDefinition(bysjglxt_task_definition);
				taskDTO.setTaskInstance(task_instance);
				listTaskDTO.add(taskDTO);
			}
			processDTO.setProcessDefinition(bysjglxt_process_definition);
			processDTO.setProcessInstance(bysjglxt_process_instance);
			processDTO.setListTaskBelongProcess(listTaskDTO);

		} else {
		}
		return processDTO;
	}

	/**********************************************
	 * 下面是点击通过或打回
	 ***************************/

	public String getCollegeId(String userId,String juese) {
		if("teacher".equals(juese)) {
			return processManagementDao.getTeacherUserByNum(userId).getUser_teacher_belong_college();
		}else if("student".equals(juese)) {
			return processManagementDao.getStudentUser(userId).getUser_student_belong_college();
		}
		return null;
	}
	
	// 通过
	@Override
	public int pass(String taskInstanceId,String userId,String juese) {
		String college = getCollegeId(userId,juese);
		if(college ==null) {
			return -3;
		}
		bysjglxt_teacher_user teacherUser = processManagementDao.getTeacherByCollege(college);
		if(teacherUser == null) {
			return -3;
		}
		boolean flag = true;
		bysjglxt_task_instance currentTaskInstance = new bysjglxt_task_instance();
		bysjglxt_task_instance nextTaskInstance = new bysjglxt_task_instance();
		bysjglxt_notice bysjglxt_notice = new bysjglxt_notice();
		bysjglxt_process_instance bysjglxt_process_instance = new bysjglxt_process_instance();
		currentTaskInstance = processManagementDao.getTaskInstanceingById(taskInstanceId);
		if (currentTaskInstance == null) {
			return -3;
			// 无该任务实例
		}
		// 只有正在进行的才能点击通过
		if (currentTaskInstance.getTask_instance_state() != 1) {
			return -2;
			// 任务实例不是正在进行状态不能点击通过
		}
		// 判断下一步角色是否存在
		// 以当前实例ID为父任务实例ID去查找下一个任务
		nextTaskInstance = processManagementDao.getTaskInstanceByFatherTaskId(taskInstanceId);
		if (nextTaskInstance != null) {
			// 判断这一步角色是否已经分配
			if (nextTaskInstance.getTask_instance_role() == null
					|| nextTaskInstance.getTask_instance_role().trim().length() < 0) {
				return -5;
			}
		}
		// 更改任务实例状态,将之改为已结束
		currentTaskInstance.setTask_instance_state(3);
		currentTaskInstance.setTask_instance_gmt_modified(TeamUtil.getStringSecond());
		currentTaskInstance.setTask_instance_stop(TeamUtil.getStringSecond());
		// 存储任务实例
		flag = processManagementDao.instanceTask(currentTaskInstance);
		bysjglxt_process_instance = processManagementDao
				.getProcessInstanceById(currentTaskInstance.getTask_instance_process_instance());
		if (!flag)
			return -1;// 更改任务实例失败
		// 将当前任务ID作为父任务实例ID可以获得下一个任务实例
		nextTaskInstance = processManagementDao
				.getTaskInstanceByFatherTaskId(currentTaskInstance.getTask_instance_id());
		if (nextTaskInstance == null) {
			// 这里是以当前任务实例ID作为下一个任务实例ID查找后没有找到
			// 也就是说这个任务实例已经是最后一个实例了
			// 将该任务所属流程实例状态更改为结束
			bysjglxt_process_instance.setProcess_instance_state("结束");
			bysjglxt_process_instance.setProcess_instance_gmt_modified(TeamUtil.getStringSecond());
			flag = processManagementDao.instanceProcess(bysjglxt_process_instance);
			if (!flag)
				return -1;
		}else {
			// 更改任务实例状态,将之改为正在进行
			nextTaskInstance.setTask_instance_state(1);
			nextTaskInstance.setTask_instance_start(TeamUtil.getStringSecond());
			nextTaskInstance.setTask_instance_gmt_modified(TeamUtil.getStringSecond());
			// 存储任务实例
			flag = processManagementDao.instanceTask(nextTaskInstance);
			if (!flag)
				return -1;// 更改任务实例失败
			// 将记录插入到通知表中
			bysjglxt_notice.setNotice_id(TeamUtil.getUuid());
			if (currentTaskInstance.getTask_instance_role() == null || nextTaskInstance.getTask_instance_role() == null) {
				return 1;
			}
			
		}
		//TODO 各项任务的通知都在这里集中
		//获取任务定义
		bysjglxt_task_definition taskDefinition = new bysjglxt_task_definition();
		if(currentTaskInstance!=null && currentTaskInstance.getTask_instance_task_definition()!=null && !"".equals(currentTaskInstance.getTask_instance_task_definition())) {
			taskDefinition = processManagementDao.getTaskDefinition(currentTaskInstance.getTask_instance_task_definition());
		}
		if(taskDefinition == null) {
			return 1;
		}
		List<StudentInformationDTO> listStudentUser = new ArrayList<>();
		List<TeacherInformationDTO> listTeacherUser = new ArrayList<>();
		bysjglxt_student_user studentUserNotice = new bysjglxt_student_user();
		bysjglxt_student_basic studentBasicNotice = new bysjglxt_student_basic();
		String neirong = "";
		switch(taskDefinition.getTask_definition_name()) {
		case "创建选题":
			//获取该学院的所有老师
			listTeacherUser = processManagementDao.getTeacherUserListByCollegeId(college);
//			listStudentUser = processManagementDao.getStudentUserByCollegeId(college);
			for (TeacherInformationDTO teacherInformationDTO : listTeacherUser) {
				if(teacherUser.getUser_teacher_id().equals(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id())) {
					continue;
				}
				//生成新的公告---新的所有人的通知
				sendMessage(teacherUser.getUser_teacher_id(),teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id(),((String)(properties.get("passCreateTopic"))),2,2);
			}
			//发给自己的-----新得任务结点的自己的通知
			sendMessage(teacherUser.getUser_teacher_id(),teacherUser.getUser_teacher_id(),((String)(properties.get("earlySelectTopic"))),2,2);
			//将创建选题的任务全部改成已完成-----上一个发给大家的通知
			List<bysjglxt_notice> listNotice = processManagementDao.getNoticeByContentAndLeiXing(((String)(properties.get("createTeacherSelect"))),2);
			if(listNotice!=null && listNotice.size()>0) {
				for (bysjglxt_notice notice : listNotice) {
					notice.setNotice_leixing(1);
					notice.setNotice_state(1);
					notice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(notice);
				}
			}
			//那个管理员的创建选题的通知也要变成已完成--------上一个发给管理员的通知
			bysjglxt_notice adminNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(teacherUser.getUser_teacher_id(), ((String)(properties.get("createAdminSelect"))),2);
			if(adminNotice!=null) {
				adminNotice.setNotice_leixing(1);
				adminNotice.setNotice_state(1);
				adminNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
				processManagementDao.saveObj(adminNotice);
			}
			break;
		case "提前选题":
			//获取该学院的所有老师
			listTeacherUser = processManagementDao.getTeacherUserListByCollegeId(college);
//			listStudentUser = processManagementDao.getStudentUserByCollegeId(college);
			for (TeacherInformationDTO teacherInformationDTO : listTeacherUser) {
				if(teacherUser.getUser_teacher_id().equals(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id())) {
					continue;
				}
				//生成新的公告
				sendMessage(teacherUser.getUser_teacher_id(),teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id(),((String)(properties.get("passEarlySelectTopic"))),2,2);
			}
			//发给自己的
			sendMessage(teacherUser.getUser_teacher_id(),teacherUser.getUser_teacher_id(),((String)(properties.get("formalSelectTopic"))),2,2);
			//将提前选题的任务全部改成已完成
			List<bysjglxt_notice> list = processManagementDao.getNoticeByContentAndLeiXing(((String)(properties.get("passCreateTopic"))),2);
			if(list!=null && list.size()>0) {
				for (bysjglxt_notice notice : list) {
					notice.setNotice_leixing(1);
					notice.setNotice_state(1);
					notice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(notice);
				}
			}
			//那个管理员的提前选题的通知也要变成已完成
			bysjglxt_notice adminNotice2 = processManagementDao.getNoticeByBelongContentAndLeiXing(teacherUser.getUser_teacher_id(), ((String)(properties.get("earlySelectTopic"))),2);
			if(adminNotice2!=null) {
				adminNotice2.setNotice_leixing(1);
				adminNotice2.setNotice_state(1);
				adminNotice2.setNotice_gmt_modified(TeamUtil.getStringSecond());
				processManagementDao.saveObj(adminNotice2);
			}
			break;
		case "正式选题":
			//获取该学院的所有老师
			listTeacherUser = processManagementDao.getTeacherUserListByCollegeId(college);
//			listStudentUser = processManagementDao.getStudentUserByCollegeId(college);
			for (TeacherInformationDTO teacherInformationDTO : listTeacherUser) {
				if(teacherUser.getUser_teacher_id().equals(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id())) {
					continue;
				}
				//生成新的公告
				sendMessage(teacherUser.getUser_teacher_id(),teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id(),((String)(properties.get("passformalSelectTopic"))),2,2);
			}
			//发给自己的
			sendMessage(teacherUser.getUser_teacher_id(),teacherUser.getUser_teacher_id(),((String)(properties.get("assignSelectTopic"))),2,2);
			//将提前选题的任务全部改成已完成
			List<bysjglxt_notice> list2 = processManagementDao.getNoticeByContentAndLeiXing(((String)(properties.get("passEarlySelectTopic"))),2);
			if(list2!=null && list2.size()>0) {
				for (bysjglxt_notice notice : list2) {
					notice.setNotice_leixing(1);
					notice.setNotice_state(1);
					notice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(notice);
				}
			}
			//那个管理员的提前选题的通知也要变成已完成
			adminNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(teacherUser.getUser_teacher_id(), ((String)(properties.get("formalSelectTopic"))),2);
			if(adminNotice!=null) {
				adminNotice.setNotice_leixing(1);
				adminNotice.setNotice_state(1);
				adminNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
				processManagementDao.saveObj(adminNotice);
			}
			break;
		case "分配选题":
			//获取该学院的所有老师
			listTeacherUser = processManagementDao.getTeacherUserListByCollegeId(college);
//			listStudentUser = processManagementDao.getStudentUserByCollegeId(college);
			for (TeacherInformationDTO teacherInformationDTO : listTeacherUser) {
				if(teacherUser.getUser_teacher_id().equals(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id())) {
					continue;
				}
				//生成新的公告
				sendMessage(teacherUser.getUser_teacher_id(),teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id(),((String)(properties.get("passassignSelectTopic"))),2,2);
			}
			//发给自己的
			sendMessage(teacherUser.getUser_teacher_id(),teacherUser.getUser_teacher_id(),((String)(properties.get("finishTopic"))),2,2);
			//将提前选题的任务全部改成已完成
			list2 = processManagementDao.getNoticeByContentAndLeiXing(((String)(properties.get("passformalSelectTopic"))),2);
			if(list2!=null && list2.size()>0) {
				for (bysjglxt_notice notice : list2) {
					notice.setNotice_leixing(1);
					notice.setNotice_state(1);
					notice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(notice);
				}
			}
			//那个管理员的提前选题的通知也要变成已完成
			adminNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(teacherUser.getUser_teacher_id(), ((String)(properties.get("assignSelectTopic"))),2);
			if(adminNotice!=null) {
				adminNotice.setNotice_leixing(1);
				adminNotice.setNotice_state(1);
				adminNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
				processManagementDao.saveObj(adminNotice);
			}
			break;
		case "完成选题":
			//获取该学院的所有老师
			listTeacherUser = processManagementDao.getTeacherUserListByCollegeId(college);
			listStudentUser = processManagementDao.getStudentUserByCollegeId(college);
			for (StudentInformationDTO studentInformationDTO : listStudentUser) {
				//生成新的公告
				sendMessage(teacherUser.getUser_teacher_id(),studentInformationDTO.getBysjglxtStudentUser().getUser_student_id(),((String)(properties.get("topicSelectFinishStudent"))),4,2);
			}
			for (TeacherInformationDTO teacherInformationDTO : listTeacherUser) {
				if(teacherUser.getUser_teacher_id().equals(teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id())) {
					continue;
				}
				//生成新的公告
				sendMessage(teacherUser.getUser_teacher_id(),teacherInformationDTO.getBysjglxtTeacherUser().getUser_teacher_id(),((String)(properties.get("passfinishTopic"))),4,2);
			}
			//发给自己的
			sendMessage(teacherUser.getUser_teacher_id(),teacherUser.getUser_teacher_id(),((String)(properties.get("createGraduationProcess"))),2,2);
			//将提前选题的任务全部改成已完成
			list2 = processManagementDao.getNoticeByContentAndLeiXing(((String)(properties.get("passassignSelectTopic"))),2);
			if(list2!=null && list2.size()>0) {
				for (bysjglxt_notice notice : list2) {
					notice.setNotice_leixing(1);
					notice.setNotice_state(1);
					notice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(notice);
				}
			}
			//那个管理员的提前选题的通知也要变成已完成
			adminNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(teacherUser.getUser_teacher_id(), ((String)(properties.get("finishTopic"))),2);
			if(adminNotice!=null) {
				adminNotice.setNotice_leixing(1);
				adminNotice.setNotice_state(1);
				adminNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
				processManagementDao.saveObj(adminNotice);
			}
			break;
		case "指导老师下发任务书":
			//获取下发任务书的通知，并将这个通知变成已完成
			bysjglxt_student_user studentUser = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			bysjglxt_student_basic studentBasic = processManagementDao.getStudentBasicById(studentUser.getUser_student_basic());
			if(studentBasic != null) {
				String content = ((String)(properties.get("createTeacherXiaTaskBook"))).replaceAll("student_num", studentBasic.getStudent_basic_num()).replaceAll("student_name", studentBasic.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			//下发新的通知
			sendMessage(userId,nextTaskInstance.getTask_instance_role(),((String)(properties.get("wanShanTaskBook"))),2,2);
			break;
		case "学生完善任务书":
			//获取下发任务书的通知，并将这个通知变成已完成
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("wanShanTaskBook")));
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			neirong = ((String)(properties.get("tutorShenTaskBook"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//下发新的通知
			sendMessage(userId,nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "指导老师审核任务书":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("tutorShenTaskBook"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			neirong = ((String)(properties.get("tutorShenTaskBookPassStudent")));
			//下发学生新的通知
			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("tutorShenTaskBookPassCollege"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());;
			//通知管理员
			sendMessage(studentUserNotice.getUser_student_id(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "指导小组组长填写任务书审核意见":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("tutorShenTaskBookPassCollege"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			neirong = ((String)(properties.get("collegeShenTaskBookPassStudent")));
			//下发学生新的通知
			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			//.replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());;
			neirong = ((String)(properties.get("studentUploadReport")));
			//通知管理员
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "学生上传开题报告":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				//.replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				String content = ((String)(properties.get("studentUploadReport")));
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
//			neirong = ((String)(properties.get("collegeShenTaskBookPassStudent")));
			//下发学生新的通知
//			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("passStudentUploadReport"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());;
			//通知管理员
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "指导老师确认开题报告":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("passStudentUploadReport"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			neirong = ((String)(properties.get("tutorShenReportPass")));
			//下发学生新的通知
			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("qianRecordStudent")));
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "学生完成进展情况记录（前期准备阶段）":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("qianRecordStudent"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
//			neirong = ((String)(properties.get("tutorShenReportPass")));
			//下发学生新的通知
//			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("qianRecordTeacher"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "指导老师填写进展情况意见（前期准备阶段）":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("qianRecordTeacher"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
//			neirong = ((String)(properties.get("tutorShenReportPass")));
			//下发学生新的通知
//			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("zhuanRecordStudent"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "学生完成进展情况记录（撰写阶段）":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("zhuanRecordStudent"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
//			neirong = ((String)(properties.get("tutorShenReportPass")));
			//下发学生新的通知
//			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("zhuanRecordTeacher"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "指导老师填写进展情况意见（撰写阶段）":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("zhuanRecordTeacher"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
//			neirong = ((String)(properties.get("tutorShenReportPass")));
			//下发学生新的通知
//			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("zhongRecordStudent"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "学生完成进展情况记录（中期自查阶段）":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("zhongRecordStudent"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
//			neirong = ((String)(properties.get("tutorShenReportPass")));
			//下发学生新的通知
//			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("zhongRecordTeacher"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "指导老师填写进展情况意见（中期自查阶段）":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("zhongRecordTeacher"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
//			neirong = ((String)(properties.get("tutorShenReportPass")));
			//下发学生新的通知
//			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("wanRecordStudent"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "学生完成进展情况记录（完善阶段）":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("wanRecordStudent"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
//			neirong = ((String)(properties.get("tutorShenReportPass")));
			//下发学生新的通知
//			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("wanRecordTeacher"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "指导老师填写进展情况意见（完善阶段）":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("wanRecordTeacher"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
//			neirong = ((String)(properties.get("tutorShenReportPass")));
			//下发学生新的通知
//			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("zongJieStudent"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "学生完成个人学习工作总结":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("zongJieStudent"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
//			neirong = ((String)(properties.get("tutorShenReportPass")));
			//下发学生新的通知
//			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("zongJieTeacher"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "指导老师填写个人学习工作总结意见":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("zongJieTeacher"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
//			neirong = ((String)(properties.get("tutorShenReportPass")));
			//下发学生新的通知
//			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("graduationStudent"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "学生提交答辩论文":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("graduationStudent"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
//			neirong = ((String)(properties.get("tutorShenReportPass")));
			//下发学生新的通知
//			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("graduationTeacher"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "指导老师填写形式审查表":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("graduationTeacher"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			neirong = ((String)(properties.get("graduationCheckTeacherPassStudent")));
			//下发学生新的通知
			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("graduationCheckTeacherPassCollege"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "指导小组组长填写形式审查表（核查）":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("graduationCheckTeacherPassCollege"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			neirong = ((String)(properties.get("graduationCheckCollegePassStudent")));
			//下发学生新的通知
			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("graduationCheckCollegePassCollege"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "指导老师填写评价审阅表":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("graduationCheckCollegePassCollege"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			neirong = ((String)(properties.get("graduationEvaluateTutorPass")));
			//下发学生新的通知
			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("graduationReviewTutor"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),nextTaskInstance.getTask_instance_role(),neirong,2,2);
			break;
		case "评阅老师填写评阅审查表":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("graduationReviewTutor"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(userId, content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			neirong = ((String)(properties.get("graduationReviewTutorPass")));
			//下发学生新的通知
			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("disserationStart"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
			//通知下一任务
			sendMessage(currentTaskInstance.getTask_instance_role(),teacherUser.getUser_teacher_id(),neirong,2,2);
			break;
		}
		
/*		// 通知执行者
		bysjglxt_notice.setNotice_launch(currentTaskInstance.getTask_instance_role());
		// 通知拥有者
		bysjglxt_notice.setNotice_belong(nextTaskInstance.getTask_instance_role());
		bysjglxt_notice.setNotice_content("上一步操作已完成");
		bysjglxt_notice.setNotice_state(2);
		bysjglxt_notice.setNotice_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_notice.setNotice_gmt_modified(bysjglxt_notice.getNotice_gmt_create());
		if (!(bysjglxt_notice.getNotice_belong().equals(bysjglxt_notice.getNotice_launch()))) {
			// 存储记录
			processManagementDao.fillNoticeRecord(bysjglxt_notice);
		}*/
		return 1;// 成功
	}

	
	/**
	 * 
	 */
	public void sendMessage(String launch,String belong,String content,int leixing,int state) {
		bysjglxt_notice notice = new bysjglxt_notice();
		notice.setNotice_id(TeamUtil.getUuid());
		notice.setNotice_launch(launch);
		notice.setNotice_belong(belong);
		notice.setNotice_leixing(leixing);
		notice.setNotice_state(state);
		notice.setNotice_content(content);
		notice.setNotice_gmt_create(TeamUtil.getStringSecond());
		notice.setNotice_gmt_modified(notice.getNotice_gmt_create());
		processManagementDao.saveObj(notice);
	}
	
	
	
	/**
	 * 
	 */
	
	// 打回
	@Override
	public int repulse(String taskInstanceId) {
		boolean flag = true;
		// 1.根据任务实例ID获取任务实例实例对象
		bysjglxt_task_instance currentTaskInstance = new bysjglxt_task_instance();
		bysjglxt_task_instance neCurrentTaskInstanceFather = new bysjglxt_task_instance();
		bysjglxt_task_instance previewTask = new bysjglxt_task_instance();
		bysjglxt_task_instance returnTask = new bysjglxt_task_instance();
		bysjglxt_task_instance currentTaskInstanceFather = new bysjglxt_task_instance();
		bysjglxt_notice bysjglxt_notice = new bysjglxt_notice();
		// bysjglxt_task_instance currentTaskInstanceReturn = new
		// bysjglxt_task_instance();
		bysjglxt_process_instance bysjglxt_process_instance = new bysjglxt_process_instance();
		currentTaskInstance = processManagementDao.getTaskInstanceingById(taskInstanceId);
		if (currentTaskInstance == null) {
			return -3;
			// 无该任务实例
		}
		bysjglxt_process_instance = processManagementDao
				.getProcessInstanceById(currentTaskInstance.getTask_instance_process_instance());
		
		// 2.将当前实例对象更改未未开始
		// 更改任务实例状态,将之改为已结束
		currentTaskInstance.setTask_instance_state(2);
		currentTaskInstance.setTask_instance_gmt_modified(TeamUtil.getStringSecond());
		// 存储任务实例
		flag = processManagementDao.instanceTask(currentTaskInstance);
		if (!flag)
			return -1;// 更改任务实例失败
		previewTask = processManagementDao.getTaskInstanceingById(currentTaskInstance.getTask_instance_father());
		returnTask = processManagementDao.getTaskInstanceingById(currentTaskInstance.getTask_instance_return());
		// 3.先获得返回任务实例的ID currentTaskInstance.getTask_instance_return()
		// id 是父任务实例ID
		neCurrentTaskInstanceFather = processManagementDao
				.getTaskInstanceingById(currentTaskInstance.getTask_instance_return());
		String id = currentTaskInstance.getTask_instance_father();
		while (true) {
			// 父任务实例
			currentTaskInstanceFather = processManagementDao.getTaskInstanceingById(id);
			// 如果属于返回的任务实例
			if ((currentTaskInstance.getTask_instance_return())
					.equals(currentTaskInstanceFather.getTask_instance_id())) {
				// 将返回的任务实例状态更改为正在进行
				currentTaskInstanceFather.setTask_instance_state(1);
				currentTaskInstanceFather.setTask_instance_gmt_modified(TeamUtil.getStringSecond());
				// 存储任务实例
				flag = processManagementDao.instanceTask(currentTaskInstanceFather);
				if (!flag)
					return -1;
				break;
			}
			// 如果不是返回的任务实例,将状态更改为未开始
			currentTaskInstanceFather.setTask_instance_state(2);
			currentTaskInstanceFather.setTask_instance_gmt_modified(TeamUtil.getStringSecond());
			// 存储任务实例
			flag = processManagementDao.instanceTask(currentTaskInstanceFather);
			if (!flag)
				return -1;
			id = currentTaskInstanceFather.getTask_instance_father();
		}

		// 将记录插入到通知表中
	/*	bysjglxt_notice.setNotice_id(TeamUtil.getUuid());
		bysjglxt_notice.setNotice_launch(currentTaskInstance.getTask_instance_role());
		bysjglxt_notice.setNotice_belong(neCurrentTaskInstanceFather.getTask_instance_role());
		bysjglxt_notice.setNotice_content("已驳回任务");
		bysjglxt_notice.setNotice_state(2);
		bysjglxt_notice.setNotice_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_notice.setNotice_gmt_modified(bysjglxt_notice.getNotice_gmt_create());
		if (!(bysjglxt_notice.getNotice_belong().equals(bysjglxt_notice.getNotice_launch()))) {
			// 存储记录
			processManagementDao.fillNoticeRecord(bysjglxt_notice);
		}*/
		//通知
		bysjglxt_task_definition taskDefinition = new bysjglxt_task_definition();
		if(currentTaskInstance!=null && currentTaskInstance.getTask_instance_task_definition()!=null && !"".equals(currentTaskInstance.getTask_instance_task_definition())) {
			taskDefinition = processManagementDao.getTaskDefinition(currentTaskInstance.getTask_instance_task_definition());
		}
		if(taskDefinition == null) {
			return 1;
		}
		List<StudentInformationDTO> listStudentUser = new ArrayList<>();
		List<TeacherInformationDTO> listTeacherUser = new ArrayList<>();
		bysjglxt_student_user studentUserNotice = new bysjglxt_student_user();
		bysjglxt_student_basic studentBasicNotice = new bysjglxt_student_basic();
		String neirong = "";
		switch(taskDefinition.getTask_definition_name()) {
		case "指导老师审核任务书":
			//先把上面的任务完成
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("tutorShenTaskBook"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(currentTaskInstance.getTask_instance_role(), content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			neirong = ((String)(properties.get("tutorShenTaskBookNotPass")));
			//下发不通过新的通知
			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			//生成关于返回的节点的任务
			neirong = ((String)(properties.get("wanShanTaskBook")));
			sendMessage(currentTaskInstance.getTask_instance_role(),returnTask.getTask_instance_role(),neirong,2,2);
			break;
		case "指导小组组长填写任务书审核意见":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("tutorShenTaskBookPassCollege"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(currentTaskInstance.getTask_instance_role(), content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			//通知学生不通过
			neirong = ((String)(properties.get("collegeShenTaskBookNotPass")));
			//下发学生新的通知
			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			neirong = ((String)(properties.get("wanShanTaskBook"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());;
			//生成新得
			sendMessage(currentTaskInstance.getTask_instance_role(),returnTask.getTask_instance_role(),neirong,2,2);
			break;
		case "指导老师确认开题报告":
			//获取学生的通知，并将这个通知变成已完成---不通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("passStudentUploadReport"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(currentTaskInstance.getTask_instance_role(), content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			neirong = ((String)(properties.get("tutorShenReportNotPass")));
			//下发学生新的通知
			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			//.replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());;
			neirong = ((String)(properties.get("studentUploadReport")));
			//通知下一任务
			sendMessage(studentUserNotice.getUser_student_id(),returnTask.getTask_instance_role(),neirong,2,2);
			break;
		case "指导老师填写形式审查表":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("graduationTeacher"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(currentTaskInstance.getTask_instance_role(), content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			neirong = ((String)(properties.get("graduationTeacherNotPass")));
			//下发学生新的通知
			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			//.replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());;
			neirong = ((String)(properties.get("graduationStudent")));
			//通知下一任务
			sendMessage(studentUserNotice.getUser_student_id(),returnTask.getTask_instance_role(),neirong,2,2);
			break;
		case "指导小组组长填写形式审查表（核查）":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("graduationCheckTeacherPassCollege"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(currentTaskInstance.getTask_instance_role(), content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			neirong = ((String)(properties.get("graduationCollegeNotPass")));
			//下发学生新的通知
			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			//.replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());;
			neirong = ((String)(properties.get("graduationStudent")));
			//通知下一任务
			sendMessage(studentUserNotice.getUser_student_id(),returnTask.getTask_instance_role(),neirong,2,2);
			break;
		case "指导老师填写评价审阅表":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("graduationCheckCollegePassCollege"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(currentTaskInstance.getTask_instance_role(), content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			neirong = ((String)(properties.get("graduationEvaluateTutorNotPass")));
			//下发学生新的通知
			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			//.replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());;
			neirong = ((String)(properties.get("graduationStudent")));
			//通知下一任务
			sendMessage(studentUserNotice.getUser_student_id(),returnTask.getTask_instance_role(),neirong,2,2);
			break;
		case "评阅老师填写评阅审查表":
			//获取学生的通知，并将这个通知变成已完成---通过
			studentUserNotice = processManagementDao.getStudentUser(bysjglxt_process_instance.getProcess_instance_man());
			studentBasicNotice = processManagementDao.getStudentBasicById(studentUserNotice.getUser_student_basic());
			if(studentBasicNotice != null) {
				String content = ((String)(properties.get("graduationReviewTutor"))).replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());
				bysjglxt_notice xiafaTaskNotice = processManagementDao.getNoticeByBelongContentAndLeiXing(currentTaskInstance.getTask_instance_role(), content, 2);
				if(xiafaTaskNotice!=null) {
					xiafaTaskNotice.setNotice_state(1);
					xiafaTaskNotice.setNotice_leixing(1);
					xiafaTaskNotice.setNotice_gmt_modified(TeamUtil.getStringSecond());
					processManagementDao.saveObj(xiafaTaskNotice);
				}
			}
			//下发学生新的通知
			neirong = ((String)(properties.get("graduationReviewTutorNotPass")));
			sendMessage(currentTaskInstance.getTask_instance_role(),studentUserNotice.getUser_student_id(),neirong,4,2);
			//.replaceAll("student_num", studentBasicNotice.getStudent_basic_num()).replaceAll("student_name", studentBasicNotice.getStudent_basic_name());;
			neirong = ((String)(properties.get("graduationStudent")));
			//通知下一任务
			sendMessage(studentUserNotice.getUser_student_id(),returnTask.getTask_instance_role(),neirong,2,2);
		}
		
		
		return 1;
	}

	/**
	 * 
	 */
	@Override
	public bysjglxt_process_instance getProcessInstanceByDefinitionId(String processDefinitionId) {
		return processManagementDao.getProcessInstanceByDefinitionAndState(processDefinitionId, "活动");
	}

	// 获取某个流程正在进行的任务实例
	//
	/*
	 * @Override public ProcessDetailDTO getTasking(String userId) {
	 * bysjglxt_process_instance bysjglxt_process_instance = new
	 * bysjglxt_process_instance(); bysjglxt_task_instance taskInstanceing = new
	 * bysjglxt_task_instance(); ProcessDetailDTO processDetailDTO = new
	 * ProcessDetailDTO(); List<bysjglxt_task_instance> listTaskInstance = new
	 * ArrayList<bysjglxt_task_instance>(); // 根据1.在一个操作者中只有一个流程实例处于正在活动的状态获取流程实例
	 * bysjglxt_process_instance =
	 * processManagementDao.getProcessInstanceByUserAndState(userId); if
	 * (bysjglxt_process_instance == null) return null; // 根据流程实例获取所有属于改流程的任务实例
	 * listTaskInstance = processManagementDao
	 * .getListTaskInstanceByProcessInstanceId(bysjglxt_process_instance.
	 * getProcess_instance_id()); for (bysjglxt_task_instance bysjglxt_task_instance
	 * : listTaskInstance) { // 根据任务实例也只会有一个处于正在进行的状态遍历属于该流程实例的任务实例 if
	 * (bysjglxt_task_instance.getTask_instance_state() == 1) { taskInstanceing =
	 * bysjglxt_task_instance; break; } }
	 * processDetailDTO.setBysjglxtTaskInstance(taskInstanceing);
	 * processDetailDTO.setBysjglxtProcessInstance(bysjglxt_process_instance);
	 * return processDetailDTO; }
	 */

}
