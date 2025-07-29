package com.nanrong.inspection.domain.biz;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class WorkloadAnalysisVO {
    private Long userId;
    private String userName;
    private Integer assignedTasks;
    private Integer completedTasks;
    private BigDecimal completionRate;
    private String department;

    public WorkloadAnalysisVO(Long userId, String userName, Long assignedTasks, Long completedTasks, Double completionRate, String department) {
        this.userId = userId;
        this.userName = userName;
        this.assignedTasks = assignedTasks != null ? assignedTasks.intValue() : 0;
        this.completedTasks = completedTasks != null ? completedTasks.intValue() : 0;
        this.completionRate = completionRate != null ? BigDecimal.valueOf(completionRate) : BigDecimal.ZERO;
        this.department = department;
    }
}