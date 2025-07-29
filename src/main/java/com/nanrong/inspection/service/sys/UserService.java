package com.nanrong.inspection.service.sys;

import com.nanrong.inspection.domain.sys.User;
import com.nanrong.inspection.dto.sys.UserRequest;

import java.util.List;

public interface UserService {
    User createUser(UserRequest userRequest);

    User updateUser(Long id, UserRequest userRequest);

    void updateUserStatus(Long id, boolean enabled);

    User getUser(Long id);

    List<User> getUsers();

    void deleteUser(Long id);
}