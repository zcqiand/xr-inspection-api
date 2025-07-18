package com.nanrong.inspection.service.system;

import com.nanrong.inspection.domain.system.Role;
import com.nanrong.inspection.dto.system.RoleRequest;

import java.util.List;

public interface RoleService {
    Role createRole(RoleRequest roleRequest);

    Role updateRole(Long id, RoleRequest roleRequest);

    void addPermissionToRole(Long roleId, Long permissionId);

    void removePermissionFromRole(Long roleId, Long permissionId);

    List<Role> getRoles();
    void deleteRole(Long id);
}