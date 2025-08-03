package com.nanrong.inspection.dto.biz;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import java.time.LocalDateTime;

@Data
@FieldNameConstants
@Accessors(chain = true)
public class ReportReviewRequest {
    /**
     * 报告ID
     */
    private Long reportId;
    
    /**
     * 审核人ID
     */
    private Long reviewerId;
    
    /**
     * 审核级别
     */
    private Integer reviewLevel;
    
    /**
     * 审核意见
     */
    private String comments;
    
    /**
     * 审核结果
     */
    private String result; // "APPROVED" or "REJECTED"
    
    /**
     * 批注位置
     */
    private String annotationPositions; // JSON格式的批注位置
    
    // 登记人
    private String registrant;
    
    // 登记时间
    private LocalDateTime registrationTime;
}