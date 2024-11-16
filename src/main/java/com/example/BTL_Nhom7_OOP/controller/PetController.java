package com.example.BTL_Nhom7_OOP.controller;


import com.example.BTL_Nhom7_OOP.dto.*;
import com.example.BTL_Nhom7_OOP.entity.Customer;
import com.example.BTL_Nhom7_OOP.entity.Pet;
import com.example.BTL_Nhom7_OOP.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    @Autowired
    private PetService petService;
    //Thêm thú cưng mới của người dùng
    @PostMapping
    ApiResponse<Pet> createPet(@RequestBody PetCreationRequest request){
        ApiResponse<Pet> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Thêm thành công");
        apiResponse.setResult(petService.createPet(request));
        return apiResponse;
    }
    //Liệt kê thông tin tất cả thú cưng của người dùng
    @GetMapping("/get_all_pet")
    List<Pet> getPets(){
        return petService.getPets();
    }
    //Lấy ra thông tin thú cưng của người dùng bằng id
    @GetMapping("/get_pet/{petId}")
    Pet getPet(@PathVariable("petId") long petId){
        return petService.getPet(petId);
    }
    //Sửa thông tin thú cưng của người dùng bằng id
    @PutMapping("/update_infor_pet/{petId}")
    ApiResponse<Pet> updatePet(@PathVariable long petId, @RequestBody PetUpdateRequest request){
        ApiResponse<Pet> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Sửa thành công");
        apiResponse.setResult(petService.updatePet(petId,request));
        return apiResponse;
    }
    //Xóa thú cưng của người dùng bằng id
    @DeleteMapping("/delete_pet/{petId}")
    String deletePet(@PathVariable long petId){
        petService.deletePet((petId));
        return "Đã xóa thành công thú cưng với ID: " + petId;
    }
}
