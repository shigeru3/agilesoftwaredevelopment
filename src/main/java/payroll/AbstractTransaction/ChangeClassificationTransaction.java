package payroll.AbstractTransaction;

import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.PaymentSchedule;
import payroll.PayrollDomain.Employee;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {
	public ChangeClassificationTransaction(int empId) {
		super(empId);
	}

	public void Change(Employee e) {
		e.SetSchedule(GetSchedule());
		e.SetClassification(GetClassification());
	}

	protected abstract PaymentClassification GetClassification();

	protected abstract PaymentSchedule GetSchedule();
}
