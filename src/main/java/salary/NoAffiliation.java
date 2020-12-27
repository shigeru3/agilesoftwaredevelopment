package salary;

import java.util.Calendar;

public class NoAffiliation implements Affiliation {
	@Override
	public double GetServiceCharge(Calendar dues) {
		return 0;
	}

	@Override
	public double CalculateDeductions(Paycheck pc) {
		return 0;
	}
}
