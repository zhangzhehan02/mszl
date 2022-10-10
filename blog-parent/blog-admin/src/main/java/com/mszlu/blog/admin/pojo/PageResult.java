package com.mszlu.blog.admin.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private List<T> list;

    private Long total;
}
