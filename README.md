### 一、序言

`UCode Cms`内容管理系统是Java知识图谱的重要组成部分，是面向企业级应用软件开发的脚手架。当前版本`1.3.3`。

使用简易demo项目[快速体验](https://gitee.com/decsa/demo-cms)地址 。

#### （一）项目特点

##### 1、源码开源

源码开源并且使用MIT开源许可协议，个人或者企业用户免费放心使用，项目[源码地址](https://gitee.com/decsa/ucode-cms-vue)。

##### 2、快速上手

新建空项目，引入相关依赖，初始化数据库，启动项目，一个项目脚手架便搭建完成，更加便捷的操作是克隆[demo项目](https://gitee.com/decsa/demo-cms)，修改数据库连接并启动项目。

```xml
<!--ucode-cms核心依赖-->
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-spring-boot-starter</artifactId>
    <version>1.3.3</version>
</dependency>
```

#### （二）项目的内在逻辑

##### 1、标准化

标准化是UCodeCms项目的核心内涵，即将通用性的功能浓缩成不可修改的互相独立的Maven依赖，用户使用时可按需配置。

##### 2、高内聚低耦合

广泛使用高内聚低耦合设计原则，将用户很少修改的源码进行封装，同时对外提供可供修改配置的开关，降低使用成本。

##### 3、积木式项目结构

核心依赖仅需要引入`ucode-cms-spring-boot-starter`便可初始化项目，starter内置其余核心依赖。

```text
├── ucode-cms-common                        // Cms通用模块（依赖可跨项目使用）
├── ucode-cms-framework                     // Cms核心模块
├── ucode-cms-spring-boot-starter           // Cms starter（核心依赖）
├── ucode-cms-system                        // Cms 系统模块
```

在核心依赖的基础上定义了可选依赖：代码生成器模块、系统UI模块、系统监控模块、任务调度模块、Excel模块，可选依赖类似于积木，按需选配。

### 二、项目细节

#### （一）项目结构

UCode Cms源码项目结构如下

```text
├── cms-ui                              // 系统UI部分源码
├── sql                                 // 系统数据库SQL脚本
├── ucode-cms-admin                     // 测试demo项目（非必选）
├── ucode-cms-code                      // 代码生成器源码
├── ucode-cms-code-spring-boot-starter  // 代码生成器starter
├── ucode-cms-common                    // 通用代码部分（核心依赖、可跨项目使用）
├── ucode-cms-excel                     // Excel处理模块源码
├── ucode-cms-framework                 // 核心依赖
├── ucode-cms-job                       // 任务调度源码
├── ucode-cms-monitor                   // 系统监控源码
├── ucode-cms-spring-boot-starter       // 核心starter
├── ucode-cms-system                    // 系统部分源码
└── ucode-cms-ui                        // 系统UI部分依赖
```

#### （二）项目依赖

`UCode Cms`使用的是热门主流框架依赖。

| 依赖             | 版本       |
| ---------------- | ---------- |
| JDK              | 1.8        |
| Spring Framework | 5          |
| Spring Boot      | 2.6.3      |
| Mybatis Plus     | 3.5.0      |
| hutool           | 5.7.19     |
| Guava            | 31.0.1-jre |

#### （三）特色功能

`CmsConfig`配置类控制系统运行的行为，所有内置的配置可以在全局配置文件中修改。

##### 1、代码生成器

通过可视化界面勾选库表结构，即可达到本地化快速生成代码的效果。对于表间含有外键关系的库表结构同样支持，一对一、一对多、多对多可无缝衔接。 [快速体验](https://gitee.com/decsa/demo-code)地址 。![imgpng](https://www.altitude.xin/typora/image-20220216105356896.png)

##### 2、内置接口列表

项目出厂时便自带接口列表特性，可快速实现接口联调与对接，降低沟通成本。

![image-20220216172127541](https://www.altitude.xin/typora/image-20220216172127541.png)

可在全局配置文件中开启或者关闭接口列表。

##### 3、可视化任务调度

可视化任务调度模块需要单独引入，对于任务调度的管理非常友好。

![image-20220216172703350](https://www.altitude.xin/typora/image-20220216172703350.png)

引入依赖

```xml
<!--定时任务依赖（非必选）-->
<dependency>
    <groupId>xin.altitude.cms.job</groupId>
    <artifactId>ucode-cms-job</artifactId>
    <version>1.3.3</version>
</dependency>
```

全局配置文件启用

```properties
ucode.job.enabled: true
```

### 三、如何使用

#### （一）核心依赖

Maven对外提供的依赖清单如下：

```xml
<!--ucode-cms核心依赖-->
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-spring-boot-starter</artifactId>
    <version>1.3.3</version>
</dependency>
```

#### （二）可选依赖

可选依赖有两重含义：一是 `ucode-cms-spring-boot-starter`中未包含的依赖可按需添加，二是 `ucode-cms-spring-boot-starter`中包含尚使用不到的依赖可按需移除。

##### 1、通用代码

通用代码依赖可跨项目使用，不仅限于此项目。其中内置的工具类`EntityUtils`在MybatisPlus多表连接查询中发挥重要作用。

```xml
<dependency>
    <groupId>xin.altitude.cms.common</groupId>
    <artifactId>ucode-cms-common</artifactId>
    <version>1.3.3</version>
</dependency>
```

##### 2、可视化界面

可根据需要是否引入可视化界面。

```xml
<dependency>
    <groupId>xin.altitude.cms.ui</groupId>
    <artifactId>ucode-cms-ui</artifactId>
    <version>1.3.3</version>
</dependency>
```

##### 3、定时任务

若有定时任务可视化管理的需求，可引入此模块。

```xml
<dependency>
    <groupId>xin.altitude.cms.job</groupId>
    <artifactId>ucode-cms-job</artifactId>
    <version>1.3.3</version>
</dependency>
```

##### 4、系统监控

若有系统监控的需求，可引入此模块。

```xml
<dependency>
    <groupId>xin.altitude.cms.monitor</groupId>
    <artifactId>ucode-cms-monitor</artifactId>
    <version>1.3.3</version>
</dependency>
```

##### 5、表格处理

```xml
<dependency>
    <groupId>xin.altitude.cms.excel</groupId>
    <artifactId>ucode-cms-excel</artifactId>
    <version>1.3.3</version>
</dependency>
```

