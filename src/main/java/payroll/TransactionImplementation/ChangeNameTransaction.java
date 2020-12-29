package payroll.TransactionImplementation;

import payroll.AbstractTransaction.ChangeEmployeeTransaction;
import payroll.PayrollDomain.Employee;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {
	private String itsName;

	public ChangeNameTransaction(int empId, String name) {
		super(empId);
		itsName = name;
	}

	public void Change(Employee e) {
		e.SetName(itsName);
	}
}
