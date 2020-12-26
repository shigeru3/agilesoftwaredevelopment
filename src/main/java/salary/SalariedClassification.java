package salary;

public class SalariedClassification implements PaymentClassification {
	private double itsSalary;

	public SalariedClassification(double salary) {
		itsSalary = salary;
	}

	public double GetSalary() {
		return itsSalary;
	}

	@Override
	public double CalculatePay(Paycheck pc) {
		return itsSalary;
	}
}
