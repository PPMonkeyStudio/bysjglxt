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
				+ ", search=" + search + "]";
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
