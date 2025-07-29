package com.nanrong.inspection.shared.biz;

public enum ReportStatus {
    /**
     * 报告委托中
     */
    REPORT_ENTRUSTING,
    
    /**
     * 报告已委托
     */
    REPORT_ENTRUSTED,
    
    /**
     * 报告已分配
     */
    REPORT_ASSIGNED,
    
    /**
     * 报告已录入
     */
    REPORT_ENTERED,
    
    /**
     * 报告已编制
     */
    REPORT_COMPILED,
    
    /**
     * 报告已审核
     */
    REPORT_REVIEWED,
    
    /**
     * 报告已批准
     */
    REPORT_APPROVED,
    
    /**
     * 报告已发放
     */
    REPORT_DISTRIBUTED,
    
    /**
     * 报告已归档
     */
    REPORT_ARCHIVED
}