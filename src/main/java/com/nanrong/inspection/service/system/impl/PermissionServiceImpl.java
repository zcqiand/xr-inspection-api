package com.nanrong.inspection.service.system.impl;

import com.nanrong.inspection.domain.system.Permission;
import com.nanrong.inspection.dto.system.PermissionRequest;
import com.nanrong.inspection.repository.system.PermissionRepository;
import com.nanrong.inspection.service.system.PermissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    @Transactional
    public Permission createPermission(PermissionRequest permissionRequest) {
        Permission permission = new Permission();
        permission.setCode(permissionRequest.getCode());
        permission.setDescription(permissionRequest.getDescription());
        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> getPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    @Transactional
    public Permission updatePermission(Long id, PermissionRequest permissionRequest) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));

        permission.setCode(permissionRequest.getCode());
        permission.setDescription(permissionRequest.getDescription());
        return permissionRepository.save(permission);
    }

    @Override
    @Transactional
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}