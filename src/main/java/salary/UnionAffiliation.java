package salary;

public class UnionAffiliation implements Affiliation {
	private int itsMemberId;
	private long itsDate;
	private double itsAmount;

	public UnionAffiliation(int memberId, double amount) {
		itsMemberId = memberId;
		itsAmount = amount;
	}

	public double GetServiceCharge(double date) {
		return itsAmount;
	}

	@Override
	public void AddServiceCharge(long date, double amount) {
		itsDate = date;
		itsAmount = amount;
	}

	@Override
	public double CalculateDeductions(Paycheck pc) {
		return 0;
	}

	public double GetDues() {
		return itsAmount;
	}

	public int GetMemberId() {
		return itsMemberId;
	}
}
