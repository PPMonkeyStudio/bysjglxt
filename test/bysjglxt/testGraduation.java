package bysjglxt;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bysjglxt.domain.VO.TeacherTutorStudentVO;
import com.bysjglxt.service.GraduationProjectManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class testGraduation {

	@Resource
	private GraduationProjectManagementService graduationProjectManagementService;

	public void setGraduationProjectManagementService(
			GraduationProjectManagementService graduationProjectManagementService) {
		this.graduationProjectManagementService = graduationProjectManagementService;
	}

	// 测试保存毕业论文
	@Test
	public void saveGrad() {
		File file = null;
		int i = 0;
		try {
			i = graduationProjectManagementService.saveDissertation(file, "", "860f9cbb-1557-4702-96eb-eaa4e310e88c",
					"");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(i);
	}

	// 测试分页显示指导的任务
	@Test
	public void testTutor() {
		TeacherTutorStudentVO teacherTutorStudentVO = new TeacherTutorStudentVO();
		teacherTutorStudentVO.setState(-1);
		teacherTutorStudentVO = graduationProjectManagementService.teacherManagementStudentVO(teacherTutorStudentVO,
				"6184cd0c-5d58-437c-84b6-65d48692da83");
		System.out.println(teacherTutorStudentVO);
	}

	// 测试导出封面
	@Test
	public void testCover() {
		try {
			graduationProjectManagementService.exportCover("860f9cbb-1557-4702-96eb-eaa4e310e88c");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试导出任务书
	@Test
	public void testTask() {
		try {
			graduationProjectManagementService.exportTask("860f9cbb-1557-4702-96eb-eaa4e310e88c");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试导出开题报告
	@Test
	public void testReport() {
		try {
			graduationProjectManagementService.exportOpeningReport("860f9cbb-1557-4702-96eb-eaa4e310e88c");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试导出情况记录
	@Test
	public void testRecord() {
		try {
			graduationProjectManagementService.exportPerfect("860f9cbb-1557-4702-96eb-eaa4e310e88c");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试个人学习工作总结
	@Test
	public void testSummary() {
		try {
			graduationProjectManagementService.exportSummary("860f9cbb-1557-4702-96eb-eaa4e310e88c");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试导出形式审查表
	@Test
	public void testExportFormal() {
		try {
			graduationProjectManagementService.exportFormal("860f9cbb-1557-4702-96eb-eaa4e310e88c");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试导出指导老师评价表
	@Test
	public void testTeacherOpin() {
		try {
			graduationProjectManagementService.exportTeacherOpin("860f9cbb-1557-4702-96eb-eaa4e310e88c");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试导出评阅老师评价表
	@Test
	public void testReview() {
		try {
			graduationProjectManagementService.exportReviewOpin("860f9cbb-1557-4702-96eb-eaa4e310e88c");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试答辩
	@Test
	public void testDefence() {
		try {
			graduationProjectManagementService.exportDefence("860f9cbb-1557-4702-96eb-eaa4e310e88c");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试到处毕业设计书
	@Test
	public void testAll() {
		try {
			graduationProjectManagementService.exportAll("860f9cbb-1557-4702-96eb-eaa4e310e88c");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
