package com.bgnt.api.request;

import com.bgnt.sys.dao.bo.SysUser;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * User: GaoYuan
 * Date: 17/11/14
 * Time: 18:17
 */

public class SaveSysUserRequest {
    @NotNull(message = "不能为空")
    @Valid
    private SysUser sysUser;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
