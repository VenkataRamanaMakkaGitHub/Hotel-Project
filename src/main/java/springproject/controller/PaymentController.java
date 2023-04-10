package springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import springproject.entity.Payment;
import springproject.repository.PaymentRepository;

@RestController
public class PaymentController {

	@Autowired
	PaymentRepository paymentRepository;
	
	@GetMapping("getAllPayments")
	@Operation(summary = "all paments list")
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}
	
	
	@PostMapping
	@Operation(summary = "checkOut And Payment")
	public void checkOutPayment(@PathVariable int roomNumber,@PathVariable String mobile  ) {
		
		
		
	}
	
	
}
