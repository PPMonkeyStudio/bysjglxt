package bysjglxt;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

	// 测试导出封面
	@Test
	public void testCover() {
		try {
			graduationProjectManagementService.exportCover("860f9cbb-1557-4702-96eb-eaa4e310e88c");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//测试导出任务书
	@Test
	public void testTask(){
		try {
			graduationProjectManagementService.exportTask("860f9cbb-1557-4702-96eb-eaa4e310e88c");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
