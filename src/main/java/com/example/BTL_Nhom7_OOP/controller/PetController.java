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
    Pet createPet(@RequestBody PetCreationRequest request){
        return petService.createPet(request);
    }
    @GetMapping
    List<Pet> getPets(){
        return petService.getPets();
    }
    @GetMapping("/{petId}")
    Pet getPet(@PathVariable("petId") String petId){
        return petService.getPet(petId);
    }
    @PutMapping("/{petId}")
    Pet updatePet(@PathVariable String petId, @RequestBody PetUpdateRequest request){
        return petService.updatePet(petId, request);
    }
    @DeleteMapping("/{petId}")
    String deletePet(@PathVariable String petId){
        petService.deletePet((petId));
        return "Pet has been deleted";
    }
}
