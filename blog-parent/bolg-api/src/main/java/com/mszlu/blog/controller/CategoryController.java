package com.mszlu.blog.controller;
import com.mszlu.blog.service.CategoryService;
import com.mszlu.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("categorys")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public Result categories(){
       return categoryService.findAll();
    }

    @GetMapping("detail")
    public Result categoriesDetail(){
        return categoryService.findAllDetail();
    }

    @GetMapping("detail/{id}")
    public Result categoriesDetail(@PathVariable("id") Long id){
        return categoryService.categoryDetailById(id);
    }
}
