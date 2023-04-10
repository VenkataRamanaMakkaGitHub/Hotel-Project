package springproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springproject.entity.Room;

@Repository // Performing CRUD Operations
public interface RoomRepository extends JpaRepository<Room, Integer> {

	// retrieve all the entities where given roomType is matched
	List<Room> findByRoomType(String roomType);

	/*
	 * retrieve all the entities where given room type and bed type and status are
	 * matched
	 */

	@Query("SELECT r FROM Room r WHERE r.roomType = :roomType AND r.bedType = :bedType AND r.status = 'Available'")
	List<Room> findAvailableRoomsByTypeAndBedType(@Param("roomType") String roomType, @Param("bedType") String bedType);

	/*
	 * retrieve all the entities where given room number is matched
	 */
	Room findByRoomNumber(int roomNumber);

	List<Room> findByRoomTypeAndBedType(String roomType, String bedType);

	long countByRoomTypeAndBedTypeAndStatus(String roomType,String bedType,String status);

//	double findTariffByRoomTypeAndBedType(String roomType, String bedType);
	
	@Query("SELECT r.tariff FROM Room r WHERE r.roomType = :roomType AND r.bedType = :bedType")
   double getTariffByRoomTypeAndBedType(String roomType, String bedType);

}