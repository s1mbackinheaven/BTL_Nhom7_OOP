package com.example.BTL_Nhom7_OOP.controller;


import com.example.BTL_Nhom7_OOP.dto.CustomerCreationRequest;
import com.example.BTL_Nhom7_OOP.dto.CustomerUpdateRequest;
import com.example.BTL_Nhom7_OOP.entity.Customer;
import com.example.BTL_Nhom7_OOP.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping
    Customer createCustomer(@RequestBody CustomerCreationRequest request){
        return customerService.createCustomer(request);
    }
    @GetMapping
    List<Customer> getCustomers(){
        return customerService.getCustomers();
    }
    @GetMapping("/{customerId}")
    Customer getCustomer(@PathVariable("customerId") String customerId){
        return customerService.getCustomer(customerId);
    }
    @PutMapping("/{customerId}")
    Customer updateCustomer(@PathVariable String customerId, @RequestBody CustomerUpdateRequest request){
        return customerService.updateCustomer(customerId, request);
    }
    @DeleteMapping("/{customerId}")
    String deleteCustomer(@PathVariable String customerId){
        customerService.deleteCustomer((customerId));
        return "Customer has been deleted";
    }
}
