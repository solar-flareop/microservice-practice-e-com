package com.solarflare.ecommerce.services;

import com.solarflare.ecommerce.dto.CustomerRequest;
import com.solarflare.ecommerce.dto.CustomerResponse;
import com.solarflare.ecommerce.entities.Customer;
import com.solarflare.ecommerce.exceptions.CustomerNotFoundException;
import com.solarflare.ecommerce.repositories.CustomerRepository;
import com.solarflare.ecommerce.utils.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.mapToCustomer(request);
        Customer savedCustomer = repository.save(customer);
        return savedCustomer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer  = repository.findById(request.id())
                .orElseThrow(()->new CustomerNotFoundException("Cannot update customer :: No customer found with id : " + request.id()));
        updateCustomerAndMergeData(customer,request);
        repository.save(customer);
    }

    private void updateCustomerAndMergeData(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address()!=null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll().stream()
                .map(mapper::mapToCustomerResponse)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::mapToCustomerResponse)
                .orElseThrow(() -> new CustomerNotFoundException("No customer found with id : " + customerId));
    }

    public void deleteCustomerById(String customerId) {
        boolean isCustomerPresent = repository.findById(customerId).isPresent();
        if (isCustomerPresent) {
            repository.deleteById(customerId);
        } else {
            throw new CustomerNotFoundException("No customer found with id : " + customerId);
        }
    }
}
