package payroll.PayrollImplementation;

import payroll.PayrollDomain.Paycheck;

import java.util.Calendar;

public class PaycheckImplementation implements Paycheck {
	private Calendar itsPayPeriodStartDate;
	private Calendar itsPayPeriodEndDate;
	private double itsGrossPay;
	private double itsDeductions;
	private double itsNetPay;

	public PaycheckImplementation(Calendar payPeriodStartDate, Calendar payPeriodEndDate) {
		itsPayPeriodStartDate = payPeriodStartDate;
		itsPayPeriodEndDate = payPeriodEndDate;
	}

	public void SetGrossPay(double grossPay) {
		itsGrossPay = grossPay;
	}

	public void SetDeductions(double deductions) {
		itsDeductions = deductions;
	}

	public void SetNetPay(double netPay) {
		itsNetPay = netPay;
	}

	public Calendar GetPayPeriodStartDate() {
		return itsPayPeriodStartDate;
	}

	public Calendar GetPayPeriodEndDate() {
		return itsPayPeriodEndDate;
	}

	public double GetGrossPay() {
		return itsGrossPay;
	}

	public String GetField(String str) {
		if (str.equals("Disposition")) {
			return "Hold";
		}
		return null;
	}

	public double GetDeductions() {
		return itsDeductions;
	}

	public double GetNetPay() {
		return itsNetPay;
	}
}
