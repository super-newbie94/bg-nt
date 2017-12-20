package com.bgnt.sys.controller;

import com.bgnt.api.request.SaveSysUserRequest;
import com.bgnt.em.BaseResultCode;
import com.spring.exception.BusinessException;
import com.spring.response.BaseResponse;
import com.bgnt.sys.dao.bo.SysUser;
import com.bgnt.sys.service.atom.ISysUserSV;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: GaoYuan
 * Date: 17/11/14
 * Time: 18:15
 */
@RestController
public class SysUserController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    private ISysUserSV iSysUserSV;

    private final static Logger LOGGER = Logger.getLogger(SysUserController.class);

    @PostMapping("/api/v0.0.1/saveUser")
    public BaseResponse saveUser(@Validated @RequestBody SaveSysUserRequest sysUser, BindingResult result) {
        List<ObjectError> list = result.getAllErrors();
        if (!CollectionUtils.isEmpty(list)) {
            StringBuffer stringBuffer = new StringBuffer();
            list.forEach(item ->
                    stringBuffer.append(item.getDefaultMessage()).append(",")
            );
            stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
            throw new BusinessException(BaseResultCode.PARAMETER_ERROR.getValue(), stringBuffer.toString());
        }
        iSysUserSV.insert(sysUser.getSysUser());
        return new BaseResponse();
    }

    @GetMapping(value = "/api/v0.0.1/redis/string")
    public BaseResponse insertString() {
        stringRedisTemplate.opsForValue().set("stringkey", "testValue");
        return new BaseResponse();
    }

    /**
     * @Cacheable	表明Spring在调用方法之前，首先应该在缓存中查找方法的返回值。如果这个值能够找到，
     *              就会返回缓存的值。否则的话，这个方法就会被调用，返回值会放到缓存之中
     * @CachePut	表明Spring应该将方法的返回值放到缓存中。在方法的调用前并不会检查缓存，方法始终都会被调用
     * @CacheEvict	表明Spring应该在缓存中清除一个或多个条目
     * @Caching	    这是一个分组的注解，能够同时应用多个其他的缓存注解
     * @CacheConfig	可以在类层级配置一些共用的缓存配置
     * @param id
     * @return
     */
    @Cacheable(value = "searchUser")
    @GetMapping(value = "/api/v0.0.1/searchUser/{id}")
    public SysUser searchUser(@PathVariable String id) {
        SysUser sysUser = iSysUserSV.findSysUserById(id);
        return sysUser;
    }
}
