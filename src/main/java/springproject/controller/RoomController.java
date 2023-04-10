package springproject.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import springproject.entity.Room;
import springproject.repository.RoomRepository;
import springproject.repository.UpcomingBookingRepository;

@RestController // create Restful web services using Spring MVC
public class RoomController {

	@Autowired // inject dependencies automatically
	private RoomRepository roomRepository;

	@Autowired
	private UpcomingBookingRepository upcomingBookingRepository;

	// List of rooms
	@GetMapping("/listOfRooms")
	@Operation(summary = "Get list of all rooms")
	public List<Room> getAllRooms() {
		List<Room> roomsList = roomRepository.findAll();
		return roomsList;
	}

	// List of rooms by room type
	@GetMapping("/roomsType/{roomType}")
	@Operation(summary = "Get rooms by room type")
	public List<Room> getRoomsByRoomType(@PathVariable String roomType) {
		List<Room> roomsList = roomRepository.findByRoomType(roomType);

		if (roomsList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room Type Not Found");
		} else {
			return roomsList;
		}

	}

	// List of rooms by room type, bed type, status
	@GetMapping("/requirements/{roomType}/{bedType}")
	@Operation(summary = "Get rooms by room type and bed type  Available ) ")
	public List<Room> getAvailableRoomsByRoomTypeAndBedType(@PathVariable String roomType,
			@PathVariable String bedType) {
		List<Room> roomsList = roomRepository.findAvailableRoomsByTypeAndBedType(roomType, bedType);
		if (roomsList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data Available");
		} else {
			return roomsList;
		}

	}

	// Change the status of a room by room number
	@PutMapping("/changeRoomStatus/{roomNumber}/{status}")
	@Operation(summary = "Update room status by room number and status ")
	public String changeRoomStatus(@PathVariable int roomNumber, @PathVariable String status) {
		Room room = roomRepository.findByRoomNumber(roomNumber);
		if (room == null) {
			return "Room Number Not Found";
		}
		if (status.equalsIgnoreCase(room.getStatus())) {
			return "The Room Status Is Already  " + status;
		} else {
			room.setStatus(status);
			roomRepository.save(room);
			return "Now this room is  " + status;
		}
	}

	@GetMapping("/roomsGivenRange/{roomType}/{bedType}/{startDate}/{endDate}")
	public long getAvailableRooms(@PathVariable("roomType") String roomType, @PathVariable("bedType") String bedType,
			@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate) {
		long count = roomRepository.countByRoomTypeAndBedTypeAndStatus(roomType, bedType, "available");
		Integer upcount = upcomingBookingRepository.roomsGivenRange(roomType, bedType, startDate, endDate);
		if (upcount == null) {
			upcount = 0;
		}
		if (count < upcount) {
			return 0;
		}
		return count - upcount;
	}

}