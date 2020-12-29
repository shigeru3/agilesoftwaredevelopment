package payroll.TransactionImplementation;

import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.Employee;
import payroll.PayrollImplementation.CommissionedClassification;
import payroll.PayrollImplementation.SalesReceipt;
import payroll.TransactionApplication.Transaction;

import java.util.Calendar;

public class SalesReceiptTransaction implements Transaction {
	private Calendar itsDate;
	private double itsAmount;
	private int itsEmpId;

	public SalesReceiptTransaction(Calendar date, double amount, int empId) {
		itsDate = date;
		itsAmount = amount;
		itsEmpId = empId;
	}

	public void Execute() {
		Employee e = GlobalDatabase.payrollDB.GetEmployee(itsEmpId);
		if (e == null) {
			throw new RuntimeException("No such employee. employee id: " + itsEmpId);
		} else {
			PaymentClassification pc = e.GetClassification();
			if (pc instanceof CommissionedClassification) {
				CommissionedClassification cc = (CommissionedClassification) pc;
				cc.AddSalesReceipt(new SalesReceipt(itsDate, itsAmount));
			} else {
				throw new RuntimeException("Tried to add sales receipt to non-commissioned employee.");
			}
		}
	}
}
