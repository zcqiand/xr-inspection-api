package com.nanrong.inspection.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "权限请求对象")
public class PermissionRequest {
    @NotBlank
    @Schema(description = "权限代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "user:create")
    private String code;

    @Schema(description = "权限描述", example = "创建用户权限")
    private String description;
}