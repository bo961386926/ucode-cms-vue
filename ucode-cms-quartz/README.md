### 定时任务使用指南

如果你想做定时任务，有`高可用`方面的需求，或者仅仅想`入门快`，上手简单，那么选用它准没错。

定时任务模块是对Quartz框架进一步封装，使用更加简洁。

##### 1、引入依赖

```xml

<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-quartz</artifactId>
    <version>1.5.3.1</version>
</dependency>
```

##### 2、快速上手

实现`org.quartz.Job`接口；使用注解`CronExp`添加任务的调度策略；使用注解`Component`将任务注入容器中。

启动项目，定时任务便处于监听与运行中。

```java

@Component
@DisallowConcurrentExecution
@CronExp(cron = "0/5 * * * * ?")
public class DemoJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("任务1：" + LocalDateTime.now());
    }
}
```

---

##### 3、手动触发定时任务

定时任务除了以既有频率周期性运行外，还有通过接口手动被触发的能力。

调用如下接口，可手动触发任务ID编号为`jobId`的任务。

```http
http://localhost:8080/cms-api/quartz/job/{jobId}
```

如果有手动触发定时任务的需求，则需要任务ID`唯一并`且`已知`，因此需要在编写定时任务时手动指定。

```java
@CronExp(id = 1, cron = "0/5 * * * * ?")
```
通过注解`CronExp`的id属性可指定任务ID，不显示指定则使用随机ID，不满足已知的条件，因此无法手动触发。

##### 4、带参数任务
尽管大多数任务不需要注入参数，但仍有少量的场景需要向定时任务注入参数。
```java
public void execute(JobExecutionContext context) {
    /* 如果在调用任务时传入了参数，则能够从Map中获取 */
    Map<String, Object> dataMap = context.getMergedJobDataMap();
    /* 比如从Map中获取一个键值对，一般来说参数均为基本数据类型 */
    Object key = dataMap.get("key");
    System.out.println("任务2：" + LocalDateTime.now() + ": " + key);
}
```
在编写定时任务时，可从`JobExecutionContext`对象中解析一个Map，从而完成参数的注入。

```http
http://localhost:8080/cms-api/quartz/job/1?key=a
```
上述http调用的含义是手动触发任务ID为【1】的任务，并且向其传递参数为【key】，值为【a】的参数。



---
> 如有疑问，可通过微信`dream4s`与作者联系。源码在[GitHub](https://gitee.com/decsa)，视频讲解在[B站](https://space.bilibili.com/1936685014)，本文收藏在[博客天地](http://www.altitude.xin)。
---
