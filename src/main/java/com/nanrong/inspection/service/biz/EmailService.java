package com.nanrong.inspection.service.biz;

import org.springframework.stereotype.Service;

import com.nanrong.inspection.domain.biz.Report;

@Service
public class EmailService {

    public void sendReportEmail(Report report) {
        // 实际发送邮件逻辑，这里简化实现
        System.out.println("发送报告邮件给客户: " + report.getId());
        System.out.println("报告路径: " + report.getPdfPath());
    }
    
    public void sendEmail(String to, String subject, String content) {
        // 实际发送邮件逻辑，这里简化实现
        System.out.println("发送邮件给: " + to);
        System.out.println("主题: " + subject);
        System.out.println("内容: " + content);
    }
}