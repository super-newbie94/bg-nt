package com.spring.configuration;

import com.spring.filter.JWTAuthenticationFilter;
import com.spring.filter.JWTLoginFilter;
import com.spring.service.impl.CustomerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * User: GaoYuan
 * Date: 17/12/19
 * Time: 13:54
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 以下service需要在此注入
     */

    @Autowired
    private CustomerUserService userDetailsService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf验证
        http.csrf().disable()
                // 对请求进行认证
                .authorizeRequests()
                // 所有 / 的所有请求 都放行
//                .antMatchers("/").permitAll()
                // 所有 /login 的POST请求 都放行
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                // 权限检查
                .antMatchers("/api/*").hasAuthority("AUTH_WRITE")
                // 角色检查
                .antMatchers("/users/signup").hasRole("ADMIN")
                // 所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
                // 注入redis
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager(), stringRedisTemplate),
                        UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法，注入redis
                .addFilterBefore(new JWTAuthenticationFilter(stringRedisTemplate),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件，自定义的组件中通过数据库中的用户信息进行判断是否有效
        // 传入service
        auth.authenticationProvider(new CustomAuthenticationProvider(userDetailsService));
        auth.userDetailsService(userDetailsService);
    }


}
