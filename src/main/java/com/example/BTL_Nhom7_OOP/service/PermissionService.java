package com.example.BTL_Nhom7_OOP.service;

import com.example.BTL_Nhom7_OOP.entity.Permission;
import com.example.BTL_Nhom7_OOP.exception.ResourceNotFoundException;
import com.example.BTL_Nhom7_OOP.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission getPermissionById(int id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy quyền"));
    }

    public List<Permission> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        if (permissions.isEmpty()) {
            throw new ResourceNotFoundException("Không có quyền nào đã tạo");
        }
        return permissions;
    }

    public Permission updatePermission(int id, Permission newPermission) {
        Permission permission = getPermissionById(id);
        permission.setName(newPermission.getName());
        permission.setDescription(newPermission.getDescription());
        return permissionRepository.save(permission);
    }

    public void deletePermission(int id) {
        Permission permission = getPermissionById(id);
        permissionRepository.delete(permission);
    }
}
