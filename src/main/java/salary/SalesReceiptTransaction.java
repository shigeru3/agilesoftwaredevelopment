package salary;

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
		Employee e = PayrollDatabase.GetEmployee(itsEmpId);
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
