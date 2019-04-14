package bysjglxt;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bysjglxt.service.LoginOrWriteOffService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class testlogin {

	@Resource
	private LoginOrWriteOffService loginOrWriteOffService;

	public void setLoginOrWriteOffService(LoginOrWriteOffService loginOrWriteOffService) {
		this.loginOrWriteOffService = loginOrWriteOffService;
	}

	// 测试账号是否正确
	@Test
	public void test() {
		int i = 0;
		i = loginOrWriteOffService.login("", "2901001");
	}

	// 测试取出对象是否正确
	@Test
	public void testfff() {
		Object object = new Object();
		object = loginOrWriteOffService.loginInformation(1, "23030026");
		System.out.println(object);
	}

}
