package com.example.BTL_Nhom7_OOP.controller;

import com.example.BTL_Nhom7_OOP.dto.request.UserRoleRequest;
import com.example.BTL_Nhom7_OOP.dto.response.ApiResponse;
import com.example.BTL_Nhom7_OOP.dto.response.UserRoleResponse;
import com.example.BTL_Nhom7_OOP.service.UserRoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user-roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRoleController {
    UserRoleService userRoleService;

    @PostMapping("/assign")
    ApiResponse<UserRoleResponse> assignRole(@RequestBody @Valid UserRoleRequest request) {
        return ApiResponse.<UserRoleResponse>builder()
                .result(userRoleService.assignRole(request.getUserId(), request.getRoleId()))
                .build();
    }

    @DeleteMapping("/{userRoleId}")
    ApiResponse<String> deleteRole(@PathVariable int userRoleId) {
        userRoleService.deleteUserRole(userRoleId);
        return ApiResponse.<String>builder()
                .result("UserRole has been deleted")
                .build();
    }
}
