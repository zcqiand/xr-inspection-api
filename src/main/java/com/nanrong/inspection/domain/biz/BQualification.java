package com.nanrong.inspection.domain.biz;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 基础资质信息实体
 */
@Entity
@Table(
    name = "biz_b_qualification"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@DynamicUpdate
@Accessors(chain = true)
@Comment("基础资质信息表")
public class BQualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("资质ID")
    private Long id;

    @Column(nullable = false)
    @Comment("资质名称")
    private String name;
}