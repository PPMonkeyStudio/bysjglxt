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
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.DTO.TopicInformationManagementDTO;
import com.bysjglxt.domain.VO.TopicInformationManagementVO;
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

	// 遍历出所有可以进行选择的课题
	@Test
	public void testD() {
		List<bysjglxt_topic> listSelectBysjglxtTopic = new ArrayList<bysjglxt_topic>();
		listSelectBysjglxtTopic = topicManagementService.listSelectBysjglxtTopic();
		System.out.println(listSelectBysjglxtTopic.size());
		System.out.println(listSelectBysjglxtTopic);

	}

	// 测试分页
	@Test
	public void testE() {
		TopicInformationManagementVO topicManagementVO = new TopicInformationManagementVO();
		topicManagementVO = topicManagementService.VO_Topic_By_PageAndSearch(topicManagementVO);
		System.out.println(topicManagementVO);
	}

	// 测试创建课题
	@Test
	public void testG() {
		TopicInformationManagementDTO topicInformationDTO = new TopicInformationManagementDTO();
		TeacherInformationDTO teacherInformationDTO = new TeacherInformationDTO();
		bysjglxt_topic bysjglxt_topic = new bysjglxt_topic();
		bysjglxt_topic.setTopic_name_chinese("111");
		bysjglxt_topic.setTopic_name_english("uiuiuiu");
		topicInformationDTO.setBysjglxtTopic(bysjglxt_topic);
		bysjglxt_teacher_user bysjglxt_teacher_user = new bysjglxt_teacher_user();
		bysjglxt_teacher_user.setUser_teacher_id("02cf3c5a-a3c0-49e1-8e60-3219a782c9cd");
		teacherInformationDTO.setBysjglxtTeacherUser(bysjglxt_teacher_user);
		topicInformationDTO.setTeacherInformationDTO(teacherInformationDTO);
		boolean flag = topicManagementService.CreateTopic(topicInformationDTO);
		System.out.println(flag);

	}

}
