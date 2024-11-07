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

  public Customer customerRequestToCustomer(CustomerRequest customerRequest) {
    return modelMapper.map(customerRequest, Customer.class);
  }

  public CustomerResponse customerToCustomerResponse(Customer customer) {
    return modelMapper.map(customer, CustomerResponse.class);
  }


}
