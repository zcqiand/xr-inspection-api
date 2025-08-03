package com.nanrong.inspection.dto.biz;

import lombok.Data;

@Data
public class ReportCompileDTO {
    private Long reportId;
    private String compiledContent;
    private String remarks;
}