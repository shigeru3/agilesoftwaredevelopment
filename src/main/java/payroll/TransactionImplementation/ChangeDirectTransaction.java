package payroll.TransactionImplementation;

import payroll.AbstractTransaction.ChangeMethodTransaction;
import payroll.PayrollDomain.PaymentMethod;
import payroll.PayrollFactory.PayrollFactory;

public class ChangeDirectTransaction extends ChangeMethodTransaction {
	private String itsBank;
	private String itsAccount;
	private PayrollFactory itsPayrollFactory;

	public ChangeDirectTransaction(int empId, String bank, String account, PayrollFactory payrollFactory) {
		super(empId);
		itsBank = bank;
		itsAccount = account;
		itsPayrollFactory = payrollFactory;
	}

	@Override
	protected PaymentMethod GetMethod() {
		return itsPayrollFactory.makeDirectMethod(itsBank, itsAccount);
	}
}
