package com.nanrong.inspection.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;

/**
 * 检测项目设置类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldNameConstants
@DynamicUpdate
@Entity
@Table(name = "test_item_setting")
public class TestItemSetting {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment("主键ID")
  private Long id;

  @Column(length = 50)
  @Comment("检测项目类型")
  private String testItemType;

  @Column(length = 50)
  @Comment("检测项目代码")
  private String testItemCode;

  @Column(length = 100)
  @Comment("检测项目名称")
  private String testItemName;

  @Column(length = 20)
  @Comment("检测项目单位")
  private String testItemUnit;
  @Comment("排序号")
  private Integer sortOrder;
}
