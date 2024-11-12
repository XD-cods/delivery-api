package com.vlad.userservice.web.controller;

import com.vlad.userservice.service.CustomerService;
import com.vlad.userservice.web.request.CustomerRequest;
import com.vlad.userservice.web.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping("/{id}")
  public ResponseEntity<CustomerResponse> findById(@PathVariable Long id) {
    return ResponseEntity.ok(customerService.getCustomer(id));
  }

  @GetMapping
  public ResponseEntity<List<CustomerResponse>> findAll() {
    return ResponseEntity.ok(customerService.getCustomers());
  }

  @PostMapping
  public ResponseEntity<CustomerResponse> createCustomer(CustomerRequest customerRequest) {
    return ResponseEntity.ok(customerService.createCustomer(customerRequest));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, CustomerRequest customerRequest) {
    return ResponseEntity.ok(customerService.updateCustomer(id, customerRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteCustomer(@PathVariable Long id) {
    return ResponseEntity.ok(customerService.deleteCustomer(id));
  }

}
