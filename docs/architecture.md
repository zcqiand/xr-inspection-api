## 概述

## 技术栈

- 核心框架: Spring Boot + Spring Data JPA
- 数据库: PostgreSQL
- 数据库版本控制: Liquibase
- 安全框架: Spring Security
- API文档: Swagger/OpenAPI
- 构建工具: Maven
- 其他可选: Redis(缓存), Quartz(定时任务), EasyExcel(报表导出)

## 文件结构
 
1.'@SpringBootApplication' 类
- 将主类放在根基础包中，例如“com.company.project”
- 确保组件扫描拾取所有子包

2.'controller/'
- 包含所有 '@RestController' 类
- 不得包含任何业务逻辑
- 公开API必须添加Swagger注解文档

3.'service/'
- 在“@Service”类中包含业务逻辑
- 可能依赖于存储库或实用程序类
- 不得调用或依赖控制器

4.'repository/'
- 包含扩展 'JpaRepository' 或 'CrudRepository' 的接口
- 用于与 JPA 实体交互

5.'domain/'
- 包含 JPA“@Entity”类
- 表示数据库表
- 应仅在 'service' 或 'repository' 层中使用
- 类需要注释
- 类保留注解`@Column`中的`precision`、`scale`、`length`,移除注解 `@Column` 中`name`属性
- 类包含注解`@FieldNameConstants`, 该注解来自lombok.experimental.FieldNameConstants
- 类包含注解`@DynamicUpdate`, 该注解来自org.hibernate.annotations.DynamicUpdate
- 类包含注解`@Accessors(chain = true)`, 该注解来自lombok.experimental.Accessors
- 将`Set`类型改为`List`，并增加 import java.util.List;
- 将`new LinkedHashSet<>()`改为`new ArrayList<>()`，并增加 import java.util.ArrayList;
- 将`Float`、`Double`类型改为`BigDecimal`。
- 将所有日期类型改为`OffsetDateTime`类型。

```java 代码示例
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
@DynamicUpdate
@Accessors(chain = true)
@Comment("待办事项")
@Entity
@Table(name = "todo")
public class Todo {
    @Comment("事项标识")
    @Column(nullable = false)
    private UUID id;

    @Comment("事项名称")
    @Column(nullable = false)
    private String name;

    @Comment("创建时间")
    @Column
    private OffsetDateTime createdDate;

    @Comment("备注")
    @Nullable
    @Column(length = 500)
    private String remark;
}
```

6.'dto/'
- 包含数据传输对象 （DTO）
- DTO对象分为request和response两类，用于请求/响应正文和服务间通信,命名约定分别Request与Response结尾
- 不应包含业务逻辑或 JPA 注释，如 '@Entity'
- 公开API必须添加Swagger注解文档

7.'config/'
- 包含“@Configuration”和“@Enable...”类
- 示例：'SwaggerConfig'、'SecurityConfig'、'KafkaConsumerConfig' 等。

8.'util/'
- 包含跨层共享的无状态实用程序/帮助程序类

9.'src/main/resources/' 中的必需文件
- 'application.yml'
- 'application-local.yml'
- 'static/' – 用于前端静态资产（如果需要）
- 'templates/' – 用于服务器端模板（例如，如果使用 Thymeleaf）
- 'messages.properties' – 用于 i18n/验证消息
 
## 命名约定
 
- 类名：使用PascalCase命名法
- 方法名：使用camelCase命名法
- 变量名：使用camelCase命名法
- 常量：使用UPPER_SNAKE_CASE命名法

## 清洁代码
 
- 编写干净、可读且可维护的代码。
- 保持功能小而集中。
- 保持变量和函数名称具有描述性。
- 保持评论和文档有意义。

## 重写、改进和重构
 
- 重构或修复代码时，请确保代码干净且易于理解，且不重复代码。
- 保持代码干净且易于理解。
- 保持代码 DRY（不要重复）。
- 保持代码干净且易于理解。
- 解决问题后，在文档字符串中提及该案例，以便将来的更改不会再次破坏它。
 
## 测试
 
- 总是先写测试。
- 始终运行测试以确保代码正常运行。
- 始终保持测试清洁且最新。