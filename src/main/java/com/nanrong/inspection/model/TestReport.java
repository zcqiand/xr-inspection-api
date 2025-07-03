package com.nanrong.inspection.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.DynamicUpdate;
import com.nanrong.inspection.util.Comment;

/**
 * 检测报告类
 */
@Entity
@Table(name = "test_report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@FieldNameConstants
@DynamicUpdate
public class TestReport {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment("主键ID")
  private Long id;

  @Column(length = 50)
  @Comment("报告编号")
  private String reportNumber;

  @ManyToOne
  @JoinColumn(name = "report_name_setting_id", referencedColumnName = "id")
  @Comment("检测报告名称设置")
  private TestReportNameSetting reportNameSetting;

  @Column(length = 50)
  @Comment("检测类别")
  private String testingCategory;

  @Column(length = 100)
  @Comment("工程名称")
  private String projectName;

  @Column(length = 100)
  @Comment("施工单位")
  private String constructionUnit;

  @Column(length = 100)
  @Comment("工程部位")
  private String engineeringLocation;

  @Column(length = 100)
  @Comment("委托单位")
  private String clientUnit;

  @Column(length = 100)
  @Comment("见证单位")
  private String witnessUnit;

  @Column(length = 50)
  @Comment("见证人")
  private String witnessPerson;

  @Column(length = 100)
  @Comment("样品来源")
  private String sampleSource;

  @Comment("收样日期")
  private OffsetDateTime receivingDate;

  @Comment("检测日期")
  private OffsetDateTime testingDate;

  @Column(length = 255)
  @Comment("检测地址")
  private String testingAddress;

  @Column(length = 100)
  @Comment("检测环境")
  private String testingEnvironment;

  @Column(length = 255)
  @Comment("主要设备")
  private String mainEquipment;

  @Column(length = 255)
  @Comment("判定依据")
  private String judgmentBasis;

  @Column(length = 500)
  @Comment("结论")
  private String conclusion;

  @Column(length = 500)
  @Comment("备注")
  private String remarks;

  @Column(length = 50)
  @Comment("检测人")
  private String testedBy;

  @Column(length = 50)
  @Comment("审核人")
  private String reviewedBy;

  @Column(length = 50)
  @Comment("批准人")
  private String approvedBy;

  @Comment("签发日期")
  private OffsetDateTime issueDate;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(referencedColumnName = "id")
  @Comment("检测单位基本信息")
  private TestingAgencyInfo testingAgencyInfo;

  @OneToMany(mappedBy = "testReport", cascade = CascadeType.ALL, orphanRemoval = true)
  @Comment("检测样品组")
  private List<TestSampleGroup> sampleGroups;
}
