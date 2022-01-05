### 一、Ucode-Cms简介

`ucode-cms`是一款快速搭建管理类软件系统的脚手架，只需引入一个Maven依赖便可以快速构建SpringBoot Web应用。

### 二、使用

#### （一）懒人模式

懒人模式只需要download已经初始化的SpringBoot Web项目

```http
https://gitee.com/decsa/demo-cms.git
```

#### （二）手动模式

手动模式支持新建标准的SpringBoot工程，引入依赖，参考懒人模式按需配置，配置数据库连接。

##### 1、引入依赖

```xml
<!--ucode-cms核心依赖-->
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-spring-boot-starter</artifactId>
    <version>1.1.3</version>
</dependency>
<!--代码生成器模块（非必选）-->
<dependency>
    <groupId>xin.altitude.cms.code</groupId>
    <artifactId>ucode-cms-code-spring-boot-starter</artifactId>
    <version>1.1.3</version>
</dependency>
```

### 三、效果图

