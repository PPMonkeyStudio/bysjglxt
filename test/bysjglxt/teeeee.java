package bysjglxt;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.VO.SectionInformationManagementVO;
import com.bysjglxt.service.SectionInformationManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class teeeee {

	@Resource
	private SectionInformationManagementService sectionInformationManagementService;

	public void setSectionInformationManagementService(
			SectionInformationManagementService sectionInformationManagementService) {
		this.sectionInformationManagementService = sectionInformationManagementService;
	}

	// 测试删除教研室
	@Test
	public void tetetet() {
		List<String> listSectionId = new ArrayList<String>();
		listSectionId.add("94943b03-e8c9-4743-a742-3c8bf95efad3");
		boolean flag = sectionInformationManagementService.deleteSection(listSectionId);
		System.out.println(flag);
	}

	// 测试更新教研室
	@Test
	public void teettete() {
		bysjglxt_section bysjglxt_section = new bysjglxt_section();
		bysjglxt_section.setSection_id("ebd25486-e610-4293-a053-73c18c3eeb4b");
		bysjglxt_section.setSection_name("数媒教研室");
		bysjglxt_section.setSection_leader("166c2390-a79a-4ab1-b2ff-73d283e3aa65");
		boolean flag = sectionInformationManagementService.updateSection(bysjglxt_section);
		System.out.println(flag);
	}

}
