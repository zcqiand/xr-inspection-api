package com.nanrong.inspection.dto.test_sample_groups;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "检测样品组响应DTO")
public class TestSampleGroupResponseDto {

  @Schema(description = "主键ID", example = "1")
  private Long id;

  @Schema(description = "样品编号", example = "YP001")
  private String sampleNumber;

  @Schema(description = "样品种类", example = "钢筋")
  private String sampleType;

  @Schema(description = "接头类型", example = "闪光对焊")
  private String jointType;

  @Schema(description = "钢筋牌号", example = "HRB400")
  private String rebarGrade;

  @Schema(description = "接头等级", example = "I级")
  private String jointGrade;

  @Schema(description = "公称直径(mm)", example = "16.0")
  private BigDecimal nominalDiameter;

  @Schema(description = "炉号(批号)", example = "LH2023001")
  private String furnaceBatchNumber;

  @Schema(description = "生产厂家", example = "XX钢铁厂")
  private String manufacturer;

  @Schema(description = "代表数量（个或t）", example = "100个")
  private String representativeQuantity;

  @Schema(description = "样品状态", example = "合格")
  private String sampleStatus;

  @Schema(description = "焊接方式", example = "电弧焊")
  private String weldingMethod;

  @Schema(description = "焊工姓名", example = "张三")
  private String welderName;

  @Schema(description = "焊工证号", example = "HGZ001")
  private String welderCertificateNumber;

  @Schema(description = "检测结果列表")
  private List<TestResultDto> testResults;
}
