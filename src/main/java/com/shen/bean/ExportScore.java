package com.shen.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @author: shenDanSen
 * @date: 2021/6/26 12:29 上午
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportScore {
    private String examCardNumber;
    private String sname;
    private String courseId;
    private String cName;
    private String eWork;
    private String eLevel;
    private String score;
}
