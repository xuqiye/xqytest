package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.LocaleResolver;

import com.programs.dao.AdminDao;
import com.programs.service.AdminService;

public class Demo {
	ApplicationContext ac;
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		
	}
	@Test
	public void test(){
		LocaleResolver bean = ac.getBean("localeResolver",LocaleResolver.class);
		
		System.out.println(bean.toString());
	}
}
