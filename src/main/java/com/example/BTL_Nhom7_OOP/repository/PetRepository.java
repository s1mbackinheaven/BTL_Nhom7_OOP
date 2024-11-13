package com.example.BTL_Nhom7_OOP.repository;

import com.example.BTL_Nhom7_OOP.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
