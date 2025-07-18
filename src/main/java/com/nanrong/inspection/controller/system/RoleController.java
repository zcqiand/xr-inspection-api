package com.nanrong.inspection.controller.system;

import com.nanrong.inspection.domain.system.Role;
import com.nanrong.inspection.dto.system.RoleRequest;
import com.nanrong.inspection.dto.system.RoleResponse;
import com.nanrong.inspection.service.system.RoleService;
import com.nanrong.inspection.util.DTOConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/system/roles")
@Tag(name = "角色管理", description = "角色和权限管理接口")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @Operation(summary = "创建角色", description = "创建新角色")
    public ResponseEntity<RoleResponse> createRole(@RequestBody RoleRequest request) {
        Role role = roleService.createRole(request);
        return ResponseEntity.ok(DTOConverter.toRoleResponse(role));
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新角色", description = "更新角色信息")
    public ResponseEntity<RoleResponse> updateRole(@PathVariable Long id, @RequestBody RoleRequest request) {
        Role role = roleService.updateRole(id, request);
        return ResponseEntity.ok(DTOConverter.toRoleResponse(role));
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    @Operation(summary = "为角色添加权限", description = "为指定角色添加权限")
    public ResponseEntity<Void> addPermissionToRole(
            @PathVariable Long roleId,
            @PathVariable Long permissionId) {
        roleService.addPermissionToRole(roleId, permissionId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除角色", description = "删除指定角色")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    @Operation(summary = "移除角色权限", description = "从指定角色移除权限")
    public ResponseEntity<Void> removePermissionFromRole(
            @PathVariable Long roleId,
            @PathVariable Long permissionId) {
        roleService.removePermissionFromRole(roleId, permissionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "获取角色列表", description = "获取所有角色列表")
    public ResponseEntity<List<RoleResponse>> getRoles() {
        List<Role> roles = roleService.getRoles();
        List<RoleResponse> responses = roles.stream()
                .map(DTOConverter::toRoleResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}