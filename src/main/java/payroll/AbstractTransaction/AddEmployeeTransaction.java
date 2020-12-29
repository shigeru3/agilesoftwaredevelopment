package payroll.AbstractTransaction;

import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDomain.*;
import payroll.PayrollFactory.PayrollFactory;
import payroll.TransactionApplication.Transaction;

public abstract class AddEmployeeTransaction implements Transaction {
	private int itsEmpId;
	private String itsName;
	private String itsAddress;
	protected PayrollFactory itsPayrollFactory;

	public AddEmployeeTransaction(int empId, String name, String address, PayrollFactory payrollFactory) {
		itsEmpId = empId;
		itsName = name;
		itsAddress = address;
		itsPayrollFactory = payrollFactory;
	}

	public void Execute() {
		PaymentClassification pc = GetClassification();
		PaymentSchedule ps = GetSchedule();
		PaymentMethod pm = itsPayrollFactory.makeHoldMethod();
		Employee e = itsPayrollFactory.makeEmployee(itsEmpId, itsName, itsAddress);
		Affiliation af = itsPayrollFactory.makeNoAffiliation();
		e.SetClassification(pc);
		e.SetSchedule(ps);
		e.SetMethod(pm);
		e.SetAffiliation(af);
		GlobalDatabase.payrollDB.AddEmployee(itsEmpId, e);
	}

	public abstract PaymentSchedule GetSchedule();

	public abstract PaymentClassification GetClassification();
}
