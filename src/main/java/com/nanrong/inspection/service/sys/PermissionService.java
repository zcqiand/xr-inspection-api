package com.nanrong.inspection.service.sys;

import com.nanrong.inspection.domain.sys.Permission;
import com.nanrong.inspection.dto.sys.PermissionRequest;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

public interface PermissionService {
    boolean hasPermission(Long userId, String permissionCode) throws AccessDeniedException;
    Permission createPermission(PermissionRequest permissionRequest);

    Permission updatePermission(Long id, PermissionRequest permissionRequest);

    void deletePermission(Long id);

    List<Permission> getPermissions();
}