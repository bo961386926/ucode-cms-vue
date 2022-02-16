### 一、序言

`UCode Cms`内容管理系统是Java知识图谱的重要组成部分，是面向企业级应用软件开发的脚手架。当前版本`1.3.3`。

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

#### （二）核心依赖

Maven对外提供的依赖清单如下：

```xml
<!--ucode-cms核心依赖-->
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-spring-boot-starter</artifactId>
    <version>1.3.3</version>
</dependency>
<!--代码生成器模块（非必选）-->
<dependency>
    <groupId>xin.altitude.cms.code</groupId>
    <artifactId>ucode-cms-code-spring-boot-starter</artifactId>
    <version>1.3.3</version>
</dependency>
```

#### （三）可选依赖

其中内置如下Maven依赖，对于未使用到的模块，可按需排除。

```xml
<dependency>
    <groupId>xin.altitude.cms.framework</groupId>
    <artifactId>ucode-cms-framework</artifactId>
</dependency>
<dependency>
    <groupId>xin.altitude.cms.common</groupId>
    <artifactId>ucode-cms-common</artifactId>
</dependency>
<dependency>
    <groupId>xin.altitude.cms.system</groupId>
    <artifactId>ucode-cms-system</artifactId>
</dependency>
<dependency>
    <groupId>xin.altitude.cms.job</groupId>
    <artifactId>ucode-cms-job</artifactId>
</dependency>
<dependency>
    <groupId>xin.altitude.cms.ui</groupId>
    <artifactId>ucode-cms-ui</artifactId>
</dependency>
<dependency>
    <groupId>xin.altitude.cms.monitor</groupId>
    <artifactId>ucode-cms-monitor</artifactId>
</dependency>
<dependency>
    <groupId>xin.altitude.cms.excel</groupId>
    <artifactId>ucode-cms-excel</artifactId>
</dependency>
```

#### （三）特色功能

##### 1、代码生成器

通过可视化界面勾选库表结构，即可达到本地化快速生成代码的效果。

对于表间含有外键关系的库表结构同样支持，一对一、一对多、多对多可无缝衔接。

##### 2、内置接口列表

项目出厂时便自带接口列表特性，可快速实现接口联调与对接，降低沟通成本。

<img src="https://www.altitude.xin/typora/image-20220117123240376.png" alt="image-20220117123240376" style="zoom:50%;" />

##### 3、可视化任务调度

项目出厂内置可视化任务调度模块，对于任务调度的管理非常友好。

上手文档见demo项目。