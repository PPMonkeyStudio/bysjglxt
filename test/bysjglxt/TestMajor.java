package bysjglxt;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bysjglxt.domain.DO.bysjglxt_major;
import com.bysjglxt.domain.DTO.SectionDTO;
import com.bysjglxt.domain.VO.MajorVO;
import com.bysjglxt.service.MajorManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class TestMajor {
	@Resource
	private MajorManagementService majorManagementService;

	public void setMajorManagementService(MajorManagementService majorManagementService) {
		this.majorManagementService = majorManagementService;
	}

	// 测试遍历所有专业
	@Test
	public void allMajor() {
		MajorVO majorVO = new MajorVO();
		majorVO = majorManagementService.majorManagementVO(majorVO, "bc1e8e7f-c8e8-4518-bb6f-fca8c9664d1f");
		System.out.println(majorVO);

	}

	// 测试添加专业
	@Test
	public void addMajor() {
		bysjglxt_major major = new bysjglxt_major();
		major.setMajor_name("石雕");
		major.setMajor_professionalcode("01010");
		major.setMajor_belong_section("78d2af27-3575-4a8d-b4a7-f1ade4aca1cc");
		System.out.println(majorManagementService.addMajor(major));
	}

	// 测试删除专业
	@Test
	public void deleteSection() {
		List<String> listSectionId = new ArrayList<>();
		listSectionId.add("78d2af27-3575-4a82-b4a7-f1ade4aca1cc");
		listSectionId.add("78d2af27-3575-4a8d-b4a7-f1ade4aca1cc");
		System.out.println(majorManagementService.deleteListSection(listSectionId));
	}

	// 测试获取教研室
	@Test
	public void ffff() {
		List<SectionDTO> listSectionDTO = new ArrayList<>();
		listSectionDTO = majorManagementService.listSectionDTO("bc1e8e7f-c8e8-4518-bb6f-fca8c9664d1f");
		for (SectionDTO sectionDTO : listSectionDTO) {
			System.out.println(sectionDTO);
		}
	}

	// 测试更改信息
	@Test
	public void genggai() {
		bysjglxt_major bysjglxt_major = new bysjglxt_major();
		bysjglxt_major.setMajor_name("软件技术");
		bysjglxt_major.setMajor_id("b8c62e55-8e71-4b8a-ad39-796c260b6db6");
		bysjglxt_major.setMajor_professionalcode("01010");
		System.out.println(majorManagementService.updateSection(bysjglxt_major));
	}

}
