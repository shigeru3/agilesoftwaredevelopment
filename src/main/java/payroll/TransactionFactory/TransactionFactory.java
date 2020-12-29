package payroll.TransactionFactory;

import payroll.TransactionApplication.Transaction;

import java.util.Calendar;

public interface TransactionFactory {
	Transaction makeAddCommissionedEmployee(int empId, String name, String address, double salary, double commissionedRate);

	Transaction makeAddHourlyEmployee(int empId, String name, String address, double hourlyRate);

	Transaction makeAddSalariedEmployee(int empId, String name, String address, double salary);

	Transaction makeChangeAddressTransaction(int empId, String address);

	Transaction makeChangeCommissionedTransaction(int empId, double salary, double hourlyRate);

	Transaction makeChangeDirectTransaction(int empId, String bank, String account);

	Transaction makeChangeHoldTransaction(int empId);

	Transaction makeChangeHourlyTransaction(int empId, double rate);

	Transaction makeChangeMailTransaction(int empId, String address);

	Transaction makeChangeMemberTransaction(int empId, int memberId, double dues);

	Transaction makeChangeNameTransaction(int empId, String name);

	Transaction makeChangeSalariedTransaction(int empId, double salary);

	Transaction makeChangeUnaffiliatedTransaction(int empId, double salary);

	Transaction makeDeleteEmployeeTransaction(int empId);

	Transaction makePaydayTransaction(Calendar payDate);

	Transaction makeSalesReceiptTransaction(Calendar saleDate, double amount, int empId);

	Transaction makeServiceChargeTransaction(int memberId, Calendar date, double amount);

	Transaction makeTimeCardTransaction(Calendar date, double hours, int empId);
}
