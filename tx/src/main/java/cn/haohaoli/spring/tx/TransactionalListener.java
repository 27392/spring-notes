package cn.haohaoli.spring.tx;

import org.springframework.context.PayloadApplicationEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 事件监听器
 * @author LiWenHao
 */
@Component
public class TransactionalListener {

    private final JdbcTemplate jdbcTemplate;

    public TransactionalListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleBeforeCommit (PayloadApplicationEvent ss) {
        Object payload = ss.getPayload();
        System.out.println("提交前后监听器 : "+ payload);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAfterCommit (PayloadApplicationEvent ss) {
        Object payload = ss.getPayload();
        System.out.println("提交后监听器 : "+ payload);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleAfterRollback (PayloadApplicationEvent ss) {
        Object payload = ss.getPayload();
        System.out.println("回滚监听器 : "+ payload);
        int    update  = jdbcTemplate.update("delete  from t_account where id = ?", payload);
        System.out.println("删除子事务已提交的内容 : "+ (update == 1));
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void handleAfterCompletion (PayloadApplicationEvent ss) {
        Object payload = ss.getPayload();
        System.out.println("完成后监听器 : "+ payload);
    }





}
