package com.nanrong.inspection.service.system;

import com.nanrong.inspection.domain.system.Permission;
import com.nanrong.inspection.dto.system.PermissionRequest;

import java.util.List;

public interface PermissionService {
    Permission createPermission(PermissionRequest permissionRequest);

    Permission updatePermission(Long id, PermissionRequest permissionRequest);

    void deletePermission(Long id);

    List<Permission> getPermissions();
}