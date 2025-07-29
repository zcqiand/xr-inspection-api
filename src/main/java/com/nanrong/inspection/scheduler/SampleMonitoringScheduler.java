package com.nanrong.inspection.scheduler;

import com.nanrong.inspection.domain.biz.SampleGroup;
import com.nanrong.inspection.repository.biz.SampleGroupRepository;
import com.nanrong.inspection.service.biz.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Component
public class SampleMonitoringScheduler {

    private final SampleGroupRepository sampleRepository;
    private final EmailService emailService;

    @Autowired
    public SampleMonitoringScheduler(SampleGroupRepository sampleRepository, EmailService emailService) {
        this.sampleRepository = sampleRepository;
        this.emailService = emailService;
    }

    // 每5分钟检查一次样品温度
    @Scheduled(fixedRate = 300000)
    public void monitorSampleTemperature() {
        List<SampleGroup> samples = sampleRepository.findAll();
        OffsetDateTime now = OffsetDateTime.now();
        
        for (SampleGroup sample : samples) {
            if (sample.isTemperatureExceeded()) {
                // 首次超标记录时间
                if (sample.getFirstExceededTime() == null) {
                    sample.setFirstExceededTime(now);
                    sampleRepository.save(sample);
                }
                
                // 计算超标持续时间
                Duration duration = Duration.between(sample.getFirstExceededTime(), now);
                long minutesExceeded = duration.toMinutes();
                
                // 10分钟持续超标触发三级告警
                if (minutesExceeded >= 10) {
                    sendTemperatureAlert(sample);
                }
            } else {
                // 温度恢复正常则重置首次超标时间
                if (sample.getFirstExceededTime() != null) {
                    sample.setFirstExceededTime(null);
                    sampleRepository.save(sample);
                }
            }
        }
    }

    private void sendTemperatureAlert(SampleGroup sample) {
        String subject = "样品存储温度超标告警";
        String content = String.format(
            "样品条码：%s\n存储温度要求：%.1f℃\n当前温度：%.1f℃\n超标持续时间：10分钟以上",
            sample.getBarcode(),
            sample.getStorageTemperature(),
            sample.getCurrentTemperature()
        );
        
        // 获取关联的检测报告负责人邮箱
        String to = sample.getReport().getContactEmail();
        
        emailService.sendEmail(to, subject, content);
    }
}