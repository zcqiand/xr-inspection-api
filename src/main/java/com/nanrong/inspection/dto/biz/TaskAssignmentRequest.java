package com.nanrong.inspection.dto.biz;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import java.time.OffsetDateTime;

@Data
@FieldNameConstants
@Accessors(chain = true)
public class TaskAssignmentRequest {
    /**
     * 委托ID
     */
    @NotNull(message = "委托ID不能为空")
    private Long entrustId;
    
    /**
     * 分配人员ID
     */
    @NotNull(message = "分配人员ID不能为空")
    private Long assigneeId;
    
    /**
     * 设备要求
     */
    @NotBlank(message = "设备要求不能为空")
    private String deviceRequirements;
    
    /**
     * 计划开始时间
     */
    @NotNull(message = "计划开始时间不能为空")
    private OffsetDateTime plannedStartTime;
    
    /**
     * 计划结束时间
     */
    @NotNull(message = "计划结束时间不能为空")
    private OffsetDateTime plannedEndTime;
    
    /**
     * 分配类型
     */
    @NotBlank(message = "分配类型不能为空")
    private String type; // "AUTO" or "MANUAL"
    
    /**
     * 重新分配原因
     */
    private String reassignReason; // Only for reassignment
}