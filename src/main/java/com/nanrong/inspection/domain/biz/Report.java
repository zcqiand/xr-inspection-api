package com.nanrong.inspection.domain.biz;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.Accessors;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import com.nanrong.inspection.shared.biz.ReportStatus;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 检测报告实体
 */
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
@DynamicUpdate
@Accessors(chain = true)
@Entity
@Table(name = "biz_report")
@Comment("检测报告表")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // 创建时间
    private OffsetDateTime createTime = OffsetDateTime.now();
    
    // 委托日期
    private OffsetDateTime entrustDate;
    
    // 委托单位
    private String entrustUnit;
    
    // 联系人
    private String contactPerson;
    
    // 联系电话
    private String contactPhone;
    
    // 联系邮箱
    private String contactEmail;
    
    // 检测项目
    private String inspectionItems;
    
    // 样品状态
    private String sampleStatus;
    
    // 备注
    private String remarks;
    
    // 关联的任务分配ID
    private Long taskAssignmentId;
    
    // 批准人ID
    private Long approverId;

    // 批准时间
    private OffsetDateTime approvalTime;

    // 发放时间
    private OffsetDateTime distributionTime;
    
    // 归档时间
    private OffsetDateTime archiveTime;
    
    // 报告状态
    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus = ReportStatus.REPORT_ENTRUSTING;

    // PDF文件路径（带水印）
    private String pdfPath;
    
    // 报告内容（HTML或JSON格式）
    @Lob
    private String content;
    
    // 使用的模板ID
    private Long templateId;
    
    // 修改历史
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "report_id")
    private List<ReportModification> modifications = new ArrayList<>();
}