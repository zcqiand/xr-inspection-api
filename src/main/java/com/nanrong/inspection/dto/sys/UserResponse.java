package com.nanrong.inspection.dto.sys;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@Accessors(chain = true)
public class UserResponse {
    /**
     * 用户ID
     */
    private Long id;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 是否启用
     */
    private boolean enabled;
    
    // 可以根据需要添加其他字段，例如角色信息
}