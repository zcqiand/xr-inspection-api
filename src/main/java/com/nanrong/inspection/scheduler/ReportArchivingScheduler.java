package com.nanrong.inspection.scheduler;

import com.nanrong.inspection.domain.biz.Report;
import com.nanrong.inspection.repository.biz.ReportRepository;
import com.nanrong.inspection.shared.biz.ReportStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReportArchivingScheduler {

    @Autowired
    private ReportRepository reportRepository;
}