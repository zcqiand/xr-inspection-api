package com.nanrong.inspection.controller.biz;

import com.nanrong.inspection.domain.biz.Report;
import com.nanrong.inspection.repository.biz.ReportRepository;
import com.nanrong.inspection.service.biz.ReportService;
import com.nanrong.inspection.config.CurrentUser;
import com.nanrong.inspection.domain.sys.User;
import com.nanrong.inspection.dto.biz.EntrustmentCreateRequest;
import com.nanrong.inspection.dto.biz.EntrustmentUpdateRequest;
import com.nanrong.inspection.dto.biz.GetEntrustDetailResponse;
import com.nanrong.inspection.dto.biz.ListAssignResponse;
import com.nanrong.inspection.dto.biz.TaskAssignmentRequest;
import com.nanrong.inspection.dto.biz.ReportCompileDTO; // 添加导入
import com.nanrong.inspection.shared.biz.ReportStatus;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "报告管理", description = "报告管理接口")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
    // 委托登记相关端点
    @GetMapping("/entrust")
    @Operation(summary = "获取委托登记列表")
    public ResponseEntity<List<GetEntrustDetailResponse>> listEntrust() {
        List<GetEntrustDetailResponse> list = reportService.listEntrust();
        return ResponseEntity.ok(list);
    }
    
    @GetMapping("/entrust/{id}")
    @Operation(summary = "获取委托登记详情", description = "根据ID获取委托登记详情")
    public ResponseEntity<GetEntrustDetailResponse> getEntrustDetail(
            @Parameter(description = "委托登记ID") @PathVariable Long id) {
        GetEntrustDetailResponse detail = reportService.getEntrustDetail(id);
        return ResponseEntity.ok(detail);
    }
    
    @PostMapping("/entrust")
    @Operation(summary = "创建委托登记", description = "创建新的委托登记")
    public ResponseEntity<Void> createEntrust(
            @RequestBody EntrustmentCreateRequest dto) {
        reportService.createEntrust(dto);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/entrust/{id}")
    @Operation(summary = "更新委托登记", description = "更新指定的委托登记")
    public ResponseEntity<Void> updateEntrust(
            @Parameter(description = "委托登记ID") @PathVariable Long id,
            @RequestBody EntrustmentUpdateRequest dto) {
        reportService.updateEntrust(id, dto);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/entrust/{id}")
    @Operation(summary = "删除委托登记", description = "删除指定的委托登记")
    public ResponseEntity<Void> deleteEntrust(
            @Parameter(description = "委托登记ID") @PathVariable Long id) {
        reportService.deleteEntrust(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/entrust/{id}/submit")
    @Operation(summary = "提交待委托报告", description = "提交委托登记，状态变更为报告已委托")
    public ResponseEntity<Void> submitEntrust(
            @Parameter(description = "委托登记ID") @PathVariable Long id) {
        reportService.submitEntrust(id);
        return ResponseEntity.ok().build();
    }

    // 任务分配相关端点
    @GetMapping("/assign")
    @Operation(summary = "获取任务分配列表")
    public ResponseEntity<List<ListAssignResponse>> listAssign() {
        List<ListAssignResponse> list = reportService.listAssign();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/assign/{id}")
    @Operation(summary = "修改任务分配", description = "修改指定的任务分配")
    public ResponseEntity<Void> updateAssign(
            @Parameter(description = "任务分配ID") @PathVariable Long id,
            @RequestBody TaskAssignmentRequest request) {
        reportService.updateAssign(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/assign/{id}/submit")
    @Operation(summary = "提交待分配报告", description = "提交任务分配，状态变更为报告已分配")
    public ResponseEntity<Void> submitAssign(
            @Parameter(description = "任务分配ID") @PathVariable Long id) {
        reportService.submitAssign(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/assign/{id}/withdraw")
    @Operation(summary = "撤回已分配报告", description = "撤回任务分配，状态变更为报告已委托")
    public ResponseEntity<Void> withdrawAssign(
            @Parameter(description = "任务分配ID") @PathVariable Long id) {
        reportService.withdrawAssign(id);
        return ResponseEntity.ok().build();
    }

    // 数据录入相关端点
    @GetMapping("/entry")
    @Operation(summary = "获取数据录入列表")
    public ResponseEntity<List<Report>> listEntry() {
        List<Report> reports = reportService.listEntry();
        return ResponseEntity.ok(reports);
    }

    @PutMapping("/entry/{id}")
    @Operation(summary = "数据录入修改", description = "修改数据录入内容")
    public ResponseEntity<Void> updateEntry(
            @Parameter(description = "报告ID") @PathVariable Long id,
            @RequestBody Report report) {
        reportService.updateEntry(id, report);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/entry/{id}/submit")
    @Operation(summary = "提交待录入报告", description = "提交数据录入，状态改为报告已录入")
    public ResponseEntity<Void> submitEntry(
            @Parameter(description = "报告ID") @PathVariable Long id) {
        reportService.submitEntry(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/entry/{id}/withdraw")
    @Operation(summary = "撤回已录入报告", description = "撤回数据录入，状态改为报告已分配")
    public ResponseEntity<Void> withdrawEntry(
            @Parameter(description = "报告ID") @PathVariable Long id) {
        reportService.withdrawEntry(id);
        return ResponseEntity.ok().build();
    }
    
    // 报告编制相关端点
    @GetMapping("/compile")
    @Operation(summary = "获取报告编制列表")
    public ResponseEntity<List<Report>> listCompile() {
        List<Report> reports = reportService.listCompile();
        return ResponseEntity.ok(reports);
    }

    @PutMapping("/compile/{id}/submit")
    @Operation(summary = "提交待编制报告", description = "将报告状态改为'已编制'")
    public ResponseEntity<Void> submitCompile(
            @RequestBody ReportCompileDTO dto) {
        reportService.submitCompile(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/compile/{id}/withdraw")
    @Operation(summary = "撤回已编制报告", description = "将报告状态从'已编制'撤回为'已录入'")
    public ResponseEntity<Void> withdrawCompile(
            @Parameter(description = "报告ID") @PathVariable Long id) {
        reportService.withdrawCompile(id);
        return ResponseEntity.ok().build();
    }
    // 报告审核相关端点
    @GetMapping("/review")
    @Operation(summary = "获取报告审核列表")
    @PreAuthorize("hasRole('AUDITOR')")
    public ResponseEntity<List<Report>> listReview(
        @Parameter(description = "报告状态（可选）") @RequestParam(required = false) ReportStatus status) {
        List<Report> reports = reportService.listReview(status);
        return ResponseEntity.ok(reports);
    }
    
    @PutMapping("/review/{id}/submit")
    @Operation(summary = "提交待审核报告", description = "将报告状态从'已编制'改为'已审核'")
    @PreAuthorize("hasRole('AUDITOR')")
    public ResponseEntity<Void> submitReview(
        @Parameter(description = "报告ID") @PathVariable Long id,
        @CurrentUser User auditor) {
        reportService.submitReview(id, auditor.getId());
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/review/{id}/withdraw")
    @Operation(summary = "撤回已审核报告", description = "将报告状态从'已审核'改为'已编制'")
    @PreAuthorize("hasRole('AUDITOR')")
    public ResponseEntity<Void> withdrawReview(
        @Parameter(description = "报告ID") @PathVariable Long id,
        @CurrentUser User auditor) {
        reportService.withdrawReview(id, auditor.getId());
        return ResponseEntity.ok().build();
    }
    
    // 报告批准相关端点
    @GetMapping("/approve")
    @Operation(summary = "获取报告批准列表")
    @PreAuthorize("hasAuthority('REPORT_APPROVE')")
    public ResponseEntity<List<Report>> listApprove() {
        List<Report> reports = reportService.listApprove();
        return ResponseEntity.ok(reports);
    }
    
    @PutMapping("/approve/{id}/submit")
    @Operation(summary = "提交待批准报告", description = "将报告状态从'已审核'改为'已批准'")
    @PreAuthorize("hasAuthority('REPORT_APPROVE')")
    public ResponseEntity<Void> submitApprove(
        @Parameter(description = "报告ID") @PathVariable Long id) {
        reportService.submitApprove(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/approve/{id}/withdraw")
    @Operation(summary = "撤回已批准报告", description = "将报告状态从'已批准'改为'已审核'")
    @PreAuthorize("hasAuthority('REPORT_APPROVE')")
    public ResponseEntity<Void> withdrawApprove(
        @Parameter(description = "报告ID") @PathVariable Long id) {
        reportService.withdrawApprove(id);
        return ResponseEntity.ok().build();
    }
    // 报告发放相关端点
    @GetMapping("/issue")
    @Operation(summary = "获取报告发放列表")
    public ResponseEntity<List<Report>> listIssue() {
        return ResponseEntity.ok(reportService.listIssue());
    }

    @PutMapping("/issue/{id}/submit")
    @Operation(summary = "提交待发放报告")
    public ResponseEntity<Void> submitIssue(@PathVariable Long id) {
        reportService.submitIssue(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/issue/{id}/withdraw")
    @Operation(summary = "撤回已发放报告")
    public ResponseEntity<Void> withdrawIssue(@PathVariable Long id) {
        reportService.withdrawIssue(id);
        return ResponseEntity.ok().build();
    }
    // 报告归档相关端点
    @GetMapping("/archive")
    @Operation(summary = "获取报告归档列表")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Report>> listArchive() {
        List<Report> archivedReports = reportService.listArchive();
        return ResponseEntity.ok(archivedReports);
    }
    
    @PutMapping("/archive/{id}/submit")
    @Operation(summary = "提交待归档报告", description = "将报告状态改为'已归档'")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> submitArchive(
        @Parameter(description = "报告ID") @PathVariable Long id) {
        reportService.submitArchive(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/archive/{id}/withdraw")
    @Operation(summary = "撤回已归档报告", description = "将报告状态从'已归档'改回'已发放'")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> withdrawArchive(
        @Parameter(description = "报告ID") @PathVariable Long id) {
        reportService.withdrawArchive(id);
        return ResponseEntity.ok().build();
    }
}