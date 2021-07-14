package com.shen.util;

import com.shen.bean.Exam;
import com.shen.bean.Question;
import com.shen.bean.Student;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/11 12:23 上午
 * @desc:
 */
public class ReadExcel {
    //考生人数变量，方便插入到t_exam表
    private int sNumber;
    private String eNumber;
    private static long dTime;


    //学生信息
    public List<Student> getStudentInfo(String path) throws IOException, BiffException {
        //获取excel文件
        File file2 = new File(path+"/Test/ExamPaper.xls");
        //转化成输入流
        InputStream input = new FileInputStream(file2);
        //创建Workbook对象
        Workbook workbook = Workbook.getWorkbook(input);
        //获取第一张表（sheet1）
        Sheet readsheet = workbook.getSheet(0);
        //获取sheet表中的总行数
        int rsRows = readsheet.getRows();
        //获取sheet表中的总列数
        int rsColumns = readsheet.getColumns();
        System.out.println("总的行数："+rsRows);
        System.out.println("总的列数："+rsColumns);
        //创建集合把单元格中的数据存储起来
        List<String> list = new ArrayList<String>();//创建两个集合是希望按照行数进行存储
        List<Student> students = new ArrayList<Student>();//
        for (int i = 1; i < rsRows; i++)  //因为第一行是字段名称，所以此处从第二行开始取的数据
        {
            for (int j = 0; j < rsColumns; j++)
            {
                Cell cell = readsheet.getCell(j, i);//获取当前表的单元格
                String str = cell.getContents();//获取单元格中的数据
                list.add(str);//把获取到的数据添加至集合中
            }
                    System.out.println("循环中list："+list);//每一个学生的集合
                    Student student=new Student();
                    student.setExamCardNumber(list.get(0));
                    student.setSname(list.get(1));
                    student.setIdCard(list.get(2));
                    student.setENumber(list.get(3));
                    student.setSsex(list.get(4));
                    student.setSage(list.get(5));
                    student.setSstate("等待登录");
                    students.add(student);
                   list.clear();
        }
        System.out.println("共取了 "+students.size()+" 行的数据集");
        sNumber=students.size();// 考生人数全局。传入exam表
        eNumber=students.get(0).getENumber();//考试编号全局，传入question表
        System.out.println("eNumber信息"+eNumber);
        System.out.println("学生集合:"+students);
        return students;
    }
    //试卷信息
    public List<Exam> getExamInfo(String path, HttpServletRequest req) throws IOException, BiffException {
        //获取excel文件
        File file2 = new File(path+"/Test/Test.xls");
        //转化成输入流
        InputStream input = new FileInputStream(file2);
        //创建Workbook对象
        Workbook workbook = Workbook.getWorkbook(input);
        //获取第一张表（sheet1）
        Sheet readsheet = workbook.getSheet(0);
        //获取sheet表中的总行数
        int rsRows = readsheet.getRows();
        //获取sheet表中的总列数
        int rsColumns = readsheet.getColumns();
        System.out.println("总的行数："+rsRows);
        System.out.println("总的列数："+rsColumns);

        //创建集合把单元格中的数据存储起来
        List<String> list2 = new ArrayList<String>();//创建两个集合是希望按照行数进行存储
        List<Exam> exams = new ArrayList<Exam>();//
            int i=1;//只获取第二行到数据
            for (int j = 0; j < rsColumns; j++) {
                Cell cell = readsheet.getCell(j, i);//获取当前表的单元格
                String str = cell.getContents();//获取单元格中的数据
                list2.add(str);//把获取到的数据添加至集合中
            }
//          System.out.println("第"+(i+1)+"行"+"第"+(j+1)+"列的值是："+str);
            System.out.println("循环中list2："+list2);//试卷的集合
            Exam exam=new Exam();
        exam.setENumber(list2.get(0));
        exam.setEName(list2.get(1));
        exam.setETime(list2.get(2));
        dTime=Integer.parseInt(exam.getETime().split("分钟")[0])*60000;

          req.getSession().setAttribute("dTime",dTime);
        exam.setEType(list2.get(3));
        exam.setEWork(list2.get(4));
        exam.setEOrgan(list2.get(5));
        exam.setELevel(list2.get(6));
        exam.setEScore(Integer.valueOf(list2.get(7)));//分数转成整型
        exam.setMDescribe(list2.get(8));
        exam.setSDescribe(list2.get(9));
        exam.setCourseId(list2.get(10));
        exam.setCName(list2.get(11));
        exam.setSNumber(sNumber);//考生人数，从前一张表获取
            exams.add(exam);
        System.out.println("共取了 "+exams.size()+" 行的数据集");
        System.out.println("试卷集合:"+exams);
        return exams;
    }
      //题目信息
      public List<Question> getQuestionInfo(String path) throws IOException, BiffException {
          //获取excel文件
          File file2 = new File(path+"/Test/Test.xls");
          //转化成输入流
          InputStream input = new FileInputStream(file2);
          //创建Workbook对象
          Workbook workbook = Workbook.getWorkbook(input);
          //获取第一张表（sheet1）
          Sheet readsheet = workbook.getSheet(0);
          //获取sheet表中的总行数
          int rsRows = readsheet.getRows();
          //获取sheet表中的总列数
          int rsColumns = readsheet.getColumns();
          System.out.println("总的行数：" + rsRows);
          System.out.println("总的列数：" + rsColumns);
          //创建集合把单元格中的数据存储起来
          List<String> list3 = new ArrayList<String>();//创建两个集合是希望按照行数进行存储

          List<Question> questions = new ArrayList<Question>();//
          for (int i = 3; i < rsRows; i++)  //因为第一行是字段名称，所以此处从第二行开始取的数据
          {
              for (int j = 0; j < rsColumns - 3; j++) {
                  Cell cell = readsheet.getCell(j, i);//获取当前表的单元格
                  String str = cell.getContents();//获取单元格中的数据
                  list3.add(str);//把获取到的数据添加至集合中
              }
              System.out.println("循环中list3：" + list3);//题目集合
              Question question = new Question();
               question.setQType(list3.get(0));

               question.setQNumber(list3.get(1).trim());//题号,去掉空格
               question.setQStem(list3.get(2));
               question.setOptionA(list3.get(3));
              question.setOptionB(list3.get(4));
              question.setOptionC(list3.get(5));
              question.setOptionD(list3.get(6));
              question.setQAnswer(list3.get(7));
              question.setQScore(Integer.valueOf(list3.get(8)));//分值
              question.setENumber(eNumber);//试卷编号，从前面获取
              questions.add(question);
              list3.clear();
      }
          System.out.println("共取了 " + questions.size() + " 行的数据集");
          System.out.println("题目集合:" + questions);
          return questions;
      }
}
