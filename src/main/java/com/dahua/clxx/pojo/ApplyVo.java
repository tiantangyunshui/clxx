package com.dahua.clxx.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class ApplyVo {
    private long id;
    private long studentId;
    private long teacherId;
    private String type;
    private String reason;
    private String timeLeave;
    private String timeBack;
    private String state;
    private String authorState;
    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;
    private String studentName;
    private String teacherName;
    private String studentNo;
    private String phone;
}
