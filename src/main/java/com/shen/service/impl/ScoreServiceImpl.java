package com.shen.service.impl;

import com.shen.bean.ExportScore;
import com.shen.bean.Score;
import com.shen.mapper.ScoreMapper;
import com.shen.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author: shenDanSen
 * @date: 2021/6/24 12:21 上午
 * @desc:
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    //service调用mapper
    @Autowired
    private ScoreMapper scoreMapper;
    @Override
    public void addScore(List<Score> list) {
        scoreMapper.addScore(list);
    }

    //导出成绩表
    @Override
    public List<ExportScore> selectScore() {
        List<ExportScore> exportScores = scoreMapper.selectScore();
        return exportScores;
    }
}
