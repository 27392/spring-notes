package cn.haohaoli.demo.annotation.scan;

import cn.haohaoli.demo.annotation.scan.filter.CustomFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.util.Arrays;

/**
 * https://docs.spring.io/spring/docs/5.0.0.RC2/spring-framework-reference/core.html#beans-postconstruct-and-predestroy-annotations
 * <p>
 * https://www.cnblogs.com/chengmi/p/12073945.html
 *
 * @author LiWenHao
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = "cn.haohaoli.demo.annotation.scan", includeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = CustomFilter.class)
}, useDefaultFilters = false)
public class ComponentScanTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ComponentScanTest.class);
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(log::info);
    }

    /*
    @ComponentScan(basePackages = "cn.haohaoli.demo.annotation.scan")
        componentScanTest
        componentExample
        controllerExample
        repositoryExample
        serviceExample
     */

    /*
    // 排除
    @ComponentScan(basePackages = "cn.haohaoli.demo.annotation.scan", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})
    })
        componentScanTest
        repositoryExample
     */

    /*
    // 包含
    @ComponentScan(basePackages = "cn.haohaoli.demo.annotation.scan", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Service.class, Repository.class})
    }, useDefaultFilters = false)
        componentScanTest
        repositoryExample
        serviceExample
     */

    /*
    // 指定类
    @ComponentScan(basePackages = "cn.haohaoli.demo.annotation.scan", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Repository.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ServiceExample.class)
    }, useDefaultFilters = false)
        componentScanTest
        repositoryExample
        serviceExample
     */

    /*
    // 正则
    @ComponentScan(basePackages = "cn.haohaoli.demo.annotation.scan", includeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "cn.haohaoli.demo.annotation.scan.*.(C|S)+.*Example")
    }, useDefaultFilters = false)
        componentScanTest
        controllerExample
        serviceExample
     */

    /*
    // 自定义过滤器
    @ComponentScan(basePackages = "cn.haohaoli.demo.annotation.scan", includeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = CustomFilter.class)
    }, useDefaultFilters = false)
        componentScanTest
        controllerExample
        myFilter
        repositoryExample
        serviceExample
     */
}

