package com.nanrong.inspection.dto.biz;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import java.time.OffsetDateTime;

@Data
@FieldNameConstants
@Accessors(chain = true)
public class BaseDeviceDTO {
    /**
     * 设备ID
     */
    private Long id;
    
    /**
     * 设备名称
     */
    private String name;
    
    /**
     * 设备型号
     */
    private String model;
    
    /**
     * 序列号
     */
    private String serialNumber;
    
    /**
     * 购买日期
     */
    private OffsetDateTime purchaseDate;
    
    /**
     * 状态
     */
    private String status;
    
    /**
     * 最后维护日期
     */
    private OffsetDateTime lastMaintenanceDate;
    
    /**
     * 位置
     */
    private String location;
    
    /**
     * 负责人
     */
    private String responsiblePerson;
}