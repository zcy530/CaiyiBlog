package com.lrm.service;

import com.lrm.po.User;
/**
 * Created by ZhangCaiyi on 2021/5/30.
 */
public interface UserService {

    User checkUser(String username, String password);
}
