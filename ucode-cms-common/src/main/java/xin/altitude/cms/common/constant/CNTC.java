/*
 * Copyright (c) 2021. 流深数据
 */

package xin.altitude.cms.common.constant;

/**
 * 定义标准CacheName名称列表
 * 不同名有不同的过期时间
 *
 * @author explore
 * @since 2021/05/12 16:34
 **/
public interface CNTC {
    String CACHE_DEFAULT = "CACHE_DEFAULT";
    String CACHE_05SECS = "CACHE_05SECS";
    String CACHE_10SECS = "CACHE_10SECS";
    String CACHE_15SECS = "CACHE_15SECS";
    String CACHE_30SECS = "CACHE_30SECS";
    String CACHE_01MINS = "CACHE_01MINS";
    String CACHE_03MINS = "CACHE_03MINS";
    String CACHE_05MINS = "CACHE_05MINS";
    String CACHE_1HOURS = "CACHE_1HOURS";
    String CACHE_1DAYS = "CACHE_1DAYS";
}
