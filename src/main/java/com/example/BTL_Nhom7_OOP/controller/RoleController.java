package com.example.BTL_Nhom7_OOP.controller;

import com.example.BTL_Nhom7_OOP.dto.request.RoleRequest;
import com.example.BTL_Nhom7_OOP.dto.response.RoleDTO;
import com.example.BTL_Nhom7_OOP.entity.Role;
import com.example.BTL_Nhom7_OOP.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleRequest roleRequest) {
        Role role = new Role();
        role.setName(roleRequest.getName());
        role.setDetail(roleRequest.getDetail());
        Role createdRole = roleService.createRole(role);
        return ResponseEntity.ok(RoleDTO.fromEntity(createdRole));
    }

    @GetMapping("/get_role_by_id/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable int id) {
        Role role = roleService.getRoleById(id);
        return ResponseEntity.ok(RoleDTO.fromEntity(role));
    }

    @GetMapping("/get_all_role")
    public ResponseEntity<List<RoleDTO>> getAllRole() {
        List<Role> roles = roleService.getAllRoles();
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (Role role : roles) {
            roleDTOS.add(RoleDTO.fromEntity(role));
        }
        return ResponseEntity.ok(roleDTOS);
    }

    @PutMapping("/update_role/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable int id, @RequestBody RoleRequest roleRequest) {
        Role role = roleService.getRoleById(id);
        role.setName(roleRequest.getName());
        role.setDetail(roleRequest.getDetail());
        Role updatedRole = roleService.updateRole(role);
        return ResponseEntity.ok(RoleDTO.fromEntity(updatedRole));
    }

    @DeleteMapping("/delete_role/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable int id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok("Đã xóa Role");
    }
}
