package springproject.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//BookingHistoryy entity mapping to BookingHistory table in the database
@Entity
@Table(name = "BookingHistory")
public class BookingHistoryy {

	/*
	 * This Entity Don't Have Relations With Any Entity, Create For Adding New Row
	 * To BookingHistory Table
	 */

	// columns in bookinghistory table
	@Id
	private int id;

	@Column(name = "customerid", nullable = false)
	private int customerId;

	@Column(name = "roomnumber", nullable = false)
	private int roomNumber;

	@Column(name = "startdate", nullable = false)
	private LocalDate startDate;

	@Column(name = "enddate", nullable = false)
	private LocalDate endDate;

	// Setter and getter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
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

}
