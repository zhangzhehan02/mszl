package com.mszlu.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class CategoryVo {

    /*@JsonSerialize(using = ToStringSerializer.class)*/
    private String id;

    private String avatar;

    private String categoryName;

    private String description;
}
