package com.vlad.userservice.service;

import com.vlad.userservice.exception.ConflictException;
import com.vlad.userservice.exception.NotFoundException;
import com.vlad.userservice.persistence.entity.Customer;
import com.vlad.userservice.persistence.repository.CustomerRepository;
import com.vlad.userservice.util.mapper.CustomerMapper;
import com.vlad.userservice.web.request.CustomerRequest;
import com.vlad.userservice.web.response.CustomerResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  private static final String NOT_FOUND_MESSAGE = "Customer not found by id: %d";
  public static final String CONFLICT_MESSAGE = "Customer with id: %s already exists";

  @Transactional
  public CustomerResponse getCustomer(Long customerId) {
    Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE, customerId)));

    return customerMapper.customerToCustomerResponse(customer);
  }

  @Transactional
  public List<CustomerResponse> getCustomers() {
    return customerRepository
            .findAll()
            .stream()
            .map(customerMapper::customerToCustomerResponse)
            .toList();
  }

  @Transactional
  public CustomerResponse createCustomer(CustomerRequest customerRequest) {
    Customer customer = customerMapper.customerRequestToCustomer(customerRequest);

    Optional<Customer> optionalCustomer = customerRepository.findByEmail(customer.getEmail());
    if (optionalCustomer.isPresent()) {
      throw new ConflictException(String.format(CONFLICT_MESSAGE, customer.getEmail()));
    }

    customerRepository.save(customer);
    return customerMapper.customerToCustomerResponse(customer);
  }

  @Transactional
  public CustomerResponse updateCustomer(Long customerId, CustomerRequest customerRequest) {
    customerRepository.findById(customerId)
            .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE, customerId)));

    Customer customer = customerMapper.customerRequestToCustomer(customerRequest);
    customerRepository.save(customer);
    return customerMapper.customerToCustomerResponse(customer);
  }

  @Transactional
  public Boolean deleteCustomer(Long customerId) {
    customerRepository.findById(customerId)
                    .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE, customerId)));
    customerRepository.deleteById(customerId);
    return true;
  }
}
