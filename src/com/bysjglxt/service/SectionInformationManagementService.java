package com.bysjglxt.service;

import com.bysjglxt.domain.VO.SectionInformationManagementVO;

public interface SectionInformationManagementService {

	/**
	 * @DOTO
	 * 
	 * @说明 根据页数查询教研室 ，不需要筛选和搜索
	 * @param sectionInformationManagementVO
	 * @return SectionInformationManagementVO
	 */
	public SectionInformationManagementVO VO_Section_By_Page(
			SectionInformationManagementVO sectionInformationManagementVO);

}
