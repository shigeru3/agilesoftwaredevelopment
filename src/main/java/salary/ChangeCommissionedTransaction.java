package salary;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {
	private double itsSalary;
	private double itsCommissionRate;

	public ChangeCommissionedTransaction(int empId, double salary, double commissionRate) {
		super(empId);
		itsSalary = salary;
		itsCommissionRate = commissionRate;
	}

	@Override
	protected PaymentClassification GetClassification() {
		return new CommissionedClassification(itsSalary, itsCommissionRate);
	}

	@Override
	protected PaymentSchedule GetSchedule() {
		return new BiweeklySchedule();
	}
}
