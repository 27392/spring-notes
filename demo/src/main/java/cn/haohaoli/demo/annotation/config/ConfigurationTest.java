package cn.haohaoli.demo.annotation.config;

import cn.haohaoli.demo.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 等同于我们写的`XML`
 * @author LiWenHao
 */
@Slf4j
@Configuration
public class ConfigurationTest {

    /**
     * `@Bean` 等于 `xml`标签中的`<bean>`标签
     * 返回值作为`class`属性
     * 方法名作为`id`属性,也可以在`@Bean`中指定名称
     */
    @Bean("person")
    public Person person01() {
        return new Person("lisi", 20);
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationTest.class);

        Object person = applicationContext.getBean("person");
        Person bean   = applicationContext.getBean(Person.class);

        log.info("{}", person);
        log.info("{}", bean);
    }
}
