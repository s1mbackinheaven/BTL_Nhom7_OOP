package com.example.BTL_Nhom7_OOP.service;


import com.example.BTL_Nhom7_OOP.entity.Pet;
import com.example.BTL_Nhom7_OOP.dto.PetCreationRequest;
import com.example.BTL_Nhom7_OOP.dto.PetUpdateRequest;
import com.example.BTL_Nhom7_OOP.exception.ResourceNotFoundException;
import com.example.BTL_Nhom7_OOP.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    public Pet createPet(PetCreationRequest request){//Thêm thú cưng mới của người dùng
        Pet pet;
        pet=new Pet();
        pet.setName(request.getName());
        pet.setSpecie(request.getSpecie());
        pet.setBirthday(request.getBirthday());
        pet.setBiography(request.getBiography());
        return petRepository.save(pet);
    }
    public void deletePet(long petId){//Xóa thú cưng của người dùng bằng id
        petRepository.deleteById(petId);
    }
    public Pet updatePet(long petId, PetUpdateRequest request){//Sửa thông tin thú cưng của người dùng bằng id
        Pet pet=getPet(petId);
        pet.setName(request.getName());
        pet.setSpecie(request.getSpecie());
        pet.setBirthday(request.getBirthday());
        pet.setBiography(request.getBiography());
        return petRepository.save(pet);
    }
    public List<Pet> getPets(){//Liệt kê thông tin tất cả thú cưng của người dùng
        if(petRepository.findAll().isEmpty()) throw new ResourceNotFoundException("Không có thú cưng nào");
        return petRepository.findAll();
    }
    public Pet getPet(long id){//Lấy ra thông tin thú cưng của người dùng bằng id
        return petRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thú cưng"));
    }
}
