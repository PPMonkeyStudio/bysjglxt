package com.bysjglxt.service;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_major;
import com.bysjglxt.domain.DTO.SectionDTO;
import com.bysjglxt.domain.VO.MajorVO;

/**
 * MajorManagementService interface
 * 
 * @author JXX
 * @date 2018/3/13
 *
 */
public interface MajorManagementService {

	/**
	 * 遍历出属于这个学院的所有专业
	 */
	public MajorVO majorManagementVO(MajorVO majorVo, String userId);

	/**
	 * 手动添加专业
	 */
	public int addMajor(bysjglxt_major major);

	/**
	 * 选中批量删除教研室
	 */
	public int deleteListSection(List<String> listSectionId);

	/**
	 * 获取教研室以及主任的信息 进行更改
	 */
	public List<SectionDTO> listSectionDTO(String userId);

	/**
	 * 修改专业信息
	 */
	public int updateSection(bysjglxt_major major);

	/**
	 * 根据专业名称获取专业信息
	 * @param bysjglxtMajor
	 */
	public bysjglxt_major getMajorByMajorName(bysjglxt_major bysjglxtMajor);

}
