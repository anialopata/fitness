package com.anialopata.service;

import com.anialopata.api.v1.model.CustomerDTO;
import com.anialopata.api.v1.model.ExerciseDTO;
import com.anialopata.domain.Customer;

import java.util.List;
import java.util.Set;

/**
 * Created by Ania on 2018-07-22.
 */
public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);
    void deleteCustomer(Long id);
    Set<ExerciseDTO> findCustomerExercises(Long id);
    ExerciseDTO addExercise(Long customerId, ExerciseDTO exercise);


}
