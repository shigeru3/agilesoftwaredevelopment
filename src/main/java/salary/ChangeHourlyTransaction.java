package salary;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
	private double itsRate;

	public ChangeHourlyTransaction(int empId, double rate) {
		super(empId);
		itsRate = rate;
	}

	protected PaymentClassification GetClassification() {
		return new HourlyClassification(itsRate);
	}

	protected PaymentSchedule GetSchedule() {
		return new WeeklySchedule();
	}
}
