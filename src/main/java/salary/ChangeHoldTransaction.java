package salary;

public class ChangeHoldTransaction extends ChangeMethodTransaction {
	private String itsAddress;

	public ChangeHoldTransaction(int empId, String address) {
		super(empId);
		itsAddress = address;
	}

	@Override
	protected PaymentMethod GetMethod() {
		return new HoldMethod(itsAddress);
	}
}
