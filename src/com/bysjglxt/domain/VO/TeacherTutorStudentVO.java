package com.bysjglxt.domain.VO;

import java.util.List;

import com.bysjglxt.domain.DTO.TeacherTutorStudentDTO;

public class TeacherTutorStudentVO {
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

	// 列表
	private List<TeacherTutorStudentDTO> list_TeacherTutorStudentDTO;

	// 搜索
	private String search;

	// 状态
	private int state = -1;

	@Override
	public String toString() {
		return "TeacherTutorStudentVO [pageIndex=" + pageIndex + ", totalRecords=" + totalRecords + ", pageSize="
				+ pageSize + ", totalPages=" + totalPages + ", HavePrePage=" + HavePrePage + ", HaveNextPage="
				+ HaveNextPage + ", list_TeacherTutorStudentDTO=" + list_TeacherTutorStudentDTO + ", search=" + search
				+ ", state=" + state + "]";
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getPageIndex() {
		return pageIndex;
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

	public List<TeacherTutorStudentDTO> getList_TeacherTutorStudentDTO() {
		return list_TeacherTutorStudentDTO;
	}

	public void setList_TeacherTutorStudentDTO(List<TeacherTutorStudentDTO> list_TeacherTutorStudentDTO) {
		this.list_TeacherTutorStudentDTO = list_TeacherTutorStudentDTO;
	}

}
