package payroll.TransactionImplementation;

import payroll.AbstractTransaction.ChangeMethodTransaction;
import payroll.PayrollDomain.PaymentMethod;
import payroll.PayrollFactory.PayrollFactory;

public class ChangeHoldTransaction extends ChangeMethodTransaction {
	private PayrollFactory itsPayrollFactory;

	public ChangeHoldTransaction(int empId, PayrollFactory payrollFactory) {
		super(empId);
		itsPayrollFactory = payrollFactory;
	}

	@Override
	protected PaymentMethod GetMethod() {
		return itsPayrollFactory.makeHoldMethod();
	}
}
