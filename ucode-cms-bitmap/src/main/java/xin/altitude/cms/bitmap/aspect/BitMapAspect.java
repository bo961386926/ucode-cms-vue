/*
 *
 * Copyright (c) 2020-2022, Java知识图谱 (http://www.altitude.xin).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package xin.altitude.cms.bitmap.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.altitude.cms.bitmap.annotation.BitMap;
import xin.altitude.cms.bitmap.util.ParserUtils;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.util.RedisUtils;

import java.lang.reflect.Method;
import java.util.TreeMap;

/**
 * Redis BitMap AOP
 *
 * @author explore
 * @since 2021/03/28 10:49
 **/
@Aspect
public class BitMapAspect {
    private static final Logger logger = LoggerFactory.getLogger(BitMapAspect.class);

    @Pointcut("@annotation(xin.altitude.cms.bitmap.annotation.BitMap)")
    public void cacheAspect() {

    }

    @Around("cacheAspect()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        BitMap annotation = method.getAnnotation(BitMap.class);
        TreeMap<String, Object> treeMap = ParserUtils.createTreeMap(point, signature);
        String elResult = ParserUtils.parse(annotation.value(), treeMap);
        if (elResult != null) {
            long value = Long.parseLong(elResult);
            Boolean exist = RedisUtils.getBit(annotation.key(), value);
            if (exist == null || !exist) {
                logger.info(String.format("当前主键{%d}不存在，直接响应用户请求", value));
                if (method.getReturnType().equals(AjaxResult.class)) {
                    return AjaxResult.success();
                }
                return null;
            }else {
                return point.proceed();
            }
        }
        return null;
    }

}
