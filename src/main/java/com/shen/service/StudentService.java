package com.shen.service;

import com.shen.bean.Student;

import java.util.List;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/11 3:35 上午
 * @desc:
 */
public interface StudentService {
   void addStudent(List<Student> list);
//   联表查询到的学生信息
   List<Student> selectAllStu();

   List<Student> stuCheckInfo(String examCardNumber);
   //将作弊插入到学生到状态中
   void updateCheat(String examCardNumber );
}
