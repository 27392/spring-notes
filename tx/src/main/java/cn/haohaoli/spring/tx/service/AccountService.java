package cn.haohaoli.spring.tx.service;

import cn.haohaoli.spring.tx.dao.AccountDao;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.*;

/**
 * @author LiWenHao
 * @date 2019/11/2 23:27
 */
@Service
public class AccountService {

    private final AccountDao                accountDao;
    private final ApplicationEventPublisher publisher;

    ExecutorService executorService = Executors.newFixedThreadPool(2);

    public AccountService(AccountDao accountDao, ApplicationEventPublisher publisher) {
        this.accountDao = accountDao;
        this.publisher = publisher;
    }

    @Transactional
    public void save() {
        accountDao.save();
        accountDao.save1();
        throw new RuntimeException("save error");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void asyncSave1() throws InterruptedException {
        int save = accountDao.save();
        System.out.println("新增后的id : " + save);
        TimeUnit.SECONDS.sleep(2);
        // 异常后回滚
        publisher.publishEvent(new PayloadApplicationEvent<>(this, save));
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(accountDao::save2);
        voidCompletableFuture.join();
    }

    @Transactional(rollbackFor = Exception.class)
    public void asyncSave() throws ExecutionException, InterruptedException {

        Future<?> submit1 = executorService.submit((Runnable) accountDao::save);

        Future<?> submit = executorService.submit((Runnable) accountDao::save1);

        Object o = submit1.get();
        Object o1 = submit.get();

       /* CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(accountDao::save);
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.runAsync(accountDao::save1);
        try {
            CompletableFuture.allOf(voidCompletableFuture, voidCompletableFuture1).get();
            transactionManager.commit(status);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            transactionManager.rollback(status);
        }*/
    }
}
