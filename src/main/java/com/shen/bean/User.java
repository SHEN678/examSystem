package com.shen.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/7 11:46 下午
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String account;
    private String pwd;

//配置文件
//    Properties properties=new Properties();
//        //加载配置文件
//        properties.load(new FileInputStream("Config/config.properties"));
//        String driver =properties.getProperty("driver");
//        String url =properties.getProperty("url");

}
