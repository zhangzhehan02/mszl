package com.mszlu.blog.controller;


import com.mszlu.blog.common.aop.LogAnnotation;
import com.mszlu.blog.common.cache.Cache;
import com.mszlu.blog.service.ArticleService;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.ArticleParam;
import com.mszlu.blog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//json数据进行交互
@RestController
@RequestMapping("articles")
public class ArticleController {


    @Autowired
    private ArticleService articleService;


    /**
     * 首页文章列表
     * @param pageParams
     * @return
     */
    @PostMapping
    //加上此日志 代表要对此接口记录日志
    @LogAnnotation(module = "文章",operator = "获取文章列表")
    @Cache(expire = 5 * 60 * 1000,name = "listArticle")
    public Result listArticle(@RequestBody PageParams pageParams){
       return articleService.listArticle(pageParams);
    }
    /*
    * 首页 最热的文章
    *
    * */
    @PostMapping("hot")
    @Cache(expire = 5 * 60 * 1000,name = "hot_article")
    public Result hotArticle(){
        int limit = 5;
        return articleService.hotArticle(limit);
    }
    /*
    * 最新文章
    * */
    @PostMapping("new")
    @Cache(expire = 5 * 60 * 1000,name = "views_article")
    public Result newArticles(){
        int limit = 5;
        return articleService.newArticles(limit);
    }
    /*
     *
     * */
    @PostMapping("listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }
    /*
    *
    *
    * */
    @PostMapping("view/{id}")
    @Cache(expire = 5 * 60 * 1000,name = "news_article")
    public Result findArticleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }

    /*
    *  发布文章
    *
    * */
    @PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam){
        return articleService.publish(articleParam);
    }

    @PostMapping("{id}")
    public Result articleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }
}
