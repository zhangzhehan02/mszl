package com.mszlu.blog.service;

import com.mszlu.blog.dao.pojo.SysUser;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.UserVo;

public interface SysUserService {

    UserVo findUserVoById(Long id);

    SysUser findUserById(Long id);

    SysUser findUser(String account, String password);

   /*
   *
   * 根据Token查询用户信息
   *
   * */
    Result findUserByToken(String token);
    /*
    * 根据账户查找用户
    *
    * */
    SysUser findUserByAccount(String account);
    /*
    *
    * 保存用户
    * */
    void save(SysUser sysUser);

}
