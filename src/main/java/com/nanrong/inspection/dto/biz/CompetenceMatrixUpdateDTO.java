package com.nanrong.inspection.dto.biz;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import java.time.OffsetDateTime;

@Data
@FieldNameConstants
@Accessors(chain = true)
public class CompetenceMatrixUpdateDTO {
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 资质ID
     */
    private String qualificationId;
    
    /**
     * 新等级
     */
    private String newLevel;
    
    /**
     * 生效日期
     */
    private OffsetDateTime effectiveDate;
}