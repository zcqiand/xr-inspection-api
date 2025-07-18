package com.nanrong.inspection.domain.system;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "system_permission")
@DynamicUpdate
@Accessors(chain = true)
@FieldNameConstants
@NoArgsConstructor
@Getter
@Setter
@Comment("系统权限表")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("权限ID")
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    @Comment("权限编码")
    private String code;

    @Column(length = 200)
    @Nullable
    @Comment("权限描述")
    private String description;
}