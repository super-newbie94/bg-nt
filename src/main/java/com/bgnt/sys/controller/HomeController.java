package com.bgnt.sys.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

/**
 * User: GaoYuan
 * Date: 17/11/13
 * Time: 17:19
 */
@RestController
public class HomeController {
    private final Logger LOGGER = Logger.getLogger(HomeController.class);
    @GetMapping(value = "/api/index")
    public Object index() {
        LOGGER.info("****访问主页****");
        Map<String ,String > map = new HashMap<>();
        map.put("1", "123");
        map.put("2", "123");
        map.put("3", "123");
        map.put("4", "123");
        return map;
    }

    @PostMapping("/users/signup")
    public Object auth() {
        return "hello";
    }
}
