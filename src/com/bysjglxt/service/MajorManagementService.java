package com.bysjglxt.service;

import com.bysjglxt.domain.DO.bysjglxt_major;
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

}
