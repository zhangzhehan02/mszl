package com.mszlu.blog.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mszlu.blog.admin.pojo.Admin;
import com.mszlu.blog.admin.pojo.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {

    @Select("SELECT * FROM ms_permission WHERE id IN(SELECT permission_id FROM ms_admin_permission WHERE admin_id=#{adminId})")
    List<Permission> findPermissionByAdminId(Long adminId);

}
