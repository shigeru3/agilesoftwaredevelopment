package payroll.TransactionImplementation;

import payroll.AbstractTransaction.ChangeClassificationTransaction;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.PaymentSchedule;
import payroll.PayrollFactory.PayrollFactory;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {
	private double itsSalary;
	private double itsCommissionRate;
	private PayrollFactory itsPayrollFactory;

	public ChangeCommissionedTransaction(int empId, double salary, double commissionRate, PayrollFactory payrollFactory) {
		super(empId);
		itsSalary = salary;
		itsCommissionRate = commissionRate;
		itsPayrollFactory = payrollFactory;
	}

	@Override
	protected PaymentClassification GetClassification() {
		return itsPayrollFactory.makeCommissionedClassification(itsSalary, itsCommissionRate);
	}

	@Override
	protected PaymentSchedule GetSchedule() {
		return itsPayrollFactory.makeBiweeklySchedule();
	}
}
