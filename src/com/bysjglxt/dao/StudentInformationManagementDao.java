package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_college;
import com.bysjglxt.domain.DO.bysjglxt_defence;
import com.bysjglxt.domain.DO.bysjglxt_dissertation;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_review;
import com.bysjglxt.domain.DO.bysjglxt_evaluate_tutor;
import com.bysjglxt.domain.DO.bysjglxt_examination_formal;
import com.bysjglxt.domain.DO.bysjglxt_major;
import com.bysjglxt.domain.DO.bysjglxt_process_instance;
import com.bysjglxt.domain.DO.bysjglxt_record_progress;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_summary;
import com.bysjglxt.domain.DO.bysjglxt_task_instance;
import com.bysjglxt.domain.DTO.ExportGeaduationStudentDTO;
import com.bysjglxt.domain.VO.StudentInformationManagementVO;

public interface StudentInformationManagementDao {

	List<bysjglxt_student_user> list_StudentUserInformation_All();

	bysjglxt_student_basic get_StudentBasicInformation_ByUserBasic(String id);

	boolean saveStudentBasic(bysjglxt_student_basic bysjglxt_student_basic);

	boolean saveStudent(bysjglxt_student_user bysjglxt_student_user);

	bysjglxt_student_user getStudentByNum(String student_num);

	boolean deleteStudentBasicInfoById(String user_student_basic);

	boolean deleteStudentInfoById(String user_student_id);

	List<bysjglxt_student_basic> listStudentBasicInformationByPageAndSearch(
			StudentInformationManagementVO studentInformationManagementVO, String college);

	int get_StudentInfor_TotalRecords_BySearch(String search);

	bysjglxt_student_user getStudentInfoByBasicId(String student_basic_id);

	List<bysjglxt_major> listStudent_Major(String college) throws Exception;

	List<String> listStudent_Grade() throws Exception;

	boolean update_Give_Student_Operate_Permission(String string, String moTime);

	boolean update_Take_Student_Operate_Permission(String string, String moTime);

	boolean update_StudentBasicInfomation(bysjglxt_student_basic bysjglxt_student_basic);

	List<bysjglxt_student_basic> getResultBySearch(StudentInformationManagementVO studentInformationManagementVO,
			String college);

	boolean updatePassword(String user_student_id, String password, String moTime);

	public boolean studentBasicIsExist(String student_basic_id);

	com.bysjglxt.domain.DO.bysjglxt_topic_select getTopicSelect(String user_student_id);

	boolean deleteTopicSelect(String topic_select_id);

	com.bysjglxt.domain.DO.bysjglxt_taskbook getTaskBookByStudent(String user_student_id);

	boolean deleteTaskBookStudent(String taskbook_id);

	com.bysjglxt.domain.DO.bysjglxt_report_opening getReportOpening(String topic_select_id);

	boolean deleteReportOpening(String report_opening_id);

	List<bysjglxt_record_progress> getProgress(String user_student_id);

	boolean deleteProgress(String record_progress_id);

	bysjglxt_summary getSummary(String user_student_id);

	boolean deleteSummary(String summary_id);

	bysjglxt_examination_formal getExaminationFormal(String user_student_id);

	boolean deleteExaminationFormal(String examination_formal_id);

	bysjglxt_evaluate_tutor getEvaluateTutor(String user_student_id);

	boolean deleteEvaluateTutor(String evaluate_tutor_id);

	bysjglxt_evaluate_review getEvaluateReview(String user_student_id);

	boolean deleteEvaluateReview(String evaluate_review_id);

	bysjglxt_defence getDefence(String user_student_id);

	boolean deleteDefence(String defence_id);

	List<String> listStudent_Level();

	List<bysjglxt_student_user> getListStudentByNotClose(String college);

	List<bysjglxt_student_user> getListStudentByExport(ExportGeaduationStudentDTO exportGeaduationStudentDTO,
			String college);

	// 根据教师User Id获取教师对象
	com.bysjglxt.domain.DO.bysjglxt_teacher_user getTeacherUserById(String userId);

	// 根据专业id获取专业对象
	com.bysjglxt.domain.DO.bysjglxt_major getMajorByMajorId(String trim);

	//根据专业代码获取专业
	bysjglxt_major getMajorByCode(String code);
	
	// 存储对象
	boolean saveObject(Object obj);

	// 根据学生UserId获取任务实例
	public List<bysjglxt_task_instance> getTaskInstanceByProcessManId(String user_student_id);

	// 根据任务实例Id删除任务实例
	public boolean deleteTaskInstanceById(String task_instance_id);

	// 获取所有流程实例
	public List<bysjglxt_process_instance> getProcessInstanceByMan(String user_student_id);

	// 据id删除
	public boolean deleteProcessById(String process_instance_id);

	// 获取毕业论文
	public bysjglxt_dissertation getDissertationByUserId(String user_student_id);

	// 删除毕业论文
	public boolean deleteDissertationById(String dissertation_id);

	bysjglxt_college getCollegeById(String college);

}
