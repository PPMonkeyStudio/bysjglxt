package com.bysjglxt.domain.VO;

import java.util.List;

import com.bysjglxt.domain.DTO.TeacherInformationDTO;

public class TeacherInformationManagementVO {

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

	// 教师列表
	private List<TeacherInformationDTO> list_TeacherInformationDTO;

	// 搜索
	private String search;

	// 性别筛选
	private String sex;
	// 教研室筛选
	private String section;

	// 专业技术职称筛选
	private String professional_title;

	// 记录员筛选
	private int recorder;

	// 答辩小组长筛选
	private int defenceLeader;

	@Override
	public String toString() {
		return "TeacherInformationManagementVO [pageIndex=" + pageIndex + ", totalRecords=" + totalRecords
				+ ", pageSize=" + pageSize + ", totalPages=" + totalPages + ", HavePrePage=" + HavePrePage
				+ ", HaveNextPage=" + HaveNextPage + ", list_TeacherInformationDTO=" + list_TeacherInformationDTO
				+ ", search=" + search + ", sex=" + sex + ", section=" + section + ", professional_title="
				+ professional_title + ", recorder=" + recorder + ", defenceLeader=" + defenceLeader + "]";
	}

	public int getRecorder() {
		return recorder;
	}

	public void setRecorder(int recorder) {
		this.recorder = recorder;
	}

	public int getDefenceLeader() {
		return defenceLeader;
	}

	public void setDefenceLeader(int defenceLeader) {
		this.defenceLeader = defenceLeader;
	}

	public String getProfessional_title() {
		return professional_title;
	}

	public void setProfessional_title(String professional_title) {
		this.professional_title = professional_title;
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

	public List<TeacherInformationDTO> getList_TeacherInformationDTO() {
		return list_TeacherInformationDTO;
	}

	public void setList_TeacherInformationDTO(List<TeacherInformationDTO> list_TeacherInformationDTO) {
		this.list_TeacherInformationDTO = list_TeacherInformationDTO;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

}
