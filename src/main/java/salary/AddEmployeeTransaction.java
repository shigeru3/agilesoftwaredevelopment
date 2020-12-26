package salary;

public abstract class AddEmployeeTransaction implements Transaction {
	private int itsEmpId;
	private String itsName;
	private String itsAddress;

	public AddEmployeeTransaction(int empId, String name, String address) {
		itsEmpId = empId;
		itsName = name;
		itsAddress = address;
	}

	public void Execute() {
		PaymentClassification pc = GetClassification();
		PaymentSchedule ps = GetSchedule();
		PaymentMethod pm = new HoldMethod(itsAddress);
		Employee e = new Employee(itsEmpId, itsName, itsAddress);
		Affiliation af = new NoAffiliation();
		e.SetClassification(pc);
		e.SetSchedule(ps);
		e.SetMethod(pm);
		e.SetAffiliation(af);
		PayrollDatabase.AddEmployee(itsEmpId, e);
	}

	abstract PaymentSchedule GetSchedule();

	abstract PaymentClassification GetClassification();
}
