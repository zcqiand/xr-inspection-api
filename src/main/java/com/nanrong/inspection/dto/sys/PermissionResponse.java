package com.nanrong.inspection.dto.sys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@Accessors(chain = true)
@Schema(description = "权限响应对象")
public class PermissionResponse {
    /**
     * 权限ID
     */
    private Long id;
    
    /**
     * 权限代码
     */
    private String code;
    
    /**
     * 权限描述
     */
    private String description;
}