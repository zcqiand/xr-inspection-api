package com.nanrong.inspection.dto.sys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Data
@FieldNameConstants
@Accessors(chain = true)
@Schema(description = "角色响应对象")
public class RoleResponse {
    /**
     * 角色ID
     */
    private Long id;
    
    /**
     * 角色名称
     */
    private String name;
    
    /**
     * 角色描述
     */
    private String description;
    
    /**
     * 关联权限ID列表
     */
    private List<Long> permissionIds; // 关联权限ID列表
}