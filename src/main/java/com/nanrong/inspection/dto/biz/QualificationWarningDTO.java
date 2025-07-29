package com.nanrong.inspection.dto.biz;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@FieldNameConstants
@Accessors(chain = true)
public class QualificationWarningDTO {
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 资质名称
     */
    private String qualificationName;
    
    /**
     * 到期日期
     */
    private OffsetDateTime expiryDate;
    
    /**
     * 剩余天数
     */
    private Long daysRemaining;  // 改为Long类型匹配日期差返回类型

    public QualificationWarningDTO(Long userId, String username, String qualificationName,
                                  OffsetDateTime expiryDate, Long daysRemaining) {
        this.userId = userId;
        this.username = username;
        this.qualificationName = qualificationName;
        this.expiryDate = expiryDate;
        this.daysRemaining = daysRemaining;
    }
}