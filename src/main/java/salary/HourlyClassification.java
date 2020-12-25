package salary;

public class HourlyClassification extends PaymentClassification {
	private double itsSalary;
	private TimeCard itsTimeCard;

	public HourlyClassification(double salary) {
		itsSalary = salary;
	}

	public double GetSalary() {
		return itsSalary;
	}

	public void AddTimeCard(TimeCard timeCard) {
		itsTimeCard = timeCard;
	}

	public TimeCard GetTimeCard(long date) {
		return itsTimeCard;
	}
}
