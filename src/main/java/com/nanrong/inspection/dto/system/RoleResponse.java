package com.nanrong.inspection.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "角色响应对象")
public class RoleResponse {
    private Long id;
    private String name;
    private String description;
    private List<Long> permissionIds; // 关联权限ID列表
}