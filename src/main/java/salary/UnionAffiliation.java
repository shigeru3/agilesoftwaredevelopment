package salary;

public class UnionAffiliation implements Affiliation {
	private long itsDate;
	private double itsAmount;

	public UnionAffiliation(double amount) {
		itsAmount = amount;
	}

	public double GetServiceCharge(double date) {
		return itsAmount;
	}

	public void AddServiceCharge(long date, double amount) {
		itsDate = date;
		itsAmount = amount;
	}
}
