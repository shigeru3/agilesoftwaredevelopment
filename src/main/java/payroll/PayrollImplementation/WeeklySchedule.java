package payroll.PayrollImplementation;

import payroll.PayrollDomain.PaymentSchedule;

import java.util.Calendar;

public class WeeklySchedule implements PaymentSchedule {
	@Override
	public boolean IsPayDate(Calendar payDate) {
		return (payDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY);
	}

	@Override
	public Calendar GetPayPeriodStartDate(Calendar payDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(payDate.getTime());
		c.add(Calendar.DATE, -6);
		return c;
	}
}
