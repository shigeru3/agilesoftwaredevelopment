package salary;

public class AddHourlyEmployee extends AddEmployeeTransaction {
	private double itsSalary;

	public AddHourlyEmployee(int empId, String name, String address, double salary) {
		super(empId, name, address);
		itsSalary = salary;
	}

	PaymentSchedule GetSchedule() {
		return new WeeklySchedule();
	}

	PaymentClassification GetClassification() {
		return new HourlyClassification(itsSalary);
	}
}

