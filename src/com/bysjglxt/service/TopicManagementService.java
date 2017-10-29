package com.bysjglxt.service;

import java.util.List;

import com.bysjglxt.domain.DTO.TopicInformationDTO;
import com.bysjglxt.domain.VO.TopicManagementVO;

public interface TopicManagementService {

	/**
	 * @DATE 2017-10-28
	 * @说明 教师创建课题，存储到bysjglxt_topic表中
	 * @param newTopic
	 * @return 1成功 0失败
	 */
	public boolean CreateTopic(TopicInformationDTO topicInformationDTO);

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
	public TopicManagementVO VO_Topic_By_PageAndSearch(TopicManagementVO topicManagementVO);

	/**
	 * @DOTO
	 * 
	 * @说明 1、判断课题是否可选，若可以则下一步操作，不可以则返回false。
	 *     2、创建一条bysjglxt_topic_select学生选题表的记录，指导老师和评阅老师为null。
	 *     3、学生课题bysjglxt_topic所属的记录中，课题已选学生数topic_student_num加1。
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
	 * @DOTO
	 * 
	 * @说明 某教师提前开放某题给某些学生： 1、判断该教师是否是该课题的指导老师之一，若可以则下一步操作，不可以则返回false。
	 *     2、将这个学生列表设为改课题的指定学生，（此后该列表中学生就可以提前选该题）
	 * 
	 * @param TeacherID
	 *            教师ID
	 * @param topicID
	 *            课题ID
	 * @param studentIDList
	 *            学生ID列表
	 * 
	 * @return 1成功 0失败
	 */
	public boolean topicSelectStudent(String TeacherID, String topicID, List<String> studentIDList);

}
