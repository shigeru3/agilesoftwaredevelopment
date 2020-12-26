package salary;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {
	private double itsSalary;

	public ChangeCommissionedTransaction(int empId, double salary) {
		super(empId);
		itsSalary = salary;
	}

	@Override
	protected PaymentClassification GetClassification() {
		return new CommissionedClassification(itsSalary);
	}

	@Override
	protected PaymentSchedule GetSchedule() {
		return new BiweeklySchedule();
	}
}
