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
    public Customer createCustomer(CustomerCreationRequest request){//Thêm khách hàng mới
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
    public void deleteCustomer(long customerId){//Xóa khách hàng bằng id
        customerRepository.deleteById(String.valueOf(customerId));
    }
    public Customer updateCustomer(long customerId, CustomerUpdateRequest request){//Sửa thông tin khách hàng bằng id
        Customer customer=getCustomer(customerId);
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());
        customer.setEmail(request.getEmail());
        customer.setBirthday(request.getBirthday());
        return customerRepository.save(customer);
    }
    public List<Customer> getCustomers(){//Liệt kê thông tin tất cả khách hàng
        return customerRepository.findAll();
    }
    public Customer getCustomer(long id){//Lấy ra thông tin khách hàng bằng id
        return customerRepository.findById(String.valueOf(id)).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
