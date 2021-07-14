package com.shen.service.impl;

import com.shen.bean.Answer;
import com.shen.mapper.AnswerMapper;
import com.shen.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: shenDanSen
 * @date: 2021/6/23 2:50 上午
 * @desc:
 */
@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerMapper answerMapper;
    //查询答题表是否有数据
    @Override
    public int selectAnswer(Answer answer) {
        int i = answerMapper.selectAnswer(answer);

        return i;
    }

    @Override
    public void updateAnswer(Answer answer) {
         answerMapper.updateAnswer(answer);
    }

    @Override
    public void addAnswer(List<Answer> list) {
        answerMapper.addAnswer(list);
    }
    //成绩和答题表联合查询
    @Override
    public List<Answer> selectScore(String examCardNumber) {
        List<Answer> answers = answerMapper.selectScore(examCardNumber);

        return answers;
    }
}
