package com.spring.service.impl;

import com.bgnt.em.BaseResultCode;
import com.bgnt.sys.dao.bo.SysUser;
import com.bgnt.sys.dao.bo.SysUserCriteria;
import com.bgnt.sys.dao.mapper.interfaces.SysUserMapper;
import com.bgnt.sys.service.atom.ISysUserSV;
import com.google.gson.Gson;
import com.spring.exception.BusinessException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * User: GaoYuan
 * Date: 17/12/20
 * Time: 16:10
 */
@Service
public class CustomerUserService implements UserDetailsService {

    private final Logger LOGGER = Logger.getLogger(CustomerUserService.class);
    @Autowired
    private ISysUserSV iSysUserSV;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUserByUserName = iSysUserSV.findSysUserByUserName(username);
        if (null == sysUserByUserName) {
            LOGGER.error("========> 用户未找到:" + username);
            return new User(null, null, null);
        }
        // 这里设置权限和角色
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add( new GrantedAuthorityImpl("ROLE_ADMIN") );
        authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
        return sysUserByUserName;
    }
}
