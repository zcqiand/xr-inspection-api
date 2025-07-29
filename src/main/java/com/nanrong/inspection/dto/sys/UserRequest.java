package com.nanrong.inspection.dto.sys;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Data
@FieldNameConstants
@Accessors(chain = true)
@Schema(description = "用户请求对象")
public class UserRequest {
    /**
     * 用户名
     */
    @NotBlank
    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "admin")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, message = "密码长度至少8位")
    @Schema(description = "密码", example = "P@ssw0rd!")
    private String password;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用", defaultValue = "true")
    private boolean enabled = true;

    /**
     * 关联角色ID列表
     */
    @Schema(description = "关联角色ID列表")
    private List<Long> roleIds;
}