package salary;

public class HourlyClassification extends PaymentClassification {
	private double itsRate;
	private TimeCard itsTimeCard;

	public HourlyClassification(double rate) {
		itsRate = rate;
	}

	public double GetSalary() {
		return itsRate;
	}

	public void AddTimeCard(TimeCard timeCard) {
		itsTimeCard = timeCard;
	}

	public TimeCard GetTimeCard(long date) {
		return itsTimeCard;
	}

	public double GetRate() {
		return itsRate;
	}
}
