package springproject.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//UpcomingBooking entity mapping to upcomingbookings table in the database
@Entity
@Table(name = "upcomingbookings")
public class UpcomingBooking {

	// columns in bookinghistory table
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// ManyToOne relation with Customer entity
	@ManyToOne
	@JoinColumn(name = "customerid", nullable = false)
	private Customer customer;

	@Column(name = "startdate", nullable = false)
	private LocalDate startDate;

	@Column(name = "roomtype", nullable = false)
	private String roomType;

	@Column(name = "bedtype", nullable = false)
	private String bedType;

	@Column(name = "numberofrooms", nullable = false)
	private int numberOfRooms;

	// No args constructor
	public UpcomingBooking() {
		super();
	}

	// Constructor with fields
	public UpcomingBooking(int id, Customer customer, LocalDate startDate, String roomType, String bedType,
			int numberOfRooms) {
		super();
		this.id = id;
		this.customer = customer;
		this.startDate = startDate;
		this.roomType = roomType;
		this.bedType = bedType;
		this.numberOfRooms = numberOfRooms;
	}

	// Setter and getter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
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

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	// Override the hashCode and equals and toString methods from object class
	@Override
	public int hashCode() {
		return Objects.hash(bedType, customer, id, numberOfRooms, roomType, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpcomingBooking other = (UpcomingBooking) obj;
		return Objects.equals(bedType, other.bedType) && Objects.equals(customer, other.customer) && id == other.id
				&& Objects.equals(numberOfRooms, other.numberOfRooms) && Objects.equals(roomType, other.roomType)
				&& Objects.equals(startDate, other.startDate);
	}

	@Override
	public String toString() {
		return "UpcomingBooking [id=" + id + ", customer=" + customer + ", startDate=" + startDate + ", roomType="
				+ roomType + ", bedType=" + bedType + ", numberOfRooms=" + numberOfRooms + "]";
	}

}
