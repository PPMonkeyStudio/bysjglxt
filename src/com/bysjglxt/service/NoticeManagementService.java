package com.bysjglxt.service;

import java.util.List;

import com.bysjglxt.domain.DTO.NoticeDTO;
import com.bysjglxt.domain.DTO.StudentInformationDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.NoticeVO;

public interface NoticeManagementService {

	// 导航栏获得所有未读消息
	public List<NoticeDTO> listNotice(String userId);

	// 获取分页显示的所有通知
	public NoticeVO noticeVO(NoticeVO noticeVO, String userId);

	/**
	 * 将通知更改为已读
	 * 
	 * 
	 * @param noticeId
	 * @return -1 更改失败 0 无该通知 1成功
	 */
	public int updateNoticeState(String noticeId);

	//获取所有某状态的数量
	public int getNoticeStateCount(String userId);

	/**
	 * 
	 * @param sendString
	 * @param message_content
	 * @return
	 */
	public String sendMessage(String sendString, String message_content,TeacherInformationDTO teacherInfoDTO,StudentInformationDTO studentInfoDTO,String juese);
	
	
}
