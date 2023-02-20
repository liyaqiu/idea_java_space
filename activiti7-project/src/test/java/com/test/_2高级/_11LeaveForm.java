package com.test._2高级;

import java.io.Serializable;

/**
 * @author eric
 * @date 2023/2/12 16:29
 * 请假单
 **/

public class _11LeaveForm implements Serializable {
    private String applicant;
    private Integer num;
    private String desc;

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
