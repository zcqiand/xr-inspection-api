package com.nanrong.inspection.domain.biz;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import com.nanrong.inspection.domain.sys.User;

import java.time.OffsetDateTime;

/*
 * 系统用户资质关联实体
 */
@Entity
@Table(name = "biz_user_qualification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@DynamicUpdate
@Accessors(chain = true)
@Comment("用户资质关联表")
public class UserQualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("关联ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("关联用户")
    private User user;

    @ManyToOne
    @JoinColumn(name = "qualification_id", nullable = false)
    @Comment("关联资质")
    private BQualification qualification;

    @Column(nullable = false)
    @Comment("资质等级")
    private String level;

    @Column(nullable = false)
    @Comment("资质到期日期")
    private OffsetDateTime expiryDate;
}