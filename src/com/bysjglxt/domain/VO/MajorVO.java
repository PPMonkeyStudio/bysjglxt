package com.bysjglxt.domain.VO;

import java.util.List;

import com.bysjglxt.domain.DTO.MajorDTO;

/**
 * MajorManagementVO class
 * 
 * @author JXX
 * @date 2018/3/13
 *
 */
public class MajorVO {

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

	// 所有专业
	private List<MajorDTO> listMajorDTO;

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

	public List<MajorDTO> getListMajorDTO() {
		return listMajorDTO;
	}

	public void setListMajorDTO(List<MajorDTO> listMajorDTO) {
		this.listMajorDTO = listMajorDTO;
	}

	@Override
	public String toString() {
		return "MajorManagementVO [pageIndex=" + pageIndex + ", totalRecords=" + totalRecords + ", pageSize=" + pageSize
				+ ", totalPages=" + totalPages + ", HavePrePage=" + HavePrePage + ", HaveNextPage=" + HaveNextPage
				+ ", listMajorDTO=" + listMajorDTO + "]";
	}

}
