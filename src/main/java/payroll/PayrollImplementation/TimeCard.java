package payroll.PayrollImplementation;

import java.util.Calendar;

public class TimeCard {
	private Calendar itsDate;
	private double itsHours;

	public TimeCard(Calendar date, double hours) {
		itsDate = date;
		itsHours = hours;
	}

	public Calendar GetDate() {
		return itsDate;
	}

	public double GetHours() {
		return itsHours;
	}
}
