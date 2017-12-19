package com.spring.service.impl;

import org.springframework.security.core.GrantedAuthority;

/**
 * User: GaoYuan
 * Date: 17/12/19
 * Time: 13:53
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
