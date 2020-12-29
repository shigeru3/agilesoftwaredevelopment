package payroll.TransactionImplementation;

import payroll.AbstractTransaction.ChangeClassificationTransaction;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.PaymentSchedule;
import payroll.PayrollFactory.PayrollFactory;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
	private double itsSalary;
	private PayrollFactory itsPayrollFactory;

	public ChangeSalariedTransaction(int empId, double salary, PayrollFactory payrollFactory) {
		super(empId);
		itsSalary = salary;
		itsPayrollFactory = payrollFactory;
	}

	@Override
	protected PaymentClassification GetClassification() {
		return itsPayrollFactory.makeSalariedClassification(itsSalary);
	}

	@Override
	protected PaymentSchedule GetSchedule() {
		return itsPayrollFactory.makeMonthlySchedule();
	}
}
