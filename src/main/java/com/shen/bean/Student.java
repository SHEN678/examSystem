package com.shen.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/10 10:58 下午
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int studentId;
    private String examCardNumber;
    private String idCard;
    private String sname;
    private String ssex;
    private String sage;
    private String eNumber;
    private String tempfirst;
    private String tempsecond;
    private String sstate;
    private Exam exam;

}
