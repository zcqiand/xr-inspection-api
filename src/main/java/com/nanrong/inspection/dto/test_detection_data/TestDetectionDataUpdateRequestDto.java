package com.nanrong.inspection.dto.test_detection_data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * 检测数据更新请求DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDetectionDataUpdateRequestDto {

  @Schema(description = "检测数据ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull(message = "检测数据ID不能为空")
  private Long id;

  @Schema(description = "所属检测结果ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull(message = "所属检测结果ID不能为空")
  private Long testResultId;

  @Schema(description = "断口离焊缝距离(mm) - 钢筋焊接", example = "10.5")
  private BigDecimal fractureDistanceToWeld;

  @Schema(description = "断裂特征 - 钢筋焊接", example = "韧性断裂")
  private String fractureCharacteristic;

  @Schema(description = "断裂位置 - 钢筋机械连接", example = "连接件内")
  private String fractureLocation;
}
