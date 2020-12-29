package payroll.TransactionImplementation;

import payroll.AbstractTransaction.ChangeAffiliationTransaction;
import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDomain.Affiliation;
import payroll.PayrollDomain.Employee;
import payroll.PayrollFactory.PayrollFactory;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {
	private int itsMemberId;
	private double itsDues;
	private PayrollFactory itsPayrollFactory;

	public ChangeMemberTransaction(int empId, int memberId, double dues, PayrollFactory payrollFactory) {
		super(empId);
		itsMemberId = memberId;
		itsDues = dues;
		itsPayrollFactory = payrollFactory;
	}

	@Override
	protected void RecordMembership(Employee e) {
		GlobalDatabase.payrollDB.AddUnionMember(itsMemberId, e);
	}

	@Override
	protected Affiliation GetAffiliation() {
		return itsPayrollFactory.makeUnionAffiliation(itsMemberId, itsDues);
	}
}
