package com.bgnt.sys.service.atom.impl;

import com.bgnt.sys.dao.bo.SysUser;
import com.bgnt.sys.dao.mapper.interfaces.SysUserMapper;
import com.bgnt.sys.service.atom.ISysUserSV;
import com.bgnt.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * User: GaoYuan
 * Date: 17/11/14
 * Time: 18:00
 */
@Service(value = "sysUserSV")
public class SysUserSVImpl implements ISysUserSV {

    private final static Logger LOGGER = Logger.getLogger(SysUserSVImpl.class);
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public void insert(SysUser sysUser) {
        LOGGER.info("===进入service===");
        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setCreated("测试用户");
        sysUser.setCreatedTime(DateUtil.now());
        sysUser.setUpdated("测试用户");
        sysUser.setUpdatedTime(DateUtil.now());
        sysUserMapper.insert(sysUser);
    }
}
