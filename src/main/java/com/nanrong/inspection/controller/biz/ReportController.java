package com.nanrong.inspection.controller.biz;

import com.nanrong.inspection.domain.biz.Report;
import com.nanrong.inspection.repository.biz.ReportRepository;
import com.nanrong.inspection.service.biz.ReportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/biz/report")
@Tag(name = "报告管理", description = "报告管理接口")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("/{reportId}/download")
    @Operation(summary = "下载报告", description = "根据报告ID下载PDF格式的报告文件")
    public ResponseEntity<Resource> downloadReport(
            @Parameter(description = "报告ID") @PathVariable Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("报告不存在"));

        try {
            Path filePath = Paths.get(report.getPdfPath());
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .header(HttpHeaders.CONTENT_DISPOSITION, 
                                "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("无法读取报告文件");
            }
        } catch (Exception e) {
            throw new RuntimeException("下载报告失败: " + e.getMessage());
        }
    }
}