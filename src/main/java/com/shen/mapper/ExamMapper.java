package com.shen.mapper;

import com.shen.bean.Exam;
import com.shen.bean.Question;
import com.shen.bean.Student;
import com.shen.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/11 3:01 下午
 * @desc:
 */
@Repository
public interface ExamMapper {
    //添加到数据库
    void addExam(List<Exam> list);
    //查询考试信息
    List<Exam> selectAllExam();
    //更新时间
    Integer updateTime(long dTime);
}
