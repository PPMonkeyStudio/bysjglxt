package com.bysjglxt.dao;

import com.bysjglxt.domain.DO.bysjglxt_leader;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;

public interface LoginOrWriteOffDao {

	public bysjglxt_student_user getBysjglxtStudentUserByNum(String username);

	public bysjglxt_teacher_user getBysjglxtTeacherUserByNum(String username);

	public bysjglxt_teacher_basic getBysjglxtTeacherBasicById(String user_teacher_basic);

	public bysjglxt_section getBysjglxtTeacherSection(String user_teacher_section);

	public bysjglxt_student_basic getBysjglxtStudentBasicById(String user_student_basic);

	public bysjglxt_leader getLeaderById(String user_teacher_id);

}
