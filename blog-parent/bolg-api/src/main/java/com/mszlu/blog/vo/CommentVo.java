package com.mszlu.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

@Data
public class CommentVo {

    /*
    *  防止前端,精度损失,把id转成String
    * 分布式id比较长,传到前端会有精度损失,必须转为String类型,进行传输,就没有太大问题了
    * */
   /* @JsonSerialize(using = ToStringSerializer.class)*/
    private String id;

    private UserVo author;

    private String content;

    private List<CommentVo> childrens;

    private String createDate;

    private Integer level;

    private UserVo toUser;
}
