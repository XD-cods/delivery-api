package com.vlad.userservice.service;

import com.vlad.userservice.exception.ConflictException;
import com.vlad.userservice.exception.NotFoundException;
import com.vlad.userservice.persistence.entity.Customer;
import com.vlad.userservice.persistence.repository.CustomerRepository;
import com.vlad.userservice.util.mapper.CustomerMapper;
import com.vlad.userservice.web.request.CustomerRequest;
import com.vlad.userservice.web.response.CustomerResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

  @Mock
  private CustomerRepository customerRepository;

  @Spy
  private CustomerMapper customerMapper = new CustomerMapper(new ModelMapper());

  @InjectMocks
  private CustomerService customerService;

  @Test
  void getCustomer_ReturnCustomerTest() {
    Long customerId = 1L;
    Customer customer = new Customer();
    when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

    CustomerResponse customerResponse = customerService.getCustomer(customerId);

    Assertions.assertEquals(customer.getName(), customerResponse.getName());
    Assertions.assertEquals(customer.getEmail(), customerResponse.getEmail());
    Assertions.assertEquals(customer.getPhone(), customerResponse.getPhone());
  }

  @Test
  void getCustomer_throwNotFoundExceptionTest() {
    long customerId = 1L;
    Customer customer = new Customer();
    when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> customerService.getCustomer(customerId));
  }

  @Test
  void createCustomer_ReturnCustomerTest() {
    Long customerId = 1L;
    Customer savedCustomer = createCustomerForTest();
    when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(savedCustomer);

    CustomerResponse customerResponse = customerService.createCustomer(createCustomerRequestForTest());

    Assertions.assertEquals(savedCustomer.getName(), customerResponse.getName());
    Assertions.assertEquals(savedCustomer.getEmail(), customerResponse.getEmail());
    Assertions.assertEquals(savedCustomer.getPhone(), customerResponse.getPhone());
  }

  @Test
  void createCustomer_ThrowConflictExceptionTest() {
    Customer customer = createCustomerForTest();
    CustomerRequest customerRequest = createCustomerRequestForTest();

    when(customerRepository.findByEmail(customer.getEmail())).thenReturn(Optional.of(customer));

    Assertions.assertThrows(ConflictException.class, () -> customerService.createCustomer(customerRequest));
  }


  @Test
  void updateCustomer_ReturnUpdatedCustomerTest() {
    Long customerId = 1L;
    CustomerRequest customerRequest = createCustomerRequestForTest();
    customerRequest.setName("updatedName");
    Customer existingCustomer = createCustomerForTest();
    Customer updatedCustomer = createCustomerForTest();
    updatedCustomer.setName("updatedName");

    when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
    when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(updatedCustomer);

    CustomerResponse customerResponse = customerService.updateCustomer(customerId, customerRequest);

    Assertions.assertEquals(updatedCustomer.getName(), customerResponse.getName());
    Assertions.assertEquals(updatedCustomer.getEmail(), customerResponse.getEmail());
    Assertions.assertEquals(updatedCustomer.getPhone(), customerResponse.getPhone());
  }

  @Test
  void updateCustomer_throwNotFoundExceptionTest() {
    Long customerId = 1L;
    CustomerRequest customerRequest = createCustomerRequestForTest();

    when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> customerService.updateCustomer(customerId, customerRequest));
  }

  @Test
  void deleteCustomer_ReturnTrueTest() {
    Long customerId = 1L;
    Customer existingCustomer = createCustomerForTest();

    when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));

    Boolean result = customerService.deleteCustomer(customerId);

    Assertions.assertTrue(result);
    Mockito.verify(customerRepository, Mockito.times(1)).deleteById(customerId);
  }

  @Test
  void deleteCustomer_throwNotFoundExceptionTest() {
    Long customerId = 1L;

    when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> customerService.deleteCustomer(customerId));
  }


  private Customer createCustomerForTest() {
    return Customer.builder()
            .name("test")
            .email("test@email.ru")
            .phone("+37533221221")
            .build();
  }

  private CustomerRequest createCustomerRequestForTest() {
    return CustomerRequest.builder()
            .name("test")
            .email("test@email.ru")
            .phone("+37533221221")
            .build();
  }
}