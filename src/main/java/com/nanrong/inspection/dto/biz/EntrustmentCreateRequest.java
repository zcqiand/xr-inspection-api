package com.nanrong.inspection.dto.biz;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;

@Schema(description = "创建委托请求")
public class EntrustmentCreateRequest {
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
    
    @Schema(description = "委托日期", required = true)
    private OffsetDateTime entrustDate;
    
    @Schema(description = "模板ID")
    private Long templateId;

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

    public OffsetDateTime getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(OffsetDateTime entrustDate) {
        this.entrustDate = entrustDate;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
}