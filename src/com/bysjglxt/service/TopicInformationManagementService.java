package com.bysjglxt.service;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TopicInformationManagementDTO;
import com.bysjglxt.domain.VO.TopicInformationManagementVO;

public interface TopicInformationManagementService {

	/**
	 * 分配评阅教师
	 * 
	 */
	public int assignment(String selectId,String reviewId);

	/**
	 * @DATE 2017-10-28
	 * @说明 教师创建课题，存储到bysjglxt_topic表中 这里需要通知管理员
	 * @param newTopic
	 * @return 1成功 0失败
	 * 
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
	 * 
	 *            1教师 2学生
	 * 
	 * 
	 * @return 封装好的topicManagementVO
	 */
	public TopicInformationManagementVO VO_Topic_By_PageAndSearch(TopicInformationManagementVO topicManagementVO,
			int studentOrTeacher);

	/**
	 * @DOTO
	 * 
	 * @DATE 2017-11-01
	 * @说明 1、判断课题是否可选，若可以则下一步操作，不可以则返回false。 如何判断课题是否可选: (1)：大众选题 判断是否达到教师可选上限
	 *     教师指导已达上限 -1 判断是否到达课题学生上限 课题已选满 -2 判断学生是否已经选题 已选题 -3 参数错误 -4
	 *     判断学生是否有选题权限 -5 成功 1 2、创建一条bysjglxt_topic_select学生选题表的记录，评阅老师为null。
	 *     3、学生课题bysjglxt_topic所属的记录中，课题已选学生数topic_student_num加1。 4. 教师的学生指导数+1
	 *     5.学生记录更改为已选题
	 * @param studentID
	 *            选题的学生 user表
	 * @param topicID
	 *            所选课题
	 * 
	 * @return
	 */
	public int selectTopic(String studentID, String topicID);

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
	 * 分配学生选题 1.创建选题记录 2.将学生选题状态更改为已选题 1 3.教师选题人数+1 4.课题选择人数+1
	 */
	public int assignmentStudentTopic(String userId, String topic);

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
	public boolean distributionTopicStudent(String topicID, List<String> studentIDList);

	/**
	 * @说明 遍历出所有可以选择的课题用于分配最后剩余的学生
	 * @return
	 */
	public List<bysjglxt_topic> listSelectBysjglxtTopic();

	/**
	 * 学生点击我的课题拿出属于自己已经选择的课题
	 * 
	 * @return
	 */
	public TopicInformationManagementDTO studentTopicInformationManagementDTO(String studentUserId);

	/**
	 * 学生点击我的课题拿出属于自己已经选择的课题
	 * 
	 * @return
	 */
	public TopicInformationManagementVO VO_TopicBelongStudent_By_PageAndSearch(
			TopicInformationManagementVO topicManagementVO, String studentUserId);

	/**
	 * 
	 * 教师点击我的课题拿出历年所有的课题
	 * 
	 */
	public TopicInformationManagementVO VO_TopicBelongTeacher_By_PageAndSearch(
			TopicInformationManagementVO topicManagementVO, String teacherUserId);

	/**
	 * 
	 * 获取学生列表
	 * 
	 * @param topicId
	 * @return
	 */
	public List<StudentInformationDTO> listStudentSelectTopic(String topicId);

	/**
	 * 指定 弃用
	 * 
	 * @param studentUserId
	 * @return
	 */
	public int specialStudentSelectTopic(String studentUserId, String topicId);

	/**
	 * 
	 * 更改课题信息
	 * 
	 * @param topic
	 * @return
	 */
	public int updateTopic(bysjglxt_topic topic);

	/**
	 * @D 学生点击退选:
	 * @D 1.根据学生userId获取学生选题表
	 * @D 2.根据学生userId更改学生登陆表是否选题字段
	 * @D 3.将教师的选题人数-1
	 * @D 4.将课题的选题人数-1
	 * @D 4.删除学生选题表记录
	 * @param studentUserId
	 *            学生userID
	 * @return -3 参数错误,系统繁忙
	 * @return -1退选失败
	 * @return 1 成功
	 */
	public int dropTopic(String studentUserId);

}
