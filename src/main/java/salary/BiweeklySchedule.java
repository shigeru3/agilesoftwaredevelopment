package salary;

import java.util.Calendar;

public class BiweeklySchedule implements PaymentSchedule {
	@Override
	public boolean IsPayDate(Calendar payDate) {
		return false;
	}

	@Override
	public Calendar GetPayPeriodStartDate(Calendar payDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(payDate.getTime());
		c.add(Calendar.DATE, -13);
		return c;
	}
}
