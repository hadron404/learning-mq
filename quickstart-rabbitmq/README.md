
## 运行环境

* JDK `17.0.2`
* Spring Boot `2.7.1`



## 遇见到的异常

IDEA中编译运行报 `无效的源发行版：17` 警告信息
可能的处理手段
* IDEA 中的 `Project Settings` 的 `Project` 和 `Module` 的 SDK 版本是否为 `build.gradle` 配置的版本
* [File | Settings | Build, Execution, Deployment | Build Tools | Gradle](jetbrains://idea/settings?name=Build%2C+Execution%2C+Deployment--Build+Tools--Gradle) 中确定 `Gradle JVM` 是否与 JDK 版本一致
