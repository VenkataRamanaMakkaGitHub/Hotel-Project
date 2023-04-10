package springproject.entity;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//Room entity mapping to rooms table in the database
@Entity
@Table(name = "rooms")
public class Room {

	// columns in rooms table
	
	@Id
	@Column(name = "roomnumber", nullable = false)
	private int roomNumber;

	@Column(name = "floor", nullable = false)
	private int floor;

	@Column(name = "roomtype", nullable = false)
	private String roomType;

	@Column(name = "bedtype", nullable = false)
	private String bedType;

	@Column(name = "tariff", nullable = false)
	private double tariff;

	@Column(name = "status", nullable = false)
	private String status;

	/*
	 * One to many relation with booking history entity and mapped with room
	 * entity ----- And mappedBy = attribute indicates that which entity owns the
	 * relationship
	 */
	
	@OneToMany(mappedBy = "room")
	@JsonIgnore     // ignore the property from serialization
	private List<BookingHistory> bookingHistoryList;

	// No args constructor
	public Room() {
		super();
	}

	// Constructor with fields
	public Room(int roomNumber, int floor, String roomType, String bedType, double tariff, String status,
			List<BookingHistory> bookingHistoryList) {
		super();
		this.roomNumber = roomNumber;
		this.floor = floor;
		this.roomType = roomType;
		this.bedType = bedType;
		this.tariff = tariff;
		this.status = status;
		this.bookingHistoryList = bookingHistoryList;
	}

	// Setter and getter methods
	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getBedType() {
		return bedType;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	public double getTariff() {
		return tariff;
	}

	public void setTariff(double tariff) {
		this.tariff = tariff;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<BookingHistory> getBookingHistoryList() {
		return bookingHistoryList;
	}

	public void setBookingHistoryList(List<BookingHistory> bookingHistoryList) {
		this.bookingHistoryList = bookingHistoryList;
	}

	// Override the hashCode and equals and toString methods from object class
	@Override
	public int hashCode() {
		return Objects.hash(bedType, bookingHistoryList, floor, roomNumber, roomType, status, tariff);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return Objects.equals(bedType, other.bedType) && Objects.equals(bookingHistoryList, other.bookingHistoryList)
				&& floor == other.floor && roomNumber == other.roomNumber && Objects.equals(roomType, other.roomType)
				&& Objects.equals(status, other.status)
				&& Double.doubleToLongBits(tariff) == Double.doubleToLongBits(other.tariff);
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", floor=" + floor + ", roomType=" + roomType + ", bedType=" + bedType
				+ ", tariff=" + tariff + ", status=" + status + ", bookingHistoryList=" + bookingHistoryList + "]";
	}

}
