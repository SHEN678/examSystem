package com.shen.mapper;

import com.shen.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/8 12:04 上午
 * @desc:
 */
@Repository
public interface UserMapper {
   List<User> selectAllUser();
}
