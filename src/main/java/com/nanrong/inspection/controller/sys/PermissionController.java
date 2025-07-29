package com.nanrong.inspection.controller.sys;

import com.nanrong.inspection.domain.sys.Permission;
import com.nanrong.inspection.dto.sys.PermissionRequest;
import com.nanrong.inspection.dto.sys.PermissionResponse;
import com.nanrong.inspection.service.sys.PermissionService;
import com.nanrong.inspection.util.DTOConverter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sys/permission")
@Tag(name = "权限管理", description = "系统权限管理接口")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    @Operation(summary = "创建权限", description = "创建新权限")
    public ResponseEntity<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {
        Permission permission = permissionService.createPermission(request);
        return ResponseEntity.ok(DTOConverter.toPermissionResponse(permission));
    }

    @GetMapping
    @Operation(summary = "获取权限列表", description = "获取所有权限列表")
    public ResponseEntity<List<PermissionResponse>> getPermissions() {
        List<Permission> permissions = permissionService.getPermissions();
        List<PermissionResponse> responses = permissions.stream()
                .map(DTOConverter::toPermissionResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新权限", description = "更新指定ID的权限信息")
    public ResponseEntity<PermissionResponse> updatePermission(
            @PathVariable Long id,
            @RequestBody PermissionRequest request) {
        Permission permission = permissionService.updatePermission(id, request);
        return ResponseEntity.ok(DTOConverter.toPermissionResponse(permission));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除权限", description = "删除指定ID的权限")
    public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }
}