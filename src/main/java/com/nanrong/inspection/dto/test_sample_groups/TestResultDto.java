package com.nanrong.inspection.dto.test_sample_groups;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "检测结果DTO")
public class TestResultDto {

  @Schema(description = "主键ID", example = "1")
  private Long id;

  @Schema(description = "检测依据", example = "GB/T 228.1-2021")
  private String testingBasis;

  @Schema(description = "所属检测项目设置ID", example = "1")
  private Long testItemSettingId;

  @Schema(description = "主项目技术要求", example = "屈服强度≥400MPa")
  private String technicalRequirements;

  @Schema(description = "弯曲压头直径(mm)", example = "50.0")
  private BigDecimal bendingHeadDiameter;

  @Schema(description = "弯曲角度(°)", example = "90.0")
  private BigDecimal bendingAngle;
}
