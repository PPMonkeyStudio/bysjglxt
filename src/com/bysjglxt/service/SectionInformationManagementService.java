package com.bysjglxt.service;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.VO.SectionInformationManagementVO;

public interface SectionInformationManagementService {

	/**
	 * @DATE 2017-10-31
	 * @说明 根据页数查询教研室 ，不需要筛选和搜索
	 * @param sectionInformationManagementVO
	 * @return SectionInformationManagementVO
	 */
	public SectionInformationManagementVO VO_Section_By_Page(
			SectionInformationManagementVO sectionInformationManagementVO);

	/**
	 * @DATE 2017-10-31 创建教研室
	 * 
	 */

	public boolean Create_Section(bysjglxt_section newSection);

	/**
	 * @DATE 2017-10-31 批量删除教研室
	 */

	public boolean deleteSection(List<String> listSectionId);

	/**
	 * 
	 * @DATE 2017-10-31 遍历出所有的教研室
	 */
	public List<bysjglxt_section> listBysjglxtSection();
	
	/**
	 * @DATE 2017-10-31
	 * 更改教研室
	 * 
	 * 
	 */
	public boolean updateSection(bysjglxt_section bysjglxt_section);
	
	
}
