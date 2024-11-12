package com.example.BTL_Nhom7_OOP.controller;


import com.example.BTL_Nhom7_OOP.entity.Pet;
import com.example.BTL_Nhom7_OOP.dto.PetCreationRequest;
import com.example.BTL_Nhom7_OOP.dto.PetUpdateRequest;
import com.example.BTL_Nhom7_OOP.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;
    @PostMapping
    Pet createPet(@RequestBody PetCreationRequest request){//Thêm thú cưng mới của người dùng
        return petService.createPet(request);
    }
    @GetMapping
    List<Pet> getPets(){//Liệt kê thông tin tất cả thú cưng của người dùng
        return petService.getPets();
    }
    @GetMapping("/{petId}")
    Pet getPet(@PathVariable("petId") long petId){//Lấy ra thông tin thú cưng của người dùng bằng id
        return petService.getPet(petId);
    }
    @PutMapping("/{petId}")
    Pet updatePet(@PathVariable long petId, @RequestBody PetUpdateRequest request){//Sửa thông tin thú cưng của người dùng bằng id
        return petService.updatePet(petId, request);
    }
    @DeleteMapping("/{petId}")
    String deletePet(@PathVariable long petId){//Xóa thú cưng của người dùng bằng id
        petService.deletePet((petId));
        return "Đã xóa thành công thú cưng với ID: " + petId;
    }
}
