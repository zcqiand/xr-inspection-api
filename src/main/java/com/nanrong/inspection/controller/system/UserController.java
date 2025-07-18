package com.nanrong.inspection.controller.system;

import com.nanrong.inspection.domain.system.User;
import com.nanrong.inspection.dto.system.UserRequest;
import com.nanrong.inspection.dto.system.UserResponse;
import com.nanrong.inspection.service.system.UserService;
import com.nanrong.inspection.util.DTOConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/system/users")
@Tag(name = "用户管理", description = "用户账户管理接口")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "创建用户", description = "创建新用户账户")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        User user = userService.createUser(request);
        return ResponseEntity.ok(DTOConverter.toUserResponse(user));
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新用户", description = "更新用户账户信息")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        User user = userService.updateUser(id, request);
        return ResponseEntity.ok(DTOConverter.toUserResponse(user));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "更新用户状态", description = "启用/禁用用户账户")
    public ResponseEntity<Void> updateUserStatus(@PathVariable Long id, @RequestParam boolean enabled) {
        userService.updateUserStatus(id, enabled);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取用户详情", description = "获取单个用户详细信息")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(DTOConverter.toUserResponse(user));
    }

    @GetMapping
    @Operation(summary = "获取用户列表", description = "获取所有用户列表")
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<User> users = userService.getUsers();
        List<UserResponse> responses = users.stream()
                .map(DTOConverter::toUserResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户", description = "删除用户账户（逻辑删除）")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}