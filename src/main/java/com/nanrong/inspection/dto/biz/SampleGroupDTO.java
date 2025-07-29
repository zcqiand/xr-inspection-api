package com.nanrong.inspection.dto.biz;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldNameConstants
@Accessors(chain = true)
public class SampleGroupDTO {
    /**
     * 条形码
     */
    private String barcode;
    
    /**
     * 接收时间
     */
    private LocalDateTime receivedTime;
    
    /**
     * 存储温度
     */
    private BigDecimal storageTemperature;
    
    /**
     * 状态
     */
    private String status;
    
    /**
     * 关联的委托任务ID
     */
    private Long entrustId;
}