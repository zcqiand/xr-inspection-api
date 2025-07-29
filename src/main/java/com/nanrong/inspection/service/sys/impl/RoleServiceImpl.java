package com.nanrong.inspection.service.sys.impl;

import com.nanrong.inspection.domain.sys.Permission;
import com.nanrong.inspection.domain.sys.Role;
import com.nanrong.inspection.dto.sys.RoleRequest;
import com.nanrong.inspection.repository.sys.PermissionRepository;
import com.nanrong.inspection.repository.sys.RoleRepository;
import com.nanrong.inspection.service.sys.RoleService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleServiceImpl(
            RoleRepository roleRepository,
            PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    @Transactional
    public Role createRole(RoleRequest roleRequest) {
        Role role = new Role();
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());
        // 假设 RoleRequest 包含 permissionIds，需要处理权限关联
        // role.setPermissions(roleRequest.getPermissionIds().stream().map(permissionRepository::findById).map(Optional::get).collect(Collectors.toSet()));
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role updateRole(Long id, RoleRequest roleRequest) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("角色不存在"));
        existingRole.setName(roleRequest.getName());
        existingRole.setDescription(roleRequest.getDescription());
        // 假设 RoleRequest 包含 permissionIds，需要处理权限关联
        // existingRole.setPermissions(roleRequest.getPermissionIds().stream().map(permissionRepository::findById).map(Optional::get).collect(Collectors.toSet()));
        return roleRepository.save(existingRole);
    }

    @Override
    @Transactional
    public void addPermissionToRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("角色不存在"));
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("权限不存在"));
        role.getPermissions().add(permission);
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void removePermissionFromRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("角色不存在"));
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("权限不存在"));
        role.getPermissions().remove(permission);
        roleRepository.save(role);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("角色不存在"));
        roleRepository.deleteById(id);
    }
}