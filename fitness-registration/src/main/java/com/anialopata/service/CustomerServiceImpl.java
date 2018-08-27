package com.anialopata.service;

import com.anialopata.api.v1.mapper.CustomerMapper;
import com.anialopata.api.v1.mapper.ExerciseMapper;
import com.anialopata.api.v1.model.CustomerDTO;
import com.anialopata.api.v1.model.ExerciseDTO;
import com.anialopata.domain.Customer;
import com.anialopata.domain.Exercise;
import com.anialopata.repository.CustomerRepository;
import com.anialopata.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Ania on 2018-07-22.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository
                .findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(RuntimeException::new);
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);
        returnDto.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());

        return returnDto;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Set<ExerciseDTO> findCustomerExercises(Long customerId) {
       CustomerDTO customerDTO = getCustomerById(customerId);
       Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
       CustomerDTO returned = customerMapper.customerToCustomerDTO(customer);

       return returned.getExercises();
    }

    @Override
    public ExerciseDTO addExercise(Long customerId, ExerciseDTO exerciseDTO) {
        Customer customer = customerRepository.getOne(customerId);
        if (customer.getExercises() == null) {
            customer.setExercises(new HashSet<>());
        }
        Exercise ex = exerciseMapper.exerciseDTOToExercise(exerciseDTO);
        exerciseRepository.save(ex);
        customer.getExercises().add(ex);
        customerRepository.save(customer);
        ExerciseDTO returned = exerciseMapper.exerciseToExerciseDTO(ex);

        return returned;

    }

}
