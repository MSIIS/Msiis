package com.web.soupe.roll;

import java.util.List;

/**
 * 封装外网数据获取结果
 * Created by nlf on 2015-8-17.
 */
public class OokerData {

    private String fieldNum;

    private String lotteyResult;

    private List<OokerKillNum> killNums;

    public OokerData() {
    }

    public String getFieldNum() {
        return fieldNum;
    }

    public void setFieldNum(String fieldNum) {
        this.fieldNum = fieldNum;
    }

    public String getLotteyResult() {
        return lotteyResult;
    }

    public void setLotteyResult(String lotteyResult) {
        this.lotteyResult = lotteyResult;
    }

    public List<OokerKillNum> getKillNums() {
        return killNums;
    }

    public void setKillNums(List<OokerKillNum> killNums) {
        this.killNums = killNums;
    }
}
