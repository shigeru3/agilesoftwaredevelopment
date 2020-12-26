package salary;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction {
	private String itsAddress;

	public ChangeAddressTransaction(int empId, String address) {
		super(empId);
		itsAddress = address;
	}

	void Change(Employee e) {
		e.setAddress(itsAddress);

	}
}
