package com.shen.service;

import com.shen.bean.Answer;

import java.util.List;

/**
 * @version 1.0
 * @author: shenDanSen
 * @date: 2021/6/23 2:50 上午
 * @desc:
 */
public interface AnswerService {
    //查询答题表是否有数据
    int selectAnswer(Answer answer);
    //更改answer的值
    void updateAnswer(Answer answer);

    void addAnswer(List<Answer> list);
    //成绩和答题表联合查询
    List<Answer> selectScore(String examCardNumber);
}
