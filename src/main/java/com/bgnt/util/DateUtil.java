package com.bgnt.util;

import java.sql.Timestamp;
import java.util.Date;

/**
 * User: GaoYuan
 * Date: 17/11/14
 * Time: 18:59
 */
public class DateUtil {
    /**
     * 获取当前系统时间
     * @return
     */
    public static Timestamp now() {
        return new Timestamp(new Date().getTime());
    }
}
