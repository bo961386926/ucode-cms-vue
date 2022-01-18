### 项目简介

`ucode-cms`是一款Maven版的后台管理系统，是快速构建简单项目的利器。

### 产品逻辑
UCode-Cms是按照设计模式中的开闭原则、高内聚低藕聚等原则，在开源项目上进行的一次封装，目的是标准化快速构建项目。


### 项目结构
#### 数据库文件
数据库脚本有两个
```text
sql/
├── cms.sql                                 // 核心脚本（必选）     
└── quartz.sql                              // 定时任务脚本（可选）
```
定时任务脚本只有在开启定时任务，并且配置持久化的情况下才必选。
```text
├── cms-ui                                  // 后台系统前端源代码（vue版）
├── sql                                     // 初始化SQL脚本
├── ucode-cms-admin                         // 测试starter使用项目
├── ucode-cms-code                          // 代码生成模块
├── ucode-cms-code-spring-boot-starter      // 代码生成模块starter
├── ucode-cms-common                        // Cms通用模块（依赖可跨项目使用）
├── ucode-cms-framework                     // Cms核心模块
├── ucode-cms-springboot-starter            // Cms starter（核心依赖）
├── ucode-cms-system                        // Cms 系统模块
└── ucode-cms-ui                            // 前端源码编译后依赖
```

### 快速使用

快速获取简单[cms demo项目](https://gitee.com/decsa/demo-cms)。

### 代码生成器模块
代码生成器模块属于可拆卸模块，即按需引入。为了增强配置灵活度，添加或者移除本模块需要简单的配置。
#### 前提条件
引入如下依赖是使用代码生成器功能的前提条件
```xml
<!--代码生成器模块（非必选）-->
<dependency>
    <groupId>xin.altitude.cms.code</groupId>
    <artifactId>ucode-cms-code-spring-boot-starter</artifactId>
    <version>1.2.6</version>
</dependency>
```
#### 全局配置
环境变量必须绑定为开发环境
```yml
spring:
  profiles:
    active: dev
```
#### 连接数据库
如果已经配置完数据库连接，则可忽略此步骤
```yml
spring:
  datasource:
    # 主库数据源
    url: jdbc:mysql://localhost:3306/ucode-cms?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
    username: root
    password: 123456
```

#### 可视化界面

访问可视化界面操作需要生成代码的表

<img src="https://www.altitude.xin/typora/image-20220118111705421.png" alt="image-20220118111705421" style="zoom:50%;" />

### 定时任务模块
