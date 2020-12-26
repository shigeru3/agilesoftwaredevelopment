package salary;

public class DirectMethod extends PaymentMethod {
	private String itsBank;
	private String itsAccount;

	public DirectMethod(String bank, String account) {
		itsBank = bank;
		itsAccount = account;
	}

	public String GetBank() {
		return itsBank;
	}
}
