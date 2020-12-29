package payroll.TransactionImplementation;

import payroll.AbstractTransaction.AddEmployeeTransaction;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.PaymentSchedule;
import payroll.PayrollFactory.PayrollFactory;

public class AddSalariedEmployee extends AddEmployeeTransaction {
	private double itsSalary;

	public AddSalariedEmployee(int empId, String name, String address, double salary, PayrollFactory payrollFactory) {
		super(empId, name, address, payrollFactory);
		itsSalary = salary;
	}

	public PaymentSchedule GetSchedule() {
		return itsPayrollFactory.makeMonthlySchedule();
	}

	public PaymentClassification GetClassification() {
		return itsPayrollFactory.makeSalariedClassification(itsSalary);
	}
}
