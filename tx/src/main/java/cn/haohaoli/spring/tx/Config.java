package cn.haohaoli.spring.tx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author LiWenHao
 * @date 2019/11/3 15:32
 */
@Configuration
@ComponentScan(value = "cn.haohaoli.spring.tx")
@EnableTransactionManagement
public class Config {

    @Bean
    public DataSource dataSource () {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://haohaoli.cn:33066/spring?useSSL=false");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("mysql123321");
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate (DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager (DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
