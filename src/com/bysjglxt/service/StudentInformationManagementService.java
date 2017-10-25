package com.bysjglxt.service;

import java.io.File;
import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DTO.StudentInformationDTO;

public interface StudentInformationManagementService {

	/**
	 * 学生信息的Excel文件流转化为bysjglxt_student_basic的List
	 *
	 * @param StudentExcel
	 * 
	 * @return List<bysjglxt_student_basic>
	 */
	public List<bysjglxt_student_basic> convertStudentExcelToList(File studentExcel);

	/**
	 * 将学生基础信息List生成bysjglxt_student_basic以及基于此生成的bysjglxt_user_student，存储到数据库中
	 * 
	 * @param studentBasicList
	 *            学生基础信息List
	 * @return 是否存储成功,1是 0否
	 */
	public boolean saveStudentList(List<bysjglxt_student_basic> studentBasicList);

	/**
	 * 获取数据库中所有的bysjglxt_student_basic记录以及bysjglxt_user_student记录，
	 * 并封装成StudentInformationDTO的List返回
	 * 
	 * @return
	 */
	public List<StudentInformationDTO> list_StudentInformationDTO_All();

	/**
	 * 通过领导小组组长手动添加的学生记录，生成用户表及基础信息表，一并存入数据库中
	 * 
	 * @param student_basic
	 *            手动输入的学生基础信息
	 * @return 是否存储成功,1是 0否
	 */
	public boolean save_NewStudent(bysjglxt_student_basic student_basic);

	/**
	 * 通过多选删除选中的学生用户表以及基础信息表，
	 * 
	 * @param useStudentNumList
	 *            由要删除的学生学号组成的列表
	 * @return 是否存储成功,1是 0否
	 */
	public boolean remove_StudentList(List<String> useStudentNumList);

}
