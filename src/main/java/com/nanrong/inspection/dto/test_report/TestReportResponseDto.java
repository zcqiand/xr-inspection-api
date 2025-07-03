package com.nanrong.inspection.dto.test_report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import com.nanrong.inspection.dto.test_sample_groups.TestSampleGroupResponseDto;
import com.nanrong.inspection.model.TestReportNameSetting;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "检测报告响应DTO")
public class TestReportResponseDto {

  @Schema(description = "主键ID")
  private Long id;

  @Schema(description = "报告编号")
  private String reportNumber;

  @Schema(description = "检测报告名称设置")
  private TestReportNameSetting reportNameSetting;

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

  @Schema(description = "检测日期")
  private OffsetDateTime testingDate;

  @Schema(description = "检测地址")
  private String testingAddress;

  @Schema(description = "检测环境")
  private String testingEnvironment;

  @Schema(description = "主要设备")
  private String mainEquipment;

  @Schema(description = "判定依据")
  private String judgmentBasis;

  @Schema(description = "结论")
  private String conclusion;

  @Schema(description = "备注")
  private String remarks;

  @Schema(description = "检测人")
  private String testedBy;

  @Schema(description = "审核人")
  private String reviewedBy;

  @Schema(description = "批准人")
  private String approvedBy;

  @Schema(description = "签发日期")
  private OffsetDateTime issueDate;

  @Schema(description = "检测单位基本信息")
  private TestingAgencyInfoResponseDto testingAgencyInfo;

  @Schema(description = "检测样品组列表")
  private List<TestSampleGroupResponseDto> sampleGroups;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @Schema(description = "检测机构信息响应DTO")
  public static class TestingAgencyInfoResponseDto {
    @Schema(description = "主键ID")
    private Long id;
    @Schema(description = "检测机构名称")
    private String agencyName;
    @Schema(description = "检测机构简称")
    private String agencyAbbreviation;
    @Schema(description = "注册地址")
    private String registrationAddress;
    @Schema(description = "检测能力场所地址")
    private String testingCapabilityAddress;
    @Schema(description = "邮政编码")
    private String postalCode;
    @Schema(description = "联系电话")
    private String contactPhone;
    @Schema(description = "电子信箱")
    private String email;
    @Schema(description = "资质证书编号")
    private String qualificationCertificateNumber;
  }
}
