package salary;

import java.util.Calendar;

public class MonthlySchedule extends PaymentSchedule {
	private boolean IsLastDayOfMonth(Calendar date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date.getTime());
		return (cal.getActualMaximum(Calendar.DATE) == cal.get(Calendar.DATE));
	}

	@Override
	public boolean IsPayDate(Calendar payDate) {
		return IsLastDayOfMonth(payDate);
	}
}
