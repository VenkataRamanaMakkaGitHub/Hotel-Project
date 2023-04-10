package springproject.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springproject.entity.BookingHistory;

@Repository
public interface BookingHistoryRepository extends JpaRepository<BookingHistory, Integer> {

	// retrieve all the entities where given CustomerIdr is matched
	List<BookingHistory> findByCustomerId(int customerId);

	// retrieve all the entities where given room number is matched and end date is
	// null
	@Query("from BookingHistory where room.roomNumber=:roomNumber AND endDate=null")
	BookingHistory displayRoomNumber(@Param("roomNumber") Integer roomNumber);

	@Query("SELECT bh FROM BookingHistory bh WHERE bh.customer.id = :customerId AND bh.room.roomType = :roomType AND bh.room.bedType = :bedType")
	List<BookingHistory> getdByCustomerIdAndRoomTypeAndBedType(int customerId, String roomType, String bedType);

	// Custom method to get the start dates list by customer id
	@Query("Select startDate from BookingHistory where customer.id = :customerId")
	List<LocalDate> getStartDateByCustomerId(int customerId);

	int countByCustomerId(int customerId);

//	List<BookingHistory> findByCustomerIdAndRoomTypeAndBedType(int customerId, String roomType, String bedType);

}
