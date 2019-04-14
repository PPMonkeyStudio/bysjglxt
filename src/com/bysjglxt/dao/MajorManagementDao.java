package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_major;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.VO.MajorVO;

public interface MajorManagementDao {

	public List<bysjglxt_major> getAllMajorCount(MajorVO majorVo, String userId);

	public List<bysjglxt_major> getAllMajorByPage(MajorVO majorVo, String userId);

	public bysjglxt_section getSectionById(String sectionId);

	public bysjglxt_major getMajorByName(String trim);

	public bysjglxt_major getMajorByProfrssional(String trim);

	public int addObject(Object obj);

	public int deleteSectionById(String section_id);

	public List<bysjglxt_section> listBysjglxtSection(String userId);

	public bysjglxt_teacher_user getTeacherUserById(String section_leader);

	public bysjglxt_teacher_basic getTeacherBasicById(String trim);

	public com.bysjglxt.domain.DO.bysjglxt_major getMajorById(String trim);

}
