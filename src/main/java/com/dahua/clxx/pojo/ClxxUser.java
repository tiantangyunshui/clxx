package com.dahua.clxx.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class ClxxUser extends Model<ClxxUser> {
    private long id;
    private String no;
    private String userName;
    private String password;
    private String phone;
    private String type;
}
