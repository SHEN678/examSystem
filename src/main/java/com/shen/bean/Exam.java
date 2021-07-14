package com.shen.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/11 3:01 下午
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    private int examId;
    private String eNumber;
    private String eName;
    private String eTime;
    private String eType;
    private String eWork;
    private String eOrgan;
    private String eLevel;
    private int sNumber;
    private int eScore;
    private String courseId;
    private String cName;
    private String mDescribe;
    private String sDescribe;
    private String TEMPFIRST;
    private String TEMPSECOND;
    private Long dTime;

}
