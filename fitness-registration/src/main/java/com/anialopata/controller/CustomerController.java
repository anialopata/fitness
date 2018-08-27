package com.anialopata.controller;

import com.anialopata.api.v1.model.CustomerDTO;
import com.anialopata.api.v1.model.CustomerListDTO;
import com.anialopata.api.v1.model.ExerciseDTO;
import com.anialopata.exception.FitnessException;
import com.anialopata.exception.TokenException;
import com.anialopata.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Ania on 2018-07-22.
 */
@Api(description = "Customer Controller")
@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "List all Customers")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getCustomers(){
        return new CustomerListDTO(customerService.getAllCustomers());
    }

    @ApiOperation(value = "Get Customer by ID value")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @ApiOperation(value = "Create new Customer")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.createCustomer(customerDTO);
    }

    @ApiOperation(value = "Update existing Customer")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return customerService.saveCustomerByDTO(id, customerDTO);
    }

    @ApiOperation(value = "Delete Customer")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }

    @ApiOperation(value = "List all Exercises for Customer")
    @GetMapping("/{customerId}/exercises")
    public Set<ExerciseDTO> findCustomerExercises(@PathVariable Long customerId) {
        return customerService.findCustomerExercises(customerId);
    }



    @ApiOperation(value = "Add Exercise to Customer")
    @PostMapping("/dupa")
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseDTO addExercise(){
        throw new TokenException("dupa");

    }
}
