package salary;

public class ChangeMailTransaction extends ChangeMethodTransaction {
	private String itsAddress;

	public ChangeMailTransaction(int empId, String address) {
		super(empId);
		itsAddress = address;
	}

	@Override
	protected PaymentMethod GetMethod() {
		return new MailMethod(itsAddress);
	}
}
