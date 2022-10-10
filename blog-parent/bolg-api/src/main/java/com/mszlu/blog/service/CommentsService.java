package com.mszlu.blog.service;

import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.CommentParam;

public interface CommentsService {
    /*
    *  根据所有的文章ID查询所有的评论列表
    * */
    Result commentsByArticleId(Long id);

    Result comment(CommentParam commentParam);

}
