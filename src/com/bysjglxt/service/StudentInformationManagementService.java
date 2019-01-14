package com.bysjglxt.service;

import java.io.File;
import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_major;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DTO.ExportGeaduationStudentDTO;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.VO.StudentInformationManagementVO;
import com.google.gson.JsonElement;

public interface StudentInformationManagementService {

	/**
	 * 
	 */
	public int distributionStudentMajor(String studentUserId, String collegeId);

	/**
	 * 可以导出毕业设计过程管理手册的学生信息
	 * 
	 * @param exportGeaduationStudentDTO
	 *            这个参数主要为了完成对信息的筛选条件
	 * @return
	 */
	public ExportGeaduationStudentDTO listStudentGreauation(ExportGeaduationStudentDTO exportGeaduationStudentDTO,
			String college);

	public List<StudentInformationDTO> listStudentNoClose(String userId);

	/**
	 * @说明 学生信息的Excel文件流转化为bysjglxt_student_basic的List
	 * @param StudentExcel
	 * @return List<bysjglxt_student_basic>
	 * @throws Exception
	 */
	public List<bysjglxt_student_basic> convertStudentExcelToList(File EXCEL_Student, String EXCEL_StudentFileName)
			throws Exception;

	/**
	 * @说明 将学生基础信息List生成bysjglxt_student_basic以及基于此生成的bysjglxt_user_student， 存储到数据库中
	 * @param studentBasicList
	 *            学生基础信息List
	 * @return 是否存储成功,1是 0否
	 */
	public boolean saveStudentList(List<bysjglxt_student_basic> studentBasicList, String userId);

	/**
	 * @说明 获取数据库中所有的bysjglxt_student_basic记录以及bysjglxt_user_student记录，
	 * @return StudentInformationDTO的List
	 */
	public List<StudentInformationDTO> list_StudentInformationDTO_All();

	/**
	 * @说明 通过领导小组组长手动添加的学生记录，生成用户表及基础信息表，一并存入数据库中
	 * @param student_basic
	 *            手动输入的学生基础信息
	 * @return 是否存储成功,1是 0否
	 */
	public boolean save_NewStudent(bysjglxt_student_basic student_basic, String userId);

	/**
	 * @说明 通过多选删除选中的学生用户表以及基础信息表，
	 * @param useStudentNumList
	 *            由要删除的学生学号组成的列表
	 * @return 是否删除成功,1是 0否
	 */
	public boolean remove_StudentList(List<String> useStudentIDList);

	/**
	 * 
	 * @DATE 2017-10-27
	 * 
	 * @说明 根据页数以及搜索关键词，查询学生信息，并连同页面信息一起封装进StudentInformationManagementVO
	 *     筛选：性别（1男、0女）、专业（String）、年级（String）、操作权限（1有、0无）
	 * @param studentInformationManagementVO
	 *            存有需要查询的当前页pageIndex
	 *            以及搜索信息search模糊查询学生姓名，并匹配变色并按照学号排序（搜索信息需要判断是否为null），
	 * 
	 *            user_student_is_select_topic -1 不进行筛选 2 未选 1已选
	 * 
	 * @return 封装好的StudentInformationManagementVO（类中所有页面信息均要封装）
	 */
	public StudentInformationManagementVO VO_Student_By_PageAndSearch(
			StudentInformationManagementVO studentInformationManagementVO, String userId);

	/**
	 * 
	 * @DATE 2017-10-27
	 * @说明 获取bysjglxt_student_basic表中所有学生信息中所有包括的专业student_basic_major
	 * 
	 * @return 返回List<String>专业名
	 * @throws Exception
	 */
	public List<bysjglxt_major> list_Student_Major(String userId) throws Exception;

	/**
	 * 遍历出所有年级
	 * 
	 * @return
	 */
	public List<String> listStudentLevel();

	/**
	 * 
	 * @DATE 2017-10-27
	 * @说明 获取bysjglxt_student_basic表中所有学生信息中所有包括的专业student_basic_grade
	 * 
	 * @return 返回List<String>年级
	 * @throws Exception
	 */
	public List<String> list_Student_Grade() throws Exception;

	/**
	 * @待测试
	 * @DATE 2017-10-28
	 * @说明 批量赋予操作权限
	 * @param listString
	 *            学生user表id 组成的list
	 * @return 成功与否
	 */
	public boolean update_Give_Student_Operate_Permission(List<String> listString);

	/**
	 * @待测试
	 * @DATE 2017-10-28
	 * @说明 批量剥夺操作权限
	 * @param listStringd
	 *            学生user表id 组成的list
	 * @return 成功与否
	 */
	public boolean update_Take_Student_Operate_Permission(List<String> listString);

	/**
	 * 
	 * 
	 * @DATE 2017-10-28
	 * @说明 修改学生基础信息
	 * @param bysjglxt_student_basic
	 *            学生基础信息对象
	 * @return 成功与否
	 */
	public boolean update_StudentBasicInfomation(bysjglxt_student_basic bysjglxt_student_basic);

	/**
	 * 重置密码 将密码设置为学号
	 * 
	 * @param user_student_id
	 * @return
	 */
	public boolean resetPassword(String user_student_id);

	/**
	 * 更改密码
	 * 
	 */
	public int updatePassword(String user_student_id, String password, String oldPassword);

	public List<StudentInformationDTO> listStudentNoCloseByCollege(String user_student_belong_college);

}
