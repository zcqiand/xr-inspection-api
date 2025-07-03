package com.nanrong.inspection.dto.test_report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "检测报告批准请求DTO")
public class TestReportApproveRequestDto {

  @Schema(description = "批准人")
  private String approvedBy;

  @Schema(description = "签发日期")
  private OffsetDateTime issueDate;
}
