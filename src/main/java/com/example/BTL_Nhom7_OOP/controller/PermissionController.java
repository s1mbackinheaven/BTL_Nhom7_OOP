package com.example.BTL_Nhom7_OOP.controller;

import com.example.BTL_Nhom7_OOP.dto.response.PermissionDTO;
import com.example.BTL_Nhom7_OOP.dto.request.PermissionRequest;
import com.example.BTL_Nhom7_OOP.entity.Permission;
import com.example.BTL_Nhom7_OOP.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public ResponseEntity<PermissionDTO> createPermission(@RequestBody PermissionRequest permissionRequest) {
        Permission permission = new Permission();
        permission.setName(permissionRequest.getName());
        permission.setDescription(permissionRequest.getDescription());
        Permission createdPermission = permissionService.createPermission(permission);
        return ResponseEntity.ok(PermissionDTO.fromEntity(createdPermission));
    }

    @GetMapping("/get_permission_by_id/{id}")
    public ResponseEntity<PermissionDTO> getPermissionById(@PathVariable int id) {
        Permission permission = permissionService.getPermissionById(id);
        return ResponseEntity.ok(PermissionDTO.fromEntity(permission));
    }

    @GetMapping("/get_all_permission")
    public ResponseEntity<List<PermissionDTO>> getAllPermission() {
        List<Permission> permissions = permissionService.getAllPermissions();
        List<PermissionDTO> permissionDTOS = new ArrayList<>();
        for (Permission permission : permissions) {
            permissionDTOS.add(PermissionDTO.fromEntity(permission));
        }
        return ResponseEntity.ok(permissionDTOS);
    }

    @PutMapping("/update_permission/{id}")
    public ResponseEntity<PermissionDTO> updatePermission(@PathVariable int id, @RequestBody PermissionRequest permissionRequest) {
        Permission permission = permissionService.getPermissionById(id);
        permission.setName(permissionRequest.getName());
        permission.setDescription(permissionRequest.getDescription());
        Permission newPermission = permissionService.updatePermission(id, permission);
        return ResponseEntity.ok(PermissionDTO.fromEntity(newPermission));
    }

    @DeleteMapping("/delete_permission")
    public ResponseEntity<String> deletePermission(@PathVariable int id) {
        permissionService.deletePermission(id);
        return ResponseEntity.ok("Đã xóa quyền");
    }
}
