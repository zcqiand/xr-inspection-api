package com.nanrong.inspection.dto.sys;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Data
@FieldNameConstants
@Accessors(chain = true)
@Schema(description = "角色请求对象")
public class RoleRequest {
    /**
     * 角色名称
     */
    @NotBlank
    @Schema(description = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "管理员")
    private String name;

    /**
     * 角色描述
     */
    @Schema(description = "角色描述", example = "系统管理员角色")
    private String description;

    /**
     * 关联权限ID列表
     */
    @Schema(description = "关联权限ID列表")
    private List<Long> permissionIds;
}