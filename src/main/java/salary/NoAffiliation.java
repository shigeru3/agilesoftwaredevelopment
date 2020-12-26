package salary;

public class NoAffiliation implements Affiliation {
	@Override
	public double GetServiceCharge(double dues) {
		return 0;
	}

	@Override
	public void AddServiceCharge(long date, double amount) {

	}

	@Override
	public double CalculateDeductions(Paycheck pc) {
		return 0;
	}
}