package com.bysjglxt.service;

import java.io.File;
import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.TeacherInformationManagementVO;

public interface TeacherInformationManagementService {

	/**
	 * @待测试
	 * @DATE 2017-10-29
	 * 
	 * @说明 教师信息的Excel文件流转化为bysjglxt_Teacher_basic的List
	 *
	 * @param TeacherExcel
	 * @return List<bysjglxt_teacher_basic>
	 * @throws Exception
	 */
	public List<bysjglxt_teacher_basic> convertTeacherExcelToList(File teacherExcel, String EXCEL_TeacherFileName)
			throws Exception;

	/**
	 * @待测试
	 * @DATE 2017-10-29
	 * @说明 将教师基础信息List生成bysjglxt_teacher_basic以及基于此生成的bysjglxt_user_teacher，
	 *     存储到数据库中
	 * 
	 * @param teacherBasicList
	 *            教师基础信息List
	 * @return 是否存储成功,1是 0否
	 */
	public boolean saveTeacherList(List<bysjglxt_teacher_basic> teacherBasicList, String userId);

	/**
	 * @DATE 2017-10-29
	 * @说明 获取数据库中所有的bysjglxt_teacher_basic记录以及bysjglxt_user_teacher记录，
	 *     并封装成TeacherInformationDTO的List返回
	 * 
	 * @return
	 */
	public List<TeacherInformationDTO> list_TeacherInformationDTO_All(String userId);

	/**
	 * @待测试
	 * @DATE 2017-10-29
	 * @说明 通过领导小组组长手动添加的教师记录，生成用户表及基础信息表，一并存入数据库中
	 * 
	 * @param student_basic
	 *            手动输入的教师基础信息
	 * @return 是否存储成功,1是 0否
	 */
	public boolean save_NewTeacher(bysjglxt_teacher_basic teacher_basic, String userId);

	/**
	 * @待测试
	 * 
	 * @说明 通过多选删除选中的教师用户表以及基础信息表，
	 * 
	 * @param useStudentNumList
	 *            由要删除的教师工号组成的列表
	 * @return 是否删除成功,1是 0否
	 */
	public boolean remove_TeacherList(List<String> useTeacherNumList);

	/**
	 * 遍历得到所有的教研室
	 * 
	 * @return
	 */
	public List<bysjglxt_section> listBysjglxtSection(String userId);

	/**
	 * 
	 * @DATE 2017-10-29
	 * 
	 * @说明 根据页数以及搜索关键词，查询教师信息，并连同页面信息一起封装进TeacherInformationManagementVO
	 *     筛选：教研室（名称）、性别（中文）
	 * @param TeacherInformationManagementVO
	 *            存有需要查询的当前页pageIndex 以及搜索信息search模糊查询教师姓名，并匹配变色并按照工号排序
	 * 
	 * @return 封装好的TeacherInformationManagementVO（类中所有页面信息均要封装）
	 */
	public TeacherInformationManagementVO VO_TEACHER_By_PageAndSearch(
			TeacherInformationManagementVO teacherInformationManagementVO, String userId);

	/**
	 * @DATE 2017-11-1 弃用 使用DTO更新教师信息
	 */
	public boolean updateBasicAndUser(TeacherInformationDTO teacherInformationDTO);

	/**
	 * 更改教师基础表信息
	 * 
	 * @param bysjglxt_teacher_basic
	 * @return
	 */
	public boolean updateTeacherBasic(bysjglxt_teacher_basic bysjglxt_teacher_basic);

	/**
	 * 修改教师登陆信息
	 * 
	 * @param bysjglxt_teacher_user
	 * @return
	 */
	public boolean updateTeacherUser(bysjglxt_teacher_user bysjglxt_teacher_user);

	/**
	 * 遍历获得所有职称
	 * 
	 * @return
	 */
	public List<String> list_Teacher_Title();

	/**
	 * 重置密码 将密码设置为工号
	 * 
	 * @param user_teacher_id
	 * @return
	 */
	public boolean resetPassword(String user_teacher_id);

	/**
	 * 更改密码
	 * 
	 * @param user_teacher_id
	 * @param password
	 * @return
	 */
	public boolean updatePassword(String user_teacher_id, String password);

	/**
	 * 批量增加记录员
	 * 
	 * @param listTeacherUserId
	 * @return 1 成功
	 * @return -1失败
	 */
	public int addRecorder(List<String> listTeacherUserId);

	/**
	 * 批量增加答辩小组长
	 * 
	 * @param listTeacherUserId
	 * @return 1成功
	 * @return -1失败
	 */
	public int addLeader(List<String> listTeacherUserId);

	/**
	 * 批量驳回记录员权限
	 * 
	 * @param listTeacherUserId
	 * @return 1成功
	 * @return -1失败
	 */
	public int removeRecoder(List<String> listTeacherUserId);

	/**
	 * 批量驳回答辩小组身份
	 * 
	 * @param listTeacherUserId
	 * @return 1 成功
	 * @return -1失败
	 */
	public int removeLeader(List<String> listTeacherUserId);

}
