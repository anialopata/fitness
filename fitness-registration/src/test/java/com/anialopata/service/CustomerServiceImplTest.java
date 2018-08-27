package com.anialopata.service;

import com.anialopata.api.v1.mapper.CustomerMapper;
import com.anialopata.api.v1.mapper.ExerciseMapper;
import com.anialopata.api.v1.model.CustomerDTO;
import com.anialopata.api.v1.model.ExerciseDTO;
import com.anialopata.domain.Customer;
import com.anialopata.domain.Exercise;
import com.anialopata.repository.CustomerRepository;
import com.anialopata.repository.ExerciseRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Ania on 2018-08-01.
 */
public class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerService customerService;
    ExerciseMapper exerciseMapper = ExerciseMapper.INSTANCE;
    ExerciseRepository exerciseRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, customerMapper, exerciseRepository, exerciseMapper);
    }

    @Test
    public void getAllCustomers() throws Exception {

        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setFirstName("Anna");
        customer1.setLastName("Lopata");

        Customer customer2 = new Customer();
        customer2.setId(2l);
        customer2.setFirstName("Adam");
        customer2.setLastName("Dyrek");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();

        assertEquals(2, customerDTOList.size());
    }

    @Test
    public void getCustomerById() throws Exception {

        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setFirstName("Anna");
        customer1.setLastName("Lopata");

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer1));

        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        assertEquals("Anna", customerDTO.getFirstName());
    }

    @Test
    public void createNewCustomer() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Adam");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDTO savedDTO = customerService.saveCustomerByDTO(1L, customerDTO);

        assertEquals(customerDTO.getFirstName(), savedDTO.getFirstName());
        assertEquals("/api/v1/customer/1",savedDTO.getCustomerUrl());

    }

    @Test
    public void deleteCustomerById() throws Exception {

        Long id = 1L;

        customerRepository.deleteById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }

}