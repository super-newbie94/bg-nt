package com.spring.configuration;

import com.bgnt.sys.dao.bo.SysUser;
import com.spring.service.impl.CustomerUserService;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
/**
 * User: GaoYuan
 * Date: 17/12/19
 * Time: 13:55
 */
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    private final Logger LOGGER = Logger.getLogger(CustomAuthenticationProvider.class);
    /**
     * 通过WebSecurityConfig注入
     */
    CustomerUserService customerUserService;

    public CustomAuthenticationProvider(CustomerUserService userDetailsService) {
        super();
        // 这个地方一定要对userDetailsService赋值，不然userDetailsService是null (这个坑有点深)
        setCustomerUserService(userDetailsService);
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        // 通过用户名查找用户信息
        SysUser sysUser = (SysUser) customerUserService.loadUserByUsername(name);
        // 认证逻辑
        if (sysUser.getUsername().equals(name) && sysUser.getPassword().equals(password)) {
            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, sysUser.getAuthorities());
            return auth;
        } else {
            LOGGER.error("========> 登录信息错误 (" + name +":"+ password+")");
            throw new BadCredentialsException("登录信息异常");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public CustomerUserService getCustomerUserService() {
        return customerUserService;
    }

    public void setCustomerUserService(CustomerUserService customerUserService) {
        this.customerUserService = customerUserService;
    }
}
