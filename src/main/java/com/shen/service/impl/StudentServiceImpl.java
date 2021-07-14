package com.shen.service.impl;

import com.shen.bean.Student;
import com.shen.mapper.StudentMapper;
import com.shen.mapper.UserMapper;
import com.shen.service.StudentService;
import com.shen.util.ReadExcel;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/11 1:36 上午
 * @desc:
 */
@Service
public class StudentServiceImpl implements StudentService {
    //service 调mapper层
    @Autowired
    private StudentMapper studentMapper;

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public void addStudent(List<Student> list) {
        studentMapper.addStudent(list);
    }


    @Override
    public List<Student> selectAllStu() {
        List<Student> students = studentMapper.selectAllStu();
        return students;
    }
    //查询学生个人信息
    @Override
    public List<Student> stuCheckInfo(String examCardNumber) {
        List<Student> students1= studentMapper.stuCheckInfo(examCardNumber);
        return students1;
    }

    @Override
    public void updateCheat(String examCardNumber) {
        studentMapper.updateCheat(examCardNumber);
    }
}
