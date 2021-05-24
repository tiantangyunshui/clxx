package com.dahua.clxx.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户信息")
public class ClxxUser extends Model<ClxxUser> {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("学号")
    private String no;
    @ApiModelProperty("姓名")
    private String userName;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("原密码")
    @TableField(exist = false)
    private String oldPassword;
    @ApiModelProperty("电话")
    private String phone;
    @ApiModelProperty("类型0老师，1学生")
    private String type;
}
