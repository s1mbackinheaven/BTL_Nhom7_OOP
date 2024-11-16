package com.example.BTL_Nhom7_OOP.dto;

import com.example.BTL_Nhom7_OOP.entity.Permission;
import com.example.BTL_Nhom7_OOP.entity.PermissionGroup;
import com.example.BTL_Nhom7_OOP.entity.PermissionGroupPermission;

public class PermissionGroupPermissionDTO {
    private int id;
    private PermissionGroup permissionGroup;
    private Permission permission;

    public PermissionGroupPermissionDTO(int id, PermissionGroup permissionGroup, Permission permission) {
        this.id = id;
        this.permissionGroup = permissionGroup;
        this.permission = permission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PermissionGroup getPermissionGroup() {
        return permissionGroup;
    }

    public void setPermissionGroup(PermissionGroup permissionGroup) {
        this.permissionGroup = permissionGroup;
    }

    public Permission getPermissions() {
        return permission;
    }

    public void setPermissions(Permission permission) {
        this.permission = permission;
    }

    public static PermissionGroupPermissionDTO fromEntity(PermissionGroupPermission permissionGroupPermission) {
        if (permissionGroupPermission == null) {
            return null;
        }

        return new PermissionGroupPermissionDTO(
                permissionGroupPermission.getId(),
                permissionGroupPermission.getPermissionGroup(),
                permissionGroupPermission.getPermission()
        );
    }
}
