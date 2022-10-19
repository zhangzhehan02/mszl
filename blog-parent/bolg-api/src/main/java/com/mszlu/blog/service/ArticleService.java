package com.mszlu.blog.service;

import com.mszlu.blog.dao.pojo.Article;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.ArticleParam;
import com.mszlu.blog.vo.params.PageParams;

import java.util.List;

public interface ArticleService {

    /**
     * 分页查询 文章列表
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);
   /*
   * 首页最热的文章
   *
   * */
    Result hotArticle(int limit);
    /*
    * 首页最新文章
    *
    * */
    Result newArticles(int limit);
    /*
    * 文章归档
    * */
    Result listArchives();
    /*
    * 查看文章详情
    * */
    Result findArticleById(Long articleId);
    /*
    * 文章发布服务
    * */
    Result publish(ArticleParam articleParam);

    /**
     * 更新（浏览量，评论数）
     * @param article
     */
    Boolean updateNumById(Article article);

    /**
     * 获取所有文章详情
     * @return
     */
    List<Article> findArticleAll();

    /**
    * 获取文章详情
    * @param articleId
    * @return
    */
    Article getArticleById(Long articleId);
}
