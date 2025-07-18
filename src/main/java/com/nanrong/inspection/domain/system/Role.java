package com.nanrong.inspection.domain.system;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "system_role")
@DynamicUpdate
@Accessors(chain = true)
@FieldNameConstants
@NoArgsConstructor
@Getter
@Setter
@Comment("系统角色表")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("角色ID")
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    @Comment("角色代码")
    private String name;

    @Column(length = 200)
    @Nullable
    @Comment("角色描述")
    private String description;

    @ManyToMany
    @JoinTable(name = "system_role_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    @Comment("关联权限")
    private List<Permission> permissions = new ArrayList<>();
}