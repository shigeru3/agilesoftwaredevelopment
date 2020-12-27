package salary;

import java.util.Calendar;

public class ServiceCharge {
	private Calendar itsDate;
	private double itsAmount;

	public ServiceCharge(Calendar date, double amount) {
		itsDate = date;
		itsAmount = amount;
	}

	public Calendar GetDate() {
		return itsDate;
	}

	public double GetAmount() {
		return itsAmount;
	}
}
