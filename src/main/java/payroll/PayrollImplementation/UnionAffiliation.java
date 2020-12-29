package payroll.PayrollImplementation;

import payroll.PayrollDomain.Affiliation;
import payroll.PayrollDomain.Paycheck;
import payroll.PayrollUtil.Date;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UnionAffiliation implements Affiliation {
	private int itsMemberId;
	private Calendar itsDate;
	private double itsAmount;
	private Map<Calendar, ServiceCharge> itsServiceCharge = new HashMap<>();

	public UnionAffiliation(int memberId, double amount) {
		itsMemberId = memberId;
		itsAmount = amount;
	}

	public void AddServiceCharge(Calendar date, double amount) {
		itsServiceCharge.put(date, new ServiceCharge(date, amount));
	}

	@Override
	public double GetServiceCharge(Calendar date) {
		return itsServiceCharge.getOrDefault(date, null).GetAmount();
	}

	@Override
	public double CalculateDeductions(Paycheck pc) {
		double totalServiceCharge = 0;
		for (ServiceCharge cs : itsServiceCharge.values()) {
			if (Date.IsBetween(cs.GetDate(), pc.GetPayPeriodStartDate(), pc.GetPayPeriodEndDate())) {
				totalServiceCharge += cs.GetAmount();
			}
		}
		int fridays = NumberOfFridaysInPayPeriod(pc.GetPayPeriodStartDate(), pc.GetPayPeriodEndDate());
		double totalDues = itsAmount * fridays;
		return totalDues + totalServiceCharge;
	}

	private int NumberOfFridaysInPayPeriod(Calendar payPeriodStart, Calendar payPeriodEnd) {
		int fridays = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(payPeriodStart.getTime());
		while(c.compareTo(payPeriodEnd) <= 0) {
			if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
				fridays++;
			}
			c.add(Calendar.DATE, 1);
		}
		return fridays;
	}

	public double GetDues() {
		return itsAmount;
	}

	public int GetMemberId() {
		return itsMemberId;
	}
}
