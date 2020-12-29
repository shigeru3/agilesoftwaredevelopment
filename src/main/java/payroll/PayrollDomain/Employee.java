package payroll.PayrollDomain;

import java.util.Calendar;

public class Employee {
	private int itsEmpId;
	private String itsName;
	private String itsAddress;
	private PaymentClassification itsClassification;
	private PaymentSchedule itsPaymentSchedule;
	private PaymentMethod itsPaymentMethod;
	private Affiliation itsAffiliation;

	public Employee(int empId, String name, String address) {
		itsEmpId = empId;
		itsName = name;
		itsAddress = address;
	}

	public String GetName() {
		return itsName;
	}

	public PaymentClassification GetClassification() {
		return itsClassification;
	}

	public PaymentSchedule GetSchedule() {
		return itsPaymentSchedule;
	}

	public PaymentMethod GetMethod() {
		return itsPaymentMethod;
	}

	public void SetClassification(PaymentClassification pc) {
		itsClassification = pc;
	}

	public void SetSchedule(PaymentSchedule ps) {
		itsPaymentSchedule = ps;
	}

	public void SetMethod(PaymentMethod pm) {
		itsPaymentMethod = pm;
	}

	public void SetAffiliation(Affiliation af) {
		itsAffiliation = af;
	}

	public Affiliation GetAffiliation() {
		return itsAffiliation;
	}

	public void SetName(String name) {
		itsName = name;
	}

	public void SetAddress(String address) {
		itsAddress = address;
	}

	public String GetAddress() {
		return itsAddress;
	}

	public void Payday(Paycheck pc) {
		double grossPay = itsClassification.CalculatePay(pc);
		double deductions = itsAffiliation.CalculateDeductions(pc);
		double netPay = grossPay - deductions;
		pc.SetGrossPay(grossPay);
		pc.SetDeductions(deductions);
		pc.SetNetPay(netPay);
		itsPaymentMethod.Pay(pc);
	}

	public boolean IsPayDate(Calendar payDate) {
		return itsPaymentSchedule.IsPayDate(payDate);
	}

	public Calendar GetPayPeriodStartDate(Calendar payDate) {
		return itsPaymentSchedule.GetPayPeriodStartDate(payDate);
	}
}
