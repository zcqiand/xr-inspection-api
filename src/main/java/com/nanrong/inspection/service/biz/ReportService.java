package com.nanrong.inspection.service.biz;

import com.nanrong.inspection.domain.biz.Report;
import com.nanrong.inspection.dto.biz.ReportModifyRequest;
import com.nanrong.inspection.dto.biz.ReportReviewRequest;
import com.nanrong.inspection.dto.biz.EntrustmentCreateRequest;
import com.nanrong.inspection.dto.biz.EntrustmentUpdateRequest;
import com.nanrong.inspection.dto.biz.GetEntrustDetailResponse;
import com.nanrong.inspection.dto.biz.TaskAssignmentRequest;
import com.nanrong.inspection.dto.biz.ListAssignResponse;
import com.nanrong.inspection.dto.biz.ReportCompileDTO; // 添加导入
import com.nanrong.inspection.shared.biz.ReportStatus;
import java.util.List;

public interface ReportService {
    /**
     * 自动生成报告
     * @param templateId 模板ID
     * @return 生成的报告
     */
    Report generateReport(Long templateId);
    
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
     * @param userId 审核人ID
     */
    void submitReview(Long reportId, Long userId);
    
    /**
     * 获取待审核报告列表
     * @param status 报告状态（默认为REPORT_COMPILED）
     * @return 报告列表
     */
    List<Report> listReview(ReportStatus status);
    
    /**
     * 撤回报告审核
     * @param reportId 报告ID
     * @param auditorId 审核人ID
     */
    void withdrawReview(Long reportId, Long auditorId);
    
    /**
     * 委托登记
     * @param reportId 报告ID
     * @param registrantId 登记人ID
     */
    void registerReportEntrustment(Long reportId, String registrantId);
    
    // 委托登记相关方法
    List<GetEntrustDetailResponse> listEntrust();
    GetEntrustDetailResponse getEntrustDetail(Long id);
    void createEntrust(EntrustmentCreateRequest dto);
    void updateEntrust(Long id, EntrustmentUpdateRequest dto);
    void deleteEntrust(Long id);
    void submitEntrust(Long id);
    
    // 任务分配相关方法
    List<ListAssignResponse> listAssign();
    void updateAssign(Long id, TaskAssignmentRequest request);
    void submitAssign(Long id);
    void withdrawAssign(Long id);
    
    // 报告编制相关方法
    List<Report> listCompile();
    
    /**
     * 提交报告编制
     * @param dto 报告编制数据传输对象
     */
    void submitCompile(ReportCompileDTO dto);

    /**
     * 撤回报告编制
     * @param reportId 报告ID
     */
    void withdrawCompile(Long reportId);
    
    // 报告批准相关方法
    List<Report> listApprove();
    void submitApprove(Long reportId);
    void withdrawApprove(Long reportId);

    // 报告发放相关方法
    List<Report> listIssue();
    void submitIssue(Long reportId);
    void withdrawIssue(Long reportId);
    /**
     * 获取已归档报告列表
     * @return 归档报告列表
     */
    List<Report> listArchive();
    
    /**
     * 归档报告
     * @param reportId 报告ID
     */
    void submitArchive(Long reportId);
    
    /**
     * 撤回归档
     * @param reportId 报告ID
     */
    void withdrawArchive(Long reportId);

    // 数据录入相关方法
    List<Report> listEntry();
    Report updateEntry(Long id, Report entryData);
    void submitEntry(Long id);
    void withdrawEntry(Long id);
}