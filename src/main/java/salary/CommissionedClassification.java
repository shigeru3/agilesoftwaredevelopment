package salary;

public class CommissionedClassification extends PaymentClassification {
	private double itsSalary;

	public CommissionedClassification(double salary) {
		itsSalary = salary;
	}

	public double GetSalary() {
		return itsSalary;
	}
}
