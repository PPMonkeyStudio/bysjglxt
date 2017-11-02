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
import com.bysjglxt.domain.DO.bysjglxt_topic_invite_teacher;
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
		bysjglxt_topic_invite_teacher bysjglxt_topic_invite_teacher = new bysjglxt_topic_invite_teacher();
		bysjglxt_topic_invite_teacher.setTopic_invite_teacher_name("2");
		TeacherInformationDTO teacherInformationDTO = new TeacherInformationDTO();
		bysjglxt_teacher_user bysjglxt_teacher_user= new bysjglxt_teacher_user();
		bysjglxt_teacher_user.setUser_teacher_id("02cf3c5a-a3c0-49e1-8e60-3219a782c9cd");
		teacherInformationDTO.setBysjglxtTeacherUser(bysjglxt_teacher_user);
		topicInformationDTO.setTeacherInformationDTO(teacherInformationDTO);
		topicInformationDTO.setBysjglxtTopic(bysjglxt_topic);
		topicInformationDTO.setBysjglxtTopicInviteTeacher(bysjglxt_topic_invite_teacher);
		boolean flag = topicManagementService.CreateTopic(topicInformationDTO);
		System.out.println(flag);
	}

	// 测试删除课题
	@Test
	public void testt() {
		List<String> topic_id = new ArrayList<String>();
		topic_id.add("1a107c81-da1c-4a0f-b15c-7ccdbdd2c208");
		boolean flag = topicManagementService.DeleteTopic(topic_id);
		System.out.println(flag);

	}

	// 测试采纳课题
	@Test
	public void testtt() {
		List<String> topic_id = new ArrayList<String>();
		topic_id.add("700f37d6-7b90-4b6a-a4c9-d2d7d6c5f7da");
		boolean flag = topicManagementService.adoptTopic(topic_id);
		System.out.println(flag);
	}

	// 测试关闭
	@Test
	public void testttt() {
		List<String> topic_id = new ArrayList<String>();
		topic_id.add("700f37d6-7b90-4b6a-a4c9-d2d7d6c5f7da");
		boolean flag = topicManagementService.closeTopic(topic_id);
		System.out.println(flag);
	}

	// 测试未通过
	@Test
	public void testtttt() {
		List<String> topic_id = new ArrayList<String>();
		topic_id.add("700f37d6-7b90-4b6a-a4c9-d2d7d6c5f7da");
		boolean flag = topicManagementService.notAdoptTopic(topic_id);
		System.out.println(flag);
	}

	// 测试分页显示
	@Test
	public void testttttt() {
		TopicInformationManagementVO VO_Topic_By_PageAndSearch = new TopicInformationManagementVO();
		VO_Topic_By_PageAndSearch.setSearch("theone");
		VO_Topic_By_PageAndSearch = topicManagementService.VO_Topic_By_PageAndSearch(VO_Topic_By_PageAndSearch);
		System.out.println(VO_Topic_By_PageAndSearch.toString());
	}

}
