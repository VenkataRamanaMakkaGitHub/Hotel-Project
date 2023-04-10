package springproject.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import springproject.entity.BookingHistory;
import springproject.entity.Details;
import springproject.entity.UpcomingBooking;
import springproject.repository.BookingHistoryRepository;
import springproject.repository.CustomerRepository;
import springproject.repository.RoomRepository;
import springproject.repository.UpcomingBookingRepository;

@RestController // create Restful web services using Spring MVC
public class UpcomingBookingController {

	@Autowired // inject dependencies automatically
	UpcomingBookingRepository upcomingBookingRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BookingHistoryRepository bookingHistoryRepository;

	@Autowired
	RoomRepository roomRepository;

	// List of Upcoming Bookings
	@GetMapping("/listOfUpcomingBookings")
	@Operation(summary = "Get Upcoming Bookings list")
	public List<UpcomingBooking> getAllUpcomingBookings() {
		List<UpcomingBooking> upcomingBookingsList = upcomingBookingRepository.findAll();
		return upcomingBookingsList;
	}

	// List of upcoming bookings of a single customer
	@GetMapping("/upcomingBookingsOf/{customerId}")
	@Operation(summary = "Get upcoming Bookings Of a customer by customer id")
	public List<UpcomingBooking> getUpcomingBookingsOfCustomer(@PathVariable("customerId") int customerId) {
		List<UpcomingBooking> upcomingBookingsOfCustomer = upcomingBookingRepository.findByCustomerId(customerId);
		if (upcomingBookingsOfCustomer.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Id Not Found");
		}
		return upcomingBookingsOfCustomer;
	}

	
}
