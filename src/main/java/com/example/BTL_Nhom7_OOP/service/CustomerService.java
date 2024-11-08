package com.example.BTL_Nhom7_OOP.service;


import com.example.BTL_Nhom7_OOP.dto.CustomerUpdateRequest;
import com.example.BTL_Nhom7_OOP.entity.Customer;
import com.example.BTL_Nhom7_OOP.dto.CustomerCreationRequest;
import com.example.BTL_Nhom7_OOP.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public Customer createCustomer(CustomerCreationRequest request){
        Customer customer;
        customer=new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());
        customer.setEmail(request.getEmail());
        customer.setBirthday(request.getBirthday());
        return customerRepository.save(customer);
    }
    public void deleteCustomer(String customerId){
        customerRepository.deleteById(customerId);
    }
    public Customer updateCustomer(String customerId, CustomerUpdateRequest request){
        Customer customer=getCustomer(customerId);
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());
        customer.setEmail(request.getEmail());
        customer.setBirthday(request.getBirthday());
        return customerRepository.save(customer);
    }
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    public Customer getCustomer(String id){
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
