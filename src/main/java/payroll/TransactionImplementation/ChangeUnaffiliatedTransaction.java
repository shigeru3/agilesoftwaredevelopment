package payroll.TransactionImplementation;

import payroll.AbstractTransaction.ChangeAffiliationTransaction;
import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDomain.Affiliation;
import payroll.PayrollDomain.Employee;
import payroll.PayrollFactory.PayrollFactory;
import payroll.PayrollImplementation.UnionAffiliation;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {
	private PayrollFactory itsPayrollFactory;

	public ChangeUnaffiliatedTransaction(int empId, PayrollFactory payrollFactory) {
		super(empId);
		itsPayrollFactory = payrollFactory;
	}

	@Override
	protected void RecordMembership(Employee e) {
		Affiliation af = e.GetAffiliation();
		if (af instanceof UnionAffiliation) {
			UnionAffiliation uf = (UnionAffiliation) af;
			int memberId = uf.GetMemberId();
			GlobalDatabase.payrollDB.RemoveUnionMember(memberId);
		}
	}

	@Override
	protected Affiliation GetAffiliation() {
		return itsPayrollFactory.makeNoAffiliation();
	}
}
