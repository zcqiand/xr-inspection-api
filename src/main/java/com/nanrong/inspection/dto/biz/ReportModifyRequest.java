package com.nanrong.inspection.dto.biz;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import java.time.OffsetDateTime;
import java.time.LocalDateTime;

/**
 * 报告修改请求
 */
@Data
@FieldNameConstants
@Accessors(chain = true)
public class ReportModifyRequest {
    /**
     * 修改人ID
     */
    private Long modifierId;
    
    /**
     * 修改内容
     */
    private String content;
    
    /**
     * 修改摘要
     */
    private String summary;
    
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
    
    // 委托日期
    private OffsetDateTime entrustDate;
    
    // 样品状态
    private String sampleStatus;
    
    // 备注
    private String remarks;
    
    // 登记人
    private String registrant;
    
    // 登记时间
    private LocalDateTime registrationTime;
}