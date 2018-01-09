package com.bysjglxt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.struts2.ServletActionContext;

import com.bysjglxt.dao.GraduationProjectManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_comment;
import com.bysjglxt.domain.DO.bysjglxt_defence;
import com.bysjglxt.domain.DO.bysjglxt_dissertation;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_report_opening;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;
import com.bysjglxt.domain.DO.bysjglxt_taskbook;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.DTO.ProcessBelongDTO;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TaskDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.DTO.TeacherTutorStudentDTO;
import com.bysjglxt.domain.VO.TeacherTutorStudentVO;
import com.bysjglxt.service.GraduationProjectManagementService;

import util.ExcelToBean2;
import util.TeamUtil;
import util.XwpfTUtil;

public class GraduationProjectManagementServiceImpl implements GraduationProjectManagementService {

	private GraduationProjectManagementDao graduationProjectManagementDao;

	public void setGraduationProjectManagementDao(GraduationProjectManagementDao graduationProjectManagementDao) {
		this.graduationProjectManagementDao = graduationProjectManagementDao;
	}

	// 导入评语
	@Override
	public int saveComment(File commentExcel, String EXCEL_CommentFileName, String userId) throws Exception {
		String houzhui = EXCEL_CommentFileName.substring(EXCEL_CommentFileName.lastIndexOf(".") + 1);
		FileInputStream input = new FileInputStream(commentExcel);
		List<Map<String, Object>> list = null;
		if ("xlsx".equals(houzhui)) {
			XSSFWorkbook workbook = new XSSFWorkbook(input);
			list = ExcelToBean2.parseUpdateExcel(workbook, "bysjglxt_comment");
		} else {
			HSSFWorkbook workbook = new HSSFWorkbook(input);
			list = ExcelToBean2.parseExcel(workbook, "bysjglxt_comment");
		}
		List<bysjglxt_comment> lists = ExcelToBean2.toObjectPerproList(list, bysjglxt_comment.class);
		String college = getCollegeByUserId(userId);
		for (bysjglxt_comment comment : lists) {
			comment.setComment_id(TeamUtil.getUuid());
			comment.setComment_college(college);
			comment.setComment_gmt_create(TeamUtil.getStringSecond());
			comment.setComment_gmt_modified(comment.getComment_gmt_create());
			graduationProjectManagementDao.saveObj(comment);
		}
		return 1;
	}

	// 根据使用者Id获取学院
	public String getCollegeByUserId(String userId) {
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_teacher_user = graduationProjectManagementDao.getTeacherUserByUserId(userId);
		if (bysjglxt_teacher_user.getUser_teacher_belong_college() != null
				&& bysjglxt_teacher_user.getUser_teacher_belong_college().trim().length() >= 0) {
			return bysjglxt_teacher_user.getUser_teacher_belong_college().trim();
		}
		return null;
	}

	/**
	 * 保存毕业论文
	 * 
	 * @throws IOException
	 */
	@Override
	public int saveDissertation(File file, String oldFileName, String userId, String newFileName) throws IOException {
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			System.out.println("获取初始路径失败");
			e.printStackTrace();
		}
		boolean flag = false;
		String path = "";
		bysjglxt_dissertation bysjglxt_dissertation = new bysjglxt_dissertation();
		// 如果新文件为空
		if (file == null) {
			path = lj + "graduagtionThesi/";
			// 判断旧文件是否处于空的状态
			if ("".equals(oldFileName) || !(oldFileName.trim().length() > 0)) {
				// 如果是空
				// 判断：在数据库中属于这个学生的毕业论文是否存在
				bysjglxt_dissertation = graduationProjectManagementDao.getThesisByStudent(userId);
				if (bysjglxt_dissertation != null && bysjglxt_dissertation.getDissertation_student() != null
						&& bysjglxt_dissertation.getDissertation_student().trim().length() > 0) {
					// 如果存在,则将原有毕业论文删除
					// 先进行删除
					// 删除学生上传的文件
					path = path + bysjglxt_dissertation.getDissertation_id() + "_"
							+ bysjglxt_dissertation.getDissertation_file();
					File deleteFile = new File(path);
					deleteFile.delete();
					flag = graduationProjectManagementDao.deleteThesisByUserId(userId);
					return 1;
				} else {
					// 如果不存在,不进行任何操作
					return 1;
				}
			} else {
				// 如果不是空
				// 不进行操作
				return 1;
			}
		} else {
			path = lj + "graduagtionThesi/";
			// 如果新文件存在
			bysjglxt_dissertation = graduationProjectManagementDao.getThesisByStudent(userId);
			// 1.判断是否有属于这个学生的毕业论文存在
			if (bysjglxt_dissertation != null && bysjglxt_dissertation.getDissertation_student() != null
					&& bysjglxt_dissertation.getDissertation_student().trim().length() > 0) {
				// 如果存在
				// 先进行删除
				// 删除学生上传的文件
				path = path + bysjglxt_dissertation.getDissertation_id() + "_"
						+ bysjglxt_dissertation.getDissertation_file();
				File deleteFile = new File(path);
				deleteFile.delete();
				// 删除学生毕业论文记录
				flag = graduationProjectManagementDao.deleteThesisByUserId(userId);
				if (!flag) {
					return -1;
				}
			}
			path = lj + "graduagtionThesi/";
			// 保存毕业论文
			// 1.上传毕业论文
			bysjglxt_dissertation = new bysjglxt_dissertation();
			bysjglxt_dissertation = graduationProjectManagementDao.getThesisByStudentId(userId);
			if (bysjglxt_dissertation != null) {
				path = path + bysjglxt_dissertation.getDissertation_id() + "_" + newFileName;
				File newFile = new File(path);
				FileUtils.copyFile(file, newFile);
				// 存储数据到数据库
				bysjglxt_dissertation.setDissertation_file(newFileName);
				bysjglxt_dissertation.setDissertation_gmt_modified(bysjglxt_dissertation.getDissertation_gmt_create());
				flag = graduationProjectManagementDao.fillEmptyThesisRecord(bysjglxt_dissertation);
				if (!flag)
					return -2;
			} else {
				return -2;
			}
		}
		return 1;
	}

	// 下载毕业论文
	@Override
	public File downloadDissertation(String userId) {
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			System.out.println("获取初始路径失败");
			e.printStackTrace();
		}
		// 1.根据user Id获得学生毕业论文表中的记录
		bysjglxt_dissertation bysjglxt_dissertation = new bysjglxt_dissertation();
		String path = lj + "graduagtionThesi/";
		bysjglxt_dissertation = graduationProjectManagementDao.getThesisByStudent(userId);
		if (bysjglxt_dissertation == null) {
			return null;
		}
		path = path + bysjglxt_dissertation.getDissertation_id() + "_" + bysjglxt_dissertation.getDissertation_file();
		File file = new File(path);
		return file;
	}

	/**
	 * 我管理的毕业设计
	 * 
	 */
	@Override
	public TeacherTutorStudentVO teacherManagementStudentVO(TeacherTutorStudentVO teacherTutorStudentVO,
			String teacherUserId) {
		List<TeacherTutorStudentDTO> list_TeacherTutorStudentDTO = new ArrayList<TeacherTutorStudentDTO>();
		TeacherTutorStudentDTO teacherTutorStudentDTO = new TeacherTutorStudentDTO();
		TaskDTO taskDTO = new TaskDTO();
		ProcessBelongDTO processBelongDTO = null;
		TeacherInformationDTO teacherInformationDTO = new TeacherInformationDTO();
		bysjglxt_task_definition taskDefinition = new bysjglxt_task_definition();
		bysjglxt_task_instance taskInstance = new bysjglxt_task_instance();
		bysjglxt_student_basic bysjglxtStudentBasic = new bysjglxt_student_basic();
		bysjglxt_section bysjglxt_section = new bysjglxt_section();
		bysjglxt_student_user bysjglxtStudentUser = new bysjglxt_student_user();
		bysjglxt_topic bysjglxtTopic = new bysjglxt_topic();
		bysjglxt_teacher_basic bysjglxt_teacher_basic = null;
		StudentInformationDTO studentInformationDTO = new StudentInformationDTO();
		List<bysjglxt_process_instance> listProcessInstance = new ArrayList<bysjglxt_process_instance>();
		bysjglxt_process_definition bysjglxt_process_definition = new bysjglxt_process_definition();
		bysjglxt_process_instance processInstance = new bysjglxt_process_instance();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_teacher_user teacherUser = null;
		List<bysjglxt_topic_select> list_bysjglxt_topic_select = new ArrayList<bysjglxt_topic_select>();
		List<bysjglxt_topic_select> list_Allbysjglxt_topic_select = new ArrayList<bysjglxt_topic_select>();
		// 判断老师的身份
		String actor = "";
		String section = "";
		// 1.判断老师是不是领导小组角色
		bysjglxt_teacher_user = graduationProjectManagementDao.getTeacherUserByUserId(teacherUserId);
		if (bysjglxt_teacher_user != null) {
			if (bysjglxt_teacher_user.getUser_teacher_is_college_admin() == 1) {
				actor = "系部管理员";
			} else {
				// 根据教研室主任userId获取教研室
				bysjglxt_section = graduationProjectManagementDao.getSectionByUserId(teacherUserId);
				if (bysjglxt_section != null) {
					actor = "教研室主任";
					section = bysjglxt_section.getSection_id();
				} else {
					// 1.判断老师是不是记录员或答辩小组长
					// 根据userId获取teacherUser表信息
					bysjglxt_teacher_user = graduationProjectManagementDao.getTeacherUserByUserId(teacherUserId);
					if (bysjglxt_teacher_user.getUser_teacher_is_recorder() == 1) {
						actor = "记录员";
					} else {
						if (bysjglxt_teacher_user.getUser_teacher_is_defence_leader() == 1) {
							actor = "答辩小组长";
						} else {
							actor = "无";
						}
					}
				}
			}
			System.out.println(actor);
		} else {
			return teacherTutorStudentVO;
		}

		// 获得总记录数
		list_Allbysjglxt_topic_select = graduationProjectManagementDao.getTeacherTutorStudentAllSelectTopic(
				teacherTutorStudentVO, teacherUserId, actor, section,
				bysjglxt_teacher_user.getUser_teacher_belong_college());
		int i = list_Allbysjglxt_topic_select.size();
		teacherTutorStudentVO.setTotalRecords(i);
		teacherTutorStudentVO.setTotalPages(((i - 1) / teacherTutorStudentVO.getPageSize()) + 1);
		if (teacherTutorStudentVO.getPageIndex() <= 1) {
			teacherTutorStudentVO.setHavePrePage(false);
		} else {
			teacherTutorStudentVO.setHavePrePage(true);
		}
		if (teacherTutorStudentVO.getPageIndex() >= teacherTutorStudentVO.getTotalPages()) {
			teacherTutorStudentVO.setHaveNextPage(false);
		} else {
			teacherTutorStudentVO.setHaveNextPage(true);
		}
		// 1.根据教师ID筛选出符合条件的最多10条选题数据
		list_bysjglxt_topic_select = graduationProjectManagementDao.getTeacherTutorStudentSelectTopicByPage(
				teacherTutorStudentVO, teacherUserId, actor, section,
				bysjglxt_teacher_user.getUser_teacher_belong_college());
		// 2.遍历选题拿到学生userId信息
		for (bysjglxt_topic_select bysjglxt_topic_select : list_bysjglxt_topic_select) {
			processBelongDTO = new ProcessBelongDTO();
			bysjglxt_teacher_basic = new bysjglxt_teacher_basic();
			teacherUser = new bysjglxt_teacher_user();
			teacherInformationDTO = new TeacherInformationDTO();
			taskDTO = new TaskDTO();
			taskDefinition = new bysjglxt_task_definition();
			bysjglxtStudentUser = new bysjglxt_student_user();
			bysjglxtStudentBasic = new bysjglxt_student_basic();
			taskInstance = new bysjglxt_task_instance();
			teacherTutorStudentDTO = new TeacherTutorStudentDTO();
			processInstance = new bysjglxt_process_instance();
			bysjglxtTopic = new bysjglxt_topic();
			studentInformationDTO = new StudentInformationDTO();
			if (bysjglxt_topic_select.getTopic_select_teacher_review() != null
					&& bysjglxt_topic_select.getTopic_select_teacher_review().trim().length() > 0) {
				// 添加评阅老师信息
				// 获取teacherUserbyId
				teacherUser = graduationProjectManagementDao
						.getTeacherUserByUserId(bysjglxt_topic_select.getTopic_select_teacher_review().trim());
				if (teacherUser != null) {
					if (teacherUser.getUser_teacher_basic() != null
							&& teacherUser.getUser_teacher_basic().trim().length() > 0) {
						bysjglxt_teacher_basic = graduationProjectManagementDao
								.getTeacherBasicByBasicId(teacherUser.getUser_teacher_basic().trim());
						if (bysjglxt_teacher_basic != null) {
							teacherInformationDTO.setBysjglxtTeacherBasic(bysjglxt_teacher_basic);
						}
						teacherInformationDTO.setBysjglxtTeacherUser(teacherUser);
					}
				}
			}

			// 3.根据选题所属学生拿到学生user表
			bysjglxtStudentUser = graduationProjectManagementDao
					.getStudentUserByUserId(bysjglxt_topic_select.getTopic_select_student());
			if (bysjglxtStudentUser != null) {
				// 变颜色
				if (teacherTutorStudentVO.getSearch() != null
						&& teacherTutorStudentVO.getSearch().trim().length() > 0) {
					bysjglxtStudentUser.setUser_student_num(bysjglxtStudentUser.getUser_student_num().replaceAll(
							teacherTutorStudentVO.getSearch(),
							"<span style='color: #ff5063;'>" + teacherTutorStudentVO.getSearch().trim() + "</span>"));
				}
				// 根据学生basicId获取学生basic表
				bysjglxtStudentBasic = graduationProjectManagementDao
						.getStudentBasicByBasicId(bysjglxtStudentUser.getUser_student_basic());
				if (bysjglxtStudentBasic != null) {
					studentInformationDTO.setBysjglxtStudentBasic(bysjglxtStudentBasic);
					studentInformationDTO.setBysjglxtStudentUser(bysjglxtStudentUser);
				}
				// 根据学生userId获取该学生所有的流程实例记录
				listProcessInstance = graduationProjectManagementDao
						.getProcessInstanceByMan(bysjglxt_topic_select.getTopic_select_student());
				// 遍历
				for (bysjglxt_process_instance bysjglxt_process_instance : listProcessInstance) {
					bysjglxt_process_definition = new bysjglxt_process_definition();
					// 根据流程定义ID获取流程定义表
					bysjglxt_process_definition = graduationProjectManagementDao.getProcessDefinitionByID(
							bysjglxt_process_instance.getProcess_instance_process_definition());
					if (bysjglxt_process_definition != null) {
						if ("毕业设计流程".equals(bysjglxt_process_definition.getProcess_definition_name())) {
							processInstance = bysjglxt_process_instance;
							processBelongDTO.setBysjglxt_process_definition(bysjglxt_process_definition);
							processBelongDTO.setBysjglxt_process_instance(processInstance);
							break;
						}
					}
				}
				if (processInstance != null) {
					// 一个流程实例中只有一个任务实例是处于正在进行的状态
					// 根据流程实例ID以及任务实例状态即可判断得到流程进度
					taskInstance = graduationProjectManagementDao
							.getTaskInstanceByProcessInstanceId(processInstance.getProcess_instance_id());
					if (taskInstance != null) {
						// 根据任务实例所属任务定义ID获取任务定义
						taskDefinition = graduationProjectManagementDao
								.getTaskDefinition(taskInstance.getTask_instance_task_definition());
						taskDTO.setTaskDefinition(taskDefinition);
						taskDTO.setTaskInstance(taskInstance);
					}
				}
			} else {
				System.out.println("学生user表为空");
			}
			teacherTutorStudentDTO.setReviewTeacher(teacherInformationDTO);
			teacherTutorStudentDTO.setProcessBelongDTO(processBelongDTO);
			teacherTutorStudentDTO.setStudentInformationDTO(studentInformationDTO);
			teacherTutorStudentDTO.setTaskDTO(taskDTO);
			// 根据选题所属课题拿到课题表信息
			bysjglxtTopic = graduationProjectManagementDao
					.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
			if (bysjglxtTopic != null) {
				teacherTutorStudentDTO.setBysjglxtTopic(bysjglxtTopic);
			}
			list_TeacherTutorStudentDTO.add(teacherTutorStudentDTO);
		}
		teacherTutorStudentVO.setList_TeacherTutorStudentDTO(list_TeacherTutorStudentDTO);
		return teacherTutorStudentVO;
	}

	@Override
	public TeacherTutorStudentVO teacherReviewStudentVO(TeacherTutorStudentVO teacherTutorStudentVO,
			String teacherUserId) {
		List<TeacherTutorStudentDTO> list_TeacherTutorStudentDTO = new ArrayList<TeacherTutorStudentDTO>();
		TeacherTutorStudentDTO teacherTutorStudentDTO = new TeacherTutorStudentDTO();
		TaskDTO taskDTO = new TaskDTO();
		ProcessBelongDTO processBelongDTO = null;
		bysjglxt_task_definition taskDefinition = new bysjglxt_task_definition();
		bysjglxt_task_instance taskInstance = new bysjglxt_task_instance();
		bysjglxt_student_basic bysjglxtStudentBasic = new bysjglxt_student_basic();
		bysjglxt_student_user bysjglxtStudentUser = new bysjglxt_student_user();
		bysjglxt_topic bysjglxtTopic = new bysjglxt_topic();
		StudentInformationDTO studentInformationDTO = new StudentInformationDTO();
		List<bysjglxt_process_instance> listProcessInstance = new ArrayList<bysjglxt_process_instance>();
		bysjglxt_process_definition bysjglxt_process_definition = new bysjglxt_process_definition();
		bysjglxt_process_instance processInstance = new bysjglxt_process_instance();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		List<bysjglxt_topic_select> list_bysjglxt_topic_select = new ArrayList<bysjglxt_topic_select>();
		List<bysjglxt_topic_select> list_Allbysjglxt_topic_select = new ArrayList<bysjglxt_topic_select>();
		// 判断老师的身份
		String actor = "评阅老师";
		String section = "";
		bysjglxt_teacher_user = graduationProjectManagementDao.getTeacherUserByUserId(teacherUserId);
		// 获得总记录数
		list_Allbysjglxt_topic_select = graduationProjectManagementDao.getTeacherTutorStudentAllSelectTopic(
				teacherTutorStudentVO, teacherUserId, actor, section,
				bysjglxt_teacher_user.getUser_teacher_belong_college());
		int i = list_Allbysjglxt_topic_select.size();
		teacherTutorStudentVO.setTotalRecords(i);
		teacherTutorStudentVO.setTotalPages(((i - 1) / teacherTutorStudentVO.getPageSize()) + 1);
		if (teacherTutorStudentVO.getPageIndex() <= 1) {
			teacherTutorStudentVO.setHavePrePage(false);
		} else {
			teacherTutorStudentVO.setHavePrePage(true);
		}
		if (teacherTutorStudentVO.getPageIndex() >= teacherTutorStudentVO.getTotalPages()) {
			teacherTutorStudentVO.setHaveNextPage(false);
		} else {
			teacherTutorStudentVO.setHaveNextPage(true);
		}
		// 1.根据教师ID筛选出符合条件的最多10条选题数据
		list_bysjglxt_topic_select = graduationProjectManagementDao.getTeacherTutorStudentSelectTopicByPage(
				teacherTutorStudentVO, teacherUserId, actor, section,
				bysjglxt_teacher_user.getUser_teacher_belong_college());
		// 2.遍历选题拿到学生userId信息
		for (bysjglxt_topic_select bysjglxt_topic_select : list_bysjglxt_topic_select) {
			processBelongDTO = new ProcessBelongDTO();
			taskDTO = new TaskDTO();
			taskDefinition = new bysjglxt_task_definition();
			bysjglxtStudentUser = new bysjglxt_student_user();
			bysjglxtStudentBasic = new bysjglxt_student_basic();
			taskInstance = new bysjglxt_task_instance();
			teacherTutorStudentDTO = new TeacherTutorStudentDTO();
			processInstance = new bysjglxt_process_instance();
			bysjglxtTopic = new bysjglxt_topic();
			studentInformationDTO = new StudentInformationDTO();
			// 3.根据选题所属学生拿到学生user表
			bysjglxtStudentUser = graduationProjectManagementDao
					.getStudentUserByUserId(bysjglxt_topic_select.getTopic_select_student());

			if (bysjglxtStudentUser != null) {
				// 变颜色
				if (teacherTutorStudentVO.getSearch() != null
						&& teacherTutorStudentVO.getSearch().trim().length() > 0) {
					bysjglxtStudentUser.setUser_student_num(bysjglxtStudentUser.getUser_student_num().replaceAll(
							teacherTutorStudentVO.getSearch(),
							"<span style='color: #ff5063;'>" + teacherTutorStudentVO.getSearch().trim() + "</span>"));
				}
				// 根据学生basicId获取学生basic表
				bysjglxtStudentBasic = graduationProjectManagementDao
						.getStudentBasicByBasicId(bysjglxtStudentUser.getUser_student_basic());
				if (bysjglxtStudentBasic != null) {
					studentInformationDTO.setBysjglxtStudentBasic(bysjglxtStudentBasic);
					studentInformationDTO.setBysjglxtStudentUser(bysjglxtStudentUser);
				}
				// 根据学生userId获取该学生所有的流程实例记录
				listProcessInstance = graduationProjectManagementDao
						.getProcessInstanceByMan(bysjglxt_topic_select.getTopic_select_student());
				// 遍历
				for (bysjglxt_process_instance bysjglxt_process_instance : listProcessInstance) {
					bysjglxt_process_definition = new bysjglxt_process_definition();
					// 根据流程定义ID获取流程定义表
					bysjglxt_process_definition = graduationProjectManagementDao.getProcessDefinitionByID(
							bysjglxt_process_instance.getProcess_instance_process_definition());
					if (bysjglxt_process_definition != null) {
						if ("毕业设计流程".equals(bysjglxt_process_definition.getProcess_definition_name())) {
							processInstance = bysjglxt_process_instance;
							processBelongDTO.setBysjglxt_process_definition(bysjglxt_process_definition);
							processBelongDTO.setBysjglxt_process_instance(processInstance);
							break;
						}
					}
				}
				if (processInstance != null) {
					// 一个流程实例中只有一个任务实例是处于正在进行的状态
					// 根据流程实例ID以及任务实例状态即可判断得到流程进度
					taskInstance = graduationProjectManagementDao
							.getTaskInstanceByProcessInstanceId(processInstance.getProcess_instance_id());
					if (taskInstance != null) {
						// 根据任务实例所属任务定义ID获取任务定义
						taskDefinition = graduationProjectManagementDao
								.getTaskDefinition(taskInstance.getTask_instance_task_definition());
						taskDTO.setTaskDefinition(taskDefinition);
						taskDTO.setTaskInstance(taskInstance);
					}
				}
			} else {
				System.out.println("学生user表为空");
			}
			teacherTutorStudentDTO.setProcessBelongDTO(processBelongDTO);
			teacherTutorStudentDTO.setStudentInformationDTO(studentInformationDTO);
			teacherTutorStudentDTO.setTaskDTO(taskDTO);
			// 根据选题所属课题拿到课题表信息
			bysjglxtTopic = graduationProjectManagementDao
					.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
			if (bysjglxtTopic != null) {
				teacherTutorStudentDTO.setBysjglxtTopic(bysjglxtTopic);
			}

			list_TeacherTutorStudentDTO.add(teacherTutorStudentDTO);
		}
		teacherTutorStudentVO.setList_TeacherTutorStudentDTO(list_TeacherTutorStudentDTO);
		return teacherTutorStudentVO;
	}

	/**
	 * 我指导的毕业设计
	 */
	@Override
	public TeacherTutorStudentVO teacherTutorStudentVO(TeacherTutorStudentVO teacherTutorStudentVO,
			String teacherUserId) {
		List<TeacherTutorStudentDTO> list_TeacherTutorStudentDTO = new ArrayList<TeacherTutorStudentDTO>();
		TeacherTutorStudentDTO teacherTutorStudentDTO = new TeacherTutorStudentDTO();
		TaskDTO taskDTO = new TaskDTO();
		ProcessBelongDTO processBelongDTO = null;
		bysjglxt_task_definition taskDefinition = new bysjglxt_task_definition();
		bysjglxt_task_instance taskInstance = new bysjglxt_task_instance();
		bysjglxt_student_basic bysjglxtStudentBasic = new bysjglxt_student_basic();
		bysjglxt_student_user bysjglxtStudentUser = new bysjglxt_student_user();
		bysjglxt_topic bysjglxtTopic = new bysjglxt_topic();
		List<bysjglxt_topic_select> listTopicSelect = new ArrayList<bysjglxt_topic_select>();
		StudentInformationDTO studentInformationDTO = new StudentInformationDTO();
		List<bysjglxt_process_instance> listProcessInstance = new ArrayList<bysjglxt_process_instance>();
		bysjglxt_process_definition bysjglxt_process_definition = new bysjglxt_process_definition();
		bysjglxt_process_instance processInstance = new bysjglxt_process_instance();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		List<bysjglxt_topic_select> list_bysjglxt_topic_select = new ArrayList<bysjglxt_topic_select>();
		List<bysjglxt_topic_select> list_Allbysjglxt_topic_select = new ArrayList<bysjglxt_topic_select>();
		// 判断老师的身份
		String actor = "";
		String section = "";
		// 1.判断老师是否是指导老师
		listTopicSelect = graduationProjectManagementDao.getSelectTopicByTutorId(teacherUserId);
		if (listTopicSelect.size() > 0) {
			actor = "指导教师";
		} else {
			actor = "无";
		}
		bysjglxt_teacher_user = graduationProjectManagementDao.getTeacherUserByUserId(teacherUserId);
		// 获得总记录数
		list_Allbysjglxt_topic_select = graduationProjectManagementDao.getTeacherTutorStudentAllSelectTopic(
				teacherTutorStudentVO, teacherUserId, actor, section,
				bysjglxt_teacher_user.getUser_teacher_belong_college());
		int i = list_Allbysjglxt_topic_select.size();
		teacherTutorStudentVO.setTotalRecords(i);
		teacherTutorStudentVO.setTotalPages(((i - 1) / teacherTutorStudentVO.getPageSize()) + 1);
		if (teacherTutorStudentVO.getPageIndex() <= 1) {
			teacherTutorStudentVO.setHavePrePage(false);
		} else {
			teacherTutorStudentVO.setHavePrePage(true);
		}
		if (teacherTutorStudentVO.getPageIndex() >= teacherTutorStudentVO.getTotalPages()) {
			teacherTutorStudentVO.setHaveNextPage(false);
		} else {
			teacherTutorStudentVO.setHaveNextPage(true);
		}
		// 1.根据教师ID筛选出符合条件的最多10条选题数据
		list_bysjglxt_topic_select = graduationProjectManagementDao.getTeacherTutorStudentSelectTopicByPage(
				teacherTutorStudentVO, teacherUserId, actor, section,
				bysjglxt_teacher_user.getUser_teacher_belong_college());
		// 2.遍历选题拿到学生userId信息
		for (bysjglxt_topic_select bysjglxt_topic_select : list_bysjglxt_topic_select) {
			processBelongDTO = new ProcessBelongDTO();
			taskDTO = new TaskDTO();
			taskDefinition = new bysjglxt_task_definition();
			bysjglxtStudentUser = new bysjglxt_student_user();
			bysjglxtStudentBasic = new bysjglxt_student_basic();
			taskInstance = new bysjglxt_task_instance();
			teacherTutorStudentDTO = new TeacherTutorStudentDTO();
			processInstance = new bysjglxt_process_instance();
			bysjglxtTopic = new bysjglxt_topic();
			studentInformationDTO = new StudentInformationDTO();
			// 3.根据选题所属学生拿到学生user表
			bysjglxtStudentUser = graduationProjectManagementDao
					.getStudentUserByUserId(bysjglxt_topic_select.getTopic_select_student());

			if (bysjglxtStudentUser != null) {
				// 变颜色
				if (teacherTutorStudentVO.getSearch() != null
						&& teacherTutorStudentVO.getSearch().trim().length() > 0) {
					bysjglxtStudentUser.setUser_student_num(bysjglxtStudentUser.getUser_student_num().replaceAll(
							teacherTutorStudentVO.getSearch(),
							"<span style='color: #ff5063;'>" + teacherTutorStudentVO.getSearch().trim() + "</span>"));
				}
				// 根据学生basicId获取学生basic表
				bysjglxtStudentBasic = graduationProjectManagementDao
						.getStudentBasicByBasicId(bysjglxtStudentUser.getUser_student_basic());
				if (bysjglxtStudentBasic != null) {
					studentInformationDTO.setBysjglxtStudentBasic(bysjglxtStudentBasic);
					studentInformationDTO.setBysjglxtStudentUser(bysjglxtStudentUser);
				}
				// 根据学生userId获取该学生所有的流程实例记录
				listProcessInstance = graduationProjectManagementDao
						.getProcessInstanceByMan(bysjglxt_topic_select.getTopic_select_student());
				// 遍历
				for (bysjglxt_process_instance bysjglxt_process_instance : listProcessInstance) {
					bysjglxt_process_definition = new bysjglxt_process_definition();
					// 根据流程定义ID获取流程定义表
					bysjglxt_process_definition = graduationProjectManagementDao.getProcessDefinitionByID(
							bysjglxt_process_instance.getProcess_instance_process_definition());
					if (bysjglxt_process_definition != null) {
						if ("毕业设计流程".equals(bysjglxt_process_definition.getProcess_definition_name())) {
							processInstance = bysjglxt_process_instance;
							processBelongDTO.setBysjglxt_process_definition(bysjglxt_process_definition);
							processBelongDTO.setBysjglxt_process_instance(processInstance);
							break;
						}
					}
				}
				if (processInstance != null) {
					// 一个流程实例中只有一个任务实例是处于正在进行的状态
					// 根据流程实例ID以及任务实例状态即可判断得到流程进度
					taskInstance = graduationProjectManagementDao
							.getTaskInstanceByProcessInstanceId(processInstance.getProcess_instance_id());
					if (taskInstance != null) {
						// 根据任务实例所属任务定义ID获取任务定义
						taskDefinition = graduationProjectManagementDao
								.getTaskDefinition(taskInstance.getTask_instance_task_definition());
						taskDTO.setTaskDefinition(taskDefinition);
						taskDTO.setTaskInstance(taskInstance);
					}
				}
			} else {
				System.out.println("学生user表为空");
			}
			teacherTutorStudentDTO.setProcessBelongDTO(processBelongDTO);
			teacherTutorStudentDTO.setStudentInformationDTO(studentInformationDTO);
			teacherTutorStudentDTO.setTaskDTO(taskDTO);
			// 根据选题所属课题拿到课题表信息
			bysjglxtTopic = graduationProjectManagementDao
					.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
			/*
			 * if (teacherTutorStudentVO.getSearch() != null &&
			 * teacherTutorStudentVO.getSearch().trim().length() > 0) {
			 * bysjglxtTopic.setTopic_name_chinese(bysjglxtTopic.
			 * getTopic_name_chinese().replaceAll(
			 * teacherTutorStudentVO.getSearch(),
			 * "<span style='color: #ff5063;'>" +
			 * teacherTutorStudentVO.getSearch().trim() + "</span>")); }
			 */
			if (bysjglxtTopic != null) {
				teacherTutorStudentDTO.setBysjglxtTopic(bysjglxtTopic);
			}

			list_TeacherTutorStudentDTO.add(teacherTutorStudentDTO);
		}
		teacherTutorStudentVO.setList_TeacherTutorStudentDTO(list_TeacherTutorStudentDTO);
		return teacherTutorStudentVO;
	}

	/**
	 * 1 成功 2失败
	 */
	@Override
	public int startGraduationProjectProcess(String studentId) {
		if (studentId == null || studentId.trim().length() <= 0) {
			return 0;
		}
		int flag = 1;
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		bysjglxt_report_opening bysjglxt_report_opening = new bysjglxt_report_opening();
		bysjglxt_record_progress bysjglxt_record_progressEarlystage = new bysjglxt_record_progress();
		bysjglxt_record_progress bysjglxt_record_progressMetaphase = new bysjglxt_record_progress();
		bysjglxt_dissertation bysjglxt_dissertation = new bysjglxt_dissertation();
		bysjglxt_record_progress bysjglxt_record_progressLaterstage = new bysjglxt_record_progress();
		bysjglxt_record_progress bysjglxt_record_progressPerfect = new bysjglxt_record_progress();
		bysjglxt_summary bysjglxt_summary = new bysjglxt_summary();
		bysjglxt_examination_formal bysjglxt_examination_formal = new bysjglxt_examination_formal();
		bysjglxt_evaluate_tutor bysjglxt_evaluate_tutor = new bysjglxt_evaluate_tutor();
		bysjglxt_evaluate_review bysjglxt_evaluate_review = new bysjglxt_evaluate_review();
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_taskbook.setTaskbook_id(TeamUtil.getUuid());
		bysjglxt_taskbook.setBysjglxt_taskbook_student(studentId);
		bysjglxt_taskbook.setTaskbook_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_taskbook.setTaskbook_gmt_modified(bysjglxt_taskbook.getTaskbook_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInTaskBook(bysjglxt_taskbook);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_report_opening.setReport_opening_id(TeamUtil.getUuid());
		bysjglxt_report_opening.setReport_opening_student(studentId);
		bysjglxt_report_opening.setReport_opening_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_report_opening.setReport_opening_gmt_modified(bysjglxt_report_opening.getReport_opening_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInOpening(bysjglxt_report_opening);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_record_progressEarlystage.setRecord_progress_id(TeamUtil.getUuid());
		bysjglxt_record_progressEarlystage.setRecord_progress_student(studentId);
		bysjglxt_record_progressEarlystage.setRecord_progress_stage("前期");
		bysjglxt_record_progressEarlystage.setRecord_progress_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_record_progressEarlystage
				.setRecord_progress_gmt_modified(bysjglxt_record_progressEarlystage.getRecord_progress_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progressEarlystage);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_record_progressMetaphase.setRecord_progress_id(TeamUtil.getUuid());
		bysjglxt_record_progressMetaphase.setRecord_progress_student(studentId);
		bysjglxt_record_progressMetaphase.setRecord_progress_stage("中期");
		bysjglxt_record_progressMetaphase.setRecord_progress_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_record_progressMetaphase
				.setRecord_progress_gmt_modified(bysjglxt_record_progressMetaphase.getRecord_progress_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progressMetaphase);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_record_progressLaterstage.setRecord_progress_id(TeamUtil.getUuid());
		bysjglxt_record_progressLaterstage.setRecord_progress_student(studentId);
		bysjglxt_record_progressLaterstage.setRecord_progress_stage("后期");
		bysjglxt_record_progressLaterstage.setRecord_progress_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_record_progressLaterstage
				.setRecord_progress_gmt_modified(bysjglxt_record_progressLaterstage.getRecord_progress_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progressLaterstage);
		if (flag == 2) {
			return flag;
		}

		bysjglxt_record_progressPerfect.setRecord_progress_id(TeamUtil.getUuid());
		bysjglxt_record_progressPerfect.setRecord_progress_student(studentId);
		bysjglxt_record_progressPerfect.setRecord_progress_stage("完善");
		bysjglxt_record_progressPerfect.setRecord_progress_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_record_progressPerfect
				.setRecord_progress_gmt_modified(bysjglxt_record_progressPerfect.getRecord_progress_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progressPerfect);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_dissertation.setDissertation_id(TeamUtil.getUuid());
		bysjglxt_dissertation.setDissertation_student(studentId);
		bysjglxt_dissertation.setDissertation_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_dissertation.setDissertation_gmt_modified(bysjglxt_dissertation.getDissertation_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInDissertation(bysjglxt_dissertation);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_summary.setSummary_id(TeamUtil.getUuid());
		bysjglxt_summary.setSummary_student(studentId);
		bysjglxt_summary.setSummary_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_summary.setSummary_gmt_modified(TeamUtil.getStringSecond());
		flag = graduationProjectManagementDao.fillEmptyInSummary(bysjglxt_summary);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_examination_formal.setExamination_formal_id(TeamUtil.getUuid());
		bysjglxt_examination_formal.setExamination_formal_student(studentId);
		bysjglxt_examination_formal.setExamination_formal_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_examination_formal.setExamination_formal_gmt_modified(TeamUtil.getStringSecond());
		flag = graduationProjectManagementDao.fillEmptyInExaminationFormal(bysjglxt_examination_formal);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_evaluate_tutor.setEvaluate_tutor_id(TeamUtil.getUuid());
		bysjglxt_evaluate_tutor.setEvaluate_tutor_student(studentId);
		bysjglxt_evaluate_tutor.setEvaluate_tutor_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_evaluate_tutor.setEvaluate_tutor_gmt_modified(bysjglxt_evaluate_tutor.getEvaluate_tutor_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyInEvaluateTutor(bysjglxt_evaluate_tutor);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_evaluate_review.setEvaluate_review_id(TeamUtil.getUuid());
		bysjglxt_evaluate_review.setEvaluate_review_student(studentId);
		bysjglxt_evaluate_review.setEvaluate_review_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_evaluate_review
				.setEvaluate_review_gmt_modified(bysjglxt_evaluate_review.getEvaluate_review_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyEvaluateReview(bysjglxt_evaluate_review);
		if (flag == 2) {
			return flag;
		}
		bysjglxt_defence.setDefence_id(TeamUtil.getUuid());
		bysjglxt_defence.setDefence_student(studentId);
		bysjglxt_defence.setDefence_gmt_create(TeamUtil.getStringSecond());
		bysjglxt_defence.setDefence_gmt_modified(bysjglxt_defence.getDefence_gmt_create());
		flag = graduationProjectManagementDao.fillEmptyDefence(bysjglxt_defence);
		return flag;

	}

	/**
	 * 学生更改任务书
	 */
	@Override
	public int updateTeacherTaskbook(bysjglxt_taskbook updateTaskbook) {
		int flag = 2;
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		bysjglxt_taskbook = graduationProjectManagementDao.getTaskbookById(updateTaskbook.getTaskbook_id());
		if (bysjglxt_taskbook != null) {
			bysjglxt_taskbook.setTaskbook_acontent_required(updateTaskbook.getTaskbook_acontent_required());
			bysjglxt_taskbook.setTaskbook_reference(updateTaskbook.getTaskbook_reference());
			bysjglxt_taskbook.setTaskbook_plan(updateTaskbook.getTaskbook_plan());
			bysjglxt_taskbook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInTaskBook(bysjglxt_taskbook);
		}
		return flag;
	}

	/**
	 * 教研室主任更改任务书
	 */
	@Override
	public int updateSectionTaskbook(bysjglxt_taskbook updateTaskbook) {
		int flag = 2;
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		bysjglxt_taskbook = graduationProjectManagementDao.getTaskbookById(updateTaskbook.getTaskbook_id());
		if (bysjglxt_taskbook != null) {
			bysjglxt_taskbook.setTaskbook_opinion(updateTaskbook.getTaskbook_opinion());
			bysjglxt_taskbook.setTaskbook_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInTaskBook(bysjglxt_taskbook);
		}
		return flag;
	}

	/**
	 * 学生更改开题报告
	 */
	@Override
	public int updateReportOpening(bysjglxt_report_opening updateReportOpening) {
		int flag = 2;
		bysjglxt_report_opening bysjglxt_report_opening = new bysjglxt_report_opening();
		bysjglxt_report_opening = graduationProjectManagementDao
				.getReportOpening(updateReportOpening.getReport_opening_id());
		if (bysjglxt_report_opening != null) {
			bysjglxt_report_opening
					.setReport_opening_documentary_survey(updateReportOpening.getReport_opening_documentary_survey());
			bysjglxt_report_opening.setReport_opening_main(updateReportOpening.getReport_opening_main());
			bysjglxt_report_opening.setReport_opening_detail(updateReportOpening.getReport_opening_detail());
			bysjglxt_report_opening.setReport_opening_reference(updateReportOpening.getReport_opening_reference());
			bysjglxt_report_opening.setReport_opening_plan(updateReportOpening.getReport_opening_plan());
			bysjglxt_report_opening
					.setReport_opening_gmt_modified(updateReportOpening.getReport_opening_gmt_modified());
			flag = graduationProjectManagementDao.fillEmptyInOpening(bysjglxt_report_opening);
		}
		return flag;
	}

	/**
	 * 学生更改前期情况记录
	 */
	@Override
	public int updateStudentRecordProgressEarlystage(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Earlystage = new bysjglxt_record_progress();
		bysjglxt_record_progress_Earlystage = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Earlystage != null) {
			bysjglxt_record_progress_Earlystage
					.setRecord_progress_record(updateRecordProgress.getRecord_progress_record());
			bysjglxt_record_progress_Earlystage.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Earlystage);
		}
		return flag;
	}

	/**
	 * 老师更改前期情况记录
	 */
	@Override
	public int updateTeacherRecordProcessEarlystage(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Earlystage = new bysjglxt_record_progress();
		bysjglxt_record_progress_Earlystage = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Earlystage != null) {
			bysjglxt_record_progress_Earlystage
					.setRecord_progress_opinion(updateRecordProgress.getRecord_progress_opinion());
			bysjglxt_record_progress_Earlystage.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Earlystage);
		}
		return flag;
	}

	/**
	 * 学生更改中期记录
	 */
	@Override
	public int updateStudentRecordProgressMetaphase(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Metaphase = new bysjglxt_record_progress();
		bysjglxt_record_progress_Metaphase = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Metaphase != null) {
			bysjglxt_record_progress_Metaphase
					.setRecord_progress_record(updateRecordProgress.getRecord_progress_record());
			bysjglxt_record_progress_Metaphase.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Metaphase);
		}
		return flag;
	}

	/**
	 * 教师更改中期记录
	 */
	@Override
	public int updateTeacherRecordProgressMetaphase(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Metaphase = new bysjglxt_record_progress();
		bysjglxt_record_progress_Metaphase = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Metaphase != null) {
			bysjglxt_record_progress_Metaphase
					.setRecord_progress_opinion(updateRecordProgress.getRecord_progress_opinion());
			bysjglxt_record_progress_Metaphase.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Metaphase);
		}
		return flag;
	}

	/**
	 * 学生修改后期记录
	 */
	@Override
	public int updateStudentRecordProgressLaterstage(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Laterstage = new bysjglxt_record_progress();
		bysjglxt_record_progress_Laterstage = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Laterstage != null) {
			bysjglxt_record_progress_Laterstage
					.setRecord_progress_record(updateRecordProgress.getRecord_progress_record());
			bysjglxt_record_progress_Laterstage.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Laterstage);
		}
		return flag;
	}

	/**
	 * 教师修改后期记录
	 */
	@Override
	public int updateTeacherRecordProgressLaterstage(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Laterstage = new bysjglxt_record_progress();
		bysjglxt_record_progress_Laterstage = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Laterstage != null) {
			bysjglxt_record_progress_Laterstage
					.setRecord_progress_opinion(updateRecordProgress.getRecord_progress_opinion());
			bysjglxt_record_progress_Laterstage.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Laterstage);
		}
		return flag;
	}

	/**
	 * 学生修改完善期信息
	 */
	@Override
	public int updateStudentRecordProgressPerfect(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Perfect = new bysjglxt_record_progress();
		bysjglxt_record_progress_Perfect = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Perfect != null) {
			bysjglxt_record_progress_Perfect
					.setRecord_progress_record(updateRecordProgress.getRecord_progress_record());
			bysjglxt_record_progress_Perfect.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Perfect);
		}
		return flag;
	}

	/**
	 * 教师修改完善期信息
	 */
	@Override
	public int updateTeacherRecordProgressPerfect(bysjglxt_record_progress updateRecordProgress) {
		int flag = 2;
		bysjglxt_record_progress bysjglxt_record_progress_Perfect = new bysjglxt_record_progress();
		bysjglxt_record_progress_Perfect = graduationProjectManagementDao
				.getRecordProgress(updateRecordProgress.getRecord_progress_id());
		if (bysjglxt_record_progress_Perfect != null) {
			bysjglxt_record_progress_Perfect
					.setRecord_progress_opinion(updateRecordProgress.getRecord_progress_opinion());
			bysjglxt_record_progress_Perfect.setRecord_progress_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInProgressEarlystage(bysjglxt_record_progress_Perfect);
		}
		return flag;
	}

	/**
	 * 学生修改个人学习工作总结
	 */
	@Override
	public int updateStudentSummary(bysjglxt_summary updateSummary) {
		int flag = 2;
		bysjglxt_summary bysjglxt_summary = new bysjglxt_summary();
		bysjglxt_summary = graduationProjectManagementDao.findSummaryById(updateSummary.getSummary_id());
		if (bysjglxt_summary != null) {
			bysjglxt_summary.setSummary_summary(updateSummary.getSummary_summary());
			bysjglxt_summary.setSummary_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInSummary(bysjglxt_summary);
		}
		return flag;
	}

	/**
	 * 教师修改个人学习工作总结
	 */
	@Override
	public int updateTeacherSummary(bysjglxt_summary updateSummary) {
		int flag = 2;
		bysjglxt_summary bysjglxt_summary = new bysjglxt_summary();
		bysjglxt_summary = graduationProjectManagementDao.findSummaryById(updateSummary.getSummary_id());
		if (bysjglxt_summary != null) {
			bysjglxt_summary.setSummary_opinion(updateSummary.getSummary_opinion());
			bysjglxt_summary.setSummary_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInSummary(bysjglxt_summary);
		}
		return flag;
	}

	@Override
	public int updateTeacherExaminationFormal(bysjglxt_examination_formal updateExaminationFormal) {
		int flag = 2;
		bysjglxt_examination_formal bysjglxt_examination_formal = new bysjglxt_examination_formal();
		bysjglxt_examination_formal = graduationProjectManagementDao
				.findExaminationFormalById(updateExaminationFormal.getExamination_formal_id());
		if (bysjglxt_examination_formal != null) {
			bysjglxt_examination_formal
					.setExamination_formal_is_cover(updateExaminationFormal.getExamination_formal_is_cover());
			bysjglxt_examination_formal
					.setExamination_formal_is_a4(updateExaminationFormal.getExamination_formal_is_a4());
			bysjglxt_examination_formal
					.setExamination_formal_is_format(updateExaminationFormal.getExamination_formal_is_format());
			bysjglxt_examination_formal.setExamination_formal_is_abstract_chinese(
					updateExaminationFormal.getExamination_formal_is_abstract_chinese());
			bysjglxt_examination_formal
					.setExamination_formal_is_catalog(updateExaminationFormal.getExamination_formal_is_catalog());
			bysjglxt_examination_formal.setExamination_formal_is_abstract_foreign(
					updateExaminationFormal.getExamination_formal_is_abstract_foreign());
			bysjglxt_examination_formal
					.setExamination_formal_is_headline(updateExaminationFormal.getExamination_formal_is_headline());
			bysjglxt_examination_formal.setExamination_formal_is_punctuation(
					updateExaminationFormal.getExamination_formal_is_punctuation());
			bysjglxt_examination_formal
					.setExamination_formal_is_typo(updateExaminationFormal.getExamination_formal_is_typo());
			bysjglxt_examination_formal.setExamination_formal_is_reference_ten(
					updateExaminationFormal.getExamination_formal_is_reference_ten());
			bysjglxt_examination_formal.setExamination_formal_is_reference_foreign(
					updateExaminationFormal.getExamination_formal_is_reference_foreign());
			bysjglxt_examination_formal.setExamination_formal_is_reference_num(
					updateExaminationFormal.getExamination_formal_is_reference_num());
			bysjglxt_examination_formal.setExamination_formal_is_reference_new(
					updateExaminationFormal.getExamination_formal_is_reference_new());
			bysjglxt_examination_formal.setExamination_formal_is_reference_format(
					updateExaminationFormal.getExamination_formal_is_reference_format());
			bysjglxt_examination_formal.setExamination_formal_is_progress_metaphase(
					updateExaminationFormal.getExamination_formal_is_progress_metaphase());
			bysjglxt_examination_formal.setExamination_formal_is_progress_summary(
					updateExaminationFormal.getExamination_formal_is_progress_summary());
			bysjglxt_examination_formal.setExamination_formal_is_progress_actual(
					updateExaminationFormal.getExamination_formal_is_progress_actual());
			bysjglxt_examination_formal.setExamination_formal_is_progress_complete(
					updateExaminationFormal.getExamination_formal_is_progress_complete());
			bysjglxt_examination_formal.setExamination_formal_is_progress_logic(
					updateExaminationFormal.getExamination_formal_is_progress_logic());
			bysjglxt_examination_formal
					.setExamination_formal_is_chart(updateExaminationFormal.getExamination_formal_is_chart());
			bysjglxt_examination_formal
					.setExamination_formal_is_enclosure(updateExaminationFormal.getExamination_formal_is_enclosure());
			bysjglxt_examination_formal.setExamination_formal_is_teacher_opinion(
					updateExaminationFormal.getExamination_formal_is_teacher_opinion());
			bysjglxt_examination_formal.setExamination_formal_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInExaminationFormal(bysjglxt_examination_formal);
		}
		return flag;
	}

	/**
	 * 领导小组更改形式审查表
	 */
	@Override
	public int updateLeaderExaminationFormal(bysjglxt_examination_formal updateExaminationFormal) {
		int flag = 2;
		bysjglxt_examination_formal bysjglxt_examination_formal = new bysjglxt_examination_formal();
		bysjglxt_examination_formal = graduationProjectManagementDao
				.findExaminationFormalById(updateExaminationFormal.getExamination_formal_id());
		if (bysjglxt_examination_formal != null) {
			bysjglxt_examination_formal.setExamination_formal_is_leader_opinion(
					updateExaminationFormal.getExamination_formal_is_leader_opinion());
			bysjglxt_examination_formal.setExamination_formal_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyInExaminationFormal(bysjglxt_examination_formal);
		}
		return flag;
	}

	@Override
	public int updateEvaluateTutor(bysjglxt_evaluate_tutor updateEvaluateTutor) {
		int flag = 2;
		bysjglxt_evaluate_tutor bysjglxt_evaluate_tutor = new bysjglxt_evaluate_tutor();
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_evaluate_tutor = graduationProjectManagementDao
				.findEvaluateTutor(updateEvaluateTutor.getEvaluate_tutor_id());
		if (bysjglxt_evaluate_tutor != null) {

			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_teacher_comment(updateEvaluateTutor.getEvaluate_tutor_teacher_comment());
			bysjglxt_evaluate_tutor.setEvaluate_tutor_grade_training_objective(
					updateEvaluateTutor.getEvaluate_tutor_grade_training_objective());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_difficulty(updateEvaluateTutor.getEvaluate_tutor_grade_difficulty());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_workload(updateEvaluateTutor.getEvaluate_tutor_grade_workload());
			bysjglxt_evaluate_tutor.setEvaluate_tutor_grade_bind(updateEvaluateTutor.getEvaluate_tutor_grade_bind());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_comprehensive(updateEvaluateTutor.getEvaluate_tutor_grade_comprehensive());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_reference(updateEvaluateTutor.getEvaluate_tutor_grade_reference());
			bysjglxt_evaluate_tutor.setEvaluate_tutor_grade_experimental_design(
					updateEvaluateTutor.getEvaluate_tutor_grade_experimental_design());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_computing(updateEvaluateTutor.getEvaluate_tutor_grade_computing());
			bysjglxt_evaluate_tutor.setEvaluate_tutor_grade_foreign_language(
					updateEvaluateTutor.getEvaluate_tutor_grade_foreign_language());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_computer(updateEvaluateTutor.getEvaluate_tutor_grade_computer());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_innovate(updateEvaluateTutor.getEvaluate_tutor_grade_innovate());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_analysis(updateEvaluateTutor.getEvaluate_tutor_grade_analysis());
			bysjglxt_evaluate_tutor.setEvaluate_tutor_grade_chart(updateEvaluateTutor.getEvaluate_tutor_grade_chart());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_instructions(updateEvaluateTutor.getEvaluate_tutor_grade_instructions());
			bysjglxt_evaluate_tutor.setEvaluate_tutor_grade_practicability(
					updateEvaluateTutor.getEvaluate_tutor_grade_practicability());
			bysjglxt_evaluate_tutor
					.setEvaluate_tutor_grade_normalization(updateEvaluateTutor.getEvaluate_tutor_grade_normalization());
			bysjglxt_evaluate_tutor.setEvaluate_tutor_grade_total(updateEvaluateTutor.getEvaluate_tutor_grade_total());
			if (bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_total() > 60) {
				bysjglxt_evaluate_tutor.setEvaluate_tutor_is_teacher_opinion(1);
			} else {
				bysjglxt_evaluate_tutor.setEvaluate_tutor_is_teacher_opinion(0);
			}
			bysjglxt_evaluate_tutor.setEvaluate_tutor_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.saveObj(bysjglxt_evaluate_tutor);
			if (flag != 2) {
				// 根据得到的答辩评分表将指导教师评分占比填写进去
				bysjglxt_defence = graduationProjectManagementDao
						.findDefenceByUserId(bysjglxt_evaluate_tutor.getEvaluate_tutor_student());
				bysjglxt_defence
						.setDefence_grade_evaluate_tutor(bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_total() * 0.3);
				bysjglxt_defence.setDefence_gmt_modified(TeamUtil.getStringSecond());
				flag = graduationProjectManagementDao.fillEmptyDefence(bysjglxt_defence);
			}
		}
		return flag;
	}

	@Override
	public int updateEvaluateReview(bysjglxt_evaluate_review updateEvaluateReview) {
		int flag = 2;
		bysjglxt_evaluate_review bysjglxt_evaluate_review = new bysjglxt_evaluate_review();
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_evaluate_review = graduationProjectManagementDao
				.findEvaluateReviewById(updateEvaluateReview.getEvaluate_review_id());
		if (bysjglxt_evaluate_review != null) {
			bysjglxt_evaluate_review
					.setEvaluate_review_teacher_comment(updateEvaluateReview.getEvaluate_review_teacher_comment());
			bysjglxt_evaluate_review.setEvaluate_review_grade_training_objective(
					updateEvaluateReview.getEvaluate_review_grade_training_objective());
			bysjglxt_evaluate_review
					.setEvaluate_review_grade_difficulty(updateEvaluateReview.getEvaluate_review_grade_difficulty());
			bysjglxt_evaluate_review
					.setEvaluate_review_grade_workload(updateEvaluateReview.getEvaluate_review_grade_workload());
			bysjglxt_evaluate_review
					.setEvaluate_review_grade_bind(updateEvaluateReview.getEvaluate_review_grade_bind());
			bysjglxt_evaluate_review.setEvaluate_review_grade_comprehensive(
					updateEvaluateReview.getEvaluate_review_grade_comprehensive());
			bysjglxt_evaluate_review
					.setEvaluate_review_grade_reference(updateEvaluateReview.getEvaluate_review_grade_reference());
			bysjglxt_evaluate_review.setEvaluate_review_grade_experimental_design(
					updateEvaluateReview.getEvaluate_review_grade_experimental_design());

			bysjglxt_evaluate_review
					.setEvaluate_review_grade_computing(updateEvaluateReview.getEvaluate_review_grade_computing());

			bysjglxt_evaluate_review.setEvaluate_review_grade_foreign_language(
					updateEvaluateReview.getEvaluate_review_grade_foreign_language());
			bysjglxt_evaluate_review
					.setEvaluate_review_grade_computer(updateEvaluateReview.getEvaluate_review_grade_computer());
			bysjglxt_evaluate_review
					.setEvaluate_review_grade_innovate(updateEvaluateReview.getEvaluate_review_grade_innovate());

			bysjglxt_evaluate_review
					.setEvaluate_review_grade_analysis(updateEvaluateReview.getEvaluate_review_grade_analysis());

			bysjglxt_evaluate_review
					.setEvaluate_review_grade_chart(updateEvaluateReview.getEvaluate_review_grade_chart());

			bysjglxt_evaluate_review.setEvaluate_review_grade_instructions(
					updateEvaluateReview.getEvaluate_review_grade_instructions());

			bysjglxt_evaluate_review.setEvaluate_review_grade_practicability(
					updateEvaluateReview.getEvaluate_review_grade_practicability());

			bysjglxt_evaluate_review.setEvaluate_review_grade_normalization(
					updateEvaluateReview.getEvaluate_review_grade_normalization());
			bysjglxt_evaluate_review
					.setEvaluate_review_grade_total(updateEvaluateReview.getEvaluate_review_grade_total());
			bysjglxt_evaluate_review.setEvaluate_review_is_teacher_opinion(
					updateEvaluateReview.getEvaluate_review_is_teacher_opinion());
			bysjglxt_evaluate_review.setEvaluate_review_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyEvaluateReview(bysjglxt_evaluate_review);
			if (flag != 2) {
				bysjglxt_defence = graduationProjectManagementDao
						.findDefenceByUserId(bysjglxt_evaluate_review.getEvaluate_review_student());
				bysjglxt_defence.setDefence_grade_evaluate_review(
						bysjglxt_evaluate_review.getEvaluate_review_grade_total() * 0.3);
				bysjglxt_defence.setDefence_gmt_modified(TeamUtil.getStringSecond());
				flag = graduationProjectManagementDao.fillEmptyDefence(bysjglxt_defence);
			}
		}
		return flag;
	}

	@Override
	public int updateDefence(bysjglxt_defence updateDefence) {
		int flag = 2;
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_defence = graduationProjectManagementDao.findDefenceByUserId(updateDefence.getDefence_student());
		String grade = "";
		if (bysjglxt_defence != null) {
			bysjglxt_defence.setDefence_leader_comment(updateDefence.getDefence_leader_comment());
			bysjglxt_defence.setDefence_grade_writing(updateDefence.getDefence_grade_writing());
			bysjglxt_defence.setDefence_grade_normalization(updateDefence.getDefence_grade_normalization());
			bysjglxt_defence.setDefence_grade_complete(updateDefence.getDefence_grade_complete());
			bysjglxt_defence.setDefence_grade_technology(updateDefence.getDefence_grade_technology());
			bysjglxt_defence.setDefence_grade_practicability(updateDefence.getDefence_grade_practicability());
			bysjglxt_defence.setDefence_grade_appearance(updateDefence.getDefence_grade_appearance());
			bysjglxt_defence.setDefence_grade_statement(updateDefence.getDefence_grade_statement());
			bysjglxt_defence.setDefence_grade_answer(updateDefence.getDefence_grade_answer());
			bysjglxt_defence.setDefence_grade_defence(updateDefence.getDefence_grade_defence());
			bysjglxt_defence.setDefence_total((int) (Math.round((bysjglxt_defence.getDefence_grade_defence() * 0.4
					+ bysjglxt_defence.getDefence_grade_evaluate_review() * 0.3
					+ bysjglxt_defence.getDefence_grade_evaluate_tutor() * 0.3) + 0.5)));
			/**
			 * 
			 * 五级评分制 优：90 中：80 良：70 及格：60 不及格：<60
			 */
			bysjglxt_defence.setDefence_finally(TeamUtil.grade(bysjglxt_defence.getDefence_total()));
			bysjglxt_defence.setDefence_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyDefence(bysjglxt_defence);
		}
		return flag;
	}

	/**
	 * 记录员更改答辩表
	 */
	@Override
	public int updateDefenceRecorder(bysjglxt_defence updateDefence) {
		int flag = 2;
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_defence = graduationProjectManagementDao.findDefenceByUserId(updateDefence.getDefence_student());
		if (bysjglxt_defence != null) {
			bysjglxt_defence.setDefence_record(updateDefence.getDefence_record());
			bysjglxt_defence.setDefence_gmt_modified(TeamUtil.getStringSecond());
			flag = graduationProjectManagementDao.fillEmptyDefence(bysjglxt_defence);
		}
		return flag;
	}

	/******************************** 下面是我的毕业设计需要 ***************************************/

	@Override
	public bysjglxt_taskbook get_TaskBook(String userId) {
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		System.out.println("userId:" + userId);
		bysjglxt_taskbook = graduationProjectManagementDao.getTaskBookByUserId(userId);
		return bysjglxt_taskbook;
	}

	@Override
	public bysjglxt_dissertation get_Dissertation(String userId) {
		bysjglxt_dissertation bysjglxt_dissertation = new bysjglxt_dissertation();
		bysjglxt_dissertation = graduationProjectManagementDao.getThesisByStudent(userId);
		return bysjglxt_dissertation;
	}

	@Override
	public bysjglxt_report_opening get_ReportOpening(String userId) {
		bysjglxt_report_opening bysjglxt_report_opening = new bysjglxt_report_opening();
		bysjglxt_report_opening = graduationProjectManagementDao.getReportOpeningUser(userId);
		return bysjglxt_report_opening;
	}

	@Override
	public bysjglxt_record_progress get_RecordProgress_1(String userId) {
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		bysjglxt_record_progress = graduationProjectManagementDao.getRecordProgress(userId, "前期");
		return bysjglxt_record_progress;
	}

	@Override
	public bysjglxt_record_progress get_RecordProgress_2(String userId) {
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		bysjglxt_record_progress = graduationProjectManagementDao.getRecordProgress(userId, "中期");
		return bysjglxt_record_progress;
	}

	@Override
	public bysjglxt_record_progress get_RecordProgress_3(String userId) {
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		bysjglxt_record_progress = graduationProjectManagementDao.getRecordProgress(userId, "后期");
		return bysjglxt_record_progress;
	}

	@Override
	public bysjglxt_record_progress get_RecordProgress_4(String userId) {
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		bysjglxt_record_progress = graduationProjectManagementDao.getRecordProgress(userId, "完善");
		return bysjglxt_record_progress;
	}

	@Override
	public bysjglxt_summary get_Summary(String userId) {
		bysjglxt_summary bysjglxt_summary = new bysjglxt_summary();
		bysjglxt_summary = graduationProjectManagementDao.getSummary(userId);
		return bysjglxt_summary;
	}

	@Override
	public bysjglxt_examination_formal get_ExaminationFormal(String userId) {
		bysjglxt_examination_formal bysjglxt_examination_formal = new bysjglxt_examination_formal();
		bysjglxt_examination_formal = graduationProjectManagementDao.getExaminationFormal(userId);
		return bysjglxt_examination_formal;
	}

	@Override
	public bysjglxt_evaluate_tutor get_EvaluateTutor(String userId) {
		bysjglxt_evaluate_tutor bysjglxt_evaluate_tutor = new bysjglxt_evaluate_tutor();
		bysjglxt_evaluate_tutor = graduationProjectManagementDao.getEvaluateTutor(userId);
		return bysjglxt_evaluate_tutor;
	}

	@Override
	public bysjglxt_evaluate_review get_EvaluateReview(String userId) {
		bysjglxt_evaluate_review bysjglxt_evaluate_review = new bysjglxt_evaluate_review();
		bysjglxt_evaluate_review = graduationProjectManagementDao.getEvaluateReview(userId);
		return bysjglxt_evaluate_review;
	}

	@Override
	public bysjglxt_defence get_Defence(String userId) {
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_defence = graduationProjectManagementDao.getDefence(userId);
		return bysjglxt_defence;
	}

	@Override
	public File exportAll(List<String> userListId) throws Exception {
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			System.out.println("获取初始路径失败");
			e.printStackTrace();
		}
		List<File> fileList = new ArrayList<File>();
		for (String usrId : userListId) {
			fileList.add(exportOneStudent(usrId));
		}
		File zipFile = new File(lj + "毕业设计过程管理手册.zip");
		TeamUtil.zipFiles(fileList, zipFile);
		for (String usrId : userListId) {
			exportOneStudent(usrId).delete();
		}
		return zipFile;
	}

	// 导出单个人
	public File exportOneStudent(String userId) throws Exception {
		/*
		 * 获取路径
		 */
		String lj = "";
		try {
			Properties props = new Properties();
			props.load(this.getClass().getClassLoader().getResourceAsStream("file.properties"));
			lj = props.getProperty("lj");
		} catch (Exception e) {
			System.out.println("获取初始路径失败");
			e.printStackTrace();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		// 封面
		params.putAll(exportCover(userId));
		// 任务书
		params.putAll(exportTask(userId));
		// 开题报告
		params.putAll(exportOpeningReport(userId));
		// 前期情况记录
		params.putAll(exportEarlystage(userId));
		// 中期情况记录
		params.putAll(exportMetaphase(userId));
		// 后期情况记录
		params.putAll(exportLaterstage(userId));
		// 完善情况记录
		params.putAll(exportPerfect(userId));
		// 个人学习工作总结
		params.putAll(exportSummary(userId));
		// 形式审查表
		params.putAll(exportFormal(userId));
		// 指导老师评价表
		params.putAll(exportTeacherOpin(userId));
		// 评阅老师评价表
		params.putAll(exportReviewOpin(userId));
		// 答辩评分表
		params.putAll(exportDefence(userId));
		XwpfTUtil xwpfTUtil = new XwpfTUtil();
		XWPFDocument doc;
		String fileNameInResource = ServletActionContext.getServletContext().getRealPath("/DocTmp/ttt.docx");
		// String fileNamefInResource = "F:\\ttt.docx";
		InputStream is;
		is = new FileInputStream(fileNameInResource);
		// is = new FileInputStream(fileNamefInResource);
		doc = new XWPFDocument(is);
		xwpfTUtil.replaceInPara(doc, params);
		xwpfTUtil.replaceInTable(doc, params);
		// 根据user Id获取学生信息
		bysjglxt_student_basic studentBasic = new bysjglxt_student_basic();
		studentBasic = graduationProjectManagementDao.getStudentBasicByUserId(userId);
		String pa = lj + "毕业设计过程管理手册——" + studentBasic.getStudent_basic_num() + studentBasic.getStudent_basic_name()
				+ ".docx";
		File ff = new File(pa);
		if (!ff.exists()) {
			ff.createNewFile();
		}
		OutputStream os = new FileOutputStream(pa);
		doc.write(os);
		xwpfTUtil.close(os);
		xwpfTUtil.close(is);
		os.flush();
		os.close();
		return new File(pa);

	}

	// 导出封面
	@Override
	public Map<String, Object> exportCover(String studentUserId) {
		// 1.根据学生user Id获取学生登录表信息
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_teacher_user bysjglxt_teacher_user_tutor = new bysjglxt_teacher_user();
		bysjglxt_teacher_user bysjglxt_teacher_user_evaluate = new bysjglxt_teacher_user();
		bysjglxt_teacher_basic bysjglxt_teacher_basic_tutor = new bysjglxt_teacher_basic();
		bysjglxt_teacher_basic bysjglxt_teacher_basic_evaluate = new bysjglxt_teacher_basic();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				int sessional = Integer.parseInt(bysjglxt_student_basic.getStudent_basic_level());
				sessional = sessional + 4;
				params.put("${coverStudentNum}", bysjglxt_student_basic.getStudent_basic_num());
				params.put("${coverStudentName}", bysjglxt_student_basic.getStudent_basic_name());
				// 届别
				params.put("${coverSessional}", sessional + "");
				params.put("${coverMajor}", bysjglxt_student_basic.getStudent_basic_major());
			} else {
				params.put("${coverStudentNum}", "");
				params.put("${coverStudentName}", "");
				params.put("${coverSessional}", "");
				params.put("${coverMajor}", "");

			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据老师userId获得指导老师user表
				bysjglxt_teacher_user_tutor = graduationProjectManagementDao
						.getTeacherUserByUserId(bysjglxt_topic_select.getTopic_select_teacher_tutor());
				// 根据老师basicId获得指导老师basic表
				bysjglxt_teacher_basic_tutor = graduationProjectManagementDao
						.getTeacherBasicByBasicId(bysjglxt_teacher_user_tutor.getUser_teacher_basic());
				params.put("${coverTutorName}", bysjglxt_teacher_basic_tutor.getName());
				params.put("${coverTutorTitle}", bysjglxt_teacher_basic_tutor.getProfessional_title());
				if (bysjglxt_topic_select.getTopic_select_teacher_review() != null
						&& bysjglxt_topic_select.getTopic_select_teacher_review().trim().length() > 0) {
					// 根据老师userId获得评阅老师user表
					bysjglxt_teacher_user_evaluate = graduationProjectManagementDao
							.getTeacherUserByUserId(bysjglxt_topic_select.getTopic_select_teacher_review());
					// 根据老师basicid获得评阅老师basic表
					bysjglxt_teacher_basic_evaluate = graduationProjectManagementDao
							.getTeacherBasicByBasicId(bysjglxt_teacher_user_evaluate.getUser_teacher_basic());
					params.put("${coverEvaluateName}", bysjglxt_teacher_basic_evaluate.getName());
					params.put("${coverEvaluateTitle}", bysjglxt_teacher_basic_evaluate.getProfessional_title());
				} else {
					params.put("${coverEvaluateName}", "");
					params.put("${coverEvaluateTitle}", "");
				}
			} else {
				params.put("${coverTutorName}", "");
				params.put("${coverTutorTitle}", "");
				params.put("${coverEvaluateName}", "");
				params.put("${coverEvaluateTitle}", "");
			}
		}
		return params;
	}

	// 导出任务书
	@Override
	public Map<String, Object> exportTask(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_taskbook bysjglxt_taskbook = new bysjglxt_taskbook();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("${taskNum}", bysjglxt_student_basic.getStudent_basic_num());
				params.put("${taskNam}", bysjglxt_student_basic.getStudent_basic_name());
				params.put("${taskMajor}", bysjglxt_student_basic.getStudent_basic_major());
			} else {
				params.put("${taskNum}", "");
				params.put("${taskNam}", "");
				params.put("${taskMajor}", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("${taskChineseName}", bysjglxt_topic.getTopic_name_chinese());
					params.put("${taskEnglishName}", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("${taskChineseName}", "");
					params.put("${taskEnglishName}", "");
				}
			}
			// 根据user Id获取任务书表
			bysjglxt_taskbook = graduationProjectManagementDao
					.getTaskBookByUserId(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_taskbook != null) {
				params.put("${taskRequired}", bysjglxt_taskbook.getTaskbook_acontent_required());
				params.put("${taskReference}", bysjglxt_taskbook.getTaskbook_reference());
				params.put("${taskPlan}", bysjglxt_taskbook.getTaskbook_plan());
				params.put("${taskOpinion}", bysjglxt_taskbook.getTaskbook_opinion());
			} else {
				params.put("${taskRequired}", "");
				params.put("${taskReference}", "");
				params.put("${taskPlan}", "");
				params.put("${taskOpinion}", "");
			}
		}
		return params;
	}

	// 导出开题报告
	@Override
	public Map<String, Object> exportOpeningReport(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_report_opening bysjglxt_report_opening = new bysjglxt_report_opening();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("${openingNum}", bysjglxt_student_basic.getStudent_basic_num());
				params.put("${openingNam}", bysjglxt_student_basic.getStudent_basic_name());
				params.put("${openingMajor}", bysjglxt_student_basic.getStudent_basic_major());
				int sessional = Integer.parseInt(bysjglxt_student_basic.getStudent_basic_level());
				sessional = sessional + 4;
				params.put("${openingSessional}", sessional + "");
			} else {
				params.put("${openingNum}", "");
				params.put("${openingNam}", "");
				params.put("${openingMajor}", "");
				params.put("${openingSessional}", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("${openingChineseName}", bysjglxt_topic.getTopic_name_chinese());
					params.put("${openingEnglishName}", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("${openingChineseName}", "");
					params.put("${openingEnglishName}", "");
				}
			}
			// 根据userId获取开题报告
			bysjglxt_report_opening = graduationProjectManagementDao
					.getReportOpeningUser(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_report_opening != null) {
				params.put("${openingDocumentSurvey}", bysjglxt_report_opening.getReport_opening_documentary_survey());
				params.put("${openingMain}", bysjglxt_report_opening.getReport_opening_main());
				params.put("${openingDetail}", bysjglxt_report_opening.getReport_opening_detail());
				params.put("${openingReference}", bysjglxt_report_opening.getReport_opening_reference());
				params.put("${openingPlan}", bysjglxt_report_opening.getReport_opening_plan());
			} else {
				params.put("${openingDocumentSurvey}", "");
				params.put("${openingMain}", "");
				params.put("${openingDetail}", "");
				params.put("${openingReference}", "");
				params.put("${openingPlan}", "");
			}
		}
		return params;
	}

	// 导出前期情况记录
	@Override
	public Map<String, Object> exportEarlystage(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("${early}", bysjglxt_student_basic.getStudent_basic_num());
				params.put("${earlyNa}", bysjglxt_student_basic.getStudent_basic_name());
				params.put("${earlyMagor}", bysjglxt_student_basic.getStudent_basic_major());
			} else {
				params.put("${early}", "");
				params.put("${earlyNa}", "");
				params.put("${earlyMagor}", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("${earlyChineseName}", bysjglxt_topic.getTopic_name_chinese());
					params.put("${earlyEnglishName}", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("${earlyChineseName}", "");
					params.put("${earlyEnglishName}", "");
				}
			}
			// 根据user ID以及阶段获取进展情况记录表
			bysjglxt_record_progress = graduationProjectManagementDao
					.getRecordProgress(bysjglxt_student_user.getUser_student_id(), "前期");
			if (bysjglxt_record_progress != null) {
				if (bysjglxt_record_progress.getRecord_progress_gmt_start() != null
						&& bysjglxt_record_progress.getRecord_progress_gmt_start().trim().length() > 0) {
					params.put("${a}", TeamUtil.timeToYear(bysjglxt_record_progress.getRecord_progress_gmt_start()));
					params.put("${b}", TeamUtil.timeToMonth(bysjglxt_record_progress.getRecord_progress_gmt_start()));
					params.put("${c}", TeamUtil.timeToDay(bysjglxt_record_progress.getRecord_progress_gmt_start()));
				} else {
					params.put("${a}", "");
					params.put("${b}", "");
					params.put("${c}", "");
				}
				if (bysjglxt_record_progress.getRecord_progress_gmt_stop() != null
						&& bysjglxt_record_progress.getRecord_progress_gmt_stop().trim().length() > 0) {
					params.put("${d}", TeamUtil.timeToYear(bysjglxt_record_progress.getRecord_progress_gmt_stop()));
					params.put("${f}", TeamUtil.timeToMonth(bysjglxt_record_progress.getRecord_progress_gmt_stop()));
					params.put("${e}", TeamUtil.timeToDay(bysjglxt_record_progress.getRecord_progress_gmt_stop()));
				} else {
					params.put("${d}", "");
					params.put("${f}", "");
					params.put("${e}", "");
				}
				params.put("${earlyProcessRecord}", bysjglxt_record_progress.getRecord_progress_record());
				params.put("${earlyProcessOpinion}", bysjglxt_record_progress.getRecord_progress_opinion());
			} else {
				params.put("${a}", "");
				params.put("${b}", "");
				params.put("${c}", "");
				params.put("${d}", "");
				params.put("${f}", "");
				params.put("${e}", "");
				params.put("${earlyProcessRecord}", "");
				params.put("${earlyProcessOpinion}", "");
			}
		}
		return params;
	}

	@Override
	public Map<String, Object> exportMetaphase(String studentUserId) {

		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("${zhongN}", bysjglxt_student_basic.getStudent_basic_num());
				params.put("${zhongNa}", bysjglxt_student_basic.getStudent_basic_name());
				params.put("${zhongMajor}", bysjglxt_student_basic.getStudent_basic_major());
			} else {
				params.put("${zhongN}", "");
				params.put("${zhongNa}", "");
				params.put("${zhongMajor}", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("${zhongChineseName}", bysjglxt_topic.getTopic_name_chinese());
					params.put("${zhongEnglishName}", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("${zhongChineseName}", "");
					params.put("${zhongEnglishName}", "");
				}
			}
			// 根据user ID以及阶段获取进展情况记录表
			bysjglxt_record_progress = graduationProjectManagementDao
					.getRecordProgress(bysjglxt_student_user.getUser_student_id(), "中期");
			if (bysjglxt_record_progress != null) {
				if (bysjglxt_record_progress.getRecord_progress_gmt_start() != null
						&& bysjglxt_record_progress.getRecord_progress_gmt_start().trim().length() > 0) {
					params.put("${zy}", TeamUtil.timeToYear(bysjglxt_record_progress.getRecord_progress_gmt_start()));
					params.put("${zm}", TeamUtil.timeToMonth(bysjglxt_record_progress.getRecord_progress_gmt_start()));
					params.put("${zd}", TeamUtil.timeToDay(bysjglxt_record_progress.getRecord_progress_gmt_start()));
				} else {
					params.put("${zy}", "");
					params.put("${zm}", "");
					params.put("${zd}", "");
				}
				if (bysjglxt_record_progress.getRecord_progress_gmt_stop() != null
						&& bysjglxt_record_progress.getRecord_progress_gmt_stop().trim().length() > 0) {
					params.put("${zyz}", TeamUtil.timeToYear(bysjglxt_record_progress.getRecord_progress_gmt_stop()));
					params.put("${zmz}", TeamUtil.timeToMonth(bysjglxt_record_progress.getRecord_progress_gmt_stop()));
					params.put("${zdz}", TeamUtil.timeToDay(bysjglxt_record_progress.getRecord_progress_gmt_stop()));
				} else {
					params.put("${zyz}", "");
					params.put("${zmz}", "");
					params.put("${zdz}", "");
				}
				params.put("${zhongRecord}", bysjglxt_record_progress.getRecord_progress_record());
				params.put("${zhongOpinion}", bysjglxt_record_progress.getRecord_progress_opinion());
			} else {
				params.put("${zy}", "");
				params.put("${zm}", "");
				params.put("${zd}", "");
				params.put("${zyz}", "");
				params.put("${zmz}", "");
				params.put("${zdz}", "");
				params.put("${zhongRecord}", "");
				params.put("${zhongOpinion}", "");
			}
		}
		return params;
	}

	// 导出后期
	@Override
	public Map<String, Object> exportLaterstage(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("${houNu}", bysjglxt_student_basic.getStudent_basic_num());
				params.put("${houName}", bysjglxt_student_basic.getStudent_basic_name());
				params.put("${houMajor}", bysjglxt_student_basic.getStudent_basic_major());
			} else {
				params.put("${houNu}", "");
				params.put("${houName}", "");
				params.put("${houMajor}", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("${houChineseName}", bysjglxt_topic.getTopic_name_chinese());
					params.put("${houEnglishName}", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("${houChineseName}", "");
					params.put("${houEnglishName}", "");
				}
			}
			// 根据user ID以及阶段获取进展情况记录表
			bysjglxt_record_progress = graduationProjectManagementDao
					.getRecordProgress(bysjglxt_student_user.getUser_student_id(), "后期");
			if (bysjglxt_record_progress != null) {
				if (bysjglxt_record_progress.getRecord_progress_gmt_start() != null
						&& bysjglxt_record_progress.getRecord_progress_gmt_start().trim().length() > 0) {
					params.put("${hy}", TeamUtil.timeToYear(bysjglxt_record_progress.getRecord_progress_gmt_start()));
					params.put("${hm}", TeamUtil.timeToMonth(bysjglxt_record_progress.getRecord_progress_gmt_start()));
					params.put("${hd}", TeamUtil.timeToDay(bysjglxt_record_progress.getRecord_progress_gmt_start()));
				} else {
					params.put("${hy}", "");
					params.put("${hm}", "");
					params.put("${hd}", "");
				}
				if (bysjglxt_record_progress.getRecord_progress_gmt_stop() != null
						&& bysjglxt_record_progress.getRecord_progress_gmt_stop().trim().length() > 0) {
					params.put("${hyh}", TeamUtil.timeToYear(bysjglxt_record_progress.getRecord_progress_gmt_stop()));
					params.put("${hmy}", TeamUtil.timeToMonth(bysjglxt_record_progress.getRecord_progress_gmt_stop()));
					params.put("${hdh}", TeamUtil.timeToDay(bysjglxt_record_progress.getRecord_progress_gmt_stop()));
				} else {
					params.put("${hyh}", "");
					params.put("${hmy}", "");
					params.put("${hdh}", "");
				}
				params.put("${houRecord}", bysjglxt_record_progress.getRecord_progress_record());
				params.put("${houOpinion}", bysjglxt_record_progress.getRecord_progress_opinion());
			} else {
				params.put("${hy}", "");
				params.put("${hm}", "");
				params.put("${hd}", "");
				params.put("${hyh}", "");
				params.put("${hmy}", "");
				params.put("${hdh}", "");
				params.put("${houRecord}", "");
				params.put("${houOpinion}", "");
			}
		}
		return params;
	}

	@Override
	public Map<String, Object> exportPerfect(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_record_progress bysjglxt_record_progress = new bysjglxt_record_progress();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("${wanNu}", bysjglxt_student_basic.getStudent_basic_num());
				params.put("${wanName}", bysjglxt_student_basic.getStudent_basic_name());
				params.put("${wanMajor}", bysjglxt_student_basic.getStudent_basic_major());
			} else {
				params.put("${wanNu}", "");
				params.put("${wanName}", "");
				params.put("${wanMajor}", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("${wanChineseName}", bysjglxt_topic.getTopic_name_chinese());
					params.put("${wanEnglishName}", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("${wanChineseName}", "");
					params.put("${wanEnglishName}", "");
				}
			}
			// 根据user ID以及阶段获取进展情况记录表
			bysjglxt_record_progress = graduationProjectManagementDao
					.getRecordProgress(bysjglxt_student_user.getUser_student_id(), "完善");
			if (bysjglxt_record_progress != null) {
				if (bysjglxt_record_progress.getRecord_progress_gmt_start() != null
						&& bysjglxt_record_progress.getRecord_progress_gmt_start().trim().length() > 0) {
					params.put("${wy}", TeamUtil.timeToYear(bysjglxt_record_progress.getRecord_progress_gmt_start()));
					params.put("${wm}", TeamUtil.timeToMonth(bysjglxt_record_progress.getRecord_progress_gmt_start()));
					params.put("${wd}", TeamUtil.timeToDay(bysjglxt_record_progress.getRecord_progress_gmt_start()));
				} else {
					params.put("${wy}", "");
					params.put("${wm}", "");
					params.put("${wd}", "");
				}
				if (bysjglxt_record_progress.getRecord_progress_gmt_stop() != null
						&& bysjglxt_record_progress.getRecord_progress_gmt_stop().trim().length() > 0) {
					params.put("${wyw}", TeamUtil.timeToYear(bysjglxt_record_progress.getRecord_progress_gmt_stop()));
					params.put("${wmw}", TeamUtil.timeToMonth(bysjglxt_record_progress.getRecord_progress_gmt_stop()));
					params.put("${wdw}", TeamUtil.timeToDay(bysjglxt_record_progress.getRecord_progress_gmt_stop()));
				} else {
					params.put("${wyw}", "");
					params.put("${wmw}", "");
					params.put("${wdw}", "");
				}
				params.put("${wanRecord}", bysjglxt_record_progress.getRecord_progress_record());
				params.put("${wanOpinion}", bysjglxt_record_progress.getRecord_progress_opinion());
			} else {
				params.put("${wy}", "");
				params.put("${wm}", "");
				params.put("${wd}", "");
				params.put("${wyw}", "");
				params.put("${wmw}", "");
				params.put("${wdw}", "");
				params.put("${wanRecord}", "");
				params.put("${wanOpinion}", "");
			}
		}
		return params;
	}

	@Override
	public Map<String, Object> exportSummary(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_summary bysjglxt_summary = new bysjglxt_summary();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("${sumNum}", bysjglxt_student_basic.getStudent_basic_num());
				params.put("${sumName}", bysjglxt_student_basic.getStudent_basic_name());
				params.put("${sumMagir}", bysjglxt_student_basic.getStudent_basic_major());
			} else {
				params.put("${sumNum}", "");
				params.put("${sumName}", "");
				params.put("${sumMagir}", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("${sumChineseName}", bysjglxt_topic.getTopic_name_chinese());
					params.put("${sumEnglishName}", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("${sumChineseName}", "");
					params.put("${sumEnglishName}", "");
				}
			}
			// 根据user Id获取个人学习工作总结
			bysjglxt_summary = graduationProjectManagementDao.getSummary(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_summary != null) {
				if (bysjglxt_summary.getSummary_gmt_start() != null
						&& bysjglxt_summary.getSummary_gmt_start().trim().length() > 0) {
					params.put("${sy}", TeamUtil.timeToYear(bysjglxt_summary.getSummary_gmt_start()));
					params.put("${sm}", TeamUtil.timeToMonth(bysjglxt_summary.getSummary_gmt_start()));
					params.put("${sd}", TeamUtil.timeToDay(bysjglxt_summary.getSummary_gmt_start()));
				} else {
					params.put("${sy}", "");
					params.put("${sm}", "");
					params.put("${sd}", "");
				}
				if (bysjglxt_summary.getSummary_gmt_stop() != null
						&& bysjglxt_summary.getSummary_gmt_stop().trim().length() > 0) {
					params.put("${sys}", TeamUtil.timeToYear(bysjglxt_summary.getSummary_gmt_stop()));
					params.put("${sms}", TeamUtil.timeToMonth(bysjglxt_summary.getSummary_gmt_stop()));
					params.put("${sds}", TeamUtil.timeToDay(bysjglxt_summary.getSummary_gmt_stop()));
				} else {
					params.put("${sys}", "");
					params.put("${sms}", "");
					params.put("${sds}", "");
				}
				params.put("${sumSummary}", bysjglxt_summary.getSummary_summary());
				params.put("${sumOpinion}", bysjglxt_summary.getSummary_opinion());
			} else {
				params.put("${sy}", "");
				params.put("${sm}", "");
				params.put("${sd}", "");
				params.put("${sys}", "");
				params.put("${sms}", "");
				params.put("${sds}", "");
				params.put("${sumSummary}", "");
				params.put("${sumOpinion}", "");
			}
		}
		return params;
	}

	/**
	 * 导出形式审查表
	 */
	@Override
	public Map<String, Object> exportFormal(String studentUserId) {

		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_examination_formal bysjglxt_examination_formal = new bysjglxt_examination_formal();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("${forNum}", bysjglxt_student_basic.getStudent_basic_num());
				params.put("${forNam}", bysjglxt_student_basic.getStudent_basic_name());
				params.put("${forMajor}", bysjglxt_student_basic.getStudent_basic_major());
			} else {
				params.put("${forNum}", "");
				params.put("${forNam}", "");
				params.put("${forMajor}", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("${forChineseName}", bysjglxt_topic.getTopic_name_chinese());
					params.put("${forEnglishName}", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("${forChineseName}", "");
					params.put("${forEnglishName}", "");
				}
			}
			// 根据user Id获取形式审查表
			bysjglxt_examination_formal = graduationProjectManagementDao
					.getExaminationFormal(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_examination_formal != null) {
				switch (bysjglxt_examination_formal.getExamination_formal_is_cover()) {
				case 1:
					params.put("${1a}", "✓");
					params.put("${1b}", "");
					params.put("${1c}", "");
					break;
				case 0:
					params.put("${1a}", "");
					params.put("${1b}", "✓");
					params.put("${1c}", "");
					break;
				case -1:
					params.put("${1a}", "");
					params.put("${1b}", "");
					params.put("${1c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_a4()) {
				case 1:
					params.put("${2a}", "✓");
					params.put("${2b}", "");
					params.put("${2c}", "");
					break;
				case 0:
					params.put("${2a}", "");
					params.put("${2b}", "✓");
					params.put("${2c}", "");
					break;
				case -1:
					params.put("${2a}", "");
					params.put("${2b}", "");
					params.put("${2c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_format()) {
				case 1:
					params.put("${3a}", "✓");
					params.put("${3b}", "");
					params.put("${3c}", "");
					break;
				case 0:
					params.put("${3a}", "");
					params.put("${3b}", "✓");
					params.put("${3c}", "");
					break;
				case -1:
					params.put("${3a}", "");
					params.put("${3b}", "");
					params.put("${3c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_abstract_chinese()) {
				case 1:
					params.put("${4a}", "✓");
					params.put("${4b}", "");
					params.put("${4c}", "");
					break;
				case 0:
					params.put("${4a}", "");
					params.put("${4b}", "✓");
					params.put("${4c}", "");
					break;
				case -1:
					params.put("${4a}", "");
					params.put("${4b}", "");
					params.put("${4c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_chinese_keyword()) {
				case 1:
					params.put("${5a}", "✓");
					params.put("${5b}", "");
					params.put("${5c}", "");
					break;
				case 0:
					params.put("${5a}", "");
					params.put("${5b}", "✓");
					params.put("${5c}", "");
					break;
				case -1:
					params.put("${5a}", "");
					params.put("${5b}", "");
					params.put("${5c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_abstract_foreign()) {
				case 1:
					params.put("${6a}", "✓");
					params.put("${6b}", "");
					params.put("${6c}", "");
					break;
				case 0:
					params.put("${6a}", "");
					params.put("${6b}", "✓");
					params.put("${6c}", "");
					break;
				case -1:
					params.put("${6a}", "");
					params.put("${6b}", "");
					params.put("${6c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_catalog()) {
				case 1:
					params.put("${7a}", "✓");
					params.put("${7b}", "");
					params.put("${7c}", "");
					break;
				case 0:
					params.put("${7a}", "");
					params.put("${7b}", "✓");
					params.put("${7c}", "");
					break;
				case -1:
					params.put("${7a}", "");
					params.put("${7b}", "");
					params.put("${7c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_headline()) {
				case 1:
					params.put("${8a}", "✓");
					params.put("${8b}", "");
					params.put("${8c}", "");
					break;
				case 0:
					params.put("${8a}", "");
					params.put("${8b}", "✓");
					params.put("${8c}", "");
					break;
				case -1:
					params.put("${8a}", "");
					params.put("${8b}", "");
					params.put("${8c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_punctuation()) {
				case 1:
					params.put("${9a}", "✓");
					params.put("${9b}", "");
					params.put("${9c}", "");
					break;
				case 0:
					params.put("${9a}", "");
					params.put("${9b}", "✓");
					params.put("${9c}", "");
					break;
				case -1:
					params.put("${9a}", "");
					params.put("${9b}", "");
					params.put("${9c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_typo()) {
				case 1:
					params.put("${10a}", "✓");
					params.put("${10b}", "");
					params.put("${10c}", "");
					break;
				case 0:
					params.put("${10a}", "");
					params.put("${10b}", "✓");
					params.put("${10c}", "");
					break;
				case -1:
					params.put("${10a}", "");
					params.put("${10b}", "");
					params.put("${10c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_reference_ten()) {
				case 1:
					params.put("${11a}", "✓");
					params.put("${11b}", "");
					params.put("${11c}", "");
					break;
				case 0:
					params.put("${11a}", "");
					params.put("${11b}", "✓");
					params.put("${11c}", "");
					break;
				case -1:
					params.put("${11a}", "");
					params.put("${11b}", "");
					params.put("${11c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_reference_foreign()) {
				case 1:
					params.put("${12a}", "✓");
					params.put("${12b}", "");
					params.put("${12c}", "");
					break;
				case 0:
					params.put("${12a}", "");
					params.put("${12b}", "✓");
					params.put("${12c}", "");
					break;
				case -1:
					params.put("${12a}", "");
					params.put("${12b}", "");
					params.put("${12c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_reference_new()) {
				case 1:
					params.put("${13a}", "✓");
					params.put("${13b}", "");
					params.put("${13c}", "");
					break;
				case 0:
					params.put("${13a}", "");
					params.put("${13b}", "✓");
					params.put("${13c}", "");
					break;
				case -1:
					params.put("${13a}", "");
					params.put("${13b}", "");
					params.put("${13c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_reference_num()) {
				case 1:
					params.put("${14a}", "✓");
					params.put("${14b}", "");
					params.put("${14c}", "");
					break;
				case 0:
					params.put("${14a}", "");
					params.put("${14b}", "✓");
					params.put("${14c}", "");
					break;
				case -1:
					params.put("${14a}", "");
					params.put("${14b}", "");
					params.put("${14c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_reference_format()) {
				case 1:
					params.put("${15a}", "✓");
					params.put("${15b}", "");
					params.put("${15c}", "");
					break;
				case 0:
					params.put("${15a}", "");
					params.put("${15b}", "✓");
					params.put("${15c}", "");
					break;
				case -1:
					params.put("${15a}", "");
					params.put("${15b}", "");
					params.put("${15c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_progress_metaphase()) {
				case 1:
					params.put("${16a}", "✓");
					params.put("${16b}", "");
					params.put("${16c}", "");
					break;
				case 0:
					params.put("${16a}", "");
					params.put("${16b}", "✓");
					params.put("${16c}", "");
					break;
				case -1:
					params.put("${16a}", "");
					params.put("${16b}", "");
					params.put("${16c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_progress_summary()) {
				case 1:
					params.put("${17a}", "✓");
					params.put("${17b}", "");
					params.put("${17c}", "");
					break;
				case 0:
					params.put("${17a}", "");
					params.put("${17b}", "✓");
					params.put("${17c}", "");
					break;
				case -1:
					params.put("${17a}", "");
					params.put("${17b}", "");
					params.put("${17c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_progress_actual()) {
				case 1:
					params.put("${18a}", "✓");
					params.put("${18b}", "");
					params.put("${18c}", "");
					break;
				case 0:
					params.put("${18a}", "");
					params.put("${18b}", "✓");
					params.put("${18c}", "");
					break;
				case -1:
					params.put("${18a}", "");
					params.put("${18b}", "");
					params.put("${18c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_progress_complete()) {
				case 1:
					params.put("${19a}", "✓");
					params.put("${19b}", "");
					params.put("${19c}", "");
					break;
				case 0:
					params.put("${19a}", "");
					params.put("${19b}", "✓");
					params.put("${19c}", "");
					break;
				case -1:
					params.put("${19a}", "");
					params.put("${19b}", "");
					params.put("${19c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_progress_logic()) {
				case 1:
					params.put("${20a}", "✓");
					params.put("${20b}", "");
					params.put("${20c}", "");
					break;
				case 0:
					params.put("${20a}", "");
					params.put("${20b}", "✓");
					params.put("${20c}", "");
					break;
				case -1:
					params.put("${20a}", "");
					params.put("${20b}", "");
					params.put("${20c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_chart()) {
				case 1:
					params.put("${21a}", "✓");
					params.put("${21b}", "");
					params.put("${21c}", "");
					break;
				case 0:
					params.put("${21a}", "");
					params.put("${21b}", "✓");
					params.put("${21c}", "");
					break;
				case -1:
					params.put("${21a}", "");
					params.put("${21b}", "");
					params.put("${21c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_enclosure()) {
				case 1:
					params.put("${22a}", "✓");
					params.put("${22b}", "");
					params.put("${22c}", "");
					break;
				case 0:
					params.put("${22a}", "");
					params.put("${22b}", "✓");
					params.put("${22c}", "");
					break;
				case -1:
					params.put("${22a}", "");
					params.put("${22b}", "");
					params.put("${22c}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_teacher_opinion()) {
				case 1:
					params.put("${1te}", "✓");
					params.put("${0te}", "");
					break;
				case 0:
					params.put("${1te}", "");
					params.put("${0te}", "✓");
					break;
				}
				switch (bysjglxt_examination_formal.getExamination_formal_is_leader_opinion()) {
				case 1:
					params.put("${1le}", "✓");
					params.put("${0le}", "");
					break;
				case 0:
					params.put("${1le}", "");
					params.put("${0le}", "✓");
					break;
				}
			}
		}
		return params;
	}

	/**
	 * 导出指导老师评价表
	 */
	@Override
	public Map<String, Object> exportTeacherOpin(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_teacher_basic bysjglxt_teacher_basic = new bysjglxt_teacher_basic();
		bysjglxt_evaluate_tutor bysjglxt_evaluate_tutor = new bysjglxt_evaluate_tutor();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("${evaNm}", bysjglxt_student_basic.getStudent_basic_num());
				params.put("${evaNa}", bysjglxt_student_basic.getStudent_basic_name());
				params.put("${evaMagor}", bysjglxt_student_basic.getStudent_basic_major());
			} else {
				params.put("${evaNm}", "");
				params.put("${evaNa}", "");
				params.put("${evaMagor}", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("${evaChineseName}", bysjglxt_topic.getTopic_name_chinese());
					params.put("${evaEnglishName}", bysjglxt_topic.getTopic_name_english());
					// 根据课题指导老师userId 获取教师user表信息
					bysjglxt_teacher_user = graduationProjectManagementDao
							.getTeacherUserByUserId(bysjglxt_topic.getTopic_teacher());
					if (bysjglxt_teacher_user != null) {
						bysjglxt_teacher_basic = graduationProjectManagementDao
								.getTeacherBasicByBasicId(bysjglxt_teacher_user.getUser_teacher_basic());
						if (bysjglxt_teacher_basic != null) {
							params.put("${evaNu}", bysjglxt_teacher_basic.getName());
							params.put("${evaTi}", bysjglxt_teacher_basic.getProfessional_title());
							params.put("${evaUnit}", bysjglxt_teacher_basic.getUnit_name());
						}
					} else {
						params.put("${evaNu}", "");
						params.put("${evaTi}", "");
						params.put("${evaUnit}", "");
					}

				} else {
					params.put("${evaChineseName}", "");
					params.put("${evaEnglishName}", "");
					params.put("${evaNu}", "");
					params.put("${evaTi}", "");
					params.put("${evaUnit}", "");
				}
			}
			// 根据学生user ID获得指导教师评价表
			bysjglxt_evaluate_tutor = graduationProjectManagementDao
					.getEvaluateTutor(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_evaluate_tutor != null) {
				params.put("${evaComment}", bysjglxt_evaluate_tutor.getEvaluate_tutor_teacher_comment() + "");
				params.put("${1g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_training_objective() + "");
				params.put("${2g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_difficulty() + "");
				params.put("${3g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_workload() + "");
				params.put("${4g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_bind() + "");
				params.put("${18g}",
						bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_training_objective()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_difficulty()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_workload()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_bind() + "");
				params.put("${5g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_comprehensive() + "");
				params.put("${6g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_reference() + "");
				params.put("${7g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_experimental_design() + "");
				params.put("${8g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_computing() + "");
				params.put("${9g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_foreign_language() + "");
				params.put("${10g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_computer() + "");
				params.put("${11g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_innovate() + "");
				params.put("${19g}",
						bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_comprehensive()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_reference()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_experimental_design()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_computing()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_foreign_language()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_computer()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_innovate() + "");

				params.put("${12g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_analysis() + "");
				params.put("${13g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_chart() + "");
				params.put("${14g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_instructions() + "");
				params.put("${15g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_practicability() + "");
				params.put("${16g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_normalization() + "");
				params.put("${20g}",
						bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_analysis()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_chart()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_instructions()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_practicability()
								+ bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_normalization() + "");
				params.put("${17g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_total() + "");
				params.put("${21g}", bysjglxt_evaluate_tutor.getEvaluate_tutor_grade_total() + "");
				switch (bysjglxt_evaluate_tutor.getEvaluate_tutor_is_teacher_opinion()) {
				case 1:
					params.put("${22g}", "✓");
					params.put("${23g}", "");
					break;
				case 0:
					params.put("${22g}", "");
					params.put("${23g}", "✓");
					break;
				}
			} else {
				params.put("${evaComment}", "");
				params.put("${1g}", "");
				params.put("${2g}", "");
				params.put("${3g}", "");
				params.put("${4g}", "");
				params.put("${18g}", "");
				params.put("${5g}", "");
				params.put("${6g}", "");
				params.put("${7g}", "");
				params.put("${8g}", "");
				params.put("${9g}", "");
				params.put("${10g}", "");
				params.put("${11g}", "");
				params.put("${19g}", "");
				params.put("${12g}", "");
				params.put("${13g}", "");
				params.put("${14g}", "");
				params.put("${15g}", "");
				params.put("${16g}", "");
				params.put("${20g}", "");
				params.put("${17g}", "");
				params.put("${21g}", "");
				params.put("${22g}", "");
				params.put("${23g}", "");
			}
		}
		return params;
	}

	@Override
	public Map<String, Object> exportReviewOpin(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		bysjglxt_teacher_basic bysjglxt_teacher_basic = new bysjglxt_teacher_basic();
		bysjglxt_evaluate_tutor bysjglxt_evaluate_tutor = new bysjglxt_evaluate_tutor();
		bysjglxt_evaluate_review bysjglxt_evaluate_review = new bysjglxt_evaluate_review();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("${4q}", bysjglxt_student_basic.getStudent_basic_num());
				params.put("${5q}", bysjglxt_student_basic.getStudent_basic_name());
				params.put("${6q}", bysjglxt_student_basic.getStudent_basic_major());
			} else {
				params.put("${4q}", "");
				params.put("${5q}", "");
				params.put("${6q}", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("${7q}", bysjglxt_topic.getTopic_name_chinese());
					params.put("${8q}", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("${7q}", "");
					params.put("${8q}", "");
				}
			}
			// 根据课题评阅老师userId 获取教师user表信息
			bysjglxt_teacher_user = graduationProjectManagementDao
					.getTeacherUserByUserId(bysjglxt_topic_select.getTopic_select_teacher_review());
			if (bysjglxt_teacher_user != null) {
				bysjglxt_teacher_basic = graduationProjectManagementDao
						.getTeacherBasicByBasicId(bysjglxt_teacher_user.getUser_teacher_basic());
				if (bysjglxt_teacher_basic != null) {
					params.put("${1q}", bysjglxt_teacher_basic.getName());
					params.put("${2q}", bysjglxt_teacher_basic.getProfessional_title());
					params.put("${3q}", bysjglxt_teacher_basic.getUnit_name());
				}
			} else {
				params.put("${1q}", "");
				params.put("${2q}", "");
				params.put("${3q}", "");
			}

			// 根据学生user ID获得指导教师评价表
			bysjglxt_evaluate_review = graduationProjectManagementDao
					.getEvaluateReview(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_evaluate_review != null) {
				params.put("${9q}", bysjglxt_evaluate_review.getEvaluate_review_teacher_comment() + "");
				params.put("${1t}", bysjglxt_evaluate_review.getEvaluate_review_grade_training_objective() + "");
				params.put("${2t}", bysjglxt_evaluate_review.getEvaluate_review_grade_difficulty() + "");
				params.put("${3t}", bysjglxt_evaluate_review.getEvaluate_review_grade_workload() + "");
				params.put("${4t}", bysjglxt_evaluate_review.getEvaluate_review_grade_bind() + "");
				params.put("${18t}",
						bysjglxt_evaluate_review.getEvaluate_review_grade_training_objective()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_difficulty()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_workload()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_bind() + "");
				params.put("${5t}", bysjglxt_evaluate_review.getEvaluate_review_grade_comprehensive() + "");
				params.put("${6t}", bysjglxt_evaluate_review.getEvaluate_review_grade_reference() + "");
				params.put("${7t}", bysjglxt_evaluate_review.getEvaluate_review_grade_experimental_design() + "");
				params.put("${8t}", bysjglxt_evaluate_review.getEvaluate_review_grade_computing() + "");
				params.put("${9t}", bysjglxt_evaluate_review.getEvaluate_review_grade_foreign_language() + "");
				params.put("${10t}", bysjglxt_evaluate_review.getEvaluate_review_grade_computer() + "");
				params.put("${11t}", bysjglxt_evaluate_review.getEvaluate_review_grade_innovate() + "");
				params.put("${19t}",
						bysjglxt_evaluate_review.getEvaluate_review_grade_comprehensive()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_reference()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_experimental_design()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_computing()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_foreign_language()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_computer()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_innovate() + "");

				params.put("${12t}", bysjglxt_evaluate_review.getEvaluate_review_grade_analysis() + "");
				params.put("${13t}", bysjglxt_evaluate_review.getEvaluate_review_grade_chart() + "");
				params.put("${14t}", bysjglxt_evaluate_review.getEvaluate_review_grade_instructions() + "");
				params.put("${15t}", bysjglxt_evaluate_review.getEvaluate_review_grade_practicability() + "");
				params.put("${16t}", bysjglxt_evaluate_review.getEvaluate_review_grade_normalization() + "");
				params.put("${20t}",
						bysjglxt_evaluate_review.getEvaluate_review_grade_analysis()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_chart()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_instructions()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_practicability()
								+ bysjglxt_evaluate_review.getEvaluate_review_grade_normalization() + "");
				params.put("${17t}", bysjglxt_evaluate_review.getEvaluate_review_grade_total() + "");
				params.put("${21t}", bysjglxt_evaluate_review.getEvaluate_review_grade_total() + "");
				switch (bysjglxt_evaluate_review.getEvaluate_review_is_teacher_opinion()) {
				case 1:
					params.put("${22t}", "✓");
					params.put("${23t}", "");
					break;
				case 0:
					params.put("${22t}", "");
					params.put("${23t}", "✓");
					break;
				}
			} else {
				params.put("${9q}", "");
				params.put("${1t}", "");
				params.put("${2t}", "");
				params.put("${3t}", "");
				params.put("${4t}", "");
				params.put("${18t}", "");
				params.put("${5t}", "");
				params.put("${6t}", "");
				params.put("${7t}", "");
				params.put("${8t}", "");
				params.put("${9t}", "");
				params.put("${10t}", "");
				params.put("${11t}", "");
				params.put("${19t}", "");
				params.put("${12t}", "");
				params.put("${13t}", "");
				params.put("${14t}", "");
				params.put("${15t}", "");
				params.put("${16t}", "");
				params.put("${20t}", "");
				params.put("${17t}", "");
				params.put("${21t}", "");
				params.put("${22t}", "");
				params.put("${23t}", "");
			}
		}
		return params;
	}

	@Override
	public Map<String, Object> exportDefence(String studentUserId) {
		bysjglxt_student_user bysjglxt_student_user = new bysjglxt_student_user();
		bysjglxt_student_basic bysjglxt_student_basic = new bysjglxt_student_basic();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_defence bysjglxt_defence = new bysjglxt_defence();
		bysjglxt_topic_select bysjglxt_topic_select = new bysjglxt_topic_select();
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据userId获取user表
		bysjglxt_student_user = graduationProjectManagementDao.getStudentUserByUserId(studentUserId);
		if (bysjglxt_student_user != null) {
			// 根据basicId获取basic表
			bysjglxt_student_basic = graduationProjectManagementDao
					.getStudentBasicByBasicId(bysjglxt_student_user.getUser_student_basic());
			if (bysjglxt_student_basic != null) {
				params.put("${1z}", bysjglxt_student_basic.getStudent_basic_num());
				params.put("${2z}", bysjglxt_student_basic.getStudent_basic_name());
				params.put("${3z}", bysjglxt_student_basic.getStudent_basic_major());
				int sessional = Integer.parseInt(bysjglxt_student_basic.getStudent_basic_level());
				sessional = sessional + 4;
				params.put("${4z}", sessional + "");
			} else {
				params.put("${1z}", "");
				params.put("${2z}", "");
				params.put("${3z}", "");
				params.put("${4z}", "");
			}
			// 根据userid获取学生选题信息
			bysjglxt_topic_select = graduationProjectManagementDao
					.getStudentSelectTopic(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_topic_select != null) {
				// 根据课题ID获取课题表信息
				bysjglxt_topic = graduationProjectManagementDao
						.getStudentTopicByTopicId(bysjglxt_topic_select.getTopic_select_topic());
				if (bysjglxt_topic != null) {
					params.put("${5z}", bysjglxt_topic.getTopic_name_chinese());
					params.put("${6z}", bysjglxt_topic.getTopic_name_english());
				} else {
					params.put("${5z}", "");
					params.put("${6z}", "");
				}
			}
			bysjglxt_defence = graduationProjectManagementDao.getDefence(bysjglxt_student_user.getUser_student_id());
			if (bysjglxt_defence != null) {
				params.put("${7z}", bysjglxt_defence.getDefence_record());
				params.put("${8z}", bysjglxt_defence.getDefence_leader_comment());

				params.put("${9z}", bysjglxt_defence.getDefence_grade_writing() + "");
				params.put("${10z}", bysjglxt_defence.getDefence_grade_normalization() + "");
				params.put("${11z}", bysjglxt_defence.getDefence_grade_complete() + "");
				params.put("${12z}", bysjglxt_defence.getDefence_grade_technology() + "");
				params.put("${13z}", bysjglxt_defence.getDefence_grade_practicability() + "");
				params.put("${18z}",
						bysjglxt_defence.getDefence_grade_writing() + bysjglxt_defence.getDefence_grade_normalization()
								+ bysjglxt_defence.getDefence_grade_complete()
								+ bysjglxt_defence.getDefence_grade_technology()
								+ bysjglxt_defence.getDefence_grade_practicability() + "");
				params.put("${14z}", bysjglxt_defence.getDefence_grade_appearance() + "");
				params.put("${15z}", bysjglxt_defence.getDefence_grade_statement() + "");
				params.put("${16z}", bysjglxt_defence.getDefence_grade_answer() + "");
				params.put("${19z}",
						bysjglxt_defence.getDefence_grade_statement() + bysjglxt_defence.getDefence_grade_appearance()
								+ bysjglxt_defence.getDefence_grade_answer() + "");

				params.put("${17z}", bysjglxt_defence.getDefence_grade_defence() + "");
				params.put("${20z}", bysjglxt_defence.getDefence_grade_defence() + "");
				params.put("${21z}", bysjglxt_defence.getDefence_grade_evaluate_tutor() + "");
				params.put("${22z}", bysjglxt_defence.getDefence_grade_evaluate_review() + "");
				params.put("${23z}", bysjglxt_defence.getDefence_total() + "");
				params.put("${24z}", bysjglxt_defence.getDefence_finally() + "");
			} else {
				params.put("${7z}", "");
				params.put("${8z}", "");
				params.put("${9z}", "");
				params.put("${10z}", "");
				params.put("${11z}", "");
				params.put("${12z}", "");
				params.put("${13z}", "");
				params.put("${18z}", "");
				params.put("${14z}", "");
				params.put("${15z}", "");
				params.put("${16z}", "");
				params.put("${19z}", "");
				params.put("${17z}", "");
				params.put("${20z}", "");
				params.put("${21z}", "");
				params.put("${22z}", "");
				params.put("${23z}", "");
				params.put("${24z}", "");
			}
		}
		return params;
	}

}