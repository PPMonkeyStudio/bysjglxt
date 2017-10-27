package com.bysjglxt.service;

import java.io.File;
import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.VO.StudentInformationManagementVO;

public interface StudentInformationManagementService {

	/**
	 * @说明 学生信息的Excel文件流转化为bysjglxt_student_basic的List
	 *
	 * @param StudentExcel
	 * 
	 * @return List<bysjglxt_student_basic>
	 * @throws Exception
	 */
	public List<bysjglxt_student_basic> convertStudentExcelToList(File EXCEL_Student, String EXCEL_StudentFileName)
			throws Exception;

	/**
	 * @说明 将学生基础信息List生成bysjglxt_student_basic以及基于此生成的bysjglxt_user_student，
	 *     存储到数据库中
	 * 
	 * @param studentBasicList
	 *            学生基础信息List
	 * @return 是否存储成功,1是 0否
	 */
	public boolean saveStudentList(List<bysjglxt_student_basic> studentBasicList);

	/**
	 * @说明 获取数据库中所有的bysjglxt_student_basic记录以及bysjglxt_user_student记录，
	 * 
	 * @return StudentInformationDTO的List
	 */
	public List<StudentInformationDTO> list_StudentInformationDTO_All();

	/**
	 * @说明 通过领导小组组长手动添加的学生记录，生成用户表及基础信息表，一并存入数据库中
	 * 
	 * @param student_basic
	 *            手动输入的学生基础信息
	 * @return 是否存储成功,1是 0否
	 */
	public boolean save_NewStudent(bysjglxt_student_basic student_basic);

	/**
	 * @说明 通过多选删除选中的学生用户表以及基础信息表，
	 * 
	 * @param useStudentNumList
	 *            由要删除的学生学号组成的列表
	 * @return 是否删除成功,1是 0否
	 */
	public boolean remove_StudentList(List<String> useStudentIDList);

	/**
	 * @DOTO 筛选：性别（1男、0女）、专业（String）、年级（String）、操作权限（1有、0无）
	 * @DATE 2017-10-27
	 * 
	 * @说明 根据页数以及搜索关键词，查询学生信息，并连同页面信息一起封装进StudentInformationManagementVO
	 * 
	 * @param studentInformationManagementVO
	 *            存有需要查询的当前页pageIndex
	 *            以及搜索信息search模糊查询学生姓名，并匹配变色并按照学号排序（搜索信息需要判断是否为null），
	 * 
	 * @return 封装好的StudentInformationManagementVO（类中所有页面信息均要封装）
	 */
	public StudentInformationManagementVO VO_Student_By_PageAndSearch(
			StudentInformationManagementVO studentInformationManagementVO);

	/**
	 * @DOTO
	 * @DATE 2017-10-27
	 * @说明 获取bysjglxt_student_basic表中所有学生信息中所有包括的专业student_basic_major
	 * 
	 * @return 返回List<String>专业名
	 */
	public List<String> list_Student_Major();

	/**
	 * @DOTO
	 * @说明 获取bysjglxt_student_basic表中所有学生信息中所有包括的专业student_basic_grade
	 * 
	 * @return 返回List<String>年级
	 */
	public List<String> list_Student_Grade();

}
