# 系统架构
## 概述
- 项目背景、目标、核心业务
- 项目名称、版本、负责人

## 技术栈

- 核心框架: Spring Boot + Spring Data JPA
- 数据库: PostgreSQL
- 数据库版本控制: Liquibase
- 缓存: Redis
- 消息队列： RabbitMQ
- 安全框架: Spring Security
- API文档: Swagger/OpenAPI
- 构建工具: Maven
- 日志: Log4j2
- 定时任务: Quartz
- 报表导出: EasyExcel

## 模块划分与职责
 
1.'controller/'
- 包含所有 '@RestController' 类
- 不得包含任何业务逻辑
- 公开API必须添加Swagger注解文档

2.'service/'
- 在“@Service”类中包含业务逻辑
- 可能依赖于存储库或实用程序类
- 不得调用或依赖控制器

3.'repository/'
- 包含扩展 'JpaRepository' 或 'CrudRepository' 的接口
- 用于与 JPA 实体交互

4.'domain/'
- 包含 JPA“@Entity”类
- 表示数据库表
- 应仅在 'service' 或 'repository' 层中使用

5.'dto/'
- 包含数据传输对象 （DTO）
- DTO对象分为request和response两类，用于请求/响应正文和服务间通信,命名约定分别Request与Response结尾
- 不应包含业务逻辑或 JPA 注释，如 '@Entity'
- 公开API必须添加Swagger注解文档

6.'config/'
- 包含“@Configuration”和“@Enable...”类
- 示例：'SwaggerConfig'、'SecurityConfig'、'KafkaConsumerConfig' 等。

7.'util/'
- 包含跨层共享的无状态实用程序/帮助程序类

8.'src/main/resources/' 中的必需文件
- 'application.yml'
- 'application-dev.yml'
- 'static/' – 用于前端静态资产（如果需要）
- 'templates/' – 用于服务器端模板（例如，如果使用 Thymeleaf）
- 'messages.properties' – 用于 i18n/验证消息