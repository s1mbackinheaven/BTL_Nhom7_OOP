package com.example.BTL_Nhom7_OOP.service;

import com.example.BTL_Nhom7_OOP.dto.response.UserRoleResponse;
import com.example.BTL_Nhom7_OOP.entity.Role;
import com.example.BTL_Nhom7_OOP.entity.User;
import com.example.BTL_Nhom7_OOP.entity.UserRole;
import com.example.BTL_Nhom7_OOP.exception.AppException;
import com.example.BTL_Nhom7_OOP.exception.ErrorCode;
import com.example.BTL_Nhom7_OOP.mapper.UserRoleMapper;
import com.example.BTL_Nhom7_OOP.repository.RoleRepository;
import com.example.BTL_Nhom7_OOP.repository.UserRepository;
import com.example.BTL_Nhom7_OOP.repository.UserRoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRoleService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserRoleRepository userRoleRepository;
    UserRoleMapper userRoleMapper;

    @Transactional
    public UserRoleResponse assignRole(String userId, int roleId) {
        // Kiểm tra User có tồn tại hay không
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // Kiểm tra Role có tồn tại hay không
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));

        UserRole userRole = UserRole.builder()
                .user(user)
                .role(role)
                .build();

        UserRole savedUserRole = userRoleRepository.save(userRole);
        return userRoleMapper.toUserRoleResponse(savedUserRole);
    }

    public void deleteUserRole(int userRoleId) {
        userRoleRepository.deleteById(userRoleId);
    }
}
