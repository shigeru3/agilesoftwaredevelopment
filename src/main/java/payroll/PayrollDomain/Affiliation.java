package payroll.PayrollDomain;

import java.util.Calendar;

public interface Affiliation {
	double GetServiceCharge(Calendar date);

	double CalculateDeductions(Paycheck pc);
}
