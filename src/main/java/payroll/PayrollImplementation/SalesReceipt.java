package payroll.PayrollImplementation;

import java.util.Calendar;

public class SalesReceipt {
	private Calendar itsDate;
	private double itsAmount;

	public SalesReceipt(Calendar date, double amount) {
		itsDate = date;
		itsAmount = amount;
	}

	public Calendar GetSalesDate() {
		return itsDate;
	}

	public double GetAmount() {
		return itsAmount;
	}
}
