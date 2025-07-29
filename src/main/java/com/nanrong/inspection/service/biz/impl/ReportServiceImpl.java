package com.nanrong.inspection.service.biz.impl;

import com.nanrong.inspection.domain.biz.*;
import com.nanrong.inspection.dto.biz.ReportModifyRequest;
import com.nanrong.inspection.dto.biz.ReportReviewRequest;
import com.nanrong.inspection.repository.biz.BTemplateRepository;
import com.nanrong.inspection.repository.biz.ReportRepository;
import com.nanrong.inspection.service.biz.EmailService;
import com.nanrong.inspection.service.biz.ReportService;
import com.nanrong.inspection.service.sys.PermissionService;
import com.nanrong.inspection.shared.biz.ReportStatus;
import com.nanrong.inspection.util.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;
    
    @Autowired
    private BTemplateRepository templateRepository;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PdfGenerator pdfGenerator;

    @Override
    @Transactional
    public Report modifyReport(Long reportId, ReportModifyRequest request) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("报告不存在"));
        
        ReportModification modification = new ReportModification();
        modification.setReportId(reportId);
        modification.setModifierId(request.getModifierId());
        modification.setSummary(request.getSummary());
        modification.setDetails(request.getContent());
        
        report.getModifications().add(modification);
        report.setContent(request.getContent());
        
        return reportRepository.save(report);
    }

    @Override
    @Transactional
    public Report generateReport(Long taskAssignmentId, Long templateId) {
        BTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("模板不存在"));
        
        Report report = new Report();
        report.setTaskAssignmentId(taskAssignmentId);
        report.setTemplateId(templateId);
        report.setReportStatus(ReportStatus.REPORT_COMPILED);
        report.setContent(template.getContent());
        
        return reportRepository.save(report);
    }

    /*
     * 批准报告
     */
    @Override
    @Transactional
    public void approveReport(Long reportId, Long userId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("报告不存在"));
        
        // 检查权限
        if (!permissionService.hasPermission(userId, "REPORT_APPROVED")) {
            throw new AccessDeniedException("无报告批准权限");
        }
        
        // 检查状态
        if (report.getReportStatus() != ReportStatus.REPORT_REVIEWED) {
            throw new IllegalStateException("报告必须批准状态后才能批准提交");
        }
        
        // 设置批准信息
        report.setReportStatus(ReportStatus.REPORT_APPROVED);
        report.setApproverId(userId);
        report.setApprovalTime(OffsetDateTime.now());
        
        // 生成带水印的PDF
        String pdfPath = pdfGenerator.generateReportPdf(report);
        report.setPdfPath(pdfPath);
        
        reportRepository.save(report);
    }
    


}