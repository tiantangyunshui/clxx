package com.dahua.clxx.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("申请表状态")
public class ApplyStateDto {
    @ApiModelProperty("ID")
    private String id;
    @ApiModelProperty("状态:0未审核1通过2不通过")
    private String state;
}
