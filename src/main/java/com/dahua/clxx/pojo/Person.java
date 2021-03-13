package com.dahua.clxx.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Person {
    @ApiModelProperty("id")
    private String id;
    private String no;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("电话")
    private String phone;
    private String type;
    private String faceImg;
}
