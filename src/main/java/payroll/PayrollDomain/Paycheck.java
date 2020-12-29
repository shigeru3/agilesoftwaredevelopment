package payroll.PayrollDomain;

import java.util.Calendar;

public interface Paycheck {
	Calendar GetPayPeriodStartDate();

	Calendar GetPayPeriodEndDate();

	double GetGrossPay();

	void SetGrossPay(double grossPay);

	String GetField(String string);

	double GetDeductions();

	void SetDeductions(double deductions);

	double GetNetPay();

	void SetNetPay(double netPay);
}
