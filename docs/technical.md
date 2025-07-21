# 技术规范
## JPA“@Entity”类
- 添加`@Entity`注解
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