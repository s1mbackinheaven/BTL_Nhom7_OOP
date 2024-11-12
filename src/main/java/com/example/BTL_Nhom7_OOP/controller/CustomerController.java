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
    Customer createCustomer(@RequestBody CustomerCreationRequest request){//Thêm khách hàng mới
        return customerService.createCustomer(request);
    }
    @GetMapping
    List<Customer> getCustomers(){//Liệt kê thông tin tất cả khách hàng
        return customerService.getCustomers();
    }
    @GetMapping("/{customerId}")
    Customer getCustomer(@PathVariable("customerId") long customerId){//Lấy ra thông tin khách hàng bằng id
        return customerService.getCustomer(customerId);
    }
    @PutMapping("/{customerId}")
    Customer updateCustomer(@PathVariable long customerId, @RequestBody CustomerUpdateRequest request){//Sửa thông tin khách hàng bằng id
        return customerService.updateCustomer(customerId, request);
    }
    @DeleteMapping("/{customerId}")
    String deleteCustomer(@PathVariable long customerId){//Xóa khách hàng bằng id
        customerService.deleteCustomer((customerId));
        return "Đã xóa thành công khách hàng với ID: " + customerId;
    }
}
