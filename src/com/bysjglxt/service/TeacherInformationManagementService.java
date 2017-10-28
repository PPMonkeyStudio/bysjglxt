package com.bysjglxt.service;

import java.io.File;
import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;

public interface TeacherInformationManagementService {

	/**
	 * 教师信息的Excel文件流转化为bysjglxt_Teacher_basic的List
	 *
	 * @param TeacherExcel
	 * @return List<bysjglxt_teacher_basic>
	 * @throws Exception
	 */
	public List<bysjglxt_teacher_basic> convertTeacherExcelToList(File teacherExcel, String EXCEL_TeacherFileName)
			throws Exception;

	/**
	 * 将教师基础信息List生成bysjglxt_teacher_basic以及基于此生成的bysjglxt_user_teacher，存储到数据库中
	 * 
	 * @param teacherBasicList
	 *            教师基础信息List
	 * @return 是否存储成功,1是 0否
	 */
	public boolean saveTeacherList(List<bysjglxt_teacher_basic> teacherBasicList);

	/**
	 * 获取数据库中所有的bysjglxt_teacher_basic记录以及bysjglxt_user_teacher记录，
	 * 并封装成TeacherInformationDTO的List返回
	 * 
	 * @return
	 */
	public List<TeacherInformationDTO> list_TeacherInformationDTO_All();

	/**
	 * 通过领导小组组长手动添加的教师记录，生成用户表及基础信息表，一并存入数据库中
	 * 
	 * @param student_basic
	 *            手动输入的教师基础信息
	 * @return 是否存储成功,1是 0否
	 */
	public boolean save_NewTeacher(bysjglxt_teacher_basic teacher_basic);

	/**
	 * 通过多选删除选中的教师用户表以及基础信息表，
	 * 
	 * @param useStudentNumList
	 *            由要删除的教师工号组成的列表
	 * @return 是否删除成功,1是 0否
	 */
	public boolean remove_TeacherList(List<String> useTeacherNumList);

}
