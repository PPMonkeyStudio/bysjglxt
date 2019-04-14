package com.bysjglxt.domain.VO;

import java.util.List;

import com.bysjglxt.domain.DTO.ProcessDetailDTO;

public class ProcessManagementVO {

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
	private List<ProcessDetailDTO> list_ProcessDetailDTO;

	// 搜索
	private String search;

	// 筛选状态
	private int state=-1;

	// 筛选搜索流程实例ID
	private String processInstance;
	// 筛选流程定义ID
	private String processDefinition;

	@Override
	public String toString() {
		return "ProcessManagementVO [pageIndex=" + pageIndex + ", totalRecords=" + totalRecords + ", pageSize="
				+ pageSize + ", totalPages=" + totalPages + ", HavePrePage=" + HavePrePage + ", HaveNextPage="
				+ HaveNextPage + ", list_ProcessDetailDTO=" + list_ProcessDetailDTO + ", search=" + search + ", state="
				+ state + ", processInstance=" + processInstance + ", processDefinition=" + processDefinition + "]";
	}

	public String getProcessDefinition() {
		return processDefinition;
	}

	public void setProcessDefinition(String processDefinition) {
		this.processDefinition = processDefinition;
	}

	public String getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(String processInstance) {
		this.processInstance = processInstance;
	}

	public List<ProcessDetailDTO> getList_ProcessDetailDTO() {
		return list_ProcessDetailDTO;
	}

	public void setList_ProcessDetailDTO(List<ProcessDetailDTO> list_ProcessDetailDTO) {
		this.list_ProcessDetailDTO = list_ProcessDetailDTO;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
