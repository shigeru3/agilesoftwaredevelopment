package salary;

import java.util.*;

public class PaydayTransaction implements Transaction {
	private Calendar itsPayDate;
	private Map<Integer, Paycheck> itsPaychecks = new HashMap<>();

	public PaydayTransaction(Calendar payDate) {
		itsPayDate = payDate;
	}

	@Override
	public void Execute() {
		List<Integer> empIds = PayrollDatabase.GetAllEmployeeIds();
		for (int empId : empIds) {
			Employee e = PayrollDatabase.GetEmployee(empId);
			if (e.IsPayDate(itsPayDate)) {
				Paycheck pc = new Paycheck(e.GetPayPeriodStartDate(itsPayDate), itsPayDate);
				itsPaychecks.put(empId, pc);
				e.Payday(pc);
			}
		}
	}

	public Paycheck GetPaycheck(int empId) {
		return itsPaychecks.getOrDefault(empId, null);
	}
}
