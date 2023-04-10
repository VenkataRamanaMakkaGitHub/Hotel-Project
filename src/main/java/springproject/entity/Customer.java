package springproject.entity;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// Customer entity mapping to customers table in the database

@Entity
@Table(name = "customers")
public class Customer {

	// columns in customers table

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "mobile", nullable = false)
	private String mobile;

	@Column(name = "gender", nullable = false)
	private String gender;

	/*
	 * One to many relation with booking history entity and mapped with customer
	 * entity ----- And mappedBy = attribute indicates that which entity owns the
	 * relationship
	 */
	@OneToMany(mappedBy = "customer")
	@JsonIgnore // ignore the property from serialization
	private List<BookingHistory> bookingHistoryList;

	/*
	 * one to many relation with upcoming booking entity and mapped with customer
	 * entity ----- And mappedBy = attribute indicates that which entity owns the
	 * relationship
	 */
	@OneToMany(mappedBy = "customer")
	@JsonIgnore // ignore the property from serialization (Object to JSON conversion
	private List<UpcomingBooking> upcomingBookingList;


	
	// No args constructor
	public Customer() {
		super();
	}

	// Constructor with fields
	public Customer(int id, String name, String email, String mobile, String gender,
			List<BookingHistory> bookingHistoryList, List<UpcomingBooking> upcomingBookingList) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.bookingHistoryList = bookingHistoryList;
		this.upcomingBookingList = upcomingBookingList;
	}

	// Setter and getter methods

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<BookingHistory> getBookingHistoryList() {
		return bookingHistoryList;
	}

	public void setBookingHistoryList(List<BookingHistory> bookingHistoryList) {
		this.bookingHistoryList = bookingHistoryList;
	}

	public List<UpcomingBooking> getUpcomingBookingList() {
		return upcomingBookingList;
	}

	public void setUpcomingBookingList(List<UpcomingBooking> upcomingBookingList) {
		this.upcomingBookingList = upcomingBookingList;
	}

	// Override the hashCode and equals and toString methods from object class

	@Override
	public int hashCode() {
		return Objects.hash(bookingHistoryList, email, gender, id, mobile, name, upcomingBookingList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(bookingHistoryList, other.bookingHistoryList) && Objects.equals(email, other.email)
				&& Objects.equals(gender, other.gender) && id == other.id && Objects.equals(mobile, other.mobile)
				&& Objects.equals(name, other.name) && Objects.equals(upcomingBookingList, other.upcomingBookingList);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", gender="
				+ gender + ", bookingHistoryList=" + bookingHistoryList + ", upcomingBookingList=" + upcomingBookingList
				+ "]";
	}

}
