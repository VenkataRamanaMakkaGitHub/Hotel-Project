package springproject.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="hotelpayments")
public class Payment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="roomnumber")
	private Room room;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="discount")
	private double discount;
	
	@Column(name="finalbill")
	private double finalBill;
	
	@Column(name="status")
	private String status;
	
	@Column(name="paymentdate")
	private LocalDateTime paymentDate;

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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getFinalBill() {
		return finalBill;
	}

	public void setFinalBill(double finalBill) {
		this.finalBill = finalBill;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	
	
	public Payment() {
		super();
	}

	public Payment(int id, Customer customer, Room room, double amount, double discount, double finalBill,
			String status, LocalDateTime paymentDate) {
		super();
		this.id = id;
		this.customer = customer;
		this.room = room;
		this.amount = amount;
		this.discount = discount;
		this.finalBill = finalBill;
		this.status = status;
		this.paymentDate = paymentDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, customer, discount, finalBill, id, paymentDate, room, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(customer, other.customer)
				&& Double.doubleToLongBits(discount) == Double.doubleToLongBits(other.discount)
				&& Double.doubleToLongBits(finalBill) == Double.doubleToLongBits(other.finalBill) && id == other.id
				&& Objects.equals(paymentDate, other.paymentDate) && Objects.equals(room, other.room)
				&& Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", customer=" + customer + ", room=" + room + ", amount=" + amount + ", discount="
				+ discount + ", finalBill=" + finalBill + ", status=" + status + ", paymentDate=" + paymentDate + "]";
	}
	
	
	
}
