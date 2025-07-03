package com.nanrong.inspection.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import java.math.BigDecimal;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 检测数据类
 */
@Entity
@Table(name = "test_detection_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@FieldNameConstants
@DynamicUpdate
public class TestDetectionData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment("主键ID")
  private Long id;

  @Column(precision = 10, scale = 2)
  @Comment("断口离焊缝距离(mm)")
  private BigDecimal fractureDistanceToWeld; // 钢筋焊接

  @Column(length = 50)
  @Comment("断裂特征")
  private String fractureCharacteristic; // 钢筋焊接

  @Column(length = 50)
  @Comment("断裂位置")
  private String fractureLocation; // 钢筋机械连接

  @ManyToOne
  @JoinColumn(name = "test_result_id", nullable = false)
  @Comment("所属检测结果")
  private TestResult testResult;
}
