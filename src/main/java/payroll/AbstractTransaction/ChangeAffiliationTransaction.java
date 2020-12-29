package payroll.AbstractTransaction;

import payroll.PayrollDomain.Affiliation;
import payroll.PayrollDomain.Employee;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {

	public ChangeAffiliationTransaction(int empId) {
		super(empId);
	}

	@Override
	public void Change(Employee e) {
		RecordMembership(e);
		e.SetAffiliation(GetAffiliation());
	}

	protected abstract void RecordMembership(Employee e);

	protected abstract Affiliation GetAffiliation();
}
