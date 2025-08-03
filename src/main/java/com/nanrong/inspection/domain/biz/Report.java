package com.nanrong.inspection.domain.biz;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.Accessors;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import com.nanrong.inspection.shared.biz.AssignmentType;
import com.nanrong.inspection.shared.biz.ReportStatus;
import com.nanrong.inspection.domain.sys.User;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    
    // 报告类型
    private String reportType;
    
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
    
    // 检测设备
    private String inspectionDevices;
    
    // 样品状态
    private String sampleStatus;
    
    // 备注
    private String remarks;
    
    // 编制内容
    private String compiledContent;
    
    // 登记人ID
    private String registrantId;
    
    // 登记时间
    private LocalDateTime registrationTime;
    
    // 分配人员ID
    private Long assigneeId;
    
    // 设备要求
    private String deviceRequirements;
    
    // 分配类型
    @Enumerated(EnumType.STRING)
    private AssignmentType assignmentType;
    
    // 重新分配原因
    private String reassignReason;
    
    // 要求检测开始时间
    private OffsetDateTime plannedStartTime;

    // 要求检测结束时间
    private OffsetDateTime plannedEndTime;

    // 实际检测开始时间
    private OffsetDateTime actualStartTime;

    // 实际检测结束时间
    private OffsetDateTime actualEndTime;
    
    // 分配时间
    private LocalDateTime assignmentTime;
    
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
    private ReportStatus reportStatus = ReportStatus.DRAFT;
    
    // 审核人ID
    private Long reviewerId;
    
    // 审核时间
    private OffsetDateTime reviewTime;

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
    /**
     * 发布报告（状态从REPORT_APPROVED改为REPORT_DISTRIBUTED）
     */
    public void issueReport() {
        if (reportStatus != ReportStatus.REPORT_APPROVED) {
            throw new IllegalStateException("报告状态必须为REPORT_APPROVED才能发布");
        }
        this.reportStatus = ReportStatus.REPORT_ISSUED;
        this.distributionTime = OffsetDateTime.now();
    }

    /**
     * 撤回发布（状态从REPORT_DISTRIBUTED改为REPORT_APPROVED）
     */
    public void withdrawIssue() {
        if (reportStatus != ReportStatus.REPORT_ISSUED) {
            throw new IllegalStateException("报告状态必须为REPORT_DISTRIBUTED才能撤回");
        }
        this.reportStatus = ReportStatus.REPORT_APPROVED;
        this.distributionTime = null;
    }
}