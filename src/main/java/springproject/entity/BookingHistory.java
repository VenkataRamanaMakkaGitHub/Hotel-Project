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

//BookingHistory entity mapping to bookinghistory table in the database

@Entity
@Table(name = "bookinghistory")
public class BookingHistory {

	// columns in bookinghistory table
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// ManyToOne relation with Customer entity
	@ManyToOne
	@JoinColumn(name = "customerid", nullable = false)
	private Customer customer;

	// ManyToOne relation with Room entity
	@ManyToOne
	@JoinColumn(name = "roomnumber", nullable = false)
	private Room room;

	@Column(name = "startdate", nullable = false)
	private LocalDate startDate;

	@Column(name = "enddate")
	private LocalDate endDate;

	// No args constructor
	public BookingHistory() {
		super();
	}

	// Constructor with fields
	public BookingHistory(int id, Customer customer, Room room, LocalDate startDate, LocalDate endDate) {
		super();
		this.id = id;
		this.customer = customer;
		this.room = room;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	// Override the hashCode and equals and toString methods from object class
	@Override
	public int hashCode() {
		return Objects.hash(customer, endDate, id, room, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingHistory other = (BookingHistory) obj;
		return Objects.equals(customer, other.customer) && Objects.equals(endDate, other.endDate) && id == other.id
				&& Objects.equals(room, other.room) && Objects.equals(startDate, other.startDate);
	}

	@Override
	public String toString() {
		return "BookingHistory [id=" + id + ", customer=" + customer + ", room=" + room + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

}
