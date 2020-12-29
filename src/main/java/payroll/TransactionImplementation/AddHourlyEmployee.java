package payroll.TransactionImplementation;

import payroll.AbstractTransaction.AddEmployeeTransaction;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.PaymentSchedule;
import payroll.PayrollFactory.PayrollFactory;

public class AddHourlyEmployee extends AddEmployeeTransaction {
	private double itsHourlyRate;

	public AddHourlyEmployee(int empId, String name, String address, double hourlyRate, PayrollFactory payrollFactory) {
		super(empId, name, address, payrollFactory);
		itsHourlyRate = hourlyRate;
	}

	public PaymentSchedule GetSchedule() {
		return itsPayrollFactory.makeWeeklySchedule();
	}

	public PaymentClassification GetClassification() {
		return itsPayrollFactory.makeHourlyClassification(itsHourlyRate);
	}
}

