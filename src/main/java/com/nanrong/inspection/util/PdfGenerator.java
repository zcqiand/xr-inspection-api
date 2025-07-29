package com.nanrong.inspection.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.nanrong.inspection.domain.biz.Report;

import org.springframework.stereotype.Component;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class PdfGenerator {

    public String generateReportPdf(Report report) {
        String filePath = "reports/report_" + report.getId() + ".pdf";
        
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            
            // 添加报告内容
            document.add(new Paragraph("检测报告 #" + report.getId()));
            document.add(new Paragraph("内容: " + report.getContent()));
            
            // 添加防伪水印
            PdfContentByte canvas = writer.getDirectContentUnder();
            canvas.beginText();
            canvas.setColorFill(BaseColor.LIGHT_GRAY);
            canvas.setFontAndSize(BaseFont.createFont(), 48);
            canvas.showTextAligned(Element.ALIGN_CENTER, "APPROVED", 300, 400, 45);
            canvas.endText();
            
            document.close();
            return filePath;
        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Failed to generate PDF", e);
        }
    }
}