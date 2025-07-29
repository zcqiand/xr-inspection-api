package com.nanrong.inspection.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nanrong.inspection.dto.biz.BaseDeviceDTO;
import com.nanrong.inspection.dto.sys.PermissionRequest;
import com.nanrong.inspection.dto.sys.PermissionResponse;
import com.nanrong.inspection.dto.sys.RoleRequest;
import com.nanrong.inspection.dto.sys.RoleResponse;
import com.nanrong.inspection.dto.sys.UserRequest;
import com.nanrong.inspection.dto.sys.UserResponse;
import com.nanrong.inspection.domain.biz.Device;
import com.nanrong.inspection.domain.sys.Permission;
import com.nanrong.inspection.domain.sys.Role;
import com.nanrong.inspection.domain.sys.User;

public class DTOConverter {
    private static final ModelMapper modelMapper = new ModelMapper();
    
    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static <D> D convertToDTO(Object source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public static <E> E convertToEntity(Object source, Class<E> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public static void updateEntityFromDTO(Object source, Object destination) {
        modelMapper.map(source, destination);
    }

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
    public static BaseDeviceDTO convertToDeviceDTO(Device device) {
        BaseDeviceDTO dto = new BaseDeviceDTO();
        dto.setId(device.getId());
        dto.setName(device.getName());
        dto.setModel(device.getModel());
        dto.setSerialNumber(device.getSerialNumber());
        dto.setPurchaseDate(device.getPurchaseDate());
        dto.setStatus(device.getStatus());
        dto.setLastMaintenanceDate(device.getLastMaintenanceDate());
        dto.setLocation(device.getLocation());
        dto.setResponsiblePerson(device.getResponsiblePerson());
        return dto;
    }

    public static Device convertToDeviceEntity(BaseDeviceDTO dto) {
        Device device = new Device();
        device.setId(dto.getId());
        device.setName(dto.getName());
        device.setModel(dto.getModel());
        device.setSerialNumber(dto.getSerialNumber());
        device.setPurchaseDate(dto.getPurchaseDate());
        device.setStatus(dto.getStatus());
        device.setLastMaintenanceDate(dto.getLastMaintenanceDate());
        device.setLocation(dto.getLocation());
        device.setResponsiblePerson(dto.getResponsiblePerson());
        return device;
    }
}