package com.example.BTL_Nhom7_OOP.dto;


import java.util.List;

public class PermissionGroupPermissionRequest {
    private int permissionGroupId;
    private Integer permissionsId;

    public PermissionGroupPermissionRequest(int permissionGroupId, Integer permissionsId) {
        this.permissionGroupId = permissionGroupId;
        this.permissionsId = permissionsId;
    }

    public int getPermissionGroupId() {
        return permissionGroupId;
    }

    public void setPermissionGroupId(int permissionGroupId) {
        this.permissionGroupId = permissionGroupId;
    }

    public Integer getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(Integer permissionsId) {
        this.permissionsId = permissionsId;
    }
}
