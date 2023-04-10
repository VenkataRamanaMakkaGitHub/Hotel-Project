package springproject.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import springproject.entity.BookingHistory;
import springproject.entity.BookingHistoryy;
import springproject.entity.Customer;
import springproject.entity.Details;
import springproject.entity.Room;
import springproject.entity.UpcomingBooking;
import springproject.repository.BookingHistoryRepository;
import springproject.repository.BookingHistoryRepositoryy;
import springproject.repository.CustomerRepository;
import springproject.repository.RoomRepository;
import springproject.repository.UpcomingBookingRepository;

@RestController // create Restful web services using Spring MVC
//@PreAuthorize("hasRole('ADMIN')") // Only admin can access BookingHistories
public class BookingHistoryController {

	@Autowired // inject dependencies automatically
	BookingHistoryRepository bookingHistoryRepository;

	@Autowired
	BookingHistoryRepositoryy bookingHistoryRepositoryy;

	@Autowired
	UpcomingBookingRepository upcomingBookingRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	CustomerRepository customerRepository;

	// List Of all Booking Histories
	@GetMapping("/allBookingHistories")
	@Operation(summary = "Get Booking History List")
	public List<BookingHistory> getAllBookingHistories() {
		List<BookingHistory> roomList = bookingHistoryRepository.findAll();
		return roomList;
	}

	// Booking history of a customer
	@GetMapping("/bookingHistoryOf/{customerId}")
	@Operation(summary = "Get Booking history of a customer by customer id")
	public List<BookingHistory> getBookingHistoryOfACustomer(@PathVariable("customerId") int customerId) {
		List<BookingHistory> bookingHystoryList = bookingHistoryRepository.findByCustomerId(customerId);
		if (bookingHystoryList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Not Found!");
		} else
			return bookingHystoryList;
	}

	// Add new row to booking histories table
	@PostMapping("/addNewBookingHistory/{id}/{customerId}/{roomNumber}/{startDate}/{endDate}")
	@Operation(summary = "Add a new Booking History")
	public String setBookingHistory(@PathVariable int id, @PathVariable int customerId, @PathVariable int roomNumber,
			@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
		try {

			BookingHistoryy bookingObj = new BookingHistoryy();
			bookingObj.setId(id);
			bookingObj.setCustomerId(customerId);
			bookingObj.setRoomNumber(roomNumber);
			bookingObj.setStartDate(startDate);
			bookingObj.setEndDate(endDate);
			bookingHistoryRepositoryy.save(bookingObj);
			return "Done!!";
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data!");
		}
	}

	// Handle check out by room number and update room status
	@PostMapping("/handleCheckOut/{roomNumber}")
	public String handleCheckOut(Integer roomNumber) {
		BookingHistory bookingHistory = bookingHistoryRepository.displayRoomNumber(roomNumber);
		if (bookingHistory == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room Not Found!");
		}
		Optional<Room> room = roomRepository.findById(roomNumber);
		if (room.isPresent()) {
			Room roomObject = room.get();
			if (roomObject.getStatus().equalsIgnoreCase("Available")) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room Status Is Alredy Available!");
			} else {
				roomObject.setStatus("Available");
				bookingHistory.setEndDate(LocalDate.now());
				bookingHistoryRepository.save(bookingHistory);
				roomRepository.save(roomObject);
				return "job done!!";
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room Number Not Found!");
		}

	}


	private void bookingHistoryEntry(Customer customer, Room oneRoom, LocalDate bookingDate) {
		BookingHistory bookingHistoryObj = new BookingHistory();
		bookingHistoryObj.setCustomer(customer);
		bookingHistoryObj.setRoom(oneRoom);
		bookingHistoryObj.setStartDate(bookingDate);
		bookingHistoryObj.setEndDate(null);
		oneRoom.setStatus("Occupied");
		bookingHistoryRepository.save(bookingHistoryObj);
		roomRepository.save(oneRoom);
	}

	private void upcomingBookingEntry(Customer customer, LocalDate bookingDate, String roomType, String bedType) {
		UpcomingBooking upcomingBooking = new UpcomingBooking();
		upcomingBooking.setCustomer(customer);
		upcomingBooking.setStartDate(bookingDate);
		upcomingBooking.setRoomType(roomType);
		upcomingBooking.setBedType(bedType);
		upcomingBooking.setNumberOfRooms(1);
		upcomingBookingRepository.save(upcomingBooking);
	}

	private Details finalBill(int customerId, List<LocalDate> visitDates, double discount, double tariff) {
		Details details = new Details();
		details.setCustomerId(customerId);
		details.setVisitDates(visitDates);
		details.setDiscount(discount);
		details.setTotalBill(tariff - discount);
		return details;
	}

	@PostMapping("/newBooking/{mobile}/{bookingDate}/{stayDays}/{roomType}/{bedType}")
	public ResponseEntity<?> customerDiscounts(@PathVariable String mobile, @PathVariable LocalDate bookingDate,
			@PathVariable int stayDays, @PathVariable String roomType, @PathVariable String bedType) {

		LocalDate today = LocalDate.now();
		if (stayDays <= 0 || !mobile.matches("\\d{10}") || bookingDate.isBefore(today)) {
          return ResponseEntity.badRequest().body("Invalid Input");
		
		}
		List<Room> roomsList = roomRepository.findAvailableRoomsByTypeAndBedType(roomType, bedType);
		if (roomsList.isEmpty()) {
	          return ResponseEntity.badRequest().body("No available rooms found!");
		}
		// take one room from room list
		Room oneRoom = roomsList.get(0);

		//discount calculation
		Double tariff = oneRoom.getTariff();
		double p1 = 0.05, p2 = 0.10, p3 = 0.15;
		double discount = 0.0;
		double consecutiveDiscount = (tariff * stayDays) * p3;
		double diffrentRoomBedDiscount = (tariff * stayDays) * p2;
		double additionalDiscount = (tariff * stayDays) * p1;

		Optional<Customer> customer = customerRepository.findByMobile(mobile);
		if (customer.isPresent()) {
			int customerId = customer.get().getId();
			List<LocalDate> visitDates = bookingHistoryRepository.getStartDateByCustomerId(customerId);
			List<BookingHistory> customerBookingHistory = bookingHistoryRepository.findByCustomerId(customerId);

			if (bookingDate.isAfter(today)) {
				upcomingBookingEntry(customer.get(), bookingDate, roomType, bedType);
			}
			if (bookingDate.isEqual(today)) {
				bookingHistoryEntry(customer.get(), oneRoom, bookingDate);
			}
			if (visitDates.size() > 2) {
				if (!customerBookingHistory.isEmpty()) {
					BookingHistory consecutive = customerBookingHistory.get(customerBookingHistory.size() - 1);
					if (consecutive.getRoom().getRoomType().equals(roomType)
							&& consecutive.getRoom().getBedType().equals(bedType)) {
						discount = consecutiveDiscount;
					}
				}
				if (stayDays > 2) {
					discount += additionalDiscount;
				} else {
					discount = diffrentRoomBedDiscount;
					if (stayDays > 2) {
						discount += additionalDiscount;
					}
				}
				return ResponseEntity.ok(finalBill(customerId, visitDates, discount, tariff));
			} else {
				return  ResponseEntity.ok(finalBill(customerId, visitDates, discount, tariff));
			}
		} else {
			Customer customerObj = new Customer();
			customerObj.setMobile(mobile);
			customerObj.setName("Customer Name");
			customerObj.setEmail("Customer@gmail.com");
			customerObj.setGender("other");
			Customer cust = customerRepository.save(customerObj);

			if (bookingDate.isAfter(today)) {
				upcomingBookingEntry(cust, bookingDate, roomType, bedType);
			} else {
				bookingHistoryEntry(cust, oneRoom, bookingDate);
			}
			return ResponseEntity.ok(finalBill(cust.getId(), null, discount, tariff));
		}
	}
}
