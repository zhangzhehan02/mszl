package com.mszlu.blog.service;

import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.TagVo;
import jdk.nashorn.internal.ir.LoopNode;

import java.util.List;

public interface TagService {



    List<TagVo> findTagsByArticleId(Long articleId);

    Result hots(int limit);
    /*
    * 查询所有的文章标签
    * */
    Result findAll();

    Result findAllDetail();

    Result findDetailById(Long id);

}
