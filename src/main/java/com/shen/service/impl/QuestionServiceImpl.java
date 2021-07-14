package com.shen.service.impl;

import com.shen.bean.Question;
import com.shen.mapper.QuestionMapper;
import com.shen.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/13 12:53 上午
 * @desc:
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    //service调Mapper
    @Autowired
    private QuestionMapper questionMapper;

    public void setQuestionMapper(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Override
    public void addQuestion(List<Question> list) {
        questionMapper.addQuestion(list);
    }

    @Override
    public List<Question> mQuestion() {
        List<Question> questions=questionMapper.mQuestion();
        return questions;
    }

    @Override
    public List<Question> sQuestion() {
        List<Question> questions2=questionMapper.sQuestion();
        return  questions2;
    }
}
