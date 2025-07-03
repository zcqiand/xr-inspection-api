package com.nanrong.inspection.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
 * 检测机构
 */
@Entity
@Table(name = "testing_agency_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@FieldNameConstants
@DynamicUpdate
public class TestingAgencyInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment("主键ID")
  private Long id;

  @Column(length = 255)
  @Comment("检测机构名称")
  private String agencyName;

  @Column(length = 100)
  @Comment("检测机构简称")
  private String agencyAbbreviation;

  @Column(length = 255)
  @Comment("注册地址")
  private String registrationAddress;

  @Column(length = 255)
  @Comment("检测能力场所地址")
  private String testingCapabilityAddress;

  @Column(length = 20)
  @Comment("邮政编码")
  private String postalCode;

  @Column(length = 20)
  @Comment("联系电话")
  private String contactPhone;

  @Column(length = 100)
  @Comment("电子信箱")
  private String email;

  @Column(length = 100)
  @Comment("资质证书编号")
  private String qualificationCertificateNumber;
}
