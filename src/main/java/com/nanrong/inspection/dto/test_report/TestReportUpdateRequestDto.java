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
@Schema(description = "检测报告更新请求DTO")
public class TestReportUpdateRequestDto {

  @Schema(description = "报告编号")
  private String reportNumber;

  @Schema(description = "检测报告名称设置ID")
  private Long reportNameSettingId;

  @Schema(description = "检测类别")
  private String testingCategory;

  @Schema(description = "工程名称")
  private String projectName;

  @Schema(description = "施工单位")
  private String constructionUnit;

  @Schema(description = "工程部位")
  private String engineeringLocation;

  @Schema(description = "委托单位")
  private String clientUnit;

  @Schema(description = "见证单位")
  private String witnessUnit;

  @Schema(description = "见证人")
  private String witnessPerson;

  @Schema(description = "样品来源")
  private String sampleSource;

  @Schema(description = "收样日期")
  private OffsetDateTime receivingDate;

  @Schema(description = "判定依据")
  private String judgmentBasis;

  @Schema(description = "备注")
  private String remarks;
}
