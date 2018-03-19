package bysjglxt;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bysjglxt.domain.DTO.CollegeInformationDTO;
import com.bysjglxt.service.CollegeManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class testCollegeManagement {
	@Resource
	private CollegeManagementService collegeManagementService;

	public void setCollegeManagementService(CollegeManagementService collegeManagementService) {
		this.collegeManagementService = collegeManagementService;
	}

	/**
	 * 
	 * 
	 * 
	 */
	// 遍历
	@Test
	public void f() {
		List<CollegeInformationDTO> listCollegeInformationDTO = collegeManagementService.listCollegeInformationDTO();
		for (CollegeInformationDTO collegeInformationDTO : listCollegeInformationDTO) {
			System.out.println(collegeInformationDTO);
		}
	}

	// 更改权限
	@Test
	public void g() {
		collegeManagementService.updateCollegeAdmin("bc1e8e7f-c8e8-4518-bb6f-fca8c9664d1f");
	}

}
