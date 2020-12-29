package payroll.TransactionImplementation;

import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDatabaseImplementation.PayrollDatabaseImplementation;
import payroll.TransactionApplication.Transaction;

public class DeleteEmployeeTransaction implements Transaction {
	private int itsEmpId;

	public DeleteEmployeeTransaction(int empId) {
		itsEmpId = empId;
	}

	public void Execute() {
		GlobalDatabase.payrollDB.DeleteEmployee(itsEmpId);
	}

}
