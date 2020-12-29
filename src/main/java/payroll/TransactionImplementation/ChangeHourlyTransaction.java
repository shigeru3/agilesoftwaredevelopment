package payroll.TransactionImplementation;

import payroll.AbstractTransaction.ChangeClassificationTransaction;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.PaymentSchedule;
import payroll.PayrollFactory.PayrollFactory;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
	private double itsRate;
	private PayrollFactory itsPayrollFactory;

	public ChangeHourlyTransaction(int empId, double rate, PayrollFactory payrollFactory) {
		super(empId);
		itsRate = rate;
		itsPayrollFactory = payrollFactory;
	}

	protected PaymentClassification GetClassification() {
		return itsPayrollFactory.makeHourlyClassification(itsRate);
	}

	protected PaymentSchedule GetSchedule() {
		return itsPayrollFactory.makeWeeklySchedule();
	}
}
