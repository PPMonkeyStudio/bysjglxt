package com.bysjglxt.domain.VO;

import java.util.List;

import com.bysjglxt.domain.DTO.StudentInformationDTO;

public class StudentInformationManagementVO {

	private int pageIndex = 1;
	private int totalRecords = 0;
	private int pageSize = 20;
	private int totalPages = 1;

	private boolean HavePrePage = false;
	private boolean HaveNextPage = false;

	private List<StudentInformationDTO> list_StudentInformationDTO;

	/*
	 * 
	 */
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

	public List<StudentInformationDTO> getList_StudentInformationDTO() {
		return list_StudentInformationDTO;
	}

	public void setList_StudentInformationDTO(List<StudentInformationDTO> list_StudentInformationDTO) {
		this.list_StudentInformationDTO = list_StudentInformationDTO;
	}

}
