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

	// 测试创建教研室
	@Test
	public void eddd() {
		bysjglxt_section newSection = new bysjglxt_section();
		newSection.setSection_leader("09f6e95d-a743-481d-bb4c-d60488d38ead");
		newSection.setSection_name("kk教研室");
		boolean flag = sectionInformationManagementService.Create_Section(newSection);
		System.out.println(flag);
	}

	// 测试删除教研室
	@Test
	public void tetetet() {
		List<String> listSectionId = new ArrayList<String>();
		listSectionId.add("70a815da-1f9f-4ab7-8b8e-63a99dd72b0a");
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
	// 测试分页显示教研室主任信息
	@Test
	public void fffff() {
		SectionInformationManagementVO VO_Section_By_Page = new SectionInformationManagementVO();
		VO_Section_By_Page = sectionInformationManagementService.VO_Section_By_Page(VO_Section_By_Page);
		System.out.println(VO_Section_By_Page);

	}

}
