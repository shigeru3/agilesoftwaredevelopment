package payroll.PayrollImplementation;

import payroll.PayrollDomain.Paycheck;
import payroll.PayrollDomain.PaymentClassification;

public class SalariedClassification implements PaymentClassification {
	private double itsSalary;

	public SalariedClassification(double salary) {
		itsSalary = salary;
	}

	public double GetSalary() {
		return itsSalary;
	}

	@Override
	public double CalculatePay(Paycheck pc) {
		return itsSalary;
	}
}
