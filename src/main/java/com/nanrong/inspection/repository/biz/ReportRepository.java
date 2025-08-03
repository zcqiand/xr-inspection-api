package com.nanrong.inspection.repository.biz;

import com.nanrong.inspection.domain.biz.Report;
import com.nanrong.inspection.shared.biz.ReportStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    // 按报告类型查找报告（委托登记类型为"ENTRUSTMENT"）
    List<Report> findByReportType(String reportType);
    
    // 按报告状态查找报告
    List<Report> findByReportStatus(ReportStatus reportStatus);
}