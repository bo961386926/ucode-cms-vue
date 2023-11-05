## 公共代码模块使用指南

### 一、序言

公共代码模块大量使用Lambda表达式和流，封装常用工具类，能够显著减少业务代码复杂度。

在项目中添加如下依赖便可直接使用

```xml
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-common</artifactId>
    <version>1.6.4.1-beta</version>
</dependency>
```
如果提示缺失关联依赖，则按照提示引入关联依赖，后文为给出会用到的关联依赖列表，仅供参考。

仓库及版本选择建议使用[最新稳定版](https://mvnrepository.com/artifact/xin.altitude.cms/mybatis-plus-max)

beta版本属于公开测试版本，里面包含较新的功能与bug修复。

> 项目[开源地址](https://gitee.com/decsa/ucode-cms-vue) 其中`ucode-cms-common`模块便集中此部分源代码。



### 二、特色功能

##### 1、EntityUtils

`EntityUtils`实体转化工具类能够简化实体类与VO等转化的代码，支持将单个对象、集合对象、分页对象优雅的转换成其VO对象；能够将集合转换成Set集合、Map集合。

```java
Industry industry = new Industry();
IndustryNode industryNode = EntityUtils.toObj(industry, IndustryNode::new);
```

本质是基于`构造器`完成转换，相较于传统通过反射复制的方式效率更高。

<img src="https://www.altitude.xin/typora/image-20230329092717316.png" alt="image-20230329092717316" style="zoom: 33%;" />

更多的使用细节见源代码注释。[EntityUtils工具类使用](https://www.bilibili.com/video/BV1b841157US) 视频教程

##### 2、TreeUtils

无需过多的操作，只需一行代码，便可以将DO实体类转化成`树状结构`。

<img src="https://www.altitude.xin/typora/image-20230329093833888.png" alt="image-20230329093833888" style="zoom:50%;" />

偷懒想看视频，在这里。


- [TreeUtils一行代码实现列表转树（第一期）](https://www.bilibili.com/video/BV1fW4y1s7du)
- [TreeUtils一行代码实现列表转树（第二期）](https://www.bilibili.com/video/BV1yP4y117H7)
- [TreeUtils一行代码实现列表转树（第三期）](https://www.bilibili.com/video/BV1X14y1H7tD)

##### 3、RedisUtils

`RedisUtils`工具类封装了访问Redis的常用方法，提供静态方法的方式调用，对于字符串、数组、Hash、Set、ZSet、BitMap等数据结构访问方式都做了封装，使用更加方便。

<img src="https://www.altitude.xin/typora/Snipaste_2023-03-29_09-48-15.png" alt="Snipaste_2023-03-29_09-48-15" style="zoom: 33%;" />

更多工具类可直接阅读源码。

##### 4、SpringUtils

`SpringUtils`工具类提供以静态方法的方式获取Spring容器对象的能力
```java
SchedulerFactoryBean factoryBean = SpringUtils.getBean(SchedulerFactoryBean.class);
```
在一些静态方法、无法使用依赖注入的场景，`SpringUtils`工具类相当实用。

##### 5、JacksonUtils

在SpringBoot体系下，建议使用Jackson来进行JSON序列化。对象转JSON字符串、JSON字符串转对象等，都非常方便。

<img src="https://www.altitude.xin/typora/image-20230329095221460.png" alt="image-20230329095221460" style="zoom:50%;" />

##### 6、LockUtils

加锁受够了`try-catch`冗长的代码？试试这个工具类，让你的加锁过程变得清爽许多。

<img src="https://www.altitude.xin/typora/image-20230329095455746.png" alt="image-20230329095455746" style="zoom:50%;" />

既支持普通的JVM锁，也支持Redisson分布式锁。

### 附录


##### 关联依赖

关联依赖是为了避免版本冲突，将版本号管理交给用户所发生的依赖。

在使用相关功能是可能会出现依赖缺失，因此根据所使用的功能检查关联依赖即可。

当在使用`ucode-cms-common`缺失依赖时，从此列表中获取即可。

```xml
<!--web-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!--Redis-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

<!--RabbitMQ-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>

<!--Web socket-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-websocket</artifactId>
</dependency>

<!--MybatisPlus-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
<version>3.5.0</version>
</dependency>

<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>31.1-jre</version>
</dependency>

<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.12.0</version>
</dependency>

<!--Caffeine缓存-->
<dependency>
    <groupId>com.github.ben-manes.caffeine</groupId>
    <artifactId>caffeine</artifactId>
    <version>2.9.2</version>
</dependency>

<!--Redisson分布式锁-->
<dependency>
    <groupId>org.redisson</groupId>
    <artifactId>redisson-spring-boot-starter</artifactId>
    <version>3.19.1</version>
</dependency>
```


---
> 如有疑问，可通过微信`dream4s`与作者联系。源码在[GitHub](https://gitee.com/decsa) ，视频讲解在[B站](https://space.bilibili.com/1936685014) ，本文收藏在[博客天地](http://www.altitude.xin) 。

---
