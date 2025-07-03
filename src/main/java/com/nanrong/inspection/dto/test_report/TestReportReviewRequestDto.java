package com.nanrong.inspection.dto.test_report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "检测报告审核请求DTO")
public class TestReportReviewRequestDto {

  @Schema(description = "审核人")
  private String reviewedBy;
}
