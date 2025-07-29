package com.nanrong.inspection.repository.biz;

import com.nanrong.inspection.domain.biz.Report;
import com.nanrong.inspection.shared.biz.ReportStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    // 按任务分配ID查找报告
    List<Report> findByTaskAssignmentId(Long taskAssignmentId);
}