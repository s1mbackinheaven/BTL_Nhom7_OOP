package com.example.BTL_Nhom7_OOP.controller;

import com.example.BTL_Nhom7_OOP.dto.PermissionGroupDTO;
import com.example.BTL_Nhom7_OOP.dto.PermissionGroupPermissionDTO;
import com.example.BTL_Nhom7_OOP.dto.PermissionGroupPermissionRequest;
import com.example.BTL_Nhom7_OOP.entity.PermissionGroup;
import com.example.BTL_Nhom7_OOP.entity.PermissionGroupPermission;
import com.example.BTL_Nhom7_OOP.service.PermissionGroupPermissionService;
import com.example.BTL_Nhom7_OOP.service.PermissionGroupService;
import com.example.BTL_Nhom7_OOP.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/permission_group_permission")
public class PermissionGroupPermissionController {
    @Autowired
    private PermissionGroupPermissionService permissionGroupPermissionService;

    @Autowired
    private PermissionGroupService permissionGroupService;

    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public ResponseEntity<PermissionGroupPermissionDTO> createPermissionGroupPermission(@RequestBody PermissionGroupPermissionRequest permissionGroupPermissionRequest) {
        PermissionGroupPermission permissionGroupPermission = new PermissionGroupPermission();
        permissionGroupPermission = permissionGroupPermissionService.createPermissionGroup(permissionGroupPermissionRequest.getPermissionGroupId(), permissionGroupPermissionRequest.getPermissionsId());
        return ResponseEntity.ok(PermissionGroupPermissionDTO.fromEntity(permissionGroupPermission));
    }

    @GetMapping("/get_all_permission_group_permission")
    public ResponseEntity<List<PermissionGroupPermissionDTO>> getPermissionGroupPermissions() {
        List<PermissionGroupPermission> permissionGroupPermissions =  permissionGroupPermissionService.getAllPermissionGroupPermission();
        List<PermissionGroupPermissionDTO> permissionGroupPermissionDTOS = new ArrayList<>();
        for (PermissionGroupPermission permissionGroupPermission : permissionGroupPermissions) {
            permissionGroupPermissionDTOS.add(PermissionGroupPermissionDTO.fromEntity(permissionGroupPermission));
        }
        return ResponseEntity.ok(permissionGroupPermissionDTOS);
    }

    @PutMapping("/update_permission_group_permission/{id}")
    public ResponseEntity<PermissionGroupPermissionDTO> updatePermissionGroupPermission(@PathVariable int id, @RequestBody PermissionGroupPermissionRequest permissionGroupPermissionRequest) {
        PermissionGroupPermission permissionGroupPermission = permissionGroupPermissionService.getPermissionGroupPermission(id);
        permissionGroupPermission.setPermissionGroup(permissionGroupService.getPermissionGroupById(permissionGroupPermissionRequest.getPermissionGroupId()));
        permissionGroupPermission.setPermission(permissionService.getPermissionById(permissionGroupPermissionRequest.getPermissionsId()));
        permissionGroupPermissionService.updatePermissionGroup(permissionGroupPermission);
        return ResponseEntity.ok(PermissionGroupPermissionDTO.fromEntity(permissionGroupPermission));
    }

    @DeleteMapping("/delete_permission_group_permission/{id}")
    public ResponseEntity<String> deletePermissionGroupPermission(@PathVariable int id) {
        permissionGroupPermissionService.deletePermissionGroupPermission(id);
        return ResponseEntity.ok("Đã xóa");

    }
}
