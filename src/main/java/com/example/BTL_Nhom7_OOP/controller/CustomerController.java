package com.example.BTL_Nhom7_OOP.controller;


import com.example.BTL_Nhom7_OOP.dto.CustomerCreationRequest;
import com.example.BTL_Nhom7_OOP.dto.CustomerUpdateRequest;
import com.example.BTL_Nhom7_OOP.entity.Customer;
import com.example.BTL_Nhom7_OOP.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    //Thêm khách hàng mới
    @PostMapping
    Customer createCustomer(@RequestBody CustomerCreationRequest request){
        return customerService.createCustomer(request);
    }
    //Liệt kê thông tin tất cả khách hàng
    @GetMapping("/get_all_customer")
    List<Customer> getCustomers(){
        return customerService.getCustomers();
    }
    //Lấy ra thông tin khách hàng bằng id
    @GetMapping("/get_customer/{customerId}")
    Customer getCustomer(@PathVariable("customerId") long customerId){
        return customerService.getCustomer(customerId);
    }
    //Sửa thông tin khách hàng bằng id
    @PutMapping("/update_infor_customer/{customerId}")
    Customer updateCustomer(@PathVariable long customerId, @RequestBody CustomerUpdateRequest request){
        return customerService.updateCustomer(customerId, request);
    }
    //Xóa khách hàng bằng id
    @DeleteMapping("/delete_customer/{customerId}")
    String deleteCustomer(@PathVariable long customerId){
        customerService.deleteCustomer((customerId));
        return "Đã xóa thành công khách hàng với ID: " + customerId;
    }
}
