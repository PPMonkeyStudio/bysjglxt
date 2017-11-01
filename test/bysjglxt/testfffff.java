package bysjglxt;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bysjglxt.service.TopicManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class testfffff {

	@Resource
	private TopicManagementService topicManagementService;

	public void setTopicManagementService(TopicManagementService topicManagementService) {
		this.topicManagementService = topicManagementService;
	}

	// 测试课题是否可选
	@Test
	public void test() {
		boolean flag = topicManagementService.selectTopic("035978e1-be36-4202-83f2-6ca275bd04ba", "3ac6eefb-7ff8-4173-bf89-c6425d024636");
		System.out.println(flag);
	}

}
