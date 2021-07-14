package com.shen.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @author: shenDanSen
 * @date: 2021/6/23 2:41 上午
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private int answerId;
    private String eNumber;
    private String examCardNumber;
    private String qNumber;
    private String answer;
    private Question question;
}
