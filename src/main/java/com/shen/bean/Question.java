package com.shen.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/13 12:32 上午
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private int questionId;
    private String eNumber;
    private String qType;
    private String qNumber;
    private String qStem;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private int qScore;
    private String qAnswer;
    private String TEMPFIRST;
    private String TEMPSECOND;
}
