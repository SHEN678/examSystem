package com.shen.service;

import com.shen.bean.ExportScore;
import com.shen.bean.Score;

import java.util.List;

/**
 * @version 1.0
 * @author: shenDanSen
 * @date: 2021/6/24 12:20 上午
 * @desc:
 */
public interface ScoreService {
    void addScore(List<Score> list );
    //导出成绩表
    List<ExportScore> selectScore();
}
