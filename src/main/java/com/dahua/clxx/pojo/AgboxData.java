package com.dahua.clxx.pojo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class AgboxData extends Model<AgboxData> {

    private String id;
    private String type;
    private String code;
    private String pCode;
    private String status;
    private String data;
    private String createTime;
    private String updateTime;

}
