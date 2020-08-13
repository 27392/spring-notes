package cn.haohaoli.demo.xml;

import cn.haohaoli.demo.domain.Person;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author LiWenHao
 */
@CommonsLog
public class XmlMain {

    /*
    `AnnotationConfigApplicationContext`
        从一个或多个基于java的配置类中加载上下文定义，适用于java注解的方式。
    `ClassPathXmlApplicationContext`
        从类路径下的一个或多个xml配置文件中加载上下文定义，适用于xml配置的方式。
    `FileSystemXmlApplicationContext`
        从文件系统下的一个或多个xml配置文件中加载上下文定义，也就是说系统盘符中加载xml配置文件。
    `AnnotationConfigWebApplicationContext`
        专门为web应用准备的，适用于注解方式。
    `XmlWebApplicationContext`
        从web应用下的一个或多个xml配置文件加载上下文定义，适用于xml配置方式。
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        Object person = applicationContext.getBean("person");
        Person bean   = applicationContext.getBean(Person.class);
        System.out.println(person);
        System.out.println(bean);
    }
}
