package com.spring.filter;

import com.bgnt.em.BaseResultCode;
import com.bgnt.sys.dao.bo.SysUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.spring.em.SysResultCode;
import com.spring.response.BaseResponse;
import com.spring.service.impl.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * User: GaoYuan
 * Date: 17/12/19
 * Time: 13:56
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    StringRedisTemplate stringRedisTemplate;

    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public JWTLoginFilter(String url, AuthenticationManager authManager, StringRedisTemplate stringRedisTemplate) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        setStringRedisTemplate(stringRedisTemplate);
    }

    /**
     * 拦截请求获取后返回令牌
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException{
        // JSON反序列化成 AccountCredentials
        SysUser sysUser = new ObjectMapper().readValue(request.getInputStream(), SysUser.class);

        // 返回一个验证令牌
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        sysUser.getUsername(),
                        sysUser.getPassword()
                )
        );
    }

    /**
     * 成功通过用户名和密码的验证把token作为json输出给用户
     * @param request
     * @param response
     * @param chain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response, FilterChain chain,
            Authentication auth) {

        String token = TokenAuthenticationService.addAuthentication(auth.getName());
        try {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            Gson gson = new Gson();
            // 存储token并且设置有效期
            stringRedisTemplate.opsForValue().set(auth.getName(), token, TokenAuthenticationService.EXPIRATIONTIME / 60, TimeUnit.SECONDS);
            response.getOutputStream().println(gson.toJson(new BaseResponse(BaseResultCode.OK.getValue(), token)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证失败返回错误信息
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        Gson gson = new Gson();
        response.getOutputStream().println(gson.toJson(new BaseResponse(SysResultCode.LOGIN_ERROR.getValue(), SysResultCode.LOGIN_ERROR.getDetail())));
    }
}
