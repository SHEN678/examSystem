package com.shen.service.impl;

import com.shen.bean.User;
import com.shen.mapper.UserMapper;
import com.shen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/8 12:24 上午
 * @desc:
 */
@Service
public class UserServiceImpl implements UserService {
    //service 调mapper层
    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> selectAllUser() {

        return userMapper.selectAllUser();
    }

}
