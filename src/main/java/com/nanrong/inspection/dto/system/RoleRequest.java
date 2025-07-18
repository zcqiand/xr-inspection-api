package com.nanrong.inspection.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Data
@Schema(description = "角色请求对象")
public class RoleRequest {
    @NotBlank
    @Schema(description = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "管理员")
    private String name;

    @Schema(description = "角色描述", example = "系统管理员角色")
    private String description;

    @Schema(description = "关联权限ID列表")
    private List<Long> permissionIds;
}