package com.nanrong.inspection.service.biz;

import com.nanrong.inspection.domain.biz.Report;
import com.nanrong.inspection.dto.biz.ReportModifyRequest;
import com.nanrong.inspection.dto.biz.ReportReviewRequest;

import java.util.List;

public interface ReportService {
    /**
     * 自动生成报告
     * @param taskAssignmentId 任务分配ID
     * @param templateId 模板ID
     * @return 生成的报告
     */
    Report generateReport(Long taskAssignmentId, Long templateId);
    
    /**
     * 修改报告内容
     * @param reportId 报告ID
     * @param request 修改请求
     * @return 修改后的报告
     */
    Report modifyReport(Long reportId, ReportModifyRequest request);
    
    /**
     * 提交报告审核
     * @param reportId 报告ID
     * @return 提交审核后的报告
     */
    void approveReport(Long reportId, Long userId);
}