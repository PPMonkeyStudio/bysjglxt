package com.bysjglxt.service;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DTO.TopicInformationManagementDTO;
import com.bysjglxt.domain.VO.TopicInformationManagementVO;

public interface TopicInformationManagementService {

	/**
	 * @DATE 2017-10-28
	 * @说明 教师创建课题，存储到bysjglxt_topic表中
	 * @param newTopic
	 * @return 1成功 0失败
	 */
	public boolean CreateTopic(TopicInformationManagementDTO topicInformationDTO);

	/**
	 * @DATE 2017-10-28
	 * @说明 删除课题
	 * @param topic_id
	 * @return 1成功 0失败
	 */
	public boolean DeleteTopic(List<String> topic_id);

	/**
	 * @DATE 2017-10-28
	 * @说明 采纳课题
	 * @param topicID
	 * @return 1成功 0失败
	 */
	public boolean adoptTopic(List<String> topicID);

	/**
	 * @DATE 2017-10-28
	 * @说明 关闭课题，(已采纳的课题关闭后不可选)
	 * @param topicID
	 * @return 1成功 0失败
	 */
	public boolean closeTopic(List<String> topicID);

	/**
	 * @DATE 2017-10-29
	 * @说明 未通过课题
	 * @param topicID
	 * @return
	 */
	public boolean notAdoptTopic(List<String> topicID);

	/**
	 * @DATE 2017-10-29
	 * 
	 * @说明 根据页数以及搜索关键词，查询课题信息，并连同页面信息一起封装进TopicManagementVO
	 * 
	 * @param topicManagementVO
	 *            存有需要查询的当前页pageIndex 以及搜索信息search模糊查询课题名，并匹配变色并按照创建时间排序，
	 * @return 封装好的topicManagementVO
	 */
	public TopicInformationManagementVO VO_Topic_By_PageAndSearch(TopicInformationManagementVO topicManagementVO);

	/**
	 * @DOTO
	 * 
	 * @DATE 2017-11-01
	 * @说明 1、判断课题是否可选，若可以则下一步操作，不可以则返回false。 如何判断课题是否可选: (1)：大众选题 ②判断是否达到教师可选上限
	 *     ③判断是否到达课题学生上限 2、创建一条bysjglxt_topic_select学生选题表的记录，评阅老师为null。
	 *     3、学生课题bysjglxt_topic所属的记录中，课题已选学生数topic_student_num加1。 4. 教师的学生指导数+1
	 * @param studentID
	 *            选题的学生
	 * 
	 * @param topicID
	 *            所选课题
	 * 
	 * @return 1成功 0失败
	 */
	public boolean selectTopic(String studentID, String topicID);

	/**
	 * 
	 * 
	 * @说明 教师点击添加指定学生,判断该教师是否有权限(以点击者是否是该课题指导老师为标准判断)
	 * @param teacherID
	 *            教师user表ID
	 * @param topicID
	 *            课题表ID
	 * 
	 * 
	 */
	public boolean teacherIsPermissionAddStudentInTopic(String teacherId, String topicId);

	/**
	 * 
	 * @DOTO
	 * 
	 * @说明 某教师提前开放某题给某些学生： 2、将这个学生列表设为改课题的指定学生（此后该列表中学生就可以提前选该题）
	 * 
	 * @param topicID
	 *            课题ID
	 * @param studentIDList
	 *            学生ID列表
	 * 
	 * @return 1成功 0失败
	 */
	public boolean topicSelectStudent(String topicID, List<String> studentIDList);

	/**
	 * @说明 遍历出所有可以选择的课题用于分配最后剩余的学生
	 * @return
	 */
	public List<bysjglxt_topic> listSelectBysjglxtTopic();
	
	
	
	
	
	
	
}
