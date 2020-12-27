package salary;

import java.util.Calendar;

public class ServiceChargeTransaction implements Transaction {
	private int itsMemberId;
	private Calendar itsDate;
	private double itsAmount;
	private double itsDues;

	public ServiceChargeTransaction(int memberId, Calendar date, double amount) {
		itsMemberId = memberId;
		itsDate = date;
		itsAmount = amount;
	}

	public void Execute() {
		Employee e = PayrollDatabase.GetUnionMember(itsMemberId);
		Affiliation af = e.GetAffiliation();
		if (af instanceof UnionAffiliation) {
			UnionAffiliation uaf = (UnionAffiliation) af;
			uaf.AddServiceCharge(itsDate, itsAmount);
		}
	}
}
