package cn.haohaoli.spring.tx.dao;

import cn.haohaoli.spring.tx.Account;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

/**
 * @author LiWenHao
 * @date 2019/11/2 23:49
 */
@Repository
public class AccountDao {

    private final JdbcTemplate              jdbcTemplate;
    private final ApplicationEventPublisher publisher;

    public AccountDao(JdbcTemplate jdbcTemplate, ApplicationEventPublisher publisher) {
        this.jdbcTemplate = jdbcTemplate;
        this.publisher = publisher;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int save () {
        publisher.publishEvent(new PayloadApplicationEvent<>(this,"1"));
        String sql = "insert into t_account(c_name) value(?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "1");
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        return generatedKeyHolder.getKey().intValue();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void save2 () {
        String sql = "select * from t_account where c_name = ?";
        List<Account> query = jdbcTemplate.query(sql,new Object[]{"1"}, (rs, rowNum) -> new Account(rs.getInt("id"),rs.getString("c_name")));

        if (query == null || query.size() == 0) {
            System.out.println("result empty");
            return;
        }
        System.out.println("result" + query);
        jdbcTemplate.update("update t_account set c_name = ? where id = ?", "xx", query.get(0).getId());

        int i = 1/0;
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
