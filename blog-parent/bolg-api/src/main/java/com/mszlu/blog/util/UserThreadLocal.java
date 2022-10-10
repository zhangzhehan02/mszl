package com.mszlu.blog.util;


import com.mszlu.blog.dao.pojo.SysUser;

public class UserThreadLocal {

   private UserThreadLocal(){};

   //线程变量隔离

   public static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

   public static void put(SysUser sysUser){
       LOCAL.set(sysUser);
   }


   public static SysUser get(){
       return LOCAL.get();
   }

   public static void remove(){
       LOCAL.remove();

   }
}
