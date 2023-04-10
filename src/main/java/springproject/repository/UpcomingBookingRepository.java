package springproject.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springproject.entity.UpcomingBooking;

@Repository
public interface UpcomingBookingRepository extends JpaRepository<UpcomingBooking, Integer> {

	// retrieve all the entities where given CustomerIdr is matched
	List<UpcomingBooking> findByCustomerId(int customerId);
	
	@Query("select SUM(ub.numberOfRooms) from UpcomingBooking ub where ub.roomType=:roomType and ub.bedType=:bedType and ub.startDate between :startDate and :endDate")
	Integer roomsGivenRange(@Param("roomType") String roomType,@Param ("bedType") String bedType, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
	
}