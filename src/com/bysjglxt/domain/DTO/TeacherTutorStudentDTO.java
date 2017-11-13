package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_topic;

/**
 * 用于
 * 
 * @author Administrator
 *
 */
public class TeacherTutorStudentDTO {
	private TaskDTO taskDTO; // 正在进行的任务实例
	private ProcessBelongDTO processBelongDTO; // 流程实例
	private bysjglxt_topic bysjglxtTopic;
	private StudentInformationDTO studentInformationDTO;

	@Override
	public String toString() {
		return "TeacherTutorStudentDTO [taskDTO=" + taskDTO + ", processBelongDTO=" + processBelongDTO
				+ ", bysjglxtTopic=" + bysjglxtTopic + ", studentInformationDTO=" + studentInformationDTO + "]";
	}

	public ProcessBelongDTO getProcessBelongDTO() {
		return processBelongDTO;
	}

	public void setProcessBelongDTO(ProcessBelongDTO processBelongDTO) {
		this.processBelongDTO = processBelongDTO;
	}

	public TaskDTO getTaskDTO() {
		return taskDTO;
	}

	public void setTaskDTO(TaskDTO taskDTO) {
		this.taskDTO = taskDTO;
	}

	public bysjglxt_topic getBysjglxtTopic() {
		return bysjglxtTopic;
	}

	public void setBysjglxtTopic(bysjglxt_topic bysjglxtTopic) {
		this.bysjglxtTopic = bysjglxtTopic;
	}

	public StudentInformationDTO getStudentInformationDTO() {
		return studentInformationDTO;
	}

	public void setStudentInformationDTO(StudentInformationDTO studentInformationDTO) {
		this.studentInformationDTO = studentInformationDTO;
	}

}
