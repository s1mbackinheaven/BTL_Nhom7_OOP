package com.example.BTL_Nhom7_OOP.service;


import com.example.BTL_Nhom7_OOP.dto.request.CustomerUpdateRequest;
import com.example.BTL_Nhom7_OOP.entity.Customer;
import com.example.BTL_Nhom7_OOP.dto.request.CustomerCreationRequest;
import com.example.BTL_Nhom7_OOP.exception.ResourceNotFoundException;
import com.example.BTL_Nhom7_OOP.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    //Thêm khách hàng mới
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

    //Liệt kê thông tin tất cả khách hàng
    public List<Customer> getCustomers(){
        if(customerRepository.findAll().isEmpty()) throw new ResourceNotFoundException("Không có khách hàng nào");
        return customerRepository.findAll();
    }

    //Lấy ra thông tin khách hàng bằng id
    public Customer getCustomer(long id){
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng"));
    }

    //Sửa thông tin khách hàng bằng id
    public Customer updateCustomer(long customerId, CustomerUpdateRequest request){
        Customer customer=getCustomer(customerId);
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());
        customer.setEmail(request.getEmail());
        customer.setBirthday(request.getBirthday());
        return customerRepository.save(customer);
    }

    //Xóa khách hàng bằng id
    public void deleteCustomer(long customerId){
        customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng"));
        customerRepository.deleteById(customerId);
    }

}
