package com.shen.mapper;

import com.shen.bean.Score;
import com.shen.bean.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/11 1:36 上午
 * @desc:
 */
@Repository
public interface StudentMapper {
   //表格数据插入到数据库
   void addStudent(List<Student> list);
   //联表查询到的学生信息
   List<Student> selectAllStu();
   //学生个人信息
   List<Student> stuCheckInfo(String examCardNumber);
   //更新学生表到状态
   void updateCheat(String examCardNumber );

}
