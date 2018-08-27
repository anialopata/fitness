package com.anialopata.repository;

import com.anialopata.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ania on 2018-07-22.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
