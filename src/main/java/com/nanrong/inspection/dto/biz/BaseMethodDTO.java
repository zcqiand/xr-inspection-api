package com.nanrong.inspection.dto.biz;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@Accessors(chain = true)
public class BaseMethodDTO {
    /**
     * 方法名称
     */
    private String name;
    
    /**
     * 方法描述
     */
    private String description;
    
    /**
     * 标准
     */
    private String standard;
    
    /**
     * 分类
     */
    private String category;
}