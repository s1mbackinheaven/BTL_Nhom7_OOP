package com.example.BTL_Nhom7_OOP.service;

import com.example.BTL_Nhom7_OOP.dto.PermissionGroupDTO;
import com.example.BTL_Nhom7_OOP.entity.Permission;
import com.example.BTL_Nhom7_OOP.entity.PermissionGroup;
import com.example.BTL_Nhom7_OOP.entity.PermissionGroupPermission;
import com.example.BTL_Nhom7_OOP.exception.ResourceNotFoundException;
import com.example.BTL_Nhom7_OOP.repository.PermissionGroupPermissionRepository;
import com.example.BTL_Nhom7_OOP.repository.PermissionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionGroupService {
    @Autowired
    PermissionGroupPermissionRepository permissionGroupPermissionRepository;

    @Autowired
    PermissionGroupRepository permissionGroupRepository;

    public PermissionGroup createPermissionGroup(PermissionGroup permissionGroup) {
        return permissionGroupRepository.save(permissionGroup);
    }

    public PermissionGroup getPermissionGroupById(int id) {
        return permissionGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhóm quyền"));
    }

    public List<PermissionGroup> getAllPermissionGroups() {
        List<PermissionGroup> permissionGroups = permissionGroupRepository.findAll();
        if (permissionGroups.isEmpty()) {
            throw new ResourceNotFoundException("Không có nhóm quyền nào");
        }
        return permissionGroups;
    }

    public PermissionGroup updatePermissionGroup(int id, PermissionGroup newPermissionGroup) {
        PermissionGroup permissionGroup = getPermissionGroupById(id);
        permissionGroup.setName(newPermissionGroup.getName());
        permissionGroup.setDescription(newPermissionGroup.getDescription());
        return permissionGroupRepository.save(permissionGroup);
    }

    public void deletePermissionGroup(int id) {
        PermissionGroup permissionGroup = getPermissionGroupById(id);
        permissionGroupRepository.delete(permissionGroup);
    }

    public List<Permission> getPermissionsByPermissionGroupId(int id) {
        List<PermissionGroupPermission> permissionGroupPermissions =
                permissionGroupPermissionRepository.findByPermissionGroupId(id);

        return permissionGroupPermissions.stream()
                .map(PermissionGroupPermission::getPermission)
                .collect(Collectors.toList());
    }
}
