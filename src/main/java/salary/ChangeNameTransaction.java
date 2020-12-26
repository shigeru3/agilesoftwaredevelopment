package salary;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {
	private String itsName;

	public ChangeNameTransaction(int empId, String name) {
		super(empId);
		itsName = name;
	}

	void Change(Employee e) {
		e.SetName(itsName);
	}
}
