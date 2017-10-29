package com.bysjglxt.domain.VO;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_topic;

public class TopicManagementVO {

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
	private List<bysjglxt_topic> list_topic;

	// 搜索
	private String search;

	// 课题来源筛选
	private String source;

	// 课题性质筛选
	private String type;

	@Override
	public String toString() {
		return "TopicManagementVO [pageIndex=" + pageIndex + ", totalRecords=" + totalRecords + ", pageSize=" + pageSize
				+ ", totalPages=" + totalPages + ", HavePrePage=" + HavePrePage + ", HaveNextPage=" + HaveNextPage
				+ ", list_topic=" + list_topic + ", search=" + search + ", source=" + source + ", type=" + type + "]";
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

	public List<bysjglxt_topic> getList_topic() {
		return list_topic;
	}

	public void setList_topic(List<bysjglxt_topic> list_topic) {
		this.list_topic = list_topic;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}