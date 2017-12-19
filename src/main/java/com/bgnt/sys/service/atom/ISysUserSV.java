package com.bgnt.sys.service.atom;

import com.bgnt.sys.dao.bo.SysUser;

import java.util.Optional;

/**
 * User: GaoYuan
 * Date: 17/11/14
 * Time: 18:00
 */
public interface ISysUserSV {
    void insert(SysUser sysUser);

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    SysUser findSysUserById(String id);

    /**
     * 通过用户名查询
     * @param username
     * @return
     */
    SysUser findSysUserByUserName(String username);

}
