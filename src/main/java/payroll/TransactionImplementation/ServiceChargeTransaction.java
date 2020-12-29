package payroll.TransactionImplementation;

import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDomain.Affiliation;
import payroll.PayrollDomain.Employee;
import payroll.PayrollImplementation.UnionAffiliation;
import payroll.TransactionApplication.Transaction;

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
		Employee e = GlobalDatabase.payrollDB.GetUnionMember(itsMemberId);
		Affiliation af = e.GetAffiliation();
		if (af instanceof UnionAffiliation) {
			UnionAffiliation uaf = (UnionAffiliation) af;
			uaf.AddServiceCharge(itsDate, itsAmount);
		}
	}
}
