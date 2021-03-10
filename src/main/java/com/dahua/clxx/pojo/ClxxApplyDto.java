package com.dahua.clxx.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("申请表")
public class ClxxApplyDto extends ClxxApply {
    @ApiModelProperty("学生姓名")
    private String studentName;
    @ApiModelProperty("教师姓名")
    private String teacherName;
    @ApiModelProperty("教师手机号")
    private String phone;
    @ApiModelProperty("人脸图片base64")
    private String base64file;
    @ApiModelProperty("已审核")
    private String stateNull;
}
