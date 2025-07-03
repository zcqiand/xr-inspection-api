package com.nanrong.inspection.dto.test_report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "检测报告数据录入请求DTO")
public class TestReportDataEntryRequestDto {

  @Schema(description = "检测日期")
  private OffsetDateTime testingDate;

  @Schema(description = "检测地址")
  private String testingAddress;

  @Schema(description = "检测环境")
  private String testingEnvironment;

  @Schema(description = "主要设备")
  private String mainEquipment;

  @Schema(description = "结论")
  private String conclusion;

  @Schema(description = "备注")
  private String remarks;

  @Schema(description = "检测人")
  private String testedBy;
}
