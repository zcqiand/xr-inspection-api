package com.nanrong.inspection.dto.test_report_name_setting;

import com.nanrong.inspection.util.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "检测报告名称设置更新请求DTO")
public class TestReportNameSettingUpdateRequestDto {

  @Schema(description = "主键ID")
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
