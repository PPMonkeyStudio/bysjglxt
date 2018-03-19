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


}
