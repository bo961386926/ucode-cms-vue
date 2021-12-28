/*
 * Copyright (c) 2021. 流深数据
 */

package xin.altitude.cms.common.constant;

/**
 * 定义标准CacheName名称列表
 * 不同名有不同的过期时间
 *
 * @Author explore
 * @Date 2021/05/12 16:34
 **/
public interface CNTC {
    String CACHE_DEFAULT = "CACHE_DEFAULT";
    String CACHE_5SECS = "CACHE_5SECS";
    String CACHE_10SECS = "CACHE_10SECS";
    String CACHE_15SECS = "CACHE_15SECS";
    String CACHE_30SECS = "CACHE_30SECS";
    String CACHE_1MINS = "CACHE_1MINS";
    String CACHE_3MINS = "CACHE_3MINS";
    String CACHE_5MINS = "CACHE_5MINS";
    String CACHE_1HOURS = "CACHE_1HOURS";
    String CACHE_1DAYS = "CACHE_1DAYS";
}
