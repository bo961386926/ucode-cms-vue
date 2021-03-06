## 代码生成器使用指南

---

#### （一）模块简介

##### 1、功能亮点

实时读取库表结构元数据信息，比如表名、字段名、字段类型、注释等，选中修改后的表，点击`一键生成`，代码成即可提现出表结构的变化。

单表快速转化restful风格的API接口并对外暴露服务。对于百余张表的数据库，使用代码生成器让开发事半功倍。

多表连接查询。多表连接查询默认不开启，需要在全局文件中手动配置。

开启多表连接查询后，代码生成器会自动读取数据库元数据信息中的`主外键关系`，分别生成`一对一`、`一对多`、`多对多`风格的源代码。

生成的代码接口可通过Swagger暴露。

##### 2、运行依赖服务

代码生成器运行依赖`Mysql数据库`、`Redis服务`，版本不限。

- Mysql数据库

Mysql数据库中库表结构分为两部分，一是系统运行必要的系统表（跟代码生成业务无关），二是用户自定义的库表，代码生成将会读取。
项目SQL脚本提供5张测试表，方便用于测试使用。

- Redis服务

Redis服务跟代码生成业务无关，系统中其它业务用到Redis，因此也依赖Redis服务。

#### （二）快速入门

使用demo脚手架[cms demo项目](https://gitee.com/decsa/demo-code) ，脚手架是标准的Spring Web工程，稍微修改配置文件可快速入门上手。

```bash
# 拉去脚手架，并初始化项目
git clone https://gitee.com/decsa/demo-code.git
```
##### 1、修改数据库连接配置

目标数据库需要初始化，数据库脚本有两类：`系统SQL`和`用户自定义表结构`。

导入系统表后，用户需自定义业务表，方可体验代码生成的功能。

```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ucode-cms
    username: root
    password: 123456
```

除了Mysql数据库外，另外需要配置Redis的服务连接。

> 请注意，一定要启动Redis服务。

##### 2、可视化界面

配置完上述连接后，访问可视化界面操作需要生成代码的表

![](https://www.altitude.xin/typora/image-20220216105356896.png)

##### 3、重启项目

勾选表结构，点击生成，重启项目，相应的代码生效。访问地址`http://localhost:8080/doc.html` 访问接口列表。

---

#### （三）嵌入已有项目
如果想要将代码生成器嵌入已有项目中使用，可能会产生系统冲突问题，原因是代码生成器运行的环境本身是一个完整的系统。建议按照如下流程尝试：

- 使用UCode CMS作为项目脚手架，无缝兼容代码生成器，同时无缝拓展十余种功能组件
- 按照上述步骤，独立运行代码生成器，然后将生成的代码拷贝至已有项目中
- 将代码生成器嵌入已有项目中，如果能够正常运行，生成代码完毕，请移除相关依赖

上述三个选项任选其一即可。下面将嵌入已有项目的操作步骤。

##### 1、添加依赖
```xml
<!--Cms核心依赖（必选）-->
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-spring-boot-starter</artifactId>
    <version>1.5.4.2</version>
</dependency>

<!--代码本地化生成依赖（可选）-->
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-code-spring-boot-starter</artifactId>
    <version>1.5.4.2</version>
</dependency>
```
如果使用代码生成器功能，则代码本地化生成依赖为必选项。

##### 2、初始化数据库
项目中【cms（必选）.sql】脚本文件为系统运行的必要脚本，需要初始化。运行前先检查脚本中的库表结构是否有冲突，然后进行下一步。

##### 3、新建配置类
本配置类是因为封装不彻底的缘故，目前尚未找到很好的解决方式，因此带入到业务代码中。
```java
@Configuration
public class SecurityConfig extends AbstractSecurityConfig {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        super.configure(httpSecurity);
    }
}
```
Mysql数据库配置和Redis配置与上述保持一致，或者参考demo-code演示项目。

> 由于涉及到两个系统的合并，系统发生冲突不可预料，强烈不推荐将代码生成器嵌入到已有项目中使用

##### 4、移除依赖
在生成完代码后，移除相关依赖，如果生成后的代码包缺少包，请添加如下轻量依赖包（通用代码包）。
```xml
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-common</artifactId>
    <version>1.5.4.2</version>
</dependency>
```

#### （四）高级使用

在完成简单入门体验后，需要了解代码生成的高级部分，即全局配置。在项目中搜索类名`CodeProperties`即可查看所有内置可供修改的参数，在全局`yml`文件中覆盖默认值即可完成修改，按需配置。

##### 1、单表处理

当全局配置`ucode.code.joinQuery = false`或者使用默认配置时，则仅处理单表结构。将表名、字段名、字段类型、备注信息转化为SSM风格的源代码。

##### 2、多表连接

当在全局配置`ucode.code.joinQuery = true`时，则手动开启主外键查询，系统会自动读取表的主外键关系，并将其转化为连接查询的源代码。

##### 3、主要参数

| 参数                           | 默认值               | 使用备注                                           |
| ------------------------------ | -------------------- | -------------------------------------------------- |
| `ucode.code.projectDir`        | 空                   | 用户自定义多模块项目需要指定项目名                 |
| `ucode.code.removeTablePrefix` | `false`              | 根据需要是否在生成代码时移除表前缀                 |
| `ucode.code.packageName`       | `xin.altitude.front` | 配置用户自己的包名                                 |
| `ucode.code.useLombok`         | `true`               | 默认使用Lombok                                     |
| `ucode.code.filterSysTable`    | `true`               | 默认过滤不显示系统表                               |
| `ucode.code.joinQuery`         | `false`              | 是否使用连接查询（默认单表查询）                   |
| `ucode.code.xml.addXml`        | `false`              | 虽然说不建议使用XML文件编写SQL，但仍提高可开启开关 |
| `ucode.code.mapper.useCache`   | `true`               | 默认开启二级缓存，自定义业务缓存可关闭             |

#### （五）常见问题
##### 1、找不到依赖？
找不到依赖很大可能是因为阿里云景象仓库尚未同步完成Jar包，在项目中`pom`文件添加如下配置：
```xml
<repository>
    <id>public</id>
    <name>maven nexus</name>
    <url>https://repo1.maven.org/maven2/</url>
    <snapshots>
        <updatePolicy>always</updatePolicy>
    </snapshots>
    <releases>
        <updatePolicy>always</updatePolicy>
    </releases>
</repository>
```
##### 2、项目打包发布时如何处理代码生成器依赖？
项目打包发布时建议手动移除代码生成器依赖，实际上代码生成器的生命周期存在于开发阶段。手动不移出对打包不会产生影响，但不推荐这么做。
```xml
<!--代码生成器模块（非必选）-->
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-code-spring-boot-starter</artifactId>
    <version>1.5.4.2</version>
</dependency>
```

##### 3、多表连接查询异常
对表连接查询是一项复杂的业务，目前支持：一个员工对应一个部门（一对一）、一个部门对应多个员工（一对多）、一名学生可选修多门课程每门课程可被多名学生选修（多对多）。
更高级别的套娃尚不支持。

##### 4、生成后的代码能无缝迁移到新项目吗？
能。生成的代码具有高度的迁移属性，在新项目中导入缺省依赖即可。如果找不到相关依赖，请参考问题一。


---
> 如有疑问，可通过微信`dream4s`与作者联系。源码在[GitHub](https://gitee.com/decsa) ，视频讲解在[B站](https://space.bilibili.com/1936685014) ，本文收藏在[博客天地](http://www.altitude.xin) 。
---
