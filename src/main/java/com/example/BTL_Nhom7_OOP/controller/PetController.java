package com.example.BTL_Nhom7_OOP.controller;


import com.example.BTL_Nhom7_OOP.dto.*;
import com.example.BTL_Nhom7_OOP.entity.Customer;
import com.example.BTL_Nhom7_OOP.entity.Pet;
import com.example.BTL_Nhom7_OOP.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets/{customerId}")
public class PetController {
    @Autowired
    private PetService petService;

    //Thêm thú cưng mới (của người dùng có id là customerId)
    @PostMapping
    ApiResponse<Pet> createPet(@PathVariable long customerId,@RequestBody PetCreationRequest request){
        ApiResponse<Pet> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Thêm thành công");
        apiResponse.setResult(petService.createPet(customerId,request));
        return apiResponse;
    }

    //Liệt kê thông tin tất cả thú cưng (của người dùng có id là customerId)
    @GetMapping("/get_all_pet")
    List<Pet> getPets(@PathVariable long customerId){
        return petService.getPets(customerId);
    }

    //Lấy ra thông tin thú cưng (của người dùng có id là customerId) bằng petId
    @GetMapping("/get_pet/{petId}")
    Pet getPet(@PathVariable long customerId,@PathVariable("petId") long petId){
        return petService.getPetByIdFromList(getPets(customerId),petId);
    }

    //Sửa thông tin thú cưng (của người dùng có id là customerId) bằng petId
    @PutMapping("/update_infor_pet/{petId}")
    ApiResponse<Pet> updatePet(@PathVariable long customerId,@PathVariable long petId, @RequestBody PetUpdateRequest request){
        ApiResponse<Pet> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Sửa thành công");
        apiResponse.setResult(petService.updatePet(getPets(customerId),petId,request));
        return apiResponse;
    }

    //Xóa thú cưng (của người dùng có id là customerId) bằng petId
    @DeleteMapping("/delete_pet/{petId}")
    String deletePet(@PathVariable long customerId,@PathVariable long petId){
        petService.deletePet(getPets(customerId),petId);
        return "Đã xóa thành công thú cưng với ID: " + petId;
    }
}
