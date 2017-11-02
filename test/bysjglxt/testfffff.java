package bysjglxt;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bysjglxt.service.TopicInformationManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class testfffff {

	@Resource
	private TopicInformationManagementService topicManagementService;

	public void setTopicManagementService(TopicInformationManagementService topicManagementService) {
		this.topicManagementService = topicManagementService;
	}

	// 测试课题是否可选
	@Test
	public void test() {
		boolean flag = topicManagementService.selectTopic("035978e1-be36-4202-83f2-6ca275bd04ba",
				"3ac6eefb-7ff8-4173-bf89-c6425d024636");
		System.out.println(flag);
	}

	// 测试点击者是否有权限进行操作
	@Test
	public void testB() {
		boolean flag = topicManagementService.teacherIsPermissionAddStudentInTopic(
				"09f6e95d-a743-481d-bb4c-d60488d38ead", "3ac6eefb-7ff8-4173-bf89-c6425d024636");
		System.out.println(flag);
	}

	// 测试添加指定学生
	@Test
	public void testC() {
		List<String> studentIDList = new ArrayList<String>();
		studentIDList.add("035978e1-be36-4202-83f2-6ca275bd04ba");
		studentIDList.add("03b0b3c1-04a9-4c43-ba29-785e660fa6ac");
		boolean flag = topicManagementService.topicSelectStudent("3ac6eefb-7ff8-4173-bf89-c6425d024636", studentIDList);
	}

}
