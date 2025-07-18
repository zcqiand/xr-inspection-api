package com.nanrong.inspection.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "权限响应对象")
public class PermissionResponse {
    private Long id;
    private String code;
    private String description;
}