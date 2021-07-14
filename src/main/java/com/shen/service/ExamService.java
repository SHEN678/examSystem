package com.shen.service;

import com.shen.bean.Exam;

import java.util.List;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/11 3:02 下午
 * @desc:
 */
public interface ExamService {

    void addExam(List<Exam> list);
    //查询所有试卷信息
    List<Exam> selectAllExam();
    //更新时间
    Integer updateTime(long dTime);
}
