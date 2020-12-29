package payroll.TransactionImplementation;

import payroll.AbstractTransaction.AddEmployeeTransaction;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.PaymentSchedule;
import payroll.PayrollFactory.PayrollFactory;

public class AddCommissionedEmployee extends AddEmployeeTransaction {
	private double itsSalary;
	private double itsCommissionRate;

	public AddCommissionedEmployee(int empId, String name, String address, double salary, double commissionRate, PayrollFactory payrollFactory) {
		super(empId, name, address, payrollFactory);
		itsSalary = salary;
		itsCommissionRate = commissionRate;
	}

	public PaymentSchedule GetSchedule() {
		return itsPayrollFactory.makeBiweeklySchedule();
	}

	public PaymentClassification GetClassification() {
		return itsPayrollFactory.makeCommissionedClassification(itsSalary, itsCommissionRate);
	}
}
