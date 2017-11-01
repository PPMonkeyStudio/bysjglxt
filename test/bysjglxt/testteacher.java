package bysjglxt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.VO.TeacherInformationManagementVO;
import com.bysjglxt.service.TeacherInformationManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class testteacher {

	@Resource
	private TeacherInformationManagementService teacherInformationManagementService;

	public void setTeacherInformationManagementService(
			TeacherInformationManagementService teacherInformationManagementService) {
		this.teacherInformationManagementService = teacherInformationManagementService;
	}

	// 测试创建
	@Test
	public void fff() {
		File file = new File("F:\\kk.xls");
		String name = "kk.xls";
		List<bysjglxt_teacher_basic> jiji = new ArrayList<bysjglxt_teacher_basic>();

		try {
			jiji = teacherInformationManagementService.convertTeacherExcelToList(file, name);
			System.out.println(jiji);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试存储
	@Test
	public void fffff() {
		File file = new File("F:\\kk.xls");
		String name = "kk.xls";
		List<bysjglxt_teacher_basic> jiji = new ArrayList<bysjglxt_teacher_basic>();

		try {
			jiji = teacherInformationManagementService.convertTeacherExcelToList(file, name);
			System.out.println(jiji);
			boolean flag = teacherInformationManagementService.saveTeacherList(jiji);
			System.out.println(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试删除教师信息
	@Test
	public void testttttt() {
		List<String> re = new ArrayList<String>();
		re.add("006d1f31-2472-4dd6-955f-bc0534c204ac");
		boolean flag = teacherInformationManagementService.remove_TeacherList(re);
		System.out.println(flag);
	}

	// 测试分页得到数据
	@Test
	public void efdsfdsf() {
		TeacherInformationManagementVO VO_TEACHER_By_PageAndSearch = new TeacherInformationManagementVO();
		VO_TEACHER_By_PageAndSearch.setSection("94943b03-e8c9-4743-a742-3c8bf95efad3");
		VO_TEACHER_By_PageAndSearch = teacherInformationManagementService
				.VO_TEACHER_By_PageAndSearch(VO_TEACHER_By_PageAndSearch);

		System.out.println(VO_TEACHER_By_PageAndSearch);

	}

}
