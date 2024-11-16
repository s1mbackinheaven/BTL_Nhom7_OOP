package com.example.BTL_Nhom7_OOP.service;

import com.example.BTL_Nhom7_OOP.entity.Permission;
import com.example.BTL_Nhom7_OOP.entity.PermissionGroup;
import com.example.BTL_Nhom7_OOP.entity.PermissionGroupPermission;
import com.example.BTL_Nhom7_OOP.repository.PermissionGroupPermissionRepository;
import com.example.BTL_Nhom7_OOP.repository.PermissionGroupRepository;
import com.example.BTL_Nhom7_OOP.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionGroupPermissionService {
    @Autowired
    private PermissionGroupPermissionRepository permissionGroupPermissionRepository;

    @Autowired
    private PermissionGroupRepository permissionGroupRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public PermissionGroupPermission createPermissionGroup(int permissionGroupId, int permissionId) {
        PermissionGroup permissionGroup = permissionGroupRepository.findById(permissionGroupId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhóm quyền"));
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy quyền"));
        PermissionGroupPermission permissionGroupPermission = new PermissionGroupPermission();
        permissionGroupPermission.setPermissionGroup(permissionGroup);
        permissionGroupPermission.setPermission(permission);
        return permissionGroupPermissionRepository.save(permissionGroupPermission);
    }

    public PermissionGroupPermission getPermissionGroupPermission(int id) {
        return permissionGroupPermissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
    }

    public List<PermissionGroupPermission> getAllPermissionGroupPermission() {
        return permissionGroupPermissionRepository.findAll();
    }

    public PermissionGroupPermission updatePermissionGroup(PermissionGroupPermission newPermissionGroupPermission) {
//        PermissionGroupPermission permissionGroupPermission = permissionGroupPermissionRepository.findById(permissionGroupPermissionId)
//                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));
        return permissionGroupPermissionRepository.save(newPermissionGroupPermission);
    }

    public void deletePermissionGroupPermission(int id) {
        permissionGroupPermissionRepository.deleteById(id);
    }
}

