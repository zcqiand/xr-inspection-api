package com.nanrong.inspection.dto.sys;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@Accessors(chain = true)
@Schema(description = "权限请求对象")
public class PermissionRequest {
    /**
     * 权限代码
     */
    @NotBlank
    @Schema(description = "权限代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "user:create")
    private String code;

    /**
     * 权限描述
     */
    @Schema(description = "权限描述", example = "创建用户权限")
    private String description;
}