package com.mszlu.blog.handler;

import com.mszlu.blog.dao.pojo.Article;
import com.mszlu.blog.service.ArticleService;
import com.mszlu.blog.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Set;

/**
 * @Author huing
 * @Create 2022-07-06 16:59
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableScheduling
public class ListenHandler {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisUtil redisUtil;

    private static final String VIEW_KEY = "viewNum";           //浏览量
    private static final String COMMENT_KEY = "commentNum";     //评论数

    //@PostConstruct该注解被用来修饰一个非静态的void（）方法。
    // 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。
    // PostConstruct在构造函数之后执行，init（）方法之前执行。
    //通常我们会是在Spring框架中使用到@PostConstruct注解 该注解的方法在整个Bean初始化中的执行顺序：
    //Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)
    @PostConstruct
    public void init(){
        log.info("数据初始化开始（浏览量，评论数）...");

        List<Article> articleList = articleService.findArticleAll();

        for (Article article : articleList) {
            //将浏览量、点赞数和评论数写入redis
            redisUtil.zAdd(VIEW_KEY,article.getId().toString(),article.getViewCounts());
            redisUtil.zAdd(COMMENT_KEY,article.getId().toString(),article.getCommentCounts());
        }

        log.info("数据（浏览量，评论数）已写入redis...");
    }

    //PreDestroy（）方法在destroy()方法执行执行之后执行
    @PreDestroy
    public void afterDestroy() {
        log.info("开始关闭...");
        //将redis中的数据写入数据库
        Set<ZSetOperations.TypedTuple<String>> viewNum = redisUtil.zReverseRangeWithScores("viewNum", 0, -1);
        Set<ZSetOperations.TypedTuple<String>> commentNum = redisUtil.zReverseRangeWithScores("commentNum", 0, -1);

        writeNum(viewNum, VIEW_KEY);
        writeNum(commentNum, COMMENT_KEY);
        log.info("redis写入数据库完毕");
    }

    @Scheduled(cron = "*/15 * * * * ?")         //每十五秒触发一次
    public void updateNum() {
        log.info("周期任务开始执行...");
        Set<ZSetOperations.TypedTuple<String>> viewNum = redisUtil.zReverseRangeWithScores("viewNum", 0, -1);
        writeNum(viewNum, VIEW_KEY);
        log.info("周期任务执行完毕,redis写入数据库完毕");
    }

    private void writeNum(Set<ZSetOperations.TypedTuple<String>> set, String fieldName) {
        set.forEach(item -> {
            Long id = Long.valueOf(item.getValue());
            Integer num = item.getScore().intValue();

            Article article = articleService.getArticleById(id);
            switch (fieldName) {
                case VIEW_KEY:
                    article.setViewCounts(num);
                    break;
                case COMMENT_KEY:
                    article.setCommentCounts(num);
                    break;
                default:
                    return;
            }

            //更新数据库
            articleService.updateNumById(article);
            log.info("{} 更新完毕", fieldName);
        });
    }
}
