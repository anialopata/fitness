package com.anialopata;

import com.anialopata.domain.Customer;
import com.anialopata.domain.Exercise;
import com.anialopata.repository.CustomerRepository;
import com.anialopata.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;


@SpringBootApplication
public class FitnessRegistrationApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ExerciseRepository exerciseRepository;

	public static void main(String[] args) {
		SpringApplication.run(FitnessRegistrationApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {


		Exercise ex1 = new Exercise();
		ex1.setName("Turbo spalanie");
		ex1.setDate(LocalDateTime.now());

		Exercise ex2 = new Exercise();
		ex2.setName("Killer");
		ex2.setDate(LocalDateTime.now().minusHours(3L));

		exerciseRepository.save(ex1);
		exerciseRepository.save(ex2);

		Customer customer = new Customer();
		customer.setFirstName("Ania");
		customer.setLastName("Lopata");
		customer.setUsername("anialopata");
		customer.setPhoneNumber("510-540-155");

		customerRepository.save(customer);
	}
}
