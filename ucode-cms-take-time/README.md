### API接口使用时长 解决方案

##### 1、引入依赖

在你的项目中引入依赖
```xml
<dependency>
    <artifactId>ucode-cms</artifactId>
    <groupId>xin.altitude.cms</groupId>
    <version>1.6.4.1-beta</version>
</dependency>
```

确保你的项目是SpringBoot项目，如果AOP不生效，请继续补充如下依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

##### 2、添加标记注解 TakeTime

在控制器层中API方法添加TakeTime标记注解

```java
@GetMapping(value = "search")
@Duration
public Result search(Dto param) {
    // ...
}
```
注意要点：

- @Duration是添加到控制器API接口方法上的
- 返回值必须是Map的子类方能生效（如果返回值是其它类型，则无显示效果）

##### 3、效果演示

没有添加TakeTime标记注解的接口返回JSON数据形式

```json
{
    "code": 200,
    "msg": "操作成功",
    "data": {}
}
```

添加注解后，结果中增加`duration`字段

```json
{
    "code": 200,
    "msg": "操作成功",
    "data": {},
    "duration": "200毫秒"
}
```

后端控制台也会打印debug日志信息，请确保日志功能正常。

通过使用此模块，可以快速让你的项目具备API接口耗时统计能力
