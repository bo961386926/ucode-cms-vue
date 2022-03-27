### 一、序言

`UCode Cms`管理系统是面向企业级应用软件开发的脚手架。希望构造一个合起来是一个系统，拆分出来是独立的组件供其它项目复用。

本项目与其它项目的显著区别是拒绝摊大饼式功能集成，核心追求是代码和功能复用。

##### 当前版本
```xml
<version>1.5.2</version>
```

### 二、项目基本情况
#### （一）项目特点

##### 1、源码开源

源码开源并且使用MIT开源许可协议，个人或者企业用户免费放心使用，项目源码地址：
```bash
git clone https://gitee.com/decsa/ucode-cms-vue.git
```

##### 2、快速上手

新建空项目，引入相关依赖，初始化数据库，启动项目，一个项目脚手架便搭建完成，更加便捷的操作是克隆[demo项目]，修改数据库连接并启动项目。

```xml
<!--ucode-cms核心依赖-->
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-spring-boot-starter</artifactId>
    <version>1.5.2</version>
</dependency>
```

#### （二）项目的内在逻辑
市面上各种管理系统做的非常好，作者无意重复造轮子，简要本项目的初衷。

大部分管理系统属于大杂烩性质的，说的难听点是堆砌功能，其实很多时候仅仅是想用其做个项目初始环境脚手架而已，并不想用过多的功能，齐全的功能此时却是累赘。

第一个特点：`积木式结构`。积木式结构允许使用者在核心依赖的基础上自由选择功能模块，体现的思想是功能复用，减少无意义的功能开发。

第二个特点：`暴露配置`。隐藏源码，暴露配置意味着使用者既能通过配置文件修改系统行为，又能较少的与不相干源码打交道。

第三个特点：`标准化代码`。鼓励使用者按照项目的框架提炼公共代码，按照类似插件性质予以封装，为本项目添砖加瓦。

##### 1、标准化

标准化是UCodeCms项目的核心内涵，即将通用性的功能浓缩成不可修改的互相独立的Maven依赖，用户使用时可`按需配置`。

##### 2、高内聚低耦合

广泛使用高内聚低耦合设计原则，将用户很少修改的源码进行封装，同时对外提供可供修改配置的开关，降低使用成本。

##### 3、积木式项目结构

核心依赖仅需要引入`ucode-cms-spring-boot-starter`便可初始化项目，starter内置其余核心依赖。

在核心依赖的基础上定义了可选依赖：代码生成器模块、系统UI模块、系统监控模块、任务调度模块、Excel模块。可选依赖类似于积木，按需选配。

### 三、项目细节

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


### 四、对外复用依赖
本系统基于Spring生态，除了满足自身需求外，还能够以Maven依赖的方式对外提供功能和代码的复用。
##### 1、公共代码
公共代码依赖广泛应用于本项目中，同时也能以工具类的方式对外提供服务。
```xml
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-common</artifactId>
    <version>1.5.2</version>
</dependency>
```
##### 2、代码生成器
作者很懒，对于重复性代码连复制粘贴的欲望也没有，于是跨项目版的代码生成器应运而生。支持单表代码生成、多表连接代码生成。
```xml
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-code-spring-boot-starter</artifactId>
    <version>1.5.2</version>
</dependency>
```
##### 3、分布式限流
使用注解，基于IP或者用户的方式对接口限流，支持分布式系统。
```xml
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-limiter</artifactId>
    <version>1.5.2</version>
</dependency>
```
##### 4、防重复提交
对于一些增加数据的场合，由于非幂等性的原因，网络不稳定容易产生脏数据，这时可使用防重复提交功能。
```xml
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-repeat</artifactId>
    <version>1.5.2</version>
</dependency>
```
##### 5、分布式日志收集
基于Redis pub/sub订阅特性实现的日志收集，用消息队列的方式收集日志，然后从另外系统消费日志（数据落库）。实现了跨系统日志收集复用。
```xml
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-log</artifactId>
    <version>1.5.2</version>
</dependency>
```
##### 6、统计API接口响应时间
基于AOP的方式封装统计API接口响应时间，按需引入。
```xml
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-take-time</artifactId>
    <version>1.5.2</version>
</dependency>
```
### 五、互相交流

如果在使用过程中有任何疑问，欢迎与我联系。

![IMG_552DA7C4E674-1](https://www.altitude.xin/typora/IMG_552DA7C4E674-1.png)


如果二维码失效，请添加微信号`dream4s`联系入群，谢谢。
