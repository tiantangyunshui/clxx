package com.dahua.clxx.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
//@TableName("clxx_apply")
@ApiModel("申请表")
public class ClxxApply extends Model<ClxxApply> {
    @ApiModelProperty("ID")
    @TableId("id")
    private Long id;
    @ApiModelProperty("学生ID")
    private Long studentId;
    @ApiModelProperty("教师ID")
    private Long teacherId;
//    @ApiModelProperty("学生姓名")
//    private String studentName;
//    @ApiModelProperty("教师姓名")
//    private String teacherName;
//    @ApiModelProperty("教师手机号")
//    private String phone;
    @ApiModelProperty("类型:0外出，1返校")
    private String type;
    @ApiModelProperty("事由")
    private String reason;
    @ApiModelProperty("离校时间")
    private String timeLeave;
    @ApiModelProperty("返校时间")
    private String timeBack;
    @ApiModelProperty("状态:0未审核1通过2不通过")
    private String state;
    @ApiModelProperty("权限下发状态:0未下发，1下发成功，2下发失败")
    private String authorState;
    @ApiModelProperty("我承诺上述填写内容真实，准确，无误！")
    private String remark1;
    @ApiModelProperty("近14天内无境内外中高风险区域居住史")
    private String remark2;
    @ApiModelProperty("无确诊，疑似病例接触史")
    private String remark3;
    @ApiModelProperty("无发烧，咳嗽，胸闷，气促")
    private String remark4;
//    @ApiModelProperty("人脸图片base64")
//    private String base64file;
}
