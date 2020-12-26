package salary;

public class AddHourlyEmployee extends AddEmployeeTransaction {
	private double itsRate;

	public AddHourlyEmployee(int empId, String name, String address, double rate) {
		super(empId, name, address);
		itsRate = rate;
	}

	PaymentSchedule GetSchedule() {
		return new WeeklySchedule();
	}

	PaymentClassification GetClassification() {
		return new HourlyClassification(itsRate);
	}
}

