package com.vlad.userservice.service;

import com.vlad.userservice.exception.NotFoundException;
import com.vlad.userservice.persistence.entity.Customer;
import com.vlad.userservice.persistence.repository.CustomerRepository;
import com.vlad.userservice.util.mapper.CustomerMapper;
import com.vlad.userservice.web.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  private static final String NOT_FOUND_MESSAGE = "Customer not found by id: %d";

  CustomerResponse getCustomer(Long customerId) {
    Customer customer = customerRepository.findById(customerId)
            .orElseThrow(()-> new NotFoundException(String.format(NOT_FOUND_MESSAGE, customerId)));

    return customerMapper.customerToCustomerResponse(customer);
  }

}
