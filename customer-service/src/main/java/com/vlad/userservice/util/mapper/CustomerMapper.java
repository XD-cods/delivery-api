package com.vlad.userservice.util.mapper;

import com.vlad.userservice.persistence.entity.Customer;
import com.vlad.userservice.web.request.CustomerRequest;
import com.vlad.userservice.web.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

  private final ModelMapper modelMapper;

  public Customer mapCustomerRequestToCustomer(CustomerRequest customerRequest) {
    return modelMapper.map(customerRequest, Customer.class);
  }

  public CustomerResponse mapCustomerToCustomerResponse(Customer customer) {
    return modelMapper.map(customer, CustomerResponse.class);
  }

  public void updateExistCustomer(Customer customer, CustomerRequest customerRequest) {
    modelMapper.map(customer, customerRequest);
  }

}
