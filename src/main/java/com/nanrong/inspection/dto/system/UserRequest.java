package com.nanrong.inspection.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Data
@Schema(description = "用户请求对象")
public class UserRequest {
    @NotBlank
    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "admin")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 8, message = "密码长度至少8位")
    @Schema(description = "密码", example = "P@ssw0rd!")
    private String password;

    @Schema(description = "是否启用", defaultValue = "true")
    private boolean enabled = true;

    @Schema(description = "关联角色ID列表")
    private List<Long> roleIds;
}