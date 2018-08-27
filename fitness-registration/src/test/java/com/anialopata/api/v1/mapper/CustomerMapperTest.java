package com.anialopata.api.v1.mapper;

import com.anialopata.api.v1.model.CustomerDTO;
import com.anialopata.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ania on 2018-08-03.
 */
public class CustomerMapperTest {

    public static final String FIRSTNAME ="Ania";
    public static final String LASTNAME = "Lopata";
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception {

        Customer customer = new Customer();
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(FIRSTNAME, customerDTO.getFirstName());
        assertEquals(LASTNAME, customerDTO.getLastName());
    }

    @Test
    public void customerDTOToCustomer() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRSTNAME);
        customerDTO.setLastName(LASTNAME);

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        assertEquals(FIRSTNAME, customer.getFirstName());
        assertEquals(LASTNAME, customer.getLastName());
    }
}