package com.nanrong.inspection.domain.biz;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.Accessors;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

/**
 * 报告修改历史记录
 */
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
@DynamicUpdate
@Accessors(chain = true)
@Entity
@Table(name = "biz_report_modification")
@Comment("检测报告修改历史表")
public class ReportModification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // 关联的报告ID
    @Column(name = "report_id")
    private Long reportId;
    
    // 修改人ID
    private Long modifierId;
    
    // 修改时间
    private OffsetDateTime modifiedTime = OffsetDateTime.now();
    
    // 修改内容摘要
    private String summary;
    
    // 修改详情（JSON格式）
    @Lob
    private String details;
}