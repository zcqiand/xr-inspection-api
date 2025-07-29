package com.nanrong.inspection.service.sys;

import com.nanrong.inspection.domain.sys.Role;
import com.nanrong.inspection.dto.sys.RoleRequest;

import java.util.List;

public interface RoleService {
    Role createRole(RoleRequest roleRequest);

    Role updateRole(Long id, RoleRequest roleRequest);

    void addPermissionToRole(Long roleId, Long permissionId);

    void removePermissionFromRole(Long roleId, Long permissionId);

    List<Role> getRoles();
    void deleteRole(Long id);
}