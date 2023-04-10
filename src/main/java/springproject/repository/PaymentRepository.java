package springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springproject.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	
	
}
