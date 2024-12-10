package com.solarflare.ecommerce.utils;

import com.solarflare.ecommerce.dto.CustomerRequest;
import com.solarflare.ecommerce.dto.CustomerResponse;
import com.solarflare.ecommerce.entities.Address;
import com.solarflare.ecommerce.entities.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer mapToCustomer(CustomerRequest request) {
        if(request==null) return null;
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse mapToCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
