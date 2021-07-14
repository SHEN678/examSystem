package com.shen.service.impl;

import com.shen.bean.Exam;
import com.shen.mapper.ExamMapper;
import com.shen.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/11 3:03 下午
 * @desc:
 */
@Service
public class ExamServiceImpl implements ExamService {
      //service调用Mapper
    @Autowired
    private ExamMapper examMapper;

    public void setExamMapper(ExamMapper examMapper) {
        this.examMapper = examMapper;
    }

    @Override
    public void addExam(List<Exam> list) {
        examMapper.addExam(list);
    }

    @Override
    public List<Exam> selectAllExam() {
        List<Exam> exams=examMapper.selectAllExam();
        return exams;
    }

    @Override
    public Integer updateTime(long dTime) {
        Integer integer = examMapper.updateTime(dTime);
        return integer;
    }
}
