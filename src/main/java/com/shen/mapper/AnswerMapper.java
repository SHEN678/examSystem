package com.shen.mapper;

import com.shen.bean.Answer;
import com.shen.bean.Exam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: shenDanSen
 * @date: 2021/6/23 2:49 上午
 * @desc:
 */
@Repository
public interface AnswerMapper {
    //查询答题表是否有数据
    int selectAnswer(Answer answer);

    //更改answer的值
   void updateAnswer(Answer answer);
    //添加新数据到数据库
    void addAnswer(List<Answer> list);
    //成绩和答题表联合查询
    List<Answer> selectScore(String examCardNumber);
}
