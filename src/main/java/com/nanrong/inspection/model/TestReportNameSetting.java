package com.nanrong.inspection.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.DynamicUpdate;
import com.nanrong.inspection.util.Comment;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Accessors(chain = true)
@FieldNameConstants
@Table(name = "test_report_name_setting")
@Comment("检测报告名称设置")
public class TestReportNameSetting implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment("主键")
  private Long id;

  @Column(length = 50)
  @Comment("检测报告代码")
  private String reportCode;

  @Column(length = 100)
  @Comment("检测报告简称")
  private String reportAbbreviation;

  @Column(length = 200)
  @Comment("检测报告名称")
  private String reportName;

  @Comment("排序号")
  private Integer sortOrder;
}
