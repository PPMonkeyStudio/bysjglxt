package com.bysjglxt.domain.DTO;

public class ReviewTutorTeacherInfoDTO {
	private TeacherInformationDTO tutorTeacherInformationDTO;
	private TeacherInformationDTO reviewTeacherInformationDTO;
	private StudentInformationDTO studentInformationDTO;
	public TeacherInformationDTO getTutorTeacherInformationDTO() {
		return tutorTeacherInformationDTO;
	}
	public void setTutorTeacherInformationDTO(TeacherInformationDTO tutorTeacherInformationDTO) {
		this.tutorTeacherInformationDTO = tutorTeacherInformationDTO;
	}
	public TeacherInformationDTO getReviewTeacherInformationDTO() {
		return reviewTeacherInformationDTO;
	}
	public void setReviewTeacherInformationDTO(TeacherInformationDTO reviewTeacherInformationDTO) {
		this.reviewTeacherInformationDTO = reviewTeacherInformationDTO;
	}
	public StudentInformationDTO getStudentInformationDTO() {
		return studentInformationDTO;
	}
	public void setStudentInformationDTO(StudentInformationDTO studentInformationDTO) {
		this.studentInformationDTO = studentInformationDTO;
	}
	@Override
	public String toString() {
		return "ReviewTutorTeacherInfoDTO [tutorTeacherInformationDTO=" + tutorTeacherInformationDTO
				+ ", reviewTeacherInformationDTO=" + reviewTeacherInformationDTO + ", studentInformationDTO="
				+ studentInformationDTO + "]";
	}
	
}
