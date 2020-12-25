package salary;

public class SalesReceiptTransaction implements Transaction {
	private long itsDate;
	private int itsAmount;
	private int itsEmpId;

	public SalesReceiptTransaction(long date, int amount, int empId) {
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
