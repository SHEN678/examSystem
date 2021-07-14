package com.shen.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @author: shenDanSen
 * @date: 2021/6/24 12:16 上午
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private int scoreId;
    private String examCardNumber;
    private String eNumber;
    private String score;
}
