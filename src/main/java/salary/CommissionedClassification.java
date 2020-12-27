package salary;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CommissionedClassification implements PaymentClassification {
	private double itsSalary;
	private double itsRate;
	private Map<Calendar, SalesReceipt> itsSalesReceipt = new HashMap<>();

	public CommissionedClassification(double salary, double rate) {
		itsSalary = salary;
		itsRate = rate;
	}

	public double GetSalary() {
		return itsSalary;
	}

	public void AddSalesReceipt(SalesReceipt salesReceipt) {
		itsSalesReceipt.put(salesReceipt.GetSalesDate(), salesReceipt);
	}

	public SalesReceipt GetSalesReceipt(Calendar date) {
		return itsSalesReceipt.get(date);
	}

	@Override
	public double CalculatePay(Paycheck pc) {
		double commission = 0.0;
		for (SalesReceipt receipt : itsSalesReceipt.values()) {
			if (Date.IsBetween(receipt.GetSalesDate(), pc.GetPayPeriodStartDate(), pc.GetPayPeriodEndDate())) {
				commission += receipt.GetAmount() * itsRate;
			}
		}
		return itsSalary + commission;
	}
}
