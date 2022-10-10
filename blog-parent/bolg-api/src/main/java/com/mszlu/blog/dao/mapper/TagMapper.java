package com.mszlu.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mszlu.blog.dao.pojo.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {

    /*
    * 根据文章id查询标签列表
    *
    * */
    List<Tag> findTagsByArticleId(Long articleId);
   /*
   *
   * 查询最热的标签 前n条
   *
   * */
    List<Long> findHotsTagIds(int limit);

    List<Tag> findTagsByTagIds(List<Long> tagIds);

}
