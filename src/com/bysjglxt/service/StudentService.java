package com.bysjglxt.service;

import java.io.File;
import java.util.List;

import com.bysjglxt.domain.bysjglxt_student_basic;
import com.bysjglxt.domain.bysjglxt_teacher_basic;

public interface StudentService {

	/**
	 * 学生信息的Excel文件流转化为bysjglxt_student_basic的List
	 *
	 * @param StudentExcel
	 * @return List<bysjglxt_student_basic>
	 */
	public List<bysjglxt_student_basic> convertStudentExcelToList(File StudentExcel);

	/**
	 * 老师信息的Excel文件流转化为bysjglxt_teacher_basic的List
	 *
	 * @param TeacherExcel
	 *            老师信息的Excel文件流
	 * @return List<bysjglxt_teacher_basic>
	 */
	public List<bysjglxt_teacher_basic> convertTeacherExcelToList(File TeacherExcel);

}
