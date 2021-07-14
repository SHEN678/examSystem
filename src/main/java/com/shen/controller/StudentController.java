package com.shen.controller;

import com.shen.bean.*;
import com.shen.service.impl.*;
import com.shen.util.ExportExcelUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/16 4:01 下午
 * @desc:
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private ExamServiceImpl examService;
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private AnswerServiceImpl answerService;
    @Autowired
    private ScoreServiceImpl scoreService;
    //跳转到登录页面
    @RequestMapping("/stuLogin")
    public String toStudentLogin(HttpServletRequest request){
        List<Exam> exams3= examService.selectAllExam();
        request.getSession().setAttribute("exams3",exams3);
        System.out.println("试卷"+exams3);
        return "studentLogin";
    }


// 学生登录
    @RequestMapping("/studentLogin")
    public String toCheckLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Student> studentList = studentService.selectAllStu();
        request.getSession().setAttribute("studentList",studentList);
        PrintWriter out =response.getWriter();

        String textName=request.getParameter("textName");
        String examCardNumber=request.getParameter("examCardNumber");
        String idCard=request.getParameter("idCard");
        String StuName=request.getParameter("StuName");

        System.out.println("下拉框:"+textName);
        System.out.println("准考证:"+examCardNumber);
        System.out.println("id:"+idCard);
        System.out.println("姓名:"+StuName);


        int check=0;
        if(textName.equals("请选择考试名称")){
            out.write("0");//选择考试名称
        }else {
            if(idCard.equals("")||examCardNumber.equals("")||StuName.equals("")){
                out.write("1");//数据不能为空
            }else{
                for (Student student : studentList) {
                        System.out.println(""+student.getSstate());
                        if(textName.equals(student.getExam().getEName())&&examCardNumber.equals(student.getExamCardNumber())&&idCard.equals(student.getIdCard())&&StuName.equals(student.getSname())){
                            if(student.getSstate().equals("作弊")) {
                                check=2;
                            }else{
                                List<Student> studentList2 = studentService.stuCheckInfo(examCardNumber);
                                request.getSession().setAttribute("studentList2",studentList2);
                                check=1;
                            }
                        }

                    }
                }
                if(check==1){
                    out.write("2");
                }else if(check==2){
                    out.write("4");
                }else {
                    out.write("3");//信息填写有误
                }
            }
        return null;
    }
    //跳转到学生信息核实
    @RequestMapping("checkInfo")
    public String toCheckInfo(){

        return "checkInfo";
    }

    //跳转到学生答题
    @RequestMapping("beginExam")
    public String toBeginExam(HttpServletRequest request){
        //题干
        List<Exam> exams4= examService.selectAllExam();
        request.getSession().setAttribute("exams4",exams4);
        //多选题目
        List<Question> questions3=questionService.mQuestion();
        request.getSession().setAttribute("questions3",questions3);
        //单选题目
        List<Question> questions4=questionService.sQuestion();
        request.getSession().setAttribute("questions4",questions4);

        return "beginExam";
    }
    //答题数据存入数据库
    @RequestMapping("beginAnswer")
    public String beginAnswer(HttpServletRequest request){
        String qNumber=request.getParameter("qNumber");
        String examCardNumber=request.getParameter("examCardNumber");
        String eNumber=request.getParameter("eNumber");
        String answer=request.getParameter("answer");
        System.out.println("题号"+qNumber);
        System.out.println("准考证"+examCardNumber);
        System.out.println("试卷编号"+eNumber);
        System.out.println("答案"+answer);
        //查找是否存在含有题号，准考证号，试卷编号的记录，存在则update答案，，，否则就插入数据
        Answer answer1=new Answer();
        answer1.setENumber(eNumber);
        answer1.setQNumber(qNumber);
        answer1.setExamCardNumber(examCardNumber);
        int i = answerService.selectAnswer(answer1);
        System.out.println("是否有记录"+i);
        if(i>0){
            //更新答案
            Answer answer2=new Answer();
            answer2.setENumber(eNumber);
            answer2.setQNumber(qNumber);
            answer2.setExamCardNumber(examCardNumber);
            answer2.setAnswer(answer);
            answerService.updateAnswer(answer2);
        }else{
            //插入新数据
            List<Answer> AnswerList2 = new ArrayList<Answer>();
            Answer answer3=new Answer();
            answer3.setAnswer(answer);
            answer3.setENumber(eNumber);
            answer3.setQNumber(qNumber);
            answer3.setExamCardNumber(examCardNumber);
            AnswerList2.add(answer3);
            answerService.addAnswer(AnswerList2);
        }

        return null;
    }
    //交卷
    @RequestMapping("subExam")
    @ResponseBody
    public void toSubExam(HttpServletRequest request,HttpServletResponse response,String examCardNumber,String studentName,String eNumber,Model model) throws IOException {

        System.out.println("准考证号"+examCardNumber);
        System.out.println("姓名"+studentName);
        System.out.println("试卷编号"+eNumber);

        List<Answer> answers= answerService.selectScore(examCardNumber);
        System.out.println(answers);
        int score=0;
        //获得总分
        for (Answer ans :answers) {
            if(ans.getAnswer().equals(ans.getQuestion().getQAnswer())){
                score+=ans.getQuestion().getQScore();
            }
        }
        //传成绩到前端
//        model.addAttribute("score",score);
        if(score==0){
            request.getSession().setAttribute("score",0);
        }else{
            request.getSession().setAttribute("score",score);
        }
//         request.getSession().setAttribute("score",score);
        //数据插入到成绩表
        List<Score> scoreList=new ArrayList<>();
         Score score1=new Score();
         score1.setExamCardNumber(examCardNumber);
         score1.setENumber(eNumber);
         score1.setScore(String.valueOf(score));
         scoreList.add(score1);
         scoreService.addScore(scoreList);//插入数据库
        //联表获取页面信息
        List<Student> studentList3 = studentService.stuCheckInfo(examCardNumber);
        request.getSession().setAttribute("studentList3",studentList3);
        //获得成绩
        System.out.println("总分："+score);

        //传回ajax
        response.getWriter().print(score);

    }
    //跳转到考试成绩页
    @RequestMapping("examGrade")
    public String toExamGrade(){

        return "examGrade";
    }
    //作弊被抓，跳转到登录页面
    @RequestMapping("cheat/{examCardNumber}/{eNumber}")
    public String cheatToCheckInfo(@PathVariable String examCardNumber, @PathVariable String eNumber){
        System.out.println(examCardNumber);
        System.out.println(eNumber);
        //数据插入到成绩表
        List<Score> scoreList=new ArrayList<>();
        Score score1=new Score();
        score1.setExamCardNumber(examCardNumber);
        score1.setENumber(eNumber);
        score1.setScore("作弊");
        scoreList.add(score1);
        scoreService.addScore(scoreList);//插入数据库
        //状态更新数据到学生表
        studentService.updateCheat(examCardNumber);

        return "studentLogin";
    }
   //导出成绩预览页面
    @RequestMapping("exportScore")
    public String toExportScore(HttpServletRequest request){
        List<ExportScore> ExportScores =scoreService.selectScore();
        System.out.println(ExportScores);
        request.getSession().setAttribute("ExportScores",ExportScores);


        return "exportScore";
    }
    //成绩导为excel
    @RequestMapping("exportExcel")
    public void toExcel(HttpServletRequest request,HttpServletResponse resp){
        List<ExportScore> scoreList  =scoreService.selectScore();//人员成绩列表集合
        String[] headers={"准考证号","考生姓名","考试科目","科目名称","工种","等级","成绩"};
        String fileName="学生成绩表";
        List<ExportScore> scores =new ArrayList<>();//这个集合为了进行0分判断
         for(ExportScore es:scoreList){
             if(es.getScore()==""||es.getScore()==null){
                 scores.add(new ExportScore(es.getExamCardNumber(),es.getSname(),es.getCourseId(),es.getCName(),es.getEWork(),es.getELevel(),"未考试"));
             }else{
                 scores.add(new ExportScore(es.getExamCardNumber(),es.getSname(),es.getCourseId(),es.getCName(),es.getEWork(),es.getELevel(),es.getScore()));
             }
         }
        System.out.println("遍历集合"+scoreList);
        System.out.println("导入数据库集合"+scores);
        ExportExcelUtil<ExportScore> exportExcelUtil=new ExportExcelUtil<>();
        exportExcelUtil.exportExcel(headers,scores,fileName,resp);
    }


}
