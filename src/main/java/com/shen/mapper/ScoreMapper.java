package com.shen.mapper;

import com.shen.bean.ExportScore;
import com.shen.bean.Score;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @author: shenDanSen
 * @date: 2021/6/24 12:19 上午
 * @desc:
 */
@Repository
public interface ScoreMapper {
    void addScore(List<Score> list );
    //导出成绩表
    List<ExportScore> selectScore();
}
