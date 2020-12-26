package salary;

public class AddHourlyEmployee extends AddEmployeeTransaction {
	private double itsHourlyRate;

	public AddHourlyEmployee(int empId, String name, String address, double hourlyRate) {
		super(empId, name, address);
		itsHourlyRate = hourlyRate;
	}

	PaymentSchedule GetSchedule() {
		return new WeeklySchedule();
	}

	PaymentClassification GetClassification() {
		return new HourlyClassification(itsHourlyRate);
	}
}

