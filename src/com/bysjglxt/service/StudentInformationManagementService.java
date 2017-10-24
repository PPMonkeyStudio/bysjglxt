package com.bysjglxt.service;

import java.io.File;
import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_student_basic;

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
}
