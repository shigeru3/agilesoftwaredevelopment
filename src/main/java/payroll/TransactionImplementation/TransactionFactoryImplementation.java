package payroll.TransactionImplementation;

import payroll.PayrollFactory.PayrollFactory;
import payroll.TransactionApplication.Transaction;
import payroll.TransactionFactory.TransactionFactory;

import java.util.Calendar;

public class TransactionFactoryImplementation implements TransactionFactory {
	private PayrollFactory itsPayrollFactory;

	public TransactionFactoryImplementation(PayrollFactory payrollFactory) {
		itsPayrollFactory = payrollFactory;
	}

	@Override
	public Transaction makeAddCommissionedEmployee(int empId, String name, String address, double salary, double commissionedRate) {
		return new AddCommissionedEmployee(empId, name, address, salary, commissionedRate, itsPayrollFactory);
	}

	@Override
	public Transaction makeAddHourlyEmployee(int empId, String name, String address, double hourlyRate) {
		return new AddHourlyEmployee(empId, name, address, hourlyRate, itsPayrollFactory);
	}

	@Override
	public Transaction makeAddSalariedEmployee(int empId, String name, String address, double salary) {
		return new AddSalariedEmployee(empId, name, address, salary, itsPayrollFactory);
	}

	@Override
	public Transaction makeChangeAddressTransaction(int empId, String address) {
		return new ChangeAddressTransaction(empId, address);
	}

	@Override
	public Transaction makeChangeCommissionedTransaction(int empId, double salary, double hourlyRate) {
		return new ChangeCommissionedTransaction(empId, salary, hourlyRate, itsPayrollFactory);
	}

	@Override
	public Transaction makeChangeDirectTransaction(int empId, String bank, String account) {
		return new ChangeDirectTransaction(empId, bank, account, itsPayrollFactory);
	}

	@Override
	public Transaction makeChangeHoldTransaction(int empId) {
		return new ChangeHoldTransaction(empId, itsPayrollFactory);
	}

	@Override
	public Transaction makeChangeHourlyTransaction(int empId, double rate) {
		return new ChangeHourlyTransaction(empId, rate, itsPayrollFactory);
	}

	@Override
	public Transaction makeChangeMailTransaction(int empId, String address) {
		return new ChangeMailTransaction(empId, address, itsPayrollFactory);
	}

	@Override
	public Transaction makeChangeMemberTransaction(int empId, int memberId, double dues) {
		return new ChangeMemberTransaction(empId, memberId, dues, itsPayrollFactory);
	}

	@Override
	public Transaction makeChangeNameTransaction(int empId, String name) {
		return new ChangeNameTransaction(empId, name);
	}

	@Override
	public Transaction makeChangeSalariedTransaction(int empId, double salary) {
		return new ChangeSalariedTransaction(empId, salary, itsPayrollFactory);
	}

	@Override
	public Transaction makeChangeUnaffiliatedTransaction(int empId, double salary) {
		return null;
	}

	@Override
	public Transaction makeDeleteEmployeeTransaction(int empId) {
		return new DeleteEmployeeTransaction(empId);
	}

	@Override
	public Transaction makePaydayTransaction(Calendar payDate) {
		return new PaydayTransaction(payDate, itsPayrollFactory);
	}

	@Override
	public Transaction makeSalesReceiptTransaction(Calendar saleDate, double amount, int empId) {
		return new SalesReceiptTransaction(saleDate, amount, empId);
	}

	@Override
	public Transaction makeServiceChargeTransaction(int memberId, Calendar date, double amount) {
		return new ServiceChargeTransaction(memberId, date, amount);
	}

	@Override
	public Transaction makeTimeCardTransaction(Calendar date, double hours, int empId) {
		return new TimeCardTransaction(date, hours, empId);
	}
}
