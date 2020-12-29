package payroll.PayrollImplementation;

import payroll.PayrollDomain.PaymentSchedule;

import java.util.Calendar;

public class MonthlySchedule implements PaymentSchedule {
	private boolean IsLastDayOfMonth(Calendar date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date.getTime());
		return (cal.getActualMaximum(Calendar.DATE) == cal.get(Calendar.DATE));
	}

	@Override
	public boolean IsPayDate(Calendar payDate) {
		return IsLastDayOfMonth(payDate);
	}

	@Override
	public Calendar GetPayPeriodStartDate(Calendar payDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(payDate.getTime());
		c.set(Calendar.DATE, 1);
		return c;
	}
}
