package com.spring.filter;

import com.spring.service.impl.TokenAuthenticationService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * User: GaoYuan
 * Date: 17/12/19
 * Time: 13:56
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

    StringRedisTemplate stringRedisTemplate;

    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public JWTAuthenticationFilter(StringRedisTemplate stringRedisTemplate) {
        setStringRedisTemplate(stringRedisTemplate);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*
            验证当前的token是否有效，需进行redis操作读取redis中存储的用户token是否有效
         */
        Authentication authentication = TokenAuthenticationService
                .getAuthentication((HttpServletRequest)request, stringRedisTemplate);

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        chain.doFilter(request,response);
    }
}
