package com.example.BTL_Nhom7_OOP.controller;


import com.example.BTL_Nhom7_OOP.dto.response.ApiResponse;
import com.example.BTL_Nhom7_OOP.dto.request.CustomerCreationRequest;
import com.example.BTL_Nhom7_OOP.dto.request.CustomerUpdateRequest;
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
    ApiResponse<Customer> createCustomer(@RequestBody CustomerCreationRequest request){
        ApiResponse<Customer> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Thêm thành công");
        apiResponse.setResult(customerService.createCustomer(request));
        return apiResponse;
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
    ApiResponse<Customer> updateCustomer(@PathVariable long customerId, @RequestBody CustomerUpdateRequest request){
        ApiResponse<Customer> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Sửa thành công");
        apiResponse.setResult(customerService.updateCustomer(customerId,request));
        return apiResponse;
    }

    //Xóa khách hàng bằng id
    @DeleteMapping("/delete_customer/{customerId}")
    String deleteCustomer(@PathVariable long customerId){
        customerService.deleteCustomer((customerId));
        return "Đã xóa thành công khách hàng với ID: " + customerId;
    }
}
