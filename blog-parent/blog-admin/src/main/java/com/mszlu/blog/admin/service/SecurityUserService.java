package com.mszlu.blog.admin.service;

import com.mszlu.blog.admin.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //登录的时候 ,会把username 传递到这里
        //通过username查询 domin表,如果domin存在,将密码告诉springSecurity;
        //如果不存在,返回null 认证失败了
        Admin admin = this.adminService.findAdminByUsername(username);
        if (admin == null){
            //登录失败
            return null;
        }
        UserDetails userDetails = new User(username, admin.getPassword(), new ArrayList<>());
        return userDetails;
    }
}
