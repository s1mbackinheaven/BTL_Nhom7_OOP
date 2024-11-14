package com.example.BTL_Nhom7_OOP.controller;

import com.example.BTL_Nhom7_OOP.dto.PermissionGroupDTO;
import com.example.BTL_Nhom7_OOP.dto.PermissionGroupRequest;
import com.example.BTL_Nhom7_OOP.entity.PermissionGroup;
import com.example.BTL_Nhom7_OOP.service.PermissionGroupService;
import com.example.BTL_Nhom7_OOP.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/permission_group")
public class PermissionGroupController {
    @Autowired
    private PermissionGroupService permissionGroupService;

    @PostMapping
    public ResponseEntity<PermissionGroupDTO> createPermissionGroup(@RequestBody PermissionGroupRequest permissionGroupRequest) {
        PermissionGroup permissionGroup = new PermissionGroup();
        permissionGroup.setName(permissionGroupRequest.getName());
        permissionGroup.setDescription(permissionGroupRequest.getDescription());
        PermissionGroup createdPermissionGroup = permissionGroupService.createPermissionGroup(permissionGroup);
        return ResponseEntity.ok(PermissionGroupDTO.fromEntity(createdPermissionGroup));
    }

    @GetMapping("/get_permission_group_by_id/{id}")
    public ResponseEntity<PermissionGroupDTO> getPermissionGroupById(@PathVariable int id) {
        PermissionGroup permissionGroup = permissionGroupService.getPermissionGroupById(id);
        return ResponseEntity.ok(PermissionGroupDTO.fromEntity(permissionGroup));
    }

    @GetMapping("/get_all_permission_group")
    public ResponseEntity<List<PermissionGroupDTO>> getAllPermissionGroup() {
        List<PermissionGroup> permissionGroups = permissionGroupService.getAllPermissionGroups();
        List<PermissionGroupDTO> permissionGroupDTOS = new ArrayList<>();
        for (PermissionGroup permissionGroup : permissionGroups) {
            permissionGroupDTOS.add(PermissionGroupDTO.fromEntity(permissionGroup));
        }
        return ResponseEntity.ok(permissionGroupDTOS);
    }

    @PutMapping("/update_permission_group/{id}")
    public ResponseEntity<PermissionGroupDTO> updatePermissionGroup(@PathVariable int id, @RequestBody PermissionGroupRequest permissionGroupRequest) {
        PermissionGroup permissionGroup = permissionGroupService.getPermissionGroupById(id);
        permissionGroup.setName(permissionGroupRequest.getName());
        permissionGroup.setDescription(permissionGroupRequest.getDescription());
        PermissionGroup newPermission = permissionGroupService.updatePermissionGroup(id, permissionGroup);
        return ResponseEntity.ok(PermissionGroupDTO.fromEntity(newPermission));
    }

    @DeleteMapping("/delete_permission_group")
    public ResponseEntity<String> deletePermissionGroup(@RequestParam int id) {
        permissionGroupService.deletePermissionGroup(id);
        return ResponseEntity.ok("Đã xóa nhóm quyền");
    }
}
