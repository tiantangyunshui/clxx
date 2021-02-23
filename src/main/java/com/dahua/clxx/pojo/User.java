package com.dahua.clxx.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("clxx_user")
public class User extends Model<User> {
    private long id;
    private String no;
    private String userName;
    private String password;
    private String phone;
}
