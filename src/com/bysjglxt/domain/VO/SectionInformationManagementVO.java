package com.bysjglxt.domain.VO;

import java.util.List;

import com.bysjglxt.domain.DTO.SectionInformationDTO;

public class SectionInformationManagementVO {

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

	// 教研室主任
	private List<SectionInformationDTO> SectionInformationDTO;

	/*
	 * 
	 */

	public int getPageIndex() {
		return pageIndex;
	}

	@Override
	public String toString() {
		return "SectionInformationManagementVO [pageIndex=" + pageIndex + ", totalRecords=" + totalRecords
				+ ", pageSize=" + pageSize + ", totalPages=" + totalPages + ", HavePrePage=" + HavePrePage
				+ ", HaveNextPage=" + HaveNextPage + ", SectionInformationDTO=" + SectionInformationDTO + "]";
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

	public List<SectionInformationDTO> getSectionInformationDTO() {
		return SectionInformationDTO;
	}

	public void setSectionInformationDTO(List<SectionInformationDTO> sectionInformationDTO) {
		SectionInformationDTO = sectionInformationDTO;
	}

}
