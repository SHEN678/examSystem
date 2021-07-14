package state;

import java.util.HashMap;

/**
 * @version 1.0
 * @author: shenDanSen
 * @date: 2021/6/17 10:34 下午
 * @desc:
 */
public class StudentState {
    public static boolean EXAM_IS_START=false;//是否开始考试的状态
    public static String TIME=null;//开始考试的时间
    public static HashMap<String, HashMap> STUDENT=new HashMap<>();//用来存储学生和其对应的答案map
    public static HashMap<String,String> STUDENT_STATE=new HashMap<>();//用来存储学生具体状态的map
    public static String LAST_TIME=null;//实时获得剩余时间
 }
