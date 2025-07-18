package com.nanrong.inspection.service.system;

import com.nanrong.inspection.domain.system.User;
import com.nanrong.inspection.dto.system.UserRequest;

import java.util.List;

public interface UserService {
    User createUser(UserRequest userRequest);

    User updateUser(Long id, UserRequest userRequest);

    void updateUserStatus(Long id, boolean enabled);

    User getUser(Long id);

    List<User> getUsers();

    void deleteUser(Long id);
}