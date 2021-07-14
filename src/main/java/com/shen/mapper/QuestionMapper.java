package com.shen.mapper;

import com.shen.bean.Exam;
import com.shen.bean.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/13 12:53 上午
 * @desc:
 */
@Repository
public interface QuestionMapper {
    void addQuestion(List<Question> list);
    //多选题目
    List<Question> mQuestion();
    //单选题目
    List<Question> sQuestion();
}
