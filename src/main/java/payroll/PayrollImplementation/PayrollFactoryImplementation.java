package payroll.PayrollImplementation;

import payroll.PayrollDomain.*;
import payroll.PayrollFactory.PayrollFactory;

import java.util.Calendar;

public class PayrollFactoryImplementation implements PayrollFactory {
	@Override
	public PaymentSchedule makeBiweeklySchedule() {
		return new BiweeklySchedule();
	}

	@Override
	public PaymentClassification makeCommissionedClassification(double salary, double commissionRate) {
		return new CommissionedClassification(salary, commissionRate);
	}

	@Override
	public PaymentMethod makeDirectMethod(String bank, String account) {
		return new DirectMethod(bank, account);
	}

	@Override
	public PaymentMethod makeHoldMethod() {
		return new HoldMethod();
	}

	@Override
	public PaymentClassification makeHourlyClassification(double hourlyRate) {
		return new HourlyClassification(hourlyRate);
	}

	@Override
	public PaymentMethod makeMailMethod(String address) {
		return new MailMethod(address);
	}

	@Override
	public PaymentSchedule makeMonthlySchedule() {
		return new MonthlySchedule();
	}

	@Override
	public PaymentClassification makeSalariedClassification(double salary) {
		return new SalariedClassification(salary);
	}

	@Override
	public SalesReceipt makeSalesReceipt(Calendar salesDate, double amount) {
		return new SalesReceipt(salesDate, amount);
	}

	@Override
	public Affiliation makeUnionAffiliation(int memberId, double dues) {
		return new UnionAffiliation(memberId, dues);
	}

	@Override
	public PaymentSchedule makeWeeklySchedule() {
		return new WeeklySchedule();
	}

	@Override
	public Affiliation makeNoAffiliation() {
		return new NoAffiliation();
	}

	@Override
	public Employee makeEmployee(int empId, String name, String address) {
		return new Employee(empId, name, address);
	}

	@Override
	public Paycheck makePayCheck(Calendar payPeriodStartDate, Calendar payDate) {
		return new PaycheckImplementation(payPeriodStartDate, payDate);
	}

}
