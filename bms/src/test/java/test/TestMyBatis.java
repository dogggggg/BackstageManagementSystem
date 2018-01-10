package test;

import javax.annotation.Resource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.hab.bms.demo.model.Demo;
import com.hab.bms.demo.service.DemoService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:/spring/spring-mybatis.xml" })

public class TestMyBatis {
	private static Logger logger = LogManager.getLogger(TestMyBatis.class);
	// private ApplicationContext ac = null;
	@Resource
	private DemoService demoService = null;

	@Before
	// public void before() {
	// ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	// demoService = (DemoService) ac.getBean("demoService");
	// }

	@Test
	public void test1() {
		Demo demo = demoService.getDemoById(1);
		System.out.println(demo.getUserName());
		logger.info("值：" + demo.getUserName());
		System.out.println(JSON.toJSONString(demo));
		logger.info(JSON.toJSONString(demo));
	}
}