package salary;

public class SalesReceipt {
	private long itsDate;
	private int itsAmount;

	public SalesReceipt(long date, int amount) {
		itsDate = date;
		itsAmount = amount;
	}


	public int GetAmount() {
		return itsAmount;
	}
}
