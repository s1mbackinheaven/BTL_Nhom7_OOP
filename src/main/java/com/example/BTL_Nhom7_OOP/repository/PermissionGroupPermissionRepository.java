package com.example.BTL_Nhom7_OOP.repository;

import com.example.BTL_Nhom7_OOP.entity.PermissionGroup;
import com.example.BTL_Nhom7_OOP.entity.PermissionGroupPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionGroupPermissionRepository extends JpaRepository<PermissionGroupPermission, Integer> {
    List<PermissionGroupPermission> findByPermissionGroupId(int permissionGroupId);
}
