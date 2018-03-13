package com.bysjglxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bysjglxt.dao.MajorManagementDao;
import com.bysjglxt.domain.DO.bysjglxt_major;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DTO.MajorDTO;
import com.bysjglxt.domain.VO.MajorVO;
import com.bysjglxt.service.MajorManagementService;

import util.TeamUtil;

public class MajorManagementServiceImpl implements MajorManagementService {
	private MajorManagementDao majorManagementDao;

	public void setMajorManagementDao(MajorManagementDao majorManagementDao) {
		this.majorManagementDao = majorManagementDao;
	}

	/**
	 * 遍历出属于这个学院的所有专业
	 */
	@Override
	public MajorVO majorManagementVO(MajorVO majorVo, String userId) {
		List<MajorDTO> listMajorDTO = new ArrayList<>();
		MajorDTO majorDTO = null;
		bysjglxt_section section = null;
		List<bysjglxt_major> listMajor = new ArrayList<>();
		// 获取属于这个学院的所有专业
		listMajor = majorManagementDao.getAllMajorCount(majorVo, userId);
		int i = listMajor.size();
		// 获取总记录数
		majorVo.setTotalRecords(i);
		majorVo.setTotalPages(((i - 1) / majorVo.getPageSize()) + 1);
		if (majorVo.getPageIndex() <= 1) {
			majorVo.setHavePrePage(false);
		} else {
			majorVo.setHavePrePage(true);
		}
		if (majorVo.getPageIndex() >= majorVo.getTotalPages()) {
			majorVo.setHaveNextPage(false);
		} else {
			majorVo.setHaveNextPage(true);
		}
		// 获取符合条件的若干条信息
		listMajor = majorManagementDao.getAllMajorByPage(majorVo, userId);
		for (bysjglxt_major bysjglxt_major : listMajor) {
			majorDTO = new MajorDTO();
			section = new bysjglxt_section();
			majorDTO.setBysjglxtMajor(bysjglxt_major);
			// 根据教研室ID获取教研室信息
			if (bysjglxt_major.getMajor_belong_section() != null
					&& bysjglxt_major.getMajor_belong_section().trim().length() > 0) {
				section = majorManagementDao.getSectionById(bysjglxt_major.getMajor_belong_section().trim());
				if (section != null) {
					majorDTO.setBysjglxtSection(section);
				}
			}
			listMajorDTO.add(majorDTO);
		}
		majorVo.setListMajorDTO(listMajorDTO);
		return majorVo;
	}

	// 手动添加专业
	@Override
	public int addMajor(bysjglxt_major major) {
		bysjglxt_major oldMajor = new bysjglxt_major();
		if (major.getMajor_name() == null || major.getMajor_name().trim().length() <= 0) {
			// 传到后台的数据有误
			System.out.println("1");
			return -1;
		}
		if (major.getMajor_professionalcode() == null || major.getMajor_professionalcode().trim().length() <= 0) {
			System.out.println("2");
			return -1;
		}
		if (major.getMajor_belong_section() == null || major.getMajor_belong_section().trim().length() <= 0) {
			System.out.println("3");
			return -1;
		}
		// 其中专业代码以及专业名称不能重复
		// 根据专业名称进行查找
		oldMajor = majorManagementDao.getMajorByName(major.getMajor_name().trim());
		if (oldMajor != null) {
			System.out.println("4");
			return -1;
		}
		oldMajor = majorManagementDao.getMajorByProfrssional(major.getMajor_professionalcode().trim());
		if (oldMajor != null) {
			System.out.println("5");
			return -1;
		}
		major.setMajor_id(TeamUtil.getUuid());
		major.setMajor_gmt_create(TeamUtil.getStringSecond());
		major.setMajor_gmt_modified(major.getMajor_gmt_create());
		return majorManagementDao.addObject(major);
	}

}
