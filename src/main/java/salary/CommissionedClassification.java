package salary;

public class CommissionedClassification extends PaymentClassification {
	private double itsSalary;
	private SalesReceipt itsSalesReceipt;

	public CommissionedClassification(double salary) {
		itsSalary = salary;
	}

	public double GetSalary() {
		return itsSalary;
	}

	public void AddSalesReceipt(SalesReceipt salesReceipt) {
		itsSalesReceipt = salesReceipt;
	}

	public SalesReceipt GetSalesReceipt(long date) {
		return itsSalesReceipt;
	}
}
