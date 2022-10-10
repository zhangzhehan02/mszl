package com.mszlu.blog.dao.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class SysUser {

    /*
    *  @TableId(type = IdType.ASSIGN_ID)  //默认id类型
    *  以后用户多了,要进行分表操作,id就需要使用分布式id了
    *  @TableId(type = IdType.AUTO) 数据库自增
    * */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String account;

    private Integer admin;

    private String avatar;

    private Long createDate;

    private Integer deleted;

    private String email;

    private Long lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;
}
