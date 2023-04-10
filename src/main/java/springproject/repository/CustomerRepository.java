package springproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springproject.entity.Customer;

@Repository // Performing CRUD Operations
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// @Query("from Customer where mobile=:mobile")
	Optional<Customer> findByMobile(@Param("mobile") String mobile);

	@Query("SELECT MAX(c.id) FROM Customer c")
	Integer findLastCustomerId();

	Customer save(Customer customerObj);

}