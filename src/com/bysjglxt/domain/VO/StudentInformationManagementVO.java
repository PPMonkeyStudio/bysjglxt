package com.bysjglxt.domain.VO;

import java.util.List;

import com.bysjglxt.domain.DTO.StudentInformationDTO;

public class StudentInformationManagementVO {

	// 当前页
	private int pageIndex = 1;

	// 总记录数
	private int totalRecords = 0;

	// 每页显示记录数
	private int pageSize = 10;

	// 总页数
	private int totalPages = 1;

	// 是否有上一页
	private boolean HavePrePage = false;

	// 是否有下一页
	private boolean HaveNextPage = false;

	// 学生列表
	private List<StudentInformationDTO> list_StudentInformationDTO;

	// 搜索
	private String search;

	// 性别筛选
	private String sex;
	// 专业筛选
	private String student_basic_major;
	// 年级筛选
	private String student_basic_grade;
	// 操作权限筛选
	private int user_student_is_operate_premission = -1;

	/*
	 * 
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	@Override
	public String toString() {
		return "StudentInformationManagementVO [pageIndex=" + pageIndex + ", totalRecords=" + totalRecords
				+ ", pageSize=" + pageSize + ", totalPages=" + totalPages + ", HavePrePage=" + HavePrePage
				+ ", HaveNextPage=" + HaveNextPage + ", list_StudentInformationDTO=" + list_StudentInformationDTO
				+ ", search=" + search + ", sex=" + sex + ", student_basic_major=" + student_basic_major
				+ ", student_basic_grade=" + student_basic_grade + ", user_student_is_operate_premission="
				+ user_student_is_operate_premission + "]";
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStudent_basic_major() {
		return student_basic_major;
	}

	public void setStudent_basic_major(String student_basic_major) {
		this.student_basic_major = student_basic_major;
	}

	public String getStudent_basic_grade() {
		return student_basic_grade;
	}

	public void setStudent_basic_grade(String student_basic_grade) {
		this.student_basic_grade = student_basic_grade;
	}

	public int getUser_student_is_operate_premission() {
		return user_student_is_operate_premission;
	}

	public void setUser_student_is_operate_premission(int user_student_is_operate_premission) {
		this.user_student_is_operate_premission = user_student_is_operate_premission;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isHavePrePage() {
		return HavePrePage;
	}

	public void setHavePrePage(boolean havePrePage) {
		HavePrePage = havePrePage;
	}

	public boolean isHaveNextPage() {
		return HaveNextPage;
	}

	public void setHaveNextPage(boolean haveNextPage) {
		HaveNextPage = haveNextPage;
	}

	public List<StudentInformationDTO> getList_StudentInformationDTO() {
		return list_StudentInformationDTO;
	}

	public void setList_StudentInformationDTO(List<StudentInformationDTO> list_StudentInformationDTO) {
		this.list_StudentInformationDTO = list_StudentInformationDTO;
	}

}
