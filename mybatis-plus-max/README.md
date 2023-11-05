### 一、MybatisPlusMax简介
`MybatisPlusMax`是`MybatisPlus`的增强包，秉承只拓展、不修改的理念，对MybatisPlus只做增强。

正如MybatisPlus是对MyBatis的增强，MybatisPlusMax是对MybatisPlus的增强，拓展理念一脉相承。

MybatisPlus依然进入软件成熟期，对其进行微小的修改会慎之又慎，向其提交修改PR周期较长，基于此考虑，为了更好的使用MybatisPlus，决定拓展第三方功能包。

当MybatisPlusMax代码逐步成熟后，作者愿意一次性无偿捐献给MybatisPlus官方团队，以方便用户更好的使用MybatisPlus。

使用`MybatisPlusMax`只需要在项目中引入如下依赖

```xml
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>mybatis-plus-max</artifactId>
    <version>1.6.4.1-beta</version>
</dependency>
```

如果提示缺失关联依赖，则按照提示引入关联依赖，后文为给出会用到的关联依赖列表，仅供参考。

仓库及版本选择建议使用[最新稳定版](https://mvnrepository.com/artifact/xin.altitude.cms/mybatis-plus-max)

beta版本属于公开测试版本，里面包含较新的功能与bug修复。

> 项目[开源地址](https://gitee.com/decsa/ucode-cms-vue) 其中mybatis-plus-max模块便集中此部分源代码。

### 二、与MybatisPlus的渊源
#### （一）MyBatis粉
早期的作者是MyBatis粉，如果你是从拼接SQL时代过来的，受够了在Java代码中拼接SQL字符串，那么MyBatis绝对让你爱不释手。

随着MyBatis的重度使用，也暴露出一些问题：访问数据库极其啰嗦，单表CURD兜兜转转需要写一大堆代码，每个项目有很多张表，每个开发者会参与很多个项目，此时的MyBatis显得过于笨重。

举个很简单的例子，对表的CURD是标配需求，MyBatis要求你不断重复的手写XML代码，我们知道不变的东西才是最稳定的，既然有共同的需求，那么抽离出相应的通用代码也是情理之中的事，于是MybatisPlus诞生了。

#### （二）MybatisPlus初版
MybatisPlus敏锐的发现MyBatis的痛点，着手对MyBatis重复业务代码进行封装，于是便形成了早期版本。

早期的MyBatisPlus字段名等`魔法值`充斥着Java代码里，并且为了构建查询条件，需要编写极其啰嗦的代码，很多MyBatis使用者对此嗤之以鼻。同样是魔法值，没有太大的区别，为什么不放在XML文件中呢？

被喷的原因如下：大量的字段名魔法值充斥在Java代码里，与早期的拼接SQL字符串有何区别；非常简单的查询条件，在XML文件中很容易完成，使用MybatisPlus构造查询条件啰啰嗦嗦等等。

早期的MybatisPlus为了改进MyBatis使用体验的想法是好的，可是在落地实践过程中带来了更大的问题，因此处于`不温不火`的状态。

#### （三）MybatisPlus新生
Java 8带来了Lambda表达式，为MybatisPlus重构代码带来了新的基础能力。MybatisPlus基于Java 8新特性进行代码重构，为其带来了新生，使用Lambda语法，干掉了Java代码里的字段名等`魔法值`，此时的MybatisPlus真正展示出吸引力。

Lambda表达式版本的MybatisPlus，不管是查询条件的构建，还是字段的选取，均使用Lambda表达式（方法引用）来完成，Java代码变得清爽。

**方法引用性能**

使用`方法引用`来替换`字段名`，是否会产生性能问题呢？会也不会。会是因为经过一层包装，性能确实有损耗，不会是因为MybatisPlus在使用反射的地方使用了缓存，有效的解决了中间过程耗时操作，因此可以认为Lambda版的MybatisPlus的性能略有下降。用略微下降一点性能的代价置换开发的灵活性，这一点是值得的。

况且现代服务器硬件逐年提高，从系统总体而言考虑，利大于弊。

基于`方法引用`实现编码，在遇到代码重构是有多爽就不用多说了。小步快跑、敏捷开发等，造成的屎山代码不胜枚举，代码重构的地位变得愈发重要。

### 三、MybatisPlusMax新增功能
#### （一）零SQL函数式编程时代
在Lambda表达式加持下的MybatisPlus，零SQL函数式编程成为了可能。零SQL是只尽量少显示的编写SQL语句，能不写就不写。对于疑难杂症，退回使用MyBatis XML更为方便，此时也不能一条道走到黑。

零SQL是一项挑战，对于很多一开始手撸SQL的开发者来说显得很别扭，归根结底是不愿意接纳新东西，排斥新内容罢了。

#### （二）MybatisPlusMax
`MybatisPlusMax`不是新内容新，是从`ucode-cms-common`包中抽离出来的代码，方便用户更好的使用和解藕。专注于MybatisPlus技术线的代码封装工作。

##### 1、DAO缓存

给DAO层`透明化`的增加缓存，能够极大的提高接口的响应效率。透明化意味着不知不觉间具备了缓存的能力，业务代码零感知。

使用的是Redis分布式缓存，Redis是继Mysql之后必备的组件，因此默认缓存使用Redis分布式缓存。考虑到项目的拓展性，选择Redis作为缓存更为合适。

<img src="https://www.altitude.xin/typora/Snipaste_2023-03-29_07-32-32.png" alt="Snipaste_2023-03-29_07-32-32" style="zoom: 33%;" />

>   原来实现`IService`接口，现在改为实现`ICacheService`接口，添加缓存、修改缓存、删除缓存完成闭环，用户无需考虑缓存生命周期，大大降低了开发成本。

有关DAO更多内容，请查看视频教程[增强MybatisPlus！一行代码整合Redis分布式缓存！](https://www.bilibili.com/video/BV1dM411m7RG/)

>   演示示例源代码见源码清单，朋友圈转发视频、做互动任务，解锁示例源码。

##### 2、自增自减零SQL编程

-   **方式一**

通过自定义`CustomLambdaUpdateWrapper`包装器，实现零SQL编程。

```java
public boolean updateUserAge(Long userId) {
    CustomLambdaUpdateWrapper<User> wrapper = new CustomLambdaUpdateWrapper<>();
    wrapper.incr(User::getAge, 1).eq(User::getUserId, userId);
    return update(wrapper);
}
```

视频教程[自增自减](https://www.bilibili.com/video/BV1SP411K7LZ/)

-   **方式二**

通过实现`IBaseService`自动获取增强方法API。

<img src="https://www.altitude.xin/typora/Snipaste_2023-03-29_07-46-48.png" alt="Snipaste_2023-03-29_07-46-48" style="zoom: 33%;" />

Java类支持单继承，多实现，因此直接进行多实现，引入增强功能。

>   演示示例源代码见源码清单，朋友圈转发视频、做互动任务，解锁示例源码。

##### 3、整合Redis BitMap

`缓存穿透`是日常开发过程中需要面对的问题，当你遇到数据库压力过大，需要保护数据库的时候，那么便需要考虑此问题。

```xml
/**
 * {@link UserMapper}切面
 */
@Aspect
@Component
public class UserMapperAspect extends BaseMapperAspect<User> {

    public static final String USER_KEY = "BITMAP_USER_KEY";

    @Override
    public String bitMapKey() {
        return USER_KEY;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
}
```

在项目中添加如下代码，即为User表注入Redis BitMap能力。

当触发保存数据SQL时，会自动将主键ID保存到Redis BitMap中；当出发删除数据SQL时，会自动从Redis BitMap中删除当前主键ID，能够自我完成数据动态维护。

当通过主键ID查询时，首先会检查当前主键在Redis BitMap中是否存在：如果存在，则允许访问数据库；不存在，则快速返回，无需访问数据库。这样就过保留真实流量访问数据库，减少了数据库压力。

配合上面`DAO缓存`模块使用，就解决了缓存穿透问题。

>   演示示例源代码见源码清单，朋友圈转发视频、做互动任务，解锁示例源码。

##### 4、优雅处理冗余字段

`冗余字段`一般都是为了`查询方便`而增加的字段，然而查询方便的同时意味着当关联表`添加数据`或者`删除数据`时，冗余字段需要做同步数据更新。

如果业务代码大量充斥着与主线业务无关的维护冗余字段的代码，一方面容易漏掉更新，造成冗余字段数据错误；另一方面，对于数据库事务，很多小伙伴掌握的并不好，数据库原子更新、回滚理论掌握并不扎实。优雅处理冗余字段这一通用需求便是在这一背景下应运而生。

[MybatisPlus优雅处理冗余字段](https://www.bilibili.com/video/BV1rs4y1H7Hx)视频教程

>   演示示例源代码见源码清单，朋友圈转发视频、做互动任务，解锁示例源码。

##### 5、复杂业务报表

通过封装`WrapperUtils`工具类，实现`and`和`or`	交替出现时复杂业务报表查询。具体使用场景举例：

```sql
((industry = ?) OR (industry = ?)) AND ((round = ?) OR (round = ?) OR (round = ?))
```

[MybatisPlus·业务报表·复杂SQL](https://www.bilibili.com/video/BV1Gv4y127Vd/)

>   演示示例源代码见源码清单，朋友圈转发视频、做互动任务，解锁示例源码。

#### （三）多表连接查询

在我们使用Lambda风格搞定单表查询之后，因业务需要，多表连接查询必不可少！为此作者在此方面也做了不少努力，既然选择零SQL编程，那么便坚持到底。

在这里需要说明几点，有不少声音认为，单表使用MybatisPlus，多表使用MyBatis，有这种想法的朋友可以更近一步，不然便是自欺欺人，挂着MybatisPlus之名干MyBatis之实。

实际上，MyBatis使用`join`来完成连表查询，本身是有性能缺陷的，数据库数据量膨胀越来越快，`join`连表查询的弊端愈发明显。《阿里巴巴开发者手册》明令禁止使用`join`查询，你还在坚持什么呢？

以Lambda版MybatisPlus单表查询为基础，通过Java内存拼装，完成多表连接查询是全新的选择。视频教程如下：

- [MybatisPlus多表连接查询](https://www.bilibili.com/video/BV1tP4y177rm/)
- [MybatisPlus多表连接查询合集说明](https://www.bilibili.com/video/BV1SG4y1H7aT/)
- [MybatisPlus多表连接查询一对一单条记录查询](https://www.bilibili.com/video/BV1K14y177SQ/)
- [MybatisPlus多表连接查询一对一查询列表数据](https://www.bilibili.com/video/BV1Pe4y117uu/)
- [MybatisPlus多表连接查询一对一分页查询数据](https://www.bilibili.com/video/BV1av4y1U7kR/)
- [MybatisPlus多表连接查询一对多查询列表数据](https://www.bilibili.com/video/BV1n44y1X73T/)
- [MybatisPlus多表连接查询一对多分页查询数据](https://www.bilibili.com/video/BV1T84y1k7oz/)
- [MybatisPlus多表连接查询结语总结](https://www.bilibili.com/video/BV14e4y1q7k3/)
- [用代码生成器自动化实现MybatisPlus多表连接查询](https://www.bilibili.com/video/BV17e4y1J7tu/)
- [MybatisPlus多表连接查询过滤敏感字段属性](https://www.bilibili.com/video/BV17m4y1F72y/)
- [MyBatis多表连接查询开启二级缓存脏数据案例分析](https://www.bilibili.com/video/BV1og411h7Nx/)
- [MybatisPlus多表连接查询之二级缓存](https://www.bilibili.com/video/BV1ze4y1n7ak/)

#### （四）零SQL分组查询

`分组查询`由于涉及到新增字段，因此普通的单表查询并不合适，不满足`DO`与数据库表结构一一对应的关系。通过对MybatisPlus源代码的阅读，找到了依然能够像`单表查询`的形式处理分组查询。

**视频教程**

- [MybatisPlus Lambda表达式 聚合查询 分组查询 lambdaQuery groupby](https://www.bilibili.com/video/BV1Gt4y1K7x1)
- [MybatisPlus Lambda分组查询 优雅高效统计部门人数](https://www.bilibili.com/video/BV11D4y1e7y2)
- [MybatisPlus Lambda表达式 聚合查询 分组查询 COUNT SUM AVG MIN MAX](https://www.bilibili.com/video/BV1324y1f726)

#### （五）MybatisPlus代码生成器

只要你想偷懒，那么Java代码生成器便是偷懒的利器，Java代码生成器以MybatisPlus为基础，通过在全局`yml`文件中复用数据库，读取数据库元数据信息，一件生成domain、mapper、service、controller等Spring MVC风格代码，以单表查询为主，兼顾多表连接查询，是你的开发好帮手。

文字教程[MybatisPlus代码生成器](https://www.altitude.xin/blog/home/#/chapter/2fd5a01a0e58c93774ee6feece727656)

**视频教程**

- [Java代码生成器 本地代码生成器](https://www.bilibili.com/video/BV1qY411b7tC)
- [Java代码生成器 基于SpringBoot MybatisPlus风格 重制版](https://www.bilibili.com/video/BV1Na411b7Xg)
- [Java代码生成器2.0（重制版）](https://www.bilibili.com/video/BV12Y4y1W7oc)

### 四、后记

##### 1、关于作者

作者叫`赛泰先生`，主要技术分享平台在B站，是一位MybatisPlus粉，基于MybatisPlus做实战技术分享，希望能对喜欢MybatisPlus的你有所帮助。

作者微信`dream4s`，请注明来意。

##### 2、关联依赖

```xml
<dependency>
    <groupId>xin.altitude.cms</groupId>
    <artifactId>ucode-cms-common</artifactId>
    <version>1.6.4.1-beta</version>
</dependency>
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-extension</artifactId>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>org.redisson</groupId>
    <artifactId>redisson</artifactId>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-tx</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
</dependency>
<!--aop-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-autoconfigure</artifactId>
</dependency>
```

关联依赖意味着相关内容在源代码编译期间使用过，因此需要在运行时也相应的提供。为了避免用户代码冲突及更好的进行版本管理，因此将管理权交给用户。

##### 3、实战源代码清单

MybatisPlus实战源代码清单，如果您想更好的掌握`实战级别`的MybatisPlus，可考虑将源码清单领回家。

| 模块名   | 实战内容   |
| ---- | ---- |
| 04001-springboot-mybatis-plus-intro |快速入门 |
| 04002-springboot-mybatis-plus-auto-fill-metainfo |字段属性全局自动填充 |
| 04003-springboot-mybatis-plus-enum |字段枚举 |
| 04004-springboot-mybatis-plus-typehandle |类型处理器 处理复杂类型 |
| 04005-springboot-mybatis-plus-custom-method |自定义Mapper方法 |
| 04006-springboot-mybatis-plus-tenant | 多租户|
| 04007-springboot-mybatis-plus-active-record |AR |
| 04008-springboot-mybatis-plus-id-generator |自定义ID生成器 |
| 04009-springboot-mybatis-plus-id-string | 自定义ID生成器（字符串）|
| 04010-springboot-mybatis-plus-resultmap |处理复杂返回值 |
| 04011-springboot-mybatis-plus-pagehelper |整合pagehelper分页 |
| 04012-springboot-mybatis-plus-pagination |分页 |
| 04013-springboot-mybatis-plus-logic-delete |逻辑删除 |
| 04014-springboot-mybatis-plus-optimistic-locker |乐观锁 |
| 04015-springboot-mybatis-plus-wrapper | 查询条件构造器|
| 04016-springboot-mybatis-plus-execution-protect |执行保护 |
| 04017-springboot-mybatis-plus-sequence |oracle等数据库保持顺序 |
| 04020-springboot-mybatis-plus-dao-cache |DAO层添加缓存 |
| 04021-springboot-mybatis-plus-dao-cache-lock |DAO通过加锁添加缓存 |
| 04022-springboot-mybatis-plus-business-report |复杂业务报表开发 |
| 04023-springboot-mybatis-plus-lambda-groupby |聚合函数lambda版实战 |
| 04024-springboot-mybatis-plus-lambda-count-sum-avg-min-max |多种类型聚合函数lambda版实战 |
| 04025-springboot-mybatis-plus-id-to-name |lambda版将ID字段替换成name字段 |
| 04026-springboot-mybatis-plus-pk-backfill | 主键回填|
| 04027-springboot-mybatis-plus-lambda-increase-decrease |lambda版自增-自减实现 |
| 04028-springboot-mybatis-plus-lambda-increase-decrease2 |lambda版自增-自减实现（自定义框架实现） |
| 04029-springboot-mybatis-plus-redundance-field | lambda版冗余字段实现|
| 04030-springboot-mybatis-plus-redundance-field2 | lambda版冗余字段实现（自定义框架实现）|

这里有《 [SpringBoot实战](https://www.bilibili.com/read/cv20939715) 》同样值得收藏。
