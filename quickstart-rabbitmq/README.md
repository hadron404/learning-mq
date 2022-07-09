
## Run Environment

* JDK `17.0.2`
* Spring Boot `2.7.1`


## Help Guide

### 0 [Gradle 构建 Spring Boot 项目使用指南](https://zhuanlan.zhihu.com/p/92706843)

### 1 [SpringBoot 整合 RabbitMQ 实战](https://blog.csdn.net/qq_38837032/article/details/121138866)
* 说明 Maven 下 SpringBoot 与 RabbitMQ 的集成与运行
* 最基础的使用MQ测试
* 分析消息乱码的原因及解决方案
* 一些常见的RabbitMQ的使用方式

### 2 [RabbitMQ 全量配置说明](https://zhuanlan.zhihu.com/p/145336656)
### 3 [RabbitMQ 各种工作模式实现案例](https://cloud.tencent.com/developer/article/1775294)
### 4 [RabbitMQ 的工作模式图解](https://www.cnblogs.com/Jeely/p/10784013.html)
### 5 [生产级消息队列配置](https://blog.csdn.net/swordcenter/article/details/101459376)
### 6 [消费者补偿幂等问题](https://www.cnblogs.com/toov5/p/10287183.html)
### 7 [RoutingKey详解](https://www.yisu.com/zixun/457019.html)
### 8 [RabbitMQ消息发送与接收](https://www.cnblogs.com/qlqwjy/p/13923237.html)
### 9 [实现重试次数](https://www.jianshu.com/p/4904c609632f)
### 10 [代码创建Queue、Exchange、Binding](https://zhuanlan.zhihu.com/p/75710822)
### 11 [Delay-Message 插件实现 RabbitMQ 延迟队列](https://zhuanlan.zhihu.com/p/121083535)
### 12 [RabbitMQ延时任务实现](https://blog.csdn.net/Sadlay/article/details/87161615)
### 13 [动态创建Queue、Exchange、Binding](https://blog.csdn.net/qq_28533563/article/details/107025629)
* Rabbit模型
### [通过 Delayed-message 插件实现延时任务](https://juejin.cn/post/6882747404894879758)

### [IDEA HTTP 工具](https://www.jianshu.com/p/2404654d655a)

### [SpringBoot整合RabbitMQ之手动消息确认（ACK）](https://blog.csdn.net/qq_38322527/article/details/103701101)
## Exceptions

### IDEA中编译运行报 `无效的源发行版：17` 警告信息
可能的处理手段
* IDEA 中的 `Project Settings` 的 `Project` 和 `Module` 的 SDK 版本是否为 `build.gradle` 配置的版本
* [File | Settings | Build, Execution, Deployment | Build Tools | Gradle](jetbrains://idea/settings?name=Build%2C+Execution%2C+Deployment--Build+Tools--Gradle) 中确定 `Gradle JVM` 是否与 JDK 版本一致

### Channel shutdown: channel error；
https://blog.csdn.net/qq_18671415/article/details/112037977
