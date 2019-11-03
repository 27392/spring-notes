package cn.haohaoli.spring.tx.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author LiWenHao
 * @date 2019/11/2 23:49
 */
@Repository
public class AccountDao {

    private final JdbcTemplate jdbcTemplate;

    public AccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save () {
        String sql = "insert into t_account(c_name) value(?)";
        return jdbcTemplate.update(sql, "xx");
    }

    // @Transactional(propagation = Propagation.NEVER) 以非事务的方式运行,如果当前存在事务就报错
    // @Transactional(propagation = Propagation.REQUIRES_NEW) 如果存在事务就把该事务挂起,然后新建事务执行
    // @Transactional(propagation = Propagation.MANDATORY) 必须要存在事务,并在当前事务中执行(不开启新的事务),如果事务不存在就报错
    public int save1 () {
        String sql = "insert into t_account(c_name) value(?)";
//        return jdbcTemplate.update(sql, "yy");
        jdbcTemplate.update(sql, "yy");
        throw new RuntimeException();
    }
}
