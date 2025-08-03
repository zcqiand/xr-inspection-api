package com.nanrong.inspection.dto.biz;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import java.time.OffsetDateTime;

@Data
@FieldNameConstants
@Accessors(chain = true)
public class ListAssignResponse {
    /**
     * 任务分配ID
     */
    private Long id;
    
    /**
     * 检测报告ID
     */
    private Long reportId;
    
    /**
     * 分配人员ID
     */
    private Long assigneeId;
    
    /**
     * 分配人员姓名
     */
    private String assigneeName;
    
    /**
     * 设备要求
     */
    private String deviceRequirements;
    
    /**
     * 计划开始时间
     */
    private OffsetDateTime plannedStartTime;
    
    /**
     * 计划结束时间
     */
    private OffsetDateTime plannedEndTime;
    
    /**
     * 实际开始时间
     */
    private OffsetDateTime actualStartTime;
    
    /**
     * 实际结束时间
     */
    private OffsetDateTime actualEndTime;
    
    /**
     * 状态
     */
    private String status;
    
    /**
     * 分配类型
     */
    private String type;
    
    /**
     * 重新分配原因
     */
    private String reassignReason;
}