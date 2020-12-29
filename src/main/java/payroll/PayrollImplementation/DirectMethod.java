package payroll.PayrollImplementation;

import payroll.PayrollDomain.Paycheck;
import payroll.PayrollDomain.PaymentMethod;

public class DirectMethod implements PaymentMethod {
	private String itsBank;
	private String itsAccount;

	public DirectMethod(String bank, String account) {
		itsBank = bank;
		itsAccount = account;
	}

	public String GetBank() {
		return itsBank;
	}

	@Override
	public void Pay(Paycheck pc) {

	}
}
