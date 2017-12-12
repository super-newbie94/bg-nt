package com.bgnt.sys.controller;

import com.bgnt.api.request.SaveSysUserRequest;
import com.bgnt.spring.exception.BusinessException;
import com.bgnt.spring.response.BaseResponse;
import com.bgnt.sys.service.atom.ISysUserSV;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: GaoYuan
 * Date: 17/11/14
 * Time: 18:15
 */
@RestController
public class SysUserController {

    @Autowired
    private ISysUserSV sysUserSV;

    private final static Logger LOGGER = Logger.getLogger(SysUserController.class);

    @PostMapping("/api/v0.0.1/saveUser")
    public BaseResponse saveUser(@Validated @RequestBody SaveSysUserRequest sysUser, BindingResult result) {
        LOGGER.info("===保存系统用户===");
        List<ObjectError> list = result.getAllErrors();
        if (!CollectionUtils.isEmpty(list)) {
            StringBuffer stringBuffer = new StringBuffer();
            list.forEach(item ->
                    stringBuffer.append(item.getDefaultMessage() + ",")
            );
            stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
            throw new BusinessException("100010", stringBuffer.toString());
        }
        sysUserSV.insert(sysUser.getSysUser());
        return new BaseResponse();
    }
}
