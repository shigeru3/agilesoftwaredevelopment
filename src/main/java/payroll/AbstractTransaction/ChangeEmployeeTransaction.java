package payroll.AbstractTransaction;

import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDatabaseImplementation.PayrollDatabaseImplementation;
import payroll.PayrollDomain.Employee;
import payroll.TransactionApplication.Transaction;

public abstract class ChangeEmployeeTransaction implements Transaction {
	private int itsEmpId;

	public ChangeEmployeeTransaction(int empId) {
		itsEmpId = empId;
	}

	public void Execute() {
		Employee e = GlobalDatabase.payrollDB.GetEmployee(itsEmpId);
		if (e != null) {
			Change(e);
		}
	}

	public abstract void Change(Employee e);
}
