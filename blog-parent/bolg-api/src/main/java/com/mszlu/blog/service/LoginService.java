package com.mszlu.blog.service;

import com.mszlu.blog.dao.pojo.SysUser;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.LoginParam;


public interface LoginService {
    /*
    *  登录功能
    *
    * */
    Result login(LoginParam loginParam);


    SysUser checkToken(String token);
    /*
    *   退出登录
    *
    * */
    Result logout(String token);

    /*
    * 注册
    *
    * */
    Result register(LoginParam loginParam);

}
