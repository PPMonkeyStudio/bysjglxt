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
import com.bysjglxt.domain.DTO.StudentInformationDTO;
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
		System.out.println(topicManagementService.selectTopic("00171b55-b96b-4f2b-97f0-58f11a22ae74",
				"16aae43a-fcc2-4b0c-af5b-1535dc9b7aed"));
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
		System.out.println(topicManagementService.distributionTopicStudent("4176296d-d8fc-435c-b2c5-ce30a4105c0d",
				"42e95437-25f2-44c9-a64e-6038e13cd4b8"));
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
		topicManagementVO = topicManagementService.VO_Topic_By_PageAndSearch(topicManagementVO, 2);
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

	// 测试学生点击我的课题
	@Test
	public void testttttt() {
		TopicInformationManagementDTO TopicInformationManagementDTO = new TopicInformationManagementDTO();
		TopicInformationManagementDTO = topicManagementService
				.studentTopicInformationManagementDTO("71816f63-f35b-4ef7-98e7-39d8f78ff3e6");
		System.out.println(TopicInformationManagementDTO);
	}

	// 测试教师点击我的课题
	@Test
	public void testteew() {
		TopicInformationManagementVO VO_TopicBelongTeacher_By_PageAndSearch = new TopicInformationManagementVO();
		VO_TopicBelongTeacher_By_PageAndSearch = topicManagementService.VO_TopicBelongTeacher_By_PageAndSearch(
				VO_TopicBelongTeacher_By_PageAndSearch, "0e3279e9-c25c-47c4-9e73-2ac261cf04b8");
		System.out.println(VO_TopicBelongTeacher_By_PageAndSearch);

	}

	// 测试已选学生列表
	@Test
	public void testghss() {
		List<StudentInformationDTO> listStudentSelectTopic = new ArrayList<StudentInformationDTO>();
		listStudentSelectTopic = topicManagementService.listStudentSelectTopic("16aae43a-fcc2-4b0c-af5b-1535dc9b7aed");
		System.out.println(listStudentSelectTopic);

	}

}
