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
 * 检测结果类
 */
@Entity
@Table(name = "test_result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@FieldNameConstants
@DynamicUpdate
public class TestResult {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment("主键ID")
  private Long id;

  @Column(length = 255)
  @Comment("检测依据")
  private String testingBasis;

  @ManyToOne
  @JoinColumn(name = "test_item_setting_id", nullable = false)
  @Comment("所属检测项目设置")
  private TestItemSetting testItemSetting;

  @Column(length = 255)
  @Comment("主项目技术要求")
  private String technicalRequirements;

  @Column
  @Comment("弯曲压头直径(mm)")
  private BigDecimal bendingHeadDiameter; // 钢筋弯曲或反向弯曲

  @Column
  @Comment("弯曲角度(°)")
  private BigDecimal bendingAngle; // 钢筋弯曲或反向弯曲

  @ManyToOne
  @JoinColumn(name = "test_sample_group_id", nullable = false)
  @Comment("所属检测样品组")
  private TestSampleGroup sampleGroup;

  @OneToMany(mappedBy = "testResult", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TestDetectionData> detectionDataList;
}
