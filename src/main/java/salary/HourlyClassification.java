package salary;

public class HourlyClassification extends PaymentClassification {
	private double itsSalary;

	public HourlyClassification(double salary) {
		itsSalary = salary;
	}

	public double GetSalary() {
		return itsSalary;
	}
}
