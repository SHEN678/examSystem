package com.shen.service;

import com.shen.bean.User;

import java.util.List;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/7 10:20 下午
 * @desc:
 */
public interface UserService {
    List<User> selectAllUser();
}
