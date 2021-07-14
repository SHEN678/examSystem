package com.shen.controller;

import com.alibaba.fastjson.JSONObject;
import com.shen.bean.Exam;
import com.shen.bean.Question;
import com.shen.bean.Student;
import com.shen.service.impl.ExamServiceImpl;
import com.shen.service.impl.QuestionServiceImpl;
import com.shen.service.impl.StudentServiceImpl;
import com.shen.util.ReadExcel;
import com.shen.util.UnZipFiles;
import com.shen.util.VerifyCode;
import jxl.read.biff.BiffException;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * @version 1.0
 * @author: shendansen
 * @date: 2021/6/8 1:47 上午
 * @desc:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    //controller调service层
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private ExamServiceImpl examService;
    @Autowired
    private QuestionServiceImpl questionService;
    //跳转管理登录页

    @RequestMapping("/homePage")
    public String toManageLogin(){

        return "manageLogin";
    }
    /**
     * 获取生成验证码显示到 UI 界面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    //验证码生成
    @RequestMapping(value="/checkCode")
    public void checkCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        VerifyCode verifyCode = new  VerifyCode();
        try {
            verifyCode.getRandcode(request, response);//输出图片方法
//            verifyCode.getRandomString()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //跳转到主界面
    @RequestMapping("/manageLogin")
    public  String toMain(String account, String pwd,String code,Model model,HttpSession session) throws IOException {
        //加载配置文件
            Properties properties=new Properties();
            properties.load(UserController.class.getClassLoader().getResourceAsStream("config.properties"));
            String account2 =properties.getProperty("account");
            String password =properties.getProperty("password");
            System.out.println(account2);
             System.out.println(password);
             //获得验证码并进行验证码判断
           if(code.equalsIgnoreCase((String) session.getAttribute("randomcode_key"))){
                 if(account==null||"".equals(account)||pwd==null||"".equals(pwd)){
                     model.addAttribute("msg","提示：账号或者密码为空，请重新填写");
                 }else{
                     if(account.equals(account2)&&pwd.equals(password)){
                         return "main";
                     }else{
                         model.addAttribute("msg","提示：账号或者密码错误");
                     }
                 }
           }else{
               model.addAttribute("msg","提示：验证码不正确，请重新填写");
               System.out.println("登录失败");
           }
        return "manageLogin";
     }
     //主页面跳转到考试管理页面
          @RequestMapping("/examManage")
          public String toExamManage(){

        return "examManage";
          }
          //跳转到帮助页面
          @RequestMapping("/help")
          public String toHelp(){

              return "help";
          }
     //文件上传
      @RequestMapping("/upload")
      @ResponseBody
      public String fileUpload(@RequestParam("file") CommonsMultipartFile file,String zipPwd,HttpServletRequest request) throws IOException, ZipException, BiffException {

          DiskFileItemFactory factory = new DiskFileItemFactory();
          ServletFileUpload upload = new ServletFileUpload(factory);
          // 中文处理
          upload.setHeaderEncoding("UTF-8");

          //获取文件名：file.getOriginalFilename()
          String upLoadfileName=file.getOriginalFilename();
          //如果文件为空直接回到首页
          if("".equals(upLoadfileName)){
              return null;
          }
          System.out.println("上传文件名："+upLoadfileName);
          //上传路径保存设置
          String path=request.getServletContext().getRealPath("/upload");
          //如果路径不存在 ，创建一个
          File realPath=new File(path);
          if(!realPath.exists()){
              realPath.mkdir();
          }
          System.out.println("上传文件保存地址："+realPath);
          InputStream is=file.getInputStream();//文件输入流
          OutputStream os=new FileOutputStream(new File(realPath,upLoadfileName));//文件输出流
          //读取写出
          int len=0;
          byte[] buffer=new byte[1024];
          while((len=is.read(buffer))!=-1){
              os.write(buffer,0,len);
              os.flush();
          }
          os.close();
          is.close();
          JSONObject json=new JSONObject();
          String path2=path+File.separator+upLoadfileName;
          System.out.println("解压码======="+zipPwd);
          System.out.println("路径不带压缩包"+path);
          System.out.println("路径带压缩包"+path2);
         boolean checkZipPwd= UnZipFiles.unZip(path2,path,zipPwd);
             json.put("msg","ok");
            if(checkZipPwd){
                json.put("code","1");
                //插入学生信息到数据库
                ReadExcel readExcel=new ReadExcel();
                List<Student> students= readExcel.getStudentInfo(path);
                System.out.println("插入students:"+students);
                studentService.addStudent(students);
                //插入试卷信息到数据库
                List<Exam> exams= readExcel.getExamInfo(path,request);
                System.out.println("插入exams:"+exams);
                examService.addExam(exams);
                //插入考试题目到数据库
                List<Question> questions= readExcel.getQuestionInfo(path);
                System.out.println("插入questions:"+questions);
                questionService.addQuestion(questions);
                //界面显现信息
                HttpSession session = request.getSession();
                session.setAttribute("exams",exams);

            }else{
                json.put("code","0");
            }
          System.out.println("json"+json.toString());
          return json.toString();
      }
    //主页面跳转到预览试卷页面
    @RequestMapping("/previewTest")
      public String toPreviewTest(HttpServletRequest request){
        //题干
        List<Exam> exams2= examService.selectAllExam();
        request.getSession().setAttribute("exams2",exams2);
        //多选题目
          List<Question> questions=questionService.mQuestion();
          request.getSession().setAttribute("questions",questions);
          //单选题目
        List<Question> questions2=questionService.sQuestion();
        request.getSession().setAttribute("questions2",questions2);
        return "previewTest";
    }
    //主页面跳转到预览学生信息
    @RequestMapping("/previewStu")
    public  String toPreviewStu(HttpServletRequest request){
        List<Student> students = studentService.selectAllStu();
        request.getSession().setAttribute("students",students);
        return "previewStu";
    }
    //监听管理
    @RequestMapping("/monitorManage")
    public  String toMonitorManage(HttpServletRequest request){
        //查找考试信息
        List<Exam> monExams= examService.selectAllExam();
        request.getSession().setAttribute("monExams",monExams);
        //查找所有学生信息
        List<Student> monStudents = studentService.selectAllStu();
        System.out.println(monStudents.get(0).getSstate());
        request.getSession().setAttribute("monStudents",monStudents);
        System.out.println("长度"+monStudents.size());
        if(monStudents.size()==0){
            return "examManage";
        }else{
            return "monitorManage";
        }
    }
    //时间更新
    @RequestMapping("/countDown")
    @ResponseBody
    public Integer updateTime(long dTime){
        System.out.println("时间====="+dTime);
        Integer integer = examService.updateTime(dTime);

        return integer;
    }
}
