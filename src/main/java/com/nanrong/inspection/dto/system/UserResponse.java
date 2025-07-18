package com.nanrong.inspection.dto.system;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private boolean enabled;
    // 可以根据需要添加其他字段，例如角色信息
}