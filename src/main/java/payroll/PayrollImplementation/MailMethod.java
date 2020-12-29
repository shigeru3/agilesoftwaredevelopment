package payroll.PayrollImplementation;

import payroll.PayrollDomain.Paycheck;
import payroll.PayrollDomain.PaymentMethod;

public class MailMethod implements PaymentMethod {
	private String itsAddress;

	public MailMethod(String address) {
		itsAddress = address;
	}

	public String GetAddress() {
		return itsAddress;
	}

	@Override
	public void Pay(Paycheck pc) {

	}
}
