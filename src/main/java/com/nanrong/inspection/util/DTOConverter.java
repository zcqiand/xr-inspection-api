package com.nanrong.inspection.util;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.nanrong.inspection.dto.system.UserRequest;
import com.nanrong.inspection.dto.system.RoleRequest;
import com.nanrong.inspection.dto.system.PermissionRequest;

import com.nanrong.inspection.domain.system.Permission;
import com.nanrong.inspection.domain.system.Role;
import com.nanrong.inspection.domain.system.User;
import com.nanrong.inspection.dto.system.PermissionResponse;
import com.nanrong.inspection.dto.system.RoleResponse;
import com.nanrong.inspection.dto.system.UserResponse;

public class DTOConverter {

    public static User toUser(UserRequest request, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(request.isEnabled());
        return user;
    }

    public static UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEnabled(user.isEnabled());
        // 根据需要添加其他字段的映射，例如角色信息
        return response;
    }

    public static RoleResponse toRoleResponse(Role role) {
        RoleResponse response = new RoleResponse();
        response.setId(role.getId());
        response.setName(role.getName());
        response.setDescription(role.getDescription());
        response.setPermissionIds(role.getPermissions().stream().map(Permission::getId).toList());
        return response;
    }

    public static PermissionResponse toPermissionResponse(Permission permission) {
        PermissionResponse response = new PermissionResponse();
        response.setId(permission.getId());
        response.setCode(permission.getCode());
        response.setDescription(permission.getDescription());
        return response;
    }

    public static Role toRole(RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        return role;
    }

    public static Permission toPermission(PermissionRequest request) {
        Permission permission = new Permission();
        permission.setCode(request.getCode());
        permission.setDescription(request.getDescription());
        return permission;
    }
}