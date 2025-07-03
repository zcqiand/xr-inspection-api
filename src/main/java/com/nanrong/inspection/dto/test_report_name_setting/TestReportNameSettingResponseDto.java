package com.nanrong.inspection.dto.test_report_name_setting;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import com.nanrong.inspection.util.Comment;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "检测报告名称设置响应DTO")
public class TestReportNameSettingResponseDto {

  @Schema(description = "主键")
  private Long id;

  @Schema(description = "检测报告代码")
  private String reportCode;

  @Schema(description = "检测报告简称")
  private String reportAbbreviation;

  @Schema(description = "检测报告名称")
  private String reportName;

  @Schema(description = "排序号")
  private Integer sortOrder;
}
