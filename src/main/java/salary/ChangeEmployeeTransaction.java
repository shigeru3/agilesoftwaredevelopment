package salary;

public abstract class ChangeEmployeeTransaction implements Transaction {
	private int itsEmpId;

	public ChangeEmployeeTransaction(int empId) {
		itsEmpId = empId;
	}

	public void Execute() {
		Employee e = PayrollDatabase.GetEmployee(itsEmpId);
		if (e != null) {
			Change(e);
		}
	}

	abstract void Change(Employee e);
}
