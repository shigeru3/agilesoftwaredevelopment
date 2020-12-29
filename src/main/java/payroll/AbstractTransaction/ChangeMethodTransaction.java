package payroll.AbstractTransaction;

import payroll.PayrollDomain.PaymentMethod;
import payroll.PayrollDomain.Employee;

public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {
	public ChangeMethodTransaction(int empId) {
		super(empId);
	}

	@Override
	public void Change(Employee e) {
		e.SetMethod(GetMethod());
	}

	protected abstract PaymentMethod GetMethod();
}
