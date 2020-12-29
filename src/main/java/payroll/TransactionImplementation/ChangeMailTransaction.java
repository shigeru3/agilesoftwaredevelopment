package payroll.TransactionImplementation;

import payroll.AbstractTransaction.ChangeMethodTransaction;
import payroll.PayrollDomain.PaymentMethod;
import payroll.PayrollFactory.PayrollFactory;

public class ChangeMailTransaction extends ChangeMethodTransaction {
	private String itsAddress;
	private PayrollFactory itsPayrollFactory;

	public ChangeMailTransaction(int empId, String address, PayrollFactory payrollFactory) {
		super(empId);
		itsAddress = address;
		itsPayrollFactory = payrollFactory;
	}

	@Override
	protected PaymentMethod GetMethod() {
		return itsPayrollFactory.makeMailMethod(itsAddress);
	}
}
