package com.nanrong.inspection.dto.test_item_setting;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "检测项目设置响应DTO")
public class TestItemSettingResponseDto {

  @Schema(description = "主键ID")
  private Long id;

  @Schema(description = "检测项目类型")
  private String testItemType;

  @Schema(description = "检测项目代码")
  private String testItemCode;

  @Schema(description = "检测项目名称")
  private String testItemName;

  @Schema(description = "检测项目单位")
  private String testItemUnit;
  @Schema(description = "排序号")
  private Integer sortOrder;
}
