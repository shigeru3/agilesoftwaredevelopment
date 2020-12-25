package salary;

public class SalariedClassification extends PaymentClassification {
	private double itsSalary;

	public SalariedClassification(double salary) {
		itsSalary = salary;
	}

	public Object GetSalary() {
		return itsSalary;
	}
}
