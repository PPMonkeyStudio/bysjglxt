package bysjglxt;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DTO.DesignationStudentInformationDTO;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.DTO.TopicInformationManagementDTO;
import com.bysjglxt.domain.VO.TopicInformationManagementVO;
import com.bysjglxt.service.TopicInformationManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class test {

	@Resource
	private TopicInformationManagementService topicManagementService;

	public void setTopicManagementService(TopicInformationManagementService topicManagementService) {
		this.topicManagementService = topicManagementService;
	}

	// 测试创建课题
	@Test
	public void test() {
		TopicInformationManagementDTO topicInformationDTO = new TopicInformationManagementDTO();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		TeacherInformationDTO teacherInformationDTO = new TeacherInformationDTO();
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_teacher_user.setUser_teacher_id("ee5ee741-7677-4541-8a85-dc2f670f6316");
		teacherInformationDTO.setBysjglxtTeacherUser(bysjglxt_teacher_user);
		topicInformationDTO.setTeacherInformationDTO(teacherInformationDTO);
		topicInformationDTO.setBysjglxtTopic(bysjglxt_topic);
	}

	// 测试删除课题
	@Test
	public void testt() {
		List<String> topic_id = new ArrayList<String>();
		topic_id.add("ffcc3ffb-9578-4ee0-bd5f-30ac73c224f3");
		boolean flag = topicManagementService.DeleteTopic(topic_id);
		System.out.println(flag);
	}


	// 测试关闭
	@Test
	public void testttt() {
		List<String> topic_id = new ArrayList<String>();
		topic_id.add("ffcc3ffb-9578-4ee0-bd5f-30ac73c224f3");
		boolean flag = topicManagementService.closeTopic(topic_id);
		System.out.println(flag);
	}



	// 测试选题
	@Test
	public void testSelectTopic() {
		int i = 0;
		i = topicManagementService.selectTopic("0e81a1be-a41e-44c0-9781-a60b87bb2e89",
				"0e18661e-9fb2-43b8-bce3-bd5a80768dc3");
		System.out.println(i);
	}

}
