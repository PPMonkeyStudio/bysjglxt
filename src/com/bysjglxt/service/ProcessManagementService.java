package com.bysjglxt.service;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.domain.DTO.ProcessDTO;
import com.bysjglxt.domain.DTO.ProcessDefinitionDetailDTO;
import com.bysjglxt.domain.DTO.ProcessDetailDTO;
import com.bysjglxt.domain.VO.ProcessManagementVO;

public interface ProcessManagementService {

	/**
	 * 开启毕业设计流程
	 */
	public int openGraduProcess(String teacherUserId);

	/**
	 * 获得当前正在进行的选题流程实例
	 * 
	 * @return
	 */
	public ProcessDTO getCurrentTaskIng(String userId, int role);

	/**
	 * @说明 点击创建流程 1. 创建流程定义记录 1 成功 -1失败
	 * @param SelectTopicProcessDefinition
	 * @return
	 */
	public String createSelectTopicProcessDefine(bysjglxt_process_definition selectTopicProcessDefinition);

	/**
	 * @说明 点击创建任务定义
	 * @param selectTopicTaskDefine
	 * @return
	 */
	public int createSelectTopicTaskDefine(bysjglxt_task_definition selectTopicTaskDefine);

	/**
	 * 遍历出流程定义
	 * 
	 * @return
	 */
	public List<bysjglxt_process_definition> listProcessDefinition();

	/**
	 * @param1 processInstanceName 流程实例名称
	 * @param2 process_definition_id 流程定义ID
	 * @param3 operation 操作者ID
	 * @说明 创建流程和任务实例
	 * 
	 * @return 1 成功
	 * @return -1 无权限开启流程
	 * @return -2已经开启流程
	 * @return -3实例化流程失败
	 * @return -4 系统繁忙，所输入的参数有问题
	 * @return -5 毕业设计 学生未选题
	 */
	public int openProcess(String processInstanceName, String process_definition_id, String operation, int fla);

	/**
	 * 遍历出所有的定义表
	 * 
	 * @return
	 */
	public ProcessDefinitionDetailDTO processDefinitionDetailDTO(String processDefinitionId);

	/**
	 * 删除流程定义
	 * 
	 * @param processDefinitionId
	 * @return 1:成功 -4系统繁忙，输入的参数有误 -3删除失败
	 */
	public int deleteProcessDefinition(List<String> listProcessDefinitionId);

	/**
	 * 点击我的任务，将内容分页显示
	 * 
	 * @param processManagementVo
	 * @return
	 */
	public ProcessManagementVO getMyTaskByPage(ProcessManagementVO processManagementVo, String userID);

	/**
	 * 获得正在进行的流程
	 */
	public ProcessDTO getCurrentTaskDTO(String userId);

	/**************************** 在点击通过或者是打回以及 ***************************************************/

	/**
	 * 通过: 实现思路: 1.根据任务实例ID获取任务实例对象 2.直接根据对象更改当前任务实例状态
	 * 3.根据a该对象的任务实例ID当作父任务实例ID来寻找下一个任务实例 4.根据next任务实例对象ID获得实例对象 5.更改任务实例状态 打回：
	 * 实现思路: 1.根据任务实例ID获取任务实例实例对象 2.将当前实例对象更改未未开始 3.先获得返回任务实例的ID
	 * 4.再往上遍历得到父任务结点,当父任务结点为返回任务结点的时候将该父任务结点的状态进行变化
	 * 
	 * @return -5下一步角色暂未分配
	 */

	// 1.通过
	public int pass(String taskInstanceId);

	// 2.打回
	public int repulse(String taskInstanceId);

	/**
	 * 获得属于某个人正在进行的任务实例以及以及所属流程实例定义
	 * 
	 * @param userId
	 * @return 没有处于活动状态的流程实例 null
	 * @return
	 */
	// public ProcessDetailDTO getTasking(String userId);

}
