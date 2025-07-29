package com.nanrong.inspection.domain.biz;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.Accessors;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.*;

/**
 * 检测模板实体
 */
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
@DynamicUpdate
@Accessors(chain = true)
@Entity
@Table(name = "biz_b_template")
@Comment("检测模板表")
public class BTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // 模板名称
    private String name;
    
    // 模板内容（HTML或Velocity模板）
    @Lob
    private String content;
    
    // 模板状态（启用/禁用）
    private String status = "ENABLED";
    
    // 模板版本
    private Integer version = 1;
}