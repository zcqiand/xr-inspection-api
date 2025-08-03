package com.nanrong.inspection.dto.biz;

import java.time.OffsetDateTime;
import java.time.LocalDateTime;
import com.nanrong.inspection.shared.biz.ReportStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "委托详情响应")
public class GetEntrustDetailResponse {
    @Schema(description = "委托ID")
    private Long id;
    
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    
    @Schema(description = "委托日期")
    private OffsetDateTime entrustDate;
    
    @Schema(description = "委托单位", required = true)
    private String entrustUnit;
    
    @Schema(description = "联系人", required = true)
    private String contactPerson;
    
    @Schema(description = "联系电话", required = true)
    private String contactPhone;
    
    @Schema(description = "联系邮箱")
    private String contactEmail;
    
    @Schema(description = "检测项目", required = true)
    private String inspectionItems;
    
    @Schema(description = "样品状态")
    private String sampleStatus;
    
    @Schema(description = "备注")
    private String remarks;
    
    @Schema(description = "报告状态")
    private ReportStatus reportStatus;
    
    @Schema(description = "PDF路径")
    private String pdfPath;
    
    @Schema(description = "报告内容")
    private String content;
    
    @Schema(description = "模板ID")
    private Long templateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public OffsetDateTime getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(OffsetDateTime entrustDate) {
        this.entrustDate = entrustDate;
    }

    public String getEntrustUnit() {
        return entrustUnit;
    }

    public void setEntrustUnit(String entrustUnit) {
        this.entrustUnit = entrustUnit;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getInspectionItems() {
        return inspectionItems;
    }

    public void setInspectionItems(String inspectionItems) {
        this.inspectionItems = inspectionItems;
    }

    public String getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(String sampleStatus) {
        this.sampleStatus = sampleStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
}