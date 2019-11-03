package cn.haohaoli.spring.ioc.service.impl;

import cn.haohaoli.spring.ioc.service.UserService;

/**
 * @author LiWenHao
 * @date 2019/11/3 19:08
 */
public class UserServiceImpl implements UserService {

    @Override
    public void save() {
        System.out.println("save user");
    }
}
