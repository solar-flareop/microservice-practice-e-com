package com.solarflare.ecommerce.controllers;

import com.solarflare.ecommerce.dto.CustomerRequest;
import com.solarflare.ecommerce.dto.CustomerResponse;
import com.solarflare.ecommerce.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request){
        String response = service.createCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public  ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request){
        service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        List<CustomerResponse> response = service.findAllCustomers();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("customer-id") String customerId) {
        Boolean response = service.existsById(customerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") String customerId) {
        CustomerResponse response = service.findById(customerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") String customerId) {
        service.deleteCustomerById(customerId);
        return ResponseEntity.accepted().build();
    }
}
