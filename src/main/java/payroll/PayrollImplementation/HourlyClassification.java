package payroll.PayrollImplementation;

import payroll.PayrollDomain.Paycheck;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollUtil.Date;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class HourlyClassification implements PaymentClassification {
	private double itsRate;
	private Map<Calendar, TimeCard> itsTimeCard = new HashMap<>();

	public HourlyClassification(double rate) {
		itsRate = rate;
	}

	public double GetSalary() {
		return itsRate;
	}

	public void AddTimeCard(TimeCard timeCard) {
		itsTimeCard.put(timeCard.GetDate(), timeCard);
	}

	public TimeCard GetTimeCard(Calendar date) {
		return itsTimeCard.get(date);
	}

	public double GetRate() {
		return itsRate;
	}

	@Override
	public double CalculatePay(Paycheck pc) {
		double total = 0;
		for (Map.Entry<Calendar, TimeCard> entry : itsTimeCard.entrySet()) {
			TimeCard tc = entry.getValue();
			if (Date.IsBetween(tc.GetDate(), pc.GetPayPeriodStartDate(), pc.GetPayPeriodEndDate())) {
				if (tc.GetHours() > 8) {
					total += itsRate * 8 + itsRate * (tc.GetHours() - 8) * 1.5;
				} else {
					total += itsRate * tc.GetHours();
				}
			}
		}
		return total;
	}
}
