package cn.haohaoli.spring.tx.service;

import cn.haohaoli.spring.tx.Config;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author LiWenHao
 * @date 2019/11/3 0:02
 */
public class AccountServiceTest {

    @Test
    public void save() throws ExecutionException, InterruptedException {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-tx.xml");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        AccountService accountService = applicationContext.getBean(AccountService.class);
        accountService.asyncSave();

        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void s () throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(()-> {
                try {
                    System.out.println(Thread.currentThread().getName() + " execute task. ");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + " finished task. ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        System.out.println("main thread await. ");
        latch.await();
        System.out.println("main thread finishes await. ");
    }
}