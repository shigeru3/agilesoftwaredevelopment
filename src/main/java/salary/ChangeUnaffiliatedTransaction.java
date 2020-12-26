package salary;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {
	public ChangeUnaffiliatedTransaction(int empId) {
		super(empId);
	}

	@Override
	protected void RecordMembership(Employee e) {
		Affiliation af = e.GetAffiliation();
		if (af instanceof UnionAffiliation) {
			UnionAffiliation uf = (UnionAffiliation) af;
			int memberId = uf.GetMemberId();
			PayrollDatabase.RemoveUnionMember(memberId);
		}
	}

	@Override
	protected Affiliation GetAffiliation() {
		return new NoAffiliation();
	}
}
