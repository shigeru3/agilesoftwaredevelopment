package salary;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
	private double itsSalary;
	public ChangeSalariedTransaction(int empId, double salary) {
		super(empId);
		itsSalary = salary;
	}

	@Override
	protected PaymentClassification GetClassification() {
		return new SalariedClassification(itsSalary);
	}

	@Override
	protected PaymentSchedule GetSchedule() {
		return new MonthlySchedule();
	}
}
