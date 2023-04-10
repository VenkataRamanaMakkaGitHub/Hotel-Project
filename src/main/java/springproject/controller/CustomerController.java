package springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import springproject.entity.Customer;
import springproject.repository.CustomerRepository;

@RestController // create Restful web services using Spring MVC
@CrossOrigin(origins = "http://localhost:9099") // enables cross-origin
public class CustomerController {

	@Autowired // inject dependencies automatically
	private CustomerRepository customerRepository;

	// List Of Customers
	@GetMapping("/listOfCustomers")
	@Operation(summary = "Get Customers List")
	public List<Customer> getAllCustomers() {
		List<Customer> customersList = customerRepository.findAll();
			return customersList;
		}

	}