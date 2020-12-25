package salary;

public class SalariedClassification extends PaymentClassification {
	private double itsSalary;

	public SalariedClassification(double salary) {
		itsSalary = salary;
	}

	public double GetSalary() {
		return itsSalary;
	}
}
