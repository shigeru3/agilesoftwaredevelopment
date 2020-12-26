package salary;

public class MailMethod extends PaymentMethod {
	private String itsAddress;

	public MailMethod(String address) {
		itsAddress = address;
	}

	public String GetAddress() {
		return itsAddress;
	}
}
