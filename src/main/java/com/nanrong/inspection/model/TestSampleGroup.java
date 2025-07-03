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
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 检测样品组类
 */
@Entity
@Table(name = "test_sample_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@FieldNameConstants
@DynamicUpdate
public class TestSampleGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment("主键ID")
  private Long id;

  @Column(length = 50)
  @Comment("样品编号")
  private String sampleNumber;

  @Column(length = 50)
  @Comment("样品种类")
  private String sampleType;

  @Column(length = 50)
  @Comment("接头类型")
  private String jointType;

  @Column(length = 50)
  @Comment("钢筋牌号")
  private String rebarGrade;

  @Column(length = 50)
  @Comment("接头等级")
  private String jointGrade;

  @Column
  @Comment("公称直径(mm)")
  private BigDecimal nominalDiameter;

  @Column(length = 50)
  @Comment("炉号(批号)")
  private String furnaceBatchNumber;

  @Column(length = 100)
  @Comment("生产厂家")
  private String manufacturer;

  @Comment("代表数量（个或t）")
  private String representativeQuantity;

  @Column(length = 50)
  @Comment("样品状态")
  private String sampleStatus;

  @Column(length = 50)
  @Comment("焊接方式")
  private String weldingMethod;

  @Column(length = 50)
  @Comment("焊工姓名")
  private String welderName;

  @Column(length = 50)
  @Comment("焊工证号")
  private String welderCertificateNumber;

  @OneToMany(mappedBy = "sampleGroup", cascade = CascadeType.ALL, orphanRemoval = true)
  @Comment("检测结果列表")
  private List<TestResult> TestResults;

  @ManyToOne
  @JoinColumn(name = "test_report_id", nullable = false)
  @Comment("所属检测报告")
  private TestReport testReport;
}
