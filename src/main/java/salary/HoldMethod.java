package salary;

public class HoldMethod extends PaymentMethod {
	private String itsAddress;

	public HoldMethod(String address) {
		itsAddress = address;
	}

	public String GetAddress() {
		return itsAddress;
	}
}
