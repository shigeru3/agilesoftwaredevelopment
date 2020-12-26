package salary;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date extends GregorianCalendar {
	public static boolean IsBetween(Calendar theDate, Calendar startDate, Calendar endDate) {
		return 0 <= theDate.compareTo(startDate) && theDate.compareTo(endDate) <= 0;
	}
}
