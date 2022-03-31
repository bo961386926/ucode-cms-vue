// /*
//  * Copyright (c) 2021. 北京流深数据科技有限公司
//  */
//
// package xin.altitude.cms.quartz.aspect;
//
// import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Pointcut;
// import org.aspectj.lang.reflect.MethodSignature;
//
// import java.lang.reflect.Method;
//
//
// /**
//  * 耗时统计，借助AOP统一处理切片逻辑
//  *
//  * @author explore
//  */
// @Aspect
// public class QuartzAspect {
//     /**
//      * 统计请求的处理时间
//      */
//     ThreadLocal<Long> startTime = new ThreadLocal<>();
//
//     /**
//      * 带有@TakeTime注解的方法
//      */
//     @Pointcut("target(org.quartz.Job+)")
//     public void time() {
//
//     }
//
//
//     @Around("time()")
//     public Object doAround(ProceedingJoinPoint point) throws Throwable {
//         MethodSignature signature = (MethodSignature) point.getSignature();
//         Method method = signature.getMethod();
//         /* 方法被调用执行前 */
//         startTime.set(System.currentTimeMillis());
//         point.proceed();
//         /* 方法被调用执行后 */
//         Long a = startTime.get();
//         long b = System.currentTimeMillis();
//         startTime.remove();
//         System.out.println("l = " + (b - a));
//         return null;
//     }
//
//     private String calTime(long time) {
//         if (time < 100) {
//             return String.format("%s毫秒", time);
//         } else {
//             return String.format("%s秒", time * 1.0 / 1000);
//         }
//     }
// }
