package com.example.BTL_Nhom7_OOP.service;


import com.example.BTL_Nhom7_OOP.entity.Customer;
import com.example.BTL_Nhom7_OOP.entity.Pet;
import com.example.BTL_Nhom7_OOP.dto.request.PetCreationRequest;
import com.example.BTL_Nhom7_OOP.dto.request.PetUpdateRequest;
import com.example.BTL_Nhom7_OOP.exception.ResourceNotFoundException;
import com.example.BTL_Nhom7_OOP.repository.CustomerRepository;
import com.example.BTL_Nhom7_OOP.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;

    //Thêm thú cưng mới (của người dùng có id là customerId)
    public Pet createPet(long customerId,PetCreationRequest request){
        Pet pet;
        pet=new Pet();
        pet.setName(request.getName());
        pet.setSpecie(request.getSpecie());
        pet.setBirthday(request.getBirthday());
        pet.setBiography(request.getBiography());
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Khách hàng không tồn tại"));
        pet.setCustomer(customer);
        return petRepository.save(pet);
    }

    //Lấy ra thông tin thú cưng bằng id
    public Pet getPet(long id){
        return petRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thú cưng"));
    }

    //Liệt kê thông tin tất cả thú cưng (của người dùng có id là customerId)
    public List<Pet> getPets(long customerId){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Khách hàng không tồn tại"));
        if(petRepository.findByCustomerId(customerId).isEmpty()) throw new ResourceNotFoundException("Không có thú cưng nào");
        return petRepository.findByCustomerId(customerId);
    }

    //Lấy ra thông tin thú cưng (của người dùng có id là customerId) bằng petId
    public Pet getPetByIdFromList(List<Pet> pets, long petId) {
        return pets.stream()
                .filter(pet -> pet.getId() == petId)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thú cưng")); // Hoặc throw một exception tùy theo yêu cầu
    }

    //Sửa thông tin thú cưng (của người dùng có id là customerId) bằng petId
    public Pet updatePet(List<Pet> pets, long petId, PetUpdateRequest request){
        for (Pet pet : pets){
            if (pet.getId() == petId){
                pet.setName(request.getName());
                pet.setSpecie(request.getSpecie());
                pet.setBirthday(request.getBirthday());
                pet.setBiography(request.getBiography());
                return petRepository.save(pet);
            }
        }
        throw new ResourceNotFoundException("Không tìm thấy thú cưng");
    }

    //Xóa thú cưng (của người dùng có id là customerId) bằng petId
    public void deletePet(List<Pet> pets, long petId){
        for (Pet pet : pets) {
            if (pet.getId() == petId) {
                petRepository.deleteById(petId);
                return;
            }
        }
        throw new ResourceNotFoundException("Không tìm thấy thú cưng");
    }

}
