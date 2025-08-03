package com.nanrong.inspection.service.biz.impl;

import com.nanrong.inspection.domain.biz.*;
import com.nanrong.inspection.dto.biz.ReportModifyRequest;
import com.nanrong.inspection.dto.biz.ReportReviewRequest;
import com.nanrong.inspection.dto.biz.EntrustmentCreateRequest;
import com.nanrong.inspection.dto.biz.EntrustmentUpdateRequest;
import com.nanrong.inspection.dto.biz.GetEntrustDetailResponse;
import com.nanrong.inspection.dto.biz.ReportCompileDTO; // 添加导入
import com.nanrong.inspection.repository.biz.BTemplateRepository;
import com.nanrong.inspection.repository.biz.ReportRepository;
import com.nanrong.inspection.service.biz.EmailService;
import com.nanrong.inspection.service.biz.ReportService;
import com.nanrong.inspection.service.sys.PermissionService;
import com.nanrong.inspection.shared.biz.AssignmentType;
import com.nanrong.inspection.shared.biz.ReportStatus;
import com.nanrong.inspection.util.PdfGenerator;
import com.nanrong.inspection.dto.biz.TaskAssignmentRequest;
import com.nanrong.inspection.dto.biz.ListAssignResponse;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import com.nanrong.inspection.domain.sys.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
    
    private final ReportRepository reportRepository;
    private final BTemplateRepository templateRepository;
    private final PermissionService permissionService;
    private final EmailService emailService;
    private final PdfGenerator pdfGenerator;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository,
                             BTemplateRepository templateRepository,
                             PermissionService permissionService,
                             EmailService emailService,
                             PdfGenerator pdfGenerator) {
       this.reportRepository = reportRepository;
       this.templateRepository = templateRepository;
       this.permissionService = permissionService;
       this.emailService = emailService;
       this.pdfGenerator = pdfGenerator;
    }

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
        report.setReportStatus(ReportStatus.DRAFT);
        
        return reportRepository.save(report);
    }

    @Override
    @Transactional
    public Report generateReport(Long templateId) {
        BTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("模板不存在"));
        
        Report report = new Report();
        report.setTemplateId(templateId);
        report.setReportStatus(ReportStatus.DRAFT);
        report.setContent(template.getContent());
        
        return reportRepository.save(report);
    }

    @Override
    @Transactional
    public void submitReview(Long reportId, Long auditorId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("报告不存在"));
        
        // 检查权限
        if (!permissionService.hasPermission(auditorId, "REPORT_REVIEW")) {
            throw new AccessDeniedException("无报告审核权限");
        }
        
        // 检查状态
        if (report.getReportStatus() != ReportStatus.REPORT_COMPILED) {
            throw new IllegalStateException("只有已编制状态的报告才能提交审核");
        }
        
        // 设置审核信息
        report.setReportStatus(ReportStatus.REPORT_REVIEWED);
        report.setReviewerId(auditorId);
        report.setReviewTime(OffsetDateTime.now());
        
        reportRepository.save(report);
    }
    
    @Override
    @Transactional
    public void registerReportEntrustment(Long reportId, String registrantId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("报告不存在"));
        
        // 检查状态转换是否允许（从REPORT_ENTRUSTING到REPORT_ENTRUSTED）
        if (report.getReportStatus() != ReportStatus.DRAFT) {
            throw new IllegalStateException("只有委托中状态的报告才能进行委托提交");
        }
        
        report.setRegistrantId(registrantId);
        report.setRegistrationTime(LocalDateTime.now());
        report.setReportStatus(ReportStatus.REPORT_ENTRUSTED);
        
        reportRepository.save(report);
    }
    
    // 委托登记相关方法实现
    @Override
    public List<GetEntrustDetailResponse> listEntrust() {
        // 查询委托登记类型的报告
        List<Report> reports = reportRepository.findByReportType("ENTRUSTMENT");
        // 转换为DTO列表
        return reports.stream().map(report -> {
            GetEntrustDetailResponse dto = new GetEntrustDetailResponse();
            dto.setId(report.getId());
            dto.setCreateTime(report.getRegistrationTime());
            dto.setEntrustDate(report.getEntrustDate());
            dto.setEntrustUnit(report.getEntrustUnit());
            dto.setContactPerson(report.getContactPerson());
            dto.setContactPhone(report.getContactPhone());
            dto.setContactEmail(report.getContactEmail());
            dto.setInspectionItems(report.getInspectionItems());
            dto.setSampleStatus(report.getSampleStatus());
            dto.setRemarks(report.getRemarks());
            dto.setReportStatus(report.getReportStatus());
            dto.setPdfPath(report.getPdfPath());
            dto.setContent(report.getContent());
            dto.setTemplateId(report.getTemplateId());
            return dto;
        }).toList();
    }
    

    @Override
    public GetEntrustDetailResponse getEntrustDetail(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("委托登记不存在"));
        
        GetEntrustDetailResponse dto = new GetEntrustDetailResponse();
        dto.setId(report.getId());
        dto.setCreateTime(report.getRegistrationTime());
        dto.setEntrustDate(report.getEntrustDate());
        dto.setEntrustUnit(report.getEntrustUnit());
        dto.setContactPerson(report.getContactPerson());
        dto.setContactPhone(report.getContactPhone());
        dto.setContactEmail(report.getContactEmail());
        dto.setInspectionItems(report.getInspectionItems());
        dto.setSampleStatus(report.getSampleStatus());
        dto.setRemarks(report.getRemarks());
        dto.setReportStatus(report.getReportStatus());
        dto.setPdfPath(report.getPdfPath());
        dto.setContent(report.getContent());
        dto.setTemplateId(report.getTemplateId());
        return dto;
    }

    @Override
    @Transactional
    public void createEntrust(EntrustmentCreateRequest dto) {
        Report report = new Report();
        report.setReportType("ENTRUSTMENT");
        report.setEntrustUnit(dto.getEntrustUnit());
        report.setContactPerson(dto.getContactPerson());
        report.setContactPhone(dto.getContactPhone());
        report.setContactEmail(dto.getContactEmail());
        report.setInspectionItems(dto.getInspectionItems());
        report.setSampleStatus(dto.getSampleStatus());
        report.setRemarks(dto.getRemarks());
        report.setEntrustDate(dto.getEntrustDate());
        report.setTemplateId(dto.getTemplateId());
        report.setReportStatus(ReportStatus.REPORT_ENTRUSTED);
        // 设置其他字段
        reportRepository.save(report);
    }

    @Override
    @Transactional
    public void updateEntrust(Long id, EntrustmentUpdateRequest dto) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("委托登记不存在"));
        
        report.setEntrustUnit(dto.getEntrustUnit());
        report.setContactPerson(dto.getContactPerson());
        report.setContactPhone(dto.getContactPhone());
        report.setContactEmail(dto.getContactEmail());
        report.setInspectionItems(dto.getInspectionItems());
        report.setSampleStatus(dto.getSampleStatus());
        report.setRemarks(dto.getRemarks());
        report.setTemplateId(dto.getTemplateId());
        report.setReportStatus(ReportStatus.DRAFT);
        // 更新其他字段
        reportRepository.save(report);
    }

    @Override
    @Transactional
    public void deleteEntrust(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("委托登记不存在"));
        
        reportRepository.delete(report);
    }

    @Override
    @Transactional
    public void submitEntrust(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("委托登记不存在"));
        
        if (report.getReportStatus() != ReportStatus.DRAFT) {
            throw new IllegalStateException("只有委托中状态的报告才能提交");
        }
        
        report.setReportStatus(ReportStatus.REPORT_ENTRUSTED);
        reportRepository.save(report);
    }

    // 任务分配相关方法实现（直接实现）
    @Override
    @Transactional
    public List<ListAssignResponse> listAssign() {
        List<Report> reports = reportRepository.findByReportType("TASK_ASSIGNMENT");
        return reports.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TASK_ASSIGNMENT_UPDATE')")
    public void updateAssign(Long id, TaskAssignmentRequest request) {
        logger.info("Updating task assignment for report id: {}", id);
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("任务分配不存在"));
        
        report.setAssigneeId(request.getAssigneeId());
        report.setDeviceRequirements(request.getDeviceRequirements());
        report.setPlannedStartTime(request.getPlannedStartTime());
        report.setPlannedEndTime(request.getPlannedEndTime());
        report.setAssignmentType(AssignmentType.valueOf(request.getType()));
        report.setReassignReason(request.getReassignReason());
        
        reportRepository.save(report);
        logger.info("Task assignment updated for report id: {}", id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TASK_ASSIGNMENT_SUBMIT')")
    public void submitAssign(Long id) {
        logger.info("Submitting task assignment for report id: {}", id);
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("任务分配不存在"));
        
        if (report.getReportStatus() != ReportStatus.DRAFT) {
            throw new IllegalStateException("只有草稿状态的任务才能提交");
        }
        
        report.setReportStatus(ReportStatus.REPORT_ASSIGNED);
        reportRepository.save(report);
        logger.info("Task assignment submitted for report id: {}, new status: {}", id, report.getReportStatus());
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('TASK_ASSIGNMENT_WITHDRAW')")
    public void withdrawAssign(Long id) {
        logger.info("Withdrawing task assignment for report id: {}", id);
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("任务分配不存在"));
        
        if (report.getReportStatus() != ReportStatus.REPORT_ASSIGNED) {
            throw new IllegalStateException("只有已分配状态的任务才能撤回");
        }
        
        report.setReportStatus(ReportStatus.REPORT_ENTRUSTED);
        reportRepository.save(report);
        logger.info("Task assignment withdrawn for report id: {}, new status: {}", id, report.getReportStatus());
    }

    private ListAssignResponse convertToResponse(Report report) {
        ListAssignResponse dto = new ListAssignResponse();
        dto.setId(report.getId());
        dto.setReportId(report.getId());
        dto.setAssigneeId(report.getAssigneeId());
        // TODO: 需要从用户服务获取姓名，待后续实现
        dto.setAssigneeName("");
        dto.setDeviceRequirements(report.getDeviceRequirements());
        dto.setPlannedStartTime(report.getPlannedStartTime());
        dto.setPlannedEndTime(report.getPlannedEndTime());
        dto.setActualStartTime(report.getActualStartTime());
        dto.setActualEndTime(report.getActualEndTime());
        dto.setStatus(report.getReportStatus().name());
        dto.setType(report.getAssignmentType().name());
        dto.setReassignReason(report.getReassignReason());
        return dto;
    }
    
    // 报告编制相关方法实现
    @Override
    public List<Report> listCompile() {
        return reportRepository.findByReportStatus(ReportStatus.REPORT_ENTERED);
    }
    
    @Override
    @Transactional
    public void submitCompile(ReportCompileDTO dto) {
        Report report = reportRepository.findById(dto.getReportId())
                .orElseThrow(() -> new RuntimeException("报告不存在"));
        
        if (report.getReportStatus() != ReportStatus.REPORT_ENTERED) {
            throw new IllegalStateException("只有已录入状态的报告可以编制");
        }
        
        report.setCompiledContent(dto.getCompiledContent());
        report.setRemarks(dto.getRemarks());
        report.setReportStatus(ReportStatus.REPORT_COMPILED);
        reportRepository.save(report);
    }

    @Override
    @Transactional
    public void withdrawCompile(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("报告不存在"));
        
        if (report.getReportStatus() != ReportStatus.REPORT_COMPILED) {
            throw new IllegalStateException("只有已编制状态的报告可以撤回");
        }
        
        report.setReportStatus(ReportStatus.REPORT_ENTERED);
        reportRepository.save(report);
    }
    @Override
    public List<Report> listReview(ReportStatus status) {
        if (status == null) {
            status = ReportStatus.REPORT_COMPILED;
        }
        return reportRepository.findByReportStatus(status);
    }
    
    @Override
    public List<Report> listApprove() {
        return reportRepository.findByReportStatus(ReportStatus.REPORT_REVIEWED);
    }
    
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REPORT_APPROVE')")
    public void submitApprove(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("报告不存在"));
        
        // 检查状态
        if (report.getReportStatus() != ReportStatus.REPORT_REVIEWED) {
            throw new IllegalStateException("只有已审核状态的报告才能批准");
        }
        
        // 更新报告状态
        report.setReportStatus(ReportStatus.REPORT_APPROVED);
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        report.setApproverId(userId);
        report.setApprovalTime(OffsetDateTime.now());
        reportRepository.save(report);
    }
    
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REPORT_APPROVE')")
    public void withdrawApprove(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("报告不存在"));
        
        // 检查状态
        if (report.getReportStatus() != ReportStatus.REPORT_APPROVED) {
            throw new IllegalStateException("只有已批准状态的报告才能撤回");
        }
        
        // 更新报告状态
        report.setReportStatus(ReportStatus.REPORT_REVIEWED);
        report.setApproverId(null);
        report.setApprovalTime(null);
        reportRepository.save(report);
    }
    
    @Override
    @Transactional
    public void withdrawReview(Long reportId, Long auditorId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("报告不存在"));
        
        // 检查权限
        if (!permissionService.hasPermission(auditorId, "REPORT_REVIEW")) {
            throw new AccessDeniedException("无报告审核权限");
        }
        
        // 检查状态
        if (report.getReportStatus() != ReportStatus.REPORT_REVIEWED) {
            throw new IllegalStateException("只有已审核状态的报告才能撤回");
        }
        
        // 重置审核信息
        report.setReportStatus(ReportStatus.REPORT_COMPILED);
        report.setReviewerId(null);
        report.setReviewTime(null);
        
        reportRepository.save(report);
    }

    private Report getReportById(Long reportId) {
        return reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("报告不存在"));
    }

    @Override
    @Transactional
    public List<Report> listIssue() {
        return reportRepository.findByReportStatus(ReportStatus.REPORT_ISSUED);
    }

    @Override
    @Transactional
    public void submitIssue(Long reportId) {
        Report report = getReportById(reportId);
        logger.trace("Issuing report with id: {}, current status: {}", reportId, report.getReportStatus());
        report.issueReport();
        logger.info("Report with id: {} issued successfully, new status: {}", reportId, report.getReportStatus());
        reportRepository.save(report);
    }

    @Override
    @Transactional
    public void withdrawIssue(Long reportId) {
        Report report = getReportById(reportId);
        logger.trace("Withdrawing issued report with id: {}, current status: {}", reportId, report.getReportStatus());
        report.withdrawIssue();
        logger.info("Report with id: {} withdrawal successful, new status: {}", reportId, report.getReportStatus());
        reportRepository.save(report);
    }
    @Override
    public List<Report> listArchive() {
        return reportRepository.findByReportStatus(ReportStatus.REPORT_ARCHIVED);
    }

    @Override
    @Transactional
    public void submitArchive(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("报告不存在"));
        if (report.getReportStatus() == ReportStatus.REPORT_ARCHIVED) {
            throw new IllegalStateException("报告已归档");
        }
        report.setReportStatus(ReportStatus.REPORT_ARCHIVED);
        reportRepository.save(report);
    }

    @Override
    @Transactional
    public void withdrawArchive(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("报告不存在"));
        if (report.getReportStatus() != ReportStatus.REPORT_ARCHIVED) {
            throw new IllegalStateException("报告未归档");
        }
        report.setReportStatus(ReportStatus.REPORT_ISSUED);
        reportRepository.save(report);
    }

    @Override
    public List<Report> listEntry() {
        return reportRepository.findByReportStatus(ReportStatus.REPORT_ASSIGNED);
    }

    @Override
    @Transactional
    public Report updateEntry(Long id, Report entryData) {
        Report existingReport = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("报告不存在"));
        
        if (existingReport.getReportStatus() == ReportStatus.REPORT_ENTERED) {
            throw new IllegalStateException("已提交的报告禁止修改");
        }
        
        existingReport.setContent(entryData.getContent());
        return reportRepository.save(existingReport);
    }

    @Override
    @Transactional
    public void submitEntry(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("报告不存在"));
        report.setReportStatus(ReportStatus.REPORT_ENTERED);
        reportRepository.save(report);
    }

    @Override
    @Transactional
    public void withdrawEntry(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("报告不存在"));
        report.setReportStatus(ReportStatus.REPORT_ASSIGNED);
        reportRepository.save(report);
    }
}