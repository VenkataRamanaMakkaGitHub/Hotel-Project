package springproject.entity;

import java.time.LocalDate;
import java.util.List;

public class Details {

	int customerId;
	List<LocalDate> visitDates;
	double discount;
	double totalBill;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<LocalDate> getVisitDates() {
		return visitDates;
	}

	public void setVisitDates(List<LocalDate> visitDates) {
		this.visitDates = visitDates;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(double totalBill) {
		this.totalBill = totalBill;
	}

}
