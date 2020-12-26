package salary;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {
	public ChangeClassificationTransaction(int empId) {
		super(empId);
	}

	public void Change(Employee e) {
		e.SetSchedule(GetSchedule());
		e.SetClassification(GetClassification());
	}

	protected abstract PaymentClassification GetClassification();

	protected abstract PaymentSchedule GetSchedule();
}
