package com.shen.service;

import com.shen.bean.Question;

import java.util.List;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/13 12:53 上午
 * @desc:
 */
public interface QuestionService {
    void addQuestion(List<Question> list);
    List<Question> mQuestion();
    List<Question> sQuestion();
}
