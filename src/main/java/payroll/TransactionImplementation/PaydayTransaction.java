package payroll.TransactionImplementation;

import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDomain.Employee;
import payroll.PayrollDomain.Paycheck;
import payroll.PayrollFactory.PayrollFactory;
import payroll.TransactionApplication.Transaction;

import java.util.*;

public class PaydayTransaction implements Transaction {
	private Calendar itsPayDate;
	private Map<Integer, Paycheck> itsPaychecks = new HashMap<>();
	private PayrollFactory itsPayrollFactory;

	public PaydayTransaction(Calendar payDate, PayrollFactory payrollFactory) {
		itsPayDate = payDate;
		itsPayrollFactory = payrollFactory;
	}

	@Override
	public void Execute() {
		List<Integer> empIds = GlobalDatabase.payrollDB.GetAllEmployeeIds();
		for (int empId : empIds) {
			Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
			if (e.IsPayDate(itsPayDate)) {
				Paycheck pc = itsPayrollFactory.makePayCheck(e.GetPayPeriodStartDate(itsPayDate), itsPayDate);
				itsPaychecks.put(empId, pc);
				e.Payday(pc);
			}
		}
	}

	public Paycheck GetPaycheck(int empId) {
		return itsPaychecks.getOrDefault(empId, null);
	}

}
