package bysjglxt;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bysjglxt.domain.DO.bysjglxt_process_definition;
import com.bysjglxt.domain.DO.bysjglxt_task_definition;
import com.bysjglxt.service.ProcessManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class testProcess {
	@Resource
	private ProcessManagementService processManagementService;

	public void setProcessManagementService(ProcessManagementService processManagementService) {
		this.processManagementService = processManagementService;
	}

	// 测试创建流程定义
	@Test
	public void testCreateProcessDefinition() {
		bysjglxt_process_definition bysjglxt_process_definition = new bysjglxt_process_definition();
		bysjglxt_process_definition.setProcess_definition_instance_role(2);
		bysjglxt_process_definition.setProcess_definition_name("TheOne");
		String msg = "";
		msg = processManagementService.createSelectTopicProcessDefine(bysjglxt_process_definition);
		System.out.println(msg);
	}

	// 测试创建任务定义
	@Test
	public void testCreateTaskDefinition() {
		bysjglxt_task_definition bysjglxt_task_definition = new bysjglxt_task_definition();
		bysjglxt_task_definition.setTask_definition_name("指导老师完成任务书");
		bysjglxt_task_definition.setTask_definition_process_definition("cb339fb5-b0d6-49aa-b389-ffa8dc3d690a");
		bysjglxt_task_definition.setTask_definition_type(1);
		bysjglxt_task_definition.setTask_definition_role(1);
		int i = 0;
		i = processManagementService.createSelectTopicTaskDefine(bysjglxt_task_definition);
		System.out.println(i);
	}

	// 测试遍历出流程定义
	@Test
	public void testListProcessDefinition() {
		List<bysjglxt_process_definition> listProcessDefinition = new ArrayList<bysjglxt_process_definition>();
		listProcessDefinition = processManagementService.listProcessDefinition();
		for (bysjglxt_process_definition bysjglxt_process_definition : listProcessDefinition) {
			System.out.println(bysjglxt_process_definition);
		}
	}

	// 测试开启流程
	@Test
	public void testOpenProcess() {
		int i = 0;
		i = processManagementService.openProcess("TheOne", "cb339fb5-b0d6-49aa-b389-ffa8dc3d690a",
				"00171b55-b96b-4f2b-97f0-58f11a22ae74");
		System.out.println(i);
	}

}
