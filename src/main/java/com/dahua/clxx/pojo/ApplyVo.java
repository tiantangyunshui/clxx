package com.dahua.clxx.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ApplyVo extends ClxxApply {
    @ApiModelProperty("序号")
    private int index;
    @ApiModelProperty("学生姓名")
    private String studentName;
    @ApiModelProperty("教师姓名")
    private String teacherName;
    @ApiModelProperty("学号")
    private String studentNo;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("人脸图像")
    private String faceImg;
}
