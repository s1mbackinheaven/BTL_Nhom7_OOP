package com.example.BTL_Nhom7_OOP.service;


import com.example.BTL_Nhom7_OOP.entity.Pet;
import com.example.BTL_Nhom7_OOP.repository.PetRepository;
import com.example.BTL_Nhom7_OOP.request.PetCreationRequest;
import com.example.BTL_Nhom7_OOP.request.PetUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    public Pet createPet(PetCreationRequest request){
        Pet pet;
        pet=new Pet();
        pet.setName(request.getName());
        pet.setSpecie(request.getSpecie());
        pet.setBirthday(request.getBirthday());
        pet.setBiography(request.getBiography());
        return petRepository.save(pet);
    }
    public void deletePet(String petId){
        petRepository.deleteById(petId);
    }
    public Pet updatePet(String petId, PetUpdateRequest request){
        Pet pet=getPet(petId);
        pet.setName(request.getName());
        pet.setSpecie(request.getSpecie());
        pet.setBirthday(request.getBirthday());
        pet.setBiography(request.getBiography());
        return petRepository.save(pet);
    }
    public List<Pet> getPets(){
        return petRepository.findAll();
    }
    public Pet getPet(String id){
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet not found"));
    }
}
