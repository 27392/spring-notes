package cn.haohaoli.spring.ioc.service.impl;

import cn.haohaoli.spring.ioc.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author LiWenHao
 * @date 2019/11/3 19:06
 */
public class UserServiceImplTest {

    @Test
    public void save() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        UserService bean = applicationContext.getBean(UserService.class);
        bean.save();
    }
}
