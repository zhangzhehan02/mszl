package com.mszlu.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mszlu.blog.dao.mapper.ArticleMapper;
import com.mszlu.blog.dao.pojo.Article;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {

    /*
    *  期望此线程在线程池里执行 不会影响到原有的主线程
    * */
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {

        int viewCounts = article.getViewCounts();
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(viewCounts+1);
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId,article.getId());
        //设置一个 为了在多线程情况下 线程安全
        updateWrapper.eq(Article::getViewCounts,viewCounts);
        // update article set view_count=100 where view_count=100 and id=11
        articleMapper.update(articleUpdate,updateWrapper);
/*        try {
            Thread.sleep(5000);
            System.out.println("更新完成了....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
